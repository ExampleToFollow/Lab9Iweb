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

public class DaoCursoHasDocente extends DaoBase{
    public int getIdCursoxDocente(int idDocente){
        int idCurso = 0 ;
        String sql = "SELECT idCurso FROM curso_has_docente WHERE idDocente= ? ";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idDocente);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    idCurso =  rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCurso;
    }

    public int getIdDocentexIdCurso(int idCurso){
        int idDocente = 0 ;
        String sql = "SELECT idDocente FROM curso_has_docente WHERE idCurso= ? ";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCurso);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    idDocente =  rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idDocente;
    }

    public ArrayList<Integer> listarIdCursosDeDocentesDeUnaFacultad (int idFacultad ){
        ArrayList<Integer> lista = new ArrayList<Integer>();
        String sql = "SELECT IDDOCENTE FROM CURSO_HAS_DOCENTE WHERE IDCURSO IN (SELECT IDCURSO FROM CURSO WHERE IDFACULTAD= ? )";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idFacultad);

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
