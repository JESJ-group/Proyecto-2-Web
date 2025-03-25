/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.data;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ryzon
 */
public class Postulaciones {

    private int id;
    private Oportunidades idOportunidades;
    private Usuario idUsuario;
    private String estado;
    private LocalDate fechaPostulacion;

    public Postulaciones() {
    }

    public Postulaciones(int id, Oportunidades idOportunidades, Usuario idUsuario, String estado, LocalDate fechaPostulacion) {
        this.id = id;
        this.idOportunidades = idOportunidades;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.fechaPostulacion = fechaPostulacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Oportunidades getIdOportunidades() {
        return idOportunidades;
    }

    public void setIdOportunidades(Oportunidades idOportunidades) {
        this.idOportunidades = idOportunidades;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDate fechaPostulacion) {
        
        this.fechaPostulacion = fechaPostulacion;
    }

}
