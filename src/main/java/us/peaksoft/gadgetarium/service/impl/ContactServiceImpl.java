package us.peaksoft.gadgetarium.service.impl;

import us.peaksoft.gadgetarium.dto.ContactRequest;
import us.peaksoft.gadgetarium.service.ContactService;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class ContactServiceImpl implements ContactService {
    @Override
        public void sendEmail(ContactRequest contact) {
            String  subject="This email from user of Gadgetarium";
            String body="Username:"+contact.getUsername()+" Name:"+contact.getName()+" Number:"+contact.getNumber()+" Email:"+contact.getEmail()+"\n"+contact.getMessage();

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.starttls.enable", "true");
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("gadgetarium44@gmail.com","icdwahoyxcfbtpjf");
                }
            };
            Session session = Session.getInstance(props,auth);

            try {

                Message message1 = new MimeMessage(session);
                message1.setFrom(new InternetAddress(contact.getEmail()));
                message1.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress("gadgetarium44@gmail.com"));
                message1.setSubject(subject);
                message1.setText(body);

                Transport.send(message1);
                System.out.println("Email sent successfully!");

            } catch (MessagingException e) {
                System.out.println("Failed to send email.");
                e.printStackTrace();
            }
        }

    }

