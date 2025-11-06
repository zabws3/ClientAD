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
        response.sendRedirect("error?from=modificarImagen.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Imagen</title>
    <style>
        body { 
            font-family: Arial, sans-serif;
            background-color: #fafafa; 
            margin: 40px; 
        }

        h2 { 
            text-align: center; 
            color: #333; 
            margin-bottom: 30px; 
        }

        form { 
            max-width: 600px; 
            margin: 0 auto; 
            background-color: #fff; 
            border: 1px solid #ddd; 
            border-radius: 8px; 
            padding: 30px 25px; 
            box-shadow: 0 2px 6px rgba(0,0,0,0.1); 
        }

        label { 
            display: block; 
            margin-top: 15px; 
            font-weight: bold; 
            color: #333; 
        }

        input[type="text"], input[type="date"], textarea {
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

        .botones {
            text-align: center; 
            margin-top: 30px;
        }

        button {
            padding: 10px 20px; 
            background-color: #28a745; 
            color: white; 
            border: none; 
            border-radius: 5px; 
            font-size: 16px; 
            cursor: pointer;
            margin-right: 10px;
        }

        button:hover {
            background-color: #218838;
        }

        .botones a {
            padding: 10px 20px; 
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
    <h2>Modificar Imagen</h2>

    <form action="modificarImagen" method="post">
        <input type="hidden" name="id" value="<%= img.getId() %>" />

        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" value="<%= img.getTitulo() %>" required />

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required><%= img.getDescripcion() %></textarea>

        <label for="keywords">Palabras Clave:</label>
        <input type="text" id="keywords" name="keywords" value="<%= img.getKeywords() %>" required />

        <label for="autor">Autor:</label>
        <input type="text" id="autor" name="autor" value="<%= img.getAutor() %>" required />

        <label for="fechaCreacion">Fecha Captura:</label>
        <input type="date" id="fechaCreacion" name="fechaCreacion" value="<%= img.getFechaCreacion() %>" required />

        <div class="botones">
            <button type="submit">Guardar Cambios</button>
            <a href="javascript:window.history.go(-1)">Volver atrás</a>
        </div>
    </form>
</body>
</html>
