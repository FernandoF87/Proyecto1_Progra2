package server.model;

import server.controller.FilesLoader;
import java.util.ArrayList;
import java.util.HashMap;
import user.model.User;

/**
 *
 * @author Fernando Flores Moya
 */
public class Data {
    
    private HashMap<String, Session> sessions;
    private HashMap<String,User> users;
    private ArrayList<Notification> notifications;
    private boolean userAvailable = true;
    private boolean sessionAvailable = true;
    
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

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }
    
    public synchronized void addSession(Session session) {
        while (!sessionAvailable)
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        sessionAvailable = false;
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
        users.put(user.getUserID(), user);
    }
    
    public synchronized void resetUserAvailable() {
        userAvailable = true;
        notifyAll();
    }
    
    public synchronized void resetSessionAvailable() {
        sessionAvailable = true;
        notifyAll();
    }
    
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }
    
    public void setAll() {
        FilesLoader.updateSessions();
        FilesLoader.updateUsers();
        FilesLoader.updateNotifications();
    }
}
