package com.example.lab9iweb.Daos;

import com.example.lab9iweb.Beans.Universidad;
import javax.swing.*;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoUniversidad extends DaoBase {
    public Universidad getUniversidadxIdUniversidad(int idUniversidad) {
        Universidad universidad =  new Universidad();
        String sql = "SELECT * FROM Universidad WHERE idUniversidad= ?";

        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idUniversidad);

            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                if (rs.next()) {
                    universidad.setIdUniversidad(rs.getInt(1));
                    universidad.setNombre(rs.getString(2));
                    universidad.setLogoUrl(rs.getString(3));
                    universidad.setIdAdministrador(rs.getInt(4));
                    universidad.setFechaRegistro(rs.getString(5));
                    universidad.setFechaEdicion(rs.getString(6));
                    return universidad;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
