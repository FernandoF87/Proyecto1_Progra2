package model;

import java.util.GregorianCalendar;

/**
 * Create the abstrac methods for the User Builder
 *
 * @version 16/6/22
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 *
 */
public interface UserAbstractbuilder {

    public void buiilUser();

    public void buildId(String id);

    public void buildName(String name);

    public void buildEmail(String email);

    public void buildPassword(String password);

    public void buildPhone(int phone);

    public void buildBorndDate(GregorianCalendar bornDate);

    public void getUser();
}
