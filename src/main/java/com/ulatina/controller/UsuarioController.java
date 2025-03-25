/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Usuario;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioUsuario;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaime
 */
@Named
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario usuario = new Usuario();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private List<Oportunidades> listaHistorialOportunidades = new ArrayList<>();
    private Servicio servicio = new Servicio() {};
    private String correoUsuario;
    private String nuevaContrasena;
    private String confirmarContrasena;
    
    @Inject
    private EmailController correo;

    public List<Oportunidades> getListaHistorialOportunidades() {
        return listaHistorialOportunidades;
    }

    public void setListaHistorialOportunidades(List<Oportunidades> listaHistorialOportunidades) {
        this.listaHistorialOportunidades = listaHistorialOportunidades;
    }

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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
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
    
    public void cambiarContrasena() {
        
        if (nuevaContrasena != null && nuevaContrasena.equals(confirmarContrasena)) {

            this.servicioUsuario.actualizarClaveUsuario(correo.getCorreo(), nuevaContrasena);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña actualizada correctamente"));
            servicio.redireccionar("/Login.xhtml");
        }

    }
    
    public void actualizarUsuario(Usuario usr, int id) {
        servicioUsuario.actualizarUsuarioEditar(usr , id);
        servicio.redireccionar("/landingPageUsuario.xhtml");
    }
    
    public void cargarHistorialOportunidades(int id) {

        this.listaHistorialOportunidades = servicioUsuario.cargarHistorialOportunidades(id);
        servicio.redireccionar("/verHistorialOportunidades.xhtml");

    }
    
    public String onFlowProcess(org.primefaces.event.FlowEvent event) {
    return event.getNewStep();
}

}
