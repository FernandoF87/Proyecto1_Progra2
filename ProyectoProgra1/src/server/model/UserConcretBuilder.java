package server.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.GregorianCalendar;
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
        if (id.length() != 9) {
            throw new NotificationException("La cedula no contiene la cantidad "
                    + "de numeros correcto");
        }

        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                throw new NotificationException("La cedula no puede contener"
                        + " letras");
            }
            if (id.charAt(i) == '-' || id.charAt(i) == ' ') {
                throw new NotificationException("La cedula no puede contener"
                        + " guiones o espacios");
            }
        }
        user.setUserID(id);

    }

    @Override
    public void buildName(String name) throws NotificationException {
        int valAscii = 0;
        if (name.length() > 100) {
            throw new NotificationException("El nombre no debe exceder los 100 caracteres");
        }
        /*compara el valor ascii de cada caracter, en un rango de valores que 
         *letras mayusculas y minusculas
         */
        for (int i = 0; i < name.length(); i++) {
            valAscii = (int) name.charAt(i);

            if (valAscii > 64 && valAscii < 91 || valAscii > 96 && valAscii < 123) {

            } else {
                throw new NotificationException("El nombre no debe contener"
                        + " caracteres especiales : " + name.charAt(i));
            }
        }
        user.setName(name);

    }

    @Override
    public void buildEmail(String email) throws NotificationException {
        for (int i = 0; i < email.length(); i++) {
            if (Character.isDigit(email.charAt(0))) {
                throw new NotificationException("El correo debe iniciar con una letra");
            }
            if (i == email.length() && email.charAt(i) != '@') {
                throw new NotificationException("El correo debe tener el simbolo @");
            } else {
                if (email.charAt(i) != '@') {
//                    if (email.charAt(email.length()) == '.') {
//                        throw new NotificationException("El correo no debe terminar en '.'");
//                    }
                    if (email.charAt(email.length() - 2) != '.' || email.charAt(email.length() - 4) != '.') {
                        throw new NotificationException("El correo no debe terminar en '.'");

                    }
                }
            }
        }
        user.setEmail(email);
    }

    @Override
    public void buildPassword(String password) throws NotificationException {
        int valAscii = 0;
        int contNum = 0;
        int contLetters = 0;
        if (password.length() <= 8) {
            throw new NotificationException("La contraseña debe tener minimo 8 caracteres");
        }

        /*compara el valor ascii de cada caracter, en un rango de valores que 
         *contemplan numeros, letras mayusculas y minusculas
         */
        for (int i = 0; i < password.length(); i++) {
            valAscii = (int) password.charAt(i);

            if (valAscii > 47 && valAscii < 58 || valAscii > 64
                    && valAscii < 91 || valAscii > 96 && valAscii < 123) {
                if (Character.isDigit(password.charAt(i))) {
                    contNum++;
                } else {

                    contLetters++;
                }

            } else {
                throw new NotificationException("La contraseña no debe contener"
                        + " caracteres especiales : " + password.charAt(i));
            }
        }
        if (contNum == 0) {
            throw new NotificationException("La contraseña debe contener al "
                    + "menos un numero");
        }
        if (contLetters == 0) {
            throw new NotificationException("La contraseña debe contener al "
                    + "menos una letra");
        } else {
        }
        user.setPassword(password);

    }

    @Override
    public void buildPhone(int phone) throws NotificationException {
        String num = (String.valueOf(phone));
        if (num.length() != 8) {
            throw new NotificationException("El largo del numero es incorrecto");
        }
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '-' || num.charAt(i) == ' ') {
                throw new NotificationException("La cedula no puede contener"
                        + " guiones o espacios");
            }
        }

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
