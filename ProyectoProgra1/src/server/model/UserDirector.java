package server.model;

import java.util.GregorianCalendar;
import server.exceptions.NotificationException;

/**
 * Receive the data of the user and a builder, to validate the entered data
 *
 * @version 18/06/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class UserDirector {

    public User buildUser(UserAbstractbuilder builder, String id, String name,
            String email, String password, int phone, GregorianCalendar bornDate) throws NotificationException {

        builder.buiilUser();
        builder.buildId(id);
        builder.buildName(name);
        builder.buildEmail(email);
        builder.buildPassword(password);
        builder.buildPhone(phone);
        builder.buildBorndDate(bornDate);
        return builder.getUser();

    }
}
