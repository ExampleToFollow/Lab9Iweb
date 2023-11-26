<%@ page import="com.example.lab9iweb.Beans.Usuario" %>
<%@ page import="com.example.lab9iweb.Daos.DaoUsuario" %><%--
  Created by IntelliJ IDEA.
  User: Hineill
  Date: 25/11/2023
  Time: 01:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9iweb.Daos.DaoCurso" %>
<%@ page import="com.example.lab9iweb.Daos.DaoCursoHasDocente" %>

<html lang="en" data-bs-theme="auto">
<head><script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.118.2">
    <title>Carousel Template · Bootstrap v5.3</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/carousel/">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">


    <style>
        /* Estilos generales */
        body {
            font-family: 'Titillium Web', sans-serif;
        }

        header {
            background-color: #322D31;
            color: white;
            padding: 20px 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        footer {
            background-color: #322D31;
            color: white;
            font-family: 'Titillium Web', sans-serif;
            padding: 20px 0;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            text-align: center;
        }

        /* Estilos del carrusel */


        /* Estilos de las secciones de características */
        .featurette {
            padding: 40px 0;
            text-align: center;
        }

        .featurette h2 {
            font-size: 2.5rem;
            margin-bottom: 20px;
        }

        .featurette p {
            font-size: 1.2rem;
        }

        .featurette-image {
            border-radius: 10px;
        }

        /* Otros estilos */
        .btn-bd-primary {
            --bd-violet-bg: #712cf9;
            --bd-violet-rgb: 112.520718, 44.062154, 249.437846;
            /* ... (resto de los estilos de botones) ... */
        }
    </style>

    <!-- Custom styles for this template -->


</head>
<body>

<!--
<svg xmlns="http://www.w3.org/2000/svg" class="d-none">
    <symbol id="check2" viewBox="0 0 16 16">
        <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
    </symbol>
    <symbol id="circle-half" viewBox="0 0 16 16">
        <path d="M8 15A7 7 0 1 0 8 1v14zm0 1A8 8 0 1 1 8 0a8 8 0 0 1 0 16z"/>
    </symbol>
    <symbol id="moon-stars-fill" viewBox="0 0 16 16">
        <path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"/>
        <path d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z"/>
    </symbol>
    <symbol id="sun-fill" viewBox="0 0 16 16">
        <path d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z"/>
    </symbol>
</svg>
-->



<header data-bs-theme="dark">
    <jsp:include page="../includesDecano/navbarDecano.jsp"></jsp:include>
</header>

<main>
    <body>
    <div class='container'>

        <div class="row mb-5 mt-4">
            <div class="col-md-7">
                <h1 class=''>Lista de Profesores en la facultad</h1>
            </div>
            <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
                <a href="<%= request.getContextPath()%>/GestionProfesoresServlet?action=formCrear" class="btn btn-primary">
                    Registrar Docente</a>
            </div>
        </div>
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Curso dictado</th>
                <th>Ultimo ingreso</th>
                <th>Cantidad Ingresos</th>
                <th>Fecha Registro</th>
                <th>Editar</th>
            </tr>
        <% ArrayList<Integer> lista  = (ArrayList<Integer>) request.getAttribute("listaProfesoresDeFacultad"); %>

            <%for(Integer idProfes : lista ){%>
            <%Usuario profe = new DaoUsuario().getUsuarioXId((int) idProfes); %>
            <tr>
                <td><%=profe.getIdUsuario()%>
                </td>
                <td><%=profe.getNombre()%>
                </td>
                <td><%=profe.getCorreo()%>
                </td>
                <td><%= new DaoCurso().getCursoxIdCurso(new DaoCursoHasDocente().getIdCursoxDocente(profe.getIdUsuario())).getNombre()%>
                </td>
                <td><%=profe.getUltimoIngreso()%>
                </td>
                <td><%=profe.getCantidadIngresos()%>
                </td>
                <td><%=profe.getFechaRegistro()%>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/GestionProfesoresServlet?action=editar&id=<%= profe.getIdUsuario()%>">
                        Editar
                    </a>
                </td>

            </tr>
        <%}%>
        </table>
        <div class="row mb-5 mt-4">
            <div class="col-md-7">
                <h1 class=''>Lista de Profesores sin cursos asignados </h1>
            </div>

        </div>
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Ultimo ingreso</th>
                <th>Cantidad Ingresos</th>
                <th>Fecha Registro</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>

            <%ArrayList<Integer> listaProfeSinCurso = (ArrayList<Integer>) request.getAttribute("listaProfesoresSinCurso");%>
            <%for(Integer idProfes : listaProfeSinCurso ){%>
            <%Usuario profe = new DaoUsuario().getUsuarioXId((int) idProfes); %>
            <tr>
                <td><%=profe.getIdUsuario()%>
                </td>
                <td><%=profe.getNombre()%>
                </td>
                <td><%=profe.getCorreo()%>
                </td>
                <td><%=profe.getUltimoIngreso()%>
                </td>
                <td><%=profe.getCantidadIngresos()%>
                </td>
                <td><%=profe.getFechaRegistro()%>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/GestionProfesoresServlet?action=editar&id=<%= profe.getIdUsuario()%>">
                        Editar
                    </a>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/GestionProfesoresServlet?action=borrar&id=<%= profe.getIdUsuario()%>">
                        Borrar
                    </a>
                </td>
            </tr>
            <%}%>
        </table>
    </div>
    </body>


</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>




</html>