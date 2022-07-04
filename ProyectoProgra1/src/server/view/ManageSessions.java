/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package server.view;

import java.time.Instant;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import server.model.Data;
import server.model.Session;

/**
 * It is responsible for modifying the data of the received session
 *
 * @version 4/7/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class ManageSessions extends javax.swing.JDialog {

    /**
     * Creates new form ManageSessions
     */
    private final Session session;
    private static Data data;
    private String message;

    public ManageSessions(javax.swing.JFrame parent, boolean modal, Data data, Session session) {
        initComponents();
        setLocationRelativeTo(parent);

        this.data = data;
        this.session = session;
        setValues();
        setDate();
    }

    /**
     * Sets the values of the date and time, with those of the session date
     */
    private void setDate() {
        Date actualDate = Date.from(Instant.now());
        chooser.setDate(session.getDate().getTime());
        chooser.setMinSelectableDate(new java.util.Date(actualDate.getTime()));

        spnHour.setModel(new javax.swing.SpinnerNumberModel(session.getDate().getTime().getHours(), session.getDate().getTime().getHours(), 23, 1));
        spnMinutes.setModel(new javax.swing.SpinnerNumberModel(session.getDate().getTime().getMinutes(), session.getDate().getTime().getMinutes(), 59, 1));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCategory = new javax.swing.JTextField();
        txtTopic = new javax.swing.JTextField();
        txtExpositor = new javax.swing.JTextField();
        txtDetail = new javax.swing.JTextField();
        cbxPlatform = new javax.swing.JComboBox<>();
        txtLink = new javax.swing.JTextField();
        rbtnOpen = new javax.swing.JRadioButton();
        rbtnClose = new javax.swing.JRadioButton();
        btnSaveSession = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        spnHour = new javax.swing.JSpinner();
        spnMinutes = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spnDuratin = new javax.swing.JSpinner();
        spnAmount = new javax.swing.JSpinner();
        chooser = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        lblEnrrolledUsers = new javax.swing.JLabel();
        btnAceptUsers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Nueva Sesion");
        setModal(true);
        setSize(new java.awt.Dimension(0, 0));

        jLabel2.setText("Categoria");

        jLabel3.setText("Tema");

        jLabel4.setText("Expositor");

        jLabel6.setText("Detalle");

        jLabel7.setText("Fecha");

        jLabel8.setText("Hora");

        jLabel9.setText("Duracion");

        jLabel10.setText("Plataforma");

        jLabel11.setText("Enlace");

        jLabel12.setText("Cupo");

        jLabel13.setText("Tipo");

        jLabel14.setFont(new java.awt.Font("Lucida Handwriting", 0, 48)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Modificar Sesion");
        jLabel14.setToolTipText("");
        jLabel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtCategory.setToolTipText("La cetegoria no debe tener caracteres especiales(*-/@#...)");

        txtTopic.setToolTipText("El tema no debe tener caracteres especiales(*-/@#...)");

        txtExpositor.setToolTipText("El nombre del expositor no debe tener caracteres especiales(*-/@#...)");

        txtDetail.setToolTipText("El detalle no debe tener caracteres especiales(*-/@#...)");

        cbxPlatform.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxPlatform.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teams", "Zoom", "Google Meet", "Skype" }));
        cbxPlatform.setToolTipText("Seleccione la plataforma de la lista");
        cbxPlatform.setAutoscrolls(true);

        txtLink.setToolTipText("Ingrese el nuevo link de la sesion");

        buttonGroup1.add(rbtnOpen);
        rbtnOpen.setSelected(true);
        rbtnOpen.setText("Abierta");
        rbtnOpen.setToolTipText("Seleccione si es una sesion abierta o cerrada");

        buttonGroup1.add(rbtnClose);
        rbtnClose.setText("Cerrada");
        rbtnClose.setToolTipText("Seleccione si es una sesion abierta o cerrada");

        btnSaveSession.setText("Guardar");
        btnSaveSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSessionActionPerformed(evt);
            }
        });

        btnDelete.setText("Borrar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExit.setText("Salir");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        spnHour.setToolTipText("Ingrese la hora usando las flechas");
        spnHour.setValue(00);

        spnMinutes.setToolTipText("Ingrese los minutos usando las flechas");

        jLabel1.setText("HH:");

        jLabel5.setText("mm");

        spnDuratin.setModel(new javax.swing.SpinnerNumberModel(30, 30, null, 1));
        spnDuratin.setToolTipText("Ingrese la duracion usando las flechas");

        spnAmount.setModel(new javax.swing.SpinnerNumberModel(5, 5, 30, 1));
        spnAmount.setToolTipText("Ingrese el nuevo cupo usando las flechas");

        chooser.setToolTipText("Ingrese la fecha usando el boton");
        chooser.setDateFormatString("d MMM yyyy");
        chooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chooserPropertyChange(evt);
            }
        });

        jLabel15.setText("Cantidad de usuarios inscritos");

        lblEnrrolledUsers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAceptUsers.setText("Aceptar usuarios");
        btnAceptUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSaveSession, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbtnOpen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbtnClose)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLink, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtExpositor, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtTopic, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCategory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(spnHour, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(17, 17, 17)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(spnMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                                    .addComponent(cbxPlatform, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5))
                                            .addComponent(txtDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(70, 70, 70))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnDuratin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(spnAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblEnrrolledUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnAceptUsers))
                                    .addComponent(chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(12, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTopic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtExpositor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spnHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(spnDuratin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtLink, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblEnrrolledUsers, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(spnAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAceptUsers, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(rbtnOpen)
                    .addComponent(rbtnClose))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveSession, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed
    /**
     * Delete the session from the list of sessions and send the notification
     *
     * @param evt
     */
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        data.deleteSession(session.getSesionId());
        message = "Borrada";
        dispose();

    }//GEN-LAST:event_btnDeleteActionPerformed
    /**
     * Set the values of the fields, with the data of the session
     */
    public void setValues() {
        txtCategory.setText(session.getCategory());
        txtDetail.setText(session.getDetail());
        txtLink.setText(session.getLink());
        txtTopic.setText(session.getTopic());
        txtExpositor.setText(session.getExpositor());
        if (session.isOpen()) {
            rbtnOpen.setSelected(true);
            btnAceptUsers.setVisible(false);
        } else {
            rbtnClose.setSelected(true);
            btnAceptUsers.setVisible(true);
        }

        String platform = session.getPlatform().toLowerCase();
        int indexPlatfor = 0;
        switch (platform) {
            case "teams" ->
                indexPlatfor = 0;
            case "zoom" ->
                indexPlatfor = 1;
            case "google meet" ->
                indexPlatfor = 2;
            case "skype" ->
                indexPlatfor = 3;
        }
        cbxPlatform.setSelectedIndex(indexPlatfor);
        spnDuratin.setValue(session.getDuration());
        spnAmount.setValue(session.getCapacity());
        String enrolledUsers = "" + session.getParticipantList().size();
        enrolledUsers += "/" + spnAmount.getValue();
        lblEnrrolledUsers.setText(enrolledUsers);
    }

    /**
     *
     * Save the modified data of the session
     *
     * @param evt
     */
    private void btnSaveSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSessionActionPerformed
        GregorianCalendar date = new GregorianCalendar(chooser.getDate().getYear() + 1900, chooser.getDate().getMonth(),
                chooser.getDate().getDate(), (int) spnHour.getModel().getValue(),
                (int) spnMinutes.getModel().getValue());
        switch (cbxPlatform.getSelectedIndex()) {
            case 0:
                data.searchSessionId(session.getSesionId()).setPlatform("Teams");
                break;
            case 1:
                data.searchSessionId(session.getSesionId()).setPlatform("Zoom");
                break;
            case 2:
                data.searchSessionId(session.getSesionId()).setPlatform("Google Meet");
                break;
            case 3:
                data.searchSessionId(session.getSesionId()).setPlatform("Skype");
                break;

        }
        data.searchSessionId(session.getSesionId()).setCategory(txtCategory.getText());
        data.searchSessionId(session.getSesionId()).setTopic(txtTopic.getText());
        data.searchSessionId(session.getSesionId()).setExpositor(txtExpositor.getText());
        data.searchSessionId(session.getSesionId()).setDetail(txtDetail.getText());
        data.searchSessionId(session.getSesionId()).setDate(date);
        data.searchSessionId(session.getSesionId()).setDuration((int) spnDuratin.getValue());
        data.searchSessionId(session.getSesionId()).setPlatform(cbxPlatform.toString());
        data.searchSessionId(session.getSesionId()).setLink(txtLink.getText());
        data.searchSessionId(session.getSesionId()).setCapacity((int) spnAmount.getValue());
        if (rbtnOpen.isSelected()) {
            data.searchSessionId(session.getSesionId()).setOpen(true);
        } else {
            data.searchSessionId(session.getSesionId()).setOpen(false);
        }
        AdminMessageDialog.showMessageDialog("Datos Guardados", "Datos guardados");
        message = "Modificada";

        AdminView.getServer().sendNotification(message, session);

    }//GEN-LAST:event_btnSaveSessionActionPerformed
    /**
     * Verify that the selected date is greater than the current date, if so,
     * set the current time as the minimum time
     *
     * @param evt
     */
    private void chooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chooserPropertyChange

        if (chooser.getDate() != null) {
            if (chooser.getDate().getTime() > Date.from(Instant.now()).getTime()) {
                spnHour.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
                spnMinutes.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

            } else {

                spnHour.setModel(new javax.swing.SpinnerNumberModel(Date.from(Instant.now()).getHours(), Date.from(Instant.now()).getHours(), 23, 1));
                spnMinutes.setModel(new javax.swing.SpinnerNumberModel(Date.from(Instant.now()).getMinutes(), Date.from(Instant.now()).getMinutes(), 59, 1));
            }
        }


    }//GEN-LAST:event_chooserPropertyChange
    /**
     * Shows the window of users pending approval, in case of a closed session
     *
     * @param evt
     */
    private void btnAceptUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptUsersActionPerformed
        AceptUsers aceptUsers = new AceptUsers((JFrame) this.getParent().getParent(), true, session);

        aceptUsers.setVisible(true);
        message = aceptUsers.getMsg();


    }//GEN-LAST:event_btnAceptUsersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptUsers;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSaveSession;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxPlatform;
    private com.toedter.calendar.JDateChooser chooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblEnrrolledUsers;
    private javax.swing.JRadioButton rbtnClose;
    private javax.swing.JRadioButton rbtnOpen;
    private javax.swing.JSpinner spnAmount;
    private javax.swing.JSpinner spnDuratin;
    private javax.swing.JSpinner spnHour;
    private javax.swing.JSpinner spnMinutes;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtDetail;
    private javax.swing.JTextField txtExpositor;
    private javax.swing.JTextField txtLink;
    private javax.swing.JTextField txtTopic;
    // End of variables declaration//GEN-END:variables
}
