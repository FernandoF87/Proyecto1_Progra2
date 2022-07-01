/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.model;

import java.io.Serializable;
import server.exceptions.NotificationException;

/**
 *
 * @version @author Jostin Castro
 */
public class Notification implements Serializable {

    /**
     * To create a notification with the five minutes left message.
     */
    public static final byte FIVE_MINUTES = 0;

    /**
     * To create a notification with the denied request message.
     */
    public static final byte DENIED_REQUEST = 1;

    /**
     * To create a notification with the accepted session request message.
     */
    public static final byte ACCEPTED_REQUEST = 2;

    /**
     * To create a notication with the deleted session message.
     */
    public static final byte DELETED_SESSION = 3;

    /**
     * To create a notification with the modified session message
     */
    public static final byte MODIFIED_SESSION = 4;

    private static final String FIVE_MINUTES_LEFT = "La sesión iniciará en menos de 5 minutos";
    private static final String DENIED_SESSION_REQUEST = "Su solicitud de ingreso a la reunión fue rechazada";
    private static final String ACCEPTED_SESSION_REQUEST = "Su solicitud de ingreso a la reunión fue aceptada";
    private static final String DELETED_SESSION_MESSAGE = "Una sesión de su lista de sesiones inscritas fue eliminada";
    private static final String MODIFIED_SESSION_MESSAGE = "Una session de su lista ha sido modificada";

    private byte type;
    private String message;
    private String userId;
    private String extraInfo;
    private boolean sended;

    /**
     * Notification constructor wich contruct a notification with a definied
     * message.
     *
     * @param type one of the public static variables of this class.
     * @param sended boolean wich defines if the notification was sended to the
     * user.
     * @throws NotificationException if the type not correspond to a public
     * static variable.
     */
    public Notification(byte type, boolean sended) throws NotificationException {
        switch (type) {
            case FIVE_MINUTES:
                message = FIVE_MINUTES_LEFT;
                break;
            case DENIED_REQUEST:
                message = DENIED_SESSION_REQUEST;
                break;
            case ACCEPTED_REQUEST:
                message = ACCEPTED_SESSION_REQUEST;
                break;
            case DELETED_SESSION:
                message = DELETED_SESSION_MESSAGE;
                break;
            case MODIFIED_SESSION:
                message = MODIFIED_SESSION_MESSAGE;
                break;
            default:
                throw new NotificationException("Se envió un valor no válido");
        }
        this.type = type;
        this.sended = sended;
    }

    /**
     * Notification constructor wich contruct a notification with a definied
     * message.
     *
     * @param userId the destinatary of this notification.
     * @param type one of the public static variables of this class.
     * @param sended boolean wich defines if the notification was sended to the
     * user.
     * @throws NotificationException if the type not correspond to a public
     * static variable.
     */
    public Notification(String userId, byte type, boolean sended) throws NotificationException {
        this(type, sended);
        this.userId = userId;
    }

    //Eliminar este constructor antes de enviar proyecto
    public Notification(String userId, String message, boolean sended) {
        this.userId = userId;
        this.message = message;
        this.sended = sended;
    }

    /**
     *
     * @param userId the destinatary of this notification.
     * @param type one of the public static variables of this class
     * @param sended boolean wich defines if the notification was sended to the
     * user.
     * @param extraInfo a extra information to the notification.
     * @throws NotificationException if the type not correspond to a public
     * static variable.
     */
    public Notification(String userId, byte type, boolean sended, String extraInfo) throws NotificationException {
        this(userId, type, sended);
        this.extraInfo = extraInfo;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isSended() {
        return sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }

    //Metodos nuevos
    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

}
