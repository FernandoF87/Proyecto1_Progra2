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
import model.User;
import model.Session;

public class TemporalMain {
    
    public static void main(String[] args) {
        //SOLO PARA PRUEBAS
        
        
        
        
        HashMap<String, User> users = FilesLoader.loadUsers();
        for (String string : users.keySet()) {
            System.out.println(string);
        }
        
        HashMap<String, Session> sessionHash = FilesLoader.loadSessions();
        
        for (String string : sessionHash.keySet()) {
            System.out.println(string);
        }
       
    }

}
