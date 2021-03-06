package server.controller;

import server.controller.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import server.exceptions.NotificationException;
import server.model.Notification;
import server.model.Session;
import server.model.Transmission;

import server.model.User;
import server.model.User;
import server.model.UserAbstractbuilder;
import server.model.UserConcretBuilder;
import server.model.UserDirector;

/**
 * Thread that allows for a user to connect to the server and handle requests
 * such as login, register, enrollment, etc
 *
 * @author Fernando Flores Moya
 */
public class ConnectionThread extends Thread {

    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private User connectionUser;
    private Data data;
    private boolean execute = true;

    /**
     *
     * @param connection the socket to be connected to
     * @param data the data that contains all the information stored by the
     * server
     */
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

    /**
     * Constantly listens for requests by the user and processes the request
     */
    private void listen() {
        while (execute) {
            try {
                Transmission request = (Transmission) (input.readObject());
                System.out.println(request);
                processRequest(request);
            } catch (SocketTimeoutException ex) {
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Initiates input and output streams
     */
    private void getStreams() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Closes input and output streams as well as the connection with the client
     */
    private void close() {
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Stops the thread from listening for new requests and sends a close
     * connection response
     */
    public void endExecution() {
        try {
            output.writeObject(new Transmission(Transmission.CLOSE_CONNECTION_REQUEST));
            output.flush();
            execute = false;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sends a notification to the connected client
     *
     * @param notification the notification to be sent to the user
     */
    public void notifyUser(Notification notification) {
        try {
            connectionUser.addNotification(notification);
            Transmission answer = new Transmission(Transmission.NOTIFICATION_REQUEST);
            answer.addComponent(notification);
            System.out.println("Enviando " + answer);
            output.writeObject(answer);
            output.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a user object corresponding to the authenticated user
     *
     * @return the connected user
     */
    public User getConnectionUser() {
        return connectionUser;
    }

    /**
     * Processes a request sent by the user and sends an appropriate response
     *
     * @param request the request to be processed
     */
    private void processRequest(Transmission request) {
        Transmission answer;
        switch (request.getType()) {
            case Transmission.LOGIN_REQUEST:
                answer = new Transmission((byte) 0);
                String email = (String) (request.getObject().get(0));
                String password = (String) (request.getObject().get(1));
                User user = data.searchUserEmail(email);
                if (user != null) { // Verifies that the email is registered
                    if (user.getPassword().equals(password)) { // Verifies the password
                        answer.addComponent(true);
                        answer.addComponent(user.getName());
                        connectionUser = user;
                    } else {
                        answer.addComponent(false);
                        answer.addComponent("La contrase??a es incorrecta");
                    }
                } else {
                    answer.addComponent(false);
                    answer.addComponent("No existe un usuario con el correo correspondiente");
                }
                try {
                    System.out.println("Respuesta: " + answer.toString());
                    System.out.println(connectionUser);
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

                String id = (String) (request.getObject().get(0));
                email = (String) (request.getObject().get(1));
                password = (String) (request.getObject().get(2));
                GregorianCalendar birthDate = (GregorianCalendar) (request.getObject().get(3));
                String name = (String) (request.getObject().get(4));
                int phone = (int) (request.getObject().get(5));

                User newUser;
                try { // Attempts to create a new user
                    newUser = director.buildUser(builder, id, name, email, password, phone, birthDate);
                    System.out.println(newUser.getPassword());
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
                    System.out.println("Respuesta: " + answer.toString());
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Transmission.AVAILABLE_SESSIONS_REQUEST:
                answer = new Transmission(Transmission.AVAILABLE_SESSIONS_REQUEST);
                HashMap<String, Session> sessions = data.getSessions();
                for (String sessionId : sessions.keySet()) { // Sends all non enrolled and non pending sessions
                    Session session = sessions.get(sessionId);
                    if (session.isOpen()) {
                        if (!session.isParticipant(connectionUser.getEmail()) && session.getState() == Session.INACTIVE_STATE) {
                            answer.addComponent(sessions.get(sessionId).clone());
                        }
                    } else {
                        if (!session.isParticipant(connectionUser.getEmail()) && !session.isWaiting(connectionUser.getEmail()) && session.getState() == Session.INACTIVE_STATE) {
                            answer.addComponent(sessions.get(sessionId).clone());
                        }
                    }
                }
                try {
                    output.writeObject(answer);
                    System.out.println("Respuesta: " + answer.toString());
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Transmission.ENROLLED_SESSIONS_REQUEST:
                answer = new Transmission(Transmission.ENROLLED_SESSIONS_REQUEST);
                sessions = data.getSessions();
                for (String sessionId : sessions.keySet()) { // Sends all enrolled and pending sessions
                    if (sessions.get(sessionId).isParticipant(connectionUser.getEmail()) && sessions.get(sessionId).getState() != Session.FINALIZED_STATE) {
                        Session temp = sessions.get(sessionId).clone();
                        temp.setNotifSent(true);
                        answer.addComponent(temp);
                    } else if (!sessions.get(sessionId).isOpen() && sessions.get(sessionId).isWaiting(connectionUser.getEmail())) {
                        Session temp = sessions.get(sessionId).clone();
                        temp.setNotifSent(false);
                        answer.addComponent(temp);
                    }
                }
                try {
                    output.writeObject(answer);
                    System.out.println("Respuesta: " + answer.toString());
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Transmission.HISTORY_REQUEST:
                answer = new Transmission(Transmission.HISTORY_REQUEST);
                sessions = data.getSessions();
                for (String sessionId : sessions.keySet()) { // Sends all finalized sessions
                    if (sessions.get(sessionId).getState() == Session.FINALIZED_STATE) {
                        answer.addComponent(sessions.get(sessionId).clone());
                    }
                }
                try {
                    output.writeObject(answer);
                    System.out.println("Respuesta: " + answer.toString());
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Transmission.NOTIFICATION_REQUEST:
                ArrayList<Notification> pending = data.getNotifications();
                for (int i = 0; i < pending.size(); i++) { // Adds all pending notifications to the user
                    if (pending.get(i).getUserId().equals(connectionUser.getEmail())) {
                        connectionUser.addNotification(pending.remove(i));
                    }
                }
                answer = new Transmission(Transmission.NOTIFICATION_REQUEST);
                LinkedList<Notification> notifications = connectionUser.getNotifications();
                for (int i = 0; i < notifications.size(); i++) { // Sends all notifications
                    answer.addComponent(notifications.get(i));
                }
                try {
                    output.writeObject(answer);
                    System.out.println("Respuesta: " + answer.toString());
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Transmission.ENROLL_SESSION_REQUEST:
                answer = new Transmission(Transmission.ENROLL_SESSION_REQUEST);
                String sessionId = (String) (request.getObject().get(0));
                Session session = data.searchSessionId(sessionId);

                for (String key : data.getSessions().keySet()) { // Verifies to not enroll in a session at the same time
                    Session temp = data.getSessions().get(key);
                    if (temp.isParticipant(connectionUser.getEmail()) && session.getDate().equals(temp.getDate())) {
                        answer.addComponent(false);
                        answer.addComponent("Usted ya est?? inscrito en una sesi??n a la misma hora");
                        try {
                            output.writeObject(answer);
                            System.out.println("Respuesta: " + answer.toString());
                            output.flush();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        return;
                    }
                }
                if (session.isOpen()) {
                    session.addUser(connectionUser.getEmail(), true);
                    answer.addComponent(true);
                    answer.addComponent("Se inscribi?? correctamente");
                } else {
                    session.addUser(connectionUser.getEmail(), false);
                    answer.addComponent(true);
                    answer.addComponent("Ahora est?? en la lista de espera a ser aprobado");
                }

                try {
                    output.writeObject(answer);
                    System.out.println("Respuesta: " + answer.toString());
                    output.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Transmission.CANCEL_ENROLL_REQUEST:
                sessionId = (String) (request.getObject().get(0));
                session = data.searchSessionId(sessionId);
                session.removeUser(connectionUser.getEmail());
                break;

            case Transmission.LOGOUT_REQUEST:
                connectionUser = null;
                break;

            case Transmission.CLOSE_CONNECTION_REQUEST:
                execute = false;
                break;
        }
    }
}
