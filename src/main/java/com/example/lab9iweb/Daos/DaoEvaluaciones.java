package com.example.lab9iweb.Daos;

import com.example.lab9iweb.Beans.Evaluaciones;

import javax.swing.*;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoEvaluaciones extends DaoBase {

    public ArrayList<Evaluaciones> getListaEvaluacionesXCurso(int idCurso ){
        ArrayList<Evaluaciones> lista = new ArrayList<Evaluaciones>();
        String sql = "SELECT * FROM Evaluaciones WHERE idCurso= ? ";

        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCurso);

            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                while (rs.next()) {
                    Evaluaciones e =  new Evaluaciones();
                    e.setIdEvaluaciones(rs.getInt(1));
                    e.setNombreEstudiantes(rs.getString(2));
                    e.setCodigoEstudiantes(rs.getString(3));
                    e.setCorreoEstudiantes(rs.getString(4));
                    e.setNota(rs.getInt(5));
                    e.setIdCurso(rs.getInt(6));
                    e.setIdSemestre(rs.getInt(7));
                    e.setFechaRegistro(rs.getString(8));
                    e.setFechaEdicion(rs.getString(9));
                    lista.add(e);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
