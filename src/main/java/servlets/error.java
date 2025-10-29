package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "error", urlPatterns = {"/error"})
public class error extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaOrigen = request.getParameter("from");
        String mensajeError;
        String urlVolver;

        if ("login.jsp".equals(paginaOrigen)) {
            mensajeError = "Usuario o contraseña incorrectos. Por favor, inténtalo de nuevo.";
            urlVolver = "login.jsp";
        } else {
            mensajeError = "Se ha producido un error. Por favor, inténtalo de nuevo.";
            urlVolver = "menu.jsp";
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Error</title><style>");
            out.println("body {font-family:Arial, sans-serif; background:#f7dddd; margin:60px;}");
            out.println(".error-box {background:#fff5f5; border:1px solid #e89292; max-width:400px; margin:0 auto; padding:30px; border-radius:8px;}");
            out.println("h2 {color:#900; margin-bottom:12px;}");
            out.println("a {display:inline-block; margin-top:15px; color:#357ab7; font-size:17px; text-decoration:none;}");
            out.println("</style></head><body>");
            out.println("<div class='error-box'>");
            out.println("<h2>Error</h2>");
            out.println("<p>" + mensajeError + "</p>");
            out.println("<a href='" + urlVolver + "'>Volver</a>");
            out.println("</div></body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Delegar a doGet para procesar también POST igual
        doGet(request, response);
    }

}
