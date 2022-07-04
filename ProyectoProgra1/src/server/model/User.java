package server.model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Class that represents a user in the session manager system
 *
 * @version 16/6/22
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class User implements Serializable {

    private final byte MAX_NOTIFICATIONS = 5;

    private String userID, name, email, password;
    private int phoneNumber;
    private GregorianCalendar bornDate;
    private LinkedList<Notification> lastNotifications;

    public User() {
        lastNotifications = new LinkedList();
    }

    /**
     * Adds a notification to the list of user notifications
     *
     * @param notification a notification to be added
     */
    public void addNotification(Notification notification) {
        if (lastNotifications.size() >= MAX_NOTIFICATIONS) {
            lastNotifications.removeFirst();
            lastNotifications.add(notification);
            return;
        }
        lastNotifications.add(notification);
    }

    public GregorianCalendar getBornDate() {
        return bornDate;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public LinkedList<Notification> getNotifications() {
        return lastNotifications;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBornDate(GregorianCalendar bornDate) {
        this.bornDate = bornDate;
    }

}
