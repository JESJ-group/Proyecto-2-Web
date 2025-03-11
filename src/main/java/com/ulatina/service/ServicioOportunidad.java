/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.controller.LoginController;
import com.ulatina.data.Oportunidades;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ryzon
 */


public class ServicioOportunidad extends Servicio{
    
    
    public void insertarOportunidad(Oportunidades oportunidades) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "INSERT INTO oportunidades (idOrganizacion, titulo, descripcion, tipo, duracion, jornada, modalidad, pago, ubicacion, provincia) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, 1);
            pstmt.setString(2, oportunidades.getTitulo());
            pstmt.setString(3, oportunidades.getDescripcion());
            pstmt.setString(4, oportunidades.getTipo());
            pstmt.setString(5, oportunidades.getDuracion());
            pstmt.setString(6, oportunidades.getJornada());
            pstmt.setString(7, oportunidades.getModalidad());
            pstmt.setString(8, oportunidades.getPago());
            pstmt.setString(9, oportunidades.getUbicacion());
            pstmt.setString(10, oportunidades.getProvincia());

            int cantidad = pstmt.executeUpdate();

            if (cantidad == 0) {
                throw new SQLException("No se logró registrar la oportunidad");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }
    
}
