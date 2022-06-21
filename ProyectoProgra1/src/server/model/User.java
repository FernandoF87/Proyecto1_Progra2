package server.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @version 16/6/22
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 *
 */
public class User implements Serializable {

    private String userID, name, email, password;
    private int phoneNumber;
    private GregorianCalendar bornDate;

    public User() {
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
