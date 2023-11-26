package com.example.lab9iweb.Servlets;

import com.example.lab9iweb.Beans.Curso;
import com.example.lab9iweb.Beans.Evaluaciones;
import com.example.lab9iweb.Beans.Usuario;
import com.example.lab9iweb.Daos.DaoCurso;
import com.example.lab9iweb.Daos.DaoCursoHasDocente;
import com.example.lab9iweb.Daos.DaoEvaluaciones;
import com.example.lab9iweb.Daos.DaoSemestre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import java.io.IOException;

@WebServlet(name = "GestionEvaluacionesServlet", value = "/GestionEvaluacionesServlet")
public class GestionEvaluacionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        switch (action){
            case "lista":
                //Obtenemos lista de evaluaciaones

                Usuario user = (Usuario) request.getSession().getAttribute("usuario");
                int idCurso = new DaoCursoHasDocente().getIdCursoxDocente(user.getIdUsuario());
                Curso curso = new DaoCurso().getCursoxIdCurso(idCurso);
                ArrayList<Integer> listaSemestres = new DaoSemestre().getListaIdSemestres();

                ArrayList<Evaluaciones> listaEvaluaciones =  new DaoEvaluaciones().getListaEvaluacionesXCurso(curso.getIdCurso());
                request.setAttribute("listaSemestres", listaSemestres);
                request.setAttribute("listaEvaluaciones", listaEvaluaciones);
                //Salta a listado de evaluaciones
                request.getRequestDispatcher("VistasProfesores/menuDocente.jsp").forward(request, response);
                break;
            case "formCrear":
                //Salta a la vista para el creado
                request.getRequestDispatcher("VistasProfesores/CrearEvaluaciones.jsp").forward(request,response);
                break;
            case "editar":
                String id = request.getParameter("id");
                //Salta a la vista para editar
                request.getRequestDispatcher("VistasProfesores/EditarEvaluaciones.jsp").forward(request,response);
                break;
            case "borrar":
                String idd = request.getParameter("id");
                //Metodo Borrado
                response.sendRedirect(request.getContextPath() + "/GestionEvaluacionesServlet");
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

