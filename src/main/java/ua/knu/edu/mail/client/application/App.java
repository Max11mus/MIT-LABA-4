package ua.knu.edu.mail.client.application;

import ua.knu.edu.mail.client.transport.GoogleMailTransport;
import ua.knu.edu.mail.client.transport.MailTransportInterface;

import javax.mail.Message;
import javax.mail.MessagingException;

public class App {
    public static void main(String[] args) throws MessagingException {
        MailTransportInterface transport = new GoogleMailTransport();

        String login = "mail.lab.ua.edu.knu@gmail.com";
        String password = "ndbflzcnebaeclvc";

        System.out.println("Send Hello email");
        transport.sendHelloEmail(login, password, "Greetings !!!", login);
        System.out.println("Done");
        System.out.println();

        System.out.println("Receive all INBOX emails");
        Message[] allMessages = transport.getAllMessages(login, password);
        System.out.println("There are " + allMessages.length + " messages in inbox");
        System.out.println("Done");
        System.out.println();
    }

}
