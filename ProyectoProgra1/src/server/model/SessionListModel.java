package server.model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * This class extends AbstractListModel and is in charge of managing the
 * sessions in a JList
 *
 * @version 4/7/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class SessionListModel extends AbstractListModel {

    private final ArrayList<Session> listArray = new ArrayList<>();

    @Override
    public int getSize() {
        return listArray.size();
    }

    @Override
    public Object getElementAt(int index) {
        Session s = listArray.get(index);
        return s.getSesionId();
    }

    public void addSession(Session s) {
        listArray.add(s);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }

    public void addSession(int index, Session s) {
        listArray.add(index, s);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }

    public void deleteSession(int index) {
        listArray.remove(index);
        this.fireIntervalRemoved(index, getSize(), getSize() + 1);
    }

    public Session getSession(int index) {
        return listArray.get(index);
    }

}
