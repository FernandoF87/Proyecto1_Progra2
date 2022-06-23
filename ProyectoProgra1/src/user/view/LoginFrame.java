/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package user.view;

import java.awt.Cursor;

/**
 *
 * @author Jostin Castro
 */
public class LoginFrame extends javax.swing.JFrame {

    
    private String email;
    private String password;
    private boolean fulled;
    private byte option;
    
    public final byte LOGIN = 1;
    public final byte REGISTER = 2;
    
    /**
     * Creates new form LoginFrame2
     */
    public LoginFrame() {
        initComponents();
        fulled = false;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        btLogin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        btRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btLogin))
                        .addGap(124, 124, 124))))
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
                .addGap(32, 32, 32)
                .addComponent(btLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btRegister)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        // TODO add your handling code here:
        String text = "";
        if (tfEmail.getText().isEmpty() || tfEmail.getText().isBlank()) {
            text += "ERROR: El nombre de usuario se encuentra vacio</p>";
        }
        if (pfPassword.getPassword().length == 0) {
            text += "<p>ERROR: El campo contraseña está vacio</p>";
        }
        if (!text.isEmpty()) {
            text += "</html>";
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

    public void resetComponents() {
        btLogin.setEnabled(true);
        btRegister.setEnabled(true);
        tfEmail.setEnabled(true);
        pfPassword.setEnabled(true);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
