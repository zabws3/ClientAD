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
        <title>Registrar Imagen</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                background-color: #fafafa;
            }

            h1 {
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

            input[type="text"], input[type="date"], textarea, input[type="file"] {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            textarea {
                resize: vertical;
                min-height: 80px;
            }

            button {
                margin-top: 20px;
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
            }

            button:hover {
                background-color: #218838;
            }

            .botones {
                text-align: center;
                margin-top: 20px;
            }

            .botones a {
                padding: 10px 20px;
                margin-right: 10px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }

            .botones a:hover {
                background-color: #0056b3;
            }

            .mensaje {
                max-width: 600px;
                margin: 20px auto;
                padding: 15px;
                border-radius: 5px;
            }

            .mensaje.info {
                background-color: #d1ecf1;
                border: 1px solid #bee5eb;
                color: #0c5460;
            }

            .mensaje.warning {
                background-color: #fff3cd;
                border: 1px solid #ffeaa7;
                color: #856404;
            }
        </style>
    </head>
    <body>
        <h1>Registrar Nueva Imagen</h1>

        <form action="registrarImagen" method="post" enctype="multipart/form-data">
            <label for="titulo">Título:</label>
            <input type="text" id="titulo" name="titulo" required>

            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required></textarea>

            <label for="palabrasClave">Palabras Clave:</label>
            <input type="text" id="palabrasClave" name="palabrasClave" required>

            <label for="autor">Autor (usuario que capturó la imagen):</label>
            <input type="text" id="autor" name="autor" required>

            <label for="fechaCreacion">Fecha de Creación:</label>
            <input type="date" id="fechaCreacion" name="fechaCreacion" required>

            <label for="archivoImagen">Archivo de Imagen: (No habilitado)</label>
            <!-- <input type="file" id="archivoImagen" name="archivoImagen" required accept=".jpg,.jpeg,.png,.gif,.jpeg,.webp"> -->

            <div class="botones">
                <button type="submit">Registrar Imagen</button>
                <a href="menu.jsp">Volver al menu</a>
            </div>
        </form>
    </body>
</html>
