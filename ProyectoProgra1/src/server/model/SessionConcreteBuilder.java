package server.model;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import server.exceptions.NotificationException;

/**
 * It is responsible for validating all the session data
 *
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
        } else {
            String concretId = session.getPlatform();
            switch (concretId) {
                case "Zoom":
                    concretId = "ZM";
                    break;
                case "Skype":
                    concretId = "SKP";
                    break;
                case "Google Meet":
                    concretId = "GMT";
                    break;
                case "Teams":
                    concretId = "TMS";
                    break;
            }
            session.setSesionId(concretId + "-" + id);
        }
    }

    @Override
    public void buildCategory(String category) throws NotificationException {
        if (category == null || category.equals("")) {
            throw new NotificationException("La categoria no debe estar vacia");
        }
        if (category.length() > 25) {
            throw new NotificationException("La categoria no debe exceder los 25 caracteres");
        }
        final String pattern = "[a-zA-Z0-9\\s]{1,}";
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(category);
        if (mat.matches()) {
            session.setCategory(category);
        } else {
            throw new NotificationException("La categoria no debe estar vacia o tener caracteres especiales");
        }

    }

    @Override
    public void buildTopic(String topic) throws NotificationException {
        if (topic == null || topic.equals("")) {
            throw new NotificationException("El tema no debe estar vacio");
        }
        if (topic.length() > 100) {
            throw new NotificationException("El tema no debe exceder los 100 caracteres");
        }

        final String pattern = "[a-zA-Z0-9\\s]{1,}";
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(topic);
        if (mat.matches()) {
            session.setTopic(topic);
        } else {
            throw new NotificationException("El tema no debe estar vacio o tener caracteres especiales");
        }

    }

    @Override
    public void buildExpositor(String expositor) throws NotificationException {
        if (expositor == null || expositor.equals("")) {
            throw new NotificationException("El nombre del expositor no debe estar vacio");
        }
        if (expositor.length() > 100) {
            throw new NotificationException("El nombre del expositor  no debe"
                    + " exceder los 100 caracteres");
        }
        final String pattern = "[a-zA-Z\\s]{1,}";
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(expositor);
        if (mat.matches()) {
            session.setExpositor(expositor);
        } else {
            throw new NotificationException("El nombre del expositor no debe tener caracteres especiales");
        }

    }

    @Override
    public void buildDetail(String detail) throws NotificationException {
        if (detail == null && detail.isEmpty()) {
            throw new NotificationException("El detalle no debe estar vacio");
        }
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
        if (platform == null || platform.trim().isEmpty()) {
            throw new NotificationException("La plataforma no debe estar vacia");
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
    public void builDuration(int duration) throws NotificationException {
        if (duration < 30) {
            throw new NotificationException("La duracion debe ser mayor a 30 minutos");
        }
        session.setDuration(duration);
    }

    @Override
    public void buildCapacity(int capacity) throws NotificationException {
        final byte MIN_CAPACITY = 5;
        final byte MAX_CAPACITY = 30;
        if (capacity >= MIN_CAPACITY && capacity <= MAX_CAPACITY) {
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
