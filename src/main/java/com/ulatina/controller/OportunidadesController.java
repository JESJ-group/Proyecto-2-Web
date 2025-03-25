/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Organizacion;
import com.ulatina.data.Postulaciones;
import com.ulatina.data.Usuario;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioOportunidad;
import com.ulatina.service.ServicioOrganizacion;
import com.ulatina.service.ServicioPostulaciones;
import com.ulatina.service.ServicioUsuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped


public class OportunidadesController implements Serializable {

    private Oportunidades oportunidades = new Oportunidades();
    private Organizacion organizacion = new Organizacion();
    private Usuario usuario = new Usuario();
    private ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private ServicioOportunidad servicioOportunidad = new ServicioOportunidad();
    private List<Oportunidades> listaOportunidades = new ArrayList<>();
    private ServicioPostulaciones servicioPostulaciones = new ServicioPostulaciones();
    private Postulaciones postulaciones = new Postulaciones();
    private String filtroBusqueda;

    @PostConstruct
    public void init() {

        cargarOportunidades();

    }

    Servicio servicio = new Servicio() {

    };

    public String getFiltroBusqueda() {
        return filtroBusqueda;
    }

    public void setFiltroBusqueda(String filtroBusqueda) {
        this.filtroBusqueda = filtroBusqueda;
    }

    public Postulaciones getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Postulaciones postulaciones) {
        this.postulaciones = postulaciones;
    }

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

    public String irADetalles(Oportunidades op) {
        this.oportunidades = op;

        return "verDetallesOportunidad?faces-redirect=true";
    }
    public String verAspirantes(Oportunidades op) {
        this.oportunidades = op;

        return "verAspirantes?faces-redirect=true";
    }

    EmailController eC = new EmailController();
    
    
    public void aplicar(int idOportunidad, int idUsuario, int idOrganizacion, String correoElectronico) throws SQLException {
        try {
            
            organizacion = servicioOrganizacion.validarOrganizacion(idOrganizacion);
            
            
            if(organizacion != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Ocurrio un error","Las organizaciones no pueden aplicar"));
                return;
            }
            
            usuario = servicioUsuario.validarUsuario(idUsuario);
            if(usuario == null){
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Ocurrio un error","Para poder aplicar debes iniciar sesion primero"));
                return;
                
            }
            
            servicioPostulaciones.insertarPostulacion(postulaciones, idOportunidad, idUsuario);

            
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aplicación satisfactoria", "Se le ha enviado un correo electronico con los detalles"));

            
            eC.enviarCorreoConfirmacionOportunidad(correoElectronico);

        } catch (ClassNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar la oportunidad:  " + e.getMessage()));
        }
    }

    public void registrarOportunidad(String correo) {
        try {
            servicioOportunidad.insertarOportunidad(oportunidades, correo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Oportunidad creada", "Oportuinidad creada con exito"));

            cargarOportunidades();
            servicio.redireccionar("/landingPageOrganizacion.xhtml");

        } catch (ClassNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar la oportunidad:  " + e.getMessage()));
        }
    }

    public void cargarOportunidades() {

        this.listaOportunidades = servicioOportunidad.cargarOportunidades();

    }

    public void filtrarOportunidades() {
        servicioOportunidad.setFiltro(filtroBusqueda);
        this.listaOportunidades = servicioOportunidad.cargarOportunidades();
    }

    public String onFlowProcess(org.primefaces.event.FlowEvent event) {
        return event.getNewStep();
    }
    
    public void nuevaOportunidad() {
         this.oportunidades = new Oportunidades();
     }
 
     public void irAPublicar() { 
         nuevaOportunidad();
         servicio.redireccionar("/publicarOportunidades.xhtml");
     }
    
}


