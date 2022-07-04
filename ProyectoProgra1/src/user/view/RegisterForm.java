/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package user.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Shows to the user a register form to put his information and create a new user.
 * @version 30/06/2022
 * @author Jostin Castro
 */
public class RegisterForm extends javax.swing.JDialog {

    private boolean leapYear;
    
    private boolean complete;
    private boolean closed;
    private String email;
    private String name;
    private String id;
    private String password;
    private GregorianCalendar bornDate;
    private int phoneNumber;
    
    private final String ID_PLACEHOLDER = "0000000000";
    private final String PASS_PLACEHOLDER = "Al menos 8 caracteres: letras y números";
    private final char PASS_ECHO_CHAR = '\u2022';
    private final char NORMAL_ECHO_CHAR = '\u0000';
    
    private final String PHONE_NUMBER_PLACEHOLDER = "00000000";
    
    /**
     * Creates new form RegisterForm
     */
    
    public RegisterForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        complete = false;
        closed = false;
        this.setLocationRelativeTo(null);
        tfId.setText(ID_PLACEHOLDER);
        pfPassword.setText(PASS_PLACEHOLDER);
        pfConfirmPassword.setText(PASS_PLACEHOLDER);
        tfPhoneNumber.setText(PHONE_NUMBER_PLACEHOLDER);
        addPlaceholder(tfId);
        addPlaceholder(pfConfirmPassword);
        addPlaceholder(pfPassword);
        addPlaceholder(tfPhoneNumber);
        configureDate();
    }
    
    /**
     * Adds a placeholder to a text field.
     * @param textField the text field to put a placeholder.
     */
    
    private void addPlaceholder(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.ITALIC);
        textField.setFont(font);
        textField.setForeground(Color.gray);
    }
    
    /**
     * Removes a placeholder in a text field.
     * @param textField the text field to remove a placeholder.
     */
    
    private void removePlaceHolder(JTextField textField) {
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN);
        textField.setFont(font);
        textField.setForeground(Color.black);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnInterface = new javax.swing.JPanel();
        tfId = new javax.swing.JTextField();
        lbMail = new javax.swing.JLabel();
        cbMonth = new javax.swing.JComboBox<>();
        tfPhoneNumber = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        pfConfirmPassword = new javax.swing.JPasswordField();
        tfEmail = new javax.swing.JTextField();
        lbId = new javax.swing.JLabel();
        lbInformation = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lbConfirmPassword = new javax.swing.JLabel();
        spDay = new javax.swing.JSpinner();
        lbName = new javax.swing.JLabel();
        lbPasword = new javax.swing.JLabel();
        lbBornDate = new javax.swing.JLabel();
        lbTelephoneNumber = new javax.swing.JLabel();
        btRegister = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        spYear = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrarse");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tfId.setToolTipText("");
        tfId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfIdFocusLost(evt);
            }
        });

        lbMail.setText("Correo");

        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        tfPhoneNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfPhoneNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPhoneNumberFocusLost(evt);
            }
        });

        pfPassword.setEchoChar('\u0000');
        pfPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pfPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pfPasswordFocusLost(evt);
            }
        });

        pfConfirmPassword.setEchoChar('\u0000');
        pfConfirmPassword.setPreferredSize(new java.awt.Dimension(95, 22));
        pfConfirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pfConfirmPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pfConfirmPasswordFocusLost(evt);
            }
        });

        lbId.setText("Número de cédula:");

        lbInformation.setText("Ingrese sus datos por favor:");

        lbConfirmPassword.setText("Confirmar contraseña:");

        spDay.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lbName.setText("Nombre:");

        lbPasword.setText("Contraseña:");

        lbBornDate.setText("Fecha de nacimiento:");

        lbTelephoneNumber.setText("Número de telefono:");

        btRegister.setText("Registrarse");
        btRegister.setToolTipText("");
        btRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterActionPerformed(evt);
            }
        });

        btCancel.setText("Cancelar");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        spYear.setModel(new javax.swing.SpinnerNumberModel(2000, 1924, 2005, 1));

        javax.swing.GroupLayout pnInterfaceLayout = new javax.swing.GroupLayout(pnInterface);
        pnInterface.setLayout(pnInterfaceLayout);
        pnInterfaceLayout.setHorizontalGroup(
            pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInterfaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnInterfaceLayout.createSequentialGroup()
                        .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPasword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbBornDate)
                            .addComponent(lbConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelephoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfId)
                            .addComponent(tfEmail)
                            .addComponent(pfPassword)
                            .addComponent(pfConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnInterfaceLayout.createSequentialGroup()
                                .addComponent(spDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(spYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnInterfaceLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btRegister)
                        .addGap(57, 57, 57)
                        .addComponent(btCancel)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnInterfaceLayout.setVerticalGroup(
            pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInterfaceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInformation)
                .addGap(18, 18, 18)
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbId))
                .addGap(18, 18, 18)
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMail))
                .addGap(18, 18, 18)
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPasword))
                .addGap(18, 18, 18)
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbConfirmPassword))
                .addGap(18, 18, 18)
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbBornDate))
                .addGap(12, 12, 12)
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbName))
                .addGap(18, 18, 18)
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelephoneNumber))
                .addGap(57, 57, 57)
                .addGroup(pnInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRegister)
                    .addComponent(btCancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(pnInterface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnInterface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        closed = true;
        this.dispose();
    }//GEN-LAST:event_btCancelActionPerformed

    private void btRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterActionPerformed
        // TODO add your handling code here:
        final String START_MESSAGE = "<html><p>";
        final String END_MESSAGE = "</p></html>";
        String message = "";
        if (checkId()) {
            this.id = tfId.getText();
        } else {
            message += "La cédula no cumple con los requisitos</p><p>";
        }
        if (checkMail()) {
            this.email = tfEmail.getText();
        } else {
            message += "Error en el correo electronico</p><p>";
        }
        if (!checkBornDate()) {
            message += "No cumple con los requisitos de edad</p><p>";
        }
        String tempPass = checkPassword();
        if (tempPass != null) {
            password = tempPass;
        } else {
            message += "La contraseña no es igual o no cumple con los requisitos</p><p>";
        }
        if (checkName()) {
            name = tfName.getText();
        } else {
            message += "El nombre no cumple con las condiciones";
        }
        if (checkPhoneNumber()) {
            phoneNumber = Integer.parseInt(tfPhoneNumber.getText());
        } else {
            message += "El número de teléfono no cumple con los requisitos";
        }
        
        
        if (!message.isEmpty()) {
            MessageDialog errorRegistration = new MessageDialog(START_MESSAGE + message + END_MESSAGE, "Error");
        } else {
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            complete = true;
        }
        
    }//GEN-LAST:event_btRegisterActionPerformed

    private void tfIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfIdFocusGained
        if (tfId.getText().equals(ID_PLACEHOLDER)) {
            tfId.setText("");
            removePlaceHolder(tfId);
        }
    }//GEN-LAST:event_tfIdFocusGained

    private void tfIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfIdFocusLost
        if (tfId.getText().isEmpty()) {
            tfId.setText(ID_PLACEHOLDER);
            addPlaceholder(tfId);
        }
    }//GEN-LAST:event_tfIdFocusLost

    private void pfPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfPasswordFocusGained
        if (pfPassword.getText().equals(PASS_PLACEHOLDER)) {
            pfPassword.setText("");
            pfPassword.setEchoChar(PASS_ECHO_CHAR);
            removePlaceHolder(pfPassword);
        }
    }//GEN-LAST:event_pfPasswordFocusGained

    private void pfConfirmPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfConfirmPasswordFocusGained
        if (pfConfirmPassword.getText().equals(PASS_PLACEHOLDER)) {
            pfConfirmPassword.setText("");
            pfConfirmPassword.setEchoChar(PASS_ECHO_CHAR);
            removePlaceHolder(pfConfirmPassword);
        }
    }//GEN-LAST:event_pfConfirmPasswordFocusGained

    private void pfPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfPasswordFocusLost
        // TODO add your handling code here:
        if (pfPassword.getText().isEmpty()) {
            pfPassword.setText(PASS_PLACEHOLDER);
            pfPassword.setEchoChar(NORMAL_ECHO_CHAR);
            addPlaceholder(pfPassword);
        }
    }//GEN-LAST:event_pfPasswordFocusLost

    private void pfConfirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfConfirmPasswordFocusLost
        // TODO add your handling code here:
        if (pfConfirmPassword.getText().isEmpty()) {
            pfConfirmPassword.setText(PASS_PLACEHOLDER);
            pfConfirmPassword.setEchoChar(NORMAL_ECHO_CHAR);
            addPlaceholder(pfConfirmPassword);
        }
    }//GEN-LAST:event_pfConfirmPasswordFocusLost

    private void tfPhoneNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPhoneNumberFocusGained
        // TODO add your handling code here:
        if (tfPhoneNumber.getText().equals(PHONE_NUMBER_PLACEHOLDER)) {
            tfPhoneNumber.setText("");
            removePlaceHolder(tfPhoneNumber);
        }
    }//GEN-LAST:event_tfPhoneNumberFocusGained

    private void tfPhoneNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPhoneNumberFocusLost
        if (tfPhoneNumber.getText().isEmpty()) {
            tfPhoneNumber.setText(PHONE_NUMBER_PLACEHOLDER);
            addPlaceholder(tfPhoneNumber);
        }
    }//GEN-LAST:event_tfPhoneNumberFocusLost

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        closed = true;
    }//GEN-LAST:event_formWindowClosed

    /**
     * Method used to check if the id comply with the requirements.
     * @return <b>true</b> if comply, <b>false</b> otherwise. 
     */
    private boolean checkId() {
        if (tfId.getText().equals(ID_PLACEHOLDER)) {
            return false;
        }
        
        final String ID_PATTERN = "[0]{1}[0-9]{9}";
        Pattern pat = Pattern.compile(ID_PATTERN);
        Matcher mat = pat.matcher(tfId.getText());
        if (mat.matches()) {
            return true;
        }
        return false;
    }
    
    /**
     * Method used to check if the email comply with the requirements.
     * @return <b>true</b> if comply, <b>false</b> otherwise. 
     */
    
    private boolean checkMail() {
        final String EMAIL_PATTERN = "[a-zA-Z0-9_.]{1,}[@]{1}[a-z1-9-.]+[.]{1}[a-z]{2,4}";
        Pattern pat = Pattern.compile(EMAIL_PATTERN);
        Matcher mat = pat.matcher(tfEmail.getText());
        if (mat.matches()) {
            return true;
        }
        return false;
    }
    
    /**
     * Method used to check if the born date comply with the requirements.
     * @return <b>true</b> if comply, <b>false</b> otherwise. 
     */ 
    
    private boolean checkBornDate() {
        final byte MIN_AGE = 18;
        bornDate = new GregorianCalendar();
        bornDate.set((int) spYear.getValue(), cbMonth.getSelectedIndex() + 1, (int) spDay.getValue());
        String temp = new SimpleDateFormat("yyy-MM-dd").format(bornDate.getTime());
        LocalDate bornDateLocal = LocalDate.parse(temp);
        Period age = Period.between(bornDateLocal, LocalDate.now());
        if (age.getYears() >= MIN_AGE) {
            return true;
        }
        return false;
    }
    
    /**
     * Method used to check if the name comply with the requirements.
     * @return <b>true</b> if comply, <b>false</b> otherwise. 
     */
    
    private boolean checkName() {
        final String NAME_PATTERN = "[[a-zA-Z]+|\\s{1}]{1,100}";
        Pattern pat = Pattern.compile(NAME_PATTERN);
        Matcher mat = pat.matcher(tfName.getText());
        if (mat.matches()) {
            return true;
        }
        return false;
    }
    
    /**
     * Method used to check if the password comply with the requirements.
     * @return <b>true</b> if comply, <b>false</b> otherwise. 
     */
    
    private String checkPassword() {
        char[] pass1 = pfPassword.getPassword();
        char[] pass2 = pfConfirmPassword.getPassword();
        final String PASSWORD_PATTERN = "[[[a-z]{0,}|[A-Z]{0,}]+&[0-9]+]{8,}";
        String temp = "";
        if (pass1.length == pass2.length) {
            for (int i = 0; i < pass1.length; i++) {
                if (pass1[i] != pass2[i]) {
                    return null;
                }
                temp += pass1[i];
            }
            Pattern pat = Pattern.compile(PASSWORD_PATTERN);
            Matcher mat = pat.matcher(temp);
            if (mat.matches()) {
                return temp;
            }
        }
        return null;
    }
    
    /**
     * Method used to check if the email comply with the requirements.
     * @return <b>true</b> if comply, <b>false</b> otherwise. 
     */
    
    private boolean checkPhoneNumber() {
        if (tfPhoneNumber.getText().equals(PHONE_NUMBER_PLACEHOLDER)) {
            return false;
        }
        final String PHONE_NUMBER_PATTERN = "[0-9]{8}";
        Pattern pat = Pattern.compile(PHONE_NUMBER_PATTERN);
        Matcher mat = pat.matcher(tfPhoneNumber.getText());
        if (mat.matches()) {
            return true;
        }
        return false;
    }

    public boolean isComplete() {
        return complete;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public GregorianCalendar getBornDate() {
        return bornDate;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isClosed() {
        return closed;
    }

    
    /**
     * Internal use method, used to set the different days by month, or determine if a year is a leap year.
     */
    
    private void configureDate() {
        spYear.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GregorianCalendar temp = new GregorianCalendar();
                temp.set(GregorianCalendar.YEAR, (int) spYear.getValue());
                if (temp.isLeapYear(temp.get(GregorianCalendar.YEAR))) {
                    leapYear = true;
                } else {
                    leapYear = false;
                }
            }
        });
        cbMonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                final byte JANUARY = 0;
                final byte FEBRUARY = 1;
                final byte MARCH = 2;
                final byte APRIL = 3;
                final byte MAY = 4;
                final byte JUNE = 5;
                final byte JULY = 6;
                final byte AUGUST = 7;
                final byte SEPTEMBER = 8;
                final byte OCTOBER = 9;
                final byte NOVEMBER = 10;
                final byte DECEMBER = 11;
                
                final byte LONG_MONTH = 31;
                final byte NORMAL_MONTH = 30;
                final byte FEBRUARY_LEAP = 29;
                final byte FEBRUARY_NORMAL = 28;
                
                SpinnerNumberModel model = new SpinnerNumberModel();
                model.setMinimum(Integer.valueOf(1));
                model.setStepSize(Integer.valueOf(1));
                model.setValue(spDay.getValue());
                switch (cbMonth.getSelectedIndex()) {
                    case FEBRUARY:
                        if (leapYear) {
                            model.setMaximum(Integer.valueOf(FEBRUARY_LEAP));
                        } else {
                            model.setMaximum(Integer.valueOf(FEBRUARY_NORMAL));
                        }
                        break;
                    case APRIL:
                    case JUNE:
                    case SEPTEMBER:
                    case NOVEMBER:
                        model.setMaximum(Integer.valueOf(NORMAL_MONTH));
                        break;
                    case JANUARY:
                    case MAY:
                    case MARCH:
                    case JULY:
                    case AUGUST:
                    case OCTOBER:
                    case DECEMBER:
                        model.setMaximum(Integer.valueOf(LONG_MONTH));
                        break;
                }
                spDay.setModel(model);
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btRegister;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JLabel lbBornDate;
    private javax.swing.JLabel lbConfirmPassword;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbInformation;
    private javax.swing.JLabel lbMail;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPasword;
    private javax.swing.JLabel lbTelephoneNumber;
    private javax.swing.JPasswordField pfConfirmPassword;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JPanel pnInterface;
    private javax.swing.JSpinner spDay;
    private javax.swing.JSpinner spYear;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
