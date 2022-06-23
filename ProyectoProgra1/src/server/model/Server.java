package server.model;

import server.exceptions.NotificationException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Fernando Flores Moya
 */
public class Server {

    private final int PORT = 8000;
    private final int TIME_OUT = 2000;

    private ServerSocket socket;
    private Socket connection;
    private Data data = new Data();
    private ArrayList<ConnectionThread> connections = new ArrayList<>();
    private HashMap<String, Session> sessions = data.getSessions();

    public static void main(String[] args) {
        new Server().runServer();
    }

    public void runServer() {
        // Aqui se leen los archivos primeros y se cargan los datos
        try {
            socket = new ServerSocket(PORT);
            socket.setSoTimeout(TIME_OUT);
            boolean execute = true;
            // Listening channel
            while (execute) {
                try {
                    connection = socket.accept();
                    System.out.println(connection);
                    ConnectionThread conThread = new ConnectionThread(connection, data);
                    connections.add(conThread);
                    System.out.println("Conexión aceptada");
                    conThread.start();
                } catch (SocketTimeoutException ex) {
                    System.out.println("Revisando las sesiones");
                    checkSessions();
                }
            }
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void checkSessions() {
//        for (String key : sessions.keySet()) {
//            Session session = sessions.get(key);
//            if (1 < 2) { // Aqui va comprobacion de fechas
//                for (int i = 0; i < connections.size(); i++) {
//                    ConnectionThread temp = connections.get(i);
//                    try {
//                        if (temp.getConnectionId() != null && temp.getConnectionId().equals(session.getSesionId())) {
//                            temp.notifyUser(new Notification(session.getSesionId(), (byte) 0));
//                        } else {
//                            data.addNotification(new Notification(session.getSesionId(), (byte) 0));
//                        }
//                    } catch (NotificationException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        }
        for (ConnectionThread connection : connections) {
            connection.test("Enviada notificacion");
        }
    }
}
