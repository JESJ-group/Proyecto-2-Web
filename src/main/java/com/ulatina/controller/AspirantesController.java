/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Usuario;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioAspirantes;

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
    Servicio servicio = new Servicio() {

    };

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

    public void verDetallesAspirante(Usuario usuario) {
        this.usuario = null;
        this.usuario = usuario;

    }
    
    

    public void cargarOportunidadesOrganizacion(int id) {

        this.listaOportunidadesOrganizacion = servicioAspirantes.cargarOportunidadesOrganizacion(id);
        servicio.redireccionar("/misOportunidadesOrganizacion.xhtml");

    }

    public void cargarAspirantes(int idOportunidad) {

        this.listaAspirantesUsuarios = servicioAspirantes.visualizarAspirantesOportunidad(idOportunidad);
        servicio.redireccionar("/verAspirantes.xhtml");

    }

}
