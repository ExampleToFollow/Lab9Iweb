package com.example.lab9iweb.Servlets;

import com.example.lab9iweb.Beans.Curso;
import com.example.lab9iweb.Beans.Usuario;
import com.example.lab9iweb.Daos.DaoCurso;
import com.example.lab9iweb.Daos.DaoFacultadHasDecano;
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
                request.getRequestDispatcher("VistasDecano/CrearCursos.jsp").forward(request,response);
                break;
            case "editar":
                String id = request.getParameter("id");
                //Salta a la vista para editar
                request.getRequestDispatcher("VistasDecano/EditarCursos.jsp").forward(request,response);
                break;
            case "borrar":
                String idd = request.getParameter("id");
                //Metodo Borrado
                response.sendRedirect(request.getContextPath() + "/GestionCursosServlet");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

