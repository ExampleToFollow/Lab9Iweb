package com.example.lab9iweb.Daos;
import javax.swing.*;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoFacultadHasDecano extends DaoBase {

    public int obtenerIdFacultad(int idDecano){
        int idFacultad =0 ;
        String sql = "SELECT * FROM FACULTAD_has_decano WHERE IDdecano= ? ";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idDecano);

            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                if (rs.next()) {
                    idFacultad = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idFacultad;
    }
}
