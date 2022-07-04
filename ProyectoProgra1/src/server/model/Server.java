package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import server.exceptions.NotificationException;

/**
 *
 * @author Fernando Flores Moya
 */
public class Server extends Thread {

    private final int PORT = 8000;
    private final int TIME_OUT = 5000;
    private final int NOTIFICATION_SECONDS = 300;

    private ServerSocket socket;
    private Socket connection;
    private static Data data = new Data();
    private ArrayList<ConnectionThread> connections = new ArrayList<>();
    private HashMap<String, Session> sessions = data.getSessions();
    private boolean execute = true;

    public void run() {
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
                    System.out.println("Revisando sesiones");
                }
            }
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
                System.out.println(session + " empieza en 5 minutos");
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        session.setState(Session.ACTIVE_STATE);
                        System.out.println("Se inicio la sesión");
                    }
                };
                new Timer().schedule(task, session.getDate().getTime());
                TimerTask task2 = new TimerTask() {
                    @Override
                    public void run() {
                        session.setState(Session.FINALIZED_STATE);
                        System.out.println("Se finalizó la sesión");
                    }
                };
                LocalDateTime finalizedTime = sessionTime.plusMinutes(session.getDuration());
                Date finalizedDate = Date.from(finalizedTime.atZone(ZoneId.systemDefault()).toInstant());
                new Timer().schedule(task2, finalizedDate);
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
                    System.out.println(new Notification(participants.get(i), type, false));
                } catch (NotificationException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void turnOff() {
        for (ConnectionThread connection : connections) {
            if (connection.isAlive()) {
                connection.endExecution();
            }
        }
        execute = false;
        System.out.println(data.getNotifications());
        System.out.println(data.getSessions());
        System.out.println(data.getUsers());
        data.setAll();
    }

    public static Data getData() {
        return data;
    }
}
