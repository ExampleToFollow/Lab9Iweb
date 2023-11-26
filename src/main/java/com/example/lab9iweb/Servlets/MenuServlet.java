package com.example.lab9iweb.Servlets;

import com.example.lab9iweb.Beans.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MenuServlet", value = "/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if((Usuario) request.getSession().getAttribute("usuario") != null) { //Si se inicia session
            //Elegimos la vista dependiendo del rol:
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
                    request.getRequestDispatcher("VistasDecano/menuDecano.jsp").forward(request, response);

                    break;
                case(4):
                    //Salta a una vista de preparada de docente de
                    request.getRequestDispatcher("VistasProfesores/menuDocente.jsp").forward(request, response);

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

