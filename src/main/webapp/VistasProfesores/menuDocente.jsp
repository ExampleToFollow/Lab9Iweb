<%@ page import="com.example.lab9iweb.Beans.Evaluaciones" %><%--
  Created by IntelliJ IDEA.
  User: Hineill
  Date: 25/11/2023
  Time: 01:14
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9iweb.Daos.DaoSemestre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

</head>
<body>

<header data-bs-theme="dark">
    <jsp:include page="../includesDocente/navbarDocente.jsp"></jsp:include>
</header>

<main>
    <body>
    <div class='container'>
        <%ArrayList<Integer> listaSemestres =  (ArrayList<Integer>) request.getAttribute("listaSemestres");%>
        <%Integer SemestreActual = 0;
        bucle1:
        for(Integer idSemestre :  listaSemestres){
        if(new DaoSemestre().getSemestrexIdSemestre((int) idSemestre).isHabilitado()){
            SemestreActual = idSemestre;
        break bucle1;
        }
        }
        %>
        <div class="row mb-5 mt-4">
            <div class="col-md-7">
                <h1 class=''>Lista de Evaluaciones <%= (String) request.getAttribute("idSemestreFiltrado") == null ? new DaoSemestre().getSemestrexIdSemestre(SemestreActual).getNombre() : new DaoSemestre().getSemestrexIdSemestre(Integer.parseInt((String) request.getAttribute("idSemestreFiltrado"))).getNombre()%></h1>
            </div>
            <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
                <a href="<%= request.getContextPath()%>/GestionEvaluacionesServlet?action=formCrear" class="btn btn-primary">
                    Registrar evaluaciones</a>
            </div>
        </div>

            <form method="get" action="GestionEvaluacionesServlet" >

                <div class="mb-3">
                    <label for="idSemestre">Semestres</label>
                    <select name="idSemestre" class="form-select" id="idSemestre">
                        <%for(Integer idSemestres : listaSemestres ){%>
                        <option value="<%=idSemestres%>"> <%= new DaoSemestre().getSemestrexIdSemestre(idSemestres).getNombre()%></option>
                        <%}%>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Filtrar</button>
            </form>
        <table class="table">

            <tr>
                <th>Id</th>
                <th>Estudiantes </th>
                <th>Código </th>
                <th>Correo</th>
                <th>Nota</th>
                <th>Semestre</th>
                <th>Fecha Registro</th>
                <th>Fecha Edicion</th>
            </tr>
            <%ArrayList<Evaluaciones> lista=  (ArrayList<Evaluaciones>) request.getAttribute("listaEvaluaciones");  %>
            <%for(Evaluaciones e : lista){ %>
            <tr>
                <td><%= e.getIdEvaluaciones()%>
                </td>
                <td><%= e.getNombreEstudiantes()%>
                </td>
                <td><%= e.getCodigoEstudiantes()%>
                </td>
                <td><%= e.getCorreoEstudiantes()%>
                </td>
                <td><%= e.getNota()%>
                </td>
                <td><%= new DaoSemestre().getSemestrexIdSemestre(e.getIdSemestre()).getNombre()%>
                </td>
                <td><%= e.getFechaRegistro()%>
                </td>
                <td><%= e.getFechaEdicion()%>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/GestionEvaluacionesServlet?action=editar&id=<%=e.getIdEvaluaciones()%>">
                        Editar
                    </a>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/GestionEvaluacionesServlet?action=borrar&id=<%=e.getIdEvaluaciones()%>">
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