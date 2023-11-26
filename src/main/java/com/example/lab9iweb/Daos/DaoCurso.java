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

    public Curso getCursoxNombre(String nombre){
        Curso curso = new Curso();
        String sql = "SELECT * FROM Curso WHERE nombre= ? ";

        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, nombre);
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

    public void actualizarNombre(String nuevoNombre, int  idCurso){
        String sql = "update Curso set nombre= ? , fecha_edicion= now() where idCurso= ? ";

        try(Connection connection = super.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,nuevoNombre);

            pstmt.setInt(2,idCurso);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registrarNuevoCurso(String nombreCurso, String codigo,int idDocentes ,int idFacultad){
        String sql = "INSERT INTO curso (codigo, nombre , idFacultad , fecha_registro, fecha_edicion)\n" +
                " VALUES (? , ?, ?, now(), now());";

        try(Connection connection = super.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,codigo);
            pstmt.setString(2,nombreCurso);
            pstmt.setInt(3,idFacultad);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Insertamos el nuevo valor en la tabla
        int idCurso = new DaoCurso().getCursoxNombre(nombreCurso).getIdCurso();
        new DaoCursoHasDocente().insertar(idCurso, idDocentes);
    }

    public void eliminarCurso(int idCurso){
        int  idDocente =  new DaoCursoHasDocente().getIdDocentexIdCurso(idCurso);
        new DaoCursoHasDocente().eliminarTabla(idCurso,idDocente);
        String sql = "delete from curso where idCurso = ?";

        try(Connection connection =super.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,idCurso);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
