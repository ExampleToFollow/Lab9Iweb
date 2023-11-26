package com.example.lab9iweb.Daos;

import com.example.lab9iweb.Beans.Facultad;
import javax.swing.*;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoFacultad extends DaoBase{

    public Facultad getFacultadXIdFacultad(int idFacultad){
        Facultad facultad=  new Facultad();
        String sql = "SELECT * FROM FACULTAD WHERE IDFACULTAD= ? ";

        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idFacultad);

            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                if (rs.next()) {
                    facultad.setIdFacultad(rs.getInt(1));
                    facultad.setNombre(rs.getString(2));
                    facultad.setIdUniversidad(rs.getInt(3));
                    facultad.setUniversidad(new DaoUniversidad().getUniversidadxIdUniversidad(facultad.getIdUniversidad()));
                    facultad.setFechaRegistro(rs.getString(4));
                    facultad.setFechaEdicion(rs.getString(5));
                    return facultad;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
