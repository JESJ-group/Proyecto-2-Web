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
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author SC
 */
public class ServicioPostulaciones extends Servicio {
  public void insertarPostulacion(Postulaciones postulaciones, int idOportunidades, int idUsuario) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = null;
        ServicioOportunidad servicioOportunidad = new ServicioOportunidad();
        ServicioUsuario servicioUsuario = new ServicioUsuario();

        Usuario usu = servicioUsuario.obtenerIdUsuario(idUsuario);
        postulaciones.setIdUsuario(usu);
        LocalDate fechaPostulacion = LocalDate.now();
        postulaciones.setFechaPostulacion(fechaPostulacion);
        postulaciones.setEstado("Pendiente de revisión");
        
        Oportunidades oportunidad = servicioOportunidad.obtenerIdOportunidad(idOportunidades);
        postulaciones.setIdOportunidades(oportunidad);
        try {
            super.conectarBD();
            String sql = "INSERT INTO postulaciones (idOportunidades, idUsuario, estado, fechaPostulacion) VALUES (?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, postulaciones.getIdOportunidades().getId());
            pstmt.setInt(2, postulaciones.getIdUsuario().getId());
            pstmt.setString(3, postulaciones.getEstado());

            Date sqlDate = Date.valueOf(postulaciones.getFechaPostulacion());
            pstmt.setDate(4, sqlDate);

            int cantidad = pstmt.executeUpdate();

            if (cantidad == 0) {
                throw new SQLException("No se logró registrar la postulacion");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("asdfasdf" + postulaciones.getIdUsuario().getId());
            System.out.println("Fecha de postulación: " + fechaPostulacion);
            cerrarPreparedStatement(pstmt);
            cerrarConexion();

        }
    }
        public boolean existePostulacion(Postulaciones postulaciones, int idUsuario, int idOportunidades) throws SQLException, ClassNotFoundException {
             ServicioOportunidad servicioOportunidad = new ServicioOportunidad();
        ServicioUsuario servicioUsuario = new ServicioUsuario(); 
            Usuario usu = servicioUsuario.obtenerIdUsuario(idUsuario);
        postulaciones.setIdUsuario(usu);
       
        
        Oportunidades oportunidad = servicioOportunidad.obtenerIdOportunidad(idOportunidades);
        postulaciones.setIdOportunidades(oportunidad);
            boolean existe = false;
        super.conectarBD();
        String sql = "SELECT COUNT(*) FROM postulaciones WHERE idOportunidades = ? AND idUsuario = ?";
        try (PreparedStatement pstmt = super.getConexion().prepareStatement(sql)) {
            pstmt.setInt(1, postulaciones.getIdOportunidades().getId());
            pstmt.setInt(2, postulaciones.getIdUsuario().getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    existe = true;
                }
            }
        }
        return existe;
    }
  public Postulaciones obtenerPostulacion(int idUsuario, int idOportunidad) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Postulaciones postulacion = null;
        postulacion = new Postulaciones();

       

        

        
        try {
            super.conectarBD();
            String sql = "SELECT * FROM postulaciones WHERE idUsuario = ? AND idOportunidades = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idOportunidad);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                postulacion.setEstado(rs.getString("estado"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }

        return postulacion;
    }
}
