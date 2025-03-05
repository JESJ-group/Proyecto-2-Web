/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;


import java.io.Serializable;


/**
 *
 * @author jaime
 */

public class DatosCorreoElectronico implements Serializable{

    
    private String correoDestinario;
    private String asunto;
    private String contenido;
   

    public DatosCorreoElectronico(String correoDestinario, String asunto, String contenido) {

        this.correoDestinario = correoDestinario;
        this.asunto = asunto;
        this.asunto = contenido;

    }
    

    public DatosCorreoElectronico() {

       
    }


    public String getCorreoDestinario() {
        return correoDestinario;
    }

    public void setCorreoDestinario(String correoDestinario) {
        this.correoDestinario = correoDestinario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    

}
