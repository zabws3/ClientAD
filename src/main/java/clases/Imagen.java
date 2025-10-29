/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 * Clase imagen que agrupa todos los atributos de una imagen
 * @author alumne
 */
public class Imagen {

    private int id;
    private String titulo;
    private String descripcion;
    private String keywords;
    private String autor;
    private String creador;
    private String fechaCreacion;
    private String fechaAlta;
    private String nombreFichero;

    public Imagen(int id, String titulo, String descripcion, String keywords,
            String autor, String creador, String fechaCreacion, String fechaAlta, String nombreFichero) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.keywords = keywords;
        this.autor = autor;
        this.creador = creador;
        this.fechaCreacion = fechaCreacion;
        this.fechaAlta = fechaAlta;
        this.nombreFichero = nombreFichero;
    }

    // Constructor sin id (inserciones)
    public Imagen(String titulo, String descripcion, String keywords,
            String autor, String creador, String fechaCreacion, String fechaAlta, String nombreFichero) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.keywords = keywords;
        this.autor = autor;
        this.creador = creador;
        this.fechaCreacion = fechaCreacion;
        this.fechaAlta = fechaAlta;
        this.nombreFichero = nombreFichero;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }
}

