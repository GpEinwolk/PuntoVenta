package Diseño;

import static Diseño.Ventas.conn;
import static Diseño.Ventas.model;
import static Diseño.Ventas.tablaVenta;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class Cobrar extends javax.swing.JDialog {

    Connection cn = conn.getConnection();
    String[] venta;
    DefaultTableModel de;
    boolean imprimir=true;
    public static JasperPrint jp;
    private final String logotipo = "/img/logoEmpresa.png";

    public Cobrar(java.awt.Frame parent, boolean modal, String[] insert) {
        super(parent, modal);
        initComponents();
        venta = insert;
        de = (DefaultTableModel) tablaVenta.getModel();
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPMenu = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jCobro = new javax.swing.JComboBox<>();
        atras = new javax.swing.JButton();
        cobroTicket = new javax.swing.JButton();
        btCobrar = new javax.swing.JButton();
        cobroHoja = new javax.swing.JButton();
        jPContenedor = new javax.swing.JPanel();
        jPefectivo = new javax.swing.JPanel();
        jPef = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPef7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPtarjeta = new javax.swing.JPanel();
        jPef1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(737, 524));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setBackground(new java.awt.Color(40, 41, 41));
        jPanel3.setMaximumSize(new java.awt.Dimension(845, 524));
        jPanel3.setMinimumSize(new java.awt.Dimension(845, 524));
        jPanel3.setPreferredSize(new java.awt.Dimension(845, 524));

        jPMenu.setBackground(new java.awt.Color(30, 30, 30));
        jPMenu.setMaximumSize(new java.awt.Dimension(239, 524));
        jPMenu.setMinimumSize(new java.awt.Dimension(239, 524));
        jPMenu.setRequestFocusEnabled(false);

        jLabel6.setBackground(new java.awt.Color(40, 41, 41));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tipo de cobro");

        jCobro.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jCobro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));
        jCobro.setToolTipText("");
        jCobro.setBorder(null);
        jCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCobroActionPerformed(evt);
            }
        });

        atras.setBackground(new java.awt.Color(69, 69, 69));
        atras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        atras.setForeground(new java.awt.Color(255, 255, 255));
        atras.setText("Atras");
        atras.setBorder(null);
        atras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atras.setFocusPainted(false);
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });

        cobroTicket.setBackground(new java.awt.Color(29, 184, 83));
        cobroTicket.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cobroTicket.setForeground(new java.awt.Color(255, 255, 255));
        cobroTicket.setText("Cobrar + Ticket");
        cobroTicket.setBorder(null);
        cobroTicket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cobroTicket.setFocusPainted(false);
        cobroTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobroTicketActionPerformed(evt);
            }
        });

        btCobrar.setBackground(new java.awt.Color(29, 184, 83));
        btCobrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btCobrar.setForeground(new java.awt.Color(255, 255, 255));
        btCobrar.setText("Cobrar");
        btCobrar.setBorder(null);
        btCobrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCobrar.setFocusPainted(false);
        btCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCobrarActionPerformed(evt);
            }
        });

        cobroHoja.setBackground(new java.awt.Color(29, 184, 83));
        cobroHoja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cobroHoja.setForeground(new java.awt.Color(255, 255, 255));
        cobroHoja.setText("Cobrar + Hoja");
        cobroHoja.setBorder(null);
        cobroHoja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cobroHoja.setFocusPainted(false);
        cobroHoja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobroHojaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPMenuLayout = new javax.swing.GroupLayout(jPMenu);
        jPMenu.setLayout(jPMenuLayout);
        jPMenuLayout.setHorizontalGroup(
            jPMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMenuLayout.createSequentialGroup()
                .addGroup(jPMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jCobro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                    .addGroup(jPMenuLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cobroTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cobroHoja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPMenuLayout.setVerticalGroup(
            jPMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(btCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cobroTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cobroHoja, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jPContenedor.setBackground(new java.awt.Color(40, 41, 41));
        jPContenedor.setMaximumSize(new java.awt.Dimension(464, 524));
        jPContenedor.setMinimumSize(new java.awt.Dimension(464, 524));
        jPContenedor.setPreferredSize(new java.awt.Dimension(464, 524));
        jPContenedor.setLayout(new java.awt.CardLayout());

        jPefectivo.setBackground(new java.awt.Color(40, 41, 41));
        jPefectivo.setPreferredSize(new java.awt.Dimension(464, 524));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {3};
        jPanel2Layout.rowHeights = new int[] {3};
        jPanel2Layout.columnWeights = new double[] {3.0};
        jPanel2Layout.rowWeights = new double[] {3.0};
        jPefectivo.setLayout(jPanel2Layout);

        jPef.setLayout(new javax.swing.BoxLayout(jPef, javax.swing.BoxLayout.Y_AXIS));

        jPanel6.setBackground(new java.awt.Color(69, 69, 69));

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText(String.valueOf(Ventas.cobrar));
        jLabel2.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel2.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel2.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("$");
        jLabel3.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel3.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel3.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TOTAL:");
        jLabel4.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel4.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel4.setPreferredSize(new java.awt.Dimension(84, 30));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPef.add(jPanel6);

        jPef7.setLayout(new javax.swing.BoxLayout(jPef7, javax.swing.BoxLayout.Y_AXIS));

        jPanel9.setBackground(new java.awt.Color(40, 41, 41));

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Efectivo:");
        jLabel8.setName(""); // NOI18N

        jTextField3.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jTextField3.setText(String.valueOf(Ventas.cobrar));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("$");
        jLabel12.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel12.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel12.setPreferredSize(new java.awt.Dimension(84, 30));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPef7.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(69, 69, 69));

        jLabel13.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 0));
        jLabel13.setText("0.00");
        jLabel13.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel13.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel13.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel14.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 0));
        jLabel14.setText("$");
        jLabel14.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel14.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel14.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel15.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Cambio:");
        jLabel15.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel15.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel15.setPreferredSize(new java.awt.Dimension(84, 30));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPef7.add(jPanel10);

        jPef.add(jPef7);

        jPefectivo.add(jPef, new java.awt.GridBagConstraints());

        jPContenedor.add(jPefectivo, "card2");

        jPtarjeta.setBackground(new java.awt.Color(40, 41, 41));
        jPtarjeta.setPreferredSize(new java.awt.Dimension(464, 524));
        jPtarjeta.setLayout(new java.awt.GridBagLayout());

        jPef1.setLayout(new javax.swing.BoxLayout(jPef1, javax.swing.BoxLayout.Y_AXIS));

        jPanel8.setBackground(new java.awt.Color(69, 69, 69));

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 0));
        jLabel9.setText(String.valueOf(Ventas.cobrar));
        jLabel9.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel9.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel9.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 0));
        jLabel10.setText("$");
        jLabel10.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel10.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel10.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel11.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TOTAL:");
        jLabel11.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel11.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel11.setPreferredSize(new java.awt.Dimension(84, 30));

        jPanel7.setBackground(new java.awt.Color(40, 41, 41));

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tarjeta:");
        jLabel7.setName(""); // NOI18N

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-####-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPef1.add(jPanel8);

        jPtarjeta.add(jPef1, new java.awt.GridBagConstraints());

        jPContenedor.add(jPtarjeta, "card2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(jPanel3);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_atrasActionPerformed

    private void cobroTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobroTicketActionPerformed
        cobro("ticket");
        imprimir=true;
    }//GEN-LAST:event_cobroTicketActionPerformed

    private void jCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCobroActionPerformed
        // TODO add your handling code here:
        String panel = (String) jCobro.getSelectedItem();
        jPContenedor.removeAll();
        switch (panel) {
            case "Tarjeta":
                jPContenedor.add(jPtarjeta);
                break;

            default:
                jPContenedor.add(jPefectivo);
                ;
                break;
        }
        jPContenedor.updateUI();
        jPContenedor.repaint();

    }//GEN-LAST:event_jCobroActionPerformed

    private void btCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCobrarActionPerformed
        cobro("ticket");
        imprimir=false;
    }//GEN-LAST:event_btCobrarActionPerformed
    private void cobro(String tipo) {
        switch ((String) jCobro.getSelectedItem()) {
            case "Tarjeta":
                crearReporte("Tarjeta", tipo, Double.parseDouble(jLabel9.getText()), 0.00, Double.parseDouble(jLabel9.getText()));
                cobrar();
                break;
            default:
                if ("Falta:".equals(jLabel15.getText())) {
                    ImageIcon ua = new ImageIcon("src/img/error (2).png");
                    JOptionPane.showMessageDialog(null, "Falta: $" + jLabel13.getText(), "Mensaje", JOptionPane.OK_OPTION, ua);
                } else {
                    crearReporte("Efectivo", tipo, Double.parseDouble(jLabel2.getText()), Double.parseDouble(jLabel13.getText()), Double.parseDouble(jTextField3.getText()));
                    cobrar();
                }
                break;
        }
    }

    private void crearReporte(String tipoPago, String tipo, double total, double cambio, double pago) {
        try {
            JRTableModelDataSource datasource = new JRTableModelDataSource(model);
            String reportSource = "/reportes/"+tipo + ".jrxml";
            JasperReport jr = JasperCompileManager.compileReport(reportSource);
            Map<String, Object> params = new HashMap<>();
            String sql = "SELECT * FROM datosempresa";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            params.clear();
            while (rs.next()) {
            params.put("nombreEmpresa", rs.getString(2));
            params.put("direccion", rs.getString(3));
            params.put("codigoPostal", rs.getString(4));
            params.put("telefono", rs.getString(5));
            params.put("rfc", rs.getString(6));
            }
            params.put("logo", this.getClass().getResourceAsStream(logotipo));
            params.put("nTicket", Ventas.nTicket);
            params.put("nombreUsuario", Ventas.Usuario);
            params.put("tipoPago", tipoPago);
            params.put("total", total);
            params.put("efectivo", pago);
            params.put("cambio", cambio);
            jp = JasperFillManager.fillReport(jr, params, datasource);
            if(imprimir==true){
            JasperPrintManager.printReport(jp, false);
            }else{
            JasperExportManager.exportReportToPdfFile(jp, tipo + ".pdf");
            }

        } catch (JRException e) {
            System.out.println(e);
            System.out.println(e.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println(ex.getMessage());
        }
    }

    void cobrar() {
        String formaP = jCobro.getSelectedItem().toString();
        String sql = "SELECT idlogin FROM login ORDER BY idlogin DESC LIMIT 1";
        Statement cp;
        String idlogin = "";
        try {
            cp = cn.createStatement();
            ResultSet rc = cp.executeQuery(sql);
            while (rc.next()) {
                idlogin = rc.getString("idlogin");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String venta1 : venta) {
            PreparedStatement pps;
            try {
                pps = cn.prepareStatement(venta1);
                pps.setString(1, String.valueOf(Ventas.nTicket));
                pps.setString(2, formaP);
                pps.setInt(3, Integer.parseInt(idlogin));
                pps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ImageIcon ua = new ImageIcon("src/img/cart (5).png");
        JOptionPane.showMessageDialog(null, "Se realizo la venta con exito", "Mensaje", JOptionPane.OK_OPTION, ua);
        Ventas.vaciarCarrito();
        Ventas.nTicket++;
        Ventas.labelTicket.setText("Ticket-" + Ventas.nTicket);
        dispose();
    }
    private void cobroHojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobroHojaActionPerformed
        cobro("hoja");
        imprimir=true;
    }//GEN-LAST:event_cobroHojaActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        float pago = Float.parseFloat(jTextField3.getText());
        float cambio = pago - Ventas.cobrar;

        if (pago >= Ventas.cobrar) {
            jLabel15.setText("Cambio:");
            jLabel13.setText(String.valueOf(cambio));
        } else {
            jLabel15.setText("Falta:");
            cambio = cambio * (-1);
            jLabel13.setText(String.valueOf(cambio));
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_jTextField3KeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras;
    private javax.swing.JButton btCobrar;
    private javax.swing.JButton cobroHoja;
    private javax.swing.JButton cobroTicket;
    private javax.swing.JComboBox<String> jCobro;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPContenedor;
    private javax.swing.JPanel jPMenu;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPef;
    private javax.swing.JPanel jPef1;
    private javax.swing.JPanel jPef7;
    private javax.swing.JPanel jPefectivo;
    private javax.swing.JPanel jPtarjeta;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
