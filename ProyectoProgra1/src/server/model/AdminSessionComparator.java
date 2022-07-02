/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package server.model;

import user.model.*;
import java.util.Comparator;
import server.model.Session;

/**
 * Class used to sort the different sessions on the differents tables in the MainFrame frame.
 * @version 28/06/2022
 * @author Jostin Castro
 */
public class AdminSessionComparator implements Comparator<Session> {

    /**
     * Method used to compare a session date to another.
     * @param o1 the first session to compare.
     * @param o2 the second session to compare.
     * @return an integer, <b>-1</b> if the first is less than the second, <b>1</b> if the first is major than
     * the second, <b>0</b> if both are equals.
     */
    
    @Override
    public int compare(Session o1, Session o2) {
        if (o1.getDate().compareTo(o2.getDate()) < 0) {
            return -1;
        } else if (o1.getDate().compareTo(o2.getDate()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
