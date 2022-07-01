/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package user.view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import server.model.Session;

/**
 *
 * @author Jostin Castro
 */
public class SessionDetails extends javax.swing.JDialog {

    /**
     * Creates new form SessionDetails
     */
    
    private Session data;
    private JButton btCancel;
    private final int WAIT_TIME = 1000;
    
    public SessionDetails(java.awt.Frame parent, boolean modal, byte type, Session data) {
        super(parent, modal);
        initComponents();
        this.data = data;
        adaptForm(type);
        fillData();
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

        pnData = new javax.swing.JPanel();
        spButtonSeparator = new javax.swing.JSeparator();
        tfType = new javax.swing.JTextField();
        tfAvailableSpaces = new javax.swing.JTextField();
        lbMinutes = new javax.swing.JLabel();
        tfCategory = new javax.swing.JTextField();
        tfPlatform = new javax.swing.JTextField();
        lbDate = new javax.swing.JLabel();
        lbTopic = new javax.swing.JLabel();
        tfTopic = new javax.swing.JTextField();
        lbPlatform = new javax.swing.JLabel();
        lbCategory = new javax.swing.JLabel();
        lbSpaces = new javax.swing.JLabel();
        lbAvailableSpaces = new javax.swing.JLabel();
        tfMinutes = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        lbExpositor = new javax.swing.JLabel();
        tfDate = new javax.swing.JTextField();
        lbId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        lbTypeSession = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();
        tfExpositor = new javax.swing.JTextField();
        lbDuration = new javax.swing.JLabel();
        btEnroll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Información de la sesión");
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(550, 650));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tfType.setEditable(false);

        tfAvailableSpaces.setEditable(false);

        lbMinutes.setText("minutos");

        tfCategory.setEditable(false);

        tfPlatform.setEditable(false);

        lbDate.setText("Fecha y hora:");

        lbTopic.setText("Tema:");

        tfTopic.setEditable(false);

        lbPlatform.setText("Plataforma");

        lbCategory.setText("Categoria");

        lbSpaces.setText("disponibles");

        lbAvailableSpaces.setText("Cupos disponibles:");

        tfMinutes.setEditable(false);

        taDescription.setEditable(false);
        taDescription.setColumns(20);
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        lbExpositor.setText("Expositor:");

        tfDate.setEditable(false);

        lbId.setText("ID de sesión:");

        tfId.setEditable(false);

        lbTypeSession.setText("Tipo:");

        lbDescription.setText("Descripción:");

        tfExpositor.setEditable(false);

        lbDuration.setText("Duración:");

