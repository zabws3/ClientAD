<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="clases.Imagen" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return; //Para parar la ejecución del jsp (Sino posible error 500)
    }
    
    Imagen img = (Imagen) request.getAttribute("imagen");
    if (img == null) {
        response.sendRedirect("error?from=eliminarImagen.jsp");
        return; 
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Eliminar Imagen</title>
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
                padding: 30px 25px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            }

            h2 {
                color: #dc3545;
                margin-bottom: 20px;
            }

            .titulo {
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 15px;
            }

            img {
                max-width: 100%;
                height: auto;
                margin: 20px 0;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            button {
                margin-top: 20px;
                padding: 10px 20px;
                font-size: 16px;
                background-color: #dc3545;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            button:hover {
                background-color: #c82333;
            }

            .botones {
                margin-top: 20px;
            }

            .botones a {
                display: inline-block;
                padding: 10px 20px;
                margin: 5px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }

            .botones a:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="contenedor">
            <h2>Eliminar Imagen</h2>

            <form action="eliminarImagen" method="post">
                <input type="hidden" name="id" value="<%= img.getId() %>"/>

                <div class="titulo"><%= img.getTitulo() %></div>

                <img src="<%= request.getContextPath() %>/uploads/<%= img.getNombreFichero() %>" alt="Imagen"/>
                <br><br>
                <button type="submit">Eliminar Imagen</button>
            </form>

            <div class="botones">
                <a href="menu.jsp">Volver al menú</a>
                <a href="javascript:window.history.go(-1)">Volver atrás</a>
            </div>
        </div>
    </body>
</html>
