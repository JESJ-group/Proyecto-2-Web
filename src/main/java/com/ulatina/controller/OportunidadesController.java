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
import com.ulatina.service.ServicioAspirantes;
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
    private String estado;  
    private String nombreEmpresa; 
    private Postulaciones postulaciones = new Postulaciones();
    private Organizacion organizaciones = new Organizacion();
    private ServicioAspirantes servicioAspirantes = new ServicioAspirantes();

    @PostConstruct
    public void init() {

        cargarOportunidades();

    }

    Servicio servicio = new Servicio() {

    };
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFiltroBusqueda() {
        return filtroBusqueda;
    }
    
    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public ServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public ServicioPostulaciones getServicioPostulaciones() {
        return servicioPostulaciones;
    }

    public void setServicioPostulaciones(ServicioPostulaciones servicioPostulaciones) {
        this.servicioPostulaciones = servicioPostulaciones;
    }
    

    public ServicioAspirantes getServicioAspirantes() {
        return servicioAspirantes;
    }

    public Organizacion getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(Organizacion organizaciones) {
        this.organizaciones = organizaciones;
    }

    public void setServicioAspirantes(ServicioAspirantes servicioAspirantes) {
        this.servicioAspirantes = servicioAspirantes;
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
        this.usuario = null;
        
        return "verAspirantes?faces-redirect=true";
    }

    EmailController eC = new EmailController();

    public void aplicar(int idOportunidad, int idUsuario, int idOrganizacion, String correoElectronico) throws SQLException {
        try {
            organizacion = servicioOrganizacion.validarOrganizacion(idOrganizacion);
            if (organizacion != null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error", "Las organizaciones no pueden aplicar"));
                return;
            }

            usuario = servicioUsuario.validarUsuario(idUsuario);
            if (usuario == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error", "Para poder aplicar debes iniciar sesión primero"));
                return;
            }
            if (servicioPostulaciones.existePostulacion(postulaciones, idUsuario, idOportunidad)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrió un error", "Ya has aplicado para esta oportunidad"));
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
    public String verEstadoDesdeHistorial(int idOp, int idUsu, Oportunidades op) throws ClassNotFoundException, SQLException {
        this.oportunidades = op;
        Postulaciones postulacioness = servicioPostulaciones.obtenerPostulacion(idUsu, idOp);
        this.estado = postulacioness.getEstado();
        Organizacion organizacionn = servicioOrganizacion.obtenerIdOrganizacion2(idOp);
        this.nombreEmpresa = organizacionn.getNombre();
        System.out.println(idOp);
        System.out.println(idUsu);
        return "estadoSolicitudesVista?faces-redirect=true";
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
