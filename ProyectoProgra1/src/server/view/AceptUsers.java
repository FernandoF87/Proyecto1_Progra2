/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package server.view;

import javax.swing.DefaultListModel;
import server.model.Session;

/**
 *
 * @author Joshua Mora Garita
 */
public class AceptUsers extends javax.swing.JDialog {

    /**
     * Creates new form AceptUsers
     */
    private Session session;
    private String msg;
    DefaultListModel model = new DefaultListModel();

    public AceptUsers(java.awt.Frame parent, boolean modal, Session session) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.session = session;

        setValues();

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
        jListParticipants = new javax.swing.JList();
        btnAcept = new javax.swing.JButton();
        btnDenied = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        jScrollPane1.setViewportView(jListParticipants);

        btnAcept.setText("Aceptar");
        btnAcept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptActionPerformed(evt);
            }
        });

        btnDenied.setText("Denegar");
        btnDenied.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeniedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 39, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAcept, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnDenied, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDenied, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAcept, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeniedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeniedActionPerformed
        String userId = String.valueOf(jListParticipants.getSelectedValue());
        session.deleteWaitingUser(userId);
        msg = "Denegado";
        model.remove(jListParticipants.getSelectedIndex());
    }//GEN-LAST:event_btnDeniedActionPerformed

    private void btnAceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptActionPerformed
        String userId = String.valueOf(jListParticipants.getSelectedValue());
        session.addUser(userId, true);
        msg = "Aceptado";
        model.remove(jListParticipants.getSelectedIndex());

    }//GEN-LAST:event_btnAceptActionPerformed

    public void setValues() {

        jListParticipants.setModel(model);

        if (session.getWaitingParticipantsList() != null) {
            for (int i = 0; i < session.getWaitingParticipantsList().size(); i++) {
                model.addElement(session.getWaitingParticipantsList().get(i));
            }
        }
    }

    public String getMsg() {
        return msg;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcept;
    private javax.swing.JButton btnDenied;
    private javax.swing.JList jListParticipants;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
