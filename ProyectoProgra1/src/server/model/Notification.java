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
    
    private static final String FIVE_MINUTES_LEFT = "La sesión iniciará en menos de 5 minutos";
    private static final String DENIED_SESSION_REQUEST = "Su solicitud de ingreso a la reunión fue rechazada";
    private static final String ACCEPTED_SESSION_REQUEST = "Su solicitud de ingreso a la reunión fue aceptada";
    
    private String message;
    private String userId;
    
    public Notification(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
    
    public Notification(String userId, byte type) throws NotificationException {
        this.userId = userId;
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
            default:
                throw new NotificationException("Se envió un valor no válido");
        }
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }
    
    

}
