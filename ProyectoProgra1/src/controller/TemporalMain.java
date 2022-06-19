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

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ArrayList;

import userModel.User;
import model.Session;
import model.Notification;
import exceptions.NotificationException;

public class TemporalMain {
    
    public static void main(String[] args) {
        //SOLO PARA PRUEBAS
        
        System.out.println("Admin user: " + FilesLoader.getAdminUser());
        System.out.println("Admin password: " + FilesLoader.getAdminPassword());
        
//        try {
//            Notification testNotification = new Notification("207850253", Notification.FIVE_MINUTES);
//            ArrayList<Notification> arrayNotifications = new ArrayList();
//            arrayNotifications.add(testNotification);
//            FilesLoader.updateNotifications();
//        } catch (NotificationException ex) {
//            ex.printStackTrace();
//        }
        
        ArrayList<Notification> testArray = FilesLoader.loadNotifications();
        
        for (Notification temp: testArray) {
            System.out.println(temp.getUserId() + " " + temp.getMessage());
        }
        
        HashMap<String, User> users = FilesLoader.loadUsers();
        for (String string : users.keySet()) {
            System.out.println(string);
        }
        
        HashMap<String, Session> session = FilesLoader.loadSessions();
        
        for (String string : session.keySet()) {
            System.out.println(string);
        }
       
    }

}
