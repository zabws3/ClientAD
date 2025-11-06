package servlets;

import clases.Imagen;
import clases.operacionesREST;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author alumne
 */
@WebServlet(name = "eliminarImagen", urlPatterns = {"/eliminarImagen"})
public class eliminarImagen extends HttpServlet {

    //En el get, recibimos el ID de la imagen a modificar, donde recuperaremos la imagen con una consulta SQL
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return; //Sino error posible nullPointer en session
        }

        String usuario = (String) session.getAttribute("usuario");
        int id = Integer.parseInt(request.getParameter("id"));

        operacionesREST op = new operacionesREST();
        Imagen img = op.buscarPorId(id);
        //Comprobación de que existe la imagen y que corresponde al usuario logeado
        if (img == null || !usuario.equals(img.getCreador())) {
            response.sendRedirect("error?from=eliminarImagen.jsp");
            return;
        }
        request.setAttribute("imagen", img);
        request.getRequestDispatcher("eliminarImagen.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuario") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String usuario = (String) session.getAttribute("usuario");
            int id = Integer.parseInt(request.getParameter("id"));

            operacionesREST op = new operacionesREST();
            int resultado = op.eliminarImagen(id, usuario); //En la operacion de eliminación de REST 
            //se comprueba que sea el usuario el propietario de la foto

            //Validación de imagen
            if (resultado == 200) {
                response.sendRedirect("exito.jsp?mensaje=Imagen+eliminada+correctamente&from=eliminarImagen.jsp");
            } else {
                response.sendRedirect("error?from=eliminarImagen.jsp");
            }

            //SALTAMOS PROCESO ELIMINACIÓN FICHERO
/*
            //Ruta de destino
            String destinoBase = "/var/webapp/uploads";
            String rutaCompleta = destinoBase + "/" + img.getNombreFichero();

            boolean eliminadoImagen = true; //Para que se elimine de la BD aun si no existe
            boolean eliminadoBD = false;
            File fichero = new File(rutaCompleta);
            
            if (fichero.exists()) eliminadoImagen = fichero.delete();

            if (eliminadoImagen) eliminadoBD = op.eliminarImagen(img.getId());

            if (eliminadoImagen && eliminadoBD) {
                response.sendRedirect("exito.jsp?mensaje=Imagen+eliminada+correctamente&from=eliminarImagen.jsp");

            } else {
                response.sendRedirect("error?from=eliminarImagen.jsp");
}
             */
        } catch (Exception e) {
            // Capturamos cualquier excepción inesperada
            System.err.println("Error en eliminarImagen: " + e.getMessage());
            response.sendRedirect("error.jsp?from=eliminarImagen.jsp");
        }
    }
}
