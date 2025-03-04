/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Chuz
 */
public abstract class Servicio {

    protected Connection conexion = null;
    
    private String host = "localhost";
    private String puerto = "3306";
    private String sid = "BDProyectoJESJ";
    private String usuario = "root";
    private String clave = "adminadmin";


    public void conectar() throws ClassNotFoundException, SQLException {
        // Paso 1: Cargar el driver JDBC (MySQL en este caso)
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Paso 2: Establecer la conexión utilizando los parámetros definidos
        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + sid + "?serverTimezone=UTC";
        conexion = DriverManager.getConnection(url, usuario, clave);
    }


    public void cerrarPrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                if (!ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void cerrarResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

  
    public void cerrarconexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
                // Se asigna null para liberar la referencia
                conexion = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected Connection getConexion() {
        return conexion;
    }

  
    protected void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
