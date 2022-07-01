/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package user.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import server.model.Session;

/**
 * Custom model used to the different tabs in the MainFrame frame. 
 * @version 29/06/2022
 * @author Jostin Castro
 */
public class SessionTableModel extends DefaultTableModel {
    
    /**
     * The pre-defined column names.
     */
    
    private static final String[] columnNames = {
        "ID", "Categoria", "Tema", "Expositor", "Fecha", "Hora", "Duración", "Plataforma", "Tipo"
    };
    
    /**
     * A vector with all the table sessions. 
     */
    private Vector<Session> sessions;
    
    /**
     * Class contructor, only puts the different column names in the table.
     */
    
    public SessionTableModel() {
        super(columnNames, 0);
        
    }
    
    /**
     * Method that fills all the table.
     * @param sessions a Vector with the information to fill. 
     */
    
    public void fillData(Vector<Session> sessions) {
        this.sessions = sessions;
        sessions.sort(new SessionComparator());
        if (super.getRowCount() < sessions.size()) {
            super.setRowCount(sessions.size());
        }
        for (int i = 0; i < sessions.size(); i++) {
            Session temp = sessions.get(i);
            super.setValueAt(temp.getSesionId(), i, 0);
            super.setValueAt(temp.getCategory(), i, 1);
            super.setValueAt(temp.getTopic(), i, 2);
            super.setValueAt(temp.getExpositor(), i, 3);
            GregorianCalendar dateInfo = temp.getDate();
            String date = dateInfo.get(Calendar.DAY_OF_MONTH) + " - " + (dateInfo.get(Calendar.MONTH) + 1)  +
                    " - " + dateInfo.get(Calendar.YEAR);
            String time = dateInfo.get(Calendar.HOUR) + ":" + dateInfo.get(Calendar.MINUTE) + ((dateInfo.get(Calendar.AM_PM) == 0) ? "AM": "PM");
            super.setValueAt(date, i, 4);
            super.setValueAt(time, i, 5);
            super.setValueAt(temp.getDuration(), i, 6);
            super.setValueAt(temp.getPlatform(), i, 7);
            super.setValueAt((temp.isOpen()) ? "Abierta" : "Cerrada", i, 8);
        }
    }
    
    /**
     * Method used to show the information of the selected session on a tab.
     * @param rowIndex the selected session index.
     * @return the selected session object.
     */
    
    public Session getSelectedSession(int rowIndex) {
        return sessions.get(rowIndex);
    }

}
