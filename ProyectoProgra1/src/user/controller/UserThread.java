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


public class UserThread {
    
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    private final int PORT = 8000;
    private final String HOST = "127.0.0.1";
    
    private LinkedList<Notification> listNotifications;
    private HashMap<String, Session> availableSessions;
    private HashMap<String, Session> registeredSessions;
    private HashMap<String, Session> historySessions;
    
    public static void main(String[] args) {
        new UserThread().mainProcess();
    }
    
    private void mainProcess() {
        try {
            connection = new Socket(HOST, PORT);
            System.out.println("Conexión aceptada");
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
        } catch(IOException ex) {
            MessageDialog.showMessageDialog("No se pudo establecer comunicación con el servidor", "Error");
        }
        if (connection != null && connection.isConnected()) {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            while (login.getOption() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            if (login.getOption() == login.REGISTER) {
                registerOption();
            } else {
                loginOption(login.getEmail(), login.getPassword());
            }
        }
    }
    
    private void registerOption() {
        RegisterForm register = new RegisterForm(null, true);
        register.setVisible(true);
        register.setAlwaysOnTop(true);
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
            } else {
                String text = "<html><p>";
                for (int i = 1; i < receivedData.size(); i++) {
                    text += (String) receivedData.get(i) + "</p>";
                }
                MessageDialog.showMessageDialog(text + "</p></html>", "Error");
            }
        } catch (ClassNotFoundException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        } catch (IOException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        }
            
    }
    
    private void loginOption(String email, String password) {
        try {
            Vector<Serializable> userData = new Vector();
            userData.add(email);
            userData.add(password);
            Transmission temp = new Transmission(Transmission.LOGIN_REQUEST, userData);
            output.writeObject(userData);
            output.flush();
            Vector vector = ((Transmission) input.readObject()).getObject();
            if ((boolean) vector.get(0)) {
                
            }
        } catch (ClassNotFoundException ex) {
            MessageDialog.showMessageDialog("Error inesperado", "Error");
        } catch (IOException ex) {
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