        btEnroll.setText("Inscribirse");
        btEnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnrollActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDataLayout = new javax.swing.GroupLayout(pnData);
        pnData.setLayout(pnDataLayout);
        pnDataLayout.setHorizontalGroup(
            pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spButtonSeparator)
                    .addGroup(pnDataLayout.createSequentialGroup()
                        .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTopic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbExpositor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPlatform, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTypeSession, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAvailableSpaces, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCategory)
                            .addComponent(tfId)
                            .addComponent(tfTopic)
                            .addComponent(tfExpositor)
                            .addComponent(tfDate)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                            .addGroup(pnDataLayout.createSequentialGroup()
                                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnDataLayout.createSequentialGroup()
                                        .addComponent(tfAvailableSpaces, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbSpaces))
                                    .addGroup(pnDataLayout.createSequentialGroup()
                                        .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(tfPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfType, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btEnroll)
                .addGap(208, 208, 208))
        );
        pnDataLayout.setVerticalGroup(
            pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbId)
                    .addComponent(tfId))
                .addGap(18, 18, 18)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCategory))
                .addGap(18, 18, 18)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTopic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTopic))
                .addGap(18, 18, 18)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfExpositor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbExpositor))
                .addGap(18, 18, 18)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDate))
                .addGap(18, 18, 18)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMinutes)
                    .addComponent(lbDuration))
                .addGap(18, 18, 18)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPlatform)
                    .addComponent(tfPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTypeSession)
                    .addComponent(tfType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDescription))
                .addGap(18, 18, 18)
                .addGroup(pnDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAvailableSpaces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSpaces)
                    .addComponent(lbAvailableSpaces))
                .addGap(18, 18, 18)
                .addComponent(spButtonSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEnroll)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(pnData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pnData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEnrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnrollActionPerformed
        // TODO add your handling code here:
        btEnroll.setEnabled(false);
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        ((MainFrame) getParent()).setSessionId(data.getSesionId());
        ((MainFrame) getParent()).setSelectedOption(MainFrame.ENROLL_SESSION);
        do {
            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException ex) {
                
            }
        } while (((MainFrame) getParent()).getSessionId() != null);
        if (data.isOpen()) {
            MessageDialog.showMessageDialog("Se ha inscrito en la sesión", "Hecho");
        } else {
            MessageDialog.showMessageDialog("Se le enviará una notificación en caso de ser aprobado en esta sesión", "Hecho");
        }
        if (btCancel != null) {
            btCancel.setEnabled(true);
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btEnrollActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        ((MainFrame) getParent()).resetComponents();
    }//GEN-LAST:event_formWindowClosed

    /**
     * Method used to fill all the field of the dialog. 
     */
    
    private void fillData() {
        if (data != null) {
            final String DATE_FORMAT = "dd MMM yyyy hh:mm aaa";
            tfId.setText(data.getSesionId());
            tfExpositor.setText(data.getExpositor());
            tfMinutes.setText(Integer.toString(data.getDuration()));
            tfCategory.setText(data.getCategory());
            tfTopic.setText(data.getTopic());
            taDescription.setText(data.getDetail());
            tfType.setText((data.isOpen()) ? "Abierta" : "Cerrada");
            tfAvailableSpaces.setText(Integer.toString(data.availableSpaces()));
            tfPlatform.setText(data.getPlatform());
            GregorianCalendar temp = data.getDate();
            SimpleDateFormat date = new SimpleDateFormat(DATE_FORMAT);
            tfDate.setText(date.format(temp.getTime()));
        }
    }
    
    /**
     * Internal use method, used to adapt the dialog to the requirements,
     * it adds buttons, or cuts the size of the dialog depending of the parameter.
     * @param type a value to determine the type of the dialog.
     */
    private void adaptForm(byte type) {
        
        switch (type) {
            case MainFrame.AVAILABLE_TAB:
                if (data.availableSpaces() > 0) {
                    btEnroll.setEnabled(true);
                } else {
                    btEnroll.setEnabled(false);
                }
                break;
            case MainFrame.ENROLLED_TAB:
                btEnroll.setEnabled(false);
                GregorianCalendar tempDate = data.getDate();
                GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
                long diff = (tempDate.getTimeInMillis() - now.getTimeInMillis()) / 1000; //Calculate left time in seconds. 
                //If the session starts in more of 5 minutes, then permit enroll or cancel the enroll.
                if (diff > 300) {
                    Point point = btEnroll.getLocation();
                    Dimension dim = btEnroll.getSize();
                    btEnroll.setLocation(300, (int) point.getY());
                    pnData.add(btEnroll);
                    btCancel = new JButton("Cancelar inscripción");
                    btCancel.setBounds(100, (int) point.getY(), 150, (int) dim.getHeight());
                    btCancel.addActionListener(new ActionListener() { //Adds a listener to the button
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ((MainFrame) getParent()).setSelectedOption(MainFrame.CANCEL_ENROLL_SESSION);
                            ((MainFrame) getParent()).setSessionId(data.getSesionId());
                            btCancel.setEnabled(false);
                            setCursor(new Cursor(Cursor.WAIT_CURSOR));
                            do {
                                try {
                                    Thread.sleep(WAIT_TIME);
                                } catch (InterruptedException ex) {
                             
                                }
                            } while (((MainFrame) getParent()).getSessionId() != null);
                            MessageDialog.showMessageDialog("Se ha eliminado de la sesión", "Correcto");
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            btEnroll.setEnabled(true);
                        }
                    });
                    btCancel.setVisible(true);
                    pnData.add(btCancel);
                } else {
                    //Code to show the session link
                    final String START_LINK = "<html><p><a href=\"";
                    final String END_LINK = "</a></p></html>";
                    final String MID_LINK = "\">";
                    String text = START_LINK + data.getLink() + MID_LINK + data.getLink() + END_LINK;
                    JLabel lbLink = new JLabel(text);
                    lbLink.setBounds(btEnroll.getLocation().x, btEnroll.getLocation().y, 100, 20);
                    lbLink.addMouseListener(new MouseListener() { //Adds the mouse listener to the label.
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Desktop desktop = Desktop.getDesktop();
                            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                                URI url;
                                try {
                                    url = new URI(data.getLink());
                                    desktop.browse(url);
                                } catch (URISyntaxException | IOException ex) {
                                    MessageDialog.showMessageDialog("No se puede abrir la dirección", "Error");
                                }
                                
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            //Not used
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            //Not used
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            //Puts the hand cursor when the mouse enter in the label area.
                            lbLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            //Purs the default cursor when the mouse exit of the label area.
                            lbLink.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        }
                    });
                    btEnroll.setVisible(false);
                    lbLink.setVisible(true);
                    pnData.add(lbLink);
                }
                break;
            case MainFrame.HISTORY_TAB:
                //Cut the dialog to hide the button.
                btEnroll.setEnabled(false);
                Dimension dim = this.getSize();
                this.setSize(dim.width, dim.height - 125);
                break;
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEnroll;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAvailableSpaces;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbDuration;
    private javax.swing.JLabel lbExpositor;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbMinutes;
    private javax.swing.JLabel lbPlatform;
    private javax.swing.JLabel lbSpaces;
    private javax.swing.JLabel lbTopic;
    private javax.swing.JLabel lbTypeSession;
    private javax.swing.JPanel pnData;
    private javax.swing.JSeparator spButtonSeparator;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfAvailableSpaces;
    private javax.swing.JTextField tfCategory;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfExpositor;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfMinutes;
    private javax.swing.JTextField tfPlatform;
    private javax.swing.JTextField tfTopic;
    private javax.swing.JTextField tfType;
    // End of variables declaration//GEN-END:variables
}
