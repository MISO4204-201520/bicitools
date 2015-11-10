package com.bicitools.enviarcorreo;



import java.io.FileNotFoundException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;

public class EnviaCorreo {
	public  void enviarCorreo(String to, String clave) {
		String respuesta = "OK";
		Properties props = new Properties();
		try {
			
			props.put("mail.smtp.user", "pruebasmaestria@gmail.com");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			SecurityManager security = System.getSecurityManager();

			Authenticator auth = new autentificadorSMTP();
			Session session = Session.getInstance(props, auth);

			MimeMessage msg = new MimeMessage(session);
			msg.setText("Su clave de acceso es : ".concat(clave));
			msg.setSubject("Recuperar clave de usuario Bicitools");
			msg.setFrom(new InternetAddress("pruebasmaestria@gmail.com"));

			msg.addRecipients(Message.RecipientType.TO, to);
			Transport.send(msg);
                        System.out.println("enviocorreo");
		} catch ( MessagingException mex) {
			System.out.println("noenviocorreo_"+mex.getMessage());
			mex.printStackTrace();
		}
		

	}
	
	 private class autentificadorSMTP extends javax.mail.Authenticator {
	        
	        public PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication("pruebasmaestria@gmail.com", "miso2015");
	        }
	    }

}
