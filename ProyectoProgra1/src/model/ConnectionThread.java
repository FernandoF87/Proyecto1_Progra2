package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Fernando Flores Moya
 */
public class ConnectionThread extends Thread {
    
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    public ConnectionThread(Socket connection) {
        this.connection = connection;
    }
    
    @Override
    public void run() {
        getStreams();
        close();
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
}
