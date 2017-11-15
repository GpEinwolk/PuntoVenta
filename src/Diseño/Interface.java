package Diseño;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class Interface extends javax.swing.JFrame {

    Icon ua;
    Icon nv;
    Conexion conn = new Conexion();
    Connection cn = conn.getConnection();
    DecimalFormat df = new DecimalFormat("#.00");
    DefaultTableModel modelCP;
    TableRowSorter<TableModel> tr;
    java.util.Date date = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    java.text.SimpleDateFormat sdfM = new java.text.SimpleDateFormat("MM");
    String dateDia = sdf.format(date);
    String dateMes = sdfM.format(date);
    Vector idlogin;
    public Interface() {
        idlogin = new Vector(10, 2);
        initComponents();
        InicioSesion log = new InicioSesion();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Punto de Venta");

        mostrarComboProducto();
        mostrarTablaProducto();
        mostrarTablaModificar();
        mostrarComboProductoMod();
        comboAlmacen();
        mostrarDistribuidor();
        mostrarCompras();
        mostrarUsuarios();
        comboCorte();
        modelCP = (DefaultTableModel) tablaBusCP.getModel();
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
        modelo.addColumn("Unidad");
        modelo.addColumn("Codigo Alterno");
        modelo.addColumn("Garantia");
        modelo.addColumn("Almacen");

        tablaDatosModificar.setModel(modelo);
        String sql = "SELECT producto.idproducto,producto.nombre,producto.codigo,producto.stock,producto.costo,producto.precio,producto.utilidad,producto.espef,producto.unidadM,producto.codigoA,garantia.nombre,almacen.nombre FROM producto,garantia,almacen WHERE producto.garantia_idgarantia = garantia.idgarantia AND producto.almacen_idalmacen = almacen.idalmacen";

        String datos[] = new String[12];
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
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
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
        String sql = "SELECT nombre FROM garantia";

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                garant.addItem(rs.getString("nombre"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void mostrarComboProductoMod() {
        garantMod.removeAllItems();
        String sql = "SELECT nombre FROM garantia";

        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                garantMod.addItem(rs.getString("nombre"));

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
        modelo.addColumn("unidad");
        modelo.addColumn("codigoA");
        modelo.addColumn("Garantia");
        modelo.addColumn("Almacen");

        tablaDatosProducto.setModel(modelo);
        String sql = "SELECT producto.nombre,producto.codigo,producto.stock,producto.costo,producto.precio,producto.utilidad,producto.espef,producto.unidadM,codigoA,garantia.nombre,almacen.nombre FROM producto,garantia,almacen WHERE producto.garantia_idgarantia = garantia.idgarantia AND producto.almacen_idalmacen=almacen.idalmacen";

        String datos[] = new String[11];
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
                datos[10] = rs.getString(11);
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
                String sql = "SELECT idgarantia,idalmacen FROM garantia,almacen ";
                Statement cp;
                String index = Integer.toString(garant.getSelectedIndex() + 1);
                String index2 = Integer.toString(almacen.getSelectedIndex() + 1);
                String uniM = "";
                if (unidadM.getSelectedIndex() == 0) {
                    uniM = "pza";
                } else {
                    uniM = "kg";
                }

                String txtid = "";
                String almacenID = "";
                java.util.Date date = new java.util.Date();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                //  date = fecha.getDate();
                String fe = sdf.format(date);

                int cantidad = Integer.parseInt(txtCant.getText());
                double costo = Double.parseDouble(txtCosto.getText());
                double precio = Double.parseDouble(txtPrecio.getText());
                double utilidad = Double.parseDouble(jSutilid.getValue().toString());
                boolean servicio = false;

                try {
                    cp = cn.createStatement();
                    ResultSet rc = cp.executeQuery(sql);
                    while (rc.next()) {
                        if (index.equals(rc.getString("idgarantia"))) {
                            txtid = index;
                        }

                    }
                    while (rc.next()) {
                        if (index2.equals(rc.getString("idalmacen"))) {
                            almacenID = index2;
                        }

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
                }

                PreparedStatement pps = cn.prepareStatement("INSERT INTO producto(nombre, codigo, codigoA, stock, costo, precio, utilidad, espef, servicio,unidadM, garantia_idgarantia, almacen_idalmacen) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
                pps.setString(1, txtNombre.getText());
                pps.setString(2, txtCodigo.getText());
                pps.setString(3, txtcodigoA.getText());
                pps.setInt(4, cantidad);
                pps.setDouble(5, costo);
                pps.setDouble(6, precio);
                pps.setDouble(7, utilidad);
                pps.setString(8, txtCaract.getText());
                pps.setBoolean(9, servicio);
                pps.setString(10, uniM);
                pps.setInt(11, Integer.parseInt(txtid));
                pps.setInt(12, Integer.parseInt(txtid));
                pps.executeUpdate();

                txtNombre.setText(null);
                txtCodigo.setText(null);
                txtCant.setText(null);
                txtCosto.setText(null);
                txtPrecio.setText(null);
                txtCaract.setText("");
                txtcodigoA.setText("");
                jSutilid.setValue(0.0f);
                ua = new ImageIcon("src/img/cart (5).png");

                JOptionPane.showMessageDialog(null, "Producto agregado exitosamente", "Mensaje", JOptionPane.OK_OPTION, ua);

            } catch (SQLException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    void modificarProducto() {
        String sql = "SELECT idgarantia,nombre FROM garantia";
        Statement cp;
        String index = garantMod.getSelectedItem().toString();
        String id = "";
        String sql2 = "SELECT idalmacen,nombre FROM almacen";
        Statement cp2;
        String index2 = almacenMod.getSelectedItem().toString();
        String id2 = "";
        String uniM = "";
        if (unidadMod.getSelectedIndex() == 0) {
            uniM = "pza";
        } else {
            uniM = "kg";
        }

        try {
            cp = cn.createStatement();
            ResultSet rc = cp.executeQuery(sql);

            while (rc.next()) {
                if (index.equals(rc.getString("nombre"))) {
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

//        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
//        date = modFecha.getDate();
//        String fe = sdf.format(date);

        int cantidad = Integer.parseInt(txtModCant.getText());
        double costo = Double.parseDouble(txtModCosto.getText());
        double precio = Double.parseDouble(txtModPrecio.getText());
        double utilidad = Double.parseDouble(jSModUtilid.getValue().toString());
        int servicio = 0;

        try {
            PreparedStatement pps = cn.prepareStatement("UPDATE producto SET nombre='"
                    + txtModNombre.getText() + "',codigo='" + txtModCodigo.getText()
                    + "',stock='" + cantidad + "',costo='" + costo
                    + "',precio='" + precio + "',utilidad='" + utilidad
                    + "',espef='" + txtModArea.getText() + "',codigoA='" + txtModcodigoA.getText()
                    + "',servicio='" + servicio + "',unidadM='" + uniM
                    + "',garantia_idgarantia='" + id + "',almacen_idalmacen='" + id2
                    + "' WHERE idproducto=" + txtID.getText());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        masterPanel = new javax.swing.JPanel();
        jPrincipal = new javax.swing.JPanel();
        jBcorteCaja = new javax.swing.JButton();
        jUsuarioA = new javax.swing.JLabel();
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
        jLabel51 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPVentas = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        labelEfectivo = new javax.swing.JLabel();
        labelTarjeta = new javax.swing.JLabel();
        labelSalidas = new javax.swing.JLabel();
        labelDevoluciones = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPdatos = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        comboUsuario = new javax.swing.JComboBox<>();
        corteFecha = new com.toedter.calendar.JDateChooser();
        rbEspecifico = new javax.swing.JRadioButton();
        rbCdia = new javax.swing.JRadioButton();
        rbCm = new javax.swing.JRadioButton();
        comboCortes = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        tUsuarios = new javax.swing.JCheckBox();
        jLabel75 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDevoluciones = new javax.swing.JTable();
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
        almacenMod = new javax.swing.JComboBox<>();
        txtModcodigoA = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        unidadMod = new javax.swing.JComboBox<>();
        jLabel86 = new javax.swing.JLabel();
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
        almacen = new javax.swing.JComboBox<>();
        agregarAlmacen = new javax.swing.JButton();
        txtcodigoA = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        unidadM = new javax.swing.JComboBox<>();
        jPcliente = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        txtNombreC = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        txtID3 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txtRFCCliente = new javax.swing.JFormattedTextField();
        txtDirCliente = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        txtColCliente = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        txtPDcliente = new javax.swing.JTextField();
        txtNextCliente = new javax.swing.JFormattedTextField();
        jLabel105 = new javax.swing.JLabel();
        paisCliente = new javax.swing.JComboBox<>();
        jLabel106 = new javax.swing.JLabel();
        estadoCliente = new javax.swing.JComboBox<>();
        jLabel107 = new javax.swing.JLabel();
        txtCPcliente = new javax.swing.JFormattedTextField();
        jLabel108 = new javax.swing.JLabel();
        txtNintCliente = new javax.swing.JFormattedTextField();
        jLabel109 = new javax.swing.JLabel();
        txtCorreoCliente = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        giroCliente = new javax.swing.JComboBox<>();
        agregar2 = new javax.swing.JButton();
        jLabel114 = new javax.swing.JLabel();
        txtCalleCliente = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JFormattedTextField();
        comboRFCC = new javax.swing.JComboBox<>();
        jPproveedor = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        txtNombrePro = new javax.swing.JTextField();
        jLabel117 = new javax.swing.JLabel();
        txtID4 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        txtRFCPro = new javax.swing.JFormattedTextField();
        txtDirPro = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        txtColPro = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        txtPDPro = new javax.swing.JTextField();
        txtNextPro = new javax.swing.JFormattedTextField();
        jLabel123 = new javax.swing.JLabel();
        paisPro = new javax.swing.JComboBox<>();
        jLabel124 = new javax.swing.JLabel();
        estadoPro = new javax.swing.JComboBox<>();
        jLabel125 = new javax.swing.JLabel();
        txtCPPro = new javax.swing.JFormattedTextField();
        jLabel126 = new javax.swing.JLabel();
        txtNintPro = new javax.swing.JFormattedTextField();
        jLabel127 = new javax.swing.JLabel();
        txtCorreoPro = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        giroPro = new javax.swing.JComboBox<>();
        agregar3 = new javax.swing.JButton();
        jLabel132 = new javax.swing.JLabel();
        txtCallePro = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        txtCodPro = new javax.swing.JFormattedTextField();
        comboRFC = new javax.swing.JComboBox<>();
        jPmodificarCP = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        txtNombrePro1 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        txtID5 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        botonActualizar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaBusCP = new javax.swing.JTable();
        jPAinv = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        txtCNA = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        txtID6 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        botonActualizar1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaComprar = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        spinnerCantidad = new javax.swing.JSpinner();
        jLabel88 = new javax.swing.JLabel();
        comboDistribuidor = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        jPAinvC = new javax.swing.JPanel();
        jLabel140 = new javax.swing.JLabel();
        txtNombrePro4 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        txtID8 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jLabel154 = new javax.swing.JLabel();
        botonActualizar3 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaBusCP3 = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        añadirUsuario = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        prodAgre = new javax.swing.JMenuItem();
        pordMod = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        serAgre = new javax.swing.JMenuItem();
        serMod = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu7 = new javax.swing.JMenu();
        provAgre = new javax.swing.JMenuItem();
        provMod = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        clieAgre = new javax.swing.JMenuItem();
        clieMod = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

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

        jUsuarioA.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jUsuarioA.setForeground(new java.awt.Color(255, 255, 255));
        jUsuarioA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jUsuarioA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/analytics (4).png"))); // NOI18N
        jUsuarioA.setText("USUARIO");
        jUsuarioA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jUsuarioA.setPreferredSize(new java.awt.Dimension(150, 150));
        jUsuarioA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPrincipalLayout.createSequentialGroup()
                        .addComponent(jBcorteCaja)
                        .addGap(0, 115, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jUsuarioA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPrincipalLayout.setVerticalGroup(
            jPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jUsuarioA, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel18.setText("Analytics");

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
        jLabel29.setText("Devoluciones:");
        jLabel29.setMaximumSize(new java.awt.Dimension(111, 23));
        jLabel29.setMinimumSize(new java.awt.Dimension(111, 23));
        jLabel29.setOpaque(true);
        jLabel29.setPreferredSize(new java.awt.Dimension(111, 23));
        jPanel4.add(jLabel29);

        jLabel51.setBackground(new java.awt.Color(40, 41, 41));
        jLabel51.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("TOTAL:");
        jLabel51.setMaximumSize(new java.awt.Dimension(111, 23));
        jLabel51.setMinimumSize(new java.awt.Dimension(111, 23));
        jLabel51.setOpaque(true);
        jLabel51.setPreferredSize(new java.awt.Dimension(111, 23));
        jPanel4.add(jLabel51);

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

        jLabel60.setBackground(new java.awt.Color(69, 69, 69));
        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(204, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel60.setText("- $");
        jLabel60.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel60.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel60.setOpaque(true);
        jLabel60.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jLabel60);

        jLabel55.setBackground(new java.awt.Color(40, 41, 41));
        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel55.setText("$");
        jLabel55.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel55.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel55.setOpaque(true);
        jLabel55.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jLabel55);

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

        jLabel61.setBackground(new java.awt.Color(69, 69, 69));
        jLabel61.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(204, 0, 0));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel61.setText("0.00");
        jLabel61.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel61.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel61.setOpaque(true);
        jLabel61.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel5.add(jLabel61);

        jLabel59.setBackground(new java.awt.Color(40, 41, 41));
        jLabel59.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel59.setText("0.00");
        jLabel59.setMaximumSize(new java.awt.Dimension(110, 23));
        jLabel59.setMinimumSize(new java.awt.Dimension(110, 23));
        jLabel59.setOpaque(true);
        jLabel59.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel5.add(jLabel59);

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
        jLabel44.setText("Con Tarjeta:");
        jLabel44.setMaximumSize(new java.awt.Dimension(170, 23));
        jLabel44.setMinimumSize(new java.awt.Dimension(170, 23));
        jLabel44.setName(""); // NOI18N
        jLabel44.setOpaque(true);
        jLabel44.setPreferredSize(new java.awt.Dimension(170, 23));
        jLabel44.setRequestFocusEnabled(false);
        jPanel7.add(jLabel44);

        jLabel45.setBackground(new java.awt.Color(40, 41, 41));
        jLabel45.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Salidas:");
        jLabel45.setMaximumSize(new java.awt.Dimension(170, 23));
        jLabel45.setMinimumSize(new java.awt.Dimension(170, 23));
        jLabel45.setOpaque(true);
        jLabel45.setPreferredSize(new java.awt.Dimension(170, 23));
        jPanel7.add(jLabel45);

        jLabel62.setBackground(new java.awt.Color(69, 69, 69));
        jLabel62.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Devoluciones:");
        jLabel62.setMaximumSize(new java.awt.Dimension(170, 23));
        jLabel62.setMinimumSize(new java.awt.Dimension(170, 23));
        jLabel62.setOpaque(true);
        jLabel62.setPreferredSize(new java.awt.Dimension(170, 23));
        jPanel7.add(jLabel62);

        jLabel46.setBackground(new java.awt.Color(40, 41, 41));
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

        jLabel71.setBackground(new java.awt.Color(69, 69, 69));
        jLabel71.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(204, 0, 0));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel71.setText("- $");
        jLabel71.setMaximumSize(new java.awt.Dimension(100, 23));
        jLabel71.setMinimumSize(new java.awt.Dimension(100, 23));
        jLabel71.setOpaque(true);
        jLabel71.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jLabel71);

        jLabel50.setBackground(new java.awt.Color(40, 41, 41));
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

        labelEfectivo.setBackground(new java.awt.Color(40, 41, 41));
        labelEfectivo.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelEfectivo.setForeground(new java.awt.Color(0, 153, 0));
        labelEfectivo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelEfectivo.setText("0.00");
        labelEfectivo.setMaximumSize(new java.awt.Dimension(110, 23));
        labelEfectivo.setMinimumSize(new java.awt.Dimension(110, 23));
        labelEfectivo.setOpaque(true);
        labelEfectivo.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel9.add(labelEfectivo);

        labelTarjeta.setBackground(new java.awt.Color(69, 69, 69));
        labelTarjeta.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelTarjeta.setForeground(new java.awt.Color(0, 153, 0));
        labelTarjeta.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelTarjeta.setText("0.00");
        labelTarjeta.setMaximumSize(new java.awt.Dimension(110, 23));
        labelTarjeta.setMinimumSize(new java.awt.Dimension(110, 23));
        labelTarjeta.setOpaque(true);
        labelTarjeta.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel9.add(labelTarjeta);

        labelSalidas.setBackground(new java.awt.Color(40, 41, 41));
        labelSalidas.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelSalidas.setForeground(new java.awt.Color(204, 0, 0));
        labelSalidas.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelSalidas.setText("0.00");
        labelSalidas.setMaximumSize(new java.awt.Dimension(110, 23));
        labelSalidas.setMinimumSize(new java.awt.Dimension(110, 23));
        labelSalidas.setOpaque(true);
        labelSalidas.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel9.add(labelSalidas);

        labelDevoluciones.setBackground(new java.awt.Color(69, 69, 69));
        labelDevoluciones.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelDevoluciones.setForeground(new java.awt.Color(204, 0, 0));
        labelDevoluciones.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelDevoluciones.setText("0.00");
        labelDevoluciones.setMaximumSize(new java.awt.Dimension(110, 23));
        labelDevoluciones.setMinimumSize(new java.awt.Dimension(110, 23));
        labelDevoluciones.setOpaque(true);
        labelDevoluciones.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel9.add(labelDevoluciones);

        jLabel54.setBackground(new java.awt.Color(40, 41, 41));
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

        jLabel56.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Usuario:");

        jLabel58.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Ver corte de la fecha:");

        comboUsuario.setToolTipText("");
        comboUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                usuarioChange(evt);
            }
        });
        comboUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboUsuarioMouseClicked(evt);
            }
        });

        corteFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                corteFechaPropertyChange(evt);
            }
        });

        buttonGroup1.add(rbEspecifico);
        rbEspecifico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rbEspecifico.setForeground(new java.awt.Color(255, 255, 255));
        rbEspecifico.setText("Especifico");
        rbEspecifico.setContentAreaFilled(false);
        rbEspecifico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                group1(evt);
            }
        });

        buttonGroup1.add(rbCdia);
        rbCdia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rbCdia.setForeground(new java.awt.Color(255, 255, 255));
        rbCdia.setText("Corte del Dia");
        rbCdia.setContentAreaFilled(false);
        rbCdia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                group1(evt);
            }
        });

        buttonGroup1.add(rbCm);
        rbCm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rbCm.setForeground(new java.awt.Color(255, 255, 255));
        rbCm.setText("Corte del Mes");
        rbCm.setContentAreaFilled(false);
        rbCm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                group1(evt);
            }
        });

        comboCortes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCortesActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Cortes:");

        tUsuarios.setBackground(new java.awt.Color(40, 41, 41));
        tUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        tUsuarios.setText("Todos los usuarios");
        tUsuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tUsuariosItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPdatosLayout = new javax.swing.GroupLayout(jPdatos);
        jPdatos.setLayout(jPdatosLayout);
        jPdatosLayout.setHorizontalGroup(
            jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPdatosLayout.createSequentialGroup()
                .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPdatosLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tUsuarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbEspecifico)
                        .addGap(18, 18, 18)
                        .addComponent(rbCdia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbCm))
                    .addGroup(jPdatosLayout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(corteFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCortes, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPdatosLayout.setVerticalGroup(
            jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPdatosLayout.createSequentialGroup()
                .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbEspecifico)
                        .addComponent(rbCdia)
                        .addComponent(rbCm))
                    .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tUsuarios)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPdatosLayout.createSequentialGroup()
                        .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(corteFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(comboCortes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        Date date = new Date();
        corteFecha.setDate(date);
        rbCdia.setSelected(true);

        jLabel75.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Devoluciones:");

        tablaDevoluciones.setAutoCreateRowSorter(true);
        tablaDevoluciones.setBackground(new java.awt.Color(251, 251, 251));
        tablaDevoluciones.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tablaDevoluciones.setForeground(new java.awt.Color(51, 51, 51));
        tablaDevoluciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Producto", "Cantidad", "Importe", "Vendedor", "Motivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaDevoluciones);

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
                                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPcorteCajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPdineroCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel75)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
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
        txtModCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        txtModPrecio.setBackground(new java.awt.Color(251, 251, 251));
        txtModPrecio.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtModPrecio.setForeground(new java.awt.Color(51, 51, 51));
        txtModPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtModPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

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
        txtModCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

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
        jSModUtilid.setEditor(new javax.swing.JSpinner.NumberEditor(jSModUtilid, ""));
        jSModUtilid.setOpaque(false);
        jSModUtilid.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSModUtilidStateChanged(evt);
            }
        });
        jSModUtilid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
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
        txtModCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        jLabel79.setBackground(new java.awt.Color(40, 41, 41));
        jLabel79.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Codigo de barras");
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
                almacen(evt);
            }
        });

        txtModcodigoA.setBackground(new java.awt.Color(251, 251, 251));
        txtModcodigoA.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtModcodigoA.setForeground(new java.awt.Color(51, 51, 51));
        txtModcodigoA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtModcodigoA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModcodigoAvalidar(evt);
            }
        });

        jLabel66.setBackground(new java.awt.Color(40, 41, 41));
        jLabel66.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Codigo Alternativo");
        jLabel66.setOpaque(true);

        unidadMod.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        unidadMod.setForeground(new java.awt.Color(51, 51, 51));
        unidadMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por unidad - Pza", "A Granel (Usa Decimales)" }));
        unidadMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unidadModMouseClicked(evt);
            }
        });
        unidadMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unidadModActionPerformed(evt);
            }
        });

        jLabel86.setBackground(new java.awt.Color(40, 41, 41));
        jLabel86.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Unidad de medida");
        jLabel86.setOpaque(true);

        javax.swing.GroupLayout jPmodificarLayout = new javax.swing.GroupLayout(jPmodificar);
        jPmodificar.setLayout(jPmodificarLayout);
        jPmodificarLayout.setHorizontalGroup(
            jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmodificarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8)
                    .addGroup(jPmodificarLayout.createSequentialGroup()
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPmodificarLayout.createSequentialGroup()
                                    .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtModNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtModCant)
                                        .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
                                .addGroup(jPmodificarLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtModCosto)
                                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPmodificarLayout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSModUtilid))
                                        .addComponent(jLabel77))))
                            .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtModcodigoA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addComponent(jLabel86)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(unidadMod, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtModPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtModCodigo))
                                .addGap(18, 18, 18)
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(almacenMod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addComponent(garantMod, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane9)))))))
                .addContainerGap())
        );
        jPmodificarLayout.setVerticalGroup(
            jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmodificarLayout.createSequentialGroup()
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                        .addGap(30, 30, 30)
                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPmodificarLayout.createSequentialGroup()
                                        .addComponent(jLabel77)
                                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addComponent(jSModUtilid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                                .addComponent(jLabel66)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPmodificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtModcodigoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(unidadMod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel86))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID))
                            .addGroup(jPmodificarLayout.createSequentialGroup()
                                .addComponent(jLabel69)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtModCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPmodificarLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
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

        txtCosto.setBackground(new java.awt.Color(251, 251, 251));
        txtCosto.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtCosto.setForeground(new java.awt.Color(51, 51, 51));
        txtCosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        txtPrecio.setBackground(new java.awt.Color(251, 251, 251));
        txtPrecio.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

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
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

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
        jScrollPane10.setViewportView(tablaDatosProducto);

        agregar.setBackground(new java.awt.Color(29, 184, 83));
        agregar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setText("Agregar Producto");
        agregar.setBorder(null);
        agregar.setFocusPainted(false);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        jSutilid.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jSutilid.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        jSutilid.setOpaque(false);
        jSutilid.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSutilidStateChanged(evt);
            }
        });
        jSutilid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
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
        agregarGarantia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        agregarGarantia.setForeground(new java.awt.Color(255, 255, 255));
        agregarGarantia.setText("Agregar una garantia");
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
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        jLabel85.setBackground(new java.awt.Color(40, 41, 41));
        jLabel85.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Codigo (Opcional)");
        jLabel85.setOpaque(true);

        txtCaract.setColumns(20);
        txtCaract.setRows(5);
        jScrollPane11.setViewportView(txtCaract);

        almacen.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        almacen.setForeground(new java.awt.Color(51, 51, 51));
        almacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                almacen(evt);
            }
        });

        agregarAlmacen.setBackground(new java.awt.Color(29, 184, 83));
        agregarAlmacen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        txtcodigoA.setBackground(new java.awt.Color(251, 251, 251));
        txtcodigoA.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txtcodigoA.setForeground(new java.awt.Color(51, 51, 51));
        txtcodigoA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtcodigoA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoAvalidar(evt);
            }
        });

        jLabel67.setBackground(new java.awt.Color(40, 41, 41));
        jLabel67.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Codigo Alternativo");
        jLabel67.setOpaque(true);

        jLabel87.setBackground(new java.awt.Color(40, 41, 41));
        jLabel87.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Unidad de medida");
        jLabel87.setOpaque(true);

        unidadM.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        unidadM.setForeground(new java.awt.Color(51, 51, 51));
        unidadM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por unidad - Pza", "A Granel (Usa Decimales)" }));

        javax.swing.GroupLayout jPcomprasLayout = new javax.swing.GroupLayout(jPcompras);
        jPcompras.setLayout(jPcomprasLayout);
        jPcomprasLayout.setHorizontalGroup(
            jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPcomprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPcomprasLayout.createSequentialGroup()
                        .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPcomprasLayout.createSequentialGroup()
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCosto, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPcomprasLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSutilid, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(agregarAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(agregarGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(garant, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPcomprasLayout.createSequentialGroup()
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcodigoA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel87)
                                    .addComponent(unidadM, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addComponent(jScrollPane10)))
        );
        jPcomprasLayout.setVerticalGroup(
            jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPcomprasLayout.createSequentialGroup()
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPcomprasLayout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPcomprasLayout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel84)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(garant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPcomprasLayout.createSequentialGroup()
                            .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPcomprasLayout.createSequentialGroup()
                                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel81))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPcomprasLayout.createSequentialGroup()
                                    .addComponent(jLabel85)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel72)
                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcomprasLayout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(unidadM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel87)
                                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                                .addComponent(jLabel67)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtcodigoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPcomprasLayout.createSequentialGroup()
                                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                                .addComponent(jLabel83)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jSutilid, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPcomprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPcomprasLayout.createSequentialGroup()
                                                .addComponent(agregarAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(agregarGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        jPcontenedor.add(jPcompras, "card4");

        jPcliente.setBackground(new java.awt.Color(40, 41, 41));
        jPcliente.setAutoscrolls(true);
        jPcliente.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPcliente.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel98.setBackground(new java.awt.Color(40, 41, 41));
        jLabel98.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Nombre de la Empresa/Razon Social/Persona Fisica *");
        jLabel98.setOpaque(true);

        txtNombreC.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombreC.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombreC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCActionPerformed(evt);
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

        txtRFCCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtRFCCliente.setForeground(new java.awt.Color(51, 51, 51));
        try {
            txtRFCCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("????######AAA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtRFCCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtDirCliente.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtDirCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtDirCliente.setText(" ");
        txtDirCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel102.setBackground(new java.awt.Color(40, 41, 41));
        jLabel102.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Direccion");
        jLabel102.setOpaque(true);

        txtColCliente.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtColCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtColCliente.setText("");
        txtColCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

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

        txtPDcliente.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtPDcliente.setForeground(new java.awt.Color(51, 51, 51));
        txtPDcliente.setText("");
        txtPDcliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txtNextCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNextCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtNextCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNextCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        jLabel105.setBackground(new java.awt.Color(40, 41, 41));
        jLabel105.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setText("No. Ext");
        jLabel105.setOpaque(true);

        paisCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        paisCliente.setForeground(new java.awt.Color(51, 51, 51));
        paisCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mexico" }));

        jLabel106.setBackground(new java.awt.Color(40, 41, 41));
        jLabel106.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setText("Pais");
        jLabel106.setOpaque(true);

        estadoCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        estadoCliente.setForeground(new java.awt.Color(51, 51, 51));
        estadoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Ciudad de México", "Coahuila", "Colima", "Durango", "Estado de México", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" }));

        jLabel107.setBackground(new java.awt.Color(40, 41, 41));
        jLabel107.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("Estado");
        jLabel107.setOpaque(true);

        txtCPcliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtCPcliente.setForeground(new java.awt.Color(51, 51, 51));
        txtCPcliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCPcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        jLabel108.setBackground(new java.awt.Color(40, 41, 41));
        jLabel108.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setText("Codigo Postal");
        jLabel108.setOpaque(true);

        txtNintCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNintCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtNintCliente.setText(null);
        txtNintCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNintCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        jLabel109.setBackground(new java.awt.Color(40, 41, 41));
        jLabel109.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setText("No. Int");
        jLabel109.setOpaque(true);

        txtCorreoCliente.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtCorreoCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtCorreoCliente.setText("");
        txtCorreoCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

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

        giroCliente.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        giroCliente.setForeground(new java.awt.Color(51, 51, 51));
        giroCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Otros servicios", "Abarrotes", "Aceros", "Aduanal", "Agrícola", "Alimenticio", "Ambientales", "Arrendadora", "Artes gráficas", "Arículos de limpieza", "Artículos deportivos", "Aseguradoras", "Asilos", "Asociación religiosa", "Autolavado", "Automotriz", "Autoservicio", "Avícola", "Calzado", "Carga aérea", "Clubes Deportivos", "Comercio", "Comunicación", "Construcción", "Despachos contables", "Editorial", "Educación", "Electricidad", "Empacadoras", "Equipo d ecómputo", "Espectáculos", "Farmacéuticos", "Ferretero", "Financiero", "Fletes", "Gasera", "Gasolinera", "Gobierno Estatal", "Gobierno Federal", "Hojalateria", "Hoteles y Moteles", "Impresiones", "Industria mecánica", "Industrial", "Inmobiliaria", "Joyería", "Juguetería", "Librería", "Manufactura", "Maquiladora", "Materiales para la construcción", "Médicos", "Mercería", "Miscelánea", "Muebles", "Ópticas", "Panteón", "Papelería", "Perfiles de Aluminio", "Perfumería", "Pesquero", "Pieles", "Pinturas", "Plásticos", "Produccion audiovisual", "Publicidad", "Químicos", "Restaurante", "Ropa", "Seguridad Privada", "Servicio", "Siderúrgico", "Textiles", "Transporte", "Turismo" }));

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

        jLabel114.setBackground(new java.awt.Color(40, 41, 41));
        jLabel114.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setText("Calle");
        jLabel114.setOpaque(true);

        txtCalleCliente.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtCalleCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtCalleCliente.setText("");
        txtCalleCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel115.setBackground(new java.awt.Color(40, 41, 41));
        jLabel115.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setText("Codigo de Cliente* ");
        jLabel115.setOpaque(true);

        txtCodCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtCodCliente.setForeground(new java.awt.Color(51, 51, 51));
        txtCodCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        comboRFCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Persona física", "Persona moral" }));
        comboRFCC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                changeRFCC(evt);
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
                    .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPclienteLayout.createSequentialGroup()
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel113)
                                    .addComponent(jLabel104))
                                .addGap(10, 10, 10)
                                .addComponent(txtPDcliente))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                                        .addComponent(jLabel103)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtColCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel114)
                                            .addComponent(jLabel100)
                                            .addComponent(jLabel102))
                                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPclienteLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtDirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtCalleCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPclienteLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(comboRFCC, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtRFCCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel101)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(giroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(txtNombreC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                                        .addComponent(jLabel106)
                                        .addGap(18, 18, 18)
                                        .addComponent(paisCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(248, 248, 248)
                                        .addComponent(jLabel107)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(estadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createSequentialGroup()
                                        .addComponent(jLabel110)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(agregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPclienteLayout.createSequentialGroup()
                                            .addComponent(jLabel115)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel108)
                                .addComponent(txtCPcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNintCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtID3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPclienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtID3))
                    .addGroup(jPclienteLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel115))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel98)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel100)
                                .addComponent(jLabel101)
                                .addComponent(giroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboRFCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtRFCCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel102))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCalleCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel114)))
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel109))
                                .addGap(4, 4, 4)
                                .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNintCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNextCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel108)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtColCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel103)
                            .addComponent(txtCPcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(txtPDcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPclienteLayout.createSequentialGroup()
                                .addComponent(jLabel104)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel113)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paisCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106)
                            .addComponent(estadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel107))
                        .addGap(18, 18, 18)
                        .addGroup(jPclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110)
                            .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPcontenedor.add(jPcliente, "card4");

        jPproveedor.setBackground(new java.awt.Color(40, 41, 41));
        jPproveedor.setAutoscrolls(true);
        jPproveedor.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPproveedor.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel116.setBackground(new java.awt.Color(40, 41, 41));
        jLabel116.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
        jLabel116.setText("Nombre de la Empresa/Razon Social/Persona Fisica *");
        jLabel116.setOpaque(true);

        txtNombrePro.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombrePro.setForeground(new java.awt.Color(51, 51, 51));
        txtNombrePro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel117.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setText("Agregar un Proveedor");

        txtID4.setBackground(new java.awt.Color(40, 41, 41));
        txtID4.setForeground(new java.awt.Color(40, 41, 41));
        txtID4.setText("0");

        jLabel118.setBackground(new java.awt.Color(40, 41, 41));
        jLabel118.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
        jLabel118.setText("R.F.C. ");
        jLabel118.setOpaque(true);

        jLabel119.setBackground(new java.awt.Color(40, 41, 41));
        jLabel119.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setText("Giro");
        jLabel119.setOpaque(true);

        txtRFCPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtRFCPro.setForeground(new java.awt.Color(51, 51, 51));
        try {
            txtRFCPro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("????######AAA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtRFCPro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtDirPro.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtDirPro.setForeground(new java.awt.Color(51, 51, 51));
        txtDirPro.setText("");
        txtDirPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel120.setBackground(new java.awt.Color(40, 41, 41));
        jLabel120.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 255, 255));
        jLabel120.setText("Direccion");
        jLabel120.setOpaque(true);

        txtColPro.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtColPro.setForeground(new java.awt.Color(51, 51, 51));
        txtColPro.setText("");
        txtColPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel121.setBackground(new java.awt.Color(40, 41, 41));
        jLabel121.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 255, 255));
        jLabel121.setText("Colonia");
        jLabel121.setOpaque(true);

        jLabel122.setBackground(new java.awt.Color(40, 41, 41));
        jLabel122.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 255, 255));
        jLabel122.setText("Poblacion y/o ");
        jLabel122.setOpaque(true);

        txtPDPro.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtPDPro.setForeground(new java.awt.Color(51, 51, 51));
        txtPDPro.setText("");
        txtPDPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        txtNextPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNextPro.setForeground(new java.awt.Color(51, 51, 51));
        txtNextPro.setText(null);
        txtNextPro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNextPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        jLabel123.setBackground(new java.awt.Color(40, 41, 41));
        jLabel123.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(255, 255, 255));
        jLabel123.setText("No. Ext");
        jLabel123.setOpaque(true);

        paisPro.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        paisPro.setForeground(new java.awt.Color(51, 51, 51));
        paisPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mexico" }));

        jLabel124.setBackground(new java.awt.Color(40, 41, 41));
        jLabel124.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(255, 255, 255));
        jLabel124.setText("Pais");
        jLabel124.setOpaque(true);

        estadoPro.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        estadoPro.setForeground(new java.awt.Color(51, 51, 51));
        estadoPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Ciudad de México", "Coahuila", "Colima", "Durango", "Estado de México", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" }));

        jLabel125.setBackground(new java.awt.Color(40, 41, 41));
        jLabel125.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setText("Estado");
        jLabel125.setOpaque(true);

        txtCPPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtCPPro.setForeground(new java.awt.Color(51, 51, 51));
        txtCPPro.setText(null);
        txtCPPro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtCPPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        jLabel126.setBackground(new java.awt.Color(40, 41, 41));
        jLabel126.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(255, 255, 255));
        jLabel126.setText("Codigo Postal");
        jLabel126.setOpaque(true);

        txtNintPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNintPro.setForeground(new java.awt.Color(51, 51, 51));
        txtNintPro.setText(null);
        txtNintPro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtNintPro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validar(evt);
            }
        });

        jLabel127.setBackground(new java.awt.Color(40, 41, 41));
        jLabel127.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(255, 255, 255));
        jLabel127.setText("No. Int");
        jLabel127.setOpaque(true);

        txtCorreoPro.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtCorreoPro.setForeground(new java.awt.Color(51, 51, 51));
        txtCorreoPro.setText("");
        txtCorreoPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel128.setBackground(new java.awt.Color(40, 41, 41));
        jLabel128.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(255, 255, 255));
        jLabel128.setText("Correo ");
        jLabel128.setOpaque(true);

        jLabel129.setBackground(new java.awt.Color(40, 41, 41));
        jLabel129.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 255, 255));
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setText("Los campos marcados con ( * ) son olbigatorios");
        jLabel129.setOpaque(true);

        jLabel130.setBackground(new java.awt.Color(40, 41, 41));
        jLabel130.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(153, 153, 153));
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel130.setText("Proporcione los datos de la persona Fisica o Moral");
        jLabel130.setOpaque(true);

        jLabel131.setBackground(new java.awt.Color(40, 41, 41));
        jLabel131.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(255, 255, 255));
        jLabel131.setText("Delegacion");
        jLabel131.setOpaque(true);

        giroPro.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        giroPro.setForeground(new java.awt.Color(51, 51, 51));
        giroPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Otros servicios", "Abarrotes", "Aceros", "Aduanal", "Agrícola", "Alimenticio", "Ambientales", "Arrendadora", "Artes gráficas", "Arículos de limpieza", "Artículos deportivos", "Aseguradoras", "Asilos", "Asociación religiosa", "Autolavado", "Automotriz", "Autoservicio", "Avícola", "Calzado", "Carga aérea", "Clubes Deportivos", "Comercio", "Comunicación", "Construcción", "Despachos contables", "Editorial", "Educación", "Electricidad", "Empacadoras", "Equipo d ecómputo", "Espectáculos", "Farmacéuticos", "Ferretero", "Financiero", "Fletes", "Gasera", "Gasolinera", "Gobierno Estatal", "Gobierno Federal", "Hojalateria", "Hoteles y Moteles", "Impresiones", "Industria mecánica", "Industrial", "Inmobiliaria", "Joyería", "Juguetería", "Librería", "Manufactura", "Maquiladora", "Materiales para la construcción", "Médicos", "Mercería", "Miscelánea", "Muebles", "Ópticas", "Panteón", "Papelería", "Perfiles de Aluminio", "Perfumería", "Pesquero", "Pieles", "Pinturas", "Plásticos", "Produccion audiovisual", "Publicidad", "Químicos", "Restaurante", "Ropa", "Seguridad Privada", "Servicio", "Siderúrgico", "Textiles", "Transporte", "Turismo" }));

        agregar3.setBackground(new java.awt.Color(29, 184, 83));
        agregar3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        agregar3.setForeground(new java.awt.Color(255, 255, 255));
        agregar3.setText("Agregar");
        agregar3.setBorder(null);
        agregar3.setFocusPainted(false);
        agregar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar3ActionPerformed(evt);
            }
        });

        jLabel132.setBackground(new java.awt.Color(40, 41, 41));
        jLabel132.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 255, 255));
        jLabel132.setText("Calle");
        jLabel132.setOpaque(true);

        txtCallePro.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtCallePro.setForeground(new java.awt.Color(51, 51, 51));
        txtCallePro.setText("");
        txtCallePro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        jLabel133.setBackground(new java.awt.Color(40, 41, 41));
        jLabel133.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(255, 255, 255));
        jLabel133.setText("Codigo de Proveedor* ");
        jLabel133.setOpaque(true);

        txtCodPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtCodPro.setForeground(new java.awt.Color(51, 51, 51));
        txtCodPro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        comboRFC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Persona física", "Persona moral" }));
        comboRFC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rfcChange(evt);
            }
        });

        javax.swing.GroupLayout jPproveedorLayout = new javax.swing.GroupLayout(jPproveedor);
        jPproveedor.setLayout(jPproveedorLayout);
        jPproveedorLayout.setHorizontalGroup(
            jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPproveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPproveedorLayout.createSequentialGroup()
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel131)
                                    .addComponent(jLabel122))
                                .addGap(10, 10, 10)
                                .addComponent(txtPDPro))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                        .addComponent(jLabel121)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtColPro, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel132)
                                            .addComponent(jLabel118)
                                            .addComponent(jLabel120))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                                .addComponent(comboRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtRFCPro, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel119)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(giroPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtDirPro, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCallePro, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtNombrePro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                        .addComponent(jLabel124)
                                        .addGap(18, 18, 18)
                                        .addComponent(paisPro, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(248, 248, 248)
                                        .addComponent(jLabel125)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(estadoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                                        .addComponent(jLabel128)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(agregar3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCorreoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPproveedorLayout.createSequentialGroup()
                                            .addComponent(jLabel133)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCodPro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel126)
                                .addComponent(txtCPPro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNextPro, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNintPro, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtID4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPproveedorLayout.createSequentialGroup()
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPproveedorLayout.setVerticalGroup(
            jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPproveedorLayout.createSequentialGroup()
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel129)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel130)
                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPproveedorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtID4))
                    .addGroup(jPproveedorLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodPro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel133))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel116)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel118)
                                .addComponent(jLabel119)
                                .addComponent(giroPro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtRFCPro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDirPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel120))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCallePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel132)))
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel123, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel127))
                                .addGap(4, 4, 4)
                                .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNintPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNextPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel126)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtColPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel121)
                            .addComponent(txtCPPro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(txtPDPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPproveedorLayout.createSequentialGroup()
                                .addComponent(jLabel122)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel131)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paisPro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel124)
                            .addComponent(estadoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel125))
                        .addGap(18, 18, 18)
                        .addGroup(jPproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel128)
                            .addComponent(txtCorreoPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agregar3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPcontenedor.add(jPproveedor, "card4");

        jPmodificarCP.setBackground(new java.awt.Color(40, 41, 41));
        jPmodificarCP.setAutoscrolls(true);
        jPmodificarCP.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPmodificarCP.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel134.setBackground(new java.awt.Color(40, 41, 41));
        jLabel134.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(255, 255, 255));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel134.setText("Codigo/Nombre de la Empresa/Razon Social/Persona Fisica");
        jLabel134.setOpaque(true);

        txtNombrePro1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombrePro1.setForeground(new java.awt.Color(51, 51, 51));
        txtNombrePro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombrePro1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombrePro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombrePro1KeyReleased(evt);
            }
        });

        jLabel135.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(255, 255, 255));
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel135.setText("Modificar un Proveedor o Cliente");

        txtID5.setBackground(new java.awt.Color(40, 41, 41));
        txtID5.setForeground(new java.awt.Color(40, 41, 41));
        txtID5.setText("0");

        jLabel147.setBackground(new java.awt.Color(40, 41, 41));
        jLabel147.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(255, 255, 255));
        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel147.setText("BUSCAR");
        jLabel147.setOpaque(true);

        jLabel148.setBackground(new java.awt.Color(40, 41, 41));
        jLabel148.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(153, 153, 153));
        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel148.setText("por codigo o nombre ");
        jLabel148.setOpaque(true);

        botonActualizar.setBackground(new java.awt.Color(29, 184, 83));
        botonActualizar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonActualizar.setForeground(new java.awt.Color(255, 255, 255));
        botonActualizar.setText("Actualizar");
        botonActualizar.setBorder(null);
        botonActualizar.setFocusPainted(false);
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        tablaBusCP = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaBusCP.setBackground(new java.awt.Color(251, 251, 251));
        tablaBusCP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaBusCP.setForeground(new java.awt.Color(51, 51, 51));
        tablaBusCP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "RFC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaBusCP.setGridColor(new java.awt.Color(255, 255, 255));
        tablaBusCP.setSelectionBackground(new java.awt.Color(62, 226, 141));
        jScrollPane4.setViewportView(tablaBusCP);

        javax.swing.GroupLayout jPmodificarCPLayout = new javax.swing.GroupLayout(jPmodificarCP);
        jPmodificarCP.setLayout(jPmodificarCPLayout);
        jPmodificarCPLayout.setHorizontalGroup(
            jPmodificarCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPmodificarCPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPmodificarCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPmodificarCPLayout.createSequentialGroup()
                        .addGroup(jPmodificarCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPmodificarCPLayout.createSequentialGroup()
                                .addGap(233, 233, 233)
                                .addComponent(txtNombrePro1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPmodificarCPLayout.setVerticalGroup(
            jPmodificarCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPmodificarCPLayout.createSequentialGroup()
                .addComponent(jLabel135)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel147)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel148)
                .addGap(4, 4, 4)
                .addComponent(jLabel134)
                .addGap(10, 10, 10)
                .addGroup(jPmodificarCPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addGap(63, 63, 63)
                .addComponent(txtID5)
                .addContainerGap())
        );

        jPcontenedor.add(jPmodificarCP, "card4");

        jPAinv.setBackground(new java.awt.Color(40, 41, 41));
        jPAinv.setAutoscrolls(true);
        jPAinv.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPAinv.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel136.setBackground(new java.awt.Color(40, 41, 41));
        jLabel136.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(255, 255, 255));
        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel136.setText("Codigo/Nombre del Producto/Codigo Alternativo");
        jLabel136.setOpaque(true);

        txtCNA.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtCNA.setForeground(new java.awt.Color(51, 51, 51));
        txtCNA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCNA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtCNA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCNAKeyReleased(evt);
            }
        });

        jLabel137.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("Agregar Compra");

        txtID6.setBackground(new java.awt.Color(40, 41, 41));
        txtID6.setForeground(new java.awt.Color(40, 41, 41));
        txtID6.setText("0");

        jLabel149.setBackground(new java.awt.Color(40, 41, 41));
        jLabel149.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(255, 255, 255));
        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel149.setText("BUSCAR");
        jLabel149.setOpaque(true);

        jLabel150.setBackground(new java.awt.Color(40, 41, 41));
        jLabel150.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(153, 153, 153));
        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel150.setText("por codigo o nombre ");
        jLabel150.setOpaque(true);

        botonActualizar1.setBackground(new java.awt.Color(29, 184, 83));
        botonActualizar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonActualizar1.setForeground(new java.awt.Color(255, 255, 255));
        botonActualizar1.setText("Comprar");
        botonActualizar1.setBorder(null);
        botonActualizar1.setFocusPainted(false);
        botonActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizar1ActionPerformed(evt);
            }
        });

        tablaComprar.setBackground(new java.awt.Color(251, 251, 251));
        tablaComprar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaComprar.setForeground(new java.awt.Color(51, 51, 51));
        tablaComprar.setGridColor(new java.awt.Color(255, 255, 255));
        tablaComprar.setSelectionBackground(new java.awt.Color(62, 226, 141));
        jScrollPane5.setViewportView(tablaComprar);

        spinnerCantidad.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        spinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinnerCantidad.setOpaque(false);
        spinnerCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerCantidadStateChanged(evt);
            }
        });
        spinnerCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spinnerCantidadvalidar(evt);
            }
        });

        jLabel88.setBackground(new java.awt.Color(40, 41, 41));
        jLabel88.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("Cantidad");
        jLabel88.setOpaque(true);

        comboDistribuidor.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        comboDistribuidor.setForeground(new java.awt.Color(51, 51, 51));
        comboDistribuidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboDistribuidorMouseClicked(evt);
            }
        });

        jLabel68.setBackground(new java.awt.Color(40, 41, 41));
        jLabel68.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Distribuidor");
        jLabel68.setOpaque(true);

        javax.swing.GroupLayout jPAinvLayout = new javax.swing.GroupLayout(jPAinv);
        jPAinv.setLayout(jPAinvLayout);
        jPAinvLayout.setHorizontalGroup(
            jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAinvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPAinvLayout.createSequentialGroup()
                        .addGroup(jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPAinvLayout.createSequentialGroup()
                                .addGroup(jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPAinvLayout.createSequentialGroup()
                                        .addGroup(jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPAinvLayout.createSequentialGroup()
                                                .addGap(233, 233, 233)
                                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(spinnerCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(comboDistribuidor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPAinvLayout.createSequentialGroup()
                                        .addGap(233, 233, 233)
                                        .addComponent(txtCNA, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPAinvLayout.setVerticalGroup(
            jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPAinvLayout.createSequentialGroup()
                .addComponent(jLabel137)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel149)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel150)
                .addGap(4, 4, 4)
                .addComponent(jLabel136)
                .addGap(10, 10, 10)
                .addGroup(jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCNA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPAinvLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel68)
                        .addGap(38, 38, 38))
                    .addGroup(jPAinvLayout.createSequentialGroup()
                        .addGroup(jPAinvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPAinvLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel88))
                            .addGroup(jPAinvLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(spinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPAinvLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(comboDistribuidor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID6)
                .addContainerGap())
        );

        jPcontenedor.add(jPAinv, "card4");

        jPAinvC.setBackground(new java.awt.Color(40, 41, 41));
        jPAinvC.setAutoscrolls(true);
        jPAinvC.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPAinvC.setMinimumSize(new java.awt.Dimension(985, 613));

        jLabel140.setBackground(new java.awt.Color(40, 41, 41));
        jLabel140.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(255, 255, 255));
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText("Codigo/Nombre del Producto/Codigo Alternativo");
        jLabel140.setOpaque(true);

        txtNombrePro4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        txtNombrePro4.setForeground(new java.awt.Color(51, 51, 51));
        txtNombrePro4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombrePro4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        txtNombrePro4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombrePro4KeyReleased(evt);
            }
        });

        jLabel141.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(255, 255, 255));
        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel141.setText("Cancelar Compra");

        txtID8.setBackground(new java.awt.Color(40, 41, 41));
        txtID8.setForeground(new java.awt.Color(40, 41, 41));
        txtID8.setText("0");

        jLabel153.setBackground(new java.awt.Color(40, 41, 41));
        jLabel153.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel153.setForeground(new java.awt.Color(255, 255, 255));
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel153.setText("BUSCAR");
        jLabel153.setOpaque(true);

        jLabel154.setBackground(new java.awt.Color(40, 41, 41));
        jLabel154.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(153, 153, 153));
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel154.setText("por codigo o nombre ");
        jLabel154.setOpaque(true);

        botonActualizar3.setBackground(new java.awt.Color(29, 184, 83));
        botonActualizar3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonActualizar3.setForeground(new java.awt.Color(255, 255, 255));
        botonActualizar3.setText("Cancelar");
        botonActualizar3.setBorder(null);
        botonActualizar3.setFocusPainted(false);
        botonActualizar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizar3ActionPerformed(evt);
            }
        });

        tablaBusCP = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaBusCP3.setBackground(new java.awt.Color(251, 251, 251));
        tablaBusCP3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaBusCP3.setForeground(new java.awt.Color(51, 51, 51));
        tablaBusCP3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "RFC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaBusCP3.setGridColor(new java.awt.Color(255, 255, 255));
        tablaBusCP3.setSelectionBackground(new java.awt.Color(62, 226, 141));
        jScrollPane7.setViewportView(tablaBusCP3);

        javax.swing.GroupLayout jPAinvCLayout = new javax.swing.GroupLayout(jPAinvC);
        jPAinvC.setLayout(jPAinvCLayout);
        jPAinvCLayout.setHorizontalGroup(
            jPAinvCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAinvCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPAinvCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPAinvCLayout.createSequentialGroup()
                        .addGroup(jPAinvCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPAinvCLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombrePro4, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonActualizar3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPAinvCLayout.setVerticalGroup(
            jPAinvCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPAinvCLayout.createSequentialGroup()
                .addComponent(jLabel141)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel153)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel154)
                .addGap(4, 4, 4)
                .addComponent(jLabel140)
                .addGap(10, 10, 10)
                .addGroup(jPAinvCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombrePro4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonActualizar3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID8)
                .addContainerGap())
        );

        jPcontenedor.add(jPAinvC, "card4");

        masterPanel.add(jPcontenedor);

        getContentPane().add(masterPanel);

        jMenuBar2.setBackground(new java.awt.Color(30, 30, 30));
        jMenuBar2.setBorder(null);

        jMenu1.setBackground(new java.awt.Color(30, 30, 30));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Archivo");
        jMenu1.setOpaque(true);

        jMenuItem3.setText("Cambiar usuario");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator5);

        jMenuItem1.setText("Importar base de datos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Exportar base de datos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarBD(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator6);

        jMenuItem4.setText("Cerrar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar2.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(30, 30, 30));
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Catalogos");
        jMenu2.setOpaque(true);

        jMenu8.setText("Usuarios");

        añadirUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user (1).png"))); // NOI18N
        añadirUsuario.setText("Añadir");
        añadirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirUsuarioActionPerformed(evt);
            }
        });
        jMenu8.add(añadirUsuario);

        jMenu2.add(jMenu8);
        jMenu2.add(jSeparator3);

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
        jMenu2.add(jSeparator2);

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

        jMenu9.setText("Clientes");

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

        jMenu10.setText("jMenu10");
        jMenu2.add(jMenu10);

        jMenuBar2.add(jMenu2);

        jMenu11.setBackground(new java.awt.Color(30, 30, 30));
        jMenu11.setForeground(new java.awt.Color(255, 255, 255));
        jMenu11.setText("Compras");
        jMenu11.setOpaque(true);

        jMenuItem8.setText("Agregar compra");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem8);

        jMenuItem9.setText("Cancelar compra");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem9);

        jMenuBar2.add(jMenu11);

        jMenu3.setBackground(new java.awt.Color(30, 30, 30));
        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Reportes");
        jMenu3.setOpaque(true);

        jMenuItem7.setText("Ventas del dia");
        jMenu3.add(jMenuItem7);

        jMenuItem10.setText("Inventario");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem11.setText("Generar Reportes");
        jMenu3.add(jMenuItem11);

        jMenuItem12.setText("Generar Reportes");
        jMenu3.add(jMenuItem12);

        jMenuItem13.setText("Reporte Perzonalizado");
        jMenu3.add(jMenuItem13);

        jMenuBar2.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(30, 30, 30));
        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Ayuda");
        jMenu4.setOpaque(true);

        jMenuItem6.setText("Informacion");
        jMenu4.add(jMenuItem6);

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
        mostrarTablaModificar();
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
                if ("pza".equals(tablaDatosModificar.getValueAt(fila, 8).toString())) {
                    unidadMod.setSelectedIndex(0);
                } else {
                    unidadMod.setSelectedIndex(1);
                }
                if (tablaDatosModificar.getValueAt(fila, 9).toString() != null) {
                    txtModcodigoA.setText(tablaDatosModificar.getValueAt(fila, 9).toString());
                } else {
                    txtModcodigoA.setText("");
                }
                garantMod.setSelectedItem(tablaDatosModificar.getValueAt(fila, 10));
                almacenMod.setSelectedItem(tablaDatosModificar.getValueAt(fila, 11));

            } catch (NumberFormatException e) {
                System.out.println("Error");
                System.out.println(e.getMessage());

            }

        }
    }//GEN-LAST:event_tablaDatosModificarMouseClicked

    private void añadirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirUsuarioActionPerformed
        Interface inter = new Interface();
        AgregarUser ag = new AgregarUser(inter, true);
        ag.setVisible(true);

    }//GEN-LAST:event_añadirUsuarioActionPerformed

    private void prodAgreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodAgreActionPerformed
        // TODO add your handling code here:
        jPcontenedor.removeAll();
        jPcontenedor.add(jPcompras);
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
        mostrarTablaProducto();

    }//GEN-LAST:event_prodAgreActionPerformed

    private void pordModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pordModActionPerformed
        // TODO add your handling code here:
        jPcontenedor.removeAll();
        jPcontenedor.add(jPmodificar);
        mostrarTablaModificar();
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
        mostrarComboProductoMod();
    }//GEN-LAST:event_pordModActionPerformed

    private void clieModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clieModActionPerformed
        modificarclipro();
    }//GEN-LAST:event_clieModActionPerformed
    public void modificarclipro() {
        modelCP.setNumRows(0);
        jPcontenedor.removeAll();
        jPcontenedor.add(jPmodificarCP);
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
        String sql = "SELECT codigo,nombreC,RFC FROM clipro";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelCP.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            tr = new TableRowSorter<>(modelCP);
            tablaBusCP.setRowSorter(tr);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void provModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provModActionPerformed
        modificarclipro();
    }//GEN-LAST:event_provModActionPerformed

    private void agregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar2ActionPerformed
        try {
            // TODO add your handling code here:
            agregarcliente(1);
        } catch (SQLException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void agregar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar3ActionPerformed
        try {
            // TODO add your handling code here:
            agregarproveedor(2);
        } catch (SQLException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_agregar3ActionPerformed
    void agregarproveedor(int tipo) throws SQLException {
        if (txtCodPro.getText().equals("") || txtNombrePro.getText().equals("")) {
            nv = new ImageIcon("src/img/cart (13).png");
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Mensaje", JOptionPane.OK_OPTION, nv);

        } else {
            int cp = 0;
            if ("".equals(txtCPPro.getText())) {
            } else {
                cp = Integer.parseInt(txtCPPro.getText());
            }
            int Nint = 0;
            if ("".equals(txtNintPro.getText())) {

            } else {
                Nint = Integer.parseInt(txtNintPro.getText());
            }
            int Next = 0;
            if ("".equals(txtNextPro.getText())) {

            } else {
                Next = Integer.parseInt(txtNextPro.getText());
            }
            PreparedStatement pps = cn.prepareStatement("INSERT INTO clipro(codigo,nombreC, direccion, calle, noInt, noExt, colonia, cp, ciudad, estado, pais, rfc,correo,tipo,giro) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, txtCodPro.getText());
            pps.setString(2, txtNombrePro.getText());
            pps.setString(3, txtDirPro.getText());
            pps.setString(4, txtCallePro.getText());
            pps.setInt(5, Nint);
            pps.setInt(6, Next);
            pps.setString(7, txtColPro.getText());
            pps.setInt(8, cp);
            pps.setString(9, txtPDPro.getText());
            pps.setString(10, (String) estadoPro.getSelectedItem());
            pps.setString(11, (String) paisPro.getSelectedItem());
            pps.setString(12, txtRFCPro.getText());
            pps.setString(13, txtCorreoPro.getText());
            pps.setInt(14, tipo);
            pps.setString(15, (String) giroPro.getSelectedItem());
            ua = new ImageIcon("src/img/succes.png");
            JOptionPane.showMessageDialog(null, "Proveedor agregado exitosamente", "Mensaje", JOptionPane.OK_OPTION, ua);
            pps.executeUpdate();
        }

    }

    void agregarcliente(int tipo) throws SQLException {
        if (txtCodCliente.getText().equals("") || txtNombreC.getText().equals("")) {
            nv = new ImageIcon("src/img/cart (13).png");
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Mensaje", JOptionPane.OK_OPTION, nv);
        } else {
            int cp = 0;
            if ("".equals(txtCPcliente.getText())) {
            } else {
                cp = Integer.parseInt(txtCPcliente.getText());
            }
            int Nint = 0;
            if ("".equals(txtNintCliente.getText())) {
            } else {
                Nint = Integer.parseInt(txtNintCliente.getText());
            }
            int Next = 0;
            if ("".equals(txtNextCliente.getText())) {
            } else {
                Next = Integer.parseInt(txtNextCliente.getText());
            }
            PreparedStatement pps = cn.prepareStatement("INSERT INTO clipro(codigo,nombreC, direccion, calle, noInt, noExt, colonia, cp, ciudad, estado, pais, rfc,correo,tipo,giro) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, txtCodCliente.getText());
            pps.setString(2, txtNombreC.getText());
            pps.setString(3, txtDirCliente.getText());
            pps.setString(4, txtCalleCliente.getText());
            pps.setInt(5, Nint);
            pps.setInt(6, Next);
            pps.setString(7, txtColCliente.getText());
            pps.setInt(8, cp);
            pps.setString(9, txtPDcliente.getText());
            pps.setString(10, (String) estadoCliente.getSelectedItem());
            pps.setString(11, (String) paisCliente.getSelectedItem());
            pps.setString(12, txtRFCCliente.getText());
            pps.setString(13, txtCorreoCliente.getText());
            pps.setInt(14, tipo);
            pps.setString(15, (String) giroCliente.getSelectedItem());
            ua = new ImageIcon("src/img/success.png");
            JOptionPane.showMessageDialog(null, "Proveedor agregado exitosamente", "Mensaje", JOptionPane.OK_OPTION, ua);
            pps.executeUpdate();
        }

    }
    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        // TODO add your handling code here:        
        try {
            String codigo = (String) tablaBusCP.getValueAt(tablaBusCP.getSelectedRow(), 0);
            editarClipro editarCP = new editarClipro(this, false, codigo);
            editarCP.setTitle("Editar Cliente/Proveedor");
            editarCP.setLocationRelativeTo(null);
            editarCP.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Cliente/Proveedor no seleccionado", "Mensaje", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void txtNombrePro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePro1KeyReleased
        // TODO add your handling code here:
        String filtro = "(?i)" + txtNombrePro1.getText();
        if (!filtro.equals("")) {
            tr.setRowFilter(RowFilter.regexFilter(filtro));
        } else {
            tr.setRowFilter(null);
        }
    }//GEN-LAST:event_txtNombrePro1KeyReleased

    private void validar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_validar
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_validar

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        InicioSesion login = new InicioSesion();
        setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void txtModcodigoAvalidar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModcodigoAvalidar
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModcodigoAvalidar

    private void unidadModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unidadModMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_unidadModMouseClicked

    private void unidadModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unidadModActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unidadModActionPerformed

    private void txtcodigoAvalidar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoAvalidar
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoAvalidar

    private void agregarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarAlmacenActionPerformed
        Interface inter = new Interface();
        Almacen alm = new Almacen(inter, true);
        alm.setVisible(true);
    }//GEN-LAST:event_agregarAlmacenActionPerformed

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

    private void exportarBD(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarBD
        // TODO add your handling code here:
        exportarBD exp = new exportarBD(this, false);
        exp.setVisible(true);
    }//GEN-LAST:event_exportarBD

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        importarBD imp = new importarBD(this, false);
        imp.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void almacen(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_almacen
        // TODO add your handling code here:
        comboAlmacen();
    }//GEN-LAST:event_almacen

    private void rfcChange(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rfcChange
        // TODO add your handling code here:
        try {
            if (comboRFC.getSelectedIndex() == 0) {
                txtRFCPro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("????######AAA")));

            } else {
                txtRFCPro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("????######AA-A")));
            }
        } catch (ParseException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rfcChange

    private void changeRFCC(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_changeRFCC
        // TODO add your handling code here:

        try {
            if (comboRFCC.getSelectedIndex() == 0) {
                txtRFCCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("????######AAA")));

            } else {
                txtRFCCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("????######AA-A")));
            }
        } catch (ParseException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_changeRFCC

    private void txtNombreCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        jPcontenedor.removeAll();
        jPcontenedor.add(jPAinv);
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
        mostrarCompras();

    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void txtCNAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCNAKeyReleased
        // TODO add your handling code here:
        String filtro = "(?i)" + txtCNA.getText();
        if (!filtro.equals("")) {
            tr.setRowFilter(RowFilter.regexFilter(filtro));
        } else {
            tr.setRowFilter(null);
        }
    }//GEN-LAST:event_txtCNAKeyReleased

    private void botonActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizar1ActionPerformed
        // TODO add your handling code here:
        String clipro = "SELECT nombreC,idclipro FROM clipro";
        String producto = "SELECT codigo,idproducto FROM producto";
        String insert = "";
        Statement cp;
        String nombreC = (String) comboDistribuidor.getSelectedItem();
        int idclipro = 0;
        int idproducto = 0;
        try {
            String codigo = (String) tablaComprar.getValueAt(tablaComprar.getSelectedRow(), 0);
            try {
                cp = cn.createStatement();
                ResultSet rc = cp.executeQuery(clipro);
                while (rc.next()) {
                    if (nombreC.equals(rc.getString("nombreC"))) {
                        idclipro = rc.getInt("idclipro");
                    }
                }
                cp = cn.createStatement();
                rc = cp.executeQuery(producto);
                while (rc.next()) {
                    if (codigo.equals(rc.getString("codigo"))) {
                        idproducto = rc.getInt("idproducto");
                    }
                }
            } catch (SQLException ex) {
                System.out.println((char) 27 + "[31m" + ex.getMessage());
            }
            insert = "INSERT INTO compra (idcompra, cantidad, fecha, producto_idproducto, clipro_idclipro) "
                    + "VALUES (NULL, '" + spinnerCantidad.getValue() + "', CURRENT_TIMESTAMP,'" + idproducto + "', '" + idclipro + "');";
        } catch (Exception e) {
            System.out.println((char) 27 + "[31m" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Producto no seleccionado", "Mensaje", JOptionPane.OK_OPTION);
        }
        try {
            PreparedStatement pps;
            pps = cn.prepareStatement(insert);
            pps.executeUpdate();
            ImageIcon icon = new ImageIcon("src/img/cart (5).png");
            JOptionPane.showMessageDialog(null, "Se realizo la compra con exito", "Mensaje", JOptionPane.OK_OPTION, icon);
        } catch (SQLException ex) {
            System.out.println((char) 27 + "[31m" + ex.getMessage());
        }
        tablaComprar.removeAll();
        tr.setRowFilter(null);
        txtCNA.setText("");
        mostrarCompras();
    }//GEN-LAST:event_botonActualizar1ActionPerformed
    private void mostrarCompras() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Codigo Alterno");
        modelo.addColumn("Existencia");
        String sql = "SELECT codigo,nombre,codigoA,stock FROM producto";
        Statement st;
        try {
            tablaComprar.setModel(modelo);
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
            tr = new TableRowSorter<>(modelo);
            tablaComprar.setRowSorter(tr);
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void mostrarUsuarios() {

        comboUsuario.removeAllItems();
        String sql = "SELECT nombre FROM usuario WHERE nivel = '2'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                comboUsuario.addItem(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println((char) 27 + "[31m" + ex.getMessage());
        }
    }

    private void mostrarDistribuidor() {
        comboDistribuidor.removeAllItems();
        String sql = "SELECT nombreC FROM clipro WHERE tipo = '2'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                comboDistribuidor.addItem(rs.getString("nombreC"));
            }
        } catch (SQLException ex) {
            System.out.println((char) 27 + "[31m" + ex.getMessage());
        }
    }
    private void spinnerCantidadvalidar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spinnerCantidadvalidar
        // TODO add your handling code here:
    }//GEN-LAST:event_spinnerCantidadvalidar

    private void spinnerCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerCantidadStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_spinnerCantidadStateChanged

    private void comboDistribuidorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboDistribuidorMouseClicked
        // TODO add your handling code here:
        mostrarDistribuidor();
    }//GEN-LAST:event_comboDistribuidorMouseClicked

    private void botonActualizar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonActualizar3ActionPerformed

    private void txtNombrePro4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePro4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePro4KeyReleased

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        jPcontenedor.removeAll();
        jPcontenedor.add(jPAinvC);
        jPcontenedor.updateUI();
        jPcontenedor.repaint();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void comboUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboUsuarioMouseClicked
        // TODO add your handling code here:
        mostrarUsuarios();
    }//GEN-LAST:event_comboUsuarioMouseClicked
    private void comboCorte() {
        
        if(idlogin.isEmpty()){
        }else{
        idlogin.clear();
        }
        
        DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
        String fecha = df.format(corteFecha.getDate());
        String sql ="SELECT TIME(fechaEnt),TIME(fechaSal),DATE_FORMAT(fechaSal, \"%d-%m-%Y\" ),DATE_FORMAT(fechaSal, \"%H\" )-DATE_FORMAT(fechaEnt, \"%H\" )AS horas,idlogin FROM usuario INNER JOIN login ON idusuario = usuario_idusuario WHERE DATE_FORMAT(fechaSal, \"%d-%m-%Y\" )= '" + fecha + "'";
        comboCortes.removeAllItems();
        Statement st;
        if(tUsuarios.isSelected()){
            sql = sql+"AND nivel = '2'";
            }else{
            sql =  sql+"AND usuario_idusuario = '"+ idUsuario() + "'";
            
        }
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                comboCortes.addItem("(" + rs.getString("TIME(fechaEnt)") + ")-(" + rs.getString("TIME(fechaSal)") + ")" + "    Horas: " + (rs.getInt("horas")));
                idlogin.addElement(rs.getInt("idlogin"));
            }
        } catch (SQLException ex) {
            System.out.println((char) 27 + "[31m" + ex);
        }
    }

    public int idUsuario() {
        Statement st;
        int id = 0;

        String usuario = "SELECT nombre,idusuario FROM usuario WHERE nombre= '"
                + comboUsuario.getSelectedItem() + "' AND nivel = '2'";
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(usuario);
            while (rs.next()) {
                id = rs.getInt("idusuario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Garant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    private void group1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_group1
        // TODO add your handling code here:        
        checkGroup();
    }//GEN-LAST:event_group1

    public void checkGroup() {
        String sql = "SELECT DATE_FORMAT(fechaSal,\"%Y-%m-%d\") AS fecha , (cantidad*precio)-(cantidad*costo)AS ganancia,formaP,producto.nombre,cantidad,(cantidad*precio) AS importe,usuario.nombre,motivo FROM login INNER JOIN venta ON idlogin = login_idlogin INNER JOIN usuario ON usuario_idusuario = idusuario INNER JOIN producto ON producto_idproducto = idproducto";
        if (rbEspecifico.isSelected()) {
            comboCortes.setEnabled(true);
            corteFecha.setEnabled(true);
            comboCorte();
            corteEspecifico();
        } else if (rbCdia.isSelected()) {
            comboCortes.setEnabled(false);
            corteFecha.setEnabled(false);
            String dia = sql + " WHERE DATE_FORMAT(fechaSal,\"%Y-%m-%d\") = '" + dateDia+"'" ;
            if(tUsuarios.isSelected()){
            
            }else{
            dia = dia +"AND idusuario ='" + idUsuario() + "'";
            }
            consultar(dia);
        } else if (rbCm.isSelected()) {
            comboCortes.setEnabled(false);
            corteFecha.setEnabled(false);
            String mes = sql+" WHERE DATE_FORMAT(fechaSal,\"%m\") = '" + dateMes+"'";
            if(tUsuarios.isSelected()){            
            }else{
            mes = mes +" AND idusuario ='" + idUsuario() + "' ";
            }
            consultar(mes);
        }
    }

    public void consultar(String sql) {
        Statement st;
        DefaultTableModel modelo = (DefaultTableModel) tablaDevoluciones.getModel();
        double efectivo = 0;
        double tarjeta = 0;
        double cancelada = 0;
        modelo.setRowCount(0);
        tablaDevoluciones.setModel(modelo);
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql + " AND formaP = 'Efectivo' AND cancelada = '0'");
            while (rs.next()) {
                efectivo = rs.getDouble(2) + efectivo;
            }
            labelEfectivo.setText(Double.toString(efectivo));
        } catch (SQLException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql + " AND formaP = 'Tarjeta' AND cancelada = '0'");
            while (rs.next()) {
                tarjeta = rs.getDouble(2) + tarjeta;
            }
            labelTarjeta.setText(Double.toString(tarjeta));
        } catch (SQLException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql + "AND cancelada = '1'");
            while (rs.next()) {
                cancelada = rs.getDouble(2) + cancelada;
                modelo.addRow(new Object[]{rs.getString("fecha"), rs.getString("producto.nombre"), rs.getString("cantidad"), rs.getString("Importe"), rs.getString("usuario.nombre"), rs.getString("motivo")});
                
            }
            labelDevoluciones.setText(Double.toString(cancelada));
        } catch (SQLException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        double total = (efectivo + tarjeta);
        jLabel47.setText(df.format(total));
        jLabel54.setText(df.format(total));
    }
    private void usuarioChange(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usuarioChange
        // TODO add your handling code here:
       checkGroup();
    }//GEN-LAST:event_usuarioChange

    private void corteFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_corteFechaPropertyChange
        // TODO add your handling code here:
        comboCorte();
        corteEspecifico();
    }//GEN-LAST:event_corteFechaPropertyChange

    private void comboCortesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCortesActionPerformed
        // TODO add your handling code here:
        corteEspecifico();
    }//GEN-LAST:event_comboCortesActionPerformed

    private void tUsuariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tUsuariosItemStateChanged
        // TODO add your handling code here:
        
        if(tUsuarios.isSelected()){
            comboUsuario.setEnabled(false);
        }else{
        comboUsuario.setEnabled(true);
        }
        checkGroup();
    }//GEN-LAST:event_tUsuariosItemStateChanged
