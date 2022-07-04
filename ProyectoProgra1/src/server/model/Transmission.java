package server.model;

import java.io.Serializable;
import java.util.Vector;

/**
 * Class that allows for the communication between server and client, contains
 * type of request/response and a vector of data sent
 * 
 * @author Fernando Flores Moya
 */
public class Transmission implements Serializable {
    private byte type;
    private Vector<Serializable> object;
    
    public static final byte LOGIN_REQUEST = 0;
    public static final byte REGISTER_REQUEST = 1;
    public static final byte AVAILABLE_SESSIONS_REQUEST = 2;
    public static final byte ENROLLED_SESSIONS_REQUEST = 3;
    public static final byte HISTORY_REQUEST = 4;
    public static final byte NOTIFICATION_REQUEST = 5;
    public static final byte ENROLL_SESSION_REQUEST = 6;
    public static final byte CANCEL_ENROLL_REQUEST = 7;
    public static final byte LOGOUT_REQUEST = 8;
    public static final byte CLOSE_CONNECTION_REQUEST = 9;
    
    
    public Transmission(byte type, Vector object) {
        this.type = type;
        this.object = object;
    }
    
    public Transmission(byte type) {
        this.type = type;
        object = new Vector<>();
    }

    public byte getType() {
        return type;
    }

    public Vector getObject() {
        return object;
    }
    
    /**
     * Adds a serializable component to the vector of componenets
     * @param component a piece of data to be inserted
     */
    public void addComponent(Serializable component) {
        object.add(component);
    }
    
    public String toString() {
        return "Tipo: " + type + " Objeto: " + ((object != null)? object.toString(): "null");
    }
}
