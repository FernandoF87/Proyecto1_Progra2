/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package user.view;

import java.awt.Cursor;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import server.model.Notification;
import user.model.NotificationTableModel;

/**
 * An basic dialog to show all the notifications receipted.
 * @version 30/06/2022
 * @author Jostin Castro
 */
public class NotificationsDialog extends javax.swing.JDialog {

    /**
     * Creates new form NotificationsDialog
     */
    public NotificationsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbNotifications = new javax.swing.JTable();
        btClose = new javax.swing.JButton();

        setTitle("Notificaciones");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tbNotifications.setModel(new NotificationTableModel());
        tbNotifications.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNotificationsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNotifications);

        btClose.setText("Cerrar");
        btClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btClose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * If the button close is clicked, puts the dialog visibility in false.
     * @param evt 
     */
    private void btCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCloseActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        ((MainFrame) getParent()).setSelectedOption((byte) ( (MainFrame) getParent()).getCurrentTab());
        ((MainFrame) getParent()).resetComponents();
    }//GEN-LAST:event_btCloseActionPerformed

    /**
     * If a notification is clicked, a notification dialog is open.
     * @param evt the click event.
     */
    private void tbNotificationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNotificationsMouseClicked
        // TODO add your handling code here:
        NotificationTableModel model = (NotificationTableModel) tbNotifications.getModel();
        Notification temp = model.getSelectedNotification(tbNotifications.getSelectedRow());
        NotificationDetails details = new NotificationDetails(null, false, temp);
        details.setVisible(true);
    }//GEN-LAST:event_tbNotificationsMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        ((MainFrame) getParent()).setReadedNotification(true);
    }//GEN-LAST:event_formWindowActivated

    /**
     * Method used to fill the table with the notifications.
     * @param notifications a LinkedList with all the notification to put.
     */
    
    public void loadNotifications(LinkedList<Notification> notifications) {
        final byte DEFAULT_NOTIFICATION = -1;
        final String DEFAULT_MESSAGE = "No tiene notificaciones";
        
        NotificationTableModel model = (NotificationTableModel) tbNotifications.getModel();
        if (notifications.size() > 0) {
            //If the notification in the first position is a default notification, it erases it.
            if (notifications.get(0).getType() == DEFAULT_NOTIFICATION) {
                notifications.poll();
                model.setRowCount(notifications.size());
            }
            if (notifications.size() != model.getRowCount()) {
                model.setRowCount(notifications.size());
            }
            //Fill the table with the notifications.
            for (int i = 0; i < notifications.size(); i++) {
                model.setValueAt(notifications.get(i).getMessage(), i, 0);
            }
        } else {
            //In case of receipt and size 0 LinkedList, it puts a default notication.
            Notification temp = new Notification(null, DEFAULT_MESSAGE, true); 
            model.setRowCount(1);
            model.setValueAt(temp.getMessage(), 0, 0);
            temp.setType(DEFAULT_NOTIFICATION);
            notifications.add(temp);
            
        }
        model.setNotifications(notifications);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbNotifications;
    // End of variables declaration//GEN-END:variables
}
