package userModel;

import java.util.GregorianCalendar;

/**
 * @version 18/06/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class UserDirector {

    public User buildUser(UserAbstractbuilder builder, String id, String name,
            String email, String password, int phone, GregorianCalendar bornDate) {

        builder.buiilUser();
        builder.buildId(id);
        builder.buildName(name);
        builder.buildEmail(email);
        builder.buildPhone(phone);
        builder.buildBorndDate(bornDate);
        return builder.getUser();

    }
}
