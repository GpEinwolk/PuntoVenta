package Diseño;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public final class Interface extends javax.swing.JFrame {

    Icon ua;
    Icon nv;
    Conexion conn = new Conexion();
    Connection cn = conn.getConnection();
    DecimalFormat df = new DecimalFormat("#.00");

    public Interface() {
        initComponents();
        InicioSesion log = new InicioSesion();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Punto de Venta");

        Calendar actual = new GregorianCalendar();
        //fecha.setCalendar(actual);

        mostrarComboProducto();
        mostrarTablaProducto();
        mostrarTablaModificar();
        mostrarComboProductoMod();
        comboAlmacen();

    }

    public void cerrar() {

        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "Desea cerrar la aplicacion", "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {

            try {
                PreparedStatement pps = cn.prepareStatement("UPDATE login SET fechaSal=CURRENT_TIMESTAMP WHERE idlogin=(SELECT AUTO_INCREMENT -1 AS LastId FROM information_schema.tables WHERE TABLE_SCHEMA='puntovent' AND TABLE_NAME='login')");
                pps.executeUpdate();
                System.exit(0);
            } catch (SQLException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
        }
    }

    void mostrarTablaModificar() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Codigo");
        modelo.addColumn("Stock");
        modelo.addColumn("Costo");
        modelo.addColumn("Precio");
        modelo.addColumn("Utilidad %");
        modelo.addColumn("Caracteristicas");
        modelo.addColumn("Garantia");
        modelo.addColumn("Almacen");

        tablaDatosModificar.setModel(modelo);
        String sql = "SELECT producto.idproducto,producto.nombre,producto.codigo,producto.stock,producto.costo,producto.precio,producto.utilidad,producto.espef,garantia.tipo,almacen.nombre FROM producto,garantia,almacen WHERE producto.garantia_idgarantia = garantia.idgarantia AND producto.almacen_idalmacen = almacen.idalmacen";

        String datos[] = new String[10];
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                modelo.addRow(datos);

            }

            tablaDatosModificar.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comboAlmacen() {

        almacenMod.removeAllItems();
        almacen.removeAllItems();
        String sql = "SELECT nombre FROM almacen";

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                almacenMod.addItem(rs.getString("nombre"));
                almacen.addItem(rs.getString("nombre"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostrarComboProducto() {
        garant.removeAllItems();
        String sql = "SELECT tipo FROM garantia";

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                garant.addItem(rs.getString("tipo"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void mostrarComboProductoMod() {
        garantMod.removeAllItems();
        String sql = "SELECT tipo FROM garantia";

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                garantMod.addItem(rs.getString("tipo"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void mostrarTablaProducto() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Codigo");
        modelo.addColumn("Existencias");
        modelo.addColumn("Costo");
        modelo.addColumn("Precio");
        modelo.addColumn("Utilidad %");
        modelo.addColumn("Caracteristicas");
        modelo.addColumn("Garantia");
        modelo.addColumn("Almacen");

        tablaDatosProducto.setModel(modelo);
        String sql = "SELECT producto.nombre,producto.codigo,producto.stock,producto.costo,producto.precio,producto.utilidad,producto.espef,garantia.tipo,almacen.nombre FROM producto,garantia,almacen WHERE producto.garantia_idgarantia = garantia.idgarantia AND producto.almacen_idalmacen=almacen.idalmacen";

        String datos[] = new String[10];
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                modelo.addRow(datos);

            }

            tablaDatosProducto.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void guardarDatosProducto() {
        if (txtNombre.getText().equals("") || txtCant.getText().equals("")
                || txtCosto.getText().equals("") || txtPrecio.getText().equals("") || txtCaract.getText().equals("")) {

            nv = new ImageIcon("src/img/cart (13).png");
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Mensaje", JOptionPane.OK_OPTION, nv);

        } else {

            try {
                ///////////////////////////////////////////////////
                //////////////////////////////////////////////////
                /////////////////////////////////////////////////
                String sql = "SELECT idgarantia FROM garantia";
                Statement cp;
                String index = Integer.toString(garant.getSelectedIndex() + 1);
                String txtid = "";

                ///////////////////////////////////////////////////
                //////////////////////////////////////////////////
                /////////////////////////////////////////////////
                java.util.Date date = new java.util.Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                //  date = fecha.getDate();
                String fe = sdf.format(date);

                int cantidad = Integer.parseInt(txtCant.getText());
                double costo = Double.parseDouble(txtCosto.getText());
                double precio = Double.parseDouble(txtPrecio.getText());
                double utilidad = Double.parseDouble(jSutilid.getValue().toString());
                boolean servicio = false;

                ///////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////
                try {
                    cp = cn.createStatement();
                    ResultSet rc = cp.executeQuery(sql);
                    while (rc.next()) {
                        if (index.equals(rc.getString("idgarantia"))) {
                            txtid = index;
                        }

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
                }

                PreparedStatement pps = cn.prepareStatement("INSERT INTO producto(nombre,codigo,stock,costo,precio,utilidad,espef,servicio,garantia_idgarantia) VALUES(?,?,?,?,?,?,?,?,?,?)");
                pps.setString(1, txtNombre.getText());
                pps.setString(2, txtCodigo.getText());
                pps.setInt(3, cantidad);
                pps.setDouble(4, costo);
                pps.setDouble(5, precio);
                pps.setDouble(6, utilidad);
                pps.setString(7, txtCaract.getText());
                pps.setString(8, fe);
                pps.setBoolean(9, servicio);
                pps.setInt(10, Integer.parseInt(txtid));
                pps.executeUpdate();

                txtNombre.setText(null);
                txtCodigo.setText(null);
                txtCant.setText(null);
                txtCosto.setText(null);
                txtPrecio.setText(null);
                txtCaract.setText("");
                jSutilid.setValue(0.0f);
                ua = new ImageIcon("src/img/cart (5).png");

                JOptionPane.showMessageDialog(null, "Producto agregado exitosamente", "Mensaje", JOptionPane.OK_OPTION, ua);

            } catch (SQLException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    void modificarProducto() {

        ///////////////////////////////////////////////////
        //////////////////////////////////////////////////
        /////////////////////////////////////////////////
        String sql = "SELECT idgarantia,tipo FROM garantia";
        Statement cp;
        String index = garantMod.getSelectedItem().toString();
        String id = "";
        String sql2 = "SELECT idalmacen,nombre FROM almacen";
        Statement cp2;
        String index2 = almacenMod.getSelectedItem().toString();
        String id2 = "";

        try {
            cp = cn.createStatement();
            ResultSet rc = cp.executeQuery(sql);

            while (rc.next()) {
                if (index.equals(rc.getString("tipo"))) {
                    id = rc.getString("idgarantia");
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cp2 = cn.createStatement();
            ResultSet rc = cp2.executeQuery(sql2);

            while (rc.next()) {
                if (index2.equals(rc.getString("nombre"))) {
                    id2 = rc.getString("idalmacen");
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        //  date = modFecha.getDate();
        String fe = sdf.format(date);

        int cantidad = Integer.parseInt(txtModCant.getText());
        double costo = Double.parseDouble(txtModCosto.getText());
        double precio = Double.parseDouble(txtModPrecio.getText());
        double utilidad = Double.parseDouble(jSModUtilid.getValue().toString());
        int servicio = 0;

        ///////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////    
        //////////////////////////////////////////////////////////////////////////
        try {
            PreparedStatement pps = cn.prepareStatement("UPDATE producto SET nombre='"
                    + txtModNombre.getText() + "',codigo='" + txtModCodigo.getText()
                    + "',stock='" + cantidad + "',costo='" + costo + "',precio='"
                    + precio + "',utilidad='" + utilidad + "',espef='" + txtModArea.getText()
                    + "',servicio='" + servicio + "',garantia_idgarantia='" + id
                    + "',almacen_idalmacen='" + id2
                    + "' WHERE idproducto=" + txtID.getText() + "");
            pps.executeUpdate();
            mostrarTablaModificar();
            nv = new ImageIcon("src/img/cart (5).png");
            JOptionPane.showMessageDialog(null, "Datos actulizados exitosamente", "Mensaje", JOptionPane.OK_OPTION, nv);
        } catch (SQLException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterPanel = new javax.swing.JPanel();
        jPrincipal = new javax.swing.JPanel();
        jBcorteCaja = new javax.swing.JButton();
        jlabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPcontenedor = new javax.swing.JPanel();
        jPcorteCaja = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPdineroCaja = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPVentas = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPdatos = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPmodificar = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtModNombre = new javax.swing.JTextField();
        txtModCosto = new javax.swing.JTextField();
        txtModPrecio = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtModCant = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaDatosModificar = new javax.swing.JTable();
        actualizar = new javax.swing.JButton();
        jSModUtilid = new javax.swing.JSpinner();
        jLabel77 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        garantMod = new javax.swing.JComboBox<>();
        eliminar = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        txtModCodigo = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtModArea = new javax.swing.JTextArea();
        txtID = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        almacenMod = new javax.swing.JComboBox<>();
        jPcompras = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tablaDatosProducto = new javax.swing.JTable();
        agregar = new javax.swing.JButton();
        jSutilid = new javax.swing.JSpinner();
        jLabel83 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        garant = new javax.swing.JComboBox<>();
        agregarGarantia = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtCaract = new javax.swing.JTextArea();
        txtID1 = new javax.swing.JLabel();
        almacen = new javax.swing.JComboBox<>();
        agregarAlmacen = new javax.swing.JButton();
        jPproveedor = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        txtID2 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        txtNombre2 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        txtNombre3 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        txtNombre4 = new javax.swing.JTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel88 = new javax.swing.JLabel();
        almacen2 = new javax.swing.JComboBox<>();
        jLabel90 = new javax.swing.JLabel();
        almacen3 = new javax.swing.JComboBox<>();
        jLabel91 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel92 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel93 = new javax.swing.JLabel();
        txtNombre5 = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        almacen4 = new javax.swing.JComboBox<>();
        agregar1 = new javax.swing.JButton();
        jPcliente = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        txtNombre6 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        txtID3 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        txtNombre7 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        txtNombre8 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        txtNombre9 = new javax.swing.JTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jLabel105 = new javax.swing.JLabel();
        almacen5 = new javax.swing.JComboBox<>();
        jLabel106 = new javax.swing.JLabel();
        almacen6 = new javax.swing.JComboBox<>();
        jLabel107 = new javax.swing.JLabel();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jLabel108 = new javax.swing.JLabel();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel109 = new javax.swing.JLabel();
        txtNombre10 = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        almacen7 = new javax.swing.JComboBox<>();
        agregar2 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        añadirUsuario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        prodAgre = new javax.swing.JMenuItem();
        pordMod = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        serAgre = new javax.swing.JMenuItem();
        serMod = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        provAgre = new javax.swing.JMenuItem();
        provMod = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        clieAgre = new javax.swing.JMenuItem();
        clieMod = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(30, 30, 30));
        setMinimumSize(new java.awt.Dimension(1280, 613));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS));

        masterPanel.setBackground(new java.awt.Color(30, 30, 30));
        masterPanel.setLayout(new javax.swing.BoxLayout(masterPanel, javax.swing.BoxLayout.LINE_AXIS));

        jPrincipal.setBackground(new java.awt.Color(30, 30, 30));
        jPrincipal.setMinimumSize(new java.awt.Dimension(300, 613));
        jPrincipal.setPreferredSize(new java.awt.Dimension(300, 613));

        jBcorteCaja.setBackground(new java.awt.Color(30, 30, 30));
        jBcorteCaja.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jBcorteCaja.setForeground(new java.awt.Color(130, 129, 128));
        jBcorteCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/receipt (2).png"))); // NOI18N
        jBcorteCaja.setText("Corte de caja");
        jBcorteCaja.setBorder(null);
        jBcorteCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBcorteCaja.setFocusPainted(false);
        jBcorteCaja.setSelected(true);
        jBcorteCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcorteCajaActionPerformed(evt);
            }
        });

        jlabel.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jlabel.setForeground(new java.awt.Color(255, 255, 255));
        jlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/analytics (4).png"))); // NOI18N
        jlabel.setText("USUARIO");
        jlabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jlabel.setPreferredSize(new java.awt.Dimension(150, 150));
        jlabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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
                    .addComponent(jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPrincipalLayout.createSequentialGroup()
                        .addComponent(jBcorteCaja)
                        .addGap(0, 115, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPrincipalLayout.setVerticalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jBcorteCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        masterPanel.add(jPrincipal);

        jPcontenedor.setBackground(new java.awt.Color(40, 41, 41));
        jPcontenedor.setForeground(new java.awt.Color(40, 41, 41));
        jPcontenedor.setAlignmentX(0.0F);
        jPcontenedor.setMinimumSize(new java.awt.Dimension(985, 613));
        jPcontenedor.setPreferredSize(new java.awt.Dimension(985, 613));
        jPcontenedor.setLayout(new java.awt.CardLayout());

        jPcorteCaja.setBackground(new java.awt.Color(40, 41, 41));
        jPcorteCaja.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPcorteCaja.setMinimumSize(new java.awt.Dimension(985, 613));
        jPcorteCaja.setName(""); // NOI18N
        jPcorteCaja.setPreferredSize(new java.awt.Dimension(985, 613));

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Dinero en Caja");

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/notes (2).png"))); // NOI18N
        jLabel20.setText("Ventas Totales");

        jLabel18.setBackground(new java.awt.Color(251, 251, 251));
        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Corte de Caja");

        jPdineroCaja.setBackground(new java.awt.Color(251, 251, 251));
        jPdineroCaja.setLayout(new javax.swing.BoxLayout(jPdineroCaja, javax.swing.BoxLayout.X_AXIS));

        jPanel4.setBackground(new java.awt.Color(251, 251, 251));
        jPanel4.setPreferredSize(new java.awt.Dimension(90, 92));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jLabel25.setBackground(new java.awt.Color(40, 41, 41));
        jLabel25.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Fondo de caja: ");
        jLabel25.setOpaque(true);
        jPanel4.add(jLabel25);

        jLabel26.setBackground(new java.awt.Color(69, 69, 69));
        jLabel26.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Entradas:");
        jLabel26.setMaximumSize(new java.awt.Dimension(111, 23));
        jLabel26.setMinimumSize(new java.awt.Dimension(111, 23));
        jLabel26.setOpaque(true);
        jLabel26.setPreferredSize(new java.awt.Dimension(111, 23));
        jPanel4.add(jLabel26);

        jLabel28.setBackground(new java.awt.Color(40, 41, 41));
        jLabel28.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Salidas:");
        jLabel28.setMaximumSize(new java.awt.Dimension(111, 23));
        jLabel28.setMinimumSize(new java.awt.Dimension(111, 23));
        jLabel28.setOpaque(true);
        jLabel28.setPreferredSize(new java.awt.Dimension(111, 23));
        jPanel4.add(jLabel28);

        jLabel29.setBackground(new java.awt.Color(69, 69, 69));
        jLabel29.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("TOTAL:");
        jLabel29.setMaximumSize(new java.awt.Dimension(111, 23));
        jLabel29.setMinimumSize(new java.awt.Dimension(111, 23));
        jLabel29.setOpaque(true);
        jLabel29.setPreferredSize(new java.awt.Dimension(111, 23));
        jPanel4.add(jLabel29);

        jPdineroCaja.add(jPanel4);

        jPanel6.setBackground(new java.awt.Color(251, 251, 251));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.Y_AXIS));

        jLabel30.setBackground(new java.awt.Color(40, 41, 41));
        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel30.setText(" $");
        jLabel30.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel30.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel30.setOpaque(true);
        jLabel30.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jLabel30);

        jLabel37.setBackground(new java.awt.Color(69, 69, 69));
        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 153, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel37.setText("+ $");
        jLabel37.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel37.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel37.setOpaque(true);
        jLabel37.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jLabel37);

        jLabel38.setBackground(new java.awt.Color(40, 41, 41));
        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel38.setText("- $");
        jLabel38.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel38.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel38.setOpaque(true);
        jLabel38.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jLabel38);

        jLabel39.setBackground(new java.awt.Color(69, 69, 69));
        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel39.setText("$");
        jLabel39.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel39.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel39.setOpaque(true);
        jLabel39.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jLabel39);

        jPdineroCaja.add(jPanel6);

        jPanel5.setBackground(new java.awt.Color(251, 251, 251));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        jLabel27.setBackground(new java.awt.Color(40, 41, 41));
        jLabel27.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText("0.00");
        jLabel27.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel27.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel27.setName(""); // NOI18N
        jLabel27.setOpaque(true);
        jLabel27.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel5.add(jLabel27);

        jLabel40.setBackground(new java.awt.Color(69, 69, 69));
        jLabel40.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 153, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel40.setText("0.00");
        jLabel40.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel40.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel40.setOpaque(true);
        jLabel40.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel5.add(jLabel40);

        jLabel41.setBackground(new java.awt.Color(40, 41, 41));
        jLabel41.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel41.setText("0.00");
        jLabel41.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel41.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel41.setOpaque(true);
        jLabel41.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel5.add(jLabel41);

        jLabel42.setBackground(new java.awt.Color(69, 69, 69));
        jLabel42.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel42.setText("0.00");
        jLabel42.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel42.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel42.setOpaque(true);
        jLabel42.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel5.add(jLabel42);

        jPdineroCaja.add(jPanel5);

        jLabel22.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/graph (2).png"))); // NOI18N
        jLabel22.setText("Ganancia");

        jPVentas.setBackground(new java.awt.Color(40, 41, 41));
        jPVentas.setLayout(new javax.swing.BoxLayout(jPVentas, javax.swing.BoxLayout.X_AXIS));

        jPanel7.setBackground(new java.awt.Color(251, 251, 251));
        jPanel7.setPreferredSize(new java.awt.Dimension(90, 92));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.Y_AXIS));

        jLabel63.setBackground(new java.awt.Color(40, 41, 41));
        jLabel63.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("En Efectivo:");
        jLabel63.setMaximumSize(new java.awt.Dimension(170, 23));
        jLabel63.setMinimumSize(new java.awt.Dimension(170, 23));
        jLabel63.setOpaque(true);
        jLabel63.setPreferredSize(new java.awt.Dimension(170, 23));
        jPanel7.add(jLabel63);

        jLabel44.setBackground(new java.awt.Color(69, 69, 69));
        jLabel44.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Con Tarjeta de Credito:");
        jLabel44.setName(""); // NOI18N
        jLabel44.setOpaque(true);
        jLabel44.setRequestFocusEnabled(false);
        jPanel7.add(jLabel44);

        jLabel45.setBackground(new java.awt.Color(40, 41, 41));
        jLabel45.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Devoluciones:");
        jLabel45.setMaximumSize(new java.awt.Dimension(170, 23));
        jLabel45.setMinimumSize(new java.awt.Dimension(170, 23));
        jLabel45.setOpaque(true);
        jLabel45.setPreferredSize(new java.awt.Dimension(170, 23));
        jPanel7.add(jLabel45);

        jLabel46.setBackground(new java.awt.Color(69, 69, 69));
        jLabel46.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("TOTAL:");
        jLabel46.setMaximumSize(new java.awt.Dimension(170, 23));
        jLabel46.setMinimumSize(new java.awt.Dimension(170, 23));
        jLabel46.setOpaque(true);
        jLabel46.setPreferredSize(new java.awt.Dimension(170, 23));
        jPanel7.add(jLabel46);

        jPVentas.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(251, 251, 251));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.Y_AXIS));

        jLabel48.setBackground(new java.awt.Color(40, 41, 41));
        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 153, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel48.setText("+ $");
        jLabel48.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel48.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel48.setOpaque(true);
        jLabel48.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jLabel48);

        jLabel64.setBackground(new java.awt.Color(69, 69, 69));
        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 153, 0));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel64.setText("+ $");
        jLabel64.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel64.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel64.setOpaque(true);
        jLabel64.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jLabel64);

        jLabel49.setBackground(new java.awt.Color(40, 41, 41));
        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(204, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel49.setText("- $");
        jLabel49.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel49.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel49.setOpaque(true);
        jLabel49.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jLabel49);

        jLabel50.setBackground(new java.awt.Color(69, 69, 69));
        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel50.setText("$");
        jLabel50.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel50.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel50.setOpaque(true);
        jLabel50.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jLabel50);

        jPVentas.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(251, 251, 251));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.Y_AXIS));

        jLabel52.setBackground(new java.awt.Color(40, 41, 41));
        jLabel52.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 153, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel52.setText("0.00");
        jLabel52.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel52.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel52.setOpaque(true);
        jLabel52.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel9.add(jLabel52);

        jLabel65.setBackground(new java.awt.Color(69, 69, 69));
        jLabel65.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 153, 0));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel65.setText("0.00");
        jLabel65.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel65.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel65.setOpaque(true);
        jLabel65.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel9.add(jLabel65);

        jLabel53.setBackground(new java.awt.Color(40, 41, 41));
        jLabel53.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(204, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel53.setText("0.00");
        jLabel53.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel53.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel53.setOpaque(true);
        jLabel53.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel9.add(jLabel53);

        jLabel54.setBackground(new java.awt.Color(69, 69, 69));
        jLabel54.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel54.setText("0.00");
        jLabel54.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel54.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel54.setOpaque(true);
        jLabel54.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel9.add(jLabel54);

        jPVentas.add(jPanel9);

        jLabel21.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Ventas");

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(41, 128, 185));
        jLabel23.setText("00.00");
        jLabel23.setMaximumSize(new java.awt.Dimension(110, 30));
        jLabel23.setMinimumSize(new java.awt.Dimension(110, 30));
        jLabel23.setPreferredSize(new java.awt.Dimension(110, 30));

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(41, 128, 185));
        jLabel24.setText("$ ");

        jLabel43.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(41, 128, 185));
        jLabel43.setText("$ ");

        jLabel47.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(41, 128, 185));
        jLabel47.setText("00.00");
        jLabel47.setMaximumSize(new java.awt.Dimension(110, 30));
        jLabel47.setMinimumSize(new java.awt.Dimension(110, 30));
        jLabel47.setPreferredSize(new java.awt.Dimension(110, 30));

        jPdatos.setBackground(new java.awt.Color(40, 41, 41));

        jLabel51.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(41, 128, 185));
        jLabel51.setText("Usuario");

        jLabel55.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("A:");

        jLabel56.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Corte de");

        jLabel57.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(41, 128, 185));
        jLabel57.setText("0/0/0");

        jLabel59.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Fecha:");

        jLabel60.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("De:");

        jLabel61.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(41, 128, 185));
        jLabel61.setText("00:00");

        jLabel62.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(41, 128, 185));
        jLabel62.setText("00:00");

        jLabel58.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Ver corte de la fecha:");

        javax.swing.GroupLayout jPdatosLayout = new javax.swing.GroupLayout(jPdatos);
        jPdatos.setLayout(jPdatosLayout);
        jPdatosLayout.setHorizontalGroup(
            jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPdatosLayout.createSequentialGroup()
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPdatosLayout.createSequentialGroup()
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPdatosLayout.setVerticalGroup(
            jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPdatosLayout.createSequentialGroup()
                .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jLabel55)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62)
                    .addComponent(jLabel58)))
        );

        jLabel75.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Devoluciones:");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(251, 251, 251));
        jTable1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jTable1.setForeground(new java.awt.Color(51, 51, 51));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel76.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Salida de dinero:");

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBackground(new java.awt.Color(251, 251, 251));
        jTable2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jTable2.setForeground(new java.awt.Color(51, 51, 51));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPcorteCajaLayout = new javax.swing.GroupLayout(jPcorteCaja);
        jPcorteCaja.setLayout(jPcorteCajaLayout);
        jPcorteCajaLayout.setHorizontalGroup(
            jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPcorteCajaLayout.createSequentialGroup()
                .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPcorteCajaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPdatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPcorteCajaLayout.createSequentialGroup()
                                .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPcorteCajaLayout.createSequentialGroup()
                                        .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPcorteCajaLayout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addGap(294, 294, 294))
                                            .addGroup(jPcorteCajaLayout.createSequentialGroup()
                                                .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jPdineroCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcorteCajaLayout.createSequentialGroup()
                                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                                        .addComponent(jLabel24)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPcorteCajaLayout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel21)
                                            .addComponent(jPVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel75)
                                    .addComponent(jLabel76))
                                .addGap(0, 60, Short.MAX_VALUE))))
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );
        jPcorteCajaLayout.setVerticalGroup(
            jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPcorteCajaLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPdatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43))
                    .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPcorteCajaLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPcorteCajaLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPdineroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel75)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPcontenedor.add(jPcorteCaja, "card4");

        jPmodificar.setBackground(new java.awt.Color(40, 41, 41));
        jPmodificar.setAutoscrolls(true);
        jPmodificar.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPmodificar.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel31.setBackground(new java.awt.Color(40, 41, 41));
        jLabel31.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Nombre del producto ");
        jLabel31.setOpaque(true);

        txtModNombre.setBackground(new java.awt.Color(251, 251, 251));
        txtModNombre.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtModNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtModNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txtModCosto.setBackground(new java.awt.Color(251, 251, 251));
        txtModCosto.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtModCosto.setForeground(new java.awt.Color(51, 51, 51));
        txtModCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txtModPrecio.setBackground(new java.awt.Color(251, 251, 251));
        txtModPrecio.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtModPrecio.setForeground(new java.awt.Color(51, 51, 51));
        txtModPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel33.setBackground(new java.awt.Color(40, 41, 41));
        jLabel33.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Precio");
        jLabel33.setOpaque(true);

        jLabel34.setBackground(new java.awt.Color(40, 41, 41));
        jLabel34.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Almacen");
        jLabel34.setOpaque(true);

        jLabel69.setBackground(new java.awt.Color(40, 41, 41));
        jLabel69.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Costo");
        jLabel69.setOpaque(true);

        txtModCant.setBackground(new java.awt.Color(251, 251, 251));
        txtModCant.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtModCant.setForeground(new java.awt.Color(51, 51, 51));
        txtModCant.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel70.setBackground(new java.awt.Color(40, 41, 41));
        jLabel70.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Caracteristicas");
        jLabel70.setOpaque(true);

        jLabel73.setBackground(new java.awt.Color(40, 41, 41));
        jLabel73.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Cantidad");
        jLabel73.setOpaque(true);

        jLabel74.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Modificar un Producto");

        tablaDatosModificar = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaDatosModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaDatosModificar.setForeground(new java.awt.Color(51, 51, 51));
        tablaDatosModificar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaDatosModificar.setGridColor(new java.awt.Color(255, 255, 255));
        tablaDatosModificar.setSelectionBackground(new java.awt.Color(62, 226, 141));
        tablaDatosModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosModificarMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaDatosModificar);

        actualizar.setBackground(new java.awt.Color(29, 184, 83));
        actualizar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        actualizar.setForeground(new java.awt.Color(255, 255, 255));
        actualizar.setText("Actualizar");
        actualizar.setBorder(null);
        actualizar.setFocusPainted(false);
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        jSModUtilid.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jSModUtilid.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        jSModUtilid.setBorder(null);
        jSModUtilid.setOpaque(false);
        jSModUtilid.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSModUtilidStateChanged(evt);
            }
        });

        jLabel77.setBackground(new java.awt.Color(40, 41, 41));
        jLabel77.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Margen de utilidad");
        jLabel77.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("%");

        garantMod.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        garantMod.setForeground(new java.awt.Color(51, 51, 51));
        garantMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                garantModMouseClicked(evt);
            }
        });

        eliminar.setBackground(new java.awt.Color(231, 76, 60));
        eliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        eliminar.setForeground(new java.awt.Color(255, 255, 255));
        eliminar.setText("Eliminar");
        eliminar.setBorder(null);
        eliminar.setFocusPainted(false);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        jLabel78.setBackground(new java.awt.Color(40, 41, 41));
        jLabel78.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Tipo de garantia");
        jLabel78.setOpaque(true);

        txtModCodigo.setBackground(new java.awt.Color(251, 251, 251));
        txtModCodigo.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtModCodigo.setForeground(new java.awt.Color(51, 51, 51));
        txtModCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel79.setBackground(new java.awt.Color(40, 41, 41));
        jLabel79.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Codigo (Opcional)");
        jLabel79.setOpaque(true);

        txtModArea.setColumns(20);
        txtModArea.setRows(5);
        jScrollPane9.setViewportView(txtModArea);

        txtID.setBackground(new java.awt.Color(40, 41, 41));
        txtID.setForeground(new java.awt.Color(40, 41, 41));
        txtID.setText("0");

        almacenMod.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacenMod.setForeground(new java.awt.Color(51, 51, 51));
        almacenMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacenModMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPmodificarLayout = new javax.swing.GroupLayout(jPmodificar);
        jPmodificar.setLayout(jPmodificarLayout);
        jPmodificarLayout.setHorizontalGroup(
            jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmodificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmodificarLayout.createSequentialGroup()
                        .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPmodificarLayout.createSequentialGroup()
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtModCosto)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSModUtilid))
                                    .addComponent(jLabel77)))
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtModNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtModCant)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtModPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtModCodigo))
                        .addGap(18, 18, 18)
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(garantMod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(almacenMod, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                    .addComponent(jScrollPane8))
                .addContainerGap())
        );
        jPmodificarLayout.setVerticalGroup(
            jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmodificarLayout.createSequentialGroup()
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPmodificarLayout.createSequentialGroup()
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel73))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtModNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtModCant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPmodificarLayout.createSequentialGroup()
                                    .addComponent(jLabel79)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtModCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(almacenMod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(24, 24, 24)
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtModPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addComponent(jLabel78)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(garantMod, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addGap(23, 23, 23))
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addComponent(jLabel69)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtModCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPmodificarLayout.createSequentialGroup()
                                .addComponent(jLabel77)
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jSModUtilid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID)
                            .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPmodificarLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPcontenedor.add(jPmodificar, "card2");

        jPcompras.setBackground(new java.awt.Color(40, 41, 41));
        jPcompras.setAutoscrolls(true);
        jPcompras.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPcompras.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel32.setBackground(new java.awt.Color(40, 41, 41));
        jLabel32.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Nombre del producto ");
        jLabel32.setOpaque(true);

        txtNombre.setBackground(new java.awt.Color(251, 251, 251));
        txtNombre.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtCosto.setBackground(new java.awt.Color(251, 251, 251));
        txtCosto.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtCosto.setForeground(new java.awt.Color(51, 51, 51));
        txtCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txtPrecio.setBackground(new java.awt.Color(251, 251, 251));
        txtPrecio.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel35.setBackground(new java.awt.Color(40, 41, 41));
        jLabel35.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Precio");
        jLabel35.setOpaque(true);

        jLabel36.setBackground(new java.awt.Color(40, 41, 41));
        jLabel36.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Almacen");
        jLabel36.setOpaque(true);

        jLabel72.setBackground(new java.awt.Color(40, 41, 41));
        jLabel72.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Costo");
        jLabel72.setOpaque(true);

        txtCant.setBackground(new java.awt.Color(251, 251, 251));
        txtCant.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtCant.setForeground(new java.awt.Color(51, 51, 51));
        txtCant.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel80.setBackground(new java.awt.Color(40, 41, 41));
        jLabel80.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Caracteristicas");
        jLabel80.setOpaque(true);

        jLabel81.setBackground(new java.awt.Color(40, 41, 41));
        jLabel81.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Cantidad");
        jLabel81.setOpaque(true);

        jLabel82.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Agregar un Producto");

        tablaDatosProducto = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaDatosProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaDatosProducto.setForeground(new java.awt.Color(51, 51, 51));
        tablaDatosProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaDatosProducto.setGridColor(new java.awt.Color(255, 255, 255));
        tablaDatosProducto.setSelectionBackground(new java.awt.Color(62, 226, 141));
        tablaDatosProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosProductoMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tablaDatosProducto);

        agregar.setBackground(new java.awt.Color(29, 184, 83));
        agregar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setText("Agregar");
        agregar.setBorder(null);
        agregar.setFocusPainted(false);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        jSutilid.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jSutilid.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        jSutilid.setBorder(null);
        jSutilid.setOpaque(false);
        jSutilid.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSutilidStateChanged(evt);
            }
        });

        jLabel83.setBackground(new java.awt.Color(40, 41, 41));
        jLabel83.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Margen de utilidad");
        jLabel83.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("%");

        garant.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        garant.setForeground(new java.awt.Color(51, 51, 51));
        garant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                garantMouseClicked(evt);
            }
        });

        agregarGarantia.setBackground(new java.awt.Color(29, 184, 83));
        agregarGarantia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        agregarGarantia.setForeground(new java.awt.Color(255, 255, 255));
        agregarGarantia.setText("Agregar un tipo de garantia");
        agregarGarantia.setBorder(null);
        agregarGarantia.setFocusPainted(false);
        agregarGarantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarGarantiaActionPerformed(evt);
            }
        });

        jLabel84.setBackground(new java.awt.Color(40, 41, 41));
        jLabel84.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Tipo de garantia");
        jLabel84.setOpaque(true);

        txtCodigo.setBackground(new java.awt.Color(251, 251, 251));
        txtCodigo.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(51, 51, 51));
        txtCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel85.setBackground(new java.awt.Color(40, 41, 41));
        jLabel85.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Codigo (Opcional)");
        jLabel85.setOpaque(true);

        txtCaract.setColumns(20);
        txtCaract.setRows(5);
        jScrollPane11.setViewportView(txtCaract);

        txtID1.setBackground(new java.awt.Color(40, 41, 41));
        txtID1.setForeground(new java.awt.Color(40, 41, 41));
        txtID1.setText("0");

        almacen.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacen.setForeground(new java.awt.Color(51, 51, 51));
        almacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacenMouseClicked(evt);
            }
        });

        agregarAlmacen.setBackground(new java.awt.Color(29, 184, 83));
        agregarAlmacen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        agregarAlmacen.setForeground(new java.awt.Color(255, 255, 255));
        agregarAlmacen.setText("Agregar un almacen");
        agregarAlmacen.setBorder(null);
        agregarAlmacen.setFocusPainted(false);
        agregarAlmacen.setMaximumSize(new java.awt.Dimension(220, 25));
        agregarAlmacen.setMinimumSize(new java.awt.Dimension(220, 25));
        agregarAlmacen.setPreferredSize(new java.awt.Dimension(220, 25));
        agregarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarAlmacenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPcomprasLayout = new javax.swing.GroupLayout(jPcompras);
        jPcompras.setLayout(jPcomprasLayout);
        jPcomprasLayout.setHorizontalGroup(
            jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPcomprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10)
                    .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcomprasLayout.createSequentialGroup()
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(agregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCosto)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPcomprasLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSutilid))
                                    .addComponent(jLabel83)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCant)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodigo))
                                .addGap(18, 18, 18)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(garant, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(almacen, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agregarAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11)
                            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addGap(0, 21, Short.MAX_VALUE)
                                .addComponent(agregarGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPcomprasLayout.setVerticalGroup(
            jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPcomprasLayout.createSequentialGroup()
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPcomprasLayout.createSequentialGroup()
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel81))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcomprasLayout.createSequentialGroup()
                                    .addComponent(jLabel85)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(24, 24, 24)
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPcomprasLayout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPcomprasLayout.createSequentialGroup()
                                        .addComponent(jLabel84)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(garant, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addGap(23, 23, 23))
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcomprasLayout.createSequentialGroup()
                                .addComponent(jLabel83)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPcomprasLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPcomprasLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jSutilid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID1)
                            .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(agregarGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(agregarAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPcomprasLayout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
        );

        jPcontenedor.add(jPcompras, "card4");

        jPproveedor.setBackground(new java.awt.Color(40, 41, 41));
        jPproveedor.setAutoscrolls(true);
        jPproveedor.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPproveedor.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel66.setBackground(new java.awt.Color(40, 41, 41));
        jLabel66.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Nombre de la Empresa/Razon Social/Persona Fisica *");
        jLabel66.setOpaque(true);

        txtNombre1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre1.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre1ActionPerformed(evt);
            }
        });
        txtNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre1KeyTyped(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("Agregar un Proveedor");

        txtID2.setBackground(new java.awt.Color(40, 41, 41));
        txtID2.setForeground(new java.awt.Color(40, 41, 41));
        txtID2.setText("0");

        jLabel67.setBackground(new java.awt.Color(40, 41, 41));
        jLabel67.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("R.F.C. ");
        jLabel67.setOpaque(true);

        jLabel71.setBackground(new java.awt.Color(40, 41, 41));
        jLabel71.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Giro");
        jLabel71.setOpaque(true);

        jFormattedTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jFormattedTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });

        txtNombre2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre2.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre2KeyTyped(evt);
            }
        });

        jLabel68.setBackground(new java.awt.Color(40, 41, 41));
        jLabel68.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Direccion");
        jLabel68.setOpaque(true);

        txtNombre3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre3.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre3KeyTyped(evt);
            }
        });

        jLabel86.setBackground(new java.awt.Color(40, 41, 41));
        jLabel86.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Colonia");
        jLabel86.setOpaque(true);

        jLabel87.setBackground(new java.awt.Color(40, 41, 41));
        jLabel87.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Poblacion y/o ");
        jLabel87.setOpaque(true);

        txtNombre4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre4.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre4KeyTyped(evt);
            }
        });

        jFormattedTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jFormattedTextField2.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextField2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });

        jLabel88.setBackground(new java.awt.Color(40, 41, 41));
        jLabel88.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("No. Ext");
        jLabel88.setOpaque(true);

        almacen2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacen2.setForeground(new java.awt.Color(51, 51, 51));
        almacen2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacen2MouseClicked(evt);
            }
        });

        jLabel90.setBackground(new java.awt.Color(40, 41, 41));
        jLabel90.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Pais");
        jLabel90.setOpaque(true);

        almacen3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacen3.setForeground(new java.awt.Color(51, 51, 51));
        almacen3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacen3MouseClicked(evt);
            }
        });
        almacen3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacen3ActionPerformed(evt);
            }
        });

        jLabel91.setBackground(new java.awt.Color(40, 41, 41));
        jLabel91.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Estado");
        jLabel91.setOpaque(true);

        jFormattedTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jFormattedTextField3.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextField3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField3ActionPerformed(evt);
            }
        });

        jLabel92.setBackground(new java.awt.Color(40, 41, 41));
        jLabel92.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Codigo Postal");
        jLabel92.setOpaque(true);

        jFormattedTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jFormattedTextField4.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextField4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jFormattedTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField4ActionPerformed(evt);
            }
        });

        jLabel93.setBackground(new java.awt.Color(40, 41, 41));
        jLabel93.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("No. Int");
        jLabel93.setOpaque(true);

        txtNombre5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre5.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre5KeyTyped(evt);
            }
        });

        jLabel94.setBackground(new java.awt.Color(40, 41, 41));
        jLabel94.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("Correo ");
        jLabel94.setOpaque(true);

        jLabel95.setBackground(new java.awt.Color(40, 41, 41));
        jLabel95.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("Los campos marcados con ( * ) son olbigatorios");
        jLabel95.setOpaque(true);

        jLabel96.setBackground(new java.awt.Color(40, 41, 41));
        jLabel96.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(153, 153, 153));
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel96.setText("Proporcione los datos de la persona Fisica o Moral");
        jLabel96.setOpaque(true);

        jLabel97.setBackground(new java.awt.Color(40, 41, 41));
        jLabel97.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Delegacion");
        jLabel97.setOpaque(true);

        almacen4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacen4.setForeground(new java.awt.Color(51, 51, 51));
        almacen4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacen4MouseClicked(evt);
            }
        });
        almacen4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacen4ActionPerformed(evt);
            }
        });

        agregar1.setBackground(new java.awt.Color(29, 184, 83));
        agregar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        agregar1.setForeground(new java.awt.Color(255, 255, 255));
        agregar1.setText("Agregar");
        agregar1.setBorder(null);
        agregar1.setFocusPainted(false);
        agregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPproveedorLayout = new javax.swing.GroupLayout(jPproveedor);
        jPproveedor.setLayout(jPproveedorLayout);
        jPproveedorLayout.setHorizontalGroup(
            jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPproveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPproveedorLayout.createSequentialGroup()
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel97)
                                    .addComponent(jLabel87))
                                .addGap(10, 10, 10)
                                .addComponent(txtNombre4))
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPproveedorLayout.createSequentialGroup()
                                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel68)
                                            .addComponent(jLabel86)
                                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel71)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(almacen4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtNombre3)
                                            .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPproveedorLayout.createSequentialGroup()
                                        .addComponent(jLabel90)
                                        .addGap(18, 18, 18)
                                        .addComponent(almacen2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(248, 248, 248)
                                        .addComponent(jLabel91)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(almacen3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPproveedorLayout.createSequentialGroup()
                                        .addComponent(jLabel94)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(agregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel92)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtID2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel89, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPproveedorLayout.setVerticalGroup(
            jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel96)
                .addGap(18, 18, 18)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(jLabel71)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(almacen4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel68))
                                .addGap(47, 47, 47))
                            .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                    .addComponent(jLabel92)
                                    .addGap(4, 4, 4))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                    .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel93))
                                    .addGap(4, 4, 4)
                                    .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jFormattedTextField4)
                                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(47, 47, 47))))
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel86)))
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtNombre4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel87)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel97)))
                        .addGap(18, 18, 18)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(almacen2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel90)
                            .addComponent(almacen3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel91))
                        .addGap(18, 18, 18)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel94)
                            .addComponent(txtNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39)
                .addComponent(agregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPcontenedor.add(jPproveedor, "card4");

        jPcliente.setBackground(new java.awt.Color(40, 41, 41));
        jPcliente.setAutoscrolls(true);
        jPcliente.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPcliente.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel98.setBackground(new java.awt.Color(40, 41, 41));
        jLabel98.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Nombre de la Empresa/Razon Social/Persona Fisica *");
        jLabel98.setOpaque(true);

        txtNombre6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre6.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre6ActionPerformed(evt);
            }
        });
        txtNombre6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre6KeyTyped(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("Agregar un Cliente");

        txtID3.setBackground(new java.awt.Color(40, 41, 41));
        txtID3.setForeground(new java.awt.Color(40, 41, 41));
        txtID3.setText("0");

        jLabel100.setBackground(new java.awt.Color(40, 41, 41));
        jLabel100.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("R.F.C. ");
        jLabel100.setOpaque(true);

        jLabel101.setBackground(new java.awt.Color(40, 41, 41));
        jLabel101.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("Giro");
        jLabel101.setOpaque(true);

        jFormattedTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jFormattedTextField5.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextField5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jFormattedTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField5ActionPerformed(evt);
            }
        });

        txtNombre7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre7.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre7KeyTyped(evt);
            }
        });

        jLabel102.setBackground(new java.awt.Color(40, 41, 41));
        jLabel102.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Direccion");
        jLabel102.setOpaque(true);

        txtNombre8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre8.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre8KeyTyped(evt);
            }
        });

        jLabel103.setBackground(new java.awt.Color(40, 41, 41));
        jLabel103.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setText("Colonia");
        jLabel103.setOpaque(true);

        jLabel104.setBackground(new java.awt.Color(40, 41, 41));
        jLabel104.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 255));
        jLabel104.setText("Poblacion y/o ");
        jLabel104.setOpaque(true);

        txtNombre9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre9.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre9KeyTyped(evt);
            }
        });

        jFormattedTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jFormattedTextField6.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextField6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jFormattedTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField6ActionPerformed(evt);
            }
        });

        jLabel105.setBackground(new java.awt.Color(40, 41, 41));
        jLabel105.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setText("No. Ext");
        jLabel105.setOpaque(true);

        almacen5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacen5.setForeground(new java.awt.Color(51, 51, 51));
        almacen5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacen5MouseClicked(evt);
            }
        });

        jLabel106.setBackground(new java.awt.Color(40, 41, 41));
        jLabel106.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setText("Pais");
        jLabel106.setOpaque(true);

        almacen6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacen6.setForeground(new java.awt.Color(51, 51, 51));
        almacen6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacen6MouseClicked(evt);
            }
        });
        almacen6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacen6ActionPerformed(evt);
            }
        });

        jLabel107.setBackground(new java.awt.Color(40, 41, 41));
        jLabel107.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("Estado");
        jLabel107.setOpaque(true);

        jFormattedTextField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jFormattedTextField7.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextField7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jFormattedTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField7ActionPerformed(evt);
            }
        });

        jLabel108.setBackground(new java.awt.Color(40, 41, 41));
        jLabel108.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("Codigo Postal");
        jLabel108.setOpaque(true);

        jFormattedTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jFormattedTextField8.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextField8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jFormattedTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField8ActionPerformed(evt);
            }
        });

        jLabel109.setBackground(new java.awt.Color(40, 41, 41));
        jLabel109.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setText("No. Int");
        jLabel109.setOpaque(true);

        txtNombre10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombre10.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombre10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre10KeyTyped(evt);
            }
        });

        jLabel110.setBackground(new java.awt.Color(40, 41, 41));
        jLabel110.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setText("Correo ");
        jLabel110.setOpaque(true);

        jLabel111.setBackground(new java.awt.Color(40, 41, 41));
        jLabel111.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setText("Los campos marcados con ( * ) son olbigatorios");
        jLabel111.setOpaque(true);

        jLabel112.setBackground(new java.awt.Color(40, 41, 41));
        jLabel112.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(153, 153, 153));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel112.setText("Proporcione los datos de la persona Fisica o Moral");
        jLabel112.setOpaque(true);

        jLabel113.setBackground(new java.awt.Color(40, 41, 41));
        jLabel113.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setText("Delegacion");
        jLabel113.setOpaque(true);

        almacen7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacen7.setForeground(new java.awt.Color(51, 51, 51));
        almacen7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacen7MouseClicked(evt);
            }
        });
        almacen7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                almacen7ActionPerformed(evt);
            }
        });

        agregar2.setBackground(new java.awt.Color(29, 184, 83));
        agregar2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        agregar2.setForeground(new java.awt.Color(255, 255, 255));
        agregar2.setText("Agregar");
        agregar2.setBorder(null);
        agregar2.setFocusPainted(false);
        agregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPclienteLayout = new javax.swing.GroupLayout(jPcliente);
        jPcliente.setLayout(jPclienteLayout);
        jPclienteLayout.setHorizontalGroup(
            jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPclienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPclienteLayout.createSequentialGroup()
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel113)
                                    .addComponent(jLabel104))
                                .addGap(10, 10, 10)
                                .addComponent(txtNombre9))
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPclienteLayout.createSequentialGroup()
                                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel102)
                                            .addComponent(jLabel103)
                                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPclienteLayout.createSequentialGroup()
                                                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel101)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(almacen7, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtNombre8)
                                            .addComponent(txtNombre7, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPclienteLayout.createSequentialGroup()
                                        .addComponent(jLabel106)
                                        .addGap(18, 18, 18)
                                        .addComponent(almacen5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(248, 248, 248)
                                        .addComponent(jLabel107)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(almacen6, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPclienteLayout.createSequentialGroup()
                                        .addComponent(jLabel110)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(agregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel108)
                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtID3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPclienteLayout.setVerticalGroup(
            jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel111)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel112)
                .addGap(18, 18, 18)
                .addComponent(jLabel98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel100)
                            .addComponent(jLabel101)
                            .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(almacen7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel102))
                                .addGap(47, 47, 47))
                            .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                                    .addComponent(jLabel108)
                                    .addGap(4, 4, 4))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                                    .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel109))
                                    .addGap(4, 4, 4)
                                    .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jFormattedTextField8)
                                        .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(47, 47, 47))))
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombre8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel103)))
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel104)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel113)))
                        .addGap(18, 18, 18)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(almacen5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106)
                            .addComponent(almacen6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel107))
                        .addGap(18, 18, 18)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110)
                            .addComponent(txtNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39)
                .addComponent(agregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPcontenedor.add(jPcliente, "card4");

        masterPanel.add(jPcontenedor);

        getContentPane().add(masterPanel);

        jMenuBar2.setBackground(new java.awt.Color(30, 30, 30));
        jMenuBar2.setBorder(null);

        jMenu1.setBackground(new java.awt.Color(30, 30, 30));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Archivo");
        jMenu1.setOpaque(true);

        añadirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user (1).png"))); // NOI18N
        añadirUsuario.setText("Añadir Usuario");
        añadirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(añadirUsuario);

        jMenuBar2.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(30, 30, 30));
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Catalogos");
        jMenu2.setOpaque(true);

        jMenu5.setText("Productos");

        prodAgre.setText("Agregar");
        prodAgre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodAgreActionPerformed(evt);
            }
        });
        jMenu5.add(prodAgre);

        pordMod.setText("Modificar");
        pordMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pordModActionPerformed(evt);
            }
        });
        jMenu5.add(pordMod);

        jMenu2.add(jMenu5);

        jMenu6.setText("Servicios");

        serAgre.setText("Agregar");
        jMenu6.add(serAgre);

        serMod.setText("Modificar");
        jMenu6.add(serMod);

        jMenu2.add(jMenu6);

        jMenu7.setText("Provedores");

        provAgre.setText("Agregar");
        provAgre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provAgreActionPerformed(evt);
            }
        });
        jMenu7.add(provAgre);

        provMod.setText("Modificar");
        provMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provModActionPerformed(evt);
            }
        });
        jMenu7.add(provMod);

        jMenu2.add(jMenu7);

        jMenu9.setText("Cliente");

        clieAgre.setText("Agregar");
        clieAgre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clieAgreActionPerformed(evt);
            }
        });
        jMenu9.add(clieAgre);

        clieMod.setText("Modificar");
        clieMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clieModActionPerformed(evt);
            }
        });
        jMenu9.add(clieMod);

        jMenu2.add(jMenu9);

        jMenuBar2.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(30, 30, 30));
        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Herramientas");
        jMenu3.setOpaque(true);
        jMenuBar2.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(30, 30, 30));
        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Ayuda");
        jMenu4.setOpaque(true);
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBcorteCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcorteCajaActionPerformed
        jPcontenedor.removeAll();
        jPcontenedor.repaint();
        jPcontenedor.revalidate();
        jPcontenedor.add(jPcorteCaja);
        jPcontenedor.repaint();
        jPcontenedor.revalidate();
        mostrarTablaProducto();
    }//GEN-LAST:event_jBcorteCajaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        modificarProducto();
    }//GEN-LAST:event_actualizarActionPerformed

    private void jSModUtilidStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSModUtilidStateChanged

        if (jSModUtilid != null && !txtModCosto.getText().isEmpty()) {
            float costo = 0;
            costo = Float.parseFloat(txtModCosto.getText());
            float utilidad = 0;
            utilidad = (float) jSModUtilid.getValue();
            float total = costo + (costo * (utilidad / 100));
            String precio = String.valueOf(total);
            txtModPrecio.setText(precio);
        }
    }//GEN-LAST:event_jSModUtilidStateChanged

    private void garantModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_garantModMouseClicked
        mostrarComboProductoMod();
    }//GEN-LAST:event_garantModMouseClicked

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        String id = txtID.getText();
        if (id.equals("")) {

        } else {
            try {
                PreparedStatement pps = cn.prepareStatement("DELETE FROM producto WHERE idproducto='" + id + "'");
                pps.executeUpdate();
                mostrarTablaModificar();
            } catch (SQLException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void tablaDatosModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosModificarMouseClicked
        int fila = tablaDatosModificar.getSelectedRow();

        if (fila >= 0) {
            try {
                txtID.setText(tablaDatosModificar.getValueAt(fila, 0).toString());
                txtModNombre.setText(tablaDatosModificar.getValueAt(fila, 1).toString());
                txtModCodigo.setText(tablaDatosModificar.getValueAt(fila, 2).toString());
                txtModCant.setText(tablaDatosModificar.getValueAt(fila, 3).toString());
                txtModCosto.setText(tablaDatosModificar.getValueAt(fila, 4).toString());
                txtModPrecio.setText(tablaDatosModificar.getValueAt(fila, 5).toString());
                jSModUtilid.setValue(Float.parseFloat(tablaDatosModificar.getValueAt(fila, 6).toString()));
                txtModArea.setText(tablaDatosModificar.getValueAt(fila, 7).toString());
                garantMod.setSelectedItem(tablaDatosModificar.getValueAt(fila, 8));
                almacenMod.setSelectedItem(tablaDatosModificar.getValueAt(fila, 9));

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_tablaDatosModificarMouseClicked

    private void añadirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirUsuarioActionPerformed
        Interface inter = new Interface();
        AgregarUser ag = new AgregarUser(inter, true);
        ag.setVisible(true);

    }//GEN-LAST:event_añadirUsuarioActionPerformed

    private void agregarGarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarGarantiaActionPerformed
        Interface inter = new Interface();
        Garant gant = new Garant(inter, true);
        gant.setVisible(true);
    }//GEN-LAST:event_agregarGarantiaActionPerformed

    private void garantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_garantMouseClicked
        mostrarComboProducto();
    }//GEN-LAST:event_garantMouseClicked

    private void jSutilidStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSutilidStateChanged

        if (jSutilid != null && !txtCosto.getText().isEmpty()) {
            float costo = 0;
            costo = Float.parseFloat(txtCosto.getText());
            float utilidad = 0;
            utilidad = (float) jSutilid.getValue();
            float total = costo + (costo * (utilidad / 100));
            String precio = String.valueOf(total);
            txtPrecio.setText(precio);
        }
    }//GEN-LAST:event_jSutilidStateChanged

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        guardarDatosProducto();
        mostrarTablaProducto();
    }//GEN-LAST:event_agregarActionPerformed

    private void tablaDatosProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosProductoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaDatosProductoMouseClicked

    private void prodAgreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodAgreActionPerformed
        // TODO add your handling code here:
        jPcontenedor.removeAll();
        jPcontenedor.add(jPcompras);
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
        mostrarTablaProducto();

    }//GEN-LAST:event_prodAgreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    private void pordModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pordModActionPerformed
        // TODO add your handling code here:
        jPcontenedor.removeAll();
        jPcontenedor.add(jPmodificar);
        mostrarTablaModificar();
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
        mostrarComboProductoMod();
    }//GEN-LAST:event_pordModActionPerformed

    private void almacenModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacenModMouseClicked
        // TODO add your handling code here:
        comboAlmacen();
    }//GEN-LAST:event_almacenModMouseClicked

    private void almacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacenMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_almacenMouseClicked

    private void agregarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarAlmacenActionPerformed
        Interface inter = new Interface();
        Almacen alm = new Almacen(inter, true);
        alm.setVisible(true);
    }//GEN-LAST:event_agregarAlmacenActionPerformed

    private void clieModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clieModActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clieModActionPerformed

    private void provModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provModActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_provModActionPerformed

    private void txtNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1KeyTyped

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void txtNombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre2KeyTyped

    private void txtNombre3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre3KeyTyped

    private void txtNombre4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre4KeyTyped

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void almacen2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacen2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen2MouseClicked

    private void almacen3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacen3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen3MouseClicked

    private void almacen3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacen3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen3ActionPerformed

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void jFormattedTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField4ActionPerformed

    private void txtNombre5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre5KeyTyped

    private void txtNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre1ActionPerformed

    private void almacen4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacen4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen4MouseClicked

    private void almacen4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacen4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen4ActionPerformed

    private void agregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agregar1ActionPerformed

    private void txtNombre6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre6ActionPerformed

    private void txtNombre6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre6KeyTyped

    private void jFormattedTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField5ActionPerformed

    private void txtNombre7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre7KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre7KeyTyped

    private void txtNombre8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre8KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre8KeyTyped

    private void txtNombre9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre9KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre9KeyTyped

    private void jFormattedTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField6ActionPerformed

    private void almacen5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacen5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen5MouseClicked

    private void almacen6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacen6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen6MouseClicked

    private void almacen6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacen6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen6ActionPerformed

    private void jFormattedTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField7ActionPerformed

    private void jFormattedTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField8ActionPerformed

    private void txtNombre10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre10KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre10KeyTyped

    private void almacen7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacen7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen7MouseClicked

    private void almacen7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_almacen7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_almacen7ActionPerformed

    private void agregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agregar2ActionPerformed

    private void provAgreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provAgreActionPerformed
       
        jPcontenedor.removeAll();
        jPcontenedor.add(jPproveedor);
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
        
    }//GEN-LAST:event_provAgreActionPerformed

    private void clieAgreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clieAgreActionPerformed
       jPcontenedor.removeAll();
        jPcontenedor.add(jPcliente);
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
    }//GEN-LAST:event_clieAgreActionPerformed

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
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
                System.out.println(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton agregar;
    private javax.swing.JButton agregar1;
    private javax.swing.JButton agregar2;
    private javax.swing.JButton agregarAlmacen;
    private javax.swing.JButton agregarGarantia;
    private javax.swing.JComboBox<String> almacen;
    private javax.swing.JComboBox<String> almacen2;
    private javax.swing.JComboBox<String> almacen3;
    private javax.swing.JComboBox<String> almacen4;
    private javax.swing.JComboBox<String> almacen5;
    private javax.swing.JComboBox<String> almacen6;
    private javax.swing.JComboBox<String> almacen7;
    private javax.swing.JComboBox<String> almacenMod;
    private javax.swing.JMenuItem añadirUsuario;
    private javax.swing.JMenuItem clieAgre;
    private javax.swing.JMenuItem clieMod;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox<String> garant;
    private javax.swing.JComboBox<String> garantMod;
    private javax.swing.JButton jBcorteCaja;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPVentas;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPcliente;
    private javax.swing.JPanel jPcompras;
    private javax.swing.JPanel jPcontenedor;
    private javax.swing.JPanel jPcorteCaja;
    private javax.swing.JPanel jPdatos;
    private javax.swing.JPanel jPdineroCaja;
    private javax.swing.JPanel jPmodificar;
    private javax.swing.JPanel jPproveedor;
    private javax.swing.JPanel jPrincipal;
    private javax.swing.JSpinner jSModUtilid;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSutilid;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jlabel;
    private javax.swing.JPanel masterPanel;
    private javax.swing.JMenuItem pordMod;
    private javax.swing.JMenuItem prodAgre;
    private javax.swing.JMenuItem provAgre;
    private javax.swing.JMenuItem provMod;
    private javax.swing.JMenuItem serAgre;
    private javax.swing.JMenuItem serMod;
    private javax.swing.JTable tablaDatosModificar;
    private javax.swing.JTable tablaDatosProducto;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextArea txtCaract;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtID1;
    private javax.swing.JLabel txtID2;
    private javax.swing.JLabel txtID3;
    private javax.swing.JTextArea txtModArea;
    private javax.swing.JTextField txtModCant;
    private javax.swing.JTextField txtModCodigo;
    private javax.swing.JTextField txtModCosto;
    private javax.swing.JTextField txtModNombre;
    private javax.swing.JTextField txtModPrecio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtNombre10;
    private javax.swing.JTextField txtNombre2;
    private javax.swing.JTextField txtNombre3;
    private javax.swing.JTextField txtNombre4;
    private javax.swing.JTextField txtNombre5;
    private javax.swing.JTextField txtNombre6;
    private javax.swing.JTextField txtNombre7;
    private javax.swing.JTextField txtNombre8;
    private javax.swing.JTextField txtNombre9;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
