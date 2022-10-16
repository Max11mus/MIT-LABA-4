package ua.knu.edu.mail.client.transport;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface MailTransportInterface {
    public void sendHelloEmail(String login, String password,
                               String message, String recipients)
            throws MessagingException;
    public Message[] getAllMessages(String login, String password)
            throws MessagingException;

}
