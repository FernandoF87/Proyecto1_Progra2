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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
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
    private LinkedList <Session> availableSessions;
    private LinkedList <Session> registeredSessions;
    private LinkedList <Session> historySessions;
    
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
            System.out.println("enviado");
            Vector vector = ((Transmission) input.readObject()).getObject();
            System.out.println(vector.get(0));
            System.out.println(vector.get(1));
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
                NotificationsDialog notifications = new NotificationsDialog(main, false);
                notifications.setVisible(true);
            }
        } catch (IOException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");

        } catch (ClassNotFoundException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");

        }
    }
    
    private void loadNotifications() {
        
    }
    
    private void loadAvailableSessions() {
        
    }
    
    private void loadRegisteredSessions() {
        
    }
    
    private void loadHistorySessions() {
        
    }
   
}
