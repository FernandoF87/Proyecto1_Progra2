package server.model;

import server.exceptions.NotificationException;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @version 18/06/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class SessionConcreteBuilder implements SessionAbstractBuilder {

    private Session session;

    @Override
    public void buildSesion() {
        session = new Session();
    }

    @Override
    public void buildId(String id) throws NotificationException {
        if (id == null || id.trim().equals("")) {
            throw new NotificationException("El id no debe estar vacio");
        }
    }

    @Override
    public void buildCategory(String category) throws NotificationException {
        int valAscii = 0;
        if (category.length() > 25) {
            throw new NotificationException("La categoria no debe exceder los 25 caracteres");
        }
        /*compara el valor ascii de cada caracter, en un rango de valores que 
         *letras mayusculas y minusculas
         */
        for (int i = 0; i < category.length(); i++) {
            valAscii = (int) category.charAt(i);

            if (valAscii > 64 && valAscii < 91 || valAscii > 96 && valAscii < 123) {

            } else {
                throw new NotificationException("La categoria no debe contener"
                        + " caracteres especiales : " + category.charAt(i));
            }
        }
        session.setCategory(category);
    }

    @Override
    public void buildTopic(String topic) throws NotificationException {
        int valAscii = 0;
        if (topic.length() > 100) {
            throw new NotificationException("El tema no debe exceder los 100 caracteres");
        }
        /*compara el valor ascii de cada caracter, en un rango de valores que 
         *letras mayusculas y minusculas
         */
        for (int i = 0; i < topic.length(); i++) {
            valAscii = (int) topic.charAt(i);

            if (valAscii > 64 && valAscii < 91 || valAscii > 96 && valAscii < 123) {

            } else {
                throw new NotificationException("El tema no debe contener"
                        + " caracteres especiales : " + topic.charAt(i));
            }
        }
        session.setTopic(topic);
    }

    @Override
    public void buildExpositor(String expositor) throws NotificationException {
        int valAscii = 0;
        if (expositor.length() > 100) {
            throw new NotificationException("El nombre del expositor  no debe"
                    + " exceder los 100 caracteres");
        }
        /*compara el valor ascii de cada caracter, en un rango de valores que 
         *letras mayusculas y minusculas
         */
        for (int i = 0; i < expositor.length(); i++) {
            valAscii = (int) expositor.charAt(i);

            if (valAscii > 64 && valAscii < 91 || valAscii > 96 && valAscii < 123) {

            } else {
                throw new NotificationException("El nombre del expositor no debe"
                        + " contener caracteres especiales : " + expositor.charAt(i));
            }
        }
        session.setExpositor(expositor);
    }

    @Override
    public void buildDetail(String detail) throws NotificationException {
        if (detail.length() > 300) {
            throw new NotificationException("El detalle no debe exceder los 300 caracteres");
        }
        session.setDetail(detail);
    }

    @Override
    public void buildLink(String link) throws NotificationException {
        if (link == null || link.trim().equals("")) {
            throw new NotificationException("El link no debe estar vacio");
        }
        session.setLink(link);
    }

    @Override
    public void buildPlatform(String platform) throws NotificationException {
        if (platform == null || platform.trim().equals("")) {
            throw new NotificationException("El link no debe estar vacio");
        }
        session.setPlatform(platform);
    }

    @Override
    public void buildDate(GregorianCalendar date) throws NotificationException {
        GregorianCalendar localDate = new GregorianCalendar(Locale.ITALY);
        if (date.getTimeInMillis() < localDate.getTimeInMillis()) {
            throw new NotificationException("La fecha debe ser mayor a la fecha actual");
        }
        session.setDate(date);
    }

    @Override
    public void builTime(GregorianCalendar time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void builDuration(int duration) throws NotificationException {
        if (duration < 30) {
            throw new NotificationException("La duracion debe ser mayor a 30 minutos");
        }
        session.setDuration(duration);
    }

    @Override
    public void buildCapacity(int capacity) throws NotificationException {
        if (capacity > 5 && capacity < 30) {
            session.setCapacity(capacity);
        } else {
            throw new NotificationException("El cupo debe estar en 5 y 30");
        }
    }

    @Override
    public void buildOpen(boolean open) {
        session.setOpen(open);
    }

    @Override
    public Session getSession() throws NotificationException {
        if (session == null) {
            throw new NotificationException("La session no se ha creado");
        }
        return session;
    }

}
