<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="clases.Imagen" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Resultados de Búsqueda</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f5f5f5;
            }
            .imagen-miniatura {
                max-width: 100px;
                max-height: 100px;
            }
            .acciones a {
                margin-right: 10px;
                padding: 5px 10px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 3px;
            }
            .acciones a.eliminar {
                background-color: #dc3545;
            }
            .acciones a.eliminar:hover {
                background-color: #c82333;
            }
            .mensaje {
                padding: 15px;
                margin: 20px 0;
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
            .botones a {
                padding: 10px 20px;
                margin-right: 10px;
                background-color: #28a745;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            .botones a:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <h2>Resultados de Búsqueda de Imágenes</h2>

        <%
            List<Imagen> resultados = (List<Imagen>) request.getAttribute("resultados");
            String titulo = request.getParameter("titulo");
            String palabrasClave = request.getParameter("palabrasClave");
            String autor = request.getParameter("autor");
            String fechaCreacion = request.getParameter("fechaCreacion");
    
            titulo = (titulo != null && titulo.trim().isEmpty()) ? null : titulo;
            palabrasClave = (palabrasClave != null && palabrasClave.trim().isEmpty()) ? null : palabrasClave;
            autor = (autor != null && autor.trim().isEmpty()) ? null : autor;
            fechaCreacion = (fechaCreacion != null && fechaCreacion.trim().isEmpty()) ? null : fechaCreacion;
        %>

        <% if (titulo!=null || palabrasClave!=null || autor!=null || fechaCreacion!=null) { %>
        <div class="mensaje info">
            <strong>Criterios de búsqueda aplicados:</strong><br>
            <% if (titulo!=null) { %>• Título: <%= titulo %><br><% } %>
            <% if (palabrasClave!=null) { %>• Palabras clave: <%= palabrasClave %><br><% } %>
            <% if (autor!=null) { %>• Autor: <%= autor %><br><% } %>
            <% if (fechaCreacion!=null) { %>• Fecha de creación: <%= fechaCreacion %><br><% } %>
        </div>
        <% } else { %>
        <div class="mensaje info">Mostrando todas las imágenes disponibles</div>
        <% } %>

        <% if (resultados != null && !resultados.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>ID</th><th>Imagen</th><th>Título</th><th>Descripción</th><th>Palabras Clave</th>
                    <th>Autor</th><th>Creador</th><th>Fecha Creación</th><th>Fecha Alta</th><th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (Imagen img : resultados) { %>
                <tr>
                    <td><%= img.getId() %></td>
                    <td>
                        <% if (img.getNombreFichero() != null && !img.getNombreFichero().isEmpty()) { %>
                        <a href="#" onclick="window.open('<%= request.getContextPath() %>/uploads/<%= img.getNombreFichero() %>', '_blank', 'width = 600, height = 400'); return false;">Ver foto</a>
                        <% } else { %>
                        Sin imagen
                        <% } %>
                    </td>
                    <td><%= img.getTitulo() %></td>
                    <td><%= img.getDescripcion() %></td>
                    <td><%= img.getKeywords() %></td>
                    <td><%= img.getAutor() %></td>
                    <td><%= img.getCreador() %></td>
                    <td><%= img.getFechaCreacion() %></td>
                    <td><%= img.getFechaAlta() %></td>
                    <td class="acciones">

                        <% if (session.getAttribute("usuario").equals(img.getCreador())) { %>
                        <a href="modificarImagen?id=<%= img.getId() %>">Modificar</a>
                        <a href="eliminarImagen?id=<%= img.getId() %>" class="eliminar">Eliminar</a>
                        <% } else { %>No disponible<% } %>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } else { %>
        <div class="mensaje warning">
            <strong>No se encontraron imágenes</strong> que coincidan con los criterios especificados.
        </div>
        <% } %>
        <br><br>
        <div class="botones">
            <a href="buscarImagen.jsp">Nueva Búsqueda</a>
            <a href="menu.jsp">Volver al Menú</a>
        </div>
    </body>
</html>