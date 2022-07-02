/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package user.view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import server.exceptions.NotificationException;
import server.model.Notification;

/**
 * Show a detailed view of a notification.
 * @author Jostin Castro
 */
public class NotificationDetails extends javax.swing.JDialog {

    /**
     * Creates new form NotificationDetails
     */
    public NotificationDetails(java.awt.Frame parent, boolean modal, Notification notification) {
        super(parent, modal);
        initComponents();
        this.setSize(500, this.getSize().height);
        this.setLocationRelativeTo(null);
        loadNotification(notification);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTypeNotification = new javax.swing.JLabel();
        tfTypeNotification = new javax.swing.JTextField();
        btClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDetails = new javax.swing.JTextArea();
        lbDescription = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalles de la notificación");
        setAlwaysOnTop(true);
        setResizable(false);

        lbTypeNotification.setText("Tipo de notificación: ");

        tfTypeNotification.setEditable(false);

        btClose.setText("Cerrar");
        btClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCloseActionPerformed(evt);
            }
        });

        taDetails.setEditable(false);
        taDetails.setColumns(20);
        taDetails.setRows(5);
        jScrollPane1.setViewportView(taDetails);

        lbDescription.setText("Descripción:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTypeNotification, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(tfTypeNotification)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btClose)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTypeNotification)
                    .addComponent(tfTypeNotification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btClose)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btCloseActionPerformed
    
    /**
     * Method used to charge and show detailed information of a notification.
     * @param notification the notification to put.
     */
    
    public void loadNotification(Notification notification) {
        final String START_LINK = "<html><p><a href=\""; //To show in a hyperlink format
        final String END_LINK = "</a></p></html>";
        final String MID_LINK = "\">";
        final String DEFAULT_MESSAGE = "** No hay información adicional **";
        tfTypeNotification.setText(notification.getMessage());
        if (notification.getType() == Notification.FIVE_MINUTES) {
            this.remove(taDetails); //Removed text area to put there a label with text, and a label with the link.
            this.remove(jScrollPane1);
            this.setSize(this.getSize().width, 250);
            Point point = lbDescription.getLocation();
            taDetails.setVisible(false);
            JLabel lb1 = new JLabel("Con este link podrás acceder a la sesión:");
            lb1.setBounds(150, point.y, 220, 20);
            lb1.setVisible(true);
            
            JLabel lbLink = new JLabel(START_LINK + notification.getExtraInfo() + MID_LINK + notification.getExtraInfo() +  END_LINK);
            lbLink.setBounds(150, point.y + 25, 220, 20);
            lbLink.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Desktop desktop = Desktop.getDesktop();
                            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                                URI url;
                                try {
                                    url = new URI(notification.getExtraInfo());
                                    desktop.browse(url);
                                } catch (URISyntaxException | IOException ex) {
                                    MessageDialog.showMessageDialog("No se puede abrir la dirección", "Error");
                                }
                                
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            lbLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            lbLink.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        }
                    }); //Adds a listener to the label.
            lbLink.setVisible(true);
            this.add(lb1);  //Add the two labels.
            this.add(lbLink);
            
        } else {
            //If theres not extra info in the notification, show a default message
            if (notification.getExtraInfo() == null) {
                taDetails.setText(DEFAULT_MESSAGE);
            } else {
                taDetails.setText(notification.getExtraInfo());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbTypeNotification;
    private javax.swing.JTextArea taDetails;
    private javax.swing.JTextField tfTypeNotification;
    // End of variables declaration//GEN-END:variables
}
