package servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import clases.Imagen;

@WebServlet(name = "modificarImagen", urlPatterns = {"/modificarImagen"})
public class modificarImagen extends HttpServlet {

    //En el get, recibimos el ID de la imagen a modificar, donde recuperaremos la imagen con una consulta SQL
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuario") == null) {
                response.sendRedirect("login.jsp");
                return; //Sino error posible nullPointer en session
            }
            String usuario = (String) session.getAttribute("usuario");
            int id = Integer.parseInt(request.getParameter("id"));

            OperacionSQL op = new OperacionSQL();
            Imagen img = op.obtenerImagenPorId(id);
            //Comprobación de que existe la imagen y que corresponde al usuario logeado
            if (img == null || !usuario.equals(img.getCreador())) {
                response.sendRedirect("error?from=modificarImagen.jsp");
                return;
            }
            request.setAttribute("imagen", img);
            request.getRequestDispatcher("modificarImagen.jsp").forward(request, response);

        } catch (Exception e) {
            // Capturamos cualquier excepción inesperada
            System.err.println("Error en modificarImagen: " + e.getMessage());
            response.sendRedirect("error.jsp?from=modificarImagen.jsp");
        }
    }

    //En el post recibimos los nuevos parámetros a modificar y realizamos la modificación
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuario") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String keywords = request.getParameter("keywords");
            String autor = request.getParameter("autor");
            String captura = request.getParameter("fechaCreacion");
            String usuario = (String) session.getAttribute("usuario");
            
            Imagen img = new Imagen(id, titulo, descripcion, keywords, autor,
                    usuario, captura,
                    null, null);

            OperacionSQL op = new OperacionSQL();
            
            //Comprobación extra de si la imagen corresponde al creador
            Imagen comprobacionID = op.obtenerImagenPorId(id);
            //Validación de imagen
            if (comprobacionID == null || !usuario.equals(img.getCreador())) {
                response.sendRedirect("error?from=modificarImagen.jsp");
                return;
            }
            
            boolean modificado = op.actualizarImagen(img);

            if (modificado) {
                response.sendRedirect("exito.jsp?mensaje=Imagen+modificada+correctamente&from=modificarImagen.jsp");

            } else {
                response.sendRedirect("error?from=modificarImagen.jsp");
            }
        } catch (Exception e) {
            // Capturamos cualquier excepción inesperada
            System.err.println("Error en modificarImagen: " + e.getMessage());
            response.sendRedirect("error.jsp?from=modificarImagen.jsp");
        }
    }
}
