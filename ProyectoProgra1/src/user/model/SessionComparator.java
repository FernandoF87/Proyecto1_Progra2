/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package user.model;

import java.util.Comparator;
import server.model.Session;

/**
 *
 * @version
 * @author Jostin Castro
 */
public class SessionComparator implements Comparator<Session> {

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
