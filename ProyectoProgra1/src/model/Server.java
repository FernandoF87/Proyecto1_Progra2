package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Fernando Flores Moya
 */
public class Server {
    
    private final int PORT = 8000;
    private ServerSocket socket;
    private Socket connection;
    
    public void runServer() {
        // Aqui se leen los archivos primeros y se cargan los datos
        try {
            socket = new ServerSocket(PORT);
            boolean execute = true;
            // Listening channel
            while (execute) {
                connection = socket.accept();
                ConnectionThread conThread = new ConnectionThread(connection);
                conThread.run();
            }
            // Aqui se 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * public void creationSession(Session session) {
     *      sessions.add(Session);
     * }
     */
}
