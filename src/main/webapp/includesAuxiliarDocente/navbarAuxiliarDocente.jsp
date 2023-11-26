<%@ page import="com.example.lab9iweb.Beans.Usuario" %>
<%@ page import="com.example.lab9iweb.Daos.DaoCurso" %>
<%@ page import="com.example.lab9iweb.Daos.DaoCursoHasDocente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item mx-2">
                    <a class="nav-link active" aria-current="page" >Bienvenid@ docente , <%= ((Usuario) request.getSession().getAttribute("usuario")).getNombre()%></a>
                </li>
                <li class="nav-item mx-2">
                    <a class="nav-link active"  > No estás dictando ningun curso </a>
                </li>

            </ul>

            <button type="button" class="btn btn-danger" onclick="cerrarSesion()">Cerrar Sesión</button>

            <script>
                function cerrarSesion() {
                    window.location.href = "<%=request.getContextPath()%>/LogginServlet?action=logout";
                }
            </script>
        </div>
    </div>
</nav>
