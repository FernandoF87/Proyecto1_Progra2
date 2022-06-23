/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package user.view;

/**
 *
 * @author Jostin Castro
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
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

        btLogout = new javax.swing.JButton();
        btNotifications = new javax.swing.JButton();
        tbControls = new javax.swing.JTabbedPane();
        pnAvailableSessions = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAvailableSessions = new javax.swing.JTable();
        pnRegisteredSessions = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbRegisteredSessions = new javax.swing.JTable();
        pnHistorySession = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSessionHistory = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");

        btLogout.setText("Cerrar sesión");

        btNotifications.setText("Notificaciones");
        btNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNotificationsActionPerformed(evt);
            }
        });

        tbAvailableSessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Categoria", "Tema", "Expositor", "Fecha", "Hora", "Duración", "Plataforma", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAvailableSessions.setColumnSelectionAllowed(true);
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

        tbRegisteredSessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Categoria", "Tema", "Expositor", "Fecha", "Hora", "Duración", "Plataforma", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbRegisteredSessions.setColumnSelectionAllowed(true);
        tbRegisteredSessions.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbRegisteredSessions);
        tbRegisteredSessions.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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

        tbSessionHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Categoria", "Tema", "Expositor", "Fecha", "Hora", "Duración", "Plataforma", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSessionHistory.setColumnSelectionAllowed(true);
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

        jLabel1.setText("Bienvenido,");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(tbControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNotificationsActionPerformed
        NotificationsDialog notificationDialog = new NotificationsDialog(this, true);
        notificationDialog.setVisible(true);
        notificationDialog.setAlwaysOnTop(true);
    }//GEN-LAST:event_btNotificationsActionPerformed

    private void tbAvailableSessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAvailableSessionsMouseClicked
        System.out.println("funciona");
    }//GEN-LAST:event_tbAvailableSessionsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btNotifications;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnAvailableSessions;
    private javax.swing.JPanel pnHistorySession;
    private javax.swing.JPanel pnRegisteredSessions;
    private javax.swing.JTable tbAvailableSessions;
    private javax.swing.JTabbedPane tbControls;
    private javax.swing.JTable tbRegisteredSessions;
    private javax.swing.JTable tbSessionHistory;
    // End of variables declaration//GEN-END:variables
}
