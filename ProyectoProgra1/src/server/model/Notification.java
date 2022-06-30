/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package server.model;

import server.exceptions.NotificationException;
import java.io.Serializable;

/**
 *
 * @version
 * @author Jostin Castro
 */
public class Notification implements Serializable {
    
    public static final byte FIVE_MINUTES = 0;
    public static final byte DENIED_REQUEST = 1;
    public static final byte ACCEPTED_REQUEST = 2;
    public static final byte DELETED_SESSION = 3;
    
    private static final String FIVE_MINUTES_LEFT = "La sesión iniciará en menos de 5 minutos";
    private static final String DENIED_SESSION_REQUEST = "Su solicitud de ingreso a la reunión fue rechazada";
    private static final String ACCEPTED_SESSION_REQUEST = "Su solicitud de ingreso a la reunión fue aceptada";
    private static final String DELETED_SESSION_MESSAGE = "Una sesión de su lista de sesiones inscritas fue eliminada";
    
    private byte type;
    private String message;
    private String userId;
    private String extraInfo;
    private boolean sended;
    
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
            default:
                throw new NotificationException("Se envió un valor no válido");
        }
        this.type = type;
        this.sended = sended;
    }
    
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
