/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.data;


/**
 *
 * @author Ryzon
 */
public class Oportunidades {
    
    private int id;
    private Organizacion idOrganizacion;
    private String tipo,titulo,descripcion,detalles,jornada,modalidad,pago, duracion, ubicacion, provincia;

    public Oportunidades() {
    }

    public Oportunidades(int id, Organizacion idOrganizacion, String tipo, String titulo, String descripcion,String detalles, String jornada, String modalidad, String pago, String duracion, String ubicacion, String provincia) {
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
        this.detalles = detalles;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
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
    }

    
    

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
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

    
    
    
}
