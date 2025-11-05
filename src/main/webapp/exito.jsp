<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String mensaje = request.getParameter("mensaje");
    String from = request.getParameter("from");

    if (mensaje == null || mensaje.trim().isEmpty()) {
        mensaje = "Operación realizada con éxito.";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operación Exitosa</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            background-color: #fafafa; 
            margin: 40px; 
            text-align: center;
        }
        .contenedor {
            max-width: 600px; 
            margin: 50px auto; 
            background-color: #fff; 
            border: 1px solid #ddd; 
            border-radius: 10px; 
            padding: 40px 30px; 
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        h1 { color: #28a745; margin-bottom: 20px; }
        p { font-size: 18px; color: #333; }
        .botones { margin-top: 30px; }
        .botones a {
            display: inline-block; 
            padding: 10px 20px; 
            background-color: #007bff; 
            color: white; 
            text-decoration: none; 
            border-radius: 5px; 
            margin: 5px;
        }
        .botones a:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <div class="contenedor">
        <h1>Operación Exitosa</h1>
        <p><%= mensaje %></p>

        <div class="botones">
            <a href="menu.jsp">Volver al menú</a>

            <% if ("registrarImagen.jsp".equals(from)) { %>
                <a href="registrarImagen.jsp">Registrar otra imagen</a>
            <% } %>
        </div>
    </div>
</body>
</html>
