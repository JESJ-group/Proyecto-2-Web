package com.ulatina.data;

import java.time.LocalDate;
import java.util.Date;

/**
 * Clase que representa una Postulación a una Oportunidad.
 */
public class Postulaciones {

    private int id;
    private int idOportunidades;
    private int idUsuario;
    private String estado;
    private LocalDate fechaPostulacion;

    // 🔹 Constructor público para poder instanciar objetos desde otras clases
    public Postulaciones(int id, int idOportunidades, int idUsuario, String estado, LocalDate fechaPostulacion) {
        this.id = id;
        this.idOportunidades = idOportunidades;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.fechaPostulacion = fechaPostulacion;
    }

    // 🔹 Constructor vacío (necesario para algunas implementaciones de frameworks)
    public Postulaciones() {
    }

    // 🔹 Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOportunidades() {
        return idOportunidades;
    }

    public void setIdOportunidades(int idOportunidades) {
        this.idOportunidades = idOportunidades;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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

    @Override
    public String toString() {
        return "Postulaciones{" +
                "id=" + id +
                ", idOportunidades=" + idOportunidades +
                ", idUsuario=" + idUsuario +
                ", estado='" + estado + '\'' +
                ", fechaPostulacion=" + fechaPostulacion +
                '}';
    }
}