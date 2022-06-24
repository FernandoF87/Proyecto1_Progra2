package server.model;

import java.io.Serializable;
import java.util.Vector;

/**
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
    
    public void addComponent(Serializable component) {
        object.add(component);
    }
    
    public String toString() {
        return "Tipo: " + type + " Objeto: " + object.toString();
    }
}
