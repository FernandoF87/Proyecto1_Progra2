package model;

import java.io.Serializable;

/**
 *
 * @author Fernando Flores Moya
 */
public class Transmission implements Serializable {
    private byte type;
    private Serializable object;
    
    public static final byte LOGIN_REQUEST = 0;
    public static final byte REGISTER_REQUEST = 1;
    public static final byte AVAILABLE_SESSIONS_REQUEST = 2;
    public static final byte ENROLLED_SESSIONS_REQUEST = 3;
    public static final byte HISTORY_REQUEST = 4;
    public static final byte NOTIFICATION_REQUEST = 5;
    
    public Transmission(byte type, Serializable object) {
        this.type = type;
        this.object = object;
    }

    public byte getType() {
        return type;
    }

    public Serializable getObject() {
        return object;
    }
}
