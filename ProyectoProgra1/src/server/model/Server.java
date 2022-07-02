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
    private final int TIME_OUT = 5000;
    private final int NOTIFICATION_SECONDS = 300;

    private ServerSocket socket;
    private Socket connection;
    private static Data data = new Data();
    private ArrayList<ConnectionThread> connections = new ArrayList<>();
    private HashMap<String, Session> sessions = data.getSessions();
    private boolean execute = true;

    // Quitar main cuando se implemente SessionSystem
    //public static void main(String[] args) {
    //new Server().runServer();
//        AdminView adminView = new AdminView(new javax.swing.JFrame(),true,data,);
//        adminView.setVisible(true);
    //}
    public void runServer() {
        // Aqui se leen los archivos primeros y se cargan los datos
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
            for (ConnectionThread connection : connections) {

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
            if (duration.getSeconds() <= NOTIFICATION_SECONDS && session.getState() != Session.FINALIZED_STATE && !session.isNotifSent()) { // Determines if notifcation has been sent and time is <= 5 minutes
                session.setNotifSent(true);
                sendNotification(Notification.FIVE_MINUTES, session);
            }
        }
    }

    public void sendNotification(String msg, Session session) {
        if (msg != null) {
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
                default:
            }
        }
    }

    private void sendNotification(byte type, Session session) {
        ArrayList<String> usersNotified = new ArrayList<>();
        ArrayList<String> participants = session.getParticipantList();
        for (int i = 0; i < connections.size(); i++) {
            ConnectionThread connectionTemp = connections.get(i);
            try {
                if (connectionTemp.getConnectionUser() != null && session.isParticipant(connectionTemp.getConnectionUser().getEmail())) {
                    connectionTemp.notifyUser(new Notification(connectionTemp.getConnectionUser().getEmail(), type, true));
                    usersNotified.add(connectionTemp.getConnectionUser().getEmail());
                }
            } catch (NotificationException ex) {
                ex.printStackTrace();
            }
        }
        for (int i = 0; i < participants.size(); i++) {
            if (!usersNotified.contains(participants.get(i))) {
                try {
                    data.addNotification(new Notification(participants.get(i), type, false));
                } catch (NotificationException ex) {
                    ex.printStackTrace();
                }
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
