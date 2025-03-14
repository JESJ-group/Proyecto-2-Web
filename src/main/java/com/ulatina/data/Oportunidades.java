package com.ulatina.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "oportunidades")
public class Oportunidades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idOrganizacion", referencedColumnName = "id")
    private Organizacion idOrganizacion;

    private String tipo;
    private String titulo;
    private String descripcion;
    private String jornada;
    private String modalidad;
    private String pago;
    private String duracion;
    private String ubicacion;
    private String provincia;

    public Oportunidades() {
    }

    public Oportunidades(int id, Organizacion idOrganizacion, String tipo, String titulo, String descripcion, String jornada, String modalidad, String pago, String duracion, String ubicacion, String provincia) {
        this.id = id;
        this.idOrganizacion = idOrganizacion;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.jornada = jornada;
        this.modalidad = modalidad;
        this.pago = pago;
        this.duracion = duracion;
        this.ubicacion = ubicacion;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Organizacion getIdOrganizacion() {
        return idOrganizacion;
    }

    public void setIdOrganizacion(Organizacion idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }}