package servlets;

import clases.Imagen;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

            try{
            // Recoger parámetros de texto
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String palabrasClave = request.getParameter("palabrasClave");
            String autor = request.getParameter("autor");
            String fechaCreacion = request.getParameter("fechaCreacion");
            // Obtener el usuario creador desde la sesión (establecida en login)
            String creador = null;
            //Obtenemos el nombre de usuario de quien hace la subida 
            if (request.getSession(false) != null) {
                creador = (String) request.getSession().getAttribute("usuario");
            }

            // Directorio donde se guardan las fotos
            String destino = "/var/webapp/uploads"; //DUDA: hardcodear esto esta bien??

            File directorio = new File(destino);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Procesar archivo subido
            Part filePart = request.getPart("archivoImagen");
            String fileName = getFileName(filePart);
            //En caso de que no haya subido ninguna imagen, lanzamos error
            if (fileName == null || fileName.isEmpty()) {
                response.sendRedirect("error?from=registrarImagen.jsp");
                return;
            }
            //Control de extensión
            String extension = "";
            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                //Extracción de extensión
                extension = fileName.substring(i + 1).toLowerCase();
            }
            List<String> extensionesValidas = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");
            //Si no tiene extension válida, error
            if (!extensionesValidas.contains(extension)) {
                response.sendRedirect("error?from=registrarImagen.jsp");
                return;
            }

            //Creación nombre con timestamp
            long timestamp = System.currentTimeMillis();
            String fileNameUnico = timestamp + "_" + creador + "_" + fileName;

            //Guardamos archivo en el servidor
            File fileGuardar = new File(directorio, fileNameUnico);

            try (InputStream input = filePart.getInputStream(); FileOutputStream output = new FileOutputStream(fileGuardar)) {
                byte[] buffer = new byte[1024];
                int bytesRead = -1;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            // Preparar datos para base de datos
            OperacionSQL op = new OperacionSQL();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaAlta = sdf.format(new Date());

            Imagen imagen = new Imagen(titulo, descripcion, palabrasClave, autor, creador, fechaCreacion, fechaAlta, fileNameUnico);
            boolean registrado = op.insertarImagen(imagen);

            if (registrado) {
                response.sendRedirect("exito.jsp?mensaje=Imagen+registrada+correctamente&from=registrarImagen.jsp");

            } else {
                response.sendRedirect("error?from=registrarImagen.jsp");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            response.sendRedirect("error?from=registrarImagen.jsp");
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
