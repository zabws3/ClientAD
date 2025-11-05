/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumne
 */
public class operacionesREST {

    private HttpURLConnection conn;
    private URL url;

    private static final String URL_API = "http://localhost:8080/RestAD/resources/jakartaee9";

    //METODOS APERTURA Y CIERRE CONEXIÓN
    private void abrirConexion(String servicio) throws Exception { // Throws porque excepciones siempre serán controladas (método privado)
        url = new URL(URL_API + "/" + servicio);
        conn = (HttpURLConnection) url.openConnection();
    }

    private void cerrarConexion() { //Aquí al ejecutarse en el finally, se controla la excepción aparte
        try {
            if (conn != null) {
                conn.disconnect();
            }
        } catch (Exception e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    //OPERACIONES GET Y POST
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

    private List<Imagen> peticionGETImagenes(String servicio) {
        try {
            abrirConexion(servicio);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {                 StringBuffer response;
                try ( // success
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String inputLine;
                    response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                System.out.println(response); //Mirar libreria GSON , en pom.xml
                return null;
            } else {
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(operacionesREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //OPERACIONES USUARIO

    public int validarUsuario(String usuario, String password) {
        String parametros = "username=" + usuario + "&password=" + password;
        return peticionPOST("login", parametros);
    }

    public List<Imagen> imagenesPorCreador(String creador) {
        return peticionGETImagenes("searchCreator/" + creador);
    }

}
