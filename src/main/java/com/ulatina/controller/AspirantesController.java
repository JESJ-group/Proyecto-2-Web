/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Postulaciones;
import com.ulatina.data.Usuario;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioAspirantes;
import com.ulatina.service.ServicioOportunidad;

import jakarta.enterprise.context.SessionScoped;
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
public class AspirantesController implements Serializable {

    private ServicioAspirantes servicioAspirantes = new ServicioAspirantes();
    private List<Oportunidades> listaOportunidadesOrganizacion = new ArrayList<>();
    private List<Usuario> listaAspirantesUsuarios = new ArrayList<>();
    private Usuario usuario = new Usuario();
    private Oportunidades oportunidad = new Oportunidades();
    private ServicioAspirantes servicioAspirante = new ServicioAspirantes();
    private String estadoSeleccionado;
    private OportunidadesController oportunidades = new OportunidadesController();
    private ServicioOportunidad servicioOportunidad = new ServicioOportunidad();
    Servicio servicio = new Servicio() {

    };

    public OportunidadesController getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(OportunidadesController oportunidades) {
        this.oportunidades = oportunidades;
    }
    

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Oportunidades> getListaOportunidadesOrganizacion() {
        return listaOportunidadesOrganizacion;
    }

    public void setListaOportunidadesOrganizacion(List<Oportunidades> listaOportunidadesOrganizacion) {
        this.listaOportunidadesOrganizacion = listaOportunidadesOrganizacion;
    }

    public List<Usuario> getListaAspirantesUsuarios() {
        return listaAspirantesUsuarios;
    }

    public void setListaAspirantesUsuarios(List<Usuario> listaAspirantesUsuarios) {
        this.listaAspirantesUsuarios = listaAspirantesUsuarios;
    }

    public void verDetallesAspirante(Usuario usuario, int idUsuario, int idOportunidad) throws ClassNotFoundException {
        this.usuario = null;
        this.usuario = usuario;

        Postulaciones post = servicioAspirante.obtenerEstado(idUsuario, idOportunidad);
        oportunidad = servicioOportunidad.obtenerIdOportunidad(idOportunidad);
        this.estadoSeleccionado = post.getEstado();
        
        
    }

    public void actualizarEstado() {
        
            int idUsuario = usuario.getId();
            int idOportunidad = oportunidad.getId();
            servicioAspirante.actualizarEstado(idUsuario, idOportunidad, estadoSeleccionado);
        
        System.out.println(oportunidad.getId());
    }

    public void cargarOportunidadesOrganizacion(int id) {

        this.listaOportunidadesOrganizacion = servicioAspirantes.cargarOportunidadesOrganizacion(id);
        servicio.redireccionar("/misOportunidadesOrganizacion.xhtml");

    }

    public void cargarAspirantes(int idOportunidad) {
        this.usuario = null;
        this.listaAspirantesUsuarios = servicioAspirantes.visualizarAspirantesOportunidad(idOportunidad);
        servicio.redireccionar("/verAspirantes.xhtml");

    }

}
