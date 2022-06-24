package server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.GregorianCalendar;
import server.exceptions.NotificationException;

import server.model.User;

/**
 *
 * @author Fernando Flores Moya
 */
public class ConnectionThread extends Thread {

    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private User connectionUser;
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
                Transmission request = (Transmission) (input.readObject());
                System.out.println(request);
                processRequest(request);
            } catch (SocketTimeoutException ex) {
                System.out.println("Nada");
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

    public User getConnectionUser() {
        return connectionUser;
    }

    private void processRequest(Transmission request) {
        Transmission answer;
        switch (request.getType()) {
            case Transmission.LOGIN_REQUEST:
                answer = new Transmission((byte) 0);
                String email = (String) (request.getObject().get(0));
                String password = (String) (request.getObject().get(1));
                User user = data.searchUserEmail(email);
                if (user != null) {
                    if (user.getPassword().equals(password)) {
                        answer.addComponent(true);
                        answer.addComponent(user.getName());
                        connectionUser = user;
                    } else {
                        answer.addComponent(false);
                        answer.addComponent("La contraseña es incorrecta");
                    }
                } else {
                    answer.addComponent(false);
                    answer.addComponent("No existe un usuario con el correo correspondiente");
                }
                try {
                    System.out.println("Respuesta: " + answer.toString());
                    output.writeObject(answer);
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Transmission.REGISTER_REQUEST:
                answer = new Transmission((byte) 1);
                UserDirector director = new UserDirector();
                UserAbstractbuilder builder = new UserConcretBuilder();
                
                String id = (String)(request.getObject().get(0));
                email = (String)(request.getObject().get(1));
                password = (String)(request.getObject().get(2));
                int phone = (int)(request.getObject().get(3));
                GregorianCalendar birthDate = (GregorianCalendar)(request.getObject().get(4));
                
                User newUser;
                try {
                    newUser = director.buildUser(builder, id, email, email, password, phone, birthDate);
                    data.addUser(newUser);
                    data.resetUserAvailable();
                    answer.addComponent(true);
                    answer.addComponent("El registro fue exitoso");
                } catch (NotificationException ex) {
                    answer.addComponent(false);
                    answer.addComponent(ex.getMessage());
                }
                
                try {
                    output.writeObject(answer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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

    public void test(String msg) {
        System.out.println(msg);
    }
}
