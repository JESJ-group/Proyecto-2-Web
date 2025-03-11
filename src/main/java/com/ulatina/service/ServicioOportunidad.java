/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.controller.LoginController;
import com.ulatina.data.Oportunidades;
import com.ulatina.data.Organizacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryzon
 */


public class ServicioOportunidad extends Servicio{
    public List<Oportunidades> cargarOportunidades() {
        List<Oportunidades> listaOportunidades = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = "SELECT id, idOrganizacion, titulo, descripcion, tipo, duracion, jornada,modalidad, pago,ubicacion,provincia FROM Oportunidades";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Oportunidades oportunidades = new Oportunidades();
                Organizacion organizacion = new Organizacion();
                oportunidades.setId(rs.getInt("id"));
                organizacion.setId(rs.getInt("idOrganizacion"));
                oportunidades.setIdOrganizacion(organizacion);
                oportunidades.setTitulo(rs.getString("titulo"));
                oportunidades.setDescripcion(rs.getString("descripcion"));
                oportunidades.setTipo(rs.getString("tipo"));
                oportunidades.setDuracion(rs.getString("duracion"));
                oportunidades.setJornada(rs.getString("jornada"));
                oportunidades.setModalidad(rs.getString("modalidad"));
                oportunidades.setPago(rs.getString("pago"));
                oportunidades.setUbicacion(rs.getString("ubicacion"));
                oportunidades.setProvincia(rs.getString("provincia"));
                
                listaOportunidades.add(oportunidades);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }

        return listaOportunidades;
    }
    
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
