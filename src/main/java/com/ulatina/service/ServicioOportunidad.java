package com.ulatina.service;

import com.ulatina.data.Oportunidades;
import com.ulatina.data.Organizacion;
import com.ulatina.data.Postulaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioOportunidad extends Servicio {

   private String filtro;

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    private String filtroProvincia, filtroModalidad, filtroJornada, filtroPago, filtroTipo, filtroDuracion;

    public String getFiltroProvincia() {
        return filtroProvincia;
    }

    public String getFiltroTipo() {
        return filtroTipo;
    }

    public void setFiltroTipo(String filtroTipo) {
        this.filtroTipo = filtroTipo;
    }

    public String getFiltroDuracion() {
        return filtroDuracion;
    }

    public void setFiltroDuracion(String filtroDuracion) {
        this.filtroDuracion = filtroDuracion;
    }

    public void setFiltroProvincia(String filtroProvincia) {
        this.filtroProvincia = filtroProvincia;
    }

    public String getFiltroModalidad() {
        return filtroModalidad;
    }

    public void setFiltroModalidad(String filtroModalidad) {
        this.filtroModalidad = filtroModalidad;
    }

    public String getFiltroJornada() {
        return filtroJornada;
    }

    public void setFiltroJornada(String filtroJornada) {
        this.filtroJornada = filtroJornada;
    }

    public String getFiltroPago() {
        return filtroPago;
    }

    public void setFiltroPago(String filtroPago) {
        this.filtroPago = filtroPago;
    }
        
public List<Oportunidades> cargarOportunidades() {

        List<Oportunidades> listaOportunidades = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            StringBuilder sql = new StringBuilder("SELECT o.id, o.idOrganizacion, o.titulo, o.descripcion,o.detalles, o.tipo, "
                    + "o.duracion, o.jornada, o.modalidad, o.pago, o.ubicacion, o.provincia, "
                    + "org.nombre AS nombreOrganizacion "
                    + "FROM Oportunidades o, Organizacion org WHERE o.idOrganizacion = org.id");

            List<String> condiciones = new ArrayList<>();

            if (filtroTipo != null && !filtroTipo.isEmpty()) {
                condiciones.add("o.tipo = ?");
            }

            if (filtroProvincia != null && !filtroProvincia.isEmpty()) {
                condiciones.add("o.provincia = ?");
            }

            if (filtroModalidad != null && !filtroModalidad.isEmpty()) {
                condiciones.add("o.modalidad = ?");
            }

            if (filtroJornada != null && !filtroJornada.isEmpty()) {
                condiciones.add("o.jornada = ?");
            }

            if (filtroPago != null && !filtroPago.isEmpty()) {
                condiciones.add("o.pago = ?");
            }

            if (filtroDuracion != null && !filtroDuracion.isEmpty()) {
                condiciones.add("o.duracion = ?");
            }

            if (!condiciones.isEmpty()) {
                sql.append(" AND ").append(String.join(" AND ", condiciones));
            }

            pstmt = super.getConexion().prepareStatement(sql.toString());
            
            int index = 1;

            if (filtroTipo != null && !filtroTipo.isEmpty()) {
                pstmt.setString(index++, filtroTipo);
            }

            if (filtroProvincia != null && !filtroProvincia.isEmpty()) {
                pstmt.setString(index++, filtroProvincia);
            }

            if (filtroModalidad != null && !filtroModalidad.isEmpty()) {
                pstmt.setString(index++, filtroModalidad);
            }

            if (filtroJornada != null && !filtroJornada.isEmpty()) {
                pstmt.setString(index++, filtroJornada);
            }

            if (filtroPago != null && !filtroPago.isEmpty()) {
                pstmt.setString(index++, filtroPago);
            }

            if (filtroDuracion != null && !filtroDuracion.isEmpty()) {
                pstmt.setString(index++, filtroDuracion);
            }

            if (filtro != null && !filtro.isEmpty()) {
                sql.append(" AND (o.titulo LIKE ? OR org.nombre LIKE ?)");
            }

            if (filtro != null && !filtro.isEmpty()) {
                String filtro1 = "%" + filtro + "%";
                pstmt.setString(1, filtro1);
                pstmt.setString(2, filtro1);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Oportunidades oportunidades = new Oportunidades();
                Organizacion organizacion = new Organizacion();

                oportunidades.setId(rs.getInt("id"));
                organizacion.setId(rs.getInt("idOrganizacion"));
                organizacion.setNombre(rs.getString("nombreOrganizacion"));

                oportunidades.setIdOrganizacion(organizacion);
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
   public void insertarOportunidad(Oportunidades oportunidades, String correo) throws ClassNotFoundException {
        PreparedStatement pstmt = null;

        ServicioOrganizacion servicioOrganizacion = new ServicioOrganizacion();
        Organizacion organizacion = servicioOrganizacion.obtenerIdOrganizacion(correo);
        oportunidades.setIdOrganizacion(organizacion);

        try {
            super.conectarBD();
            String sql = "INSERT INTO oportunidades (idOrganizacion, titulo, descripcion, detalles, tipo, duracion, jornada, modalidad, pago, ubicacion, provincia) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, oportunidades.getIdOrganizacion().getId());
            pstmt.setString(2, oportunidades.getTitulo());
            pstmt.setString(3, oportunidades.getDescripcion());
            pstmt.setString(4, oportunidades.getDetalles());
            pstmt.setString(5, oportunidades.getTipo());
            pstmt.setString(6, oportunidades.getDuracion());
            pstmt.setString(7, oportunidades.getJornada());
            pstmt.setString(8, oportunidades.getModalidad());
            pstmt.setString(9, oportunidades.getPago());
            pstmt.setString(10, oportunidades.getUbicacion());
            pstmt.setString(11, oportunidades.getProvincia());
            int cantidad = pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        } finally {
            cerrarPreparedStatement(pstmt);
            cerrarConexion();

        }

    }

    public Oportunidades obtenerIdOportunidad(int id) throws ClassNotFoundException {

        Oportunidades oportunidades = null;
        Postulaciones postulacion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            super.conectarBD();
            String sql = "SELECT * FROM oportunidades WHERE id= ?";
            pstmt = super.getConexion().prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                postulacion = new Postulaciones();
                oportunidades = new Oportunidades();
                oportunidades.setId(rs.getInt("id"));
                postulacion.setIdOportunidades(oportunidades);

            }
        } catch (SQLException e) {
        } finally {

            super.cerrarPreparedStatement(pstmt);
            super.cerrarResultSet(rs);
            super.cerrarConexion();

        }
        return oportunidades;
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
