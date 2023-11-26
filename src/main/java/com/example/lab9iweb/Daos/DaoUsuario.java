package com.example.lab9iweb.Daos;

import com.example.lab9iweb.Beans.Usuario;
import javax.swing.*;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoUsuario extends DaoBase {

    //METODO PARA RECONOCER LO HASHEADO
    public Usuario validarUsuarioPasswordHashed(String correo, String password){
        Usuario user = new Usuario();
        //Como el nombre de Usuario es unico en la base de datos no ocurriran conflictos o solo se obtendra una fila
        String sql = "SELECT * FROM usuario WHERE correo = ? AND password = SHA2(?,256)";

        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, correo);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                if (rs.next()) {
                    user.setIdUsuario(rs.getInt(1) );
                    user.setNombre(rs.getString(2));
                    user.setCorreo(rs.getString(3));
                    user.setIdRol( rs.getInt(5));
                    new DaoUsuario().actualizamosDatosAlIngresar(user.getIdUsuario());
                    return user;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void actualizamosDatosAlIngresar(int idUsuario){
        String sql = "UPDATE USUARIO SET ultimo_ingreso = noW() , cantidad_ingresos = cantidad_ingresos +1 WHERE idUsuario = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idUsuario);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}


