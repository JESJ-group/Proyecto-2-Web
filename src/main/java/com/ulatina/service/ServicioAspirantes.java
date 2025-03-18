/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ServicioAspirantes extends Servicio {

    public List<Oportunidades> cargarOportunidadesOrganizacion(int idOrganizacion) {

        List<Oportunidades> listaOportunidadesOrganizacion = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            super.conectarBD();
            String sql = "SELECT * "
                    + "FROM Oportunidades o, Organizacion org WHERE o.idOrganizacion = org.id "
                    + "AND org.id = ?";

            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, idOrganizacion);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Oportunidades oportunidades = new Oportunidades();

                oportunidades.setId(rs.getInt("id"));
                oportunidades.setTitulo(rs.getString("titulo"));
                oportunidades.setDescripcion(rs.getString("descripcion"));
                oportunidades.setDetalles(rs.getString("detalles"));
                oportunidades.setTipo(rs.getString("tipo"));
                oportunidades.setDuracion(rs.getString("duracion"));
                oportunidades.setJornada(rs.getString("jornada"));
                oportunidades.setModalidad(rs.getString("modalidad"));
                oportunidades.setPago(rs.getString("pago"));
                oportunidades.setUbicacion(rs.getString("ubicacion"));
                oportunidades.setProvincia(rs.getString("provincia"));

                listaOportunidadesOrganizacion.add(oportunidades);
            }

        } catch (Exception e) {
        } finally {

            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();

        }

        return listaOportunidadesOrganizacion;

    }

    public List<Usuario> visualizarAspirantesOportunidad(int idOportunidad) {

        List<Usuario> listaAspirantesUsuarios = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            super.conectarBD();
            String sql = "SELECT * "
                    + "FROM postulaciones p, usuario u, oportunidades o "
                    + "where o.id = p.idOportunidades "
                    + "and p.idUsuario = u.id "
                    + "and o.id = ?";

            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, idOportunidad);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellido"));
                usuario.setCorreoElectronico(rs.getString("correoElectronico"));
                Date fecha = rs.getDate("fechaNacimiento");
                usuario.setFechaNacimiento(fecha);
                usuario.setGenero(rs.getString("genero"));
                usuario.setEstatus(rs.getString("estatus"));
                usuario.setProvincia(rs.getString("provincia"));
                usuario.setCanton(rs.getString("canton"));
                usuario.setDistrito(rs.getString("distrito"));
                usuario.setNumeroContacto(rs.getString("numeroContacto"));

                listaAspirantesUsuarios.add(usuario);
            }

        } catch (Exception e) {
        } finally {

            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();

        }

        return listaAspirantesUsuarios;

    }

}
