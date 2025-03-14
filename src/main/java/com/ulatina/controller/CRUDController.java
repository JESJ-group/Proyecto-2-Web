
package com.ulatina.controller;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Organizacion;
import com.ulatina.data.Usuario;
import com.ulatina.service.ServicioOportunidad;
import com.ulatina.service.ServicioOrganizacion;
import com.ulatina.service.ServicioUsuario;
import jakarta.annotation.PostConstruct;
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

    // USUARIOS
    private boolean esInsertarUsuario;
    private Usuario selectedUsuario;
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<Usuario> usuariosSeleccionados = new ArrayList<>();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();

    // ORGANIZACIONES
    private boolean esInsertarOrganizacion;
    private Organizacion selectedOrganizacion;
    private List<Organizacion> listaOrganizaciones = new ArrayList<>();
    private List<Organizacion> organizacionesSeleccionadas = new ArrayList<>();
    private ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();

  

    @PostConstruct
    public void init() {
        try {
            listaUsuarios = servicioUsuario.demeUsuarios();
            listaOrganizaciones = servicioOrganizacion.demeOrganizaciones();
            listaOportunidades = servicioOportunidades.cargarOportunidades();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Métodos para USUARIOS
    public void openNewUsuario() {
        this.selectedUsuario = new Usuario();
        this.esInsertarUsuario = true;
    }
    
    public void prepareEditUsuario(Usuario user) {
        selectedUsuario = user;
        esInsertarUsuario = false;
    }
    
    public void saveUsuario() {
        try {
            if (servicioUsuario.validarUsuario(selectedUsuario.getId()) == null) {
                servicioUsuario.insertarUsuario(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage("form:messages", 
                    new FacesMessage("Usuario agregado correctamente"));
            } else {
                servicioUsuario.actualizar(selectedUsuario);
                FacesContext.getCurrentInstance().addMessage("form:messages", 
                    new FacesMessage("Usuario actualizado correctamente"));
            }
            listaUsuarios = servicioUsuario.demeUsuarios();
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("form:messages", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no procesado: " + e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }
    
    public void eliminarUsuario() {
        try {
            if (selectedUsuario != null && selectedUsuario.getCorreoElectronico() != null) {
                servicioUsuario.eliminarUsuario(selectedUsuario.getCorreoElectronico());
                listaUsuarios = servicioUsuario.demeUsuarios();
                FacesContext.getCurrentInstance().addMessage("form:messages", 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", null));
            } else {
                FacesContext.getCurrentInstance().addMessage("form:messages", 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se seleccionó ningún usuario"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("form:messages", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el usuario: " + e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
    }
    
    public void borrarUsuariosSeleccionados() {
        if (usuariosSeleccionados != null && !usuariosSeleccionados.isEmpty()) {
            try {
                List<Usuario> usuariosAEliminar = new ArrayList<>(usuariosSeleccionados);
                for (Usuario usuario : usuariosAEliminar) {
                    servicioUsuario.eliminarUsuario(usuario.getCorreoElectronico());
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
    
    public void limpiarUsuario() {
        selectedUsuario = new Usuario();
        esInsertarUsuario = false;
    }
    
    // Métodos para ORGANIZACIONES
    public void openNewOrganizacion() {
        selectedOrganizacion = new Organizacion();
        esInsertarOrganizacion = true;
    }
    
    public void prepareEditOrganizacion(Organizacion org) {
        selectedOrganizacion = org;
        esInsertarOrganizacion = false;
    }
    
    public void saveOrganizacion() {
        try {
            if (selectedOrganizacion != null && servicioOrganizacion.validarOrganizacion(selectedOrganizacion.getId()) == null) {
                servicioOrganizacion.insertarOrganizacion(selectedOrganizacion);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Organización agregada correctamente"));
            } else {
                servicioOrganizacion.actualizarOrganizacion(selectedOrganizacion);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Organización actualizada correctamente"));
            }
            listaOrganizaciones = servicioOrganizacion.demeOrganizaciones();
            PrimeFaces.current().executeScript("PF('manageOrgDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("form:messages",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Organización no procesada: " + e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-org");
    }
    
    public void eliminarOrganizacion() {
        try {
            if (selectedOrganizacion != null && selectedOrganizacion.getCorreoElectronico() != null) {
                servicioOrganizacion.eliminarOrganizacion(selectedOrganizacion.getCorreoElectronico());
                listaOrganizaciones = servicioOrganizacion.demeOrganizaciones();
                FacesContext.getCurrentInstance().addMessage("form:messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Organización eliminada", null));
            } else {
                FacesContext.getCurrentInstance().addMessage("form:messages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se seleccionó ninguna organización"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("form:messages",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la organización: " + e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-org");
    }
    
    public void borrarOrganizacionesSeleccionadas() {
        if (organizacionesSeleccionadas != null && !organizacionesSeleccionadas.isEmpty()) {
            try {
                List<Organizacion> orgsAEliminar = new ArrayList<>(organizacionesSeleccionadas);
                for (Organizacion org : orgsAEliminar) {
                    servicioOrganizacion.eliminarOrganizacion(org.getCorreoElectronico());
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
        PrimeFaces.current().ajax().update("form:dt-org", "form:messages");
    }
    
    public void limpiarOrganizacion() {
        selectedOrganizacion = new Organizacion();
        esInsertarOrganizacion = false;
    }
    public String getDeleteButtonMessageUsuarios() {
    if (usuariosSeleccionados != null && !usuariosSeleccionados.isEmpty()) {
        int size = usuariosSeleccionados.size();
        return size > 1 ? size + " usuarios seleccionados" : "1 usuario seleccionado";
    }
    return "Borrar";
}
public String getDeleteButtonMessageOrganizaciones() {
    if (organizacionesSeleccionadas != null && !organizacionesSeleccionadas.isEmpty()) {
        int size = organizacionesSeleccionadas.size();
        return size > 1 ? size + " organizaciones seleccionadas" : "1 organizacion seleccionada";
    }
    return "Borrar";
}


   
    
    // Getters y setters para Usuarios
    public boolean isEsInsertarUsuario() {
        return esInsertarUsuario;
    }
    public void setEsInsertarUsuario(boolean esInsertarUsuario) {
        this.esInsertarUsuario = esInsertarUsuario;
    }
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
    
    // Getters y setters para Organizaciones
    public boolean isEsInsertarOrganizacion() {
        return esInsertarOrganizacion;
    }
    public void setEsInsertarOrganizacion(boolean esInsertarOrganizacion) {
        this.esInsertarOrganizacion = esInsertarOrganizacion;
    }
    public Organizacion getSelectedOrganizacion() {
        return selectedOrganizacion;
    }
    public void setSelectedOrganizacion(Organizacion selectedOrganizacion) {
        this.selectedOrganizacion = selectedOrganizacion;
    }
    public List<Organizacion> getListaOrganizaciones() {
        return listaOrganizaciones;
    }
    public void setListaOrganizaciones(List<Organizacion> listaOrganizaciones) {
        this.listaOrganizaciones = listaOrganizaciones;
    }
    public List<Organizacion> getOrganizacionesSeleccionadas() {
        return organizacionesSeleccionadas;
    }
    public void setOrganizacionesSeleccionadas(List<Organizacion> organizacionesSeleccionadas) {
        this.organizacionesSeleccionadas = organizacionesSeleccionadas;
    }
    public ServicioOrganizacion getServicioOrganizacion() {
        return servicioOrganizacion;
    }
    public void setServicioOrganizacion(ServicioOrganizacion servicioOrganizacion) {
        this.servicioOrganizacion = servicioOrganizacion;
    }
      public Oportunidades getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(Oportunidades oportunidades) {
        this.oportunidades = oportunidades;
    }

    public Oportunidades getSelectedOportunidades() {
        return selectedOportunidades;
    }

    public void setSelectedOportunidades(Oportunidades selectedOportunidades) {
        this.selectedOportunidades = selectedOportunidades;
    }

    public boolean isEsInsertarOportunidades() {
        return esInsertarOportunidades;
    }

    public void setEsInsertarOportunidades(boolean esInsertarOportunidades) {
        this.esInsertarOportunidades = esInsertarOportunidades;
    }

    public List<Oportunidades> getListaOportunidades() {
        return listaOportunidades;
    }

    public void setListaOportunidades(List<Oportunidades> listaOportunidades) {
        this.listaOportunidades = listaOportunidades;
    }

    public ServicioOportunidad getServicioOportunidades() {
        return servicioOportunidades;
    }

    public void setServicioOportunidades(ServicioOportunidad servicioOportunidades) {
        this.servicioOportunidades = servicioOportunidades;
    }

    public List<Oportunidades> getOportunidadesSeleccionadas() {
        return oportunidadesSeleccionadas;
    }

    public void setOportunidadesSeleccionadas(List<Oportunidades> oportunidadesSeleccionadas) {
        this.oportunidadesSeleccionadas = oportunidadesSeleccionadas;
    }

    private Oportunidades oportunidades = new Oportunidades();
    private Oportunidades selectedOportunidades;
    private boolean esInsertarOportunidades;
    private List<Oportunidades> listaOportunidades = new ArrayList<>();
    private ServicioOportunidad servicioOportunidades = new ServicioOportunidad();
    private List<Oportunidades> oportunidadesSeleccionadas = new ArrayList<>();

   
    

    public void openNew() {
        this.selectedOportunidades  = new Oportunidades();
        this.esInsertarOportunidades = true;
    }

    public void limpiar() {
        this.esInsertarOportunidades = false;
    }


    public void saveOportunidades() {
        try {
           if (selectedOportunidades != null && servicioOportunidades.validarOportunidades(selectedOportunidades.getId()) == null) {
                servicioOportunidades.insertarOportunidad(selectedOportunidades);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Oportunidad agregada correctamente"));
            } else {
                servicioOportunidades.actualizarOportunidad(selectedOportunidades);
                FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage("Oportunidad actualizada correctamente"));
            }
            listaOportunidades = servicioOportunidades.cargarOportunidades();
            PrimeFaces.current().executeScript("PF('manageOrgDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Oportuniddad no agregada. Intente de nuevo: " + e.getMessage()));
        }
        PrimeFaces.current().ajax().update("form:messages", "formOpor:dt-opor");
    }

    public void eliminarOportunidad() throws ClassNotFoundException {
        if (selectedOportunidades != null && selectedOportunidades.getId() > 0) {
            servicioOportunidades.eliminarOportunidad(selectedOportunidades.getId());
            listaOportunidades = servicioOportunidades.cargarOportunidades();
            FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Oportunidad eliminada", null));
        } else {
            FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se seleccionó ninguna oportunidad"));
        }
        PrimeFaces.current().ajax().update("form:messages", "formOpor:dt-opor");
    }
    public boolean hasSelectedOportunidad() {
        return this.oportunidadesSeleccionadas != null && !this.oportunidadesSeleccionadas.isEmpty();
    }
    public String getDeleteButtonMessage() {
        if (hasSelectedOportunidad()) {
            int size = this.oportunidadesSeleccionadas.size();
            return size > 1 ? size + " oportunidades seleccionadas" : "1 oportunidad seleccionada";
        }

        return "Borrar";}
    
 public void borrarOportunidadesSeleccionados() {
    if (oportunidadesSeleccionadas != null && !oportunidadesSeleccionadas.isEmpty()) {
        try {
            List<Oportunidades> oportunidadesAEliminar = new ArrayList<>(oportunidadesSeleccionadas);

            for (Oportunidades oportunidades : oportunidadesAEliminar) {
                servicioOportunidades.eliminarOportunidad(oportunidades.getId());
            }

            listaOportunidades = servicioOportunidades.cargarOportunidades();

            
            oportunidadesSeleccionadas.clear();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Oportunidades eliminadas correctamente.", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar las oportunidades seleccionadss: " + e.getMessage()));
        }
    } else {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No hay oportunidades seleccionados."));
    }

    PrimeFaces.current().ajax().update("formOpor:dt-opor", "form:messages");
     System.out.println("estoy borrando");
}



  
    }

