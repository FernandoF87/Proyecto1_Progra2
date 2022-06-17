/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

/**
 *
 * @version
 * @author Jostin Castro
 */


import java.io.EOFException;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;


import model.User;
import model.Session;

public class FilesLoader {
    
    private static final String USERS_FILE = "src/files/users.dat";
    private static final String NOTIFICATIONS_FILE = "src/files/notifications.dat";
    private static final String SESSIONS_FILE = "src/files/sessions.dat";
    private static final byte USERS = 0;
    private static final byte SESSION = 1;
    
    private static HashMap<String, User> usersList = new HashMap();
    private static HashMap<String, Session> sessionList = new HashMap();
    private static ArrayList notificationList;
    
    public static HashMap loadUsers() {
        final byte USERS = 0;
        return loadHashMap(USERS);
    }
    
    public static HashMap loadSessions() {
        final byte SESSION = 1;
        return loadHashMap(SESSION);
    }
    
    private static HashMap loadHashMap(byte type) {
        HashMap hashMap = null;
        String path = "";
        switch (type) {
            case USERS:
                path = USERS_FILE;
                hashMap = usersList;
                break;
            case SESSION:
                path = SESSIONS_FILE;
                hashMap = sessionList;
                break;
        }
        hashMap = new HashMap();
        File file = new File(path);
        //hashMap = new HashMap();
        ObjectInputStream input = null;
        
        try {
            FileInputStream fileInput = new FileInputStream(file);
            input = new ObjectInputStream(fileInput);
            Object object = input.readObject();
            while (object != null) {
                if (object != null) {
                    hashMap.put(((type == USERS)?((User) object).getUserID(): ((Session) object).getSesionId()), 
                        ((type == USERS)? (User) object: (Session) object));
                    object = input.readObject();
                } 
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            if (ex instanceof EOFException) {
                System.out.println("Fin de archivo");;
            } else {
                ex.printStackTrace();
            }
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return hashMap;
    }
    
    public static ArrayList loadNotifications() {
        
        return notificationList;
    }
    
    public static void updateUsers() {
        updateHashMap(USERS);
    }
    
    public static void updateSessions() {
        updateHashMap(SESSION);
    }
    
    public static void updateHashMap(byte type) {
        String path = "";
        HashMap hashMap = null;
        
        switch (type) {
            case USERS:
                path = USERS_FILE;
                hashMap = usersList;
                break;
            case SESSION:
                path = SESSIONS_FILE;
                hashMap = sessionList;
                break;
        }
        
        File file = new File(path);
        ObjectOutputStream output = null;
        
        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOutput);
            
            for (String temp: (Set<String>) hashMap.keySet()) {
                output.writeObject(hashMap.get(temp));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void updateNotifications() {
        
    }

}
