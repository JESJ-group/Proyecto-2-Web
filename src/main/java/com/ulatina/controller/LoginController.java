package com.ulatina.controller;

import com.ulatina.data.Usuario;
import com.ulatina.service.ServicioUsuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private String user;
    private String pass;
    private Usuario usuario;
    private ServicioUsuario servicioUsuario = new ServicioUsuario();

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
    
   
    public void ingresar() throws ClassNotFoundException {
        usuario = servicioUsuario.validarOrganizacion(user, pass);
        if (usuario != null) {
            redireccionar("/organizaciones.xhtml");
            return;
        }
        
        usuario = servicioUsuario.validarUsuario(user, pass);
        if (usuario != null) {
            redireccionar("/usuarios.xhtml");
            return;
        }
        
        
        if (user.equals("admin")&&pass.equals("adminadmin")) {
            redireccionar("/admin.xhtml");
            return;
        }
        
        FacesContext.getCurrentInstance().addMessage("form:messages",
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Campos inválidos", "El usuario o contraseña no son correctos"));
    }


    public void redireccionar(String ruta) {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
