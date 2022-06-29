package server.model;

import java.util.GregorianCalendar;
import server.exceptions.NotificationException;

/**
 * Create the abstrac methods for the User Builder
 *
 * @version 16/6/22
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 *
 */
public interface UserAbstractbuilder {

    public void buiilUser() throws NotificationException;

    public void buildId(String id) throws NotificationException;

    public void buildName(String name) throws NotificationException;

    public void buildEmail(String email) throws NotificationException;

    public void buildPassword(String password) throws NotificationException;

    public void buildPhone(int phone) throws NotificationException;

    public void buildBorndDate(GregorianCalendar bornDate) throws NotificationException;

    public User getUser() throws NotificationException;
}
