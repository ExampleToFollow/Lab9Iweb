package com.example.lab9iweb.Servlets;

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
                request.getRequestDispatcher("VistasDecano/listaProfesoresFacultad.jsp").forward(request,response);
                break;
            case "formCrear":
                //Salta a la vista para el creado
                request.getRequestDispatcher("VistasDecano/CrearProfesores.jsp").forward(request,response);
                break;
            case "editar":
                String id = request.getParameter("id");
                //Salta a la vista para editar
                request.getRequestDispatcher("VistasDecano/EditarProfesores.jsp").forward(request,response);
                break;
            case "borrar":
                String idd = request.getParameter("id");
                //Metodo Borrado
                response.sendRedirect(request.getContextPath() + "/GestionProfesoresServlet");
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

