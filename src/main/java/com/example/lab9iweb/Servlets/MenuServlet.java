package com.example.lab9iweb.Servlets;

import com.example.lab9iweb.Beans.Usuario;
import com.example.lab9iweb.Daos.DaoCursoHasDocente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.ArrayList;

import java.io.IOException;

@WebServlet(name = "MenuServlet", value = "/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if((Usuario) request.getSession().getAttribute("usuario") != null) { //Si se inicia session
            //Elegimos la vista dependiendo del rol:
            Usuario user  = (Usuario) request.getSession().getAttribute("usuario");
            int idRol = ((Usuario)request.getSession().getAttribute("usuario")).getIdRol();
            switch(idRol){
                case(1):
                    request.getRequestDispatcher("vistaAuxiliar.jsp").forward(request, response);

                    //No se implementa el flujo como administrador
                    break;
                case(2):
                    request.getRequestDispatcher("vistaAuxiliar.jsp").forward(request, response);
                    //No se implementa el flujo como rector
                    break;
                case(3):
                    //Salta a una vista preparada de decano de una facultad
                    response.sendRedirect("GestionCursosServlet");
                    break;
                case(4):
                    //Salta a una vista de preparada de docente de
                    if(new DaoCursoHasDocente().getIdCursoxDocente(user.getIdUsuario()) != 0) {
                        response.sendRedirect("GestionEvaluacionesServlet");
                    }else{
                        request.getRequestDispatcher("vistaAuxiliarDocente.jsp").forward(request, response);
                    }
                    break;
            }
        }else{
            RequestDispatcher view = request.getRequestDispatcher("Loging/Loging.jsp");
            view.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

