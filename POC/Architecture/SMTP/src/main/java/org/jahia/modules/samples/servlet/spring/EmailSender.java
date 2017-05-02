package org.jahia.modules.samples.servlet.spring;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender extends HttpServlet {

    private HttpService httpService;

    public EmailSender() {
    }

    public void postConstruct() {
    }

    public void preDestroy() {
    }

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }
	
	private Boolean sendEmail(String account) {

        final String username = "xumak.poc@gmail.com";
        final String password = "p4ssw0rd!!!";
		


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("xumak.poc@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(account));
            message.setSubject("Saludos");
            message.setText("Querido amigo,"
                + "\n\n Muchos saludos");

            Transport.send(message);

			return true;


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
    }		
		

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		final String account=req.getQueryString().split("=")[1];
		
		if (sendEmail(account)) {
			
			writer.println("Email sent to:"+ account);
		
		}
			
			
    }
}
