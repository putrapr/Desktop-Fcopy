package fcopy;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import utility.koneksi_database;

/**
 * @author Putra Prasetya
 */
public class penjualan extends javax.swing.JFrame {
    private Connection conn = new koneksi_database().connect();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;
    private int id_admin;
    private JTextField kode_penjualan = new JTextField();
    private JTextField singkatan_admin = new JTextField();
    private String[] arr_id = new String[50];
    private String[] arr_jasa = new String[50];
    private String[] arr_nama = new String[50];
    private String[] arr_harga = new String[50];
    private String[] arr_qty = new String[50];
    private String[] arr_jumlah = new String[50];
    
    public penjualan(){
        initComponents();
        myInitComponents();
        setMinimumSize(new Dimension(850,540));
        tabelBarang();
        getDateString();
        getJasa();
        getNoFaktur();
        getNamaBarang();
    }
    
    public penjualan(Rectangle bound, int id) {
        initComponents();
        myInitComponents();
        setMinimumSize(new Dimension(850,540));
        tabelBarang();
        getDateString();
        getJasa();
        this.id_admin = id;
        getNoFaktur();
        getNamaBarang();
               
        //lb_no_faktur.setText(""+id);
        this.setBounds(bound);
        
        // terima ukuran window & tetapkan di window yang aktif
        this.setBounds(bound);
               
        //saat restore dari kondisi maximize kembali ke ukuran minimal
        Dimension size = this.getSize();
        Double width = size.getWidth();
        Double height = size.getHeight();
        if (width > 1381 && height > 743) {
            this.setSize(850,540);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }        
    }
    
