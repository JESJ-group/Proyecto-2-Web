/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Organizacion;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioOrganizacion;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Ryzon
 */

@Named
@SessionScoped
public class OrganizacionController implements Serializable{
    
    private Organizacion organizacion = new Organizacion();
    private ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();
    private Servicio servicio = new Servicio() {};
    private String correoOrganizacion;
    private String nuevaContrasena;
    private String confirmarContrasena;
    
    @Inject
    private EmailController correo;

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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getCorreoOrganizacion() {
        return correoOrganizacion;
    }

    public void setCorreoOrganizacion(String correoOrganizacion) {
        this.correoOrganizacion = correoOrganizacion;
    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    public void setNuevaContrasena(String nuevaContrasena) {
        this.nuevaContrasena = nuevaContrasena;
    }

    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }

    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    public EmailController getCorreo() {
        return correo;
    }

    public void setCorreo(EmailController correo) {
        this.correo = correo;
    }
    
    public void cambiarContrasena() {
        
        if (nuevaContrasena != null && nuevaContrasena.equals(confirmarContrasena)) {

            this.servicioOrganizacion.actualizarClaveOrganizacion(correo.getCorreo(), nuevaContrasena);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña actualizada correctamente"));
            servicio.redireccionar("/Login.xhtml");
        }

    }
    
    public void actualizarOrganizacion(Organizacion org,  int id) {
        servicioOrganizacion.actualizarOrganizacionEditar(org, id);
        servicio.redireccionar("/landingPageOrganizacion.xhtml");
    }
    
}
