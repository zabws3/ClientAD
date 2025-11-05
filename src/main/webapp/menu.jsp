<!-- JSP: menu.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return; //Para parar la ejecución del jsp (Sino posible error 500)
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Menú Principal</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 800px;
                margin: 50px auto;
                background: white;
                padding: 20px 40px;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            }
            h2 {
                color: #333;
                margin-bottom: 20px;
            }
            .menu-list {
                list-style: none;
                padding: 0;
            }
            .menu-list li {
                margin: 15px 0;
            }
            .menu-list a {
                display: block;
                padding: 12px 20px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 4px;
                font-size: 18px;
            }
            .menu-list a:hover {
                background-color: #0056b3;
            }
            .logout {
                margin-top: 30px;
                text-align: center;
            }
            .logout a {
                color: #dc3545;
                text-decoration: none;
                font-weight: bold;
            }
            .logout a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Bienvenid@, <%= usuario %></h2>
            <ul class="menu-list">
                <li><a href="registrarImagen.jsp">Registrar Imagen</a></li>
                <li><a href="buscarImagen.jsp">Buscar Imagen</a></li>
            </ul>
            <div class="logout">
                <a href="logout">Cerrar sesión</a>
            </div>
        </div>
    </body>
</html>