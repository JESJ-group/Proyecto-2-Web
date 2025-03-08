/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Organizaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEsus
 */
public class ServicioOrganizacion extends Servicio {

       public Organizaciones validarOrganizacion(String user, String pass) throws ClassNotFoundException {
        Organizaciones organizaciones = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo, clave FROM organizacion WHERE correo = ? AND clave = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                organizaciones = new Organizaciones();
                organizaciones.setId(rs.getInt("id"));
                organizaciones.setNombre(rs.getString("nombre"));
                organizaciones.setCorreo(rs.getString("correo"));
                organizaciones.setClave(rs.getString("clave"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return organizaciones;
    }

    
    public Organizaciones validarOrganizacion(String correo) throws ClassNotFoundException {
        Organizaciones organizaciones = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo, clave FROM organizacion WHERE correo = ? AND clave = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, correo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                organizaciones = new Organizaciones();
                organizaciones.setId(rs.getInt("id"));
                organizaciones.setNombre(rs.getString("nombre"));
                organizaciones.setCorreo(rs.getString("correo"));
                organizaciones.setClave(rs.getString("clave"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return organizaciones;
    }

    public List<Organizaciones> demeOrganizaciones() throws ClassNotFoundException {
        List<Organizaciones> listaRetorno = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo, clave FROM organizacion";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Organizaciones organizaciones = new Organizaciones();
                organizaciones.setId(rs.getInt("id"));
                organizaciones.setNombre(rs.getString("nombre"));
                organizaciones.setCorreo(rs.getString("correo"));
                organizaciones.setClave(rs.getString("clave"));

                listaRetorno.add(organizaciones);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return listaRetorno;
    }

 
    public void actualizarClave(String correo, String clave) {

        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "UPDATE organizacion SET clave = ? WHERE correo = ?";
            pstmt = super.getConexion().prepareStatement(sql);

            pstmt.setString(1, clave);
            pstmt.setString(2, correo);
            int cantidad = pstmt.executeUpdate();

            if (cantidad == 0) {
                throw new SQLException("No se logro realizar el insert del usuario");
            }

        } catch (ClassNotFoundException | SQLException e) {
        } finally {

            cerrarPreparedStatement(pstmt);
            cerrarConexion();

        }

    }

    public void insertarOrganizacion(Organizaciones organizaciones) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "INSERT INTO organizacion(nombre, correo, clave) VALUES (?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, organizaciones.getNombre());
            pstmt.setString(2, organizaciones.getCorreo());
            pstmt.setString(3, organizaciones.getClave());

            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logro insertar el usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarConexion();
        }
    }

    public void actualizarOrganizacion(Organizaciones organizaciones) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "UPDATE organizacion SET nombre = ?,clave = ? WHERE correo = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, organizaciones.getNombre());
            pstmt.setString(2, organizaciones.getClave());
            pstmt.setString(3, organizaciones.getCorreo());

            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logro actualizar el usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarConexion();
        }

    }

    public void eliminarOrganizacion(String correo) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "DELETE FROM organizacion WHERE correo = ?";
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

   

}
