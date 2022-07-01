package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import server.controller.FilesLoader;

/**
 *
 * @author Fernando Flores Moya
 */
public class Data {

    private HashMap<String, Session> sessions;
    private HashMap<String, User> users;
    private ArrayList<Notification> notifications;
    private boolean userAvailable = true;

    public Data() {
        sessions = FilesLoader.loadSessions();
        users = FilesLoader.loadUsers();
        notifications = FilesLoader.loadNotifications();
    }

    public HashMap<String, Session> getSessions() {
        return sessions;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public User searchUserEmail(String email) {
        return users.get(email);
    }
    
    public Session searchSessionId(String id) {
        return sessions.get(id);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public void addSession(Session session) {
        sessions.put(session.getSesionId(), session);
    }

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

    public synchronized void resetUserAvailable() {
        userAvailable = true;
        notifyAll();
    }

    public void setAll() {
        FilesLoader.updateSessions();
        FilesLoader.updateUsers();
        FilesLoader.updateNotifications();
    }

    //Eliminar sesion, se usa en el Adminview
    public Session deleteSession(String key) {
        return sessions.remove(key);
    }
}
