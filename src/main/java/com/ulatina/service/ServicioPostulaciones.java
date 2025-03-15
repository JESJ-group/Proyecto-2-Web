/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Postulaciones;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author SC
 */
public class ServicioPostulaciones extends Servicio {

    public void insertarPostulacion(Postulaciones postulaciones) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "INSERT INTO postulaciones (idOrganizacion, idUsuario, estado, fechaPostulacion) VALUES (?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, postulaciones.getIdOportunidades());
            pstmt.setInt(2, postulaciones.getIdUsuario());
            pstmt.setString(3, postulaciones.getEstado());
            pstmt.setDate(4, java.sql.Date.valueOf(postulaciones.getFechaPostulacion()));

            int cantidad = pstmt.executeUpdate();

            if (cantidad == 0) {
                throw new SQLException("No se logró registrar la organizacion");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }
}
