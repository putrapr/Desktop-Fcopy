package fcopy;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utility.koneksi_database;

/**
 * @author Putra Prasetya
 */
public class pembelian extends javax.swing.JFrame {
    private Connection conn = new koneksi_database().connect();
    private DefaultTableModel tabmode;
    private int id_admin;
    private String[] arr_id = new String[50];
    private String[] arr_nama = new String[50];
    private String[] arr_satuan = new String[50];
    private String[] arr_harga_beli = new String[50];
    private String[] arr_qty = new String[50];
    private String[] arr_jumlah = new String[50];
    
    public pembelian() {
        initComponents();
        setMinimumSize(new Dimension(850,540));
        getNamaBarang();
        getPembelianKe();
        getDateString();
        getSupplier();
    }
    
    public pembelian(Rectangle bound, int id) {
        initComponents();
        setMinimumSize(new Dimension(850,540));
        getNamaBarang();
        getPembelianKe();
        getDateString();
        getSupplier();
        this.id_admin = id;
        
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

    protected void clear(){
        cb_nama.setSelectedIndex(0);
        cb_supplier.setSelectedIndex(0);
        tf_no_faktur.setText("");
        tf_qty.setText("");
        lb_jumlah.setText("...");
        tf_ppn.setText("");
        lb_total.setText("...");
        Object[] Kolom = {"ID Barang", "Nama Barang", "Satuan", "Harga Beli", "Qty", "Jumlah"};
        tabmode = new DefaultTableModel(null, Kolom);
        tbl_pembelian.setModel(tabmode);
    }
    
    protected void getNamaBarang(){
        String sql = "SELECT nama_template FROM template_barang "
                   + "WHERE status = 'Aktif'";
        try {
            String a = "";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                a = hasil.getString("nama_template");
                cb_nama.addItem(a); 
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Nama : " + e);
        }
    }
    
    protected void getPembelianKe(){
        try {
            int nilai = 0;
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT COUNT(no_faktur_beli) FROM pembelian");
            while(hasil.next()){
                nilai = hasil.getInt("COUNT(no_faktur_beli)");
            }
            nilai++;
            lb_pembelian.setText(""+nilai);
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal pembelian ke-n : " + e);
        }
    }
    
    protected void getDateString(){
        Date date = new Date();
        SimpleDateFormat formatting = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = formatting.format(date);
        lb_tgl.setText(strDate);
    }
    
    protected void getSupplier(){
        try {
            String a = "";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT nama_sup "
                                                + "FROM supplier ");
                                               
            while (hasil.next()){
                a = hasil.getString("nama_sup");
                cb_supplier.addItem(a); 
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Supplier : " + e);
        }
    }
    
    protected void getTotal(){
        int hasil = 0;
        String str_ppn = tf_ppn.getText();
        int ppn = 0;
        int count = tbl_pembelian.getRowCount();
        
        if(!(arr_jumlah[0]==null)){
            for (int i = 0; i<count ;i++){
                hasil += Integer.valueOf(arr_jumlah[i]);
            }
            
            if (str_ppn.equals("")){}
            else {
                ppn = Integer.valueOf(str_ppn);
            }
            hasil+=ppn;
            lb_total.setText(""+hasil);
        } else {
            lb_total.setText(str_ppn);
        }
    }
    
    protected String getFormatDate(String date){
        String year = date.substring(6,10);
        String month = date.substring(3,5);
        String  dt = date.substring(0,2);
        date = year+"-"+month+"-"+dt;
        return date;
    }
    
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
        jLabel15 = new javax.swing.JLabel();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_pembelian = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        tf_no_faktur = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tf_ppn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lb_total = new javax.swing.JLabel();
        btn_simpan_semua = new javax.swing.JButton();
        lb_tgl = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lb_pembelian = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cb_nama = new javax.swing.JComboBox<>();
        lb_id = new javax.swing.JLabel();
        lb_satuan = new javax.swing.JLabel();
        tf_harga_beli = new javax.swing.JTextField();
        tf_qty = new javax.swing.JTextField();
        lb_jumlah = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        cb_supplier = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 450));

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
        menu_pembelian.setForeground(new java.awt.Color(255, 255, 255));
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
                .addContainerGap(142, Short.MAX_VALUE))
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

        jLabel15.setText("Tanggal");

        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        tbl_pembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Barang", "Satuan", "Harga Beli", "Qty", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_pembelian.getTableHeader().setReorderingAllowed(false);
        tbl_pembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pembelianMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_pembelian);
        if (tbl_pembelian.getColumnModel().getColumnCount() > 0) {
            tbl_pembelian.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_pembelian.getColumnModel().getColumn(2).setPreferredWidth(10);
            tbl_pembelian.getColumnModel().getColumn(3).setPreferredWidth(10);
            tbl_pembelian.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jLabel17.setText("No. Faktur");

        jLabel18.setText("Supplier");

        jLabel1.setText("PPN :");

        tf_ppn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_ppnKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_ppnKeyTyped(evt);
            }
        });

        jLabel2.setText("Total :");

        lb_total.setText("...");

        btn_simpan_semua.setBackground(new java.awt.Color(255, 0, 0));
        btn_simpan_semua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_simpan_semua.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpan_semua.setText("SIMPAN SEMUA");
        btn_simpan_semua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_semuaActionPerformed(evt);
            }
        });

        lb_tgl.setText("___");

        jLabel19.setText("Pembelian ke-");

        lb_pembelian.setText("___");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("INPUT");

        jLabel7.setText("ID Barang");

        jLabel8.setText("Nama Barang");

        jLabel9.setText("Satuan");

        jLabel10.setText("Harga Beli");

        jLabel11.setText("Qty");

        jLabel12.setText("Jumlah");

        cb_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_namaActionPerformed(evt);
            }
        });

        lb_id.setText("...");

        lb_satuan.setText("...");

        tf_harga_beli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_harga_beliFocusLost(evt);
            }
        });
        tf_harga_beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_harga_beliKeyTyped(evt);
            }
        });

        tf_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_qtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_qtyKeyTyped(evt);
            }
        });

        lb_jumlah.setText("...");

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_nama, 0, 210, Short.MAX_VALUE)
                    .addComponent(tf_harga_beli)
                    .addComponent(tf_qty)
                    .addComponent(lb_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_satuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btn_simpan)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lb_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lb_satuan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_harga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tf_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lb_jumlah)
                    .addComponent(btn_simpan))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bg21Layout = new javax.swing.GroupLayout(bg21);
        bg21.setLayout(bg21Layout);
        bg21Layout.setHorizontalGroup(
            bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bg21Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_ppn, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_simpan_semua, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_no_faktur, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(lb_tgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lb_pembelian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_supplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18))
        );
        bg21Layout.setVerticalGroup(
            bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg21Layout.createSequentialGroup()
                .addComponent(header_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lb_pembelian))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lb_tgl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_no_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bg21Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(cb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_ubah)
                                    .addComponent(btn_hapus))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_ppn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(lb_total)
                    .addComponent(btn_simpan_semua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
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

    private void menu_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_pegawaiActionPerformed
        Rectangle pembelianBound = this.getBounds();
        new pegawai(pembelianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_pegawaiActionPerformed

    private void menu_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_supplierActionPerformed
        Rectangle pembelianBound = this.getBounds();
        new supplier(pembelianBound, id_admin).setVisible(true);
        this.dispose();      
    }//GEN-LAST:event_menu_supplierActionPerformed

    private void menu_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_pembelianActionPerformed
        
    }//GEN-LAST:event_menu_pembelianActionPerformed

    private void menu_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_barangActionPerformed
        Rectangle pembelianBound = this.getBounds();
        new barang(pembelianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_barangActionPerformed

    private void menu_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_penjualanActionPerformed
        Rectangle pembelianBound = this.getBounds();
        new penjualan(pembelianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_penjualanActionPerformed

    private void menu_penyesuaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_penyesuaianActionPerformed
        Rectangle pembelianBound = this.getBounds();
        new penyesuaian(pembelianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_penyesuaianActionPerformed

    private void menu_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_laporanActionPerformed
        Rectangle pembelianBound = this.getBounds();
        new laporan(pembelianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_laporanActionPerformed

    private void menu_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_stokActionPerformed
        Rectangle pembelianBound = this.getBounds();
        new stok(pembelianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_stokActionPerformed

    private void cb_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_namaActionPerformed
        String nama = cb_nama.getSelectedItem().toString(); 
        String sql = "SELECT id_template, satuan_template, harga_beli_template "
                        + "FROM template_barang "
                        + "WHERE nama_template = '"+nama+"'";
        
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);            
            String a = "", b = "", c = "";
            
            while(hasil.next()){
                a = hasil.getString("id_template");
                b = hasil.getString("satuan_template");
                c = hasil.getString("harga_beli_template");
            }
            lb_id.setText(a);
            lb_satuan.setText(b);
            tf_harga_beli.setText(c);
            tf_qty.setText("");
            lb_jumlah.setText("...");
        }catch(SQLException ee){
            JOptionPane.showMessageDialog(null, "Data gagal diambil"+ee);
        }
    }//GEN-LAST:event_cb_namaActionPerformed

    private void tf_harga_beliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_harga_beliFocusLost
        String id = lb_id.getText();
        int harga_baru = Integer.valueOf(tf_harga_beli.getText());
        int harga_beli = 0;
        try {     
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT harga_beli_template "
                                                + "FROM template_barang "
                                                + "WHERE id_template = '"+id+"'");
            while (hasil.next()){
                harga_beli = hasil.getInt("harga_beli_template");
                if (harga_beli != harga_baru){
                    int ok = JOptionPane.showConfirmDialog(null,
                            "Ubah harga beli dari "+harga_beli+" menjadi "+harga_baru+" ?",
                            "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
                    if (ok==1){
                        tf_harga_beli.setText(""+harga_beli);
                    }
                }
            }            
        }catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Satuan : " + e);
        }
    }//GEN-LAST:event_tf_harga_beliFocusLost

    private void tf_harga_beliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_harga_beliKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_harga_beliKeyTyped

    private void tf_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_qtyKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_qtyKeyTyped

    private void tf_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_qtyKeyReleased
        String str_qty = tf_qty.getText();
        if (str_qty.equals("")){
            lb_jumlah.setText("...");
        }else {
            int harga_beli = Integer.valueOf(tf_harga_beli.getText());
            int qty = Integer.valueOf(tf_qty.getText());
            int jumlah = harga_beli * qty;
            lb_jumlah.setText(""+jumlah);
        }
    }//GEN-LAST:event_tf_qtyKeyReleased

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String id = lb_id.getText();
        String nama = cb_nama.getSelectedItem().toString();
        int count = tbl_pembelian.getRowCount();
        arr_id[count] = id;
        if (count >= 50){
            JOptionPane.showMessageDialog(null, "Maksimal 50 data dalam satu transaksi, simpan semua terlebih dahulu");
            return;
        }else if(tf_qty.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Qty kosong, silahkan isi qty terlebih dahulu");
            return;
        }else if(tf_harga_beli.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Harga beli kosong, silahkan isi harga beli terlebih dahulu");
            return;
        }else if (count > 0){
            for(int i = 0; i < count;i++){
                if (id.equals(arr_id[i])){
                    JOptionPane.showMessageDialog(null, "' "+nama+" ' sudah ada, silahkan ubah data");
                    return;
                }
            }
        }
        
        arr_nama[count] = nama;
        arr_satuan[count] = lb_satuan.getText();
        arr_harga_beli[count] = tf_harga_beli.getText();
        arr_qty[count] = tf_qty.getText();
        arr_jumlah[count] = lb_jumlah.getText();
        
        Object[] Kolom = {"ID Barang", "Nama Barang", "Satuan", "Harga Beli", "Qty", "Jumlah"};
        tabmode = new DefaultTableModel(null, Kolom);
        tbl_pembelian.setModel(tabmode);
        String[] data = new String [1];
        
        for(int i=0; i<count+1;i++){
            data = new String[] {arr_id[i], arr_nama[i], arr_satuan[i], arr_harga_beli[i], arr_qty[i], arr_jumlah[i]};
            tabmode.addRow(data);
        }
        getTotal();          
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void tbl_pembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pembelianMouseClicked
        int bar = tbl_pembelian.getSelectedRow();
        
        //String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        //String c = tabmode.getValueAt(bar, 2).toString();
        //String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
        
        //lb_id.setText(a);
        cb_nama.setSelectedItem(b);
        tf_qty.setText(e);
        lb_jumlah.setText(f);
    }//GEN-LAST:event_tbl_pembelianMouseClicked

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        int bar = tbl_pembelian.getSelectedRow();
        int count = tbl_pembelian.getRowCount();
        
        arr_harga_beli[bar] = tf_harga_beli.getText();
        arr_qty[bar] = tf_qty.getText();
        arr_jumlah[bar] = lb_jumlah.getText();
        
        Object[] Kolom = {"ID Barang", "Nama Barang", "Satuan", "Harga Beli", "Qty", "Jumlah"};
        tabmode = new DefaultTableModel(null, Kolom);
        tbl_pembelian.setModel(tabmode);
        String[] data = new String [1];
        
        for(int i=0; i<count+1;i++){
            data = new String[] {arr_id[i], arr_nama[i], arr_satuan[i], arr_harga_beli[i], arr_qty[i], arr_jumlah[i]};
            tabmode.addRow(data);
        }
        
        tabmode.removeRow(count);
        getTotal();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int bar = tbl_pembelian.getSelectedRow();
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            tabmode.removeRow(bar);
        }
        getTotal();
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void tf_ppnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_ppnKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_ppnKeyTyped

    private void tf_ppnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_ppnKeyReleased
        getTotal();
    }//GEN-LAST:event_tf_ppnKeyReleased

    protected int getIdSupplier(String nama){
        String a = "";
        int id = 0;
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT id_sup FROM supplier "
                                                + "WHERE nama_sup = '"+nama+"'");
                                               
            while (hasil.next()){
                a = hasil.getString("id_sup");
            }
            id = Integer.valueOf(a);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Supplier : " + e);
        }
        return id;
    }
    
    
    
    private void btn_simpan_semuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_semuaActionPerformed
        /*  Algorithm
            1. get no_faktur, id_sup, ppn, date, count_row_table, total
            2. if (no_faktur = null){print note}, 
               else if (count = 0){print note}, 
               else {(next algorithm)
            3.     if (no_faktur==""){print note, return;}
            4.     Insert data ke tbl_pembelian
            5.     Insert data ke tbl_pembelian_detail
            6.     setStok(no_faktur, id_brg)
            6.1       get id_temp, nama_temp, satuan_temp, harga_beli, harga_jual from template_barang
            6.2       get qty from pembelian detail
            6.3       get nama_sup, id_sup, tgl
            6.4       cek id_brg. Jika sama, update barang. Jika beda, insert barang.
        */
        
        // 1. get no_faktur, id_sup, ppn, qty, date, count_row_table, total
        String no_faktur = tf_no_faktur.getText();
        String nama_sup = cb_supplier.getSelectedItem().toString();         
        int id_sup = getIdSupplier(nama_sup);
        String str_qty = "";
        String str_ppn = tf_ppn.getText();
        int ppn = 0, qty = 0;
        if (str_ppn.equals("")){}
        else { ppn = Integer.valueOf(str_ppn); }
        
        String date = lb_tgl.getText();
        date = getFormatDate(date);
        int count = tbl_pembelian.getRowCount();
        int total = Integer.valueOf(lb_total.getText());
        
        // 2. if no_faktur = null, else if count = 0, else (next algorithm)
        if (no_faktur.equals("")){
            JOptionPane.showMessageDialog(null, "No.Faktur kosong, silahkan isi no.faktur terlebih dahulu");
        }else if (count == 0){
            JOptionPane.showMessageDialog(null, "Tabel kosong, silahkan isi tabel terlebih dahulu");
        }else{
        
            // 3. if (no_faktur==""){print note, return;}
            if (CekNoFaktur(no_faktur)){
                return;
            }
            
            // 4. Insert data ke tbl_pembelian
            InsertPembelian(no_faktur, id_sup, ppn, total, date);
            
            // 5. Insert data ke tbl_pembelian_detail
            InsertPembelianDetail(no_faktur, count);
            
            // 6. setStok(no_faktur, id_brg)
            String id_brg = "";
            for(int i = 0; i<count;i++){
                id_brg = arr_id[i];
                str_qty = arr_qty[i];
                qty = Integer.valueOf(str_qty);
                setStok(no_faktur, id_brg, qty);
            }
            // 6.1 get id_temp, nama_temp, satuan_temp, harga_beli, harga_jual from template_barang
            // 6.2 get qty from pembelian detail
            // 6.3 get nama_sup, id_sup, tgl
            // 6.4 cek id_brg. Jika sama, update barang. Jika beda, insert barang.
            
            clear();
        }
    }//GEN-LAST:event_btn_simpan_semuaActionPerformed

    protected boolean CekNoFaktur(String no_faktur){
        boolean kembalian = false;
        String db_faktur = "";
            try{
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery("SELECT no_faktur_beli FROM pembelian WHERE no_faktur_beli = '"+no_faktur+"'");
                while(hasil.next()){
                    db_faktur = hasil.getString("no_faktur_beli");
                }
                if (db_faktur.equals(no_faktur)){
                    JOptionPane.showMessageDialog(null, "No.faktur sudah ada !. Ubah No.faktur...");
                    kembalian = true;
                } 
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal mengambil No.faktur"+e);
            }
        return kembalian;
    }
    
    protected void InsertPembelian(String no_faktur, int id_sup, int ppn, int total, String date){
        String sql = "INSERT INTO pembelian VALUES (?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement (sql);
            stat.setString(1, no_faktur);
            stat.setInt(2, id_sup);
            stat.setInt(3, ppn);
            stat.setInt(4, total);
            stat.setString(5, date);
            
            stat.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal insert data pembelian"+e);
        }
    }
    
    protected void InsertPembelianDetail(String no_faktur, int count){
        String sql = "INSERT INTO pembelian_detail VALUES (?,?,?,?)";
        String id_brg = "";
        int qty = 0;
        int jumlah = 0;
           
        for(int i = 0; i < count;i++){
            id_brg = arr_id[i];
            qty = Integer.valueOf(arr_qty[i]);
            jumlah = Integer.valueOf(arr_jumlah[i]);
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, no_faktur);
                stat.setString(2, id_brg);
                stat.setInt(3, qty);
                stat.setInt(4, jumlah);
                stat.executeUpdate();                    
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal Insert data pembelian_detail"+e);
            }
        }
    }
    
    protected void setStok(String no_faktur, String id_brg, int qty){
        // get id_temp, nama_temp, satuan_temp, harga_beli, harga_jual 
        String id_temp = "", nama_temp = "", satuan_temp = "pcs";
        int konv = 0, harga_beli = 0, harga_jual = 0;
        
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT id_template, nama_template, konversi_satuan, "
                                                + "harga_beli_template, harga_jual_template "
                                                + "FROM template_barang "
                                                + "WHERE id_template = '"+id_brg+"'");
            while (hasil.next()){
                id_temp = hasil.getString("id_template");
                nama_temp = hasil.getString("nama_template");
                konv = hasil.getInt("konversi_satuan");
                harga_beli = hasil.getInt("harga_beli_template");
                harga_jual = hasil.getInt("harga_jual_template");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal mengambil data template barang : "+e);
        }
        
        // get nama_sup, id_sup, tgl
        String nama_sup = cb_supplier.getSelectedItem().toString();         
        int id_sup = getIdSupplier(nama_sup);
        String tgl = lb_tgl.getText();
        tgl = getFormatDate(tgl);
        qty *= konv;
        harga_beli /= konv;
        harga_jual /= konv;
        
        //cek id brg. Jika sama update, jika beda insert
        if (CekIdBrg(id_brg)){
            // Jika sama update
            UpdateStok(id_temp, nama_temp, satuan_temp, qty, harga_beli, harga_jual, id_sup, tgl);
        } else {
            // Jika beda insert
            AddStok(id_temp, nama_temp, satuan_temp, qty, harga_beli, harga_jual, id_sup, tgl);
        }
    }
  
    protected boolean CekIdBrg(String id_brg){
        boolean output = false;
        String id = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT id_brg FROM barang "
                                                + "WHERE id_brg = '"+id_brg+"'");
            while (hasil.next()){
                id = hasil.getString("id_brg");
            }
            if (id.equals(id_brg)){
                output = true;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal mengambil id barang : "+e);
        }
        return output;
    }
    
    protected void AddStok(String id_brg, String nama, String satuan, int qty, 
                              int harga_beli, int harga_jual, int id_sup, String tgl){
        String sql = "INSERT INTO barang VALUES (?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement (sql);
            stat.setString(1, id_brg);
            stat.setString(2, nama);
            stat.setString(3, satuan);
            stat.setInt(4, qty);
            stat.setInt(5, harga_beli);
            stat.setInt(6, harga_jual);
            stat.setInt(7, id_sup);
            stat.setString(8, tgl);
            
            stat.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal insert data barang"+e);
        }
    }
    
    protected void UpdateStok(String id_brg, String nama, String satuan, int qty, 
                              int harga_beli, int harga_jual, int id_sup, String tgl){
        int new_qty = 0;
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT qty_beli "
                                                + "FROM pembelian_detail "
                                                + "WHERE id_brg_beli_detail = '"+id_brg+"'");
            while (hasil.next()){
                new_qty = hasil.getInt("qty_beli");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal mengambil qty barang : "+e);
        }
        
        qty += new_qty;
        
        try {
            String sql = "UPDATE barang SET nama_brg=?, satuan_brg=?, qty_brg=?, harga_beli_brg=?,"
                         + "harga_jual_brg=?, supplier_brg=?, tgl_brg=? WHERE id_brg=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            
                stat.setString(1, nama);
                stat.setString(2, satuan);
                stat.setInt(3, qty);
                stat.setInt(4, harga_beli);
                stat.setInt(5, harga_jual);
                stat.setInt(6, id_sup);
                stat.setString(7, tgl);
                stat.setString(8, id_brg);
            
                stat.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal update data barang : "+e);
        }
    }
    
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
            java.util.logging.Logger.getLogger(pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utility.bg2 bg21;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_simpan_semua;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cb_nama;
    private javax.swing.JComboBox<String> cb_supplier;
    private javax.swing.JPanel header_menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_jumlah;
    private javax.swing.JLabel lb_pembelian;
    private javax.swing.JLabel lb_satuan;
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
    private javax.swing.JTable tbl_pembelian;
    private javax.swing.JTextField tf_harga_beli;
    private javax.swing.JTextField tf_no_faktur;
    private javax.swing.JTextField tf_ppn;
    private javax.swing.JTextField tf_qty;
    // End of variables declaration//GEN-END:variables
}
