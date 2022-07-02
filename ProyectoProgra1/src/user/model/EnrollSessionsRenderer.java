/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package user.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import server.model.Session;

/**
 *
 * @version
 * @author Jostin Castro
 */
public class EnrollSessionsRenderer extends DefaultTableCellRenderer {
    private final Color DEFAULT_COLOR = getBackground();
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        SessionTableModel model = (SessionTableModel) table.getModel();
        Session session = model.getSelectedSession(row);
        if (!session.isOpen()) {
            if (!session.isNotifSent()) {
            setBackground(Color.CYAN);
            } else {
                setBackground(DEFAULT_COLOR);
            }
        } else {
            setBackground(DEFAULT_COLOR);
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

    
    
}
