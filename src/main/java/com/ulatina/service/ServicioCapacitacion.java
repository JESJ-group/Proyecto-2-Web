/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Capacitacion;
import com.ulatina.data.Oportunidades;
import com.ulatina.data.Organizacion;
import com.ulatina.data.Postulaciones;
import com.ulatina.data.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SC
 */
public class ServicioCapacitacion extends Servicio {

    public void insertarCapaciotacion(Capacitacion capacitacion, String correo) throws ClassNotFoundException {
        PreparedStatement pstmt = null;

        ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();
        Organizacion organizacion = servicioOrganizacion.obtenerIdOrganizacion(correo);
        capacitacion.setIdOrganizacion(organizacion);
        LocalDate fechaPublicacion = LocalDate.now();
        capacitacion.setFechaPublicacion(fechaPublicacion);

        try {
            super.conectarBD();
            String sql = "INSERT INTO capacitaciones (idOrganizacion, titulo, descripcion, urlVideo, categoria, fechaPublicacion) VALUES (?,?,?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, capacitacion.getIdOrganizacion().getId());
            pstmt.setString(2, capacitacion.getTitulo());
            pstmt.setString(3, capacitacion.getDescripcion());
            pstmt.setString(4, capacitacion.getUrlVideo());
            pstmt.setString(5, capacitacion.getCategoria());

            Date sqlDate = Date.valueOf(capacitacion.getFechaPublicacion());
            pstmt.setDate(6, sqlDate);

            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logró registrar la capacitacion");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();

        }

    }

    public List<Capacitacion> cargarCapacitaciones(int idOrganizacion) {

        List<Capacitacion> listaCapacitaciones = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = ("SELECT * FROM capacitaciones c, organizacion org WHERE c.idOrganizacion = org.id AND org.id = ?");

            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, idOrganizacion);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Capacitacion capacitacion = new Capacitacion();

                capacitacion.setId(rs.getInt("id"));
                capacitacion.setTitulo(rs.getString("titulo"));
                capacitacion.setDescripcion(rs.getString("descripcion"));
                capacitacion.setUrlVideo(rs.getString("urlVideo"));
                capacitacion.setCategoria(rs.getString("categoria"));
                capacitacion.setFechaPublicacion(rs.getDate("fechaPublicacion").toLocalDate());

                listaCapacitaciones.add(capacitacion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }

        return listaCapacitaciones;
    }

    public List<Capacitacion> mostrarCapacitaciones() {

        List<Capacitacion> listaCompletaCapacitaciones = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = ("SELECT * FROM capacitaciones c, organizacion org WHERE c.idOrganizacion = org.id");

            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Capacitacion capacitacion = new Capacitacion();

                capacitacion.setId(rs.getInt("id"));
                capacitacion.setTitulo(rs.getString("titulo"));
                capacitacion.setDescripcion(rs.getString("descripcion"));
                capacitacion.setUrlVideo(rs.getString("urlVideo"));
                capacitacion.setCategoria(rs.getString("categoria"));
                capacitacion.setFechaPublicacion(rs.getDate("fechaPublicacion").toLocalDate());

                listaCompletaCapacitaciones.add(capacitacion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }

        return listaCompletaCapacitaciones;
    }

}
