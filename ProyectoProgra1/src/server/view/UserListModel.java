package server.view;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import server.model.User;

/**
 * @version @author Joshua Mora Garita
 */
public class UserListModel extends AbstractListModel {

    private final ArrayList<User> listArray = new ArrayList<>();

    @Override
    public int getSize() {
        return listArray.size();
    }

    @Override
    public Object getElementAt(int index) {
        User user = listArray.get(index);
        return user.getUserID();
    }

    public void addUser(User s) {
        listArray.add(s);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }

    public void deleteUser(int index) {
        listArray.remove(index);
        this.fireIntervalRemoved(index, getSize(), getSize() + 1);
    }

    public User getItem(int index) {
        return listArray.get(index);
    }

}
