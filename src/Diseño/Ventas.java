package Diseño;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Ventas extends javax.swing.JFrame {

    Conexion conn = new Conexion();
    Connection cn = conn.getConnection();
    DecimalFormat df = new DecimalFormat("#.00");

    DefaultTableModel model;
    float cobrar = 0;
    int filas = -1;
    Icon ua;

    public Ventas() {
        initComponents();
        tablaVentas();
    }

    void tablaVentas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        tablaVenta.setModel(modelo);
    }

    void agregar() {
        int x = tablaVenta.getRowCount();
        if (buscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No a ingresado ningun producto", "Mensaje", JOptionPane.OK_OPTION);
        } else {

            String sql = "SELECT nombre,precio,cantidad FROM producto";
            Statement st;

            int cantidad = 1;
            String datos[] = new String[5];

            try {

                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);

                    if (buscar.getText().equals(datos[0])) {
                        model = (DefaultTableModel) tablaVenta.getModel();
                        int exist = Integer.parseInt(datos[2]);
                        
                        if (filas == -1) {
                            model.addRow(new Object[]{datos[0], datos[1], cantidad});
                        } else {
                            
                            boolean variable = false;
                            
                            for (int i = 0; i < model.getRowCount(); i++) {
                                Object producto = model.getValueAt(i, 0);
                                int cant = (int) model.getValueAt(i, 2);
                                if (buscar.getText().equals(producto)) {
                                    if (exist == cant) {
                                        JOptionPane.showMessageDialog(null, "producto agotado", "Mensaje", JOptionPane.OK_OPTION);
                                    } else {
                                        model.setValueAt((cant + 1), i, 2);
                                    }
                                    variable = true;
                                }
                            }
                            if (variable == false) {
                                model.addRow(new Object[]{datos[0], datos[1], cantidad});
                            }

                        }
                        filas++;
                        x = x + 2;
                        break;

                    }
                }

                if (x == tablaVenta.getRowCount()) {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado", "Mensaje", JOptionPane.OK_OPTION);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPrincipal = new javax.swing.JPanel();
        jBcorteCaja = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPcontenedor = new javax.swing.JPanel();
        jPVenta = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVenta = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPrincipal.setBackground(new java.awt.Color(30, 30, 30));
        jPrincipal.setMinimumSize(new java.awt.Dimension(300, 610));
        jPrincipal.setPreferredSize(new java.awt.Dimension(300, 610));

        jBcorteCaja.setBackground(new java.awt.Color(30, 30, 30));
        jBcorteCaja.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jBcorteCaja.setForeground(new java.awt.Color(255, 255, 255));
        jBcorteCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/receipt (2).png"))); // NOI18N
        jBcorteCaja.setText("Realizar corte de caja");
        jBcorteCaja.setBorder(null);
        jBcorteCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBcorteCaja.setFocusPainted(false);
        jBcorteCaja.setSelected(true);
        jBcorteCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcorteCajaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cashier (3).png"))); // NOI18N
        jLabel2.setText("USUARIO");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 150));
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Punto de Venta Beta 2.0 ");

        javax.swing.GroupLayout jPrincipalLayout = new javax.swing.GroupLayout(jPrincipal);
        jPrincipal.setLayout(jPrincipalLayout);
        jPrincipalLayout.setHorizontalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jBcorteCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPrincipalLayout.setVerticalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jBcorteCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        getContentPane().add(jPrincipal);

        jPcontenedor.setBackground(new java.awt.Color(251, 251, 251));
        jPcontenedor.setForeground(new java.awt.Color(255, 255, 255));
        jPcontenedor.setMinimumSize(new java.awt.Dimension(980, 613));
        jPcontenedor.setName(""); // NOI18N
        jPcontenedor.setPreferredSize(new java.awt.Dimension(980, 613));
        jPcontenedor.setLayout(new java.awt.CardLayout());

        jPVenta.setBackground(new java.awt.Color(40, 41, 41));
        jPVenta.setMinimumSize(new java.awt.Dimension(980, 613));
        jPVenta.setName(""); // NOI18N
        jPVenta.setPreferredSize(new java.awt.Dimension(980, 613));
        jPVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPVentaKeyPressed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(251, 251, 251));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Carro de compras");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre del producto (Codigo)");

        tablaVenta = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaVenta.setBackground(new java.awt.Color(251, 251, 251));
        tablaVenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaVenta.setForeground(new java.awt.Color(51, 51, 51));
        tablaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaVenta.setGridColor(new java.awt.Color(255, 255, 255));
        tablaVenta.setSelectionBackground(new java.awt.Color(62, 226, 141));
        jScrollPane2.setViewportView(tablaVenta);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 50)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(39, 174, 96));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("$0.00");
        jLabel1.setOpaque(true);

        jButton2.setBackground(new java.awt.Color(46, 204, 113));
        jButton2.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cobrar");
        jButton2.setBorder(null);

        jLabel11.setBackground(new java.awt.Color(251, 251, 251));
        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Busque el producto");

        jButton5.setBackground(new java.awt.Color(231, 76, 60));
        jButton5.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Vaciar carrito");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        agregar.setBackground(new java.awt.Color(46, 204, 113));
        agregar.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setMnemonic('a');
        agregar.setText("Agregar");
        agregar.setBorder(null);
        agregar.setMaximumSize(new java.awt.Dimension(90, 36));
        agregar.setMinimumSize(new java.awt.Dimension(90, 36));
        agregar.setPreferredSize(new java.awt.Dimension(90, 36));
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        agregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                agregarKeyPressed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(142, 68, 173));
        jButton8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Servicio");
        jButton8.setBorder(null);
        jButton8.setMaximumSize(new java.awt.Dimension(90, 36));
        jButton8.setMinimumSize(new java.awt.Dimension(90, 36));
        jButton8.setPreferredSize(new java.awt.Dimension(90, 36));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(52, 152, 219));
        jButton9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Buscar");
        jButton9.setBorder(null);
        jButton9.setMaximumSize(new java.awt.Dimension(90, 36));
        jButton9.setMinimumSize(new java.awt.Dimension(90, 36));
        jButton9.setPreferredSize(new java.awt.Dimension(90, 36));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        buscar.setMaximumSize(new java.awt.Dimension(90, 36));
        buscar.setMinimumSize(new java.awt.Dimension(90, 36));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPVentaLayout = new javax.swing.GroupLayout(jPVenta);
        jPVenta.setLayout(jPVentaLayout);
        jPVentaLayout.setHorizontalGroup(
            jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPVentaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPVentaLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPVentaLayout.setVerticalGroup(
            jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVentaLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(agregar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );

        jPcontenedor.add(jPVenta, "card5");

        getContentPane().add(jPcontenedor);
        jPcontenedor.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        agregar();

    }//GEN-LAST:event_agregarActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jBcorteCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcorteCajaActionPerformed
        InicioSesion login = new InicioSesion();
        setVisible(false);
        login.setVisible(true);

    }//GEN-LAST:event_jBcorteCajaActionPerformed

    private void agregarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agregarKeyPressed

    }//GEN-LAST:event_agregarKeyPressed

    private void jPVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPVentaKeyPressed

    }//GEN-LAST:event_jPVentaKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        model.setRowCount(0);        
    }//GEN-LAST:event_jButton5ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Ventas().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JTextField buscar;
    private javax.swing.JButton jBcorteCaja;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPVenta;
    private javax.swing.JPanel jPcontenedor;
    private javax.swing.JPanel jPrincipal;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaVenta;
    // End of variables declaration//GEN-END:variables
}
