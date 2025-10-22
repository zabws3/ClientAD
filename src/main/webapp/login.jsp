<!-- JSP: login.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login - Gestión de Imágenes</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                margin: 0;
                padding: 0;
            }
            .login-container {
                max-width: 400px;
                margin: 80px auto;
                background: white;
                padding: 30px 40px;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            }
            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-top: 15px;
                font-weight: bold;
                color: #555;
            }
            input[type="text"], input[type="password"] {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .btn {
                margin-top: 20px;
                width: 100%;
                padding: 12px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
            }
            .btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>Iniciar Sesión</h2>
            <form action="login" method="POST">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" required />
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required />
                <button type="submit" class="btn">Entrar</button>
            </form>
        </div>
    </body>
</html>