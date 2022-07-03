package server.model;

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
    private boolean execute = true;

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
                try {
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
                for (String sessionId : sessions.keySet()) {
                    Session session = sessions.get(sessionId);
                    if (session.isOpen()) {
                        if (!session.isParticipant(connectionUser.getEmail()) && session.getState() == Session.INACTIVE_STATE) {
                            answer.addComponent(sessions.get(sessionId).clone());
                        }
                    } else {
                        if (!session.isWaiting(connectionUser.getEmail()) && session.getState() == Session.INACTIVE_STATE) {
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
                for (String sessionId : sessions.keySet()) {
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
                for (String sessionId : sessions.keySet()) {
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
                for (int i = 0; i < pending.size(); i++) {
                    if (pending.get(i).getUserId().equals(connectionUser.getEmail())) {
                        connectionUser.addNotification(pending.remove(i));
                    }
                }
                answer = new Transmission(Transmission.NOTIFICATION_REQUEST);
                LinkedList<Notification> notifications = connectionUser.getNotifications();
                for (int i = 0; i < notifications.size(); i++) {
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
                // Verifies to not enroll in a session at the same time
                for (String key : data.getSessions().keySet()) {
                    Session temp = data.getSessions().get(key);
                    if (temp.isParticipant(connectionUser.getEmail()) && session.getDate().equals(temp.getDate())) {
                        answer.addComponent(false);
                        answer.addComponent("Usted ya está inscrito en una sesión a la misma hora");
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
                    answer.addComponent("Se inscribió correctamente");
                } else {
                    session.addUser(connectionUser.getEmail(), false);
                    answer.addComponent(true);
                    answer.addComponent("Ahora está en la lista de espera a ser aprobado");
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
