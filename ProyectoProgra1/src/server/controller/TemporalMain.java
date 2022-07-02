/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package server.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import server.exceptions.NotificationException;
import server.model.Notification;
import server.model.Session;
import server.model.User;

/**
 *
 * @version
 * @author Jostin Castro
 */

public class TemporalMain {
    
    public static void main(String[] args) {
        //SOLO PARA PRUEBAS
        
        System.out.println("Admin user: " + FilesLoader.getAdminUser());
        System.out.println("Admin password: " + FilesLoader.getAdminPassword());
        System.out.println("prueba"+FilesLoader.loadSessions());
        
        //ESTO POR SI SE HACE ALGÚN CAMBIO A LAS CLASES, RECOMPILAR PARA EVITAR ERROR NO CLASS FOUND:
            
//        ArrayList<Notification> testArray = new ArrayList();
//        try {
//            testArray.add(new Notification("207850253", Notification.ACCEPTED_REQUEST, false));
//        } catch (NotificationException ex) {
//            ex.printStackTrace();
//        }
//        
//        HashMap<String, User> testUserH = new HashMap();
//        User user = new User();
//        user.setBornDate(new GregorianCalendar());
//        user.setEmail("hola@gmail.com");
//        user.setName("Jostin Castro");
//        user.setPassword("bolita123");
//        user.setPhoneNumber(2522);
//        user.setUserID("207890325");
//        testUserH.put(user.getEmail(), user);
//        
//
//        GregorianCalendar temp = (GregorianCalendar) GregorianCalendar.getInstance();
//        temp.set(Calendar.HOUR, 6);
//        temp.set(Calendar.MINUTE, 0);
//        temp.set(Calendar.AM_PM, Calendar.PM);
//        Session session = new Session("ZM0", "Sesión n: " + 0, "Jostin Castro", "Sesiones de prueba", "http://google.com", "Zoom",
//                    "Prueba", temp, 30, 25, true, false, Session.FINALIZED_STATE);
//        session.addUser(user.getEmail(), true);
//
//        HashMap<String, Session> testSessionH = new HashMap();
//        testSessionH.put(session.getSesionId(), session);
//        for (int i = 1; i < 10; i++) {
//            byte hour = (byte) (Math.random() * 11);
//            System.out.println(hour);
//            temp = (GregorianCalendar) GregorianCalendar.getInstance();
//            temp.set(Calendar.HOUR, hour);
//            temp.set(Calendar.MINUTE, 0);
//            temp.set(Calendar.DAY_OF_MONTH, i);
//            session = new Session("ZM" + i, "Sesión n: " + i, "Jostin Castro", "Sesiones de prueba", "http://google.com", "Zoom",
//                    "Prueba", temp, 30, 25, ((i % 2) == 0) ?true : false, false, Session.INACTIVE_STATE);
//            testSessionH.put(session.getSesionId(), session);
//        }
//        temp = (GregorianCalendar) GregorianCalendar.getInstance();
//        temp.set(Calendar.HOUR, 11);
//        temp.set(Calendar.MINUTE, 07);
//        session = new Session("ZM110", "Sesión n: " + 110, "Jostin Castro", "Sesiones de prueba", "http://google.com", "Zoom",
//                    "Prueba", temp, 30, 25, true, false, Session.INACTIVE_STATE);
//        session.addUser(user.getEmail(), true);
//        testSessionH.put(session.getSesionId(), session);
//        
//        FilesLoader.updateNotifications(testArray);
//        FilesLoader.updateHashMap((byte) 0, testUserH);
//        FilesLoader.updateHashMap((byte) 1, testSessionH);

        //ACÁ TERMINA PARTE PARA REPARAR LOS ARCHIVOS
        

        //ESTO MUESTRA TODO LO QUE TENGAN LOS ARCHIVOS PARA VERIFICAR SI ESTÁ ALMACENANDO BIEN
        
//        ArrayList<Notification> testArray = FilesLoader.loadNotifications();
//        for (Notification temp: testArray) {
//            System.out.println(temp.getUserId() + " " + temp.getMessage());
//        }
//        
//        HashMap<String, User> users = FilesLoader.loadUsers();
//        for (String string : users.keySet()) {
//            System.out.println(string);
//        }
//        
//        HashMap<String, Session> session = FilesLoader.loadSessions();
//        
//        for (String string : session.keySet()) {
//            System.out.println(string);
//        }
       
    }

}
