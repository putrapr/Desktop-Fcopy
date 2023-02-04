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
public class penyesuaian extends javax.swing.JFrame {
    private Connection conn = new koneksi_database().connect();
    private DefaultTableModel tabmode;
    private int id_admin;
    public penyesuaian() {
        initComponents();
        setMinimumSize(new Dimension(850,540));
        getNamaBarang();
        rb_status_belum.setSelected(true);
        rb_tampil_tidak.setSelected(true);
        tabelPenyesuaian();
    }
    
    public penyesuaian(Rectangle bound, int id) {
        initComponents();
        setMinimumSize(new Dimension(850,540));
        this.setBounds(bound);
        this.id_admin = id;
        getNamaBarang();
        rb_status_belum.setSelected(true);
        rb_tampil_tidak.setSelected(true);
        tabelPenyesuaian();
        
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

    private void getNamaBarang(){
        String nama = "";
        try{
            String sql = "SELECT nama_brg FROM barang";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                nama = hasil.getString("nama_brg");
                cb_nama.addItem(nama);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil nama barang");
        }
    }
    
    private void tabelPenyesuaian(){
        if(rb_tampil_tidak.isSelected()){
            // status = belum sesuai
            Object[] Baris = {"Tanggal", "Nama Barang", "Qty (pcs)", "Keterangan", "Status"};
            tabmode = new DefaultTableModel(null, Baris);
            tbl_penyesuaian.setModel(tabmode);
            String nama = "", qty = "", ket = "", tgl = "", status="", a = "", b = "", c = "";
            String sql = "SELECT barang.nama_brg, "
                        + "penyesuaian.qty_suai, "
                        + "penyesuaian.ket_suai, "
                        + "penyesuaian.tgl_suai,"
                        + "penyesuaian.status_suai "
                        + "FROM penyesuaian "
                        + "JOIN barang ON (penyesuaian.id_brg_suai = barang.id_brg) "
                        + "WHERE status_suai = 'Belum Sesuai' "
                        + "ORDER BY tgl_suai DESC";
            try {
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while(hasil.next()){
                    nama = hasil.getString("nama_brg");
                    qty = hasil.getString("qty_suai");
                    ket = hasil.getString("ket_suai");
                    tgl = hasil.getString("tgl_suai");
                    status = hasil.getString("status_suai");
                    a = tgl.substring(8,10);
                    b = tgl.substring(5,7);
                    c = tgl.substring(0,4);
                    tgl = a+"-"+b+"-"+c;
                    String[] data={tgl, nama, qty, ket, status};
                    tabmode.addRow(data);
                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal ambil id barang : "+e);
            }
        }else if (rb_tampil_ya.isSelected()){
            // status = beres
            Object[] Baris = {"Tanggal", "Nama Barang", "Qty (pcs)", "Keterangan", "Status"};
            tabmode = new DefaultTableModel(null, Baris);
            tbl_penyesuaian.setModel(tabmode);
            String nama = "", qty = "", ket = "", tgl = "", status="", a = "", b = "", c = "";
            String sql = "SELECT barang.nama_brg, "
                        + "penyesuaian.qty_suai, "
                        + "penyesuaian.ket_suai, "
                        + "penyesuaian.tgl_suai,"
                        + "penyesuaian.status_suai "
                        + "FROM penyesuaian "
                        + "JOIN barang ON (penyesuaian.id_brg_suai = barang.id_brg) "
                        + "ORDER BY tgl_suai DESC";
            try {
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery(sql);
                while(hasil.next()){
                    nama = hasil.getString("nama_brg");
                    qty = hasil.getString("qty_suai");
                    ket = hasil.getString("ket_suai");
                    tgl = hasil.getString("tgl_suai");
                    status = hasil.getString("status_suai");
                    a = tgl.substring(8,10);
                    b = tgl.substring(5,7);
                    c = tgl.substring(0,4);
                    tgl = a+"-"+b+"-"+c;
                    String[] data={tgl, nama, qty, ket, status};
                    tabmode.addRow(data);
                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal ambil id barang : "+e);
            }
        }
        
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rb_grup_status = new javax.swing.ButtonGroup();
        rb_grup_tampilkan = new javax.swing.ButtonGroup();
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
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        tf_qty = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_penyesuaian = new javax.swing.JTable();
        cb_nama = new javax.swing.JComboBox<>();
        lb_harga_beli = new javax.swing.JLabel();
        lb_qty = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lb_supplier = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb_no_faktur = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rb_status_belum = new javax.swing.JRadioButton();
        rb_status_beres = new javax.swing.JRadioButton();
        alamatScrollPane = new javax.swing.JScrollPane();
        ta_ket = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        rb_tampil_ya = new javax.swing.JRadioButton();
        rb_tampil_tidak = new javax.swing.JRadioButton();

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
        menu_penyesuaian.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel25.setText("Nama Barang");

        jLabel29.setText("Qty Barang");

        tf_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_qtyKeyTyped(evt);
            }
        });

        jLabel30.setText("Keterangan");

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

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

        tbl_penyesuaian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tanggal", "Nama Barang", "Qty (pcs)", "Keterangan", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_penyesuaian.getTableHeader().setReorderingAllowed(false);
        tbl_penyesuaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_penyesuaianMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbl_penyesuaian);
        if (tbl_penyesuaian.getColumnModel().getColumnCount() > 0) {
            tbl_penyesuaian.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_penyesuaian.getColumnModel().getColumn(2).setPreferredWidth(10);
            tbl_penyesuaian.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        cb_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_namaActionPerformed(evt);
            }
        });

        lb_harga_beli.setText("...");

        lb_qty.setText("...");

        jLabel3.setText("Supplier");

        lb_supplier.setText("...");

        jLabel5.setText("Faktur Pembelian");

        lb_no_faktur.setText("...");

        jLabel31.setText("Jumlah");

        jLabel26.setText("Qty Bermasalah");

        jLabel27.setText("pcs");

        jLabel1.setText("Status");

        rb_grup_status.add(rb_status_belum);
        rb_status_belum.setText("Belum Sesuai");

        rb_grup_status.add(rb_status_beres);
        rb_status_beres.setText("Beres");

        ta_ket.setColumns(20);
        ta_ket.setRows(5);
        alamatScrollPane.setViewportView(ta_ket);

        jLabel2.setText("Tampilkan Semua :");

        rb_grup_tampilkan.add(rb_tampil_ya);
        rb_tampil_ya.setText("Ya");
        rb_tampil_ya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_tampil_yaActionPerformed(evt);
            }
        });

        rb_grup_tampilkan.add(rb_tampil_tidak);
        rb_tampil_tidak.setText("Tidak");
        rb_tampil_tidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_tampil_tidakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bg21Layout = new javax.swing.GroupLayout(bg21);
        bg21.setLayout(bg21Layout);
        bg21Layout.setHorizontalGroup(
            bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bg21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb_tampil_ya)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb_tampil_tidak)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11)
                            .addGroup(bg21Layout.createSequentialGroup()
                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(bg21Layout.createSequentialGroup()
                                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(bg21Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lb_supplier, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                                    .addComponent(lb_qty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lb_no_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(bg21Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(lb_harga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tf_qty)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel27))))
                                    .addGroup(bg21Layout.createSequentialGroup()
                                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(bg21Layout.createSequentialGroup()
                                                .addComponent(rb_status_belum)
                                                .addGap(18, 18, 18)
                                                .addComponent(rb_status_beres))
                                            .addComponent(alamatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18))))
        );
        bg21Layout.setVerticalGroup(
            bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg21Layout.createSequentialGroup()
                .addComponent(header_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(cb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_harga_beli)
                            .addComponent(jLabel31))
                        .addGap(10, 10, 10)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_qty)
                            .addComponent(jLabel29)))
                    .addComponent(alamatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(rb_status_belum, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_status_beres, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan)
                            .addComponent(btn_ubah)
                            .addComponent(btn_hapus)))
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lb_no_faktur))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rb_tampil_ya, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_tampil_tidak, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void menu_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_pegawaiActionPerformed
        Rectangle penyesuaianBound = this.getBounds();
        new pegawai(penyesuaianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_pegawaiActionPerformed

    private void menu_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_supplierActionPerformed
        Rectangle penyesuaianBound = this.getBounds();
        new supplier(penyesuaianBound, id_admin).setVisible(true);
        this.dispose();        
    }//GEN-LAST:event_menu_supplierActionPerformed

    private void menu_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_pembelianActionPerformed
        Rectangle penyesuaianBound = this.getBounds();
        new pembelian(penyesuaianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_pembelianActionPerformed

    private void menu_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_barangActionPerformed
        Rectangle penyesuaianBound = this.getBounds();
        new barang(penyesuaianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_barangActionPerformed

    private void menu_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_penjualanActionPerformed
        Rectangle penyesuaianBound = this.getBounds();
        new penjualan(penyesuaianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_penjualanActionPerformed

    private void menu_penyesuaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_penyesuaianActionPerformed
        
    }//GEN-LAST:event_menu_penyesuaianActionPerformed

    private void menu_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_laporanActionPerformed
        Rectangle penyesuaianBound = this.getBounds();
        new laporan(penyesuaianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_laporanActionPerformed

    private void menu_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_stokActionPerformed
        Rectangle penyesuaianBound = this.getBounds();
        new stok(penyesuaianBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_stokActionPerformed

    private void cb_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_namaActionPerformed
        // 1. Inisialisasi nama_brg, id_brg, hrg_beli, hrg_jual, supplier, faktur
        String nama = cb_nama.getSelectedItem().toString(); 
        String id_brg = "", hrg_beli="", supplier="", faktur="", satuan="";
        
        // 2. getIdBarang WHERE nama_brg = ...
        String sql = "SELECT id_brg FROM barang WHERE nama_brg = '"+nama+"'";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                id_brg = hasil.getString("id_brg");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil id barang : "+e);
        }
        
        // 3. get FakturPembelian, HargaBeli WHERE last id_brg = ...
        //      HargaBeli = Jumlah/qty;
        int qty = 0, jumlah = 0;
        sql = "SELECT pembelian_detail.no_faktur_beli_detail, "
                + "pembelian_detail.qty_beli, "
                + "pembelian_detail.jumlah_beli, "
                + "template_barang.satuan_template "
            + "FROM pembelian_detail "
            + "JOIN template_barang ON "
                + "(pembelian_detail.id_brg_beli_detail = "
                + "template_barang.id_template) "
            + "WHERE id_brg_beli_detail = '"+id_brg+"'";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                faktur = hasil.getString("no_faktur_beli_detail");
                qty = hasil.getInt("qty_beli");
                jumlah = hasil.getInt("jumlah_beli");
                satuan = hasil.getString("satuan_template");
            }
            hrg_beli = "" + (jumlah/qty);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil faktur dan harga beli barang : "+e);
        }
        
        // 4. getSupplier WHERE no_faktur = ...
        sql = "SELECT supplier.nama_sup FROM pembelian "
            + "JOIN supplier ON (pembelian.id_sup_beli = supplier.id_sup) "
            + "WHERE no_faktur_beli = '"+faktur+"'";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                supplier = hasil.getString("nama_sup");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil nama supplier : "+e);
        }
        
        lb_harga_beli.setText(hrg_beli);
        lb_qty.setText(""+qty+" / "+satuan);
        lb_supplier.setText(supplier);
        lb_no_faktur.setText(faktur);
    }//GEN-LAST:event_cb_namaActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String qty = tf_qty.getText();
        String keterangan = ta_ket.getText();
        if (qty.equals("")) {
            JOptionPane.showMessageDialog(null, "Qty Bermasalah kosong, silahkan isi terlebih dahulu");
        } else if(keterangan.equals("")) {
            JOptionPane.showMessageDialog(null, "Keterangan kosong, silahkan isi terlebih dahulu");
        } else {
            String nama = cb_nama.getSelectedItem().toString();
            String id_brg = getIdBrg(nama); 
            String id_sup = getIdSup(lb_supplier.getText());
            String faktur = lb_no_faktur.getText();          
            String tgl = getDateNow();
            String status="";
            if(rb_status_belum.isSelected()) status="Belum Sesuai";
            else status="Beres";
            
            String sql = "INSERT INTO penyesuaian VALUES (?,?,?,?,?,?,?,?)";
            try{
                PreparedStatement stat = conn.prepareStatement (sql);
                stat.setString(1, null);
                stat.setString(2, id_brg);
                stat.setString(3, id_sup);
                stat.setString(4, faktur);
                stat.setString(5, qty);
                stat.setString(6, keterangan);
                stat.setString(7, tgl);
                stat.setString(8, status);
            
                stat.executeUpdate(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan: " + e);
            }
            tabelPenyesuaian();
        }
        
    }//GEN-LAST:event_btn_simpanActionPerformed

    private String getIdBrg(String nama_brg){
        String id_brg = "";
        String sql = "SELECT id_brg FROM barang WHERE nama_brg = '"+nama_brg+"'";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                id_brg = hasil.getString("id_brg");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil id barang : "+e);
        }
        return id_brg;
    }
    
    private String getIdSup(String nama_sup){
        String sql = "SELECT id_sup FROM supplier WHERE nama_sup = '"+nama_sup+"'";
        String id_sup = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                id_sup = hasil.getString("id_sup");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil id supplier : "+e);
        }
        return id_sup;
    }
    
    private String getDateNow(){
        Date date = new Date();
        SimpleDateFormat formatting = new SimpleDateFormat("yyyy-MM-dd");
        String hasil = formatting.format(date);
        return hasil;
    }
    
    private void tf_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_qtyKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_qtyKeyTyped

    private void tbl_penyesuaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_penyesuaianMouseClicked
        int bar = tbl_penyesuaian.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString(); // Tanggal
        String b = tabmode.getValueAt(bar, 1).toString(); // Nama
        String c = tabmode.getValueAt(bar, 2).toString(); // Qty
        String d = tabmode.getValueAt(bar, 3).toString(); // Keterangan
        String e = tabmode.getValueAt(bar, 4).toString(); // Status
        
        cb_nama.setSelectedItem(b);
        //cb_nama.setEnabled(false);
        tf_qty.setText(c);
        ta_ket.setText(d);
        switch (e) {
                case "Belum Sesuai":
                    rb_status_belum.setSelected(true);
                    rb_status_beres.setSelected(false);
                    break;
                case "Beres":
                    rb_status_belum.setSelected(false);
                    rb_status_beres.setSelected(true);
                    break;
                default:
                    rb_status_belum.setSelected(false);
                    rb_status_beres.setSelected(false);
                    break;
            }
    }//GEN-LAST:event_tbl_penyesuaianMouseClicked

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        int bar = tbl_penyesuaian.getSelectedRow();
        if (bar == -1){
            JOptionPane.showMessageDialog(null, "Pilih baris data yang ingin diubah");
            return;
        }
        
        String qty = tf_qty.getText();
        String keterangan = ta_ket.getText();
        if (qty.equals("")) {
            JOptionPane.showMessageDialog(null, "Qty Bermasalah kosong, silahkan isi terlebih dahulu");
        } else if(keterangan.equals("")) {
            JOptionPane.showMessageDialog(null, "Keterangan kosong, silahkan isi terlebih dahulu");
        } else {
            String tgl_asal = tabmode.getValueAt(bar, 0).toString();
            String a = tgl_asal.substring(6,10);
            String b = tgl_asal.substring(3,5);
            String c = tgl_asal.substring(0,2);
            tgl_asal = a+"-"+b+"-"+c;
            String nama_asal = tabmode.getValueAt(bar, 1).toString();
            String id_brg_asal = getIdBrg(nama_asal);
            String id_suai = getIdSuai(tgl_asal, id_brg_asal);
            
            String nama = cb_nama.getSelectedItem().toString();
            String id_brg = getIdBrg(nama); 
            String id_sup = getIdSup(lb_supplier.getText());
            String faktur = lb_no_faktur.getText();
            String status="";
            if(rb_status_belum.isSelected()) status="Belum Sesuai";
            else status="Beres";
            System.out.println("Status = "+status);
            System.out.println("Id Suai = "+id_suai);
            String sql = "UPDATE penyesuaian SET "
                       + "id_brg_suai=?, id_sup_suai=?, no_faktur_beli_suai=?, "
                       + "qty_suai=?, ket_suai=?, status_suai=? "
                       + "WHERE id_suai=?";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, id_brg);
                stat.setString(2, id_sup);
                stat.setString(3, faktur);
                stat.setString(4, qty);
                stat.setString(5, keterangan);
                stat.setString(6, status);
                stat.setString(7, id_suai);
            
                stat.executeUpdate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
            }
            tabelPenyesuaian();
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private String getIdSuai(String tgl, String id_brg){
        String id_suai = "";
        String sql = "SELECT id_suai FROM penyesuaian "
                    + "WHERE id_brg_suai = '"+id_brg+"' AND tgl_suai = '"+tgl+"'";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                id_suai = hasil.getString("id_suai");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil id penyesuaian : "+e);
        }
        return id_suai;
    }
    
    private void rb_tampil_yaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_tampil_yaActionPerformed
        tabelPenyesuaian();
    }//GEN-LAST:event_rb_tampil_yaActionPerformed

    private void rb_tampil_tidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_tampil_tidakActionPerformed
        tabelPenyesuaian();
    }//GEN-LAST:event_rb_tampil_tidakActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int bar = tbl_penyesuaian.getSelectedRow();
        String tgl_asal = tabmode.getValueAt(bar, 0).toString();
        String a = tgl_asal.substring(6,10);
        String b = tgl_asal.substring(3,5);
        String c = tgl_asal.substring(0,2);
        tgl_asal = a+"-"+b+"-"+c;
        String nama_asal = tabmode.getValueAt(bar, 1).toString();
        String id_brg_asal = getIdBrg(nama_asal);
        String id_suai = getIdSuai(tgl_asal, id_brg_asal);
        
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "DELETE FROM penyesuaian WHERE id_suai='"+id_suai+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
                tabelPenyesuaian();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    
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
            java.util.logging.Logger.getLogger(penyesuaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(penyesuaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(penyesuaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(penyesuaian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new penyesuaian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane alamatScrollPane;
    private utility.bg2 bg21;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cb_nama;
    private javax.swing.JPanel header_menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JLabel lb_harga_beli;
    private javax.swing.JLabel lb_no_faktur;
    private javax.swing.JLabel lb_qty;
    private javax.swing.JLabel lb_supplier;
    private javax.swing.JButton menu_barang;
    private javax.swing.JButton menu_laporan;
    private javax.swing.JButton menu_pegawai;
    private javax.swing.JButton menu_pembelian;
    private javax.swing.JButton menu_penjualan;
    private javax.swing.JButton menu_penyesuaian;
    private javax.swing.JButton menu_stok;
    private javax.swing.JButton menu_supplier;
    private javax.swing.ButtonGroup rb_grup_status;
    private javax.swing.ButtonGroup rb_grup_tampilkan;
    private javax.swing.JRadioButton rb_status_belum;
    private javax.swing.JRadioButton rb_status_beres;
    private javax.swing.JRadioButton rb_tampil_tidak;
    private javax.swing.JRadioButton rb_tampil_ya;
    private javax.swing.JTextArea ta_ket;
    private javax.swing.JTable tbl_penyesuaian;
    private javax.swing.JTextField tf_qty;
    // End of variables declaration//GEN-END:variables
}
