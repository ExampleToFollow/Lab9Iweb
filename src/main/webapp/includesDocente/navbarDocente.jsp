<%@ page import="com.example.lab9iweb.Beans.Usuario" %>
<%@ page import="com.example.lab9iweb.Daos.DaoCurso" %>
<%@ page import="com.example.lab9iweb.Daos.DaoCursoHasDocente" %>
<%@ page import="com.example.lab9iweb.Daos.DaoSemestre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.ArrayList" %>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item mx-2">
                    <a class="nav-link active" aria-current="page" >Bienvenid@ docente,  <%= ((Usuario) request.getSession().getAttribute("usuario")).getNombre()%></a>
                </li>
                <li class="nav-item mx-2">
                    <%Usuario user = (Usuario) request.getSession().getAttribute("usuario");%>
                    <%int idCurso = new DaoCursoHasDocente().getIdCursoxDocente(user.getIdUsuario());%>

                    <a class="nav-link active"  href="<%=request.getContextPath()%>/GestionEvaluacionesServlet" >Evaluaciones del curso <%= new DaoCurso().getCursoxIdCurso(idCurso).getNombre()%></a>
                </li>
                <li class="nav-item mx-2">
                    <%ArrayList<Integer> listaSemestres1 =  (ArrayList<Integer>) request.getAttribute("listaSemestres");
                    Integer idSemestreActual = 0;
                    bucle1:
                    for(Integer idSemestre :  listaSemestres1){
                        if(new DaoSemestre().getSemestrexIdSemestre((int) idSemestre).isHabilitado()){
                            idSemestreActual = idSemestre;
                            break bucle1;
                        }
                    }

                    %>
                    <a class="nav-link active"  >Semestre actual : <%= new DaoSemestre().getSemestrexIdSemestre(idSemestreActual).getNombre()%></a>
                </li>
            </ul>

            <button type="button" class="btn btn-danger" onclick="cerrarSesion()">Cerrar Sesión</button>

            <script>
                function cerrarSesion() {
                    // Redirige a tu enlace deseado
                    window.location.href = "<%=request.getContextPath()%>/LogginServlet?action=logout";
                }
            </script>
        </div>
    </div>
</nav>