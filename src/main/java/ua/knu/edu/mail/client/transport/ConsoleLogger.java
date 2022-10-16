package ua.knu.edu.mail.client.transport;

public class ConsoleLogger {
    public static void log(String dateTime, String message) {
        System.out.println(dateTime + " - " + message);
    }
}
