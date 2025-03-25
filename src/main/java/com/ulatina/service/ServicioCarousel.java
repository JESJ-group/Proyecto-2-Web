/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryzon
 */
public class ServicioCarousel extends Servicio {

    public List<String> obtenerRutasImagenes() throws ClassNotFoundException, SQLException {
        List<String> rutas = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = "SELECT rutaImagen FROM organizacion WHERE rutaImagen IS NOT NULL";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                rutas.add(rs.getString("rutaImagen"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }

        return rutas;
    }
}
