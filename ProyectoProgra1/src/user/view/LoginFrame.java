/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package user.view;

import java.awt.Cursor;
import java.awt.Dialog;

/**
 * Frame that permits login in the system.
 * @version 30/06/2022
 * @author Jostin Castro
 */
public class LoginFrame extends javax.swing.JFrame {

    
    private String email;
    private String password;
    private boolean fulled;
    private byte option;
    private boolean closed;
    
    public final byte WAIT = 0;
    public final byte LOGIN = 1;
    public final byte REGISTER = 2;
    
    /**
     * Creates new LoginFrame frame.
     */
    public LoginFrame() {
        initComponents();
        fulled = false;
        closed = false;
        this.setLocationRelativeTo(null);
        this.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        btLogin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        btRegister = new javax.swing.JButton();

        setTitle("Iniciar sesión");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Bienvenido, digite sus datos:");

        jLabel2.setText("Email:");

        jLabel3.setText("Contraseña:");

        btLogin.setText("Iniciar sesión");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        jLabel4.setText("o");

        btRegister.setText("Registrarse");
        btRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfEmail)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btLogin)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(btRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btLogin)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(btRegister)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method manages the action event when the users click on login button
     * @param evt a click in the login button
     */
    
    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        // TODO add your handling code here:
        final String EMPTY_EMAIL = "ERROR: El nombre de usuario se encuentra vacio";
        final String EMPTY_PASS = "ERROR: El campo contraseña está vacio";
        final String START_TEXT = "<html><p>";
        final String START_PAR = "<p>";
        final String END_PAR = "</p>";
        final String END_TEXT = "</html>";
        String text = "";
        if (tfEmail.getText().isEmpty() || tfEmail.getText().isBlank()) {
            text += START_TEXT + EMPTY_EMAIL + END_PAR;
        }
        if (pfPassword.getPassword().length == 0) {
            text += ((text.isEmpty()) ? START_TEXT : START_PAR) + EMPTY_PASS + END_PAR;
        }
        if (!text.isEmpty()) {
            text += END_TEXT;
            MessageDialog.showMessageDialog(text, "Error");
        } else {
            option = LOGIN;
            tfEmail.setEnabled(false);
            pfPassword.setEnabled(false);
            btLogin.setEnabled(false);
            btRegister.setEnabled(false);
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            email = tfEmail.getText();
            char[] temp = pfPassword.getPassword();
            password = "";
            for (int i = 0; i < temp.length; i++) {
                password += temp[i];
            }
            fulled = true;
        }
    }//GEN-LAST:event_btLoginActionPerformed

    private void btRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterActionPerformed
        option = REGISTER;
        tfEmail.setEnabled(false);
        pfPassword.setEnabled(false);
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }//GEN-LAST:event_btRegisterActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        final int WAIT_TIME = 2000;
        closed = true;
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException ex) {
        }
        System.exit(0);
            
    }//GEN-LAST:event_formWindowClosing

    /**
     * Method used to reset the different components in the frame.
     */
    
    public void resetComponents() {
        pfPassword.setText("");
        btLogin.setEnabled(true);
        btRegister.setEnabled(true);
        tfEmail.setEnabled(true);
        pfPassword.setEnabled(true);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    private void disableComponents() {
        pfPassword.setEnabled(false);
        tfEmail.setEnabled(false);
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFulled() {
        return fulled;
    }

    public void setFulled(boolean fulled) {
        this.fulled = fulled;
    }

    public byte getOption() {
        return option;
    }

    public void setOption(byte option) {
        this.option = option;
    }
    
    public boolean isClosed() {
        return closed;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogin;
    private javax.swing.JButton btRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfEmail;
    // End of variables declaration//GEN-END:variables
}
