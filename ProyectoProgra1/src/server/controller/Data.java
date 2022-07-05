package server.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import server.controller.FilesLoader;
import server.model.Notification;
import server.model.Session;
import server.model.User;

/**
 * Controller class that contains all the system data obtained from the files
 *
 * @author Fernando Flores Moya
 */
public class Data {

    private HashMap<String, Session> sessions;
    private HashMap<String, User> users;
    private ArrayList<Notification> notifications;
    private boolean userAvailable = true;
    private final int FIVE_MINUTES_SECONDS = 300;

    /**
     * Constuctor that instantiates the collections for sessions, users and
     * notifications from files and determines if a session has finalized based
     * on its time and duration
     */
    public Data() {
        sessions = FilesLoader.loadSessions();
        users = FilesLoader.loadUsers();
        notifications = FilesLoader.loadNotifications();
        for (String key : sessions.keySet()) {
            Session session = sessions.get(key);
            GregorianCalendar currentDate = new GregorianCalendar();
            LocalDateTime currentTime = LocalDateTime.ofInstant(currentDate.toInstant(), currentDate.getTimeZone().toZoneId());
            LocalDateTime sessionTime = LocalDateTime.ofInstant(session.getDate().toInstant(), session.getDate().getTimeZone().toZoneId());
            Duration duration = Duration.between(currentTime, sessionTime);
            if (duration.getSeconds() + FIVE_MINUTES_SECONDS < 0) {
                session.setState(Session.FINALIZED_STATE);
            }
        }
    }

    public HashMap<String, Session> getSessions() {
        return sessions;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    /**
     * Finds and returns a user from the user list based on their email
     *
     * @param email the email of the user to find
     * @return the user found
     */
    public User searchUserEmail(String email) {
        return users.get(email);
    }

    /**
     * Finds and returns a session from the session list based on its ID
     *
     * @param id the ID of the session to find
     * @return the session found
     */
    public Session searchSessionId(String id) {
        return sessions.get(id);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    /**
     * Adds a notification to the pending notification list
     *
     * @param notification the notification to be added
     */
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    /**
     * Adds a session to the session list
     *
     * @param session the session to be added
     */
    public void addSession(Session session) {
        sessions.put(session.getSesionId(), session);
    }

    /**
     * Adds a user to the user list and makes other threads wait in case one is
     * currently being added
     *
     * @param user the user to be added
     */
    public synchronized void addUser(User user) {
        while (!userAvailable)
            try {
            wait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        userAvailable = false;
        users.put(user.getEmail(), user);
    }

    /**
     * Notifies other threads that a user can now be added
     */
    public synchronized void resetUserAvailable() {
        userAvailable = true;
        notifyAll();
    }

    /**
     * Writes all of the data stored in collection to files
     */
    public void setAll() {
        FilesLoader.updateNotifications();
        FilesLoader.updateSessions();
        FilesLoader.updateUsers();
    }

    /**
     * Deletes and returns a user from the user list based on their email
     *
     * @param key the email of the user to delete
     * @return the deleted user
     */
    public Session deleteSession(String key) {
        return sessions.remove(key);
    }
}
