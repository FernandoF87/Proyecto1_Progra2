package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import server.exceptions.NotificationException;

/**
 *
 * @author Fernando Flores Moya
 */
public class Server {

    private final int PORT = 8000;
    private final int TIME_OUT = 2000;
    private final int NOTIFICATION_SECONDS = 300;

    private ServerSocket socket;
    private Socket connection;
    private static Data data = new Data();
    private ArrayList<ConnectionThread> connections = new ArrayList<>();
    private HashMap<String, Session> sessions = data.getSessions();
    private boolean execute = true;

    // Quitar main cuando se implemente SessionSystem
//    public static void main(String[] args) {
//        new Server().runServer();
//       
////        AdminView adminView = new AdminView(new javax.swing.JFrame(),true,data,);
////        adminView.setVisible(true);
//    }
    public void runServer() {
        // Aqui se leen los archivos primeros y se cargan los datos
        for (String key : data.getUsers().keySet()) {
        }
        try {
            socket = new ServerSocket(PORT);
            socket.setSoTimeout(TIME_OUT);
            // Listening channel
            while (execute) {
                try {
                    connection = socket.accept();
                    System.out.println(connection);
                    ConnectionThread conThread = new ConnectionThread(connection, data);
                    connections.add(conThread);
                    System.out.println("Conexión aceptada");
                    conThread.start();
                } catch (SocketTimeoutException ex) {
                    checkSessions();
                }
            }
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void checkSessions() {
        for (String key : sessions.keySet()) {
            Session session = sessions.get(key);
            GregorianCalendar currentDate = new GregorianCalendar();
            LocalDateTime currentTime = LocalDateTime.ofInstant(currentDate.toInstant(), currentDate.getTimeZone().toZoneId());
            LocalDateTime sessionTime = LocalDateTime.ofInstant(session.getDate().toInstant(), session.getDate().getTimeZone().toZoneId());
            Duration duration = Duration.between(currentTime, sessionTime);
            if (duration.getSeconds() <= NOTIFICATION_SECONDS && !session.isNotifSent()) { // Determines if notifcation has been sent and time is <= 5 minutes
                session.setNotifSent(true);
                ArrayList<String> usersNotified = new ArrayList<>();
                ArrayList<Notification> tempNotifications = new ArrayList<>();
                for (int i = 0; i < connections.size(); i++) {
                    ConnectionThread temp = connections.get(i);
                    try {
                        if (temp.getConnectionUser() != null && session.isParticipant(temp.getConnectionUser().getEmail())) {
                            temp.notifyUser(new Notification(temp.getConnectionUser().getEmail(), Notification.FIVE_MINUTES, true));
                            usersNotified.add(temp.getConnectionUser().getEmail());
                        } else {
                            tempNotifications.add(new Notification(Notification.FIVE_MINUTES, false));
                        }
                    } catch (NotificationException ex) {
                        ex.printStackTrace();
                    } catch (ThreadDeath ex) {
                    }
                }
                for (int i = 0, count = 0; i < session.getParticipantList().size() && count < tempNotifications.size(); i++) {
                    if (!usersNotified.contains(session.getParticipantList().get(i))) {
                        tempNotifications.get(count).setUserId(session.getParticipantList().get(i));
                        data.addNotification(tempNotifications.get(count));
                        count++;
                    }
                }
            }
        }
    }

    public void sendNotification(String msg, Session session) {

        switch (msg) {
            case "Modificada":
                sendNotification(Notification.MODIFIED_SESSION, session);
                break;
            case "Borrada":
                sendNotification(Notification.DELETED_SESSION, session);
                break;
            case "Aceptado":
                sendNotification(Notification.ACCEPTED_REQUEST, session);
                break;
            case "Denegado":
                sendNotification(Notification.DENIED_REQUEST, session);
                break;
        }

    }

    private void sendNotification(byte type, Session session) {
        ArrayList<String> usersNotified = new ArrayList<>();
        ArrayList<Notification> tempNotifications = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            ConnectionThread connectionTemp = connections.get(i);
            try {
                if (connectionTemp.getConnectionUser() != null && session.isParticipant(connectionTemp.getConnectionUser().getEmail())) {
                    connectionTemp.notifyUser(new Notification(connectionTemp.getConnectionUser().getEmail(), type, true));
                    usersNotified.add(connectionTemp.getConnectionUser().getEmail());
                } else {
                    tempNotifications.add(new Notification(type, false));
                }
            } catch (NotificationException ex) {
                ex.printStackTrace();
            } catch (ThreadDeath ex) {
            }
        }
        for (int i = 0, count = 0; i < session.getParticipantList().size() && count < tempNotifications.size(); i++) {
            if (!usersNotified.contains(session.getParticipantList().get(i))) {
                tempNotifications.get(count).setUserId(session.getParticipantList().get(i));
                data.addNotification(tempNotifications.get(count));
                count++;
            }
        }

    }

    public void turnOff() {
        execute = false;
    }

    public static Data getData() {
        return data;
    }
}
