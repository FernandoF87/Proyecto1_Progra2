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
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


import server.model.Notification;
import server.model.Session;
import server.model.User;

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
    
    private static final String KEY = "jorchozz";
    
    public static HashMap loadUsers() {
        return loadHashMap(USERS);
    }
    
    public static HashMap loadSessions() {
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
                    if (type == USERS) {
                        User user = (User) object;
                        user.setPassword(decrypt(user.getPassword()));
                        hashMap.put(user.getEmail(), user);
                    } else {
                        Session session = (Session) object;
                        hashMap.put(session.getSesionId(), session);
                    }
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
        return decrypt(adminData.getProperty("adminPassword"));
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
            if (type == USERS) {
                for (String temp: (Set<String>) hashMap.keySet()) {
                    User user = (User) hashMap.get(temp); 
                    user.setPassword(encrypt(user.getPassword()));
                    output.writeObject(user);
                } 
            } else {
                for (String temp : (Set<String>) hashMap.keySet()) {
                    output.writeObject(hashMap.get(temp));
                }
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
    
    private static SecretKeySpec createPass() {
        try {
            byte[] string = KEY.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            string = Arrays.copyOf(string, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(string, "AES");
            return secretKeySpec;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    private static String encrypt(String toEncript) {
        try {
            SecretKeySpec secretKey = createPass();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] string = toEncript.getBytes("UTF-8");
            byte[] encripted = cipher.doFinal(string);
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(encripted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            ex.printStackTrace();
        } catch (InvalidKeyException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IllegalBlockSizeException | BadPaddingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private static String decrypt(String toDecrypt) {
        try {
            SecretKeySpec secretKey = createPass();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] string = decoder.decode(toDecrypt);
            byte[] decryptate = cipher.doFinal(string);
            return new String(decryptate);
        } catch (BadPaddingException | IllegalBlockSizeException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            ex.printStackTrace();
        } catch (InvalidKeyException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    //METODOS TEMPORALES. BORRAR ANTES DE ENTREGAR
    
    public static void updateNotifications(ArrayList<Notification> a) {
        File file = new File(NOTIFICATIONS_FILE);
        ObjectOutputStream output = null;
        
        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOutput);
            
            for (Notification temp: a) {
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
    
    public static void updateHashMap(byte type, HashMap h) {
        String path = "";
        HashMap hashMap = h;
        
        switch (type) {
            case USERS:
                path = USERS_FILE;
                //hashMap = usersList;
                break;
            case SESSION:
                path = SESSIONS_FILE;
                //hashMap = sessionList;
                break;
        }
        
        File file = new File(path);
        ObjectOutputStream output = null;
        
        try {
            FileOutputStream fileOutput = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOutput);
            
            if (type == USERS) {
                for (String temp: (Set<String>) hashMap.keySet()) {
                    User user = (User) hashMap.get(temp); 
                    user.setPassword(encrypt(user.getPassword()));
                    output.writeObject(user);
                } 
            } else {
                for (String temp : (Set<String>) hashMap.keySet()) {
                    output.writeObject(hashMap.get(temp));
                }
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
