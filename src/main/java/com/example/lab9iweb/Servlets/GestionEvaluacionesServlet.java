package com.example.lab9iweb.Servlets;

import com.example.lab9iweb.Beans.Curso;
import com.example.lab9iweb.Beans.Evaluaciones;
import com.example.lab9iweb.Beans.Usuario;
import com.example.lab9iweb.Daos.*;
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
            int idSemestreFiltrado = request.getParameter("idSemestre") == null ? new DaoSemestre().getSemestreActual().getIdSemestre() : Integer.parseInt(request.getParameter("idSemestre"));
            ArrayList<Evaluaciones> listaEvaluaciones =  new DaoEvaluaciones().getListaEvaluacionesXCurso(curso.getIdCurso() , idSemestreFiltrado);
            request.setAttribute("idSemestreFiltrado","" + idSemestreFiltrado);
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
            String idEvaluacion = request.getParameter("id");
            request.setAttribute("idEvaluacion", idEvaluacion);
            //Salta a la vista para editar
            request.getRequestDispatcher("VistasProfesores/EditarEvaluaciones.jsp").forward(request,response);
            break;
         case "borrar":
            int  idd = Integer.parseInt(request.getParameter("id"));
            new DaoEvaluaciones().eliminarEvaluacion(idd);
            //Metodo Borrado
            response.sendRedirect(request.getContextPath() + "/GestionEvaluacionesServlet");
            break;
      }
   }


   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

      switch (action){
         case "lista":
            //Salta al listado
            String idd = (String ) request.getParameter("idSemestre");
            request.setAttribute("idSemestre",idd );
            response.sendRedirect("GestionEvaluacionesServlet");
            break;
         case "formCrear":
            //Salta a la vista para el creado
            //Le damos una lista con los profesores necesarios
            Usuario u = (Usuario) request.getSession().getAttribute("usuario");
            String nombreAlumno = request.getParameter("nombre");
            String codigo = request.getParameter("codigo");
            String correo = request.getParameter("correo");
            String nota = request.getParameter("nota");
            new DaoEvaluaciones().registrarEvaluacion(nombreAlumno , codigo ,  correo , Integer.parseInt(nota) , u.getIdUsuario());
            response.sendRedirect("GestionEvaluacionesServlet");
            break;
         case "editar":
            int idEvaluacion = Integer.parseInt(request.getParameter("idEvaluacion"));
            String nuevoNombre = request.getParameter("nombre");
            String nuevoCodigo = request.getParameter("codigo");
            String nuevoCorreo = request.getParameter("correo");
            int nuevoNota = Integer.parseInt(request.getParameter("nota"));
            new DaoEvaluaciones().actualizarEvaluacion(idEvaluacion,nuevoNombre, nuevoCodigo, nuevoCorreo, nuevoNota );
            response.sendRedirect("GestionEvaluacionesServlet");
            break;
         case "borrar":
            response.sendRedirect(request.getContextPath() + "/GestionEvaluacionesServlet");
            break;
      }
   }
}

