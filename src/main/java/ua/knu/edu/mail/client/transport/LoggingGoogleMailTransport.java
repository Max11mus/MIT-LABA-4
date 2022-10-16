package ua.knu.edu.mail.client.transport;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingGoogleMailTransport implements MailTransportInterface {
    private GoogleMailTransport googleMailTransport = new GoogleMailTransport();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    @Override
    public void sendHelloEmail(String login, String password, String message, String recipients)
            throws MessagingException {

        LocalDateTime startTime = LocalDateTime.now();
        ConsoleLogger.log(dtf.format(startTime), "Start - Calling GoogleMailTransport " +
                "- sendHelloEmail method ");

        googleMailTransport.sendHelloEmail(login,password, message, recipients);

        LocalDateTime endTime = LocalDateTime.now();
        ConsoleLogger.log(dtf.format(endTime), "End - Calling GoogleMailTransport " +
                "- sendHelloEmail method ");

        long elapsedMillis = Duration.between(startTime, endTime).toMillis();
        ConsoleLogger.log(dtf.format(endTime), "Elapsed - " + elapsedMillis +
                " milliseconds");
    }

    @Override
    public Message[] getAllMessages(String login, String password)
            throws MessagingException {

        LocalDateTime startTime = LocalDateTime.now();
        ConsoleLogger.log(dtf.format(startTime), "Start - Calling GoogleMailTransport " +
                "- getAllMessages method ");

        Message[] allMessages = googleMailTransport.getAllMessages(login, password);

        LocalDateTime endTime = LocalDateTime.now();
        ConsoleLogger.log(dtf.format(endTime), "End - Calling GoogleMailTransport " +
                "- getAllMessages method ");

        long elapsedMillis = Duration.between(startTime, endTime).toMillis();
        ConsoleLogger.log(dtf.format(endTime), "Elapsed - " + elapsedMillis +
                " milliseconds");
        return allMessages;
    }

}