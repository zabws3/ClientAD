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
import clases.operacionesREST;
import java.util.ArrayList;

/**
 * Servlet que procesa la búsqueda de imágenes y delega la presentación a un JSP
 */
/**
 * Servlet que procesa la búsqueda de imágenes y delega la presentación a un JSP
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Controlamos si hay sesión
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

            // Inicializar a null si el parámetro está vacío (para mostrar todos los resultados)
            // Trim para evitar espacios entre medio
            titulo = (titulo != null && !titulo.trim().isEmpty()) ? titulo.trim() : null;
            palabrasClave = (palabrasClave != null && !palabrasClave.trim().isEmpty()) ? palabrasClave.trim() : null;
            autor = (autor != null && !autor.trim().isEmpty()) ? autor.trim() : null;
            fechaCreacion = (fechaCreacion != null && !fechaCreacion.trim().isEmpty()) ? fechaCreacion.trim() : null;

            // Realizar consulta REST
            operacionesREST op = new operacionesREST();
            List<Imagen> resultados = new ArrayList<>();

            // Si no hay ningún parámetro de búsqueda, traer todas las imágenes del usuario
            if (titulo == null && palabrasClave == null && autor == null && fechaCreacion == null) {
                resultados = op.buscarPorCreador(usuarioLogueado);
            } else {
                // Si hay parámetros, hacer búsquedas específicas

                // Búsqueda por título
                if (titulo != null) {
                    resultados = op.buscarPorTitulo(titulo, usuarioLogueado);
                } // Búsqueda por palabras clave
                else if (palabrasClave != null) {
                    resultados = op.buscarPorKeywords(palabrasClave, usuarioLogueado);
                } // Búsqueda por autor
                else if (autor != null) {
                    resultados = op.buscarPorAutor(autor, usuarioLogueado);
                } // Búsqueda por fecha de creación
                else if (fechaCreacion != null) {
                    resultados = op.buscarPorFecha(fechaCreacion, usuarioLogueado);
                }
            }

            // Pasar resultados al JSP
            request.setAttribute("resultados", resultados);
            request.getRequestDispatcher("resultadoBusqueda.jsp").forward(request, response);

        } catch (Exception e) {
            // Capturamos cualquier excepción inesperada
            System.err.println("Error en buscarImagen: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("error.jsp?from=buscarImagen");
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
