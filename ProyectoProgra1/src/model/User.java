package model;

import java.util.GregorianCalendar;

/**
 * @version 16/6/22
 * @author Jostin Castro, Fernando Flores, Joshua Mora
 */
public class User {

    private String userID, name, email, password;
    private int phoneNumber;
    private GregorianCalendar bornDate;

    public User() {
    }

    public User(String userID, String name, String email, String password, int phoneNumber, GregorianCalendar bornDate) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.bornDate = bornDate;
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

    public void setBornDate(GregorianCalendar bornDate) {
        this.bornDate = bornDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
