package Diseño;

import static Diseño.Ventas.conn;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.PrinterName;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Cobrar extends javax.swing.JDialog {

    Connection cn = conn.getConnection();
    String[] venta;

    public Cobrar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Cobrar(Ventas vnt, boolean modal) {
        super(vnt, modal);
        initComponents();
        this.setTitle("Vender");
    }

    public Cobrar(java.awt.Frame parent, boolean modal, String[] insert) {
        super(parent, modal);
        initComponents();
        venta = insert;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPMenu = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jCobro = new javax.swing.JComboBox<>();
        atras = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        agregar1 = new javax.swing.JButton();
        agregar2 = new javax.swing.JButton();
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
        jMixto = new javax.swing.JPanel();
        jPef2 = new javax.swing.JPanel();
        jPef8 = new javax.swing.JPanel();
        jPef10 = new javax.swing.JPanel();
        jPef11 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPef3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jPef4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPef5 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();

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
        jCobro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta", "Mixto" }));
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

        agregar.setBackground(new java.awt.Color(29, 184, 83));
        agregar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setText("Cobrar + Ticket");
        agregar.setBorder(null);
        agregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregar.setFocusPainted(false);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        agregar1.setBackground(new java.awt.Color(29, 184, 83));
        agregar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        agregar1.setForeground(new java.awt.Color(255, 255, 255));
        agregar1.setText("Cobrar");
        agregar1.setBorder(null);
        agregar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregar1.setFocusPainted(false);
        agregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar1ActionPerformed(evt);
            }
        });

        agregar2.setBackground(new java.awt.Color(29, 184, 83));
        agregar2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        agregar2.setForeground(new java.awt.Color(255, 255, 255));
        agregar2.setText("Cobrar + Hoja");
        agregar2.setBorder(null);
        agregar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregar2.setFocusPainted(false);
        agregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar2ActionPerformed(evt);
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
                            .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(agregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(agregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jMixto.setBackground(new java.awt.Color(40, 41, 41));
        jMixto.setMaximumSize(new java.awt.Dimension(464, 524));
        jMixto.setMinimumSize(new java.awt.Dimension(464, 524));
        jMixto.setPreferredSize(new java.awt.Dimension(464, 524));
        jMixto.setLayout(new java.awt.GridBagLayout());

        jPef2.setBackground(new java.awt.Color(40, 41, 41));
        jPef2.setLayout(new javax.swing.BoxLayout(jPef2, javax.swing.BoxLayout.Y_AXIS));

        jPef8.setBackground(new java.awt.Color(40, 41, 41));
        jPef8.setLayout(new javax.swing.BoxLayout(jPef8, javax.swing.BoxLayout.Y_AXIS));

        jPef10.setBackground(new java.awt.Color(40, 41, 41));
        jPef10.setLayout(new javax.swing.BoxLayout(jPef10, javax.swing.BoxLayout.Y_AXIS));

        jPef11.setBackground(new java.awt.Color(40, 41, 41));
        jPef11.setLayout(new javax.swing.BoxLayout(jPef11, javax.swing.BoxLayout.Y_AXIS));

        jPanel19.setBackground(new java.awt.Color(69, 69, 69));

        jLabel36.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 153, 0));
        jLabel36.setText(String.valueOf(Ventas.cobrar));
        jLabel36.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel36.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel36.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel37.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("TOTAL:");
        jLabel37.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel37.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel37.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel38.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 0));
        jLabel38.setText("$");
        jLabel38.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel38.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel38.setPreferredSize(new java.awt.Dimension(84, 30));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPef11.add(jPanel19);

        jPef10.add(jPef11);

        jPef8.add(jPef10);

        jPef2.add(jPef8);

        jPef3.setLayout(new javax.swing.BoxLayout(jPef3, javax.swing.BoxLayout.Y_AXIS));

        jPanel11.setBackground(new java.awt.Color(40, 41, 41));

        jLabel16.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("N° Tarjeta:");
        jLabel16.setName(""); // NOI18N

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField2.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPef3.add(jPanel11);

        jPef2.add(jPef3);

        jPef4.setBackground(new java.awt.Color(40, 41, 41));
        jPef4.setLayout(new javax.swing.BoxLayout(jPef4, javax.swing.BoxLayout.Y_AXIS));

        jPanel13.setBackground(new java.awt.Color(40, 41, 41));

        jLabel21.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Tarjeta:");
        jLabel21.setName(""); // NOI18N

        jLabel22.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("$");
        jLabel22.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel22.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel22.setPreferredSize(new java.awt.Dimension(84, 30));

        jTextField1.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jTextField1.setText("0.00");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPef4.add(jPanel13);

        jPef5.setBackground(new java.awt.Color(40, 41, 41));
        jPef5.setLayout(new javax.swing.BoxLayout(jPef5, javax.swing.BoxLayout.Y_AXIS));

        jPanel15.setBackground(new java.awt.Color(40, 41, 41));

        jLabel26.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Efectivo:");
        jLabel26.setName(""); // NOI18N

        jLabel27.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("$");
        jLabel27.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel27.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel27.setPreferredSize(new java.awt.Dimension(84, 30));

        jTextField2.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jTextField2.setText("0.00");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPef5.add(jPanel15);

        jPanel16.setBackground(new java.awt.Color(69, 69, 69));

        jLabel33.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 153, 0));
        jLabel33.setText("0.00");
        jLabel33.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel33.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel33.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel35.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Cambio:");
        jLabel35.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel35.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel35.setPreferredSize(new java.awt.Dimension(84, 30));

        jLabel34.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 153, 0));
        jLabel34.setText("$");
        jLabel34.setMaximumSize(new java.awt.Dimension(84, 30));
        jLabel34.setMinimumSize(new java.awt.Dimension(84, 30));
        jLabel34.setPreferredSize(new java.awt.Dimension(84, 30));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPef5.add(jPanel16);

        jPef4.add(jPef5);

        jPef2.add(jPef4);

        jMixto.add(jPef2, new java.awt.GridBagConstraints());

        jPContenedor.add(jMixto, "card2");

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

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed

    }//GEN-LAST:event_agregarActionPerformed

    private void jCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCobroActionPerformed
        // TODO add your handling code here:
        String panel = (String) jCobro.getSelectedItem();
        jPContenedor.removeAll();
        switch (panel) {
            case "Tarjeta":
                jPContenedor.add(jPtarjeta);
                break;
            case "Mixto":
                jPContenedor.add(jMixto);
                break;

            default:
                jPContenedor.add(jPefectivo);
                ;
                break;
        }
        jPContenedor.updateUI();
        jPContenedor.repaint();

    }//GEN-LAST:event_jCobroActionPerformed

    private void agregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar1ActionPerformed
     cobrar();
    }//GEN-LAST:event_agregar1ActionPerformed
    void cobrar() {
        //        
        String sql = "SELECT idlogin FROM login ORDER BY idlogin DESC LIMIT 1";
        Statement cp;
        String idlogin="";
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
                pps.setString(2, "efectivo");
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
    private void agregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar2ActionPerformed

    }//GEN-LAST:event_agregar2ActionPerformed

    void cobroMixto() {
        float tarjeta = Float.parseFloat(jTextField1.getText());
        float efectivo = Float.parseFloat(jTextField2.getText());
        float pago = tarjeta + efectivo;
        float cambio = pago - Ventas.cobrar;
        if (pago >= Ventas.cobrar) {
            jLabel35.setText("Cambio:");
            jLabel33.setText(String.valueOf(cambio));
        } else {
            jLabel35.setText("Falta:");
            cambio = cambio * (-1);
            jLabel33.setText(String.valueOf(cambio));
        }
    }

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

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        cobroMixto();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        cobroMixto();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();  // ignorar el evento de teclado
        }
    }//GEN-LAST:event_jTextField2KeyTyped

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
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cobrar dialog = new Cobrar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton agregar1;
    private javax.swing.JButton agregar2;
    private javax.swing.JButton atras;
    private javax.swing.JComboBox<String> jCobro;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jMixto;
    private javax.swing.JPanel jPContenedor;
    private javax.swing.JPanel jPMenu;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPef;
    private javax.swing.JPanel jPef1;
    private javax.swing.JPanel jPef10;
    private javax.swing.JPanel jPef11;
    private javax.swing.JPanel jPef2;
    private javax.swing.JPanel jPef3;
    private javax.swing.JPanel jPef4;
    private javax.swing.JPanel jPef5;
    private javax.swing.JPanel jPef7;
    private javax.swing.JPanel jPef8;
    private javax.swing.JPanel jPefectivo;
    private javax.swing.JPanel jPtarjeta;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    private void editarPDF() throws IOException, DocumentException {
        String src = "archivo.pdf";
        String dest = "archivo-edit.pdf";
        PdfReader reader = new PdfReader(src);
        PdfDictionary dict = reader.getPageN(1);
        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
        if (object instanceof PRStream) {
            PRStream stream = (PRStream) object;
            byte[] data = PdfReader.getStreamBytes(stream);
            stream.setData(new String(data).replace("Titulo", "Grupo Einwolk").getBytes());
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }

    private void imprimir() throws FileNotFoundException, IOException, PrintException {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("archivo.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Formato de Documento
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //Lectura de Documento
        Doc document = new SimpleDoc(inputStream, docFormat, null);
        PrinterJob job = PrinterJob.getPrinterJob();
        job.printDialog();
        String impresora = job.getPrintService().getName();
        //Nombre de la impresora
        String printerName = impresora;
        //Inclusion del nombre de impresora y sus atributos
        AttributeSet attributeSet = new HashAttributeSet();
        attributeSet.add(new PrinterName(printerName, null));
        attributeSet = new HashAttributeSet();
        //Soporte de color o no
        attributeSet.add(ColorSupported.NOT_SUPPORTED);
        //Busqueda de la impresora por el nombre asignado en attributeSet
        PrintService[] services = PrintServiceLookup.lookupPrintServices(docFormat, attributeSet);
        //En caso de que tengamos varias impresoras configuradas
        PrintService myPrinter = null;
        for (int i = 0; i < services.length; i++) {
            if (services[i].getName().equals(printerName)) {
                myPrinter = services[i];
                System.out.println("Imprimiendo en : " + services[i].getName());
                break;
            }
        }

        DocPrintJob printJob = myPrinter.createPrintJob();
        try {
            //Envio a la impresora
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            printJob.print(document, aset);
        } catch (PrintException ex) {
            Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
