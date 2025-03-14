/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Oportunidades;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioOportunidad;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryzon
 */


@Named
@SessionScoped

public class OportunidadesController implements Serializable{
    
    private Oportunidades oportunidades = new Oportunidades();
    private ServicioOportunidad servicioOportunidad = new ServicioOportunidad();
    private List<Oportunidades> listaOportunidades = new ArrayList<>();
    

    @PostConstruct
    public void init() throws ClassNotFoundException {
        
        cargarOportunidades();
        
    }
    
    Servicio servicio = new Servicio(){
        
    };

    public ServicioOportunidad getServicioOportunidad() {
        return servicioOportunidad;
    }

    public void setServicioOportunidad(ServicioOportunidad servicioOportunidad) {
        this.servicioOportunidad = servicioOportunidad;
    }

   

    public Oportunidades getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(Oportunidades oportunidades) {
        this.oportunidades = oportunidades;
    }

    public List<Oportunidades> getListaOportunidades() {
        return listaOportunidades;
    }

    public void setListaOportunidades(List<Oportunidades> listaOportunidades) {
        this.listaOportunidades = listaOportunidades;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    

    

   
    
    
    public void registrarOportunidad() {
        try {
            servicioOportunidad.insertarOportunidad(oportunidades);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Oportunidad creada", "Oportuinidad creada con exito"));
            
            servicio.redireccionar("/landingPageOrganizacion.xhtml");
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar la oportunidad:  " + e.getMessage()));
        }
    }

    public void cargarOportunidades() throws ClassNotFoundException {

        this.listaOportunidades = servicioOportunidad.cargarOportunidades();

    }
    
    
    public String onFlowProcess(org.primefaces.event.FlowEvent event) {
        return event.getNewStep();
    }
}
                                                                                                                                                                                                
                                                                                                                                                                                               
