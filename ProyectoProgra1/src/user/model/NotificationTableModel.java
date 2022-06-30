/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package user.model;

import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import server.model.Notification;

/**
 *
 * @version
 * @author Jostin Castro
 */
public class NotificationTableModel extends DefaultTableModel {
    
    private LinkedList<Notification> notifications;
    private static String[] columnName = {"Vista previa"};
    
    public NotificationTableModel() {
        super(columnName, 0);
        
    }
    
    public Notification getSelectedNotification(int rowIndex) {
        return notifications.get(rowIndex);
    }
    
    public void setNotifications(LinkedList<Notification> notifications) {
        this.notifications = notifications;
    }

}
