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

    public int getCantidadDeEvaluacionesEnSemestreXIdCurso( int idCurso ){
        int cantidad = 0 ;
        String sql = "Select count(*)  from evaluaciones where idsemestre = 1  group by idcurso having idCurso = ?;\n";
        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCurso);

            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                while (rs.next()) {
                    cantidad =  rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cantidad;
    }

    public Evaluaciones getEvaluacionesXIdEvaluacion(int idEvaluacion){
        Evaluaciones e =  new Evaluaciones();
        String sql = "SELECT * FROM Evaluaciones WHERE idEvaluaciones = ?  ";

        try (Connection conn = super.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idEvaluacion);
            try (ResultSet rs = pstmt.executeQuery();) {
                //Guardamos todos sus datos para poder iniciar la sesion , esto ocurre cuando se loguea correctamente
                while (rs.next()) {
                    e.setIdEvaluaciones(rs.getInt(1));
                    e.setNombreEstudiantes(rs.getString(2));
                    e.setCodigoEstudiantes(rs.getString(3));
                    e.setCorreoEstudiantes(rs.getString(4));
                    e.setNota(rs.getInt(5));
                    e.setIdCurso(rs.getInt(6));
                    e.setIdSemestre(rs.getInt(7));
                    e.setFechaRegistro(rs.getString(8));
                    e.setFechaEdicion(rs.getString(9));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }




    public void  registrarEvaluacion(String nombreAlumno , String codigo ,  String correo , int nota, int idProfesor){
        String sql = "INSERT INTO evaluaciones (nombre_estudiantes, codigo_estudiantes , correo_estudiantes , nota  ,idCurso , idSemestre, fecha_registro, fecha_edicion)\n" +
                " VALUES (? , ?, ?, ?, ? , ?, now(), now());";

        try(Connection connection = super.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,nombreAlumno);
            pstmt.setString(2,codigo);
            pstmt.setString(3,correo);
            pstmt.setInt(4,nota);
            pstmt.setInt(5,new DaoCursoHasDocente().getIdCursoxDocente(idProfesor));
            pstmt.setInt(6,new DaoSemestre().getSemestreActual().getIdSemestre());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void actualizarEvaluacion(int idEvaluacion,String nuevoNombre, String nuevoCodigo, String nuevoCorreo, int nuevaNota ){
        String sql = "update Evaluaciones set nombre_estudiantes = ? , codigo_estudiantes = ? , correo_estudiantes = ? , nota = ? , fecha_edicion= now() where idEvaluaciones = ? ";

        try(Connection connection = super.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,nuevoNombre);
            pstmt.setString(2,nuevoCodigo);
            pstmt.setString(3,nuevoCorreo);
            pstmt.setInt(4,nuevaNota);
            pstmt.setInt(5,idEvaluacion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarEvaluacion(int idd){
        String sql = "delete from Evaluaciones where idEvaluaciones = ?";

        try(Connection connection =super.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,idd);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
