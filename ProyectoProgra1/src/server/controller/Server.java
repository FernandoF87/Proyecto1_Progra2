package server.controller;



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

import server.model.Session;
import server.model.Notification;

/**
 * Class that represents a session system and allows multiple users to connect
 * who can enroll in sessions to be notified
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

    @Override
    public void run() {
        try {
            socket = new ServerSocket(PORT);
            socket.setSoTimeout(TIME_OUT);
            // Listening channel
            while (execute) {
                try {
                    connection = socket.accept();
                    ConnectionThread conThread = new ConnectionThread(connection, data);
                    connections.add(conThread);
                    conThread.start();
                } catch (SocketTimeoutException ex) { // Checks sessions every timeout
                    checkSessions();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks if any session is about to start in 5 minutes and sends each
     * corresponding notification
     */
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
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        session.setState(Session.ACTIVE_STATE);
                    }
                };
                new Timer().schedule(task, session.getDate().getTime());
                TimerTask task2 = new TimerTask() {
                    @Override
                    public void run() {
                        session.setState(Session.FINALIZED_STATE);
                    }
                };
                LocalDateTime finalizedTime = sessionTime.plusMinutes(session.getDuration());
                Date finalizedDate = Date.from(finalizedTime.atZone(ZoneId.systemDefault()).toInstant());
                new Timer().schedule(task2, finalizedDate);
            }
        }
    }

    /**
     * Sends notifications to the participants of a session
     *
     * @param msg the message type to be sent
     * @param session the session to obtain participants from
     */
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

    /**
     * Sends a specific type of notification to the participants of a session
     *
     * @param type the message type to be sent
     * @param session the session to obtain participants from
     */
    private void sendNotification(byte type, Session session) {
        ArrayList<String> usersNotified = new ArrayList<>();
        ArrayList<String> participants = session.getParticipantList();
        ArrayList<String> waitingUsers = session.getWaitingParticipantsList();
        for (int i = 0; i < connections.size(); i++) {
            ConnectionThread connectionTemp = connections.get(i);
            try {
                if (connectionTemp.getConnectionUser() != null && session.isParticipant(connectionTemp.getConnectionUser().getEmail())) {
                    connectionTemp.notifyUser(new Notification(connectionTemp.getConnectionUser().getEmail(), type, true));
                    usersNotified.add(connectionTemp.getConnectionUser().getEmail());
                } else if (connectionTemp.getConnectionUser() != null && waitingUsers != null && session.isWaiting(connectionTemp.getConnectionUser().getEmail())) {
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
        if (waitingUsers != null) {
            for (int i = 0; i < waitingUsers.size(); i++) {
                if (!usersNotified.contains(waitingUsers.get(i))) {
                    try {
                        data.addNotification(new Notification(waitingUsers.get(i), type, false));
                    } catch (NotificationException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Turns off the session system and saves all the data
     */
    public void turnOff() {
        for (ConnectionThread connection : connections) {
            if (connection.isAlive()) {
                connection.endExecution();
            }
        }
        execute = false;
        data.setAll();
    }

    /**
     *
     * @return the object that contains all the data used by the server
     */
    public static Data getData() {
        return data;
    }
}
