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
        LoginFrame login = new LoginFrame(null, true);
        login.setVisible(true);
        while (!login.isFulled()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        login();
    }
    
    public void login() {
        try {
            connection = new Socket(HOST, PORT);
        } catch (IOException ex) {
            TextPrompt temp = new TextPrompt("No se ha podido establecer la conexión con el servidor", null);
        }
    }

}
