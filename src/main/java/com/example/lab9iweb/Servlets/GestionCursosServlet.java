package com.example.lab9iweb.Servlets;

import com.example.lab9iweb.Beans.Curso;
import com.example.lab9iweb.Beans.Usuario;
import com.example.lab9iweb.Daos.DaoCurso;
import com.example.lab9iweb.Daos.DaoFacultadHasDecano;
import com.example.lab9iweb.Daos.DaoUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import java.io.IOException;

@WebServlet(name = "GestionCursosServlet", value = "/GestionCursosServlet")
public class GestionCursosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        switch (action){
            case "lista":
                //Salta al listado
                Usuario user = (Usuario) request.getSession().getAttribute("usuario");
                int idFacultad = new DaoFacultadHasDecano().obtenerIdFacultad(user.getIdUsuario());
                ArrayList<Curso> lista = new DaoCurso().listarCursosXFacultad(idFacultad);
                request.setAttribute("listaCursos", lista);
                request.getRequestDispatcher("VistasDecano/menuDecano.jsp").forward(request,response);
                break;
            case "formCrear":
                //Salta a la vista para el creado
                //Le damos una lista con los profesores necesarios
                request.setAttribute("listaProfesoresSinCurso", new DaoUsuario().listarIdProfesoresSinCurso());
                request.getRequestDispatcher("VistasDecano/CrearCursos.jsp").forward(request,response);
                break;
            case "editar":
                String idCurso = request.getParameter("id");
                request.setAttribute("idCurso", idCurso);
                //Salta a la vista para editar
                request.getRequestDispatcher("VistasDecano/EditarCursos.jsp").forward(request,response);
                break;
            case "borrar":
                Integer idd = Integer.parseInt(request.getParameter("id"));
                new DaoCurso().eliminarCurso((int)idd);
                //Metodo Borrado
                response.sendRedirect(request.getContextPath() + "/GestionCursosServlet");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");
        switch(action){
            case("crear"):
                String nombreCurso = request.getParameter("nombre");
                String codigo = request.getParameter("codigo");
                Integer idDocentes = Integer.parseInt(request.getParameter("idDocentes"));
                if(nombreCurso.equals("") || codigo.equals("") ) {
                    response.sendRedirect("GestionCursosServlet?action=formCrear");
                }else{
                    int idFacultad = new DaoFacultadHasDecano().obtenerIdFacultad(((Usuario) request.getSession().getAttribute("usuario")).getIdUsuario());
                    new DaoCurso().registrarNuevoCurso(nombreCurso, codigo, idDocentes, idFacultad);
                    response.sendRedirect("GestionCursosServlet");
                }
                break;
            case("edit"):
                String idCurso = request.getParameter("idCurso");
                String nuevoNombre = request.getParameter("nombre");
                new DaoCurso().actualizarNombre(nuevoNombre, Integer.parseInt(idCurso));
                response.sendRedirect("GestionCursosServlet");
                break;
        }
    }
}

