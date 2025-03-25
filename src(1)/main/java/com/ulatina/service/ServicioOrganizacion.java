/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Organizacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santi
 */
public class ServicioOrganizacion extends Servicio {

    public Organizacion validarOrganizacion(String user, String pass) throws ClassNotFoundException {
        Organizacion organizacion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, nombreRepresentante, cedulaRepresentante, correoElectronico, clave, numeroTelefonico, descripcion, provincia, canton, distrito "
                    + "FROM organizacion WHERE correoElectronico = ? AND clave = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                organizacion = new Organizacion();
                organizacion.setId(rs.getInt("id"));
                organizacion.setNombre(rs.getString("nombre"));
                organizacion.setNombreRepresentante(rs.getString("nombreRepresentante"));
                organizacion.setCedulaRepresentante(rs.getString("cedulaRepresentante"));
                organizacion.setCorreoElectronico(rs.getString("correoElectronico"));
                organizacion.setClave(rs.getString("clave"));
                organizacion.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                organizacion.setDescripcion(rs.getString("descripcion"));
                organizacion.setProvincia(rs.getString("provincia"));
                organizacion.setCanton(rs.getString("canton"));
                organizacion.setDistrito(rs.getString("distrito"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return organizacion;
    }

    private int idPrueba;

    public Organizacion validarOrganizacion(int id) throws ClassNotFoundException {
        Organizacion organizacion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, nombreRepresentante, cedulaRepresentante, "
                    + "correoElectronico, clave, numeroTelefonico, descripcion, "
                    + "provincia, canton, distrito "
                    + "FROM organizacion "
                    + "WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                organizacion = new Organizacion();
                Oportunidades oportunidades = new Oportunidades();

                organizacion.setId(rs.getInt("id"));
                organizacion.setNombre(rs.getString("nombre"));
                organizacion.setNombreRepresentante(rs.getString("nombreRepresentante"));
                organizacion.setCedulaRepresentante(rs.getString("cedulaRepresentante"));
                organizacion.setCorreoElectronico(rs.getString("correoElectronico"));
                organizacion.setClave(rs.getString("clave"));
                organizacion.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                organizacion.setDescripcion(rs.getString("descripcion"));
                organizacion.setProvincia(rs.getString("provincia"));
                organizacion.setCanton(rs.getString("canton"));
                organizacion.setDistrito(rs.getString("distrito"));
                oportunidades.setIdOrganizacion(organizacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return organizacion;
    }

    public void insertarOrganizacion(Organizacion organizacion) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "INSERT INTO organizacion (nombre, nombreRepresentante, cedulaRepresentante, correoElectronico, clave, numeroTelefonico, descripcion, provincia, canton, distrito) VALUES (?,?,?,?,?,?,?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, organizacion.getNombre());
            pstmt.setString(2, organizacion.getNombreRepresentante());
            pstmt.setString(3, organizacion.getCedulaRepresentante());
            pstmt.setString(4, organizacion.getCorreoElectronico());
            pstmt.setString(5, organizacion.getClave());
            pstmt.setString(6, organizacion.getNumeroTelefonico());
            pstmt.setString(7, organizacion.getDescripcion());
            pstmt.setString(8, organizacion.getProvincia());
            pstmt.setString(9, organizacion.getCanton());
            pstmt.setString(10, organizacion.getDistrito());

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

    public void actualizarClaveOrganizacion(String correoElectronico, String clave) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "UPDATE organizacion SET clave = ? WHERE correoElectronico = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, clave);
            pstmt.setString(2, correoElectronico);
            int cantidad = pstmt.executeUpdate();

            if (cantidad == 0) {
                throw new SQLException("No se logró actualizar la clave de la organizacion");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }

    public void eliminarOrganizacion(String correo) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "DELETE FROM organizacion WHERE correoElectronico = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, correo);

            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logró eliminar el usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarConexion();
        }
    }

    public void actualizarOrganizacion(Organizacion organizacion) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "UPDATE organizacion SET "
                    + "nombre = ?, nombreRepresentante = ?, cedulaRepresentante = ?, correoElectronico = ?, "
                    + "clave = ?, numeroTelefonico = ?, descripcion = ?, provincia = ?, canton = ?, distrito = ? "
                    + "WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, organizacion.getNombre());
            pstmt.setString(2, organizacion.getNombreRepresentante());
            pstmt.setString(3, organizacion.getCedulaRepresentante());
            pstmt.setString(4, organizacion.getCorreoElectronico());
            pstmt.setString(5, organizacion.getClave());
            pstmt.setString(6, organizacion.getNumeroTelefonico());
            pstmt.setString(7, organizacion.getDescripcion());
            pstmt.setString(8, organizacion.getProvincia());
            pstmt.setString(9, organizacion.getCanton());
            pstmt.setString(10, organizacion.getDistrito());
            pstmt.setInt(11, organizacion.getId());
            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logró actualizar la organización");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarConexion();
        }
    }

    public void actualizarOrganizacionEditar(Organizacion organizacion, int id) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "UPDATE organizacion SET "
                    + "nombre = ?, nombreRepresentante = ?, cedulaRepresentante = ?, correoElectronico = ?, "
                    + "clave = ?, numeroTelefonico = ?, descripcion = ?, provincia = ?, canton = ?, distrito = ? "
                    + "WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, organizacion.getNombre());
            pstmt.setString(2, organizacion.getNombreRepresentante());
            pstmt.setString(3, organizacion.getCedulaRepresentante());
            pstmt.setString(4, organizacion.getCorreoElectronico());
            pstmt.setString(5, organizacion.getClave());
            pstmt.setString(6, organizacion.getNumeroTelefonico());
            pstmt.setString(7, organizacion.getDescripcion());
            pstmt.setString(8, organizacion.getProvincia());
            pstmt.setString(9, organizacion.getCanton());
            pstmt.setString(10, organizacion.getDistrito());
            pstmt.setInt(11, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarConexion();
        }
    }

    public List<Organizacion> demeOrganizaciones() throws ClassNotFoundException {
        List<Organizacion> lista = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, nombreRepresentante, cedulaRepresentante, "
                    + "correoElectronico, clave, numeroTelefonico, descripcion, "
                    + "provincia, canton, distrito "
                    + "FROM organizacion";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Organizacion Organizacion = new Organizacion();
                Organizacion.setId(rs.getInt("id"));
                Organizacion.setNombre(rs.getString("nombre"));
                Organizacion.setNombreRepresentante(rs.getString("nombreRepresentante"));
                Organizacion.setCedulaRepresentante(rs.getString("cedulaRepresentante"));
                Organizacion.setCorreoElectronico(rs.getString("correoElectronico"));
                Organizacion.setClave(rs.getString("clave"));
                Organizacion.setNumeroTelefonico(rs.getString("numeroTelefonico"));
                Organizacion.setDescripcion(rs.getString("descripcion"));
                Organizacion.setProvincia(rs.getString("provincia"));
                Organizacion.setCanton(rs.getString("canton"));
                Organizacion.setDistrito(rs.getString("distrito"));
                lista.add(Organizacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return lista;
    }

    public Organizacion obtenerIdOrganizacion(String correo) throws ClassNotFoundException {

        Organizacion organizacion = null;
        Oportunidades oportunidades = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT * FROM organizacion WHERE correoElectronico = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, correo);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                organizacion = new Organizacion();
                oportunidades = new Oportunidades();
                organizacion.setId(rs.getInt("id"));
                oportunidades.setIdOrganizacion(organizacion);

            }
        } catch (SQLException e) {
        } finally {

            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();

        }
        return organizacion;
    }

}
