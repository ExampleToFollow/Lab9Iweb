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

    <title>Nuevo empleado</title>
</head>
<body>
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
                <label for="Docentes">Docentes disponibles</label>
                <select name="Docentes" class="form-select" id="Docentes">
                    <option value="id"> ola </option>
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
