package wordup.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Email util class to send email reports to user easily from the AttemptServlet
 * class when user requests an email report
 * 
 * @author abalaji 
 *
 */
public class MailUtilGmail {

	/**
	 * Sends the email given the to and from address, with the given subject and body (via gmail!)
	 * @param to recipient email address 
	 * @param from sender email address 
	 * @param subject subject of the email 
	 * @param body body of the email 
	 * @param bodyIsHTML true if email is encapsulated in html tags otherwise false (plain-text) 
	 * @throws MessagingException if an error occurs 
	 */
	public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
			throws MessagingException {

		// 1 - get a mail session
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", "smtp.gmail.com");
		props.put("mail.smtps.port",  465);
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtps.quitwait", "false");
		//props.put("mail.smtp.ssl.enable", "true");
		//props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(props);
		
		session.setDebug(true);

		// 2 - create a message
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@wordup.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress("anubala16@gmail.com"));
            message.setSubject(subject);
			if (bodyIsHTML) {
				message.setContent(body, "text/html");
			} else {
				message.setText(body);
			}

			// 3 - address the message
			/**
			 * Address fromAddress = new InternetAddress(from); Address
			 * toAddress = new InternetAddress(to);
			 * message.setFrom(fromAddress);
			 * message.setRecipient(Message.RecipientType.TO, toAddress);
			 */
			// 4 - send the message
			//Transport.send(message);
			Transport transport = session.getTransport();
			transport.connect("abalaji@uncc.edu", "pwd");
			
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}

	}
}

/**
 * 
 * public static void send(String from,String password,String to,String
 * sub,String msg){ //Get properties object Properties props = new Properties();
 * props.put("mail.smtp.host", "smtp.gmail.com");
 * props.put("mail.smtp.socketFactory.port", "465");
 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.port", "465");
 * //get Session Session session = Session.getDefaultInstance(props, new
 * javax.mail.Authenticator() { protected PasswordAuthentication
 * getPasswordAuthentication() { return new
 * PasswordAuthentication(from,password); } }); //compose message try {
 * MimeMessage message = new MimeMessage(session);
 * message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
 * message.setSubject(sub); message.setText(msg); //send message
 * Transport.send(message); //system.out.println("message sent successfully"); }
 * catch (MessagingException e) {throw new RuntimeException(e);}
 * 
 */