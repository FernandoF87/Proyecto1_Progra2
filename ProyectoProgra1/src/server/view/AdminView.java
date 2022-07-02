/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package server.view;

import java.util.HashMap;
import javax.swing.JOptionPane;
import server.model.Data;
import server.model.Server;
import server.model.Session;
import server.model.SessionListModel;
import server.model.User;

/**
 *
 * @author Joshua Mora Garita
 */
public class AdminView extends javax.swing.JFrame {

    /**
     * Probando pull Creates new form AdminView
     */
    private SessionListModel sessionModel;
    private UserListModel userModel;
    private static Data data;
    private static Server server;

    public AdminView(javax.swing.JFrame parent, boolean modal, Data data, Server server) {
        views();
        initComponents();
        setLocationRelativeTo(null);
        jListArea.setVisible(false);
        btnDeleteSession.setVisible(false);
        btnModify.setVisible(false);
        btnSessionDetails.setVisible(false);
        this.data = data;
        this.server = server;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCreateSession = new javax.swing.JButton();
        btnManageSessions = new javax.swing.JButton();
        btnShowSessions = new javax.swing.JButton();
        btnShowUsersList = new javax.swing.JButton();
        jListArea = new javax.swing.JList();
        btnDeleteSession = new javax.swing.JButton();
        btnModify = new javax.swing.JButton();
        btnSessionDetails = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ModoAdmin.jpeg"))); // NOI18N

        btnCreateSession.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCreateSession.setText("Crear Nueva Sesion");
        btnCreateSession.setBorder(null);
        btnCreateSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateSessionActionPerformed(evt);
            }
        });

        btnManageSessions.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnManageSessions.setText("Administrar Sesiones");
        btnManageSessions.setBorder(null);
        btnManageSessions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageSessionsActionPerformed(evt);
            }
        });

        btnShowSessions.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnShowSessions.setText("Lista de sesiones");
        btnShowSessions.setToolTipText("");
        btnShowSessions.setBorder(null);
        btnShowSessions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowSessionsActionPerformed(evt);
            }
        });

        btnShowUsersList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnShowUsersList.setText("Lista de usuarios");
        btnShowUsersList.setBorder(null);
        btnShowUsersList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowUsersListActionPerformed(evt);
            }
        });

        jListArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jListArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jListArea.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListArea.setFocusable(false);
        jListArea.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);

        btnDeleteSession.setText("Eliminar");
        btnDeleteSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSessionActionPerformed(evt);
            }
        });

        btnModify.setText("Modificar");
        btnModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyActionPerformed(evt);
            }
        });

        btnSessionDetails.setText("Ver Sesion");
        btnSessionDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSessionDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnShowUsersList, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowSessions, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateSession, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageSessions, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDeleteSession, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSessionDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jListArea, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnManageSessions, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnCreateSession, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnShowSessions, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnShowUsersList, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(61, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jListArea, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeleteSession, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModify, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSessionDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//
    private void btnCreateSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateSessionActionPerformed
        CreateSessions sessions = new CreateSessions(this, true, data);

        sessions.setVisible(true);

    }//GEN-LAST:event_btnCreateSessionActionPerformed

    private void btnManageSessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageSessionsActionPerformed
        sessionModel = new SessionListModel();
        jListArea.setModel(sessionModel);
        jListArea.setVisible(true);
        for (HashMap.Entry<String, Session> session : data.getSessions().entrySet()) {

            for (int i = 0; i < sessionModel.getSize(); i++) {
                if (sessionModel.getSession(i) != null) {
                    if (sessionModel.getSession(i).getDate().getTimeInMillis() > session.getValue().getDate().getTimeInMillis()) {
                        System.out.println("ADMINVIEW");
                        sessionModel.addSession(i + 1, session.getValue());
                        break;
                    }
                } else {
                    sessionModel.addSession(session.getValue());
                    break;
                }
            }
        }
        btnDeleteSession.setVisible(true);
        btnModify.setVisible(true);
        btnSessionDetails.setVisible(false);


    }//GEN-LAST:event_btnManageSessionsActionPerformed

    private void btnShowSessionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowSessionsActionPerformed
        sessionModel = new SessionListModel();
        jListArea.setModel(sessionModel);
        jListArea.setVisible(true);
        for (HashMap.Entry<String, Session> session : data.getSessions().entrySet()) {
            for (int i = 0; i < sessionModel.getSize(); i++) {
                if (sessionModel.getSession(i) != null) {
                    if (sessionModel.getSession(i).getDate().getTimeInMillis() > session.getValue().getDate().getTimeInMillis()) {

                        sessionModel.addSession(i + 1, session.getValue());
                        break;
                    }
                } else {
                    sessionModel.addSession(session.getValue());
                    break;
                }
            }

        }
        btnDeleteSession.setVisible(false);
        btnModify.setVisible(false);
        btnSessionDetails.setVisible(true);

    }//GEN-LAST:event_btnShowSessionsActionPerformed

    private void btnShowUsersListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowUsersListActionPerformed
        userModel = new UserListModel();
        jListArea.setModel(userModel);
        jListArea.setVisible(true);
        for (HashMap.Entry<String, User> user : data.getUsers().entrySet()) {
            userModel.addUser(user.getValue());
        }
        btnDeleteSession.setVisible(false);
        btnModify.setVisible(false);
        btnSessionDetails.setVisible(false);
    }//GEN-LAST:event_btnShowUsersListActionPerformed

    private void btnModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyActionPerformed
        int index = -2;
        index = jListArea.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado una sesion o no hay sesiones en la lista", "Error", NORMAL);
            return;
        }
        if (index == -2) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna sesion", "Error", NORMAL);
            return;
        }
        Session session = sessionModel.getSession(index);

        ManageSessions manageSessions = new ManageSessions(this, true, data, session);

        manageSessions.setVisible(true);
        server.sendNotification(manageSessions.getMessage(), session);

    }//GEN-LAST:event_btnModifyActionPerformed

    private void btnDeleteSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSessionActionPerformed
        int index = -2;
        index = jListArea.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado una sesion o no hay sesiones en la lista", "Error", NORMAL);
            return;
        }
        if (index == -2) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna sesion", "Error", NORMAL);
            return;
        }
        data.deleteSession(sessionModel.getSession(index).getSesionId());
        sessionModel.deleteSession(index);

    }//GEN-LAST:event_btnDeleteSessionActionPerformed

    private void btnSessionDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSessionDetailsActionPerformed
        int index = -2;
        index = jListArea.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "No ha seleccionado una sesion o no hay sesiones en la lista", "Error", NORMAL);
            return;
        }
        if (index == -2) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna sesion", "Error", NORMAL);
            return;
        }
        Session session = sessionModel.getSession(index);

        SessionDetail sessionDetail = new SessionDetail(this, true, data, session);
        sessionDetail.setVisible(true);
    }//GEN-LAST:event_btnSessionDetailsActionPerformed

    private void views() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateSessions.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateSessions.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateSessions.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateSessions.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateSession;
    private javax.swing.JButton btnDeleteSession;
    private javax.swing.JButton btnManageSessions;
    private javax.swing.JButton btnModify;
    private javax.swing.JButton btnSessionDetails;
    private javax.swing.JButton btnShowSessions;
    private javax.swing.JButton btnShowUsersList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jListArea;
    // End of variables declaration//GEN-END:variables
}
