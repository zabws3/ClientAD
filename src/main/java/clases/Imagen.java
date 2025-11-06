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
    private String title;
    private String description;
    private String keywords;
    private String author;
    private String creator;
    private String capture_date;
    private String storage_date;
    private String filename;

    public Imagen(int id, String titulo, String descripcion, String keywords,
            String autor, String creador, String fechaCreacion, String fechaAlta, String nombreFichero) {
        this.id = id;
        this.title = titulo;
        this.description = descripcion;
        this.keywords = keywords;
        this.author = autor;
        this.creator = creador;
        this.capture_date = fechaCreacion;
        this.storage_date = fechaAlta;
        this.filename = nombreFichero;
    }

    // Constructor sin id (inserciones)
    public Imagen(String titulo, String descripcion, String keywords,
            String autor, String creador, String fechaCreacion, String fechaAlta, String nombreFichero) {
        this.title = titulo;
        this.description = descripcion;
        this.keywords = keywords;
        this.author = autor;
        this.creator = creador;
        this.capture_date = fechaCreacion;
        this.storage_date = fechaAlta;
        this.filename = nombreFichero;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return title;
    }

    public void setTitulo(String titulo) {
        this.title = titulo;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAutor() {
        return author;
    }

    public void setAutor(String autor) {
        this.author = autor;
    }

    public String getCreador() {
        return creator;
    }

    public void setCreador(String creador) {
        this.creator = creador;
    }

    public String getFechaCreacion() {
        return capture_date;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.capture_date = fechaCreacion;
    }

    public String getFechaAlta() {
        return storage_date;
    }

    public void setFechaAlta(String fechaAlta) {
        this.storage_date = fechaAlta;
    }

    public String getNombreFichero() {
        return filename;
    }

    public void setNombreFichero(String nombreFichero) {
        this.filename = nombreFichero;
    }
}

