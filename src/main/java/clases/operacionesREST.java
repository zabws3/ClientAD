/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumne
 */
public class operacionesREST {

    private static final String URL_API = "http://localhost:8080/RestAD/resources/jakartaee9";

    public int peticionPOST(String servicio, String parametros) {
        try {
            URL url = new URL(URL_API + "/" + servicio);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            
        } catch (Exception ex) {
            Logger.getLogger(operacionesREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
