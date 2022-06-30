/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package user.view;

import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.table.DefaultTableModel;
import server.model.Session;
import user.model.SessionTableModel;


/**
 *
 * @author Jostin Castro
 */
public class MainFrame extends javax.swing.JFrame {

    private byte selectedOption;
    private String sessionId;
    
    public final static byte AVAILABLE_TAB = 0;
    public final static byte ENROLLED_TAB = 1;
    public final static byte HISTORY_TAB = 2;
    public final static byte NOTIFICATION_OPTION = 3;
    public final static byte ENROLL_SESSION = 4;
    public final static byte CANCEL_ENROLL_SESSION = 5;
    public final static byte LOGIN_OUT = 6;
    
    private boolean readedNotification;
    private final String NOTIFICATION_AUDIO = "src/img/new_notification_sound.wav";
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame(String username) {
        initComponents();
        this.setLocationRelativeTo(null);
        lbWelcome.setText(lbWelcome.getText() + " " + username);
        selectedOption = AVAILABLE_TAB;
        disableComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btLogout = new javax.swing.JButton();
        btNotifications = new javax.swing.JButton();
        tbControls = new javax.swing.JTabbedPane();
        pnAvailableSessions = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAvailableSessions = new javax.swing.JTable();
        pnRegisteredSessions = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbEnrolleddSessions = new javax.swing.JTable();
        pnHistorySession = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSessionHistory = new javax.swing.JTable();
        lbWelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        btLogout.setText("Cerrar sesión");
        btLogout.setFocusable(false);
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });

        btNotifications.setText("Notificaciones");
        btNotifications.setFocusable(false);
        btNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNotificationsActionPerformed(evt);
            }
        });

        tbControls.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbControlsStateChanged(evt);
            }
        });

        tbAvailableSessions.setModel(new SessionTableModel());
        tbAvailableSessions.getTableHeader().setReorderingAllowed(false);
        tbAvailableSessions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAvailableSessionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAvailableSessions);
        tbAvailableSessions.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout pnAvailableSessionsLayout = new javax.swing.GroupLayout(pnAvailableSessions);
        pnAvailableSessions.setLayout(pnAvailableSessionsLayout);
        pnAvailableSessionsLayout.setHorizontalGroup(
            pnAvailableSessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnAvailableSessionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnAvailableSessionsLayout.setVerticalGroup(
            pnAvailableSessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnAvailableSessionsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbControls.addTab("Sesiones disponibles", pnAvailableSessions);

        tbEnrolleddSessions.setModel(new SessionTableModel());
        tbEnrolleddSessions.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbEnrolleddSessions);
        tbEnrolleddSessions.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout pnRegisteredSessionsLayout = new javax.swing.GroupLayout(pnRegisteredSessions);
        pnRegisteredSessions.setLayout(pnRegisteredSessionsLayout);
        pnRegisteredSessionsLayout.setHorizontalGroup(
            pnRegisteredSessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnRegisteredSessionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnRegisteredSessionsLayout.setVerticalGroup(
            pnRegisteredSessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnRegisteredSessionsLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbControls.addTab("Sesiones inscritas", pnRegisteredSessions);

        tbSessionHistory.setModel(new SessionTableModel());
        tbSessionHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbSessionHistory);
        tbSessionHistory.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout pnHistorySessionLayout = new javax.swing.GroupLayout(pnHistorySession);
        pnHistorySession.setLayout(pnHistorySessionLayout);
        pnHistorySessionLayout.setHorizontalGroup(
            pnHistorySessionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnHistorySessionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnHistorySessionLayout.setVerticalGroup(
            pnHistorySessionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHistorySessionLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbControls.addTab("Historial de sesiones", pnHistorySession);

        lbWelcome.setText("Bienvenido,");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbControls)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btNotifications)
                .addGap(18, 18, 18)
                .addComponent(btLogout)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLogout)
                    .addComponent(btNotifications)
                    .addComponent(lbWelcome))
                .addGap(18, 18, 18)
                .addComponent(tbControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNotificationsActionPerformed
        selectedOption = NOTIFICATION_OPTION;
        disableComponents();
    }//GEN-LAST:event_btNotificationsActionPerformed

    private void tbAvailableSessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAvailableSessionsMouseClicked
        disableComponents();
        SessionTableModel model = getTableModel(selectedOption);
        SessionDetails details = new SessionDetails(this, false, selectedOption, model.getSelectedSession());
        details.setVisible(true);
    }//GEN-LAST:event_tbAvailableSessionsMouseClicked

    private void tbControlsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbControlsStateChanged
        // TODO add your handling code here:
        selectedOption = (byte) tbControls.getSelectedIndex();
        disableComponents();
    }//GEN-LAST:event_tbControlsStateChanged

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        // TODO add your handling code here:
        selectedOption = LOGIN_OUT;
        disableComponents();
    }//GEN-LAST:event_btLogoutActionPerformed

    public void writeData(byte table, Vector<Session> data) {
        SessionTableModel model = getTableModel(table);
        model.fillData(data);
        resetComponents();
    }
    
    private SessionTableModel getTableModel(byte table) {
        switch(table) {
            case AVAILABLE_TAB:
                return (SessionTableModel) tbAvailableSessions.getModel();
            case ENROLLED_TAB:
                return (SessionTableModel) tbEnrolleddSessions.getModel();
            case HISTORY_TAB:
                return (SessionTableModel) tbEnrolleddSessions.getModel();
        }
        return null;
    }
    
    public byte getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(byte selectedOption) {
        this.selectedOption = selectedOption;
    }
    
    public void manageNewNotification() {
        readedNotification = false;
        File file = new File(NOTIFICATION_AUDIO);
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        
        Color buttonColor = btNotifications.getBackground();
        Runnable task = () -> {
            while (!readedNotification) {
                btNotifications.setBackground(Color.red);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                btNotifications.setBackground(buttonColor);                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };
        new Thread(task).start();
    }
    
    public void resetComponents() {
        tbControls.setEnabled(true);
        tbAvailableSessions.setEnabled(true);
        tbEnrolleddSessions.setEnabled(true);
        tbSessionHistory.setEnabled(true);
        btLogout.setEnabled(true);
        btNotifications.setEnabled(true);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    private void disableComponents() {
        tbControls.setEnabled(false);
        tbAvailableSessions.setEnabled(false);
        tbEnrolleddSessions.setEnabled(false);
        tbSessionHistory.setEnabled(false);
        btLogout.setEnabled(false);
        btNotifications.setEnabled(false);
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btNotifications;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbWelcome;
    private javax.swing.JPanel pnAvailableSessions;
    private javax.swing.JPanel pnHistorySession;
    private javax.swing.JPanel pnRegisteredSessions;
    private javax.swing.JTable tbAvailableSessions;
    private javax.swing.JTabbedPane tbControls;
    private javax.swing.JTable tbEnrolleddSessions;
    private javax.swing.JTable tbSessionHistory;
    // End of variables declaration//GEN-END:variables
}
