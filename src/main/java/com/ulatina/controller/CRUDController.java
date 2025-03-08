package com.ulatina.controller;

import com.ulatina.data.Organizaciones;
import com.ulatina.data.Usuario;
import com.ulatina.service.ServicioOrganizacion;
import com.ulatina.service.ServicioUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.PrimeFaces;

@Named("crudController")
@SessionScoped
public class CRUDController implements Serializable {

    public boolean isEsInsertarUsuario() {
        return esInsertarUsuario;
    }

    public void setEsInsertarUsuario(boolean esInsertarUsuario) {
        this.esInsertarUsuario = esInsertarUsuario;
    }

    public boolean isEsInsertarOrganizacion() {
        return esInsertarOrganizacion;
    }

    public void setEsInsertarOrganizacion(boolean esInsertarOrganizacion) {
        this.esInsertarOrganizacion = esInsertarOrganizacion;
    }

    private boolean esInsertarUsuario;
    private boolean esInsertarOrganizacion;

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getListaUsuarios() {
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

    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public Organizaciones getSelectedOrganizacion() {
        return selectedOrganizacion;
    }

    public void setSelectedOrganizacion(Organizaciones selectedOrganizacion) {
        this.selectedOrganizacion = selectedOrganizacion;
    }

    public List<Organizaciones> getListaOrganizaciones() {
        return listaOrganizaciones;
    }

    public void setListaOrganizaciones(List<Organizaciones> listaOrganizaciones) {
        this.listaOrganizaciones = listaOrganizaciones;
    }

    public List<Organizaciones> getOrganizacionesSeleccionadas() {
        return organizacionesSeleccionadas;
    }

    public void setOrganizacionesSeleccionadas(List<Organizaciones> organizacionesSeleccionadas) {
        this.organizacionesSeleccionadas = organizacionesSeleccionadas;
    }

    public ServicioOrganizacion getServicioOrganizacion() {
        return servicioOrganizacion;
    }

    public void setServicioOrganizacion(ServicioOrganizacion servicioOrganizacion) {
        this.servicioOrganizacion = servicioOrganizacion;
    }

    // Atributos para Usuario
    private Usuario selectedUsuario;
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<Usuario> usuariosSeleccionados = new ArrayList<>();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();

    // Atributos para Organizacion
    private Organizaciones selectedOrganizacion;
    private List<Organizaciones> listaOrganizaciones = new ArrayList<>();
    private List<Organizaciones> organizacionesSeleccionadas = new ArrayList<>();
    private ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();

    // Métodos para Usuario
    public void openNewUsuario() {
        this.selectedUsuario = new Usuario();
        this.esInsertarUsuario = true;

    }

    public void saveUsuario() {
        try {
            if (servicioUsuario.validarUsuario(selectedUsuario.getCorreo()) == null) {
                servicioUsuario.insertar(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Usuario agregado correctamente"));
            } else {
                servicioUsuario.actualizar(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Usuario actualizado correctamente"));
            }
            listaUsuarios = servicioUsuario.demeUsuarios();
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("form:messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no agregado. Intente de nuevo: " + e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }

    public void eliminarUsuario() throws ClassNotFoundException {
        if (selectedUsuario != null && selectedUsuario.getCorreo() != null) {
            servicioUsuario.eliminarUsuario(selectedUsuario.getCorreo());
            listaUsuarios = servicioUsuario.demeUsuarios();
            FacesContext.getCurrentInstance().addMessage("form:messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", null));
        } else {
            FacesContext.getCurrentInstance().addMessage("form:messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se seleccionó ningún usuario"));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }

    public String getDeleteButtonMessageUsuarios() {
        if (usuariosSeleccionados != null && !usuariosSeleccionados.isEmpty()) {
            int size = usuariosSeleccionados.size();
            return size > 1 ? size + " usuarios seleccionados" : "1 usuario seleccionado";
        }
        return "Borrar";
    }

    public void borrarUsuariosSeleccionados() {
        if (usuariosSeleccionados != null && !usuariosSeleccionados.isEmpty()) {
            try {
                List<Usuario> usuariosAEliminar = new ArrayList<>(usuariosSeleccionados);
                for (Usuario usuario : usuariosAEliminar) {
                    servicioUsuario.eliminarUsuario(usuario.getCorreo());
                }
                listaUsuarios = servicioUsuario.demeUsuarios();
                usuariosSeleccionados.clear();
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
        PrimeFaces.current().ajax().update("form:dt-users", "form:messages");
    }

    // Métodos para Organizacion
    public void openNewOrganizacion() {
        this.selectedOrganizacion = new Organizaciones();
        this.esInsertarOrganizacion = true;

    }

    public void saveOrganizacion() {
        try {
            if (servicioOrganizacion.validarOrganizacion(selectedOrganizacion.getCorreo()) == null) {
                servicioOrganizacion.insertarOrganizacion(selectedOrganizacion);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Organización agregada correctamente"));
            } else {
                servicioOrganizacion.actualizarOrganizacion(selectedOrganizacion);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Organización actualizada correctamente"));
            }
            listaOrganizaciones = servicioOrganizacion.demeOrganizaciones();
            PrimeFaces.current().executeScript("PF('manageOrganizacionDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("form:messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Organización no agregada. Intente de nuevo: " + e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organizaciones");
    }

    public void eliminarOrganizacion() throws ClassNotFoundException {
        if (selectedOrganizacion != null && selectedOrganizacion.getCorreo() != null) {
            servicioOrganizacion.eliminarOrganizacion(selectedOrganizacion.getCorreo());
            listaOrganizaciones = servicioOrganizacion.demeOrganizaciones();
            FacesContext.getCurrentInstance().addMessage("form:messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Organización eliminada", null));
        } else {
            FacesContext.getCurrentInstance().addMessage("form:messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se seleccionó ninguna organización"));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-organizaciones");
    }

    public String getDeleteButtonMessageOrganizaciones() {
        if (organizacionesSeleccionadas != null && !organizacionesSeleccionadas.isEmpty()) {
            int size = organizacionesSeleccionadas.size();
            return size > 1 ? size + " organizaciones seleccionadas" : "1 organización seleccionada";
        }
        return "Borrar";
    }

    public void borrarOrganizacionesSeleccionadas() {
        if (organizacionesSeleccionadas != null && !organizacionesSeleccionadas.isEmpty()) {
            try {
                List<Organizaciones> orgsAEliminar = new ArrayList<>(organizacionesSeleccionadas);
                for (Organizaciones org : orgsAEliminar) {
                    servicioOrganizacion.eliminarOrganizacion(org.getCorreo());
                }
                listaOrganizaciones = servicioOrganizacion.demeOrganizaciones();
                organizacionesSeleccionadas.clear();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Organizaciones eliminadas correctamente.", null));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar las organizaciones seleccionadas: " + e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No hay organizaciones seleccionadas."));
        }
        PrimeFaces.current().ajax().update("form:dt-organizaciones", "form:messages");
    }

    public void limpiarUsuario() {
        // Resetea el objeto seleccionado y la bandera de inserción para usuarios
        this.selectedUsuario = new Usuario();
        this.esInsertarUsuario = false;
    }

    public void limpiarOrganizacion() {
        // Resetea el objeto seleccionado y la bandera de inserción para organizaciones
        this.selectedOrganizacion = new Organizaciones();
        this.esInsertarOrganizacion = false;
    }

}
