/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package model;

/**
 *
 * @author Jostin Castro
 */
public class NotificationException extends Exception {

    /**
     * Creates a new instance of <code>NotificationException</code> without
     * detail message.
     */
    public NotificationException() {
    }

    /**
     * Constructs an instance of <code>NotificationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotificationException(String msg) {
        super(msg);
    }
}