    protected void tabelBarang(){
        Object[] Baris = {"ID Barang", "Nama", "Stok", "Harga"};
        tabmode = new DefaultTableModel(null, Baris);
        tbl_barang.setModel(tabmode);
        String sql = "SELECT "
                        + "id_brg, "
                        + "nama_brg, "
                        + "qty_brg, "                        
                        + "harga_jual_brg "                        
                    + "FROM barang" ;
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String a = hasil.getString("id_brg"); 
                String c = hasil.getString("nama_brg");
                String d = hasil.getString("qty_brg");                
                String e = hasil.getString("harga_jual_brg");
                
                String[] data={a,c,d,e};
                tabmode.addRow(data); 
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan: " + e);
        }
    }   
   
    protected void getDateString(){
        Date date = new Date();
        SimpleDateFormat formatting = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = formatting.format(date);
        lb_tgl.setText(strDate);
    }
    
    protected void getJasa(){
        String jasa = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT nama_jasa FROM jasa");
            while(hasil.next()){
                jasa = hasil.getString("nama_jasa");
                cb_jasa.addItem(jasa);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil data jasa");
        }
    }
    
    protected void getNamaBarang(){
        String nama = "";
        try{
            String sql = "SELECT nama_brg FROM barang WHERE id_brg LIKE '%K%'";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                nama = hasil.getString("nama_brg");
                nama = nama.substring(7,nama.length());
                cb_barang.addItem(nama);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil nama barang");
        }
    }
    
    private void menu_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_pegawaiActionPerformed
        Rectangle penjualanBound = this.getBounds();
        new pegawai(penjualanBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_pegawaiActionPerformed
    
	private void menu_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_supplierActionPerformed
        Rectangle penjualanBound = this.getBounds();
        new supplier(penjualanBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_supplierActionPerformed
	
	private void menu_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_pembelianActionPerformed
        Rectangle penjualanBound = this.getBounds();
        new pembelian(penjualanBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_pembelianActionPerformed
	
	private void menu_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_barangActionPerformed
        Rectangle penjualanBound = this.getBounds();
        new barang(penjualanBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_barangActionPerformed
	
	private void menu_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_penjualanActionPerformed

    }//GEN-LAST:event_menu_penjualanActionPerformed
	
	private void menu_penyesuaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_penyesuaianActionPerformed
        Rectangle penjualanBound = this.getBounds();
        new penyesuaian(penjualanBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_penyesuaianActionPerformed
		
	private void menu_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_laporanActionPerformed
        Rectangle penjualanBound = this.getBounds();
        new laporan(penjualanBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_laporanActionPerformed
	
	private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        Object[] Baris = {"ID Barang", "Nama", "Qty", "Harga"};
        tabmode = new DefaultTableModel(null, Baris);
        tbl_barang.setModel(tabmode);
        String cari = tf_pencarian.getText();
        String sql = "SELECT "
                        + "id_brg, "
                        + "nama_brg, "
                        + "qty_brg, "                        
                        + "harga_jual_brg "                        
                    + "FROM barang "
                    + "WHERE id_brg LIKE '%"+cari+"%' "
                        + "OR nama_brg LIKE '%"+cari+"%' "
                        + "OR qty_brg LIKE '%"+cari+"%' "
                        + "OR harga_jual_brg LIKE '%"+cari+"%' ";        
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String a = hasil.getString("id_brg");
                String b = hasil.getString("nama_brg");
                String c = hasil.getString("qty_brg");
                String d = hasil.getString("harga_jual_brg");
               
                String[] data={a,b,c,d};
                tabmode.addRow(data);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan: " + e);
        }
    }//GEN-LAST:event_btn_cariActionPerformed
	
	private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        tabelBarang();
    }//GEN-LAST:event_btn_resetActionPerformed

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg21 = new utility.bg2();
        header_menu = new javax.swing.JPanel();
        menu_pegawai = new javax.swing.JButton();
        menu_supplier = new javax.swing.JButton();
        menu_pembelian = new javax.swing.JButton();
        menu_barang = new javax.swing.JButton();
        menu_penjualan = new javax.swing.JButton();
        menu_penyesuaian = new javax.swing.JButton();
        menu_laporan = new javax.swing.JButton();
        menu_stok = new javax.swing.JButton();
        tf_pencarian = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_barang = new javax.swing.JTable();
        btn_selesai = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        lb_no_faktur = new javax.swing.JLabel();
        btn_ubah = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lb_total = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cb_jasa = new javax.swing.JComboBox<>();
        btn_remove_jasa = new javax.swing.JButton();
        btn_add_jasa = new javax.swing.JButton();
        tf_qty_jasa = new javax.swing.JTextField();
        tf_total = new javax.swing.JTextField();
        tf_ok_jasa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tf_qty_brg = new javax.swing.JTextField();
        lb_harga = new javax.swing.JLabel();
        lb_stok = new javax.swing.JLabel();
        lb_nama = new javax.swing.JLabel();
        lb_id = new javax.swing.JLabel();
        btn_ok_barang = new javax.swing.JButton();
        cb_barang = new javax.swing.JComboBox<>();
        lb_tgl = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btn_ubah_faktur = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 450));

        bg21.setMinimumSize(new java.awt.Dimension(800, 450));

        header_menu.setBackground(new java.awt.Color(255, 255, 204, 150));

        menu_pegawai.setBackground(new java.awt.Color(255, 153, 51));
        menu_pegawai.setText("Pegawai");
        menu_pegawai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_pegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_pegawaiActionPerformed(evt);
            }
        });

        menu_supplier.setBackground(new java.awt.Color(255, 153, 51));
        menu_supplier.setText("Supplier");
        menu_supplier.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_supplierActionPerformed(evt);
            }
        });

        menu_pembelian.setBackground(new java.awt.Color(255, 153, 51));
        menu_pembelian.setText("Pembelian");
        menu_pembelian.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_pembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_pembelianActionPerformed(evt);
            }
        });

        menu_barang.setBackground(new java.awt.Color(255, 153, 51));
        menu_barang.setText("Barang");
        menu_barang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_barangActionPerformed(evt);
            }
        });

        menu_penjualan.setBackground(new java.awt.Color(255, 153, 51));
        menu_penjualan.setForeground(new java.awt.Color(255, 255, 255));
        menu_penjualan.setText("Penjualan");
        menu_penjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_penjualanActionPerformed(evt);
            }
        });

        menu_penyesuaian.setBackground(new java.awt.Color(255, 153, 51));
        menu_penyesuaian.setText("Penyesuaian");
        menu_penyesuaian.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_penyesuaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_penyesuaianActionPerformed(evt);
            }
        });

        menu_laporan.setBackground(new java.awt.Color(255, 153, 51));
        menu_laporan.setText("Laporan");
        menu_laporan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_laporanActionPerformed(evt);
            }
        });

        menu_stok.setBackground(new java.awt.Color(255, 153, 51));
        menu_stok.setText("Stok");
        menu_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_stokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout header_menuLayout = new javax.swing.GroupLayout(header_menu);
        header_menu.setLayout(header_menuLayout);
        header_menuLayout.setHorizontalGroup(
            header_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header_menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu_pegawai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_supplier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_barang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_pembelian)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_penjualan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_penyesuaian)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_laporan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header_menuLayout.setVerticalGroup(
            header_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(menu_pegawai)
                .addComponent(menu_supplier)
                .addComponent(menu_pembelian)
                .addComponent(menu_barang)
                .addComponent(menu_penjualan)
                .addComponent(menu_penyesuaian)
                .addComponent(menu_laporan)
                .addComponent(menu_stok))
        );

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        jLabel16.setText("No. Faktur :");

        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Jasa", "Nama Barang", "Harga", "Qty", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_transaksi.getTableHeader().setReorderingAllowed(false);
        tbl_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transaksiMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbl_transaksi);
        if (tbl_transaksi.getColumnModel().getColumnCount() > 0) {
            tbl_transaksi.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_transaksi.getColumnModel().getColumn(1).setPreferredWidth(10);
            tbl_transaksi.getColumnModel().getColumn(3).setPreferredWidth(10);
            tbl_transaksi.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        tbl_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama", "Stok", "Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_barang.getTableHeader().setReorderingAllowed(false);
        tbl_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_barangMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_barang);

        btn_selesai.setBackground(new java.awt.Color(255, 0, 0));
        btn_selesai.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_selesai.setForeground(new java.awt.Color(255, 255, 255));
        btn_selesai.setText("SELESAI");
        btn_selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesaiActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        lb_no_faktur.setText("___");

        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Total :");

        lb_total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_total.setText("...");

        jPanel3.setOpaque(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Jasa");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Barang");

        btn_remove_jasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_minus_11px.png"))); // NOI18N
        btn_remove_jasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remove_jasaActionPerformed(evt);
            }
        });

        btn_add_jasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_add_11px.png"))); // NOI18N
        btn_add_jasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_jasaActionPerformed(evt);
            }
        });

        tf_qty_jasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_qty_jasaKeyTyped(evt);
            }
        });

        tf_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_totalKeyTyped(evt);
            }
        });

        tf_ok_jasa.setText("OK");
        tf_ok_jasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ok_jasaActionPerformed(evt);
            }
        });

        jLabel1.setText("Total Harga");

        jLabel2.setText("Qty");

        jLabel5.setText("Nama Barang");

        jLabel6.setText("Jasa");

        jLabel7.setText("ID");

        jLabel8.setText("Nama");

        jLabel9.setText("Stok");

        jLabel10.setText("Harga");

        jLabel11.setText("Qty");

        tf_qty_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_qty_brgKeyTyped(evt);
            }
        });

        lb_harga.setText("0");

        lb_stok.setText("0");

        lb_nama.setText("...");

        lb_id.setText("...");

        btn_ok_barang.setText("OK");
        btn_ok_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ok_barangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGap(51, 51, 51)
                                    .addComponent(btn_ok_barang))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(tf_qty_brg, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lb_harga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lb_stok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lb_id, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addGap(90, 90, 90))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tf_ok_jasa))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_add_jasa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_remove_jasa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tf_total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                .addComponent(tf_qty_jasa, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(cb_jasa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_jasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_id, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_remove_jasa)
                            .addComponent(btn_add_jasa)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_qty_jasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(tf_qty_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_ok_jasa)
                    .addComponent(btn_ok_barang))
                .addGap(6, 6, 6))
        );

        lb_tgl.setText("30-12-2025");

        jLabel17.setText("Tanggal :");

        btn_ubah_faktur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_transfer_11px.png"))); // NOI18N
        btn_ubah_faktur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubah_fakturActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bg21Layout = new javax.swing.GroupLayout(bg21);
        bg21.setLayout(bg21Layout);
        bg21Layout.setHorizontalGroup(
            bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                            .addGroup(bg21Layout.createSequentialGroup()
                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bg21Layout.createSequentialGroup()
                                        .addComponent(tf_pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_cari)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_reset))
                                    .addGroup(bg21Layout.createSequentialGroup()
                                        .addComponent(btn_ubah_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lb_no_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8)
                            .addGroup(bg21Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_tgl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        bg21Layout.setVerticalGroup(
            bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg21Layout.createSequentialGroup()
                .addComponent(header_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cari)
                            .addComponent(btn_reset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(8, 8, 8)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ubah_faktur)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(lb_no_faktur))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_ubah)
                        .addGap(18, 18, 18)
                        .addComponent(btn_hapus)
                        .addGap(18, 18, 18)
                        .addComponent(btn_selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lb_tgl)
                                .addComponent(jLabel17))
                            .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(lb_total)))))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_stokActionPerformed
        Rectangle penjualanBound = this.getBounds();
        new stok(penjualanBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_stokActionPerformed

    private void tbl_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_barangMouseClicked
        int bar = tbl_barang.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        
        lb_id.setText(a);
        lb_nama.setText(b);
        lb_stok.setText(c);
        lb_harga.setText(d);
        tf_qty_brg.setText("");
    }//GEN-LAST:event_tbl_barangMouseClicked

    private void btn_add_jasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_jasaActionPerformed
        JTextField jasa_add = new JTextField();
        Object[] fields = {
            "Jasa :", jasa_add            
        };
        int ok = JOptionPane.showConfirmDialog(null, fields, "Tambah Jasa",JOptionPane.OK_CANCEL_OPTION);
        String str_jasa = jasa_add.getText();
        if (ok == 0){
            if (cekJasa(str_jasa)){
                JOptionPane.showMessageDialog(null, "Jasa sudah ada !");
            }else {
                String sql = "INSERT INTO jasa VALUES (?,?)";
                try{
                    PreparedStatement stat = conn.prepareStatement (sql);
                    stat.setString(1, null);
                    stat.setString(2, str_jasa);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Jasa berhasil disimpan !");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e);
                }
                cb_jasa.addItem(str_jasa);
            }
        }
    }//GEN-LAST:event_btn_add_jasaActionPerformed

    protected boolean cekJasa(String nama){
        boolean result = false;
        String jasa = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT nama_jasa FROM jasa");
            while(hasil.next()){
                jasa = hasil.getString("nama_jasa");
                if (jasa.equals(nama)){
                    result = true;
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil data jasa");
        }
        return result;
    }
    
    private void btn_remove_jasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remove_jasaActionPerformed
        JComboBox jasa_remove = new JComboBox();
        String str_jasa = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT nama_jasa FROM jasa");
            while(hasil.next()){
                str_jasa = hasil.getString("nama_jasa"); 
                jasa_remove.addItem(str_jasa);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil data jasa");
        }
        Object[] fields = {
            "Jasa :", jasa_remove            
        };
        int ok = JOptionPane.showConfirmDialog(null, fields, "Hapus Jasa",JOptionPane.OK_CANCEL_OPTION);
        
        String nama = jasa_remove.getSelectedItem().toString();
        if (ok==0){
            String sql = "DELETE FROM jasa WHERE nama_jasa='"+nama+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
                               
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
            cb_jasa.removeItem(jasa_remove.getSelectedItem());
        }
    }//GEN-LAST:event_btn_remove_jasaActionPerformed

    protected String[] getKodeSingkatan(){
        String[] kode_singkatan = new String[2];
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(""
                    + "SELECT kode_penjualan, singkatan_admin "
                    + "FROM admin WHERE id_admin = '"+/*id_admin+*/"' ");
            while(hasil.next()){
                kode_singkatan[0] = hasil.getString("kode_penjualan");
                kode_singkatan[1] = hasil.getString("singkatan_admin");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil data jasa");
        }
        return kode_singkatan;
    }
    
    private void btn_ubah_fakturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubah_fakturActionPerformed
        String kode = "", singkatan = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(""
                    + "SELECT kode_penjualan, singkatan_admin "
                    + "FROM admin WHERE id_admin = '"+id_admin+"' ");
            while(hasil.next()){
                kode = hasil.getString("kode_penjualan");
                singkatan = hasil.getString("singkatan_admin");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil kode dan singkatan");
        }
        
        kode_penjualan.setText(kode);
        singkatan_admin.setText(singkatan);
        Object[] fields = {
            "Kode Penjualan (2 karakter) :", kode_penjualan,
            "Singkatan Admin (3 karakter) :", singkatan_admin
        };
        int ok = JOptionPane.showConfirmDialog(null, fields, "Ubah Format Faktur",JOptionPane.OK_CANCEL_OPTION);
        
        if (ok==0){
            kode = kode_penjualan.getText();
            singkatan = singkatan_admin.getText();
            kode = kode.substring(0,2).toUpperCase();
            singkatan = singkatan.substring(0,3).toUpperCase();
            System.out.println("kode : "+kode+"\nsingkatan : "+singkatan);
        }
    }//GEN-LAST:event_btn_ubah_fakturActionPerformed

    public void myInitComponents(){
        kode_penjualan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (kode_penjualan.getText().length()>=2)
                    e.consume();
            }
        });
        
        singkatan_admin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (singkatan_admin.getText().length()>=3)
                    e.consume();
            }
        });
    }
    
    protected void getNoFaktur(){
        //getKode&Singkatan
        String kode = "", singkatan = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(""
                    + "SELECT kode_penjualan, singkatan_admin "
                    + "FROM admin WHERE id_admin = '"+id_admin+"'");
            while(hasil.next()){
                kode = hasil.getString("kode_penjualan");
                singkatan = hasil.getString("singkatan_admin");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil kode dan singkatan");
        }

        //getDateNow
        Date date = new Date();
        SimpleDateFormat formatting = new SimpleDateFormat("MM");
        SimpleDateFormat formatting2 = new SimpleDateFormat("yyyy");
        String month = formatting.format(date);
        String year = formatting2.format(date);

        //get penjualan perbulan
        int jml = 0;
        String str_jml = "";
         
        try{
            String sql = "SELECT COUNT(no_faktur_jual) FROM penjualan "
                        + "WHERE MONTH(tgl_jual) = '"+month+"'";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                jml = hasil.getInt("COUNT(no_faktur_jual)");
                jml++;
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil jumlah penjualan");
        }
        str_jml = String.format("%04d", jml);
        month = String.format("%02d", Integer.valueOf(month));
        year = year.substring(2,4);
        //System.out.println("Jumlah : "+str_jml+"\nMonth : "+month+"\nYear : "+year);
        String no_faktur = kode + str_jml + "/" + month + "/" + year + "-" + singkatan;
        //System.out.println("No_Faktur : "+no_faktur);
        lb_no_faktur.setText(no_faktur);
    }
    
    private void btn_ok_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ok_barangActionPerformed
        int count = tbl_transaksi.getRowCount();
        arr_id[count] = lb_id.getText();
        arr_jasa[count] = "-";
        arr_nama[count] = lb_nama.getText();
        arr_harga[count] = lb_harga.getText();
        arr_qty[count] = tf_qty_brg.getText();
        if(arr_qty[count].equals("")){
            JOptionPane.showMessageDialog(null,"Qty kosong, silahkan isi terlebih dahulu");
            return;
        } else if (arr_id[count].equals("...")){
            JOptionPane.showMessageDialog(null,"Pilih barang pada tabel kiri atas terlebih dahulu");
            return;
        }
        arr_jumlah[count] = ""+(Integer.valueOf(arr_harga[count])*Integer.valueOf(arr_qty[count]));
        System.out.println("jasa = "+arr_jasa[count]);
        
        if (count >= 50){
            JOptionPane.showMessageDialog(null, "Maksimal 50 data dalam satu transaksi, simpan semua terlebih dahulu");
            return;
        } else if (count > 0){
            for(int i = 0; i < count;i++){
                if (arr_id[i].equals(arr_id[count]) && arr_jasa[i].equals(arr_jasa[count])){
                    System.out.println("jasa 2 = "+arr_jasa[i]);
                    arr_qty[i] = ""+(Integer.valueOf(arr_qty[i])+Integer.valueOf(arr_qty[count]));
                    arr_jumlah[i] = ""+ (Integer.valueOf(arr_harga[i])*Integer.valueOf(arr_qty[i]));
                    Object[] Kolom = {"ID Barang", "Jasa", "Nama Barang", "Harga", "Qty", "Jumlah"};
                    tabmode2 = new DefaultTableModel(null, Kolom);
                    tbl_transaksi.setModel(tabmode2);
                    String[] data = new String [1];
        
                    for(int j=0; j<count;j++){
                        data = new String[] {arr_id[j], arr_jasa[j], arr_nama[j], arr_harga[j], arr_qty[j], arr_jumlah[j]};
                        tabmode2.addRow(data);
                    }
                    getTotal();
                    return;
                }
            }
        }
        
        Object[] Kolom = {"ID Barang", "Jasa", "Nama Barang", "Harga", "Qty", "Jumlah"};
        tabmode2 = new DefaultTableModel(null, Kolom);
        tbl_transaksi.setModel(tabmode2);
        String[] data = new String [1];
        
        for(int i=0; i<count+1;i++){
            data = new String[] {arr_id[i],arr_jasa[i], arr_nama[i], arr_harga[i], arr_qty[i], arr_jumlah[i]};
            tabmode2.addRow(data);
        }
        getTotal();
    }//GEN-LAST:event_btn_ok_barangActionPerformed

    private void getTotal(){
        int count = tbl_transaksi.getRowCount();
        int jumlah = 0;
        for (int i = 0; i<count; i++){
            jumlah += Integer.valueOf(tabmode2.getValueAt(i,5).toString());
        }
        lb_total.setText(""+jumlah);
    }
    
    private void tf_qty_brgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_qty_brgKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_qty_brgKeyTyped

    private void tf_qty_jasaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_qty_jasaKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_qty_jasaKeyTyped

    private void tf_totalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_totalKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_totalKeyTyped

    private void tf_ok_jasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ok_jasaActionPerformed
        int count = tbl_transaksi.getRowCount();
        arr_nama[count] = "Kertas "+cb_barang.getSelectedItem().toString();
        arr_id[count] = getId(arr_nama[count]);
        arr_jasa[count] = cb_jasa.getSelectedItem().toString();
        arr_qty[count] = tf_qty_jasa.getText();
        arr_jumlah[count] = tf_total.getText();
        
        if (arr_qty[count].equals("")){
            JOptionPane.showMessageDialog(null, "Qty kosong, isi terlebih dahulu");
            return;
        } else if (arr_jumlah[count].equals("")){
            JOptionPane.showMessageDialog(null, "Jumlah kosong, isi terlebih dahulu");
            return;
        }
        arr_harga[count] = "" + (Integer.valueOf(arr_jumlah[count])/Integer.valueOf(arr_qty[count]));
        
        for(int i = 0; i < count;i++){
            if (arr_jasa[i].equals("-")){}
            else if (arr_jasa[i].equals(arr_jasa[count])){
                arr_qty[i] = ""+(Integer.valueOf(arr_qty[i])+Integer.valueOf(arr_qty[count]));
                arr_jumlah[i] = ""+(Integer.valueOf(arr_jumlah[i])+Integer.valueOf(arr_jumlah[count]));
                arr_harga[i] = "" + (Integer.valueOf(arr_jumlah[i])/Integer.valueOf(arr_qty[i]));
                
                Object[] Kolom = {"ID Barang", "Jasa", "Nama Barang", "Harga", "Qty", "Jumlah"};
                tabmode2 = new DefaultTableModel(null, Kolom);
                tbl_transaksi.setModel(tabmode2);
                String[] data = new String [1];
        
                for(int j=0; j<count;j++){
                    data = new String[] {arr_id[j], arr_jasa[j], arr_nama[j], arr_harga[j], arr_qty[j], arr_jumlah[j]};
                    tabmode2.addRow(data);
                }
                getTotal();
                return;
            }
        }
        
        Object[] Kolom = {"ID Barang", "Jasa", "Nama Barang", "Harga", "Qty", "Jumlah"};
        tabmode2 = new DefaultTableModel(null, Kolom);
        tbl_transaksi.setModel(tabmode2);
        String[] data = new String [1];
        
        for(int i=0; i<count+1;i++){
            data = new String[] {arr_id[i], arr_jasa[i], arr_nama[i], arr_harga[i], arr_qty[i], arr_jumlah[i]};
            tabmode2.addRow(data);
        }
        getTotal();
    }//GEN-LAST:event_tf_ok_jasaActionPerformed
    
    private String getId(String nama){
        System.out.println("nama : "+nama);
        String id = "";
        try{
            String sql = "SELECT id_brg FROM barang "
                        + "WHERE nama_brg = '"+nama+"'";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                id = hasil.getString("id_brg");
            }
        } catch (SQLException ee){
            JOptionPane.showMessageDialog(null, "Gagal mengambil id barang");
        }
        System.out.println("id : "+id);
        return id;
    }
    
    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
        int bar = tbl_transaksi.getSelectedRow();
        
        String a = tabmode2.getValueAt(bar, 0).toString(); //id_barang
        String b = tabmode2.getValueAt(bar, 1).toString(); //jasa
        String c = tabmode2.getValueAt(bar, 2).toString(); //nama_barang
        String d = tabmode2.getValueAt(bar, 3).toString(); //harga
        String e = tabmode2.getValueAt(bar, 4).toString(); //qty
        String f = tabmode2.getValueAt(bar, 5).toString(); //jumlah
        
        if (a.equals("-")){
            //jasa
            cb_jasa.setSelectedItem(b);
            cb_barang.setSelectedItem(c);
            tf_qty_jasa.setText(e);
            tf_total.setText(f);
            
        }else{
            //barang
            lb_id.setText(a);
            lb_nama.setText(c);
            
            //get_stok
            String stok = "";
            try{
                String sql = "SELECT qty_brg FROM barang "
                            + "WHERE id_brg = '"+a+"'";
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while(hasil.next()){
                    stok = hasil.getString("qty_brg");
                }
            } catch (SQLException ee){
                JOptionPane.showMessageDialog(null, "Gagal mengambil stok barang");
            }
            
            lb_stok.setText(stok);
            lb_harga.setText(d);
            tf_qty_brg.setText(e);
        }
    }//GEN-LAST:event_tbl_transaksiMouseClicked

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        int bar = tbl_transaksi.getSelectedRow();
        if (bar == -1){
            JOptionPane.showMessageDialog(null, "Pilih baris data yang ingin diubah");
            return;
        }
        int count = tbl_transaksi.getRowCount();
        
        String jasa = tabmode2.getValueAt(bar, 1).toString();
        
        
        if (jasa.equals("-")){
            // barang
            arr_qty[bar] = tf_qty_brg.getText();
            
            if(arr_qty[bar].equals("")){
                JOptionPane.showMessageDialog(null,"Qty kosong, silahkan isi terlebih dahulu");
            } else {
                arr_id[bar] = lb_id.getText();
                arr_jasa[bar] = "-";
                arr_nama[bar] = lb_nama.getText();
                arr_harga[bar] = lb_harga.getText();
                arr_jumlah[bar] = ""+(Integer.valueOf(arr_harga[bar])*Integer.valueOf(arr_qty[bar]));
                
                Object[] Kolom = {"ID Barang", "Jasa", "Nama Barang", "Harga", "Qty", "Jumlah"};
                tabmode2 = new DefaultTableModel(null, Kolom);
                tbl_transaksi.setModel(tabmode2);
                String[] data = new String [1];
        
                for(int i=0; i<count;i++){
                    data = new String[] {arr_id[i], arr_jasa[i], arr_nama[i], arr_harga[i], arr_qty[i], arr_jumlah[i]};
                    tabmode2.addRow(data);
                }
                getTotal();
            }
        } else {
            // jasa
            arr_qty[bar] = tf_qty_jasa.getText();
            arr_jumlah[bar] = tf_total.getText();
            
            if (arr_qty[bar].equals("")){
                JOptionPane.showMessageDialog(null, "Qty kosong, isi terlebih dahulu");
            } else if (arr_jumlah[bar].equals("")){
                JOptionPane.showMessageDialog(null, "Jumlah kosong, isi terlebih dahulu");
            } else {
                arr_jasa[bar] = cb_jasa.getSelectedItem().toString();
                arr_nama[bar] = "Kertas "+cb_barang.getSelectedItem().toString();
                arr_harga[bar] = "" + (Integer.valueOf(arr_jumlah[bar])/Integer.valueOf(arr_qty[bar]));
                arr_id[bar] = getId(arr_nama[bar]);
                
                Object[] Kolom = {"ID Barang", "Jasa", "Nama Barang", "Harga", "Qty", "Jumlah"};
                tabmode2 = new DefaultTableModel(null, Kolom);
                tbl_transaksi.setModel(tabmode2);
                String[] data = new String [1];
        
                for(int i=0; i<count;i++){
                    data = new String[] {arr_id[i], arr_jasa[i], arr_nama[i], arr_harga[i], arr_qty[i], arr_jumlah[i]};
                    tabmode2.addRow(data);
                }
                getTotal();
            }   
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int bar = tbl_transaksi.getSelectedRow();
        if (bar == -1){
            JOptionPane.showMessageDialog(null, "Pilih baris data yang ingin diubah");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            tabmode2.removeRow(bar);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        }
        getTotal();
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesaiActionPerformed
        int count = tbl_transaksi.getRowCount();
        if (count == 0){
            JOptionPane.showMessageDialog(null, "Tabel kosong, silahkan isi tabel terlebih dahulu");
        } else {
            String faktur = lb_no_faktur.getText();
            int total = Integer.valueOf(lb_total.getText());
            String tgl = lb_tgl.getText();
            
            String a = tgl.substring(6,10);
            String b = tgl.substring(3,5);
            String c = tgl.substring(0,2);
            tgl = a+"-"+b+"-"+c;
            String sql = "INSERT INTO penjualan VALUES (?,?,?)";
            try{
                PreparedStatement stat = conn.prepareStatement (sql);
                stat.setString(1, faktur);
                stat.setInt(2, total);
                stat.setString(3, tgl);
                
                stat.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal insert data pembelian"+e);
            }
            
            sql = "INSERT INTO penjualan_detail VALUE (?,?,?,?,?)";
            for (int i = 0; i < count; i++){
                try{
                    PreparedStatement stat2 = conn.prepareStatement (sql);
                    stat2.setString(1, faktur);
                    stat2.setString(2, arr_id[i]);
                    stat2.setString(3, arr_jasa[i]);
                    stat2.setString(4, arr_qty[i]);
                    stat2.setString(5, arr_jumlah[i]);
                
                    stat2.executeUpdate();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Gagal insert data pembelian_detail"+e);
                }
            }
            
            //kurangi stok
            for (int i = 0; i < count; i++){
                try {
                    int qty = 0;
                    sql = "SELECT qty_brg FROM barang "
                        + "WHERE nama_brg = '"+arr_nama[i]+"'";
                    Statement stat3 = conn.createStatement();
                    ResultSet hasil = stat3.executeQuery(sql);
                    while(hasil.next()){
                        qty = hasil.getInt("qty_brg");
                    }
                    arr_qty[i] = ""+ (qty - Integer.valueOf(arr_qty[i]));
                    sql = "UPDATE barang SET qty_brg=? WHERE nama_brg=?";
                    PreparedStatement stat4 = conn.prepareStatement(sql);
                    stat4.setString(1, arr_qty[i]);
                    stat4.setString(2, arr_nama[i]);
            
                    stat4.executeUpdate();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
                }
            }
            tabelBarang();
            getNoFaktur();
            Object[] Kolom = {"ID Barang", "Jasa", "Nama Barang", "Harga", "Qty", "Jumlah"};
            tabmode2 = new DefaultTableModel(null, Kolom);
            tbl_transaksi.setModel(tabmode2);
        }
    }//GEN-LAST:event_btn_selesaiActionPerformed
    
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
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new penjualan().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utility.bg2 bg21;
    private javax.swing.JButton btn_add_jasa;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_ok_barang;
    private javax.swing.JButton btn_remove_jasa;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_selesai;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JButton btn_ubah_faktur;
    private javax.swing.JComboBox<String> cb_barang;
    private javax.swing.JComboBox<String> cb_jasa;
    private javax.swing.JPanel header_menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lb_harga;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_nama;
    private javax.swing.JLabel lb_no_faktur;
    private javax.swing.JLabel lb_stok;
    private javax.swing.JLabel lb_tgl;
    private javax.swing.JLabel lb_total;
    private javax.swing.JButton menu_barang;
    private javax.swing.JButton menu_laporan;
    private javax.swing.JButton menu_pegawai;
    private javax.swing.JButton menu_pembelian;
    private javax.swing.JButton menu_penjualan;
    private javax.swing.JButton menu_penyesuaian;
    private javax.swing.JButton menu_stok;
    private javax.swing.JButton menu_supplier;
    private javax.swing.JTable tbl_barang;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JButton tf_ok_jasa;
    private javax.swing.JTextField tf_pencarian;
    private javax.swing.JTextField tf_qty_brg;
    private javax.swing.JTextField tf_qty_jasa;
    private javax.swing.JTextField tf_total;
    // End of variables declaration//GEN-END:variables
}
    
