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
 *
 * @version
 * @author Jostin Castro
 */
public class SessionTableModel extends DefaultTableModel {
    
    private static final String[] columnNames = {
        "ID", "Categoria", "Tema", "Expositor", "Fecha", "Hora", "Duración", "Plataforma", "Tipo"
    };
    private Vector<Session> sessions;
    
    public SessionTableModel() {
        super(columnNames, 0);
        
    }
    
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
    
    public Session getSelectedSession(int rowIndex) {
        return sessions.get(rowIndex);
    }

}
