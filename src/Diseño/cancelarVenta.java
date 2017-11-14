/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diseño;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Daniel
 */
public final class cancelarVenta extends javax.swing.JDialog {

    Conexion conn = new Conexion();
    Connection cn = conn.getConnection();
    DefaultTableModel modelo = new DefaultTableModel();
    TableRowSorter<TableModel> tr;
    
   
    
    public cancelarVenta(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        setTitle("Cancelar ventas");
        initComponents();        
        tablaVentas();
    }

    void tablaVentas() {
        modelo.addColumn("Fecha");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Importe");
        modelo.addColumn("Número de venta");
        modelo.addColumn("Forma de pago");

        String sql = "SELECT fecha, nombre, cantidad, (cantidad*precio) AS importe, nventa, formaP FROM producto INNER JOIN venta ON idproducto = producto_idproducto WHERE login_idlogin = ((SELECT idlogin FROM login ORDER BY idlogin DESC LIMIT 1)) ORDER BY fecha ASC";
        Statement st;
        try {
            tablaVentas.setModel(modelo);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        tr = new TableRowSorter<>(modelo);
        tablaVentas.setRowSorter(tr);
                
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelcancelarVenta = new javax.swing.JPanel();
        panelScroll = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        botonCancelar = new javax.swing.JButton();
        campoMotivo = new javax.swing.JTextField();
        labelCancelar = new javax.swing.JLabel();
        labelMotivo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelcancelarVenta.setBackground(new java.awt.Color(40, 41, 41));
        panelcancelarVenta.setMaximumSize(new java.awt.Dimension(800, 400));
        panelcancelarVenta.setMinimumSize(new java.awt.Dimension(800, 400));
        panelcancelarVenta.setPreferredSize(new java.awt.Dimension(800, 400));

        panelScroll.setBorder(null);

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Producto", "Cantidad", "Importe", "Número de venta", "Forma de pago"
            }
        ));
        panelScroll.setViewportView(tablaVentas);

        botonCancelar.setBackground(new java.awt.Color(46, 204, 113));
        botonCancelar.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        botonCancelar.setForeground(new java.awt.Color(255, 255, 255));
        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        campoMotivo.setBorder(null);

        labelCancelar.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        labelCancelar.setForeground(new java.awt.Color(255, 255, 255));
        labelCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCancelar.setText("Cancelar Venta");

        labelMotivo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelMotivo.setForeground(new java.awt.Color(255, 255, 255));
        labelMotivo.setText("Motivo");

        javax.swing.GroupLayout panelcancelarVentaLayout = new javax.swing.GroupLayout(panelcancelarVenta);
        panelcancelarVenta.setLayout(panelcancelarVentaLayout);
        panelcancelarVentaLayout.setHorizontalGroup(
            panelcancelarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcancelarVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelcancelarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelScroll)
                    .addGroup(panelcancelarVentaLayout.createSequentialGroup()
                        .addComponent(labelMotivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonCancelar)))
                .addContainerGap())
            .addGroup(panelcancelarVentaLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(labelCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        panelcancelarVentaLayout.setVerticalGroup(
            panelcancelarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcancelarVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelcancelarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelcancelarVentaLayout.createSequentialGroup()
                        .addGroup(panelcancelarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonCancelar)
                            .addComponent(campoMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcancelarVentaLayout.createSequentialGroup()
                        .addComponent(labelMotivo)
                        .addGap(25, 25, 25)))
                .addComponent(panelScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelScroll.getAccessibleContext().setAccessibleDescription("");
        panelScroll.getAccessibleContext().setAccessibleParent(panelScroll);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelcancelarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelcancelarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // TODO add your handling code here:
        try {
            int row = tablaVentas.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel) tablaVentas.getModel();
                int nventa = (int) dtm.getValueAt(tablaVentas.getSelectedRow(), 4);
            if("".equals(campoMotivo.getText())){
        JOptionPane.showMessageDialog(null, "No ha escrito un motivo", "Mensaje", JOptionPane.OK_OPTION);
        }else{
        JOptionPane.showMessageDialog(null, "Me ha dado hueva hacer el codigo", "Mensaje", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Venta no seleccionada", "Mensaje", JOptionPane.OK_OPTION);
        }
        

    }//GEN-LAST:event_botonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JTextField campoMotivo;
    private javax.swing.JLabel labelCancelar;
    private javax.swing.JLabel labelMotivo;
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JPanel panelcancelarVenta;
    private javax.swing.JTable tablaVentas;
    // End of variables declaration//GEN-END:variables
}
