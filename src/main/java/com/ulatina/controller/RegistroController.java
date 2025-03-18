/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Organizacion;
import com.ulatina.data.Usuario;
import com.ulatina.service.ServicioOrganizacion;
import com.ulatina.service.ServicioUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import com.ulatina.service.Servicio;



@Named
@SessionScoped

public class RegistroController implements Serializable{
    
    private Usuario usuario = new Usuario();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private Organizacion organizacion = new Organizacion();
    private ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();

    Servicio servicio = new Servicio(){
        
    };
    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public ServicioOrganizacion getServicioOrganizacion() {
        return servicioOrganizacion;
    }

    public void setServicioOrganizacion(ServicioOrganizacion servicioOrganizacion) {
        this.servicioOrganizacion = servicioOrganizacion;
    }
    
    
    public void registrarUsuario() {
        try {
            servicioUsuario.insertarUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El usuario se registró correctamente"));
            
            servicio.redireccionar("/Login.xhtml");
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar el registro: " + e.getMessage()));
        }
    }

    
    public void registrarOrganizacion() {
        try {
            servicioOrganizacion.insertarOrganizacion(organizacion);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "La organizacion se registró correctamente"));
            
            servicio.redireccionar("/Login.xhtml");
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar el registro: " + e.getMessage()));
        }
    }
    
    public String onFlowProcess(org.primefaces.event.FlowEvent event) {
        return event.getNewStep();
    }
    
}
