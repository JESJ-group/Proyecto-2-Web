/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Usuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import com.ulatina.service.ServicioEmail;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 *
 * @author jaime
 */
@Named
@SessionScoped
public class EmailController implements Serializable {

    private ServicioEmail servicioEmail = new ServicioEmail();
    
    private String correo;
    private ServicioEmail servicioUsuario = new ServicioEmail();
    private Usuario usuario = new Usuario();
    private String codigoGenerado;
    private String codigoIngresado;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCodigoGenerado() {
        return codigoGenerado;
    }

    public void setCodigoGenerado(String codigoGenerado) {
        this.codigoGenerado = codigoGenerado;
    }

    public String getCodigoIngresado() {
        return codigoIngresado;
    }

    public void setCodigoIngresado(String codigoIngresado) {
        this.codigoIngresado = codigoIngresado;
    }

    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ServicioEmail getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(ServicioEmail servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public ServicioEmail getServicioEmail() {
        return servicioEmail;
    }

    public void setServicioEmail(ServicioEmail servicioEmail) {
        this.servicioEmail = servicioEmail;
    }

    

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (IOException e) {
            
        }
    }

    public String generarCodigoVerificacion() {
        int codigo = (int) (Math.random() * 900000) + 100000; 
        return String.valueOf(codigo);
    }

    public void enviarCorreo() {
        String asunto = "Recuperación de contraseña";
        String contenido = "A continuación, le aparecerá un código de recuperación";

        try {
            this.usuario = this.servicioEmail.validarCorreo(correo);
            
            if (usuario != null) {
                
               codigoGenerado = generarCodigoVerificacion();

                
                this.servicioEmail.crearCorreo(correo, asunto, contenido + "\nCódigo: " + codigoGenerado);
                this.servicioEmail.enviarCorreo();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Código enviado correctamente"));
                this.redireccionar("/codigoRecuperacion.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correo inválido"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al enviar el correo, intente nuevamente"));
        }

        
    }
    
    public void validarCodigo() {
        if (codigoIngresado != null && codigoIngresado.equals(codigoGenerado)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Código correcto, puede cambiar su contraseña"));
            this.redireccionar("/crearNuevaContrasena.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Código incorrecto, intente nuevamente"));
        }
    }

}
