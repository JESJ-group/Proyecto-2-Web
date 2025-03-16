/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Postulaciones;
import com.ulatina.data.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author SC
 */
public class ServicioPostulaciones extends Servicio {
    
    int id;
    int idUsuario;
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    Postulaciones postulaciones = new Postulaciones();
    
    public Postulaciones getPostulaciones() {
        return postulaciones;
    }
    
    public void setPostulaciones(Postulaciones postulaciones) {
        this.postulaciones = postulaciones;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void insertarPostulacion(Postulaciones postulaciones, int idOportunidades, int idUsuario) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = null;
        ServicioOportunidad servicioOportunidad = new ServicioOportunidad();
        ServicioUsuario servicioUsuario = new ServicioUsuario();
        Usuario usu = servicioUsuario.obtenerIdUsuario(idUsuario);
        postulaciones.setIdUsuario(usu);
        Oportunidades oportunidad = servicioOportunidad.obtenerIdOportunidad(idOportunidades);
        postulaciones.setIdOportunidades(oportunidad);
        try {
            super.conectarBD();
            String sql = "INSERT INTO postulaciones (idOportunidades, idUsuario, estado, fechaPostulacion) VALUES (?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, postulaciones.getIdOportunidades().getId());
            pstmt.setInt(2, postulaciones.getIdUsuario().getId());
            pstmt.setString(3, postulaciones.getEstado());
            LocalDate fechaPostulacion = postulaciones.getFechaPostulacion();
            pstmt.setDate(4, java.sql.Date.valueOf(fechaPostulacion));
            
            int cantidad = pstmt.executeUpdate();
            
            if (cantidad == 0) {
                throw new SQLException("No se logró registrar la postulacion");
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }
    
}
