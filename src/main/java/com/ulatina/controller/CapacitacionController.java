/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Capacitacion;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioCapacitacion;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SC
 */
@Named
@SessionScoped

public class CapacitacionController implements Serializable {

    Servicio servicio = new Servicio() {
    };
    ServicioCapacitacion servicioCapacitacion = new ServicioCapacitacion();
    Capacitacion capacitacion = new Capacitacion();
    List<Capacitacion> listaCapacitaciones = new ArrayList<>();
    List<Capacitacion> listaCompletaCapacitaciones = new ArrayList<>();

    public List<Capacitacion> getListaCompletaCapacitaciones() {
        return listaCompletaCapacitaciones;
    }

    public void setListaCompletaCapacitaciones(List<Capacitacion> listaCompletaCapacitaciones) {
        this.listaCompletaCapacitaciones = listaCompletaCapacitaciones;
    }

    public List<Capacitacion> getListaCapacitaciones() {
        return listaCapacitaciones;
    }

    public void setListaCapacitaciones(List<Capacitacion> listaCapacitaciones) {
        this.listaCapacitaciones = listaCapacitaciones;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public ServicioCapacitacion getServicioCapacitacion() {
        return servicioCapacitacion;
    }

    public void setServicioCapacitacion(ServicioCapacitacion servicioCapacitacion) {
        this.servicioCapacitacion = servicioCapacitacion;
    }

    public Capacitacion getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(Capacitacion capacitacion) {
        this.capacitacion = capacitacion;
    }

    public void registrarCapacitacion(String correo) {
        try {
            servicioCapacitacion.insertarCapaciotacion(capacitacion, correo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Capacitacion creada", "Capacitacion creada con exito"));

            servicio.redireccionar("/landingPageOrganizacion.xhtml");

        } catch (ClassNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar la oportunidad:  " + e.getMessage()));
        }
    }

    public String onFlowProcess(org.primefaces.event.FlowEvent event) {
        return event.getNewStep();
    }

    public void nuevaCapacitacion() {
        this.capacitacion = new Capacitacion();
    }

    public void cargarCapacitaciones(int id) {

        this.listaCapacitaciones = servicioCapacitacion.cargarCapacitaciones(id);
        servicio.redireccionar("/misCapacitacionesOrganizacion.xhtml");

    }
    
    public void muestraCapacitaciones() {

        this.listaCompletaCapacitaciones = servicioCapacitacion.mostrarCapacitaciones();
        servicio.redireccionar("/capacitacionUsuario.xhtml");
    }

    public void irAPublicar() {
        nuevaCapacitacion();
        servicio.redireccionar("/publicarCapacitaciones.xhtml");
    }

}
