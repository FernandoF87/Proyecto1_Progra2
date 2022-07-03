/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package user.controller;

/**
 * Class that manages all de user input, and output from the differents forms
 * and manages the communication with server. 
 * @version
 * @author Jostin Castro
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Vector;


import user.view.LoginFrame;
import user.view.MessageDialog;
import user.view.RegisterForm;
import server.model.Transmission;
import server.model.Notification;
import user.view.MainFrame;
import user.view.NotificationsDialog;


public class UserThread {
    
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    private final int PORT = 8000;
    private final int WAIT_TIME = 2000;
    private final String HOST = "127.0.0.1";
    
    private String loggedUsername;
    private LinkedList<Notification> listNotifications;
    private MainFrame main;
    private NotificationsDialog notifications;
    
    /**
     * Constructor of the class.
     */
    
    public UserThread() {
        listNotifications = new LinkedList();
    }
    
    /**
     * Creates and run the UserThread.
     * @param args 
     */
    
    public static void main(String[] args) {
        new UserThread().mainProcess();
//        MainFrame test = new MainFrame("Prueba");
//        test.setVisible(true);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException ex) {
//        }
//        test.manageNewNotification();
    }
    
    /**
     * Method that manage the main process of the thread 
     */
    
    private void mainProcess() {
        try {
            connection = new Socket(HOST, PORT);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            do {
                try {
                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (login.getOption() == login.REGISTER) {
                    login.setVisible(false);
                    registerOption();
                    login.resetComponents();
                    login.setOption(login.WAIT);
                    login.setVisible(true);
                } else if (login.getOption() == login.LOGIN) {
                    if (loginOption(login.getEmail(), login.getPassword())) {
                        //If login is correct:
                        login.setVisible(false);
                        loggedUserInterface();
                        login.setOption(login.WAIT);
                        login.resetComponents();
                        login.setVisible(true);
                    } else {
                        //If login is incorrect:
                        login.setOption(login.WAIT);
                        login.resetComponents();
                    }
                }
            } while (login.getOption() == login.WAIT && !login.isClosed());
            output.writeObject(new Transmission(Transmission.CLOSE_CONNECTION_REQUEST));
            System.out.println("Petición de cerrar conexión hecha");
            output.flush();
        } catch(IOException ex) {
            MessageDialog.showMessageDialog("No se pudo establecer comunicación con el servidor", "Error");
        } finally {
            if (connection != null && connection.isConnected())
                closeConnection();
        }
    }
    
    /**
     * Method to show the register form, capture data from user and
     * send it to the server. 
     */
    
    private void registerOption() {
        RegisterForm register = new RegisterForm(null, false);
        register.setVisible(true);
        while (!register.isComplete() && !register.isClosed()) {
            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (!register.isClosed()) {
            Vector<Serializable> userData = new Vector();
            userData.add(register.getId());
            userData.add(register.getEmail());
            userData.add(register.getPassword());
            userData.add(register.getBornDate());
            userData.add(register.getName());
            userData.add(register.getPhoneNumber());
            Transmission temp = new Transmission(Transmission.REGISTER_REQUEST, userData);
            System.out.println(temp);
            try {
                output.writeObject(temp);
                output.flush();
                Vector receivedData = ((Transmission) input.readObject()).getObject();
                if ((Boolean) receivedData.get(0)) {
                    MessageDialog.showMessageDialog((String) receivedData.get(1), "Hecho");
                    register.dispose();
                } else {
                    String text = "<html>";
                    for (int i = 1; i < receivedData.size(); i++) {
                        text += "<p>" + (String) receivedData.get(i) + "</p>";
                    }
                    MessageDialog.showMessageDialog(text + "</html>", "Error");
                }
            } catch (ClassNotFoundException ex) {
                MessageDialog.showMessageDialog("Error inesperado", "Error");
            } catch (IOException ex) {
                MessageDialog.showMessageDialog("Error inesperado", "Error");
            }
        }     
    }
    
    /**
     * Method throwed when the user click on the Login button, it send the email
     * and the password to the server and manage his answer 
     * @param email the user email typed in the TextField
     * @param password the password typed in the PasswordField
     * @return true if the login is correct, false otherwise
     */
    
    private boolean loginOption(String email, String password) {
        try {
            Vector<Serializable> userData = new Vector();
            userData.add(email);
            userData.add(password);
            System.out.println(email + "\n" + password);
            Transmission temp = new Transmission(Transmission.LOGIN_REQUEST, userData);
            output.writeObject(temp);
            output.flush();
            Vector vector = ((Transmission) input.readObject()).getObject();
            if ((boolean) vector.get(0)) {
                loggedUsername = (String) vector.get(1);
                return true;
            } else {
                MessageDialog.showMessageDialog((String) vector.get(1), "Error");
                return false;
            }
        } catch (ClassNotFoundException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        } catch (IOException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        }
        return false;
    }
    
    /**
     * Method enchargated of show to the user differents tabs with the data
     * sended by the server.
     */
    
    private void loggedUserInterface() {
        Transmission temp = null;
        byte lastSelected = -1;
        try {
            output.writeObject(new Transmission(Transmission.NOTIFICATION_REQUEST, null));
            output.flush();
            temp = (Transmission) input.readObject();
            System.out.println("Llegada objeto " + temp.getType() + "  " + temp.getObject().toString());
            main = new MainFrame(loggedUsername);
            main.setVisible(true);
            if (temp.getType() == Transmission.NOTIFICATION_REQUEST) {
                notifications = new NotificationsDialog(main, false);
                notifications.setVisible(true);
                if (temp.getObject().size() > 0) {
                    Vector data = temp.getObject();
                    for (int i = 0; i < data.size(); i++) {
                        listNotifications.add((Notification) data.get(i));
                    }
                }
                notifications.loadNotifications(listNotifications);
            }
        } catch (ClassNotFoundException | IOException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        }

        do {
            try {
                connection.setSoTimeout(WAIT_TIME);
                temp = (Transmission) input.readObject();
                if (temp.getType() == Transmission.NOTIFICATION_REQUEST) {
                    Notification notification = (Notification) ((Vector) temp.getObject()).get(0);
                    newNotification(notification);
                    main.manageNewNotification();
                    System.out.println("Llegada de notificación " + temp);
                }
            } catch (SocketTimeoutException ex) {
                try {
                    //if (lastSelected != main.getSelectedOption()) {
                        switch (main.getSelectedOption()) {
                            case MainFrame.AVAILABLE_TAB:
                                output.writeObject(new Transmission(Transmission.AVAILABLE_SESSIONS_REQUEST, null));
                                break;
                            case MainFrame.ENROLLED_TAB:
                                output.writeObject(new Transmission(Transmission.ENROLLED_SESSIONS_REQUEST, null));
                                break;
                            case MainFrame.HISTORY_TAB:
                                output.writeObject(new Transmission(Transmission.HISTORY_REQUEST, null));
                                break;
                            case MainFrame.NOTIFICATION_OPTION:
                                notifications.setVisible(true);
                                break;
                            case MainFrame.ENROLL_SESSION:
                                temp = new Transmission(Transmission.ENROLL_SESSION_REQUEST);
                                temp.getObject().add(main.getSessionId());
                                output.writeObject(temp);
                                main.setSessionId(null);
                                break;
                            case MainFrame.CANCEL_ENROLL_SESSION:
                                temp = new Transmission(Transmission.CANCEL_ENROLL_REQUEST);
                                temp.getObject().add(main.getSessionId());
                                output.writeObject(temp);
                                main.setSessionId(null);
                                break;
                            case MainFrame.LOGIN_OUT:
                                output.writeObject(new Transmission(Transmission.LOGOUT_REQUEST, null));
                                break;
                        }
                        if (main.getSelectedOption() != MainFrame.NOTIFICATION_OPTION) {
                            output.flush();
                            System.out.println("Envio peticion" + main.getSelectedOption());
                        }
                    //}
                    lastSelected = main.getSelectedOption();
                    temp = (Transmission) input.readObject();
                    if (temp.getType() == Transmission.NOTIFICATION_REQUEST) {
                        Notification notification = (Notification) ((Vector) temp.getObject()).get(0);
                        newNotification(notification);
                        main.manageNewNotification();
                        continue;
                    } else if (temp.getType() == Transmission.ENROLL_SESSION_REQUEST) {
                        Vector vector = temp.getObject();
                        if (vector != null && vector.size() > 0) {
                            String string = (String) vector.get(1);
                            if ((boolean) vector.get(0)) {
                                MessageDialog.showMessageDialog(main, false, string, "Correcto");
                                main.setSessionAccepted(Boolean.TRUE);
                            } else {
                                MessageDialog.showMessageDialog(main, false, string, "Incorrecto");
                                main.setSessionAccepted(Boolean.FALSE);
                            }
                        }
                    } else {
                        System.out.println("Respuesta " + temp);
                        main.writeData((byte) (temp.getType() - 2), temp.getObject());
                    }
                    System.out.println("Llegada transmisión" + temp.getType() + "\n" + temp.getObject().toString());
                } catch (SocketTimeoutException ex1) {
                    System.out.println("tiempo agotado");
                } catch (IOException | ClassNotFoundException ex2) {
                    MessageDialog.showMessageDialog("Error inesperado", "Error");
                }
            } catch (SocketException ex) {
                MessageDialog.showMessageDialog("Error inesperado", "Error");
            } catch (IOException ex) {
                MessageDialog.showMessageDialog("Error inesperado", "Error");;
            } catch (ClassNotFoundException ex) {
                MessageDialog.showMessageDialog("Error inesperado", "Error");
            }
            System.out.println("Actual seleccionado: " + main.getSelectedOption() + "\nAnterior: " + lastSelected);
        } while ((main.getSelectedOption() != MainFrame.LOGIN_OUT));
        main.dispose();
    }
        
        
    
    /**
     * Method that charge the notification data to his respective form
     * @param notification a new notification to manage
     */
    
    private void newNotification(Notification notification) {
        if (listNotifications.size() == 5) {
            listNotifications.pop();
        } else {
            listNotifications.add(notification);
        }
        notifications.loadNotifications(listNotifications);
        
    }
     
    /**
     * Method used to close connection when user close the program
     */
    
    private void closeConnection() {
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        }
    }
   
}
