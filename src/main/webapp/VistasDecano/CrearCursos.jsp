<%--
  Created by IntelliJ IDEA.
  User: Hineill
  Date: 25/11/2023
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <%@page import="java.util.ArrayList" %>
    <%@ page import="com.example.lab9iweb.Daos.DaoUsuario" %>

    <title>Nuevo empleado</title>
</head>
<body>
<% ArrayList<Integer> lista  = (ArrayList<Integer>) request.getAttribute("listaProfesoresSinCurso"); %>
<div class='container'>
    <div class="row justify-content-center">
        <form method="POST" action="GestionCursosServlet" class="col-md-6 col-lg-6">
            <h1 class='mb-3'>Nuevo curso</h1>
            <hr>
            <div class="mb-3">
                <label for="nombre">nombre del curso</label>
                <input type="text" class="form-control form-control-sm" name="nombre" id="nombre">
            </div>
            <div class="mb-3">
                <label for="codigo">codigo del curso</label>
                <input type="text" class="form-control form-control-sm" name="codigo" id="codigo">
            </div>
            <div class="mb-3">
                <label for="idDocentes">Docentes disponibles</label>
                <select name="idDocentes" class="form-select" id="idDocentes">
                    <%for(int idProfesor : lista){%>
                    <option value="<%= idProfesor%>"> <%= new DaoUsuario().getUsuarioXId(idProfesor).getNombre() %> </option>
                    <%}%>
                </select>
            </div>
            <a href="<%= request.getContextPath()%>/GestionCursosServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Guardar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>

</body>
</html>
