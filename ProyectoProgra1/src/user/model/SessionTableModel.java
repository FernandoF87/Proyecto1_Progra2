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
        if (sessions.size() == 0) {
            super.setRowCount(0);
            sessions = null;
            return;
        }
        this.sessions = sessions;
        sessions.sort(new SessionComparator());
        if (super.getRowCount() != sessions.size()) {
            super.setRowCount(sessions.size());
        }
        
        final byte SESSION_ID_COLUMN = 0;
        final byte SESSION_CATEGORY_COLUMN = 1;
        final byte SESSION_TOPIC_COLUMN = 2;
        final byte SESSION_EXPOSITOR_COLUMN = 3;
        final byte SESSION_DATE_COLUMN = 4;
        final byte SESSION_TIME_COLUMN = 5;
        final byte SESSION_DURATION_COLUMN = 6;
        final byte SESSION_PLATFORM_COLUMN = 7;
        final byte SESSION_TYPE_COLUMN = 8;
        
        for (int i = 0; i < sessions.size(); i++) {
            Session temp = sessions.get(i);
            super.setValueAt(temp.getSesionId(), i, SESSION_ID_COLUMN);
            super.setValueAt(temp.getCategory(), i, SESSION_CATEGORY_COLUMN);
            super.setValueAt(temp.getTopic(), i, SESSION_TOPIC_COLUMN);
            super.setValueAt(temp.getExpositor(), i, SESSION_EXPOSITOR_COLUMN);
            GregorianCalendar dateInfo = temp.getDate();
            String date = dateInfo.get(Calendar.DAY_OF_MONTH) + " - " + (dateInfo.get(Calendar.MONTH) + 1)  +
                    " - " + dateInfo.get(Calendar.YEAR);
            String time = dateInfo.get(Calendar.HOUR) + ":" + ((dateInfo.get(Calendar.MINUTE) < 10)? "0" + dateInfo.get(Calendar.MINUTE) :dateInfo.get(Calendar.MINUTE)) + 
                    ((dateInfo.get(Calendar.AM_PM) == Calendar.AM) ? "AM": "PM");
            super.setValueAt(date, i, SESSION_DATE_COLUMN);
            super.setValueAt(time, i, SESSION_TIME_COLUMN);
            super.setValueAt(temp.getDuration(), i, SESSION_DURATION_COLUMN);
            super.setValueAt(temp.getPlatform(), i, SESSION_PLATFORM_COLUMN);
            super.setValueAt((temp.isOpen()) ? "Abierta" : "Cerrada", i, SESSION_TYPE_COLUMN);
        }
    }
    /**
     * Permits add only one session to the table.
     * @param session the session to add.
     */
    public void addSession(Session session) {
        sessions.add(session);
        fillData(sessions);
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
