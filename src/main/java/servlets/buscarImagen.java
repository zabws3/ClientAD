// Servlet: buscarImagen.java
package servlets;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import clases.Imagen;
import clases.Imagen;
import clases.operacionesREST;

/**
 * Servlet que procesa la búsqueda de imágenes y delega la presentación a un JSP
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Controlamos si hay sesión
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuario") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            String usuarioLogueado = (String) session.getAttribute("usuario");

            // Obtener parámetros
            String titulo = request.getParameter("titulo");
            String palabrasClave = request.getParameter("palabrasClave");
            String autor = request.getParameter("autor");
            String fechaCreacion = request.getParameter("fechaCreacion");

            //Inicializar a null si el parámetro está vacio (para mostrar todos los resultados)
            //Trim para evitar espacios entre medio
            titulo = (titulo != null && titulo.trim().isEmpty()) ? null : titulo;
            palabrasClave = (palabrasClave != null && palabrasClave.trim().isEmpty()) ? null : palabrasClave;
            autor = (autor != null && autor.trim().isEmpty()) ? null : autor;
            fechaCreacion = (fechaCreacion != null && fechaCreacion.trim().isEmpty()) ? null : fechaCreacion;

            //Realizar consulta SQL
            operacionesREST op = new operacionesREST();
            
            List<Imagen> resultados;
            if (titulo == null && palabrasClave == null && autor == null && fechaCreacion == null) {
                resultados = op.imagenesPorCreador(usuarioLogueado);
            } else {
                resultados = null;
                //resultados = op.buscarImagenes(titulo, palabrasClave, autor, fechaCreacion, usuarioLogueado);
            }

            //Pasar resultados al JSP
            request.setAttribute("resultados", resultados);
            request.getRequestDispatcher("resultadoBusqueda.jsp").forward(request, response);
        } catch (Exception e) {
            // Capturamos cualquier excepción inesperada
            System.err.println("Error en buscarImagen: " + e.getMessage());
            response.sendRedirect("error.jsp?from=buscarImagen.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");

        } else {
            response.sendRedirect("menu.jsp");
        }

    }

}
