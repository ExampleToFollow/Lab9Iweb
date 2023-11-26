package com.example.lab9iweb.Daos;

import com.example.lab9iweb.Beans.Semestre;

import javax.swing.*;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoSemestre extends DaoBase{
    public Semestre getSemestrexIdSemestre(int idSemestre){
        Semestre semestre = new Semestre();
        String sql = "SELECT * FROM Semestre WHERE idSemestre= ?";

        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idSemestre);

            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                if (rs.next()) {
                    semestre.setIdSemestre(rs.getInt(1));
                    semestre.setNombre(rs.getString(2));
                    semestre.setIdAdministrador(rs.getInt(3));
                    semestre.setHabilitado(rs.getBoolean(4));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return semestre;
    }

    public ArrayList<Integer> getListaIdSemestres(){
        ArrayList<Integer> lista  =  new ArrayList<Integer>();
        String sql = "Select idSemestre from Semestre ;";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                while (rs.next()) {
                    lista.add(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

}
