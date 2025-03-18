package com.ulatina.controller;

import com.ulatina.data.Organizacion;
import com.ulatina.data.Usuario;
import com.ulatina.service.Servicio;
import com.ulatina.service.ServicioOrganizacion;
import com.ulatina.service.ServicioUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private String user;
    private String pass;
    private Usuario usuario;
    private Organizacion organizacion;
    private ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();

    private String tipoUsuario = "invitado";


    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

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
    
    
    public Organizacion getOrganizacion() {
        return organizacion;
    }
    
    Servicio servicio = new Servicio(){
        
    };
    
    public void ingresar() throws ClassNotFoundException {
        organizacion = servicioOrganizacion.validarOrganizacion(user, pass);
        if (organizacion != null) {
            tipoUsuario = "organizacion";
            servicio.redireccionar("/landingPageOrganizacion.xhtml");
            return;
        }
        
        usuario = servicioUsuario.validarUsuario(user, pass);
        if (usuario != null) {
            tipoUsuario = "usuario";
            servicio.redireccionar("/landingPageUsuario.xhtml");
            return;
        }
       
        
        if (user.equals("admin") && pass.equals("adminadmin")) {
            servicio.redireccionar("/crudUsuario.xhtml");
            return;
        }
        
        FacesContext.getCurrentInstance().addMessage("form:messages",
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Campos inválidos", "El correo o contraseña no son correctos"));
    }
    
    public void salirConexion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/faces/index.xhtml?faces-redirect=true");
            tipoUsuario = "invitado";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    



}
