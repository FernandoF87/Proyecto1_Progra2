package server.model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * @version @author Joshua Mora Garita
 */
public class CustomListModel extends AbstractListModel {

    private ArrayList<Session> listArray = new ArrayList<>();

    @Override
    public int getSize() {
        return listArray.size();
    }

    @Override
    public Object getElementAt(int index) {
        Session s = listArray.get(index);
        return s.getSesionId();
    }

    public void addSessio(Session s) {
        listArray.add(s);
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
