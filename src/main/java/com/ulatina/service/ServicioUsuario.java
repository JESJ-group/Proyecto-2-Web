/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chuz
 */
public class ServicioUsuario extends Servicio {

    public Usuario validarUsuario(String user, String pass) throws ClassNotFoundException {
        Usuario usuario = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo, clave FROM usuario WHERE correo = ? AND clave = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return usuario;
    }

     
    public Usuario validarOrganizacion(String user, String pass) throws ClassNotFoundException {
        Usuario usuario = null;
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
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return usuario;
    }

    public void actualizarClave(String correo, String clave) {

        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "UPDATE usuario SET clave = ? WHERE correo = ?";
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


 

   
}



