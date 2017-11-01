package Diseño;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Ventas extends javax.swing.JFrame {

    public static Conexion conn = new Conexion();
    public static Connection cn = conn.getConnection();
    DecimalFormat df = new DecimalFormat("#.00");
    public static DefaultTableModel model;
    public static float cobrar = 0;
    public static int filas = -1;
    public static int nTicket = 1;
    public static Vector idProducto = new Vector();
    ;
    Icon ua;
    public static String Usuario = "";

    public Ventas(String User) {
        Usuario = User;
        initComponents();
        tablaVentas();

    }

    public static void tablaVentas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        tablaVenta.setModel(modelo);
    }

    public static void agregar() {
        int x = tablaVenta.getRowCount();
        if (textBuscar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No a ingresado ningun producto", "Mensaje", JOptionPane.OK_OPTION);
        } else {

            String sql = "SELECT codigo,precio,stock,nombre,idproducto FROM producto";
            Statement st;

            int cantidad = (int) jSpinner1.getValue();
            String datos[] = new String[5];

            try {
                
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    if (textBuscar.getText().equals(datos[0])) {
                        model = (DefaultTableModel) tablaVenta.getModel();
                        int exist = Integer.parseInt(datos[2]);
                        if (model.getRowCount() == 0) {
                            if (exist < cantidad) {
                                JOptionPane.showMessageDialog(null, "producto agotado", "Mensaje", JOptionPane.OK_OPTION);
                            } else {
                                model.addRow(new Object[]{datos[0], datos[3], datos[1], cantidad});
                                idProducto.add(rs.getString(5));
                                cobrar = cobrar + Float.parseFloat(datos[1]);
                                cobrar = cobrar * cantidad;
                                jLabel1.setText("$" + String.valueOf(cobrar));
                            }
                        } else {
                            boolean variable = false;
                            for (int i = 0; i < model.getRowCount(); i++) {
                                Object producto = model.getValueAt(i, 0);
                                int cant = (int) model.getValueAt(i, 3) + (int) jSpinner1.getValue();
                                if (textBuscar.getText().equals(producto)) {
                                    if (exist < cant) {
                                        JOptionPane.showMessageDialog(null, "producto agotado", "Mensaje", JOptionPane.OK_OPTION);
                                    } else {
                                        model.setValueAt(cant, i, 3);
                                        cobrar = cobrar + (Float.parseFloat(datos[1]) * cantidad);
                                        jLabel1.setText("$" + String.valueOf(cobrar));
                                    }
                                    variable = true;
                                }
                            }
                            if (variable == false) {
                                if (exist < cantidad) {
                                    JOptionPane.showMessageDialog(null, "producto agotado", "Mensaje", JOptionPane.OK_OPTION);
                                } else {
                                    model.addRow(new Object[]{datos[0], datos[3], datos[1], cantidad});
                                    idProducto.add(rs.getString(5));
                                    cobrar = cobrar + (Float.parseFloat(datos[1]) * cantidad);
                                    jLabel1.setText("$" + String.valueOf(cobrar));
                                }
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
                System.out.println(ex.getMessage());
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
        jBcancelarV = new javax.swing.JButton();
        jBcorteCaja1 = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jPcontenedor = new javax.swing.JPanel();
        jPVenta = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVenta = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        textBuscar = new javax.swing.JTextField();
        borrarProducto = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        labelTicket = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPrincipal.setBackground(new java.awt.Color(30, 30, 30));
        jPrincipal.setMinimumSize(new java.awt.Dimension(300, 610));
        jPrincipal.setPreferredSize(new java.awt.Dimension(300, 610));

        jBcorteCaja.setBackground(new java.awt.Color(30, 30, 30));
        jBcorteCaja.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jBcorteCaja.setForeground(new java.awt.Color(255, 255, 255));
        jBcorteCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/receipt (2).png"))); // NOI18N
        jBcorteCaja.setText("Realizar corte del dia");
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
        jLabel2.setText(Usuario);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 150));
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Punto de Venta Beta 2.0 ");

        jBcancelarV.setBackground(new java.awt.Color(30, 30, 30));
        jBcancelarV.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jBcancelarV.setForeground(new java.awt.Color(255, 255, 255));
        jBcancelarV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/receipt (2).png"))); // NOI18N
        jBcancelarV.setText("Cancelar una Venta");
        jBcancelarV.setBorder(null);
        jBcancelarV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBcancelarV.setFocusPainted(false);
        jBcancelarV.setSelected(true);
        jBcancelarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarVActionPerformed(evt);
            }
        });

        jBcorteCaja1.setBackground(new java.awt.Color(30, 30, 30));
        jBcorteCaja1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jBcorteCaja1.setForeground(new java.awt.Color(255, 255, 255));
        jBcorteCaja1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/receipt (2).png"))); // NOI18N
        jBcorteCaja1.setText("Realizar corte de cajero");
        jBcorteCaja1.setBorder(null);
        jBcorteCaja1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBcorteCaja1.setFocusPainted(false);
        jBcorteCaja1.setSelected(true);
        jBcorteCaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcorteCaja1ActionPerformed(evt);
            }
        });

        salir.setBackground(new java.awt.Color(40, 41, 41));
        salir.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("Minimizar");
        salir.setBorder(null);
        salir.setFocusPainted(false);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPrincipalLayout = new javax.swing.GroupLayout(jPrincipal);
        jPrincipal.setLayout(jPrincipalLayout);
        jPrincipalLayout.setHorizontalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jBcorteCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBcancelarV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBcorteCaja1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPrincipalLayout.setVerticalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBcorteCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBcorteCaja1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBcancelarV, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(salir)
                .addContainerGap())
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

        jLabel10.setBackground(new java.awt.Color(251, 251, 251));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Carro de compras");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CODIGO");

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

        jLabel1.setBackground(new java.awt.Color(40, 41, 41));
        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 50)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("$0.00");
        jLabel1.setOpaque(true);

        jButton2.setBackground(new java.awt.Color(29, 184, 83));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cobrar");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(192, 57, 43));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
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
        agregar.setBorderPainted(false);
        agregar.setDefaultCapable(false);
        agregar.setMaximumSize(new java.awt.Dimension(90, 36));
        agregar.setMinimumSize(new java.awt.Dimension(90, 36));
        agregar.setPreferredSize(new java.awt.Dimension(90, 36));
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(142, 68, 173));
        jButton8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Reimprimir ultimo ticket");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setDefaultCapable(false);
        jButton8.setMaximumSize(new java.awt.Dimension(90, 36));
        jButton8.setMinimumSize(new java.awt.Dimension(90, 36));
        jButton8.setPreferredSize(new java.awt.Dimension(90, 36));

        jButton9.setBackground(new java.awt.Color(52, 152, 219));
        jButton9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Buscar");
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setDefaultCapable(false);
        jButton9.setMaximumSize(new java.awt.Dimension(90, 36));
        jButton9.setMinimumSize(new java.awt.Dimension(90, 36));
        jButton9.setPreferredSize(new java.awt.Dimension(90, 36));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        textBuscar.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        textBuscar.setBorder(null);
        textBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textBuscar.setMaximumSize(new java.awt.Dimension(90, 36));
        textBuscar.setMinimumSize(new java.awt.Dimension(90, 36));
        textBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBuscarKeyPressed(evt);
            }
        });

        borrarProducto.setBackground(new java.awt.Color(192, 57, 43));
        borrarProducto.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        borrarProducto.setForeground(new java.awt.Color(255, 255, 255));
        borrarProducto.setText("Eliminar");
        borrarProducto.setBorder(null);
        borrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarProductoActionPerformed(evt);
            }
        });

        jSpinner1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner1.setBorder(null);
        jSpinner1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labelTicket.setBackground(new java.awt.Color(251, 251, 251));
        labelTicket.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        labelTicket.setForeground(new java.awt.Color(255, 255, 255));
        labelTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTicket.setText("Ticket-"+nTicket);

        javax.swing.GroupLayout jPVentaLayout = new javax.swing.GroupLayout(jPVenta);
        jPVenta.setLayout(jPVentaLayout);
        jPVentaLayout.setHorizontalGroup(
            jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPVentaLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(jPVentaLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(borrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(labelTicket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPVentaLayout.setVerticalGroup(
            jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPVentaLayout.createSequentialGroup()
                .addComponent(labelTicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSpinner1)
                    .addComponent(textBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        BuscarProductos bp = new BuscarProductos(this, false);
        bp.setVisible(true);

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jBcorteCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcorteCajaActionPerformed
        InicioSesion login = new InicioSesion();
        setVisible(false);
        login.setVisible(true);

    }//GEN-LAST:event_jBcorteCajaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        vaciarCarrito();
    }//GEN-LAST:event_jButton5ActionPerformed
    public static void vaciarCarrito() {
        try {
            if(model.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "El carrito esta vacio", "Mensaje", JOptionPane.OK_OPTION);
            }else{
            model.setRowCount(0);
            cobrar = 0;
            jLabel1.setText("$" + String.valueOf(cobrar));
            idProducto.removeAllElements();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El carrito esta vacio", "Mensaje", JOptionPane.OK_OPTION);
        }
    }
    private void borrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarProductoActionPerformed
        // TODO add your handling code here:
        try {
            if(model.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "El carrito esta vacio", "Mensaje", JOptionPane.OK_OPTION);
            }else{
            idProducto.removeElementAt(tablaVenta.getSelectedRow());
            DefaultTableModel dtm = (DefaultTableModel) tablaVenta.getModel();
            int canti = (int) dtm.getValueAt(tablaVenta.getSelectedRow(), 3);
            float precio = Float.parseFloat((String) dtm.getValueAt(tablaVenta.getSelectedRow(), 2));
            float quitarprecio = canti * precio;
            cobrar = cobrar - quitarprecio;
            jLabel1.setText("$" + String.valueOf(cobrar));
            dtm.removeRow(tablaVenta.getSelectedRow());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Producto no seleccionado", "Mensaje", JOptionPane.OK_OPTION);
        }

    }//GEN-LAST:event_borrarProductoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = tablaVenta.getRowCount();
        String query = "SELECT COUNT(*) FROM venta";
        Statement st;
        int count = 0;                
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                    count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (count!=0){
        count++;
        }else{
        count=1;
        }
        if (fila != 0) {
            String[] insert = new String[fila];
            for (int i = 0; i < fila; i++) {
                String sql = "INSERT INTO `venta` (`idventa`, `fecha`, `cantidad`, `cancelada`, `motivo`, `nventa`, `formaP`, `login_idlogin`, `producto_idproducto`, `clipro_idclipro`,`ventacol`) VALUES (NULL, CURRENT_TIMESTAMP, '" + tablaVenta.getValueAt(i, 3) + "', '0', NULL, ?, ?, ?, '" + idProducto.elementAt(i) + "', '5', '" + count + "');";
                insert[i] = sql;
            }
            Cobrar ventanaCobrar = new Cobrar(this, false, insert);
            ventanaCobrar.setLocationRelativeTo(null);
            ventanaCobrar.setVisible(true);
        } else {
            ImageIcon icon = new ImageIcon("src/img/error (2).png");
            JOptionPane.showMessageDialog(null, "El Carrito esta Vacio", "Mensaje", JOptionPane.OK_OPTION, icon);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void textBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            agregar();
            textBuscar.setText("");
        }
    }//GEN-LAST:event_textBuscarKeyPressed

    private void jBcancelarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBcancelarVActionPerformed

    private void jBcorteCaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcorteCaja1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBcorteCaja1ActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        setExtendedState(Ventas.CROSSHAIR_CURSOR);
    }//GEN-LAST:event_salirActionPerformed

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
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton borrarProducto;
    private javax.swing.JButton jBcancelarV;
    private javax.swing.JButton jBcorteCaja;
    private javax.swing.JButton jBcorteCaja1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPVenta;
    private javax.swing.JPanel jPcontenedor;
    private javax.swing.JPanel jPrincipal;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JSpinner jSpinner1;
    public static javax.swing.JLabel labelTicket;
    private javax.swing.JButton salir;
    public static javax.swing.JTable tablaVenta;
    public static javax.swing.JTextField textBuscar;
    // End of variables declaration//GEN-END:variables

}
