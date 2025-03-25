/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;

import com.ulatina.data.Organizacion;
import com.ulatina.data.Usuario;
import com.ulatina.service.ServicioOrganizacion;
import com.ulatina.service.ServicioUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import com.ulatina.service.Servicio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;
import org.primefaces.model.file.UploadedFile;

@Named
@SessionScoped

public class RegistroController implements Serializable {

    private Usuario usuario = new Usuario();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private Organizacion organizacion = new Organizacion();
    private ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    Servicio servicio = new Servicio() {

    };

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

    public void registrarUsuario() {
        try {
            servicioUsuario.insertarUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El usuario se registró correctamente"));

            servicio.redireccionar("/Login.xhtml");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar el registro: " + e.getMessage()));
        }
    }

    public void registrarOrganizacion() {
        try {
            servicioOrganizacion.insertarOrganizacion(organizacion);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "La organizacion se registró correctamente"));

            servicio.redireccionar("/Login.xhtml");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado", "No se pudo completar el registro: " + e.getMessage()));
        }
    }

    public String onFlowProcess(org.primefaces.event.FlowEvent event) {
        return event.getNewStep();
    }

    public void handleFileUpload() {
        try {
            String fileNameOriginal = this.file.getFileName();
            InputStream input = this.file.getInputStream();

            // Obtener extensión del archivo original
            String extension = fileNameOriginal.substring(fileNameOriginal.lastIndexOf("."));

            // Usar el correo electrónico como nombre de archivo (eliminando caracteres inválidos)
            String correo = this.organizacion.getNombre().replaceAll("[^a-zA-Z0-9@.]", "_");
            String nombreArchivoFinal = correo + extension;

            String rutaGuardada = this.copyFile(nombreArchivoFinal, input, false);
            this.organizacion.setRutaImagen(rutaGuardada);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Imagen cargada con éxito"));

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir la imagen", e.getMessage()));
        }
    }

    protected String copyFile(String fileName, InputStream in, boolean esTemporal) {
        String rutaCompleta = null;
        try {
            if (fileName != null) {
                // Ruta absoluta de destino en tu PC
                String destinoBase = "C:/Users/Ryzon/iCloudDrive/Desktop/Universidad_/lV Cuatrimestre/Proyecto 2/Proyecto/ProyectoPasantiaJESJ/Proyecto-2-Web/src/main/webapp/resources/images/Organizaciones/";

                // Separar nombre y extensión
                String[] partesArchivo = fileName.split(Pattern.quote("."));
                String nombreArchivo = partesArchivo[0];
                String extensionArchivo = partesArchivo[1];

                if (esTemporal) {
                    nombreArchivo += "_TMP";
                }

                File archivoDestino = new File(destinoBase + nombreArchivo + "." + extensionArchivo);
                archivoDestino.getParentFile().mkdirs();

                OutputStream out = new FileOutputStream(archivoDestino);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

                in.close();
                out.flush();
                out.close();

                // Esta ruta es la que se guarda en la base de datos
                rutaCompleta = "resources/images/Organizaciones/" + nombreArchivo + "." + extensionArchivo;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return rutaCompleta;
    }

}
