package ua.knu.edu.mail.client.transport;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class GoogleMailTransport implements MailTransportInterface {

    @Override
    public void sendHelloEmail(String login, String password, String message, String recipients)
            throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login, password);
                    }
                });

        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(login));
        mimeMessage.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipients)
        );

        mimeMessage.setSubject("Hello World !!!");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(message, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        mimeMessage.setContent(multipart);

        Transport.send(mimeMessage);
    }

    @Override
    public Message[] getAllMessages(String login, String password)
            throws MessagingException {
        String imapHost = "imap.gmail.com";

        Properties props = new Properties();
        props.put("mail.store.protocol", "imap");
        props.put("mail.imap.host", imapHost);
        props.put("mail.imap.port", "993");
        props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.imap.socketFactory.fallback","false");
        props.put("mail.imap.socketFactory.port", "993");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login, password);
                    }
                });

        Store store = null;
        Folder inbox = null;

            store = session.getStore();
            store.connect(imapHost, login, password);

            inbox = store.getDefaultFolder().getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            return inbox.getMessages();

    }

}