public void corteEspecifico(){
   int id = comboCortes.getSelectedIndex();
        if (idlogin.size()<=0){        
        }else{
            String sql = "SELECT DATE_FORMAT(fechaSal,\"%Y-%m-%d\") AS fecha , (cantidad*precio)-(cantidad*costo)AS ganancia,formaP,producto.nombre,cantidad,(cantidad*precio) AS importe,usuario.nombre,motivo FROM login INNER JOIN venta ON idlogin = login_idlogin INNER JOIN usuario ON usuario_idusuario = idusuario INNER JOIN producto ON producto_idproducto = idproducto WHERE login_idlogin='"+idlogin.elementAt(id)+"'";
            consultar(sql);
        }
}
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
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton agregar;
    private javax.swing.JButton agregar2;
    private javax.swing.JButton agregar3;
    private javax.swing.JButton agregarAlmacen;
    private javax.swing.JButton agregarGarantia;
    private javax.swing.JComboBox<String> almacen;
    private javax.swing.JComboBox<String> almacenMod;
    private javax.swing.JMenuItem añadirUsuario;
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonActualizar1;
    private javax.swing.JButton botonActualizar3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem clieAgre;
    private javax.swing.JMenuItem clieMod;
    private javax.swing.JComboBox<String> comboCortes;
    private javax.swing.JComboBox<String> comboDistribuidor;
    private javax.swing.JComboBox<String> comboRFC;
    private javax.swing.JComboBox<String> comboRFCC;
    private javax.swing.JComboBox<String> comboUsuario;
    private com.toedter.calendar.JDateChooser corteFecha;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox<String> estadoCliente;
    private javax.swing.JComboBox<String> estadoPro;
    private javax.swing.JComboBox<String> garant;
    private javax.swing.JComboBox<String> garantMod;
    private javax.swing.JComboBox<String> giroCliente;
    private javax.swing.JComboBox<String> giroPro;
    private javax.swing.JButton jBcorteCaja;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
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
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPAinv;
    private javax.swing.JPanel jPAinvC;
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
    private javax.swing.JPanel jPmodificarCP;
    private javax.swing.JPanel jPproveedor;
    private javax.swing.JPanel jPrincipal;
    private javax.swing.JSpinner jSModUtilid;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JSpinner jSutilid;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jUsuarioA;
    private javax.swing.JLabel labelDevoluciones;
    private javax.swing.JLabel labelEfectivo;
    private javax.swing.JLabel labelSalidas;
    private javax.swing.JLabel labelTarjeta;
    private javax.swing.JPanel masterPanel;
    private javax.swing.JComboBox<String> paisCliente;
    private javax.swing.JComboBox<String> paisPro;
    private javax.swing.JMenuItem pordMod;
    private javax.swing.JMenuItem prodAgre;
    private javax.swing.JMenuItem provAgre;
    private javax.swing.JMenuItem provMod;
    private javax.swing.JRadioButton rbCdia;
    private javax.swing.JRadioButton rbCm;
    private javax.swing.JRadioButton rbEspecifico;
    private javax.swing.JMenuItem serAgre;
    private javax.swing.JMenuItem serMod;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JCheckBox tUsuarios;
    public static javax.swing.JTable tablaBusCP;
    public static javax.swing.JTable tablaBusCP3;
    public static javax.swing.JTable tablaComprar;
    private javax.swing.JTable tablaDatosModificar;
    private javax.swing.JTable tablaDatosProducto;
    private javax.swing.JTable tablaDevoluciones;
    private javax.swing.JTextField txtCNA;
    private javax.swing.JFormattedTextField txtCPPro;
    private javax.swing.JFormattedTextField txtCPcliente;
    private javax.swing.JTextField txtCalleCliente;
    private javax.swing.JTextField txtCallePro;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextArea txtCaract;
    private javax.swing.JFormattedTextField txtCodCliente;
    private javax.swing.JFormattedTextField txtCodPro;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtColCliente;
    private javax.swing.JTextField txtColPro;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtCorreoPro;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtDirCliente;
    private javax.swing.JTextField txtDirPro;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtID3;
    private javax.swing.JLabel txtID4;
    private javax.swing.JLabel txtID5;
    private javax.swing.JLabel txtID6;
    private javax.swing.JLabel txtID8;
    private javax.swing.JTextArea txtModArea;
    private javax.swing.JTextField txtModCant;
    private javax.swing.JTextField txtModCodigo;
    private javax.swing.JTextField txtModCosto;
    private javax.swing.JTextField txtModNombre;
    private javax.swing.JTextField txtModPrecio;
    private javax.swing.JTextField txtModcodigoA;
    private javax.swing.JFormattedTextField txtNextCliente;
    private javax.swing.JFormattedTextField txtNextPro;
    private javax.swing.JFormattedTextField txtNintCliente;
    private javax.swing.JFormattedTextField txtNintPro;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtNombrePro;
    private javax.swing.JTextField txtNombrePro1;
    private javax.swing.JTextField txtNombrePro4;
    private javax.swing.JTextField txtPDPro;
    private javax.swing.JTextField txtPDcliente;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JFormattedTextField txtRFCCliente;
    private javax.swing.JFormattedTextField txtRFCPro;
    private javax.swing.JTextField txtcodigoA;
    private javax.swing.JComboBox<String> unidadM;
    private javax.swing.JComboBox<String> unidadMod;
    // End of variables declaration//GEN-END:variables
}
