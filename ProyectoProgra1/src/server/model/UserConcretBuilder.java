package server.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import server.exceptions.NotificationException;

/**
 * @version 18/06/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class UserConcretBuilder implements UserAbstractbuilder {

    private User user;

    @Override
    public void buiilUser() {
        user = new User();
    }

    @Override
    public void buildId(String id) throws NotificationException {
        if (id == null || id.trim().equals("")) {
            throw new NotificationException("La cedula no puede estar vacia");
        }
        if (id.length() != 10) {
            throw new NotificationException("La cedula no contiene la cantidad "
                    + "de numeros correcto");
        }
        final String patter = "[0-9]{8}";
        Pattern pat = Pattern.compile(patter);
        Matcher mat = pat.matcher(id);
        if (mat.matches()) {
            user.setUserID(id);
        } else {
            throw new NotificationException("La cedula no es valida");
        }

    }

    @Override
    public void buildName(String name) throws NotificationException {
        if (name == null || name.equals("")) {
            throw new NotificationException("El nombre no debe estar vacio");
        }

        if (name.length() > 100) {
            throw new NotificationException("El nombre no debe exceder los 100 caracteres");
        }
        final String patter = "[a-zA-Z]{1,}";
        Pattern pat = Pattern.compile(patter);
        Matcher mat = pat.matcher(name);
        if (mat.matches()) {
            user.setName(name);
        } else {
            throw new NotificationException("El nombre no es valido");
        }

    }

    @Override
    public void buildEmail(String email) throws NotificationException {
        if (email == null || email.equals("")) {
            throw new NotificationException("El correo no debe estar vacio");
        }

        if (Character.isDigit(email.charAt(0))) {
            throw new NotificationException("El correo debe iniciar con una letra");
        }

        final String EMAIL_PATTERN = "[a-zA-Z0-9_.]{1,}[@]{1}[a-z1-9-.]+[.]{1}[a-z]{2,4}";
        Pattern pat = Pattern.compile(EMAIL_PATTERN);
        Matcher mat = pat.matcher(email);
        if (mat.matches()) {
            user.setEmail(email);
        } else {
            throw new NotificationException("El correo no es valido");
        }

    }

    @Override
    public void buildPassword(String password) throws NotificationException {
        if (password == null || password.equals("")) {
            throw new NotificationException("La contraseña no debe estar vacia");
        }

        if (password.length() < 8) {
            throw new NotificationException("La contraseña debe tener minimo 8 caracteres");
        }
        String pattern = "(?=.*[0-9])"
                + "(?=.*[a-zA-Z]).{8,}";
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(password);
        if (mat.matches()) {
            user.setPassword(password);
        } else {
            throw new NotificationException("La contraseña no es valida");
        }

    }

    @Override
    public void buildPhone(int phone) throws NotificationException {
        String num = (String.valueOf(phone));
        if (num.length() != 8) {
            throw new NotificationException("El largo del numero es incorrecto");
        }
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '-' || num.charAt(i) == ' ') {
                throw new NotificationException("El numero de telefono no puede contener"
                        + " guiones o espacios");
            }
        }
        user.setPhoneNumber(phone);

    }

    @Override
    public void buildBorndDate(GregorianCalendar bornDate) throws NotificationException {
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(bornDate.getTime());
        LocalDate localDate = LocalDate.parse(dateString);
        Period age = Period.between(localDate, LocalDate.now());
        if (age.getYears() < 18) {
            throw new NotificationException("Eres menor de edad");
        }
        user.setBornDate(bornDate);
    }

    @Override
    public User getUser() {
        return user;
    }

}
