/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.data;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author SC
 */
public class Capacitacion {
    private int id;
    private String Titulo;

    public Capacitacion() {
    }
    private String descripcion;
    private String urlVideo;
    private String categoria;

    public Capacitacion(int id, String Titulo, String descripcion, String urlVideo, String categoria, LocalDate fechaPublicacion, Organizacion idOrganizacion) {
        this.id = id;
        this.Titulo = Titulo;
        this.descripcion = descripcion;
        this.urlVideo = urlVideo;
        this.categoria = categoria;
        this.fechaPublicacion = fechaPublicacion;
        this.idOrganizacion = idOrganizacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Organizacion getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(Organizacion idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }
    private LocalDate fechaPublicacion;
    private Organizacion idOrganizacion;
    
    public String obtenerIdVideo() {
    if (this.urlVideo == null || this.urlVideo.isEmpty()) {
        return "";
    }

    String videoId = "";
    
    try {
        if (this.urlVideo.contains("youtube.com/watch?v=")) {
            videoId = this.urlVideo.split("v=")[1].split("&")[0]; 
        } else if (this.urlVideo.contains("youtu.be/")) {
            videoId = this.urlVideo.split("youtu.be/")[1].split("\\?")[0];
        } else if (this.urlVideo.contains("youtube.com/embed/")) {
            videoId = this.urlVideo.split("embed/")[1].split("\\?")[0]; 
        }
    } catch (Exception e) {
        videoId = ""; 
    }

    return videoId;
}

}
