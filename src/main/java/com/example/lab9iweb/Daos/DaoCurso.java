package com.example.lab9iweb.Daos;

import com.example.lab9iweb.Beans.Curso;
import com.example.lab9iweb.Beans.Evaluaciones;

import javax.swing.*;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoCurso extends DaoBase {
    public Curso getCursoxIdCurso(int idCurso){
        Curso curso = new Curso();
        String sql = "SELECT * FROM Curso WHERE idCurso= ? ";

        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCurso);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    curso.setIdCurso(rs.getInt(1));
                    curso.setCodigo(rs.getString(2));
                    curso.setNombre(rs.getString(3));
                    curso.setIdFacultad(rs.getInt(4));
                    curso.setFacultad(new DaoFacultad().getFacultadXIdFacultad(curso.getIdFacultad()));
                    curso.setFechaRegistro(rs.getString(5));
                    curso.setFechaEdicion(rs.getString(6));
                    return curso;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public ArrayList<Curso> listarCursosXFacultad(int idFacultad){
        ArrayList<Curso> lista = new ArrayList<Curso>();
        String sql = "SELECT * FROM curso WHERE idFacultad= ? ";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idFacultad);

            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                while (rs.next()) {
                    Curso c =  new Curso();
                    c.setIdCurso(rs.getInt(1));
                    c.setCodigo(rs.getString(2));
                    c.setNombre(rs.getString(3));
                    c.setIdFacultad(rs.getInt(4));
                    c.setFacultad(new DaoFacultad().getFacultadXIdFacultad(c.getIdFacultad()));
                    c.setFechaRegistro(rs.getString(5));
                    c.setFechaEdicion(rs.getString(6));
                    lista.add(c);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return lista;
    }

}
