/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package user.controller;

/**
 *
 * @version
 * @author Jostin Castro
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.JTextComponent;

import user.view.LoginFrame;
import user.view.MessageDialog;
import user.view.RegisterForm;
import server.model.Transmission;
import server.model.Notification;
import server.model.Session;
import server.model.User;
import user.view.MainFrame;
import user.view.NotificationsDialog;


public class UserThread {
    
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    private final int PORT = 8000;
    private final String HOST = "127.0.0.1";
    
    private String loggedUsername;
    private LinkedList<Notification> listNotifications;
    private Vector <Session> availableSessions;
    private Vector <Session> registeredSessions;
    private Vector <Session> historySessions;
    private NotificationsDialog notifications;
    
    public UserThread() {
        listNotifications = new LinkedList();
        availableSessions = new Vector();
        registeredSessions = new Vector();
        historySessions = new Vector();
    }
    
    public static void main(String[] args) {
        new UserThread().mainProcess();
    }
    
    private void mainProcess() {
        try {
            connection = new Socket(HOST, PORT);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
        } catch(IOException ex) {
            MessageDialog.showMessageDialog("No se pudo establecer comunicación con el servidor", "Error");
        }
        if (connection != null && connection.isConnected()) {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            do {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (login.getOption() == login.REGISTER) {
                    login.setVisible(false);
                    registerOption();
                    login.resetComponents();
                    login.setOption((byte) 0);
                } else if (login.getOption() == login.LOGIN) {
                    if (loginOption(login.getEmail(), login.getPassword())) {
                        //Acá lo que pasa cuando todo está correcto
                        login.dispose();
                        loggedUserInterface();
                    } else {
                        //Login incorrecto, reinicio de interfaz LoginFrame
                        login.setOption((byte) 0);
                        login.resetComponents();
                    }
                }
            } while (login.getOption() == 0);
            
        }
    }
    
    private void registerOption() {
        RegisterForm register = new RegisterForm(null, false);
        register.setVisible(true);
        while (!register.isComplete()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        Vector<Serializable> userData = new Vector();
        userData.add(register.getId());
        userData.add(register.getEmail());
        userData.add(register.getPassword());
        userData.add(register.getBornDate());
        userData.add(register.getName());
        userData.add(register.getPhoneNumber());
        Transmission temp = new Transmission(Transmission.REGISTER_REQUEST, userData);
        try {
            System.out.println("Enviando " + temp);
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
    
    private void loggedUserInterface() {
        try {
            output.writeObject(new Transmission(Transmission.NOTIFICATION_REQUEST, null));
            Transmission temp = (Transmission) input.readObject();
            MainFrame main = new MainFrame(loggedUsername);
            main.setVisible(true);
            if (temp.getType() == Transmission.NOTIFICATION_REQUEST) {
                notifications = new NotificationsDialog(main, false);
                notifications.setVisible(true);
                if (temp.getObject() != null) {
                    Vector data = temp.getObject();
                    for (int i = 0; i < data.size(); i++) {
                        listNotifications.add((Notification) data.get(i));
                    }
                    notifications.loadNotifications(listNotifications);
                } else {
                    notifications.loadNotifications(null);
                }
            }
            do {
                try {
                    connection.setSoTimeout(5000);
                    temp = (Transmission) input.readObject();
                    if (temp.getType() == Transmission.NOTIFICATION_REQUEST) {
                        Notification notification = (Notification) ((Vector) temp.getObject()).get(0);
                        newNotification(notification);
                    }
                } catch (SocketTimeoutException ex) {
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
                    case MainFrame.LOGIN_OUT:
                        output.writeObject(new Transmission(Transmission.LOGIN_REQUEST, null));
                        break;
                    }
                    output.flush();
                    temp = (Transmission) input.readObject();
                    
                }
                
            } while ((main.getSelectedOption() != MainFrame.LOGIN_OUT));
        } catch (IOException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");

        } catch (ClassNotFoundException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        }
        
    }
    
    private void newNotification(Notification notification) {
        if (listNotifications.size() == 5) {
            listNotifications.pop();
        } else {
            listNotifications.add(notification);
        }
        notifications.loadNotifications(listNotifications);
        
    }
    
    private void loadAvailableSessions() {
        try {
            output.writeObject(new Transmission(Transmission.AVAILABLE_SESSIONS_REQUEST, null));
            output.flush();
            Transmission temp = (Transmission) input.readObject();
            if (temp.getType() == Transmission.NOTIFICATION_REQUEST) {
                
            } else {
                System.out.println("loadAvailableSessions: llegó otro tipo de paquete, tipo:" + temp.getType());
            }
        } catch (IOException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        } catch (ClassNotFoundException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        }
    }
    
    private void loadRegisteredSessions() {
        
    }
    
    private void loadHistorySessions() {
        
    }
   
}
