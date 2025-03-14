package com.ulatina.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "organizacion")
public class Organizacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String nombreRepresentante;
    private String correoElectronico;
    private String clave;
    private String numeroTelefonico;
    private String descripcion;
    private String provincia;
    private String canton;
    private String distrito;
    private String cedulaRepresentante;

    public Organizacion() {
    }

    public Organizacion(int id, String cedulaRepresentante, String nombre, String correoElectronico, String clave, String numeroTelefonico, String descripcion, String provincia, String canton, String distrito, String nombreRepresentante) {
        this.id = id;
        this.nombre = nombre;
        this.nombreRepresentante = nombreRepresentante;
        this.correoElectronico = correoElectronico;
        this.clave = clave;
        this.numeroTelefonico = numeroTelefonico;
        this.descripcion = descripcion;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.cedulaRepresentante = cedulaRepresentante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCedulaRepresentante() {
        return cedulaRepresentante;
    }

    public void setCedulaRepresentante(String cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
    }
}