
package Diseño;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Alejandro
 */
public class TEST extends javax.swing.JFrame {

    /**
     * Creates new form TEST
     */
    public TEST() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboNIVEL = new javax.swing.JComboBox<>();
        cacular = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jSutilid = new javax.swing.JSpinner();
        txtCosto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMagregarUser = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboNIVEL.setBackground(new java.awt.Color(251, 251, 251));
        jComboNIVEL.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboNIVEL.setForeground(new java.awt.Color(51, 51, 51));
        jComboNIVEL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Vendedor", "Comprador", "Cliente" }));
        jComboNIVEL.setToolTipText("");
        jComboNIVEL.setBorder(null);
        jComboNIVEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboNIVELActionPerformed(evt);
            }
        });

        cacular.setBackground(new java.awt.Color(46, 204, 113));
        cacular.setForeground(new java.awt.Color(255, 255, 255));
        cacular.setText("Agregar");
        cacular.setBorder(null);
        cacular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cacular.setFocusPainted(false);
        cacular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cacularActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Tipo de usuario");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Contraseña");

        txtPass.setBackground(new java.awt.Color(251, 251, 251));
        txtPass.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtPass.setForeground(new java.awt.Color(51, 51, 51));
        txtPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });

        jSutilid.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jSutilid.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(999999.0f), Float.valueOf(1.0f)));
        jSutilid.setBorder(null);
        jSutilid.setOpaque(false);
        jSutilid.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSutilidStateChanged(evt);
            }
        });

        txtCosto.setBackground(new java.awt.Color(251, 251, 251));
        txtCosto.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtCosto.setForeground(new java.awt.Color(51, 51, 51));
        txtCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("%");

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Costo");
        jLabel11.setOpaque(true);

        jLabel71.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Margen de utilidad");
        jLabel71.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Precio");
        jLabel8.setOpaque(true);

        txtPrecio.setBackground(new java.awt.Color(251, 251, 251));
        txtPrecio.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(46, 204, 113));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Agregar");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(30, 30, 30));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setToolTipText("");
        jMenuBar1.setPreferredSize(new java.awt.Dimension(120, 19));

        jMenu1.setBackground(new java.awt.Color(251, 251, 251));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Archivo");

        jMagregarUser.setBackground(new java.awt.Color(251, 251, 251));
        jMagregarUser.setForeground(new java.awt.Color(51, 51, 51));
        jMagregarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user (1).png"))); // NOI18N
        jMagregarUser.setText("Agregar usuario");
        jMagregarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMagregarUserActionPerformed(evt);
            }
        });
        jMenu1.add(jMagregarUser);

        jMenuItem7.setBackground(new java.awt.Color(251, 251, 251));
        jMenuItem7.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/database  (1).png"))); // NOI18N
        jMenuItem7.setText("Exportar base de datos");
        jMenu1.add(jMenuItem7);

        jMenuItem8.setBackground(new java.awt.Color(251, 251, 251));
        jMenuItem8.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/database  (5).png"))); // NOI18N
        jMenuItem8.setText("Importar base de datos");
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(251, 251, 251));
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Catalogo");

        jMenuItem1.setBackground(new java.awt.Color(251, 251, 251));
        jMenuItem1.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem1.setText("Productos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setBackground(new java.awt.Color(251, 251, 251));
        jMenuItem2.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem2.setText("Provedores");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setBackground(new java.awt.Color(251, 251, 251));
        jMenuItem3.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem3.setText("Clientes");
        jMenu2.add(jMenuItem3);

        jMenuItem5.setBackground(new java.awt.Color(251, 251, 251));
        jMenuItem5.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem5.setText("Servicios");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setBackground(new java.awt.Color(251, 251, 251));
        jMenuItem6.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem6.setText("Usuarios");
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(251, 251, 251));
        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Ayuda");

        jMenuItem4.setBackground(new java.awt.Color(251, 251, 251));
        jMenuItem4.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem4.setText("Informacion");
        jMenuItem4.setToolTipText("");
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(37, 37, 37)
                                .addComponent(jComboNIVEL, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSutilid))
                                            .addComponent(jLabel71)))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(201, 201, 201)
                                        .addComponent(cacular, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton3)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel6)
                        .addGap(119, 119, 119)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(396, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(866, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(22, 22, 22)
                                .addComponent(jComboNIVEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jSutilid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel71))))
                        .addGap(61, 61, 61)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(90, 90, 90)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cacular, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(38, 38, 38))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(535, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cacularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cacularActionPerformed
       
        float costo = 0;
        

        
       costo = Integer.parseInt(txtCosto.getText());
    
       
    }//GEN-LAST:event_cacularActionPerformed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSutilidStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSutilidStateChanged
        if (jSutilid!=null&& !txtCosto.getText().isEmpty()) {
             float costo = 0;
       costo = Float.parseFloat(txtCosto.getText());
       float utilidad = 0;
       utilidad = (float) jSutilid.getValue();
       float total= costo + (costo*(utilidad/100));
       String precio = String.valueOf(total);
             
       txtPrecio.setText(precio);
        }
       
        
        
        
        
    }//GEN-LAST:event_jSutilidStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int test =  jComboBox1.getSelectedIndex();
        System.out.println(test+1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    txtCosto.setText(null);
    txtPrecio.setText(null); 
   
   
    jSutilid.setValue(0.0f);
    
    
    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboNIVELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboNIVELActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboNIVELActionPerformed

    private void jMagregarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMagregarUserActionPerformed
        Interface inter = new Interface();
        AgregarUser agreuser = new AgregarUser(inter,true);
        agreuser.setVisible(true);
    }//GEN-LAST:event_jMagregarUserActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoActionPerformed

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
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TEST().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cacular;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboNIVEL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMagregarUser;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JSpinner jSutilid;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
