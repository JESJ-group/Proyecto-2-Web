/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.Organizaciones;
import com.ulatina.data.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEsus
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
    
    public Usuario validarUsuario(String correo) throws ClassNotFoundException {
        Usuario usuario = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo, clave FROM  usuario WHERE correo = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, correo);

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
            //paso 4
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }

        return usuario;

    }

    public List<Usuario> demeUsuarios() throws ClassNotFoundException {
        List<Usuario> listaRetorno = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo, clave FROM usuario";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));

                listaRetorno.add(usuario);
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

    public void insertar(Usuario usuario) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "INSERT INTO usuario(nombre, correo, clave) VALUES (?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getClave());

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

    public void actualizar(Usuario usuario) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "UPDATE usuario SET nombre = ?,clave = ? WHERE correo = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getClave());
            pstmt.setString(3, usuario.getCorreo());

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

    public void eliminarUsuario(String correo) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "DELETE FROM usuario WHERE correo = ?";
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
