<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Buscar Imagen</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                background-color: #fafafa;
            }

            h2 {
                text-align: center;
                color: #333;
            }

            form {
                max-width: 600px;
                margin: 30px auto;
                background-color: #fff;
                border: 1px solid #ddd;
                padding: 25px;
                border-radius: 8px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            }

            label {
                display: block;
                margin-top: 15px;
                font-weight: bold;
                color: #333;
            }

            input[type="text"], input[type="date"] {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #218838;
            }

            .botones {
                text-align: center;
                margin-top: 20px;
            }

            .botones a {
                padding: 10px 20px;
                margin-left: 10px;
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
        <h2>Buscar Imágenes</h2>

        <form action="buscarImagen" method="post">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" />

            <label for="palabrasClave">Palabras Clave:</label>
            <input type="text" id="palabrasClave" name="palabrasClave" />

            <label for="autor">Autor:</label>
            <input type="text" id="autor" name="autor" />

            <label for="fechaCreacion">Fecha de Creación:</label>
            <input type="date" id="fechaCreacion" name="fechaCreacion" />

            <div class="botones">
                <input type="submit" value="Buscar" />
                <a href="menu.jsp">Volver al menú</a>
            </div>
        </form>
    </body>
</html>
