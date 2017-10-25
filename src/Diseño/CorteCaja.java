package Diseño;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CorteCaja extends javax.swing.JFrame {
  Conexion conn = new Conexion();
  Connection cn = conn.getConnection();
  String User = "";
    public CorteCaja(String user) {
        User = user;
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtDinero = new javax.swing.JTextField();
        registrarDinero = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(251, 251, 251));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(40, 41, 41));
        jPanel1.setMaximumSize(new java.awt.Dimension(160, 155));
        jPanel1.setMinimumSize(new java.awt.Dimension(160, 155));
        jPanel1.setPreferredSize(new java.awt.Dimension(160, 155));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cashier (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(40, 41, 41));

        jLabel26.setBackground(new java.awt.Color(40, 41, 41));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("¿Efectivo inicial en caja?");
        jLabel26.setMaximumSize(new java.awt.Dimension(111, 23));
        jLabel26.setMinimumSize(new java.awt.Dimension(111, 23));
        jLabel26.setOpaque(true);
        jLabel26.setPreferredSize(new java.awt.Dimension(111, 23));

        txtDinero.setBackground(new java.awt.Color(40, 41, 41));
        txtDinero.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtDinero.setForeground(new java.awt.Color(255, 255, 255));
        txtDinero.setBorder(null);
        txtDinero.setCaretColor(new java.awt.Color(255, 255, 255));
        txtDinero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDineroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDineroKeyTyped(evt);
            }
        });

        registrarDinero.setBackground(new java.awt.Color(29, 184, 83));
        registrarDinero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        registrarDinero.setForeground(new java.awt.Color(255, 255, 255));
        registrarDinero.setText("Registrar");
        registrarDinero.setBorder(null);
        registrarDinero.setFocusPainted(false);
        registrarDinero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarDineroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(registrarDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDinero))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(registrarDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registrarDineroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarDineroActionPerformed
    float dinero= Float.parseFloat(txtDinero.getText());
      try {
          PreparedStatement pps = cn.prepareStatement("UPDATE login SET dineroEnt='"+dinero+"' WHERE idlogin=(SELECT AUTO_INCREMENT -1 AS LastId FROM information_schema.tables WHERE TABLE_SCHEMA='puntovent' AND TABLE_NAME='login')");
              pps.executeUpdate();
                    Ventas vent = new Ventas(User);
                    this.setVisible(false);
                    vent.setVisible(true);
      
      } catch (SQLException ex) {
          Logger.getLogger(CorteCaja.class.getName()).log(Level.SEVERE, null, ex);
      }
    }//GEN-LAST:event_registrarDineroActionPerformed

    private void txtDineroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
           registrarDinero.doClick();
        }
    }//GEN-LAST:event_txtDineroKeyPressed

    private void txtDineroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_txtDineroKeyTyped

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
            java.util.logging.Logger.getLogger(CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton registrarDinero;
    private javax.swing.JTextField txtDinero;
    // End of variables declaration//GEN-END:variables
}
