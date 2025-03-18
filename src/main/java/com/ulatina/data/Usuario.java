package com.ulatina.data;


import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



/**
 *
 * @author SC
 */
public class Usuario implements Serializable {

    private int id;
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String clave;
    private String genero;
    private String estatus; 
    private String provincia;
    private String canton;
    private String distrito;
    private String numeroContacto;
    
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellidos, String correoElectronico, String clave, String genero, String estatus, String provincia, String canton, String distrito, String numeroContacto, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.clave = clave;
        this.genero = genero;
        this.estatus = estatus;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.numeroContacto = numeroContacto;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        if (fechaNacimiento!= null) {
        this.fechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
    } else {
        this.fechaNacimiento = null;
    }
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public void setCorreo(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}