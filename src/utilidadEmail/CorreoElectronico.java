/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadEmail;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author usuario
 */
public class CorreoElectronico {

    public static void main(String[] args) {

        //CorreoElectronico mail = new CorreoElectronico("jorge07200653@gmail.com", "shd07200653?");
        /*mail.enviaStartTLS("jorge07200653@gmail.com", "olujan@soxtec.com.mx",
                "Correo de Prueba Servidor Soxtec Monterrety", 
                "Correo enviado desde PC ");*/
        
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        props.put("mail.smtp.host", "mail.soxtec.com.mx");
        //props.put("mail.smtp.host", "smtp.gmail.com");
        
        props.put("mail.smtp.port", "26");
        //props.put("mail.smtp.port", "587");
        CorreoElectronico mail = new CorreoElectronico( props,
                                                        "no-reply@soxtec.com.mx", 
                                                        "abgomez18"
                                                    );
        
        mail.enviaStartTLS("abraham.gomez@soxtec.com.mx",
                "Correo de Prueba Servidor Soxtec Monterrety",
                "Prueba de No Reply ");

        
        
    }

    private String usuario;
    private String pass;
    private Properties props;

    public CorreoElectronico(Properties props,String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
        this.props= props;
    }

    public void enviaStartTLS( String to, String subject, String text) {
        final String username = usuario;
        final String password = pass;

        String from=usuario;
        
       

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
