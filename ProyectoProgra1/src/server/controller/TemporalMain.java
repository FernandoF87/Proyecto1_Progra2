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
//        HashMap<String, Session> testSessionH = new HashMap();
//        Session session = new Session("test", "test", "Jostin Castro", "Esto es una prueba", "dksdjfkd.com", "Zoom", 
//            "Prueba", new GregorianCalendar(), new GregorianCalendar(), 5, 25, false);
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
