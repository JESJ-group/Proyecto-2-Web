/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Postulaciones;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioOportunidad;
import com.ulatina.service.ServicioPostulaciones;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
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
        private ServicioPostulaciones servicioPostulaciones = new ServicioPostulaciones();
        private Postulaciones postulaciones = new Postulaciones();

    public Postulaciones getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Postulaciones postulaciones) {
        this.postulaciones = postulaciones;
    }


    @PostConstruct
    public void init() throws ClassNotFoundException {
        
        cargarOportunidades();
        
    }
       public String irADetalles(Oportunidades op) {
        this.oportunidades = op;
       
        return "verDetallesOportunidad?faces-redirect=true";
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

    

    

 public void aplicar(Postulaciones postulaciones, int idOportunidad, int idUsuario) throws SQLException {
        try {
            servicioPostulaciones.insertarPostulacion(postulaciones, idOportunidad, idUsuario);
            
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Oportunidad creada", "Oportuinidad creada con exito"));
            
            servicio.redireccionar("/pasantias.xhtml");
            
        } catch (ClassNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar la oportunidad:  " + e.getMessage()));
        }
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
                                                                                                                                                                                                
                                                                                                                                                                                               
