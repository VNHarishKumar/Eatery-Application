/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.deliveryPersonFrame;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.EateryEnterprise;

/**
 *
 * @author ktkir
 */
public class deliveryPersonManagerLogin extends javax.swing.JFrame {

    /**
     * Creates new form deliveryPersonManagerLogin
     */
    EateryEnterprise eatery;
    String passCode="delPerMng";
    public deliveryPersonManagerLogin(EateryEnterprise eatery) {
        initComponents();
        
        this.getContentPane().setBackground(new Color(0,153,204));
          this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          
        this.eatery=eatery;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPatientLogin = new javax.swing.JButton();
        passcode = new javax.swing.JLabel();
        textPasscode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 204));

        btnPatientLogin.setBackground(new java.awt.Color(102, 102, 102));
        btnPatientLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnPatientLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnPatientLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        btnPatientLogin.setText("LOGIN");
        btnPatientLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPatientLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPatientLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPatientLoginMouseExited(evt);
            }
        });
        btnPatientLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPatientLoginActionPerformed(evt);
            }
        });

        passcode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passcode.setText("PASSCODE");

        textPasscode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Please enter your Deliver Person Manager PassCode");

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SYSTEM ADMINISTRATOR LOGIN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1600, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(629, Short.MAX_VALUE)
                .addComponent(passcode, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPatientLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(textPasscode))
                .addGap(687, 687, 687))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passcode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPasscode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnPatientLogin)
                .addGap(0, 541, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPatientLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPatientLoginActionPerformed
        // TODO add your handling code here:
        if(!textPasscode.getText().equals(""))
        {
            if(textPasscode.getText().equals(passCode))
            {
                deliveryBoyManageFrame delPerMngFrame = new deliveryBoyManageFrame(eatery);
                delPerMngFrame.setVisible(true);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "This is not a valid passcode!");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Enter the System Admin passcode to login!");
        }
    }//GEN-LAST:event_btnPatientLoginActionPerformed

    private void btnPatientLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPatientLoginMouseEntered
        // TODO add your handling code here:
        btnPatientLogin.setBackground(new Color(255,204,0));
    }//GEN-LAST:event_btnPatientLoginMouseEntered

    private void btnPatientLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPatientLoginMouseExited
        // TODO add your handling code here:
         btnPatientLogin.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_btnPatientLoginMouseExited

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
            java.util.logging.Logger.getLogger(deliveryPersonManagerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(deliveryPersonManagerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(deliveryPersonManagerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(deliveryPersonManagerLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new deliveryPersonManagerLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPatientLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel passcode;
    private javax.swing.JTextField textPasscode;
    // End of variables declaration//GEN-END:variables
}