/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package server.controller;

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
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.Set;


import server.model.User;
import server.model.Session;
import server.model.Notification;

public class FilesLoader {
    
    private static final String USERS_FILE = "src/server/files/users.dat";
    private static final String NOTIFICATIONS_FILE = "src/server/files/notifications.dat";
    private static final String SESSIONS_FILE = "src/server/files/sessions.dat";
    private static final String ADMIN_DATA = "src/server/files/adminData.properties";
    private static final byte USERS = 0;
    private static final byte SESSION = 1;
    
    private static HashMap<String, server.model.User> usersList = new HashMap();
    private static HashMap<String, server.model.Session> sessionList = new HashMap();
    private static ArrayList<server.model.Notification> notificationList = new ArrayList();
    private static Properties adminData;
    
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
        ObjectInputStream input = null;
        
        try {
            FileInputStream fileInput = new FileInputStream(file);
            input = new ObjectInputStream(fileInput);
            Object object = input.readObject();
            while (object != null) {
                if (object != null) {
                    hashMap.put(((type == USERS)?((server.model.User) object).getUserID(): ((server.model.Session) object).getSesionId()), 
                        ((type == USERS)? (server.model.User) object: (server.model.Session) object));
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
        notificationList = new ArrayList();
        File file = new File(NOTIFICATIONS_FILE);
        ObjectInputStream input = null;
        try {
            FileInputStream fileInput = new FileInputStream(file);
            input = new ObjectInputStream(fileInput);
            Object object = input.readObject();
            while (object != null) {
                if (object != null) {
                    notificationList.add((server.model.Notification) object);
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
        return notificationList;
    }
    
    private static void loadPropertiesFile() {
        adminData = new Properties();
        try {
            FileReader fileReader = new FileReader(ADMIN_DATA);
            adminData.load(fileReader);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static String getAdminUser() {
        if (adminData == null) {
            loadPropertiesFile();
        }
        return adminData.getProperty("adminUser");
    }
    
    public static String getAdminPassword() {
        if (adminData == null) {
            loadPropertiesFile();
        }
        return adminData.getProperty("adminPassword");
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
        File file = new File(NOTIFICATIONS_FILE);
        ObjectOutputStream output = null;
        
        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOutput);
            
            for (Notification temp: notificationList) {
                output.writeObject(temp);
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

}
