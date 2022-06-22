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
import user.view.TextPrompt;


public class UserThread {
    
    private DataInputStream primitiveInput;
    private DataOutputStream primitiveOutput;
    private Socket connection;
    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;
    
    private final int PORT = 8000;
    private final String HOST = "127.0.0.1";
    
    public static void main(String[] args) {
        new UserThread().startUserClient();
    }
    
    public void startUserClient() {
        try {
            connection = new Socket(HOST, PORT);
        } catch(IOException ex) {
            MessageDialog message = new MessageDialog(null, true, "No se pudo establecer comunicación con el servidor", "Error");
        }
        if (connection.isConnected()) {
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
                RegisterForm register = new RegisterForm(null, true);
                register.setVisible(true);
                register.setAlwaysOnTop(true);
            } else {
                
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
}
