/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.service;

import com.ulatina.data.DatosCorreoElectronico;
import com.ulatina.data.Usuario;
import com.ulatina.data.DatosCorreoElectronico;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author jaime
 */
public class ServicioEmail extends Servicio {

    private static String correoElectronico = "jaimefungdel@gmail.com";
    private static String contrasenna = "wvyl jbvm ktic vawm";
    private Properties propiedades;
    private Session session;
    private MimeMessage correo;

    public Usuario validarCorreo(String correo) throws ClassNotFoundException {
        Usuario usuario = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            super.conectarBD();
            String sql = "SELECT id, nombre, correo, clave FROM usuario WHERE correo = ?";
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
            cerrarPreparedStatement(pstmt);
            cerrarResultSet(rs);
            cerrarConexion();
        }
        return usuario;
    }

    private DatosCorreoElectronico objDatos;

    public void crearCorreo(String correoDestinario, String asunto, String contenido) {
        
        this.objDatos = new DatosCorreoElectronico();
        objDatos.setCorreoDestinario(correoDestinario);
        objDatos.setAsunto(asunto);
        objDatos.setContenido(contenido);

        propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.ssl.protocols", "TLSv1.2");

        session = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoElectronico, contrasenna);
            }
        });

        try {
            correo = new MimeMessage(session);
            correo.setFrom(new InternetAddress(correoElectronico));
            correo.setRecipient(Message.RecipientType.TO, new InternetAddress(objDatos.getCorreoDestinario()));
            correo.setSubject(asunto);
            correo.setText(contenido, "ISO-8859-1", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
  

    public void enviarCorreo() {
        
        try {

            MimeMultipart objMultiParte = new MimeMultipart();
            BodyPart texto = new MimeBodyPart();
            texto.setText(objDatos.getContenido());
            objMultiParte.addBodyPart(texto);

            correo.setContent(objMultiParte);
            Transport.send(correo);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
