package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 *
 * @author Fernando Flores Moya
 */
public class ConnectionThread extends Thread {

    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String connectionId;
    private Data data;

    public ConnectionThread(Socket connection, Data data) {
        this.connection = connection;
        this.data = data;
        try {
            connection.setSoTimeout(5000);
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        getStreams();
        listen();
        close();
    }

    public void listen() {
        boolean execute = true;
        while (execute) {
            try {
                Transmission request = (Transmission)(input.readObject());
                processRequest(request);
            } catch (SocketTimeoutException ex) {
                
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void getStreams() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void notifyUser(Notification notification) {
        try {
            output.writeObject(notification);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getConnectionId() {
        return connectionId;
    }
    
    private void processRequest(Transmission request) {
        Transmission answer;
        switch (request.getType()) {
            case Transmission.LOGIN_REQUEST:
                break;
            case Transmission.REGISTER_REQUEST:
                break;
            case Transmission.AVAILABLE_SESSIONS_REQUEST:
                break;
            case Transmission.ENROLLED_SESSIONS_REQUEST:
                break;
            case Transmission.HISTORY_REQUEST:
                break;
            case Transmission.NOTIFICATION_REQUEST:
                break;
        }
    }
}
