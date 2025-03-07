/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.controller;
import com.ulatina.data.Usuario;
import com.ulatina.service.ServicioUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.PrimeFaces;

@Named
@SessionScoped
public class CRUDController implements Serializable {

    private String user;
    private String pass;
    private Usuario usuario = new Usuario();
    private Usuario selectedUsuario;
    private boolean esInsertar;
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private List<Usuario> usuariosSeleccionados = new ArrayList<>();

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public boolean isEsInsertar() {
        return esInsertar;
    }

    public void setEsInsertar(boolean esInsertar) {
        this.esInsertar = esInsertar;
    }

    public List<Usuario> getListaUsuarios() {
        if (listaUsuarios == null) {
            try {
                listaUsuarios = servicioUsuario.demeUsuarios();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getUsuariosSeleccionados() {
        return usuariosSeleccionados;
    }

    public void setUsuariosSeleccionados(List<Usuario> usuariosSeleccionados) {
        this.usuariosSeleccionados = usuariosSeleccionados;
    }

    public ServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void openNew() {
        this.selectedUsuario = new Usuario();
        this.esInsertar = true;
    }

    public void limpiar() {
        this.esInsertar = false;
    }

  

   

    public void saveUser() {
        try {
            if (selectedUsuario != null && servicioUsuario.validarUsuario(selectedUsuario.getCorreo()) == null) {
                servicioUsuario.insertar(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Usuario agregado correctamente"));
            } else {
                servicioUsuario.actualizar(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Usuario actualizado correctamente"));
            }
            listaUsuarios = servicioUsuario.demeUsuarios();
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no agregado. Intente de nuevo: " + e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }

    public void eliminarUsuario() throws ClassNotFoundException {
        if (selectedUsuario != null && selectedUsuario.getCorreo() != null) {
            servicioUsuario.eliminarUsuario(selectedUsuario.getCorreo());
            listaUsuarios = servicioUsuario.demeUsuarios();
            FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", null));
        } else {
            FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se seleccionó ningún usuario"));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }
    public boolean hasSelectedUsers() {
        return this.usuariosSeleccionados != null && !this.usuariosSeleccionados.isEmpty();
    }
    public String getDeleteButtonMessage() {
        if (hasSelectedUsers()) {
            int size = this.usuariosSeleccionados.size();
            return size > 1 ? size + " usuarios seleccionados" : "1 usuario seleccionado";
        }

        return "Borrar";}
    
 public void borrarUsuariosSeleccionados() {
    if (usuariosSeleccionados != null && !usuariosSeleccionados.isEmpty()) {
        try {
            // Crear una copia de la lista para evitar ConcurrentModificationException
            List<Usuario> usuariosAEliminar = new ArrayList<>(usuariosSeleccionados);

            for (Usuario usuario : usuariosAEliminar) {
                servicioUsuario.eliminarUsuario(usuario.getCorreo());
            }

            // Recargar la lista de usuarios desde la base de datos
            listaUsuarios = servicioUsuario.demeUsuarios();

            // Limpiar la selección
            usuariosSeleccionados.clear();

            // Mostrar mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuarios eliminados correctamente.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar los usuarios seleccionados: " + e.getMessage()));
        }
    } else {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No hay usuarios seleccionados."));
    }

    // Actualizar la tabla en la interfaz
    PrimeFaces.current().ajax().update("form:dt-users", "form:messages");
     System.out.println("estoy borrando");
}


}