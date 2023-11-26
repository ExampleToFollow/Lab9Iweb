package com.example.lab9iweb.Daos;

import com.example.lab9iweb.Beans.Curso;

public class DaoCurso extends DaoBase {
    public Curso getCursoxIdCurso(int idCurso){
        Curso curso = new Curso();
        String sql = "SELECT * FROM usuario WHERE idCurso= ? ";

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

}
