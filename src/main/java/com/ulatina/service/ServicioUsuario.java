package com.ulatina.service;

import com.ulatina.data.Postulaciones;
import com.ulatina.data.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServicioUsuario extends Servicio {
Postulaciones postulaciones = new Postulaciones();

    public Postulaciones getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Postulaciones postulaciones) {
        this.postulaciones = postulaciones;
    }
    public Usuario validarUsuario(String user, String pass) throws ClassNotFoundException {
        Usuario usuario = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, apellido, correoElectronico, clave, fechaNacimiento, genero, estatus, provincia, canton, distrito, numeroContacto "
                       + "FROM usuario WHERE correoElectronico = ? AND clave = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellido"));
                usuario.setCorreoElectronico(rs.getString("correoElectronico"));
                usuario.setClave(rs.getString("clave"));
                Date fecha = rs.getDate("fechaNacimiento");
                usuario.setFechaNacimiento(fecha);
                usuario.setGenero(rs.getString("genero"));
                usuario.setEstatus(rs.getString("estatus"));
                usuario.setProvincia(rs.getString("provincia"));
                usuario.setCanton(rs.getString("canton"));
                usuario.setDistrito(rs.getString("distrito"));
                usuario.setNumeroContacto(rs.getString("numeroContacto"));
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
public Usuario obtenerIdUsuario(int id) throws ClassNotFoundException {

        
        Usuario usuario = null;
Postulaciones postulaciones= null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT * FROM usuario WHERE id= ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                postulaciones = new Postulaciones();
                usuario = new Usuario();
                postulaciones.setId(rs.getInt("id"));
                postulaciones.setIdUsuario(postulaciones.getIdUsuario());

            }
        } catch (SQLException e) {
        } finally {
            
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();

        }
        return usuario;
    }
    
    
    public Usuario validarUsuario(int id) throws ClassNotFoundException {
        Usuario usuario = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, apellido, correoElectronico, clave, fechaNacimiento, "
                       + "genero, estatus, provincia, canton, distrito, numeroContacto "
                       + "FROM usuario "
                       + "WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellido"));
                usuario.setCorreoElectronico(rs.getString("correoElectronico"));
                usuario.setClave(rs.getString("clave"));
                Date fecha = rs.getDate("fechaNacimiento");
                usuario.setFechaNacimiento(fecha);
                usuario.setGenero(rs.getString("genero"));
                usuario.setEstatus(rs.getString("estatus"));
                usuario.setProvincia(rs.getString("provincia"));
                usuario.setCanton(rs.getString("canton"));
                usuario.setDistrito(rs.getString("distrito"));
                usuario.setNumeroContacto(rs.getString("numeroContacto"));
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

    public void insertarUsuario(Usuario usuario) {
        PreparedStatement pstmt = null;

        try {
            super.conectarBD();
            String sql = "INSERT INTO usuario (nombre, apellido, correoElectronico, clave, fechaNacimiento, genero, estatus, provincia, canton, distrito, numeroContacto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = super.getConexion().prepareStatement(sql);
                                                                                                                                            
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setString(3, usuario.getCorreoElectronico());
            pstmt.setString(4, usuario.getClave());
            pstmt.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            pstmt.setString(6, usuario.getGenero());
            pstmt.setString(7, usuario.getEstatus());
            pstmt.setString(8, usuario.getProvincia());
            pstmt.setString(9, usuario.getCanton());
            pstmt.setString(10, usuario.getDistrito());
            pstmt.setString(11, usuario.getNumeroContacto());

            int cantidad = pstmt.executeUpdate();

            if (cantidad == 0) {
                throw new SQLException("No se logró registrar el usuario");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }
    
    public void actualizarClaveUsuario(String correoElectronico, String clave) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "UPDATE usuario SET clave = ? WHERE correoElectronico = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, clave);
            pstmt.setString(2, correoElectronico);
            int cantidad = pstmt.executeUpdate();

            if (cantidad == 0) {
                throw new SQLException("No se logró actualizar la clave del usuario");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }
    
    public List<Usuario> demeUsuarios() throws ClassNotFoundException {
        List<Usuario> listaRetorno = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, apellido, correoElectronico, clave, fechaNacimiento, "
                       + "genero, estatus, provincia, canton, distrito, numeroContacto "
                       + "FROM usuario";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellido"));
                usuario.setCorreoElectronico(rs.getString("correoElectronico"));
                usuario.setClave(rs.getString("clave"));
                Date fecha = rs.getDate("fechaNacimiento");
                usuario.setFechaNacimiento(fecha);
                usuario.setGenero(rs.getString("genero"));
                usuario.setEstatus(rs.getString("estatus"));
                usuario.setProvincia(rs.getString("provincia"));
                usuario.setCanton(rs.getString("canton"));
                usuario.setDistrito(rs.getString("distrito"));
                usuario.setNumeroContacto(rs.getString("numeroContacto"));
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
    
    
    
    public void actualizar(Usuario usuario) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "UPDATE usuario SET "
                       + "nombre = ?, apellido = ?, correoElectronico = ?, clave = ?, fechaNacimiento = ?, "
                       + "genero = ?, estatus = ?, provincia = ?, canton = ?, distrito = ?, numeroContacto = ? "
                       + "WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setString(3, usuario.getCorreoElectronico());
            pstmt.setString(4, usuario.getClave());
            if (usuario.getFechaNacimiento() != null) {
                pstmt.setDate(5, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }
            pstmt.setString(6, usuario.getGenero());
            pstmt.setString(7, usuario.getEstatus());
            pstmt.setString(8, usuario.getProvincia());
            pstmt.setString(9, usuario.getCanton());
            pstmt.setString(10, usuario.getDistrito());
            pstmt.setString(11, usuario.getNumeroContacto());
            pstmt.setInt(12, usuario.getId());
            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logró actualizar el usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarConexion();
        }
    }

    public void eliminarUsuario(String correoElectronico) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "DELETE FROM usuario WHERE correoElectronico = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setString(1, correoElectronico);
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
