package com.ulatina.service;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Organizacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioOportunidad extends Servicio {

    public List<Oportunidades> cargarOportunidades() throws ClassNotFoundException {
        List<Oportunidades> listaOportunidades = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, idOrganizacion, titulo, descripcion, tipo, duracion, provincia, jornada, modalidad, pago, ubicacion FROM oportunidades";
            pstmt = super.getConexion().prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Oportunidades op = new Oportunidades();
                Organizacion org = new Organizacion();
                op.setId(rs.getInt("id"));
                org.setId(rs.getInt("idOrganizacion"));
                op.setIdOrganizacion(org);
                op.setTitulo(rs.getString("titulo"));
                op.setDescripcion(rs.getString("descripcion"));
                op.setTipo(rs.getString("tipo"));
                op.setDuracion(rs.getString("duracion"));
                op.setProvincia(rs.getString("provincia"));
                op.setJornada(rs.getString("jornada"));
                op.setModalidad(rs.getString("modalidad"));
                op.setPago(rs.getString("pago"));
                op.setUbicacion(rs.getString("ubicacion"));
                listaOportunidades.add(op);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return listaOportunidades;
    }

    public void insertarOportunidad(Oportunidades op) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "INSERT INTO oportunidades (idOrganizacion, titulo, descripcion, tipo, duracion, provincia,"
                    + " jornada, modalidad, pago, ubicacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, 1);
            pstmt.setString(2, op.getTitulo());
            pstmt.setString(3, op.getDescripcion());
            pstmt.setString(4, op.getTipo());
            pstmt.setString(5, op.getDuracion());
            pstmt.setString(6, op.getProvincia());
            pstmt.setString(7, op.getJornada());
            pstmt.setString(8, op.getModalidad());
            pstmt.setString(9, op.getPago());
            pstmt.setString(10, op.getUbicacion());
            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logró insertar la oportunidad");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }
    
     
    

    public Oportunidades validarOportunidades(int id) throws ClassNotFoundException {
        Oportunidades op = null;
        Organizacion org = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT id, idOrganizacion, titulo, descripcion, tipo, duracion, provincia, jornada, modalidad, pago, ubicacion FROM oportunidades WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                op = new Oportunidades();
                org = new Organizacion();
                op.setId(rs.getInt("id"));
                org.setId(rs.getInt("idOrganizacion"));
                op.setIdOrganizacion(org);
                op.setTitulo(rs.getString("titulo"));
                op.setDescripcion(rs.getString("descripcion"));
                op.setTipo(rs.getString("tipo"));
                op.setDuracion(rs.getString("duracion"));
                op.setProvincia(rs.getString("provincia"));
                op.setJornada(rs.getString("jornada"));
                op.setModalidad(rs.getString("modalidad"));
                op.setPago(rs.getString("pago"));
                op.setUbicacion(rs.getString("ubicacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();
        }
        return op;
    }

    public void actualizarOportunidad(Oportunidades op) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "UPDATE oportunidades SET idOrganizacion = ?, titulo = ?, descripcion = ?, tipo = ?, duracion = ?, provincia = ?, jornada = ?, modalidad = ?, pago = ?, ubicacion = ? WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, op.getIdOrganizacion().getId());
            pstmt.setString(2, op.getTitulo());
            pstmt.setString(3, op.getDescripcion());
            pstmt.setString(4, op.getTipo());
            pstmt.setString(5, op.getDuracion());
            pstmt.setString(6, op.getProvincia());
            pstmt.setString(7, op.getJornada());
            pstmt.setString(8, op.getModalidad());
            pstmt.setString(9, op.getPago());
            pstmt.setString(10, op.getUbicacion());
            pstmt.setInt(11, op.getId());
            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logró actualizar la oportunidad");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }

    public void eliminarOportunidad(int id) {
        PreparedStatement pstmt = null;
        try {
            super.conectarBD();
            String sql = "DELETE FROM oportunidades WHERE id = ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, id);
            int cantidad = pstmt.executeUpdate();
            if (cantidad == 0) {
                throw new SQLException("No se logró eliminar la oportunidad");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();
        }
    }
}
