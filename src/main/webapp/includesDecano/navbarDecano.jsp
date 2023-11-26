<%@ page import="com.example.lab9iweb.Beans.Usuario" %>
<%@ page import="com.example.lab9iweb.Daos.DaoFacultadHasDecano" %>
<%@ page import="com.example.lab9iweb.Daos.DaoFacultad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item mx-2">
                    <!--<a class="nav-link active" aria-current="page" href="#">Menú</a>-->
                    <a class="nav-link active" aria-current="page" >Bienvenid@ decan@ , <%= ((Usuario) request.getSession().getAttribute("usuario")).getNombre()%></a>
                </li>
                <li class="nav-item mx-2">
                    <%Usuario user =(Usuario) request.getSession().getAttribute("usuario"); %>
                    <%int idFacultad = new DaoFacultadHasDecano().obtenerIdFacultad(user.getIdUsuario());%>
                    <a class="nav-link active" href="<%=request.getContextPath()%>/GestionCursosServlet">Cursos de la facultad <%=  new DaoFacultad().getFacultadXIdFacultad(idFacultad).getNombre()%></a>
                </li>
                <li class="nav-item mx-2">
                    <!--<a class="nav-link disabled" aria-disabled="true">Disabled</a>-->
                    <a class="nav-link active" href="<%=request.getContextPath()%>/GestionProfesoresServlet">Profesores de la facultad </a>
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