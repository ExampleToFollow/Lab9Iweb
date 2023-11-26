package com.example.lab9iweb.Servlets;

import com.example.lab9iweb.Beans.Usuario;
import com.example.lab9iweb.Daos.DaoCursoHasDocente;
import com.example.lab9iweb.Daos.DaoFacultad;
import com.example.lab9iweb.Daos.DaoFacultadHasDecano;
import com.example.lab9iweb.Daos.DaoUsuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.ArrayList;

import java.io.IOException;

@WebServlet(name = "GestionProfesoresServlet", value = "/GestionProfesoresServlet")
public class GestionProfesoresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        switch (action){
            case "lista":
                //Salta al listado
                Usuario user = (Usuario) request.getSession().getAttribute("usuario");
                int idFacultad = new DaoFacultadHasDecano().obtenerIdFacultad(user.getIdUsuario());
                ArrayList<Integer> lista  = new DaoCursoHasDocente().listarIdCursosDeDocentesDeUnaFacultad(idFacultad);
                request.setAttribute("listaProfesoresDeFacultad", lista );
                request.setAttribute("listaProfesoresSinCurso", new DaoUsuario().listarIdProfesoresSinCurso());
                request.getRequestDispatcher("VistasDecano/listaProfesoresFacultad.jsp").forward(request,response);
                break;
            case "formCrear":
                //Salta a la vista para el creado
                request.getRequestDispatcher("VistasDecano/CrearProfesores.jsp").forward(request,response);
                break;
            case "editar":
                String idProfesor =  request.getParameter("id");
                request.setAttribute("idProfesor", idProfesor);
                //Salta a la vista para editar
                request.getRequestDispatcher("VistasDecano/EditarProfesores.jsp").forward(request,response);
                break;
            case "borrar":
                String idd = request.getParameter("id");
                new DaoUsuario().deleteProfesor(Integer.parseInt(idd));
                //Metodo Borrado
                response.sendRedirect(request.getContextPath() + "/GestionProfesoresServlet");
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");
        switch(action){
            case("crear"):
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");
                String password = request.getParameter("password");
                new DaoUsuario().registrarNuevoProfesor(nombre, correo, password);
                response.sendRedirect("GestionProfesoresServlet");
                break;
            case("edit"):
                String idProfesor = request.getParameter("idProfesor");
                String nuevoNombre = request.getParameter("nombre");
                new DaoUsuario().actualizarNombre(nuevoNombre, Integer.parseInt(idProfesor));
                response.sendRedirect("GestionProfesoresServlet");
                break;
        }


    }
}

