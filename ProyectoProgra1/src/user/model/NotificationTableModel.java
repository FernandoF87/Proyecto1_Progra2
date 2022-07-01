/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package user.model;

import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import server.model.Notification;

/**
 * Model used in the notifications dialog. Permit obtain the selected notification.
 * @version 29/06/2022
 * @author Jostin Castro
 */
public class NotificationTableModel extends DefaultTableModel {
    
    /**
     * A linkd list with all the user notifications.
     */
    private LinkedList<Notification> notifications;
    
    /**
     * The column name of the table.
     */
    private static String[] columnName = {"Vista previa"};
    
    /**
     * Empty contructor, only set the column name of the table.
     */
    public NotificationTableModel() {
        super(columnName, 0);
        
    }
    /**
     * Method used to return the selected notification from the NotificationDialog.
     * @param rowIndex the selected row in the table.
     * @return the selected notification.
     */
    
    public Notification getSelectedNotification(int rowIndex) {
        return notifications.get(rowIndex);
    }
    
    /**
     * Sets the linked list of notifications to the table model.
     * @param notifications the linked list of notifications.
     */
    public void setNotifications(LinkedList<Notification> notifications) {
        this.notifications = notifications;
    }

}
