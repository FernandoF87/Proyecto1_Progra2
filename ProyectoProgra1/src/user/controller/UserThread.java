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
import java.net.ConnectException;
import java.net.Socket;
import javax.swing.text.JTextComponent;

import user.view.LoginFrame;
import user.view.MessageDialog;
import user.view.RegisterForm;
import server.model.Transmission;


public class UserThread {
    
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    private final int PORT = 8000;
    private final String HOST = "127.0.0.1";
    
    public static void main(String[] args) {
        new UserThread().startUserClient();
    }
    
    private void startUserClient() {
        try {
            connection = new Socket(HOST, PORT);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
        } catch(IOException ex) {
            MessageDialog message = new MessageDialog("No se pudo establecer comunicación con el servidor", "Error");
        }
        if (connection != null && connection.isConnected()) {
            LoginFrame login = new LoginFrame(null, true);
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
                loginOption();
            }
        }
        
//        LoginFrame login = new LoginFrame(null, true);
//        login.setVisible(true);
//        while (!login.isFulled()) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }
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
        Transmission temp = new Transmission(Transmission.REGISTER_REQUEST, register.getUser());
        try {
            output.writeObject(temp);
            output.flush();
        } catch (IOException ex) {
            MessageDialog message = new MessageDialog("Error inesperado", "Error");
        }
            
    }
    
    private void loginOption() {
        try {
            Transmission temp = new Transmission(Transmission.LOGIN_REQUEST, new user.model.User());
            output.writeObject(PORT);
            output.flush();
        } catch (IOException ex) {
            MessageDialog message = new MessageDialog("Error inesperado", "Error");
        }
    }
    
    
}
