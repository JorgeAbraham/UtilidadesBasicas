/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidadEmail;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author usuario
 */
public class CorreoElectronico {

    
    

    private String usuario;
    private String pass;
    private Properties props;
    ArrayList<String> archivo;
    ArrayList<String> nombreArchivo;
    
    
    
    public CorreoElectronico(Properties props,String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
        this.props= props;
        
        
        archivo =new ArrayList();
        nombreArchivo=new ArrayList();
        
        
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

            System.out.println("Correo Enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    
    
    public void addAdjuntarArchivo(String Archivo, String NombreArchivo){
        archivo.add(Archivo);
        nombreArchivo.add(NombreArchivo);
    
    }
    
    
    
    public void enviaStartTLSArchivoAdjunto( String to, String subject, String text ) {
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
        
        
        // Se compone la parte del texto
       // BodyPart texto = new MimeBodyPart();
        try {
            
            MimeMultipart multiParte = new MimeMultipart();
            
            
            BodyPart adjunto[] = new MimeBodyPart[archivo.size()];
            
            
            for (int i=0;i<archivo.size();i++){
                adjunto[i] = new MimeBodyPart();
                adjunto[i].setDataHandler(
                                        new DataHandler(
                                                            //new FileDataSource("c:/temp/documento.html")
                                                            new FileDataSource(archivo.get(i))
                                                        )
                                  );
                adjunto[i].setFileName(nombreArchivo.get(i));
                multiParte.addBodyPart(adjunto[i]);
            }
            
            
            BodyPart texto = new MimeBodyPart();
            //texto.setText(text);
            texto.setContent(text, "text/html" ) ; 
            multiParte.addBodyPart(texto);
            
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@soxtec.com.mx"));
            message.addRecipient(
                                    Message.RecipientType.TO,
                                    new InternetAddress(to)
                                );
            message.setSubject(subject);
            message.setContent(multiParte);
            Transport t = session.getTransport("smtp");
            t.connect("no-reply@soxtec.com.mx", "abgomez181");
            t.sendMessage(message, message.getAllRecipients());
            t.close();

            System.out.println("Correo Enviado");


        } catch (MessagingException ex) {
            Logger.getLogger(CorreoElectronico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
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
                                                        "abgomez181"
                                                    );
        
     /*   mail.enviaStartTLS("abraham.gomez@soxtec.com.mx",
                "Correo de Prueba Servidor Soxtec Monterrety",
                "Prueba de No Reply ");  */

     
        mail.addAdjuntarArchivo("c:/temp/documento.html", "UNO");
        mail.addAdjuntarArchivo("c:/temp/documento.html", "DOS");
        
        String Html="<!DOCTYPE html>\n" +
                    "<html lang=\"es\">\n" +
                    "<head>\n" +
                    "	<title>Estilos CSS Avanzados</title>\n" +
                    "	<!--Etiquetas metas importantes-->\n" +
                    "	<meta charset=\"utf-8\">\n" +
                    "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "	<!--Fuente Roboto obtenida desde Google Fonts-->\n" +
                    "	<link href=\"https://fonts.googleapis.com/css?family=Roboto:400,500,700\" rel=\"stylesheet\">\n" +
                    "	<style>\n" +
                    "		body{\n" +
                    "	font-family: 'Roboto', sans-serif;\n" +
                    "}\n" +
                    "\n" +
                    "/*\n" +
                    "	Para mayor información visita los siguientes enlaces:\n" +
                    "	https://goo.gl/rfRwjR\n" +
                    "	https://goo.gl/KpQrQV\n" +
                    "	https://goo.gl/PJceyf\n" +
                    "*/\n" +
                    "\n" +
                    ".info{\n" +
                    "	border: thin solid #585151;\n" +
                    "	border-radius: 10px;\n" +
                    "	padding: 1em;\n" +
                    "	margin-bottom: 2em;\n" +
                    "	width: 60%;\n" +
                    "}\n" +
                    "/*\n" +
                    "Estilo aplicado a todas las clases que\n" +
                    "contengan la palabra link, dentro de su nombre\n" +
                    "por ejemplo: link-web, link-oficial, ...\n" +
                    "*/\n" +
                    "a[class*=\"link\"]{\n" +
                    "	display: block;\n" +
                    "	font-size: 1.8em;\n" +
                    "	position: relative;\n" +
                    "}\n" +
                    "/*\n" +
                    "Estilo para todos los tag a que contengan en su nombre\n" +
                    "la cadena link, a excepción del ultimo elemento, por que \n" +
                    "se lo esta negando con el atributo :not()\n" +
                    "*/\n" +
                    "a[class*=\"link\"]:not(:last-child){\n" +
                    "	margin-top: 0.8em;\n" +
                    "	margin-bottom: 1.5em;\n" +
                    "}\n" +
                    ".link-info[title]::after{\n" +
                    "	font-size: 0.5em;\n" +
                    "	margin-left: 1em;\n" +
                    "}\n" +
                    "/*Estilos aplicados según la cadena que contenga su atributo title*/\n" +
                    ".link-info[title='Buscador Google']{\n" +
                    "	color: #DA3232;\n" +
                    "}\n" +
                    ".link-info[title='01luisrene - Luis Rene Mas Mas']{\n" +
                    "	color: #29942E;\n" +
                    "}\n" +
                    ".link-info[title='Tutoriales de desarrollo']{\n" +
                    "	color: #04B176;\n" +
                    "}\n" +
                    ".link-info[title='Libros web de programación']{\n" +
                    "	color: #3F13A4;\n" +
                    "}\n" +
                    "/*Agrego el contenido del atributo title al final del tag a*/\n" +
                    ".link-info[title]::after{\n" +
                    "	content: attr(title);\n" +
                    "}\n" +
                    "\n" +
                    "/*Estilo cuando el tag esta activado*/\n" +
                    ".link:active{\n" +
                    "	color: #3E7DDB;\n" +
                    "	text-decoration: none;\n" +
                    "	border-bottom: thin solid #000;\n" +
                    "}\n" +
                    "/*Estilo cuando el puntero del mouse pasa sobre el tag*/\n" +
                    ".link:hover{\n" +
                    "	color: #171616;\n" +
                    "}\n" +
                    "/*Estilo cuando el foco esta sobre el tag*/\n" +
                    ".link:focus{\n" +
                    "	color: #45AB45;\n" +
                    "	outline: dotted;\n" +
                    "	text-decoration: none;\n" +
                    "}\n" +
                    "/*Estilo cuando el link aya sido visitado*/\n" +
                    ".link:visited{\n" +
                    "	color: #A025A4;\n" +
                    "	font-weight: 700;\n" +
                    "	text-transform: capitalize;\n" +
                    "}\n" +
                    "\n" +
                    ".sistemas-operativos{\n" +
                    "	list-style-type: upper-roman;\n" +
                    "}\n" +
                    ".sistemas-operativos li p{\n" +
                    "	margin: 0;\n" +
                    "}\n" +
                    "\n" +
                    "/*Estilo para el primer elemento li*/\n" +
                    ".sistemas-operativos li:first-child p{\n" +
                    "	color: #1496E9\n" +
                    "	font-size: 1.2em;\n" +
                    "	font-weight: 700;\n" +
                    "}\n" +
                    "/*Estilo para el elemento li posición 2*/\n" +
                    ".sistemas-operativos li:nth-child(2) p{\n" +
                    "	color: #1B1C1C;\n" +
                    "	font-size: 1.4em;\n" +
                    "}\n" +
                    "/*Estilo para el elemento li posición 3*/\n" +
                    ".sistemas-operativos li:nth-child(3) p{\n" +
                    "	color: #DB0817;\n" +
                    "	font-size: 1.4em;\n" +
                    "}\n" +
                    "/*Estilo para el ultimo elemento li*/\n" +
                    ".sistemas-operativos li:last-child p{\n" +
                    "	font-size: 1.2em;\n" +
                    "	color: #F73B08;\n" +
                    "	letter-spacing: 3px;\n" +
                    "	text-transform: uppercase;\n" +
                    "}\n" +
                    "/*Estilo para todos los elementos li a excepción del ultimo*/\n" +
                    ".sistemas-operativos li:not(:last-child){\n" +
                    "	margin-bottom: 10px;\n" +
                    "}\n" +
                    "\n" +
                    ".pago{\n" +
                    "	font-size: 1.3em;\n" +
                    "}\n" +
                    ".monto-pago{\n" +
                    "	font-size: 1.5em;\n" +
                    "	font-weight: 700;\n" +
                    "}\n" +
                    "\n" +
                    "/*Estilo que muestra el atributo antes del tag*/\n" +
                    ".monto-pago::before{\n" +
                    "	content: attr(data-left);\n" +
                    "}\n" +
                    "/*Estilo que muestra el atributo despues del tag*/\n" +
                    ".monto-pago::after{\n" +
                    "	content: attr(data-right);\n" +
                    "}\n" +
                    "\n" +
                    ".poesia{\n" +
                    "	font-size: 2em;\n" +
                    "	margin-left: 40px;\n" +
                    "	margin-right: 40px;\n" +
                    "}\n" +
                    "\n" +
                    "/*Estilo para la primera letra del texto*/\n" +
                    ".poesia::first-letter{\n" +
                    "	font-size: 2.3em;\n" +
                    "}\n" +
                    "\n" +
                    ".texto-grande{\n" +
                    "	font-size: 1.8em;\n" +
                    "}\n" +
                    "/*Estilo para la primera linea de texto*/\n" +
                    ".texto-grande::first-line{\n" +
                    "	background-color: yellow;\n" +
                    "	letter-spacing: 8px;\n" +
                    "	text-decoration: underline;\n" +
                    "	font-weight: 700;\n" +
                    "}\n" +
                    "\n" +
                    "/*Estilo a aplicarse cuando se selecciona una porción de texto con el mouse*/\n" +
                    "::selection{\n" +
                    "	background: #D61212;\n" +
                    "	color: #fff;\n" +
                    "}\n" +
                    "\n" +
                    "/*============Combinadores y selectores múltiples + flexbox============*/\n" +
                    "\n" +
                    "/*\n" +
                    "FlexBox: si deseas mas información y ver el funcionamiento\n" +
                    " en tiempo real visita http://flexbox.help/\n" +
                    "*/\n" +
                    ".lista{\n" +
                    "	display: flex;\n" +
                    "	flex-direction: row;\n" +
                    "	flex-wrap: wrap;\n" +
                    "	justify-content: space-between;\n" +
                    "	align-items: stretch;\n" +
                    "	align-content: stretch;\n" +
                    "}\n" +
                    "/*\n" +
                    "Puedes aplicar un mismo estilo a varios \n" +
                    "elementos separándolos por una coma\n" +
                    "*/\n" +
                    ".lista .item, .lista .sub-item{\n" +
                    "	box-sizing: border-box;\n" +
                    "}\n" +
                    "/*Estilo para el primer elemento*/\n" +
                    ".lista .item:first-child{\n" +
                    "	background: #ABD6FB;\n" +
                    "	margin-bottom: 2em;\n" +
                    "	width: 100%;\n" +
                    "}\n" +
                    "/*\n" +
                    "Estilo para hijo de tag div que se \n" +
                    "encuentre dentro de la clase padre .lista\n" +
                    "*/\n" +
                    ".lista div{\n" +
                    "	padding: 1em;\n" +
                    "}\n" +
                    "\n" +
                    "/*\n" +
                    "Estilo para uno de los siguientes \n" +
                    "hermanos que coincida con el tag div\n" +
                    "*/\n" +
                    ".lista div  ~  div{\n" +
                    "	margin-bottom: 1em;\n" +
                    "	width: 48%;\n" +
                    "}\n" +
                    "\n" +
                    "/*\n" +
                    "Estilo para hijos directos de los tag div que\n" +
                    " sean descendientes del contenedor padre .lista\n" +
                    " */\n" +
                    ".lista > div{\n" +
                    "	border: thin solid #8E8E8E;\n" +
                    "}\n" +
                    "/*\n" +
                    "Estilo que se aplica a partir del segundo tag p \n" +
                    "que se encuentre en el contenedor padre:\n" +
                    "en este caso le aplicaran a todos los párrafos\n" +
                    "que se encuentren dentro de .lista div sin importar\n" +
                    " que sea un sub hijo.\n" +
                    "*/\n" +
                    ".lista div p + p{\n" +
                    "	display: inline-block;\n" +
                    "	position: relative;\n" +
                    "	padding-left: 20px;\n" +
                    "	padding-right: 20px;\n" +
                    "}\n" +
                    "/*Estilo before y after*/\n" +
                    ".lista div p + p::before, .lista div p + p::after{\n" +
                    "	font-size: 2em;\n" +
                    "	font-family: sans-serif;\n" +
                    "	content: '\"';\n" +
                    "	height: 15px;\n" +
                    "	width: 15px;\n" +
                    "	position: absolute;\n" +
                    "}\n" +
                    "\n" +
                    ".lista div p + p::before{\n" +
                    "	top: 0;\n" +
                    "	left: 0;\n" +
                    "}\n" +
                    ".lista div p + p::after{\n" +
                    "	right: 0;\n" +
                    "	bottom: 0;\n" +
                    "}\n" +
                    "/*Estilo que se aplica a las clases .sub-item*/\n" +
                    ".lista div .sub-item{\n" +
                    "	border-left: 4px solid #8E8E8E;\n" +
                    "	width: 100%;\n" +
                    "}\n" +
                    "\n" +
                    "/*Regla media query*/\n" +
                    "@media (max-width: 600px) { /*Se aplica siempre y cuando el ancho de la pantalla sea menor a 600px*/\n" +
                    "	.lista .item{\n" +
                    "		background: #D2D1D1;\n" +
                    "		width: 100%;\n" +
                    "	}\n" +
                    "}\n" +
                    "	</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "	<!--Selectores de Atributos-->\n" +

                    "	\n" +
                    "	<!--Ejemplo para first-letter-->\n" +
                    "	\n" +
                    "\n" +
                    "	<!--Ejemplo para Combinadores y selectores múltiples + flexbox-->\n" +
                    "	<div class=\"lista\">\n" +

                    "		<div class=\"item\">\n" +

                                    "<img src=\"https://www.soxtec.com.mx/logo/logoSox.png\" border=\"0\" width=\"197 \" height=\"60\">\n" +
                    "		<h1>Has received an email from Soxtec</h1>\n" +

                    	
                    "			<div class=\"sub-item\">\n" +
                    "				<h4>Report.</h4>\n" +
                    "				<p>The files are in the attached documents.</p>\n" +
                    "			</div>\n" +               
                    "		</div>\n" +

                    "	</div>\n" +
                    "</body>\n" +
                    "</html>";
        
        
        mail.enviaStartTLSArchivoAdjunto("abraham.gomez@soxtec.com.mx",
               "Test de Soxtec",
                Html);
        
    }
    
    
    
}
