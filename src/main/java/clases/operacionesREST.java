package clases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * Clase que realiza llamadas REST al servicio de imágenes
 *
 * @author alumne
 */
public class operacionesREST {

    private HttpURLConnection conn;
    private URL url;

    private static final String URL_API = "http://localhost:8080/restAD/resources/jakartaee9";

    // MÉTODOS APERTURA Y CIERRE CONEXIÓN
    private void abrirConexion(String servicio) throws Exception {
        url = new URL(URL_API + "/" + servicio);
        conn = (HttpURLConnection) url.openConnection();
    }

    private void cerrarConexion() {
        try {
            if (conn != null) {
                conn.disconnect();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // OPERACIONES GET Y POST
    private int peticionPOST(String servicio, String parametros) {
        try {
            abrirConexion(servicio);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(parametros.getBytes());
                os.flush();
            }
            return conn.getResponseCode();
        } catch (Exception ex) {
            Logger.getLogger(operacionesREST.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return -1;
    }

    private Imagen peticionGETImagen(String servicio) {
        try {
            abrirConexion(servicio);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuffer response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                // Deserializar el JSON a objeto Imagen
                Gson gson = new Gson();
                Imagen imagen = gson.fromJson(response.toString(), Imagen.class);
                return imagen;
            } else {
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(operacionesREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<Imagen> peticionGETImagenes(String servicio) {
        try {
            abrirConexion(servicio);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuffer response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                // Deserializar el JSON a lista de Imagen
                Gson gson = new Gson();
                Type imagenListaType = new TypeToken<List<Imagen>>() {
                }.getType();
                List<Imagen> lista = gson.fromJson(response.toString(), imagenListaType);
                return lista;
            } else {
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(operacionesREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //LOGIN
    public int validarUsuario(String usuario, String password) {
        try {
            String parametros = "username=" + URLEncoder.encode(usuario, "UTF-8")
                    + "&password=" + URLEncoder.encode(password, "UTF-8");
            return peticionPOST("login", parametros);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    //METODO DE SUBIDA
    public int registrarImagen(Imagen img) {
        try {
            String parametros = "title=" + URLEncoder.encode(img.getTitulo(), "UTF-8")
                    + "&description=" + URLEncoder.encode(img.getDescripcion(), "UTF-8")
                    + "&keywords=" + URLEncoder.encode(img.getKeywords(), "UTF-8")
                    + "&author=" + URLEncoder.encode(img.getAutor(), "UTF-8")
                    + "&creator=" + URLEncoder.encode(img.getCreador(), "UTF-8")
                    + "&capture=" + URLEncoder.encode(img.getFechaCreacion(), "UTF-8");

            return peticionPOST("register", parametros);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //METODO DE MODIFICACIÓN
    public int modificarImagen(Imagen img) {
        try {
            String parametros = "id=" + img.getId()
                    + "&title=" + URLEncoder.encode(img.getTitulo(), "UTF-8")
                    + "&description=" + URLEncoder.encode(img.getDescripcion(), "UTF-8")
                    + "&keywords=" + URLEncoder.encode(img.getKeywords(), "UTF-8")
                    + "&author=" + URLEncoder.encode(img.getAutor(), "UTF-8")
                    + "&creator=" + URLEncoder.encode(img.getCreador(), "UTF-8")
                    + "&capture=" + URLEncoder.encode(img.getFechaCreacion(), "UTF-8");

            return peticionPOST("modify", parametros);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int eliminarImagen(int id, String creador) {
        try {
            String parametros = "id=" + id
                    + "&creator=" + URLEncoder.encode(creador, "UTF-8");
            return peticionPOST("delete", parametros);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

//OPERACIONES BÚSQUEDA
    public Imagen buscarPorId(int id) {
        return peticionGETImagen("searchID/" + id);
    }

    public List<Imagen> buscarPorTitulo(String titulo, String creador) {
        try {
            String tituloEncodificado = URLEncoder.encode(titulo, "UTF-8");
            List<Imagen> imagenes = peticionGETImagenes("searchTitle/" + tituloEncodificado);
            return filtrarPorCreador(imagenes, creador);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(operacionesREST.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    public List<Imagen> buscarPorFecha(String fecha, String creador) {
        try {
            String fechaEncodificada = URLEncoder.encode(fecha, "UTF-8");
            List<Imagen> imagenes = peticionGETImagenes("searchCreationDate/" + fechaEncodificada);
            return filtrarPorCreador(imagenes, creador);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(operacionesREST.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    public List<Imagen> buscarPorCreador(String creador) {
        try {
            String creadorEncodificado = URLEncoder.encode(creador, "UTF-8");
            List<Imagen> imagenes = peticionGETImagenes("searchCreator/" + creadorEncodificado);
            return imagenes;

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(operacionesREST.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    public List<Imagen> buscarPorAutor(String autor, String creador) {
        try {
            String autorEncodificado = URLEncoder.encode(autor, "UTF-8");
            List<Imagen> imagenes = peticionGETImagenes("searchAuthor/" + autorEncodificado);
            return filtrarPorCreador(imagenes, creador);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(operacionesREST.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    public List<Imagen> buscarPorKeywords(String keywords, String creador) {
        try {
            String keywordsEncodificados = URLEncoder.encode(keywords, "UTF-8");
            List<Imagen> imagenes = peticionGETImagenes("searchKeywords/" + keywordsEncodificados);
            return filtrarPorCreador(imagenes, creador);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(operacionesREST.class
                    .getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    //Metodo auxiliar para filtrar por creador
    private List<Imagen> filtrarPorCreador(List<Imagen> imagenes, String creador) {
        List<Imagen> listaFiltrada = new ArrayList<>();

        if (imagenes == null || imagenes.isEmpty()) {
            return listaFiltrada;
        }

        // Solo añadir imágenes cuyo creator sea igual al usuario logueado
        for (Imagen img : imagenes) {
            if (img.getCreador() != null && img.getCreador().equals(creador)) {
                listaFiltrada.add(img);
            }
        }

        return listaFiltrada;
    }

}
