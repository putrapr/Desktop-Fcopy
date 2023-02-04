package fcopy;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import utility.koneksi_database;

/**
 * @author Putra Prasetya
 */
public class barang extends javax.swing.JFrame {
    private Connection conn = new koneksi_database().connect();
    private DefaultTableModel tabmode;
    private int id_admin;
    private JCheckBox jenis3 = new JCheckBox();
    private JComboBox jenis4 = new JComboBox(getIDCheckbox());
    private JTextField konversi_satuan = new JTextField();
    
    public barang() {
        initComponents();
        myInitComponents();
        setMinimumSize(new Dimension(850,540));
        tabelBarang();
        getJenis();
        getSatuan();
        rb_aktif.setSelected(true);
    }
    
    public barang(Rectangle bound, int id) {
        initComponents();
        myInitComponents();
        setMinimumSize(new Dimension(850,540));
        tabelBarang();
        getJenis();
        getSatuan();
        rb_aktif.setSelected(true);
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
    
    protected void tabelBarang(){
        Object[] Baris = {"ID Barang", "Nama Barang", "Satuan", "Harga Beli", "Harga Jual", "Status"};
        tabmode = new DefaultTableModel(null, Baris);
        tbl_template.setModel(tabmode);
        String sql = "SELECT "
                        + "id_template, "
                        + "nama_template, "
                        + "satuan_template, "
                        + "harga_beli_template, "
                        + "harga_jual_template, "
                        + "status "                        
                    + "FROM template_barang";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
                String a = hasil.getString("id_template");
                String b = hasil.getString("nama_template");
                String c = hasil.getString("satuan_template");
                String d = hasil.getString("harga_beli_template");
                String e = hasil.getString("harga_jual_template");
                String f = hasil.getString("status");                
                                
                String[] data={a,b,c,d,e,f};
                tabmode.addRow(data); 
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan: " + e);
        }
    }
    
    protected void clear(){
        cb_jenis.setSelectedIndex(0);
        lb_id.setText("...");
        tf_nama.setText("");
        cb_satuan.setSelectedIndex(0);
        tf_harga_beli.setText("");
        tf_harga_jual.setText("");
        lb_satu.setText("...");
        lb_harga_jual.setText("...");
        rb_aktif.setSelected(true);
        tf_nama.requestFocus();
    }
    
    protected void getJenis(){
        int length = 0;
        try {     
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT COUNT(DISTINCT(jenis_template)) "
                                                + "FROM template_barang ");
            while (hasil.next()){
                length = hasil.getInt("COUNT(DISTINCT(jenis_template))");
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Jenis Barang : " + e);
        }
        String[] jenis = new String[length];
        int no=0;
        try {  
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT DISTINCT(jenis_template) "
                                                + "FROM template_barang "
                                                + "ORDER BY jenis_template ASC");
            while (hasil.next()){
                jenis[no] = hasil.getString("jenis_template");
                no++;
            }            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Jenis Barang : " + e);
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(jenis);
        cb_jenis.setModel(model);
    }
    
    protected String[] getArrayJenis(){
        String jenis = "";
        int length = 0, no = 0;
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT COUNT(DISTINCT(jenis_template)) "
                                                + "FROM template_barang ");
            while (hasil.next()){
                length = hasil.getInt("COUNT(DISTINCT(jenis_template))");                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal ambil panjang array jenis : "+e);
        }
        String[] arrayJenis = new String[length];
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT DISTINCT(jenis_template) "
                                                + "FROM template_barang "
                                                + "ORDER BY jenis_template ASC");
            while (hasil.next()){
                jenis = hasil.getString("jenis_template");
                arrayJenis[no]= jenis;
                no++;
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Jenis Barang : " + e);
        }
        return arrayJenis;
    }
    
    protected String[] getIDCheckbox(){
        String id = "";
        int no = 0;        
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT COUNT(id_template) FROM template_barang");
            while(hasil.next()){
                no = hasil.getInt("COUNT(id_template)");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Count(Id) : "+e);
        }
        String[] char_id = new String[no];
        String idCh = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT id_template FROM template_barang");
            for(int i =0; i<no;i++){
                hasil.next();
                id = hasil.getString("id_template");
                idCh = id.substring(0,1);
                char_id[i] = idCh;
            }            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Id : "+e);
        }
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                             "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                             "U", "V", "W", "X", "Y", "Z"};
        char_id = Arrays.stream(char_id).distinct().toArray(String[]::new);        
        return subtractArray(alphabet, char_id);
    }
    
    protected void UpdateAllId(String jenis, String IdKarakter){
        String id = "", new_id = "";
        System.out.println("masuk ke method UpdateAllId :"+jenis+", "+IdKarakter);
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT id_template FROM template_barang WHERE jenis_template ='"+jenis+"'");
            while(hasil.next()){
                id = hasil.getString("id_template");
                System.out.println("id = "+id);
                new_id = id.substring(1,6);
                System.out.println("id substring = "+id.substring(1,6));
                System.out.println("new_id 1 = "+new_id);
                System.out.println("IDKarakter = "+IdKarakter);
                new_id = IdKarakter + new_id;
                System.out.println("new_id 2 = "+new_id);
                String sql = "UPDATE template_barang SET id_template = ? WHERE id_template = '"+id+"'";
                PreparedStatement prep = conn.prepareStatement(sql);
                prep.setString(1, new_id);
                prep.executeUpdate();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Count(Id) : "+e);
        }
    }
    
    protected void UbahJenis(String jenis_lama, String jenis_baru){
        try{
            String sql = "UPDATE template_barang SET jenis_template = ? WHERE jenis_template = '"+jenis_lama+"'";
                PreparedStatement prep = conn.prepareStatement(sql);
                prep.setString(1, jenis_baru);
                prep.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal Ubah Jenis : "+e);
        }
    }
    
    protected void getSatuan(){
        int length = 0;
            try {     
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery("SELECT COUNT(DISTINCT(satuan_template)) "
                                                    + "FROM template_barang ");
                while (hasil.next()){
                    length = hasil.getInt("COUNT(DISTINCT(satuan_template))");
                }
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Jumlah Satuan : " + e);
            }
            String[] satuanArray = new String[length];
            int no = 0;
            try {     
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery("SELECT DISTINCT(satuan_template) "
                                                    + "FROM template_barang "
                                                    + "ORDER BY satuan_template ASC");
                while (hasil.next()){
                    satuanArray[no] = hasil.getString("satuan_template");
                    no++;                    
                }            
            }catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Satuan : " + e);
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(satuanArray);
            cb_satuan.setModel(model);
    }
    
    protected String[] subtractArray(String[] a, String[] b){
        int no = 0;
        for (int i = 0; i < b.length;i++){
            no = 0;
            for (int j = 0; j < a.length-i; j++){
                if (a[j].equals(b[i])){
                    continue;
                }
                a[no] = a[j];
                no++;
            }
        }

        String[] result = new String[a.length - b.length];
        for (int i = 0; i < result.length; i++){
            result[i] = a[i];
        }
        return result;
    }

    protected boolean cekNama(String nama){
        boolean z = false;
        nama = tf_nama.getText();
        String sql = "SELECT nama_template FROM template_barang WHERE nama_template = '"+nama+"'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            String a = "";
            while(hasil.next()){
                a = hasil.getString("nama_template");
            }
            if(a.equals(nama)) {                
                z = true;
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal ambil nama : "+e);
        }
        return z;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        btn_simpan = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_template = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_nama = new javax.swing.JTextField();
        cb_jenis = new javax.swing.JComboBox<>();
        lb_id = new javax.swing.JLabel();
        btn_plus_jenis = new javax.swing.JButton();
        btn_change_jenis = new javax.swing.JButton();
        cb_satuan = new javax.swing.JComboBox<>();
        btn_plus_satuan = new javax.swing.JButton();
        btn_change_satuan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tf_harga_beli = new javax.swing.JTextField();
        tf_harga_jual = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rb_aktif = new javax.swing.JRadioButton();
        rb_nonaktif = new javax.swing.JRadioButton();
        btn_hapus = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_bersihkan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lb_satu = new javax.swing.JLabel();
        lb_harga_jual = new javax.swing.JLabel();

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
        menu_barang.setForeground(new java.awt.Color(255, 255, 255));
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

        btn_simpan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        tbl_template.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Satuan", "Harga Beli", "Harga Jual", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_template.getTableHeader().setReorderingAllowed(false);
        tbl_template.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_templateMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_template);
        if (tbl_template.getColumnModel().getColumnCount() > 0) {
            tbl_template.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_template.getColumnModel().getColumn(2).setPreferredWidth(10);
            tbl_template.getColumnModel().getColumn(3).setPreferredWidth(10);
            tbl_template.getColumnModel().getColumn(4).setPreferredWidth(10);
            tbl_template.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Jenis");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("ID Barang");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Satuan");

        tf_nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cb_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_jenisActionPerformed(evt);
            }
        });

        lb_id.setText("...");

        btn_plus_jenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_add_11px.png"))); // NOI18N
        btn_plus_jenis.setMaximumSize(new java.awt.Dimension(43, 30));
        btn_plus_jenis.setMinimumSize(new java.awt.Dimension(43, 20));
        btn_plus_jenis.setPreferredSize(new java.awt.Dimension(43, 30));
        btn_plus_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plus_jenisActionPerformed(evt);
            }
        });

        btn_change_jenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_transfer_11px.png"))); // NOI18N
        btn_change_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_change_jenisActionPerformed(evt);
            }
        });

        cb_satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_satuanActionPerformed(evt);
            }
        });

        btn_plus_satuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_add_11px.png"))); // NOI18N
        btn_plus_satuan.setMaximumSize(new java.awt.Dimension(43, 30));
        btn_plus_satuan.setMinimumSize(new java.awt.Dimension(43, 20));
        btn_plus_satuan.setPreferredSize(new java.awt.Dimension(43, 30));
        btn_plus_satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plus_satuanActionPerformed(evt);
            }
        });

        btn_change_satuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_transfer_11px.png"))); // NOI18N
        btn_change_satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_change_satuanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Harga Beli");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Harga Jual");

        tf_harga_beli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_harga_beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_harga_beliKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_harga_beliKeyTyped(evt);
            }
        });

        tf_harga_jual.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_harga_jual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_harga_jualKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_harga_jualKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Status");

        buttonGroup1.add(rb_aktif);
        rb_aktif.setText("Aktif");

        buttonGroup1.add(rb_nonaktif);
        rb_nonaktif.setText("Nonaktif");

        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_ubah.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_bersihkan.setText("Bersihkan");
        btn_bersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bersihkanActionPerformed(evt);
            }
        });

        jLabel8.setText("X");

        jLabel10.setText("=");

        lb_satu.setText("...");

        lb_harga_jual.setText("...");

        javax.swing.GroupLayout bg21Layout = new javax.swing.GroupLayout(bg21);
        bg21.setLayout(bg21Layout);
        bg21Layout.setHorizontalGroup(
            bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bg21Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bg21Layout.createSequentialGroup()
                                        .addComponent(cb_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_plus_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_change_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(bg21Layout.createSequentialGroup()
                                        .addComponent(cb_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_plus_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_change_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tf_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lb_id, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_harga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bg21Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tf_harga_jual, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8)
                                .addGap(8, 8, 8)
                                .addComponent(lb_satu, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_harga_jual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(bg21Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb_aktif)
                                .addGap(18, 18, 18)
                                .addComponent(rb_nonaktif))
                            .addGroup(bg21Layout.createSequentialGroup()
                                .addComponent(btn_simpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(btn_bersihkan)))))
                .addGap(18, 18, 18))
        );
        bg21Layout.setVerticalGroup(
            bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg21Layout.createSequentialGroup()
                .addComponent(header_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bg21Layout.createSequentialGroup()
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(cb_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tf_harga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(btn_plus_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lb_id)
                            .addComponent(jLabel7)
                            .addComponent(tf_harga_jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(lb_satu)
                            .addComponent(lb_harga_jual)))
                    .addComponent(btn_change_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(rb_aktif)
                    .addComponent(rb_nonaktif))
                .addGap(8, 8, 8)
                .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addComponent(btn_plus_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_change_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bg21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_simpan)
                        .addComponent(btn_ubah)
                        .addComponent(btn_hapus)
                        .addComponent(btn_bersihkan)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
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

    public void myInitComponents(){
        jenis3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis3ActionPerformed(evt);
            }
        });
    }
    private void menu_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_laporanActionPerformed
        Rectangle barangBound = this.getBounds();
        new laporan(barangBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_laporanActionPerformed

    private void menu_penyesuaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_penyesuaianActionPerformed
        Rectangle barangBound = this.getBounds();
        new penyesuaian(barangBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_penyesuaianActionPerformed

    private void menu_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_penjualanActionPerformed
        Rectangle barangBound = this.getBounds();
        new penjualan(barangBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_penjualanActionPerformed

    private void menu_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_barangActionPerformed

    }//GEN-LAST:event_menu_barangActionPerformed

    private void menu_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_pembelianActionPerformed
        Rectangle barangBound = this.getBounds();
        new pembelian(barangBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_pembelianActionPerformed

    private void menu_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_supplierActionPerformed
        Rectangle barangBound = this.getBounds();
        new supplier(barangBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_supplierActionPerformed

    private void menu_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_pegawaiActionPerformed
        Rectangle barangBound = this.getBounds();
        new pegawai(barangBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_pegawaiActionPerformed

    private void menu_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_stokActionPerformed
        Rectangle barangBound = this.getBounds();
        new stok(barangBound, id_admin).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menu_stokActionPerformed

    private void cb_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_jenisActionPerformed
        String jenis = cb_jenis.getSelectedItem().toString(); 
        try {
            String id = "";
            int no = 0;
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT id_template FROM template_barang WHERE jenis_template = '"+jenis+"'");
            while(hasil.next()){
                id = hasil.getString("id_template");
            }      
            char idCh = id.charAt(0); 
            no = Integer.parseInt(id.substring(1,6));            
            no++;            
            id = idCh + String.format("%05d",no);
            lb_id.setText(id);
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Id Barang : " + e);
        }
    }//GEN-LAST:event_cb_jenisActionPerformed

    private void cb_satuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_satuanActionPerformed
        String satuan = cb_satuan.getSelectedItem().toString();
        try {
            int nilai = 0;
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT konversi_satuan FROM template_barang WHERE satuan_template = '"+satuan+"'");
            while(hasil.next()){
                nilai = hasil.getInt("konversi_satuan");
            }
            if (nilai == 0){
                nilai = Integer.valueOf(konversi_satuan.getText());
            }
            lb_satu.setText(""+nilai);
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Xsatu Barang : " + e);
        }
        
    }//GEN-LAST:event_cb_satuanActionPerformed

    private void tf_harga_beliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_harga_beliKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_harga_beliKeyTyped

    private void tf_harga_beliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_harga_beliKeyReleased
        String str_beli = tf_harga_beli.getText();
        int harga_beli = 0;
        if(str_beli.equals("")){}
        else{
            harga_beli = Integer.valueOf(str_beli);
        }
        int satu = Integer.valueOf(lb_satu.getText());
        int harga_jual = harga_beli / satu;             
        tf_harga_jual.setText(""+harga_jual);
        lb_harga_jual.setText(""+harga_beli);
    }//GEN-LAST:event_tf_harga_beliKeyReleased

    private void tf_harga_jualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_harga_jualKeyReleased
        String str_jual = tf_harga_jual.getText();
        int harga_jual = 0;
        if(str_jual.equals("")){}
        else {
            harga_jual = Integer.valueOf(str_jual);
        }
        int satu = Integer.valueOf(lb_satu.getText());
        int total = harga_jual * satu;
        lb_harga_jual.setText(""+total);
    }//GEN-LAST:event_tf_harga_jualKeyReleased

    private void btn_plus_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plus_jenisActionPerformed
        JTextField jenis1 = new JTextField();
        JComboBox jenis2 = new JComboBox(getIDCheckbox());
        Object[] fields = {
            "Nama Jenis :",jenis1,
            "1 Huruf Untuk ID :",jenis2
        };
        
        int ok = JOptionPane.showConfirmDialog(null, fields, "Tambahkan Jenis",
                 JOptionPane.OK_CANCEL_OPTION);
        if (ok==0){
            String str_jenis2 = jenis2.getSelectedItem().toString();
            str_jenis2 += "00000";
            String str_jenis1 = jenis1.getText();
            String sql = "INSERT INTO template_barang VALUES (?,?,?,?,?,?,?,?)";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, str_jenis2);
                stat.setString(2, str_jenis1);
                stat.setString(3, "");
                stat.setString(4, "pcs");
                stat.setInt(5, 1);
                stat.setInt(6, 0);
                stat.setInt(7, 0);
                stat.setString(8, "Nonaktif");
                stat.executeUpdate();
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Gagal insert id : "+e);
            }
            cb_jenis.addItem(str_jenis1);
        }
        
    }//GEN-LAST:event_btn_plus_jenisActionPerformed

    private void btn_change_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_change_jenisActionPerformed
        jenis3.setSelected(true);
        String[] jenis = getArrayJenis();
        JComboBox jenis1 = new JComboBox(jenis);        
        JTextField jenis2 = new JTextField();
        Object[] fields = {
            "Jenis :",jenis1,
            "Jenis baru :",jenis2,
            "Ubah ID Karakter ?", jenis3,
            "ID karakter baru :", jenis4
        };
        
        int ok = JOptionPane.showConfirmDialog(null, fields, "Ubah Jenis",JOptionPane.OK_CANCEL_OPTION);
        System.out.println("Keluar JOptionPane");
        if (ok==0){            
            String str_jenis1 = jenis1.getSelectedItem().toString();
            String str_jenis2 = jenis2.getText();
            String str_jenis4 = jenis4.getSelectedItem().toString();
            System.out.println("Masuk ke kondisi 'ok'");
            if(str_jenis1.equals("")){
                JOptionPane.showMessageDialog(null, "Jenis kosong, ubah jenis dibatalkan");
            }else if(jenis3.isSelected()){
                UpdateAllId(str_jenis1,str_jenis4);
                UbahJenis(str_jenis1, str_jenis2);
            }else if(!jenis3.isSelected()){
                UbahJenis(str_jenis1, str_jenis2);
            }
            System.out.println("jenis3 : "+jenis3.isSelected());
            getJenis();
        }
    }//GEN-LAST:event_btn_change_jenisActionPerformed

    private void jenis3ActionPerformed(java.awt.event.ActionEvent evt){
        if (jenis3.isSelected()){
            jenis4.setEnabled(true);
        } else {
            jenis4.setEnabled(false);
        }
    }
    
    private void btn_plus_satuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plus_satuanActionPerformed
        JTextField satuan = new JTextField();
        Object[] fields = {
            "Masukkan Satuan :",satuan,
            "Masukkan Nilai Konversi Satuan:",konversi_satuan
        };
        
        int ok = JOptionPane.showConfirmDialog(null, fields, "Tambahkan Satuan",JOptionPane.OK_CANCEL_OPTION);
        String str_satuan = satuan.getText();
        if (ok==0){
            int length = 0;
            try {     
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery("SELECT COUNT(DISTINCT(satuan_template)) "
                                                    + "FROM template_barang ");
                while (hasil.next()){
                    length = hasil.getInt("COUNT(DISTINCT(satuan_template))");
                }
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Jumlah Satuan : " + e);
            }
            String[] satuanArray = new String[length + 1];
            satuanArray[0] = str_satuan;
            String row = "";
            int no = 1;
            try {     
                Statement stat = conn.createStatement();
                ResultSet hasil = stat.executeQuery("SELECT DISTINCT(satuan_template) "
                                                    + "FROM template_barang "
                                                    + "ORDER BY satuan_template ASC");
                while (hasil.next()){
                    row = hasil.getString("satuan_template");
                    satuanArray[no] = row;
                    no++;
                    if (str_satuan.equals(row)){
                        JOptionPane.showMessageDialog(null, "Satuan sudah ada");
                        return;
                    }
                }            
            }catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Satuan : " + e);
            }
            lb_satu.setText(konversi_satuan.getText());
            DefaultComboBoxModel model = new DefaultComboBoxModel(satuanArray);
            cb_satuan.setModel(model);
        }
    }//GEN-LAST:event_btn_plus_satuanActionPerformed

    private void btn_change_satuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_change_satuanActionPerformed
        int size = cb_satuan.getItemCount();
        String[] satuanArray = new String[size];
        for (int i = 0; i < size; i++){
            satuanArray[i] = cb_satuan.getItemAt(i);
        }
        
        JComboBox satuan1 = new JComboBox(satuanArray);        
        JTextField satuan2 = new JTextField();        
        Object[] fields = {
            "Satuan :",satuan1,
            "Satuan baru :",satuan2,
            "Nilai Konversi Satuan", konversi_satuan
        };        
        
        int ok = JOptionPane.showConfirmDialog(null, fields, "Ubah Satuan",JOptionPane.OK_CANCEL_OPTION);
        String str_satuan1 = satuan1.getSelectedItem().toString();
        String str_satuan2 = satuan2.getText();
        String satuan = "";
        try {
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT satuan_template "
                                                + "FROM template_barang "
                                                + "WHERE satuan_template = '"+ str_satuan2 +"'");
            while (hasil.next()){
                satuan = hasil.getString("satuan_template");
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Mendapatkan Satuan : " + e);
        }
        
        if (ok==0){    
            if(str_satuan2.equals("")){
                JOptionPane.showMessageDialog(null, "Satuan baru kosong, ubah satuan dibatalkan");
                return;
            } else if (satuan.equals("")){ // satuan beda
                cb_satuan.removeItem(str_satuan1);
                cb_satuan.addItem(str_satuan2);
                JOptionPane.showMessageDialog(null, "Satuan Berhasil Diubah !");
            } else { // satuan sama
                try{
                String sql = "UPDATE template_barang SET satuan_template = ? WHERE satuan_template = '"+str_satuan1+"'";
                PreparedStatement prep = conn.prepareStatement(sql);
                
                prep.setString(1, str_satuan2);
                prep.executeUpdate();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Gagal Ubah Jenis : "+e);
                }
                getSatuan();
                tabelBarang();
            }
        }
    }//GEN-LAST:event_btn_change_satuanActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        String nama = tf_nama.getText();
        if (lb_id.equals("...")){
            JOptionPane.showMessageDialog(null, "ID Barang Kosong !. Silahkan pilih jenis terlebih dahulu");
        } else if(cekNama(nama)) {
            JOptionPane.showMessageDialog(null, "Nama Sudah Ada!. Silahkan tulis nama barang lain");
        } else {
            String sql = "INSERT INTO template_barang VALUES (?,?,?,?,?,?,?,?)";
            String jenis = cb_jenis.getSelectedItem().toString();
            String satuan = cb_satuan.getSelectedItem().toString();
        
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, lb_id.getText());
                stat.setString(2, jenis);
                stat.setString(3, tf_nama.getText());
                stat.setString(4, satuan);
                stat.setString(5, lb_satu.getText());
                stat.setString(6, tf_harga_beli.getText());
                stat.setString(7, tf_harga_jual.getText());
            
                String status="";
                if(rb_aktif.isSelected()) status="Aktif";
                else status="Nonaktif";
                stat.setString(8, status);
            
                stat.executeUpdate();
                clear();            
                tabelBarang();
            
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data tidak bisa dimasukkan"+e);
            }   
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        String id = "";
        try{
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery("SELECT id_template "
                                                + "FROM template_barang "
                                                + "WHERE id_template = '"+ lb_id.getText() +"'");
            while (hasil.next()){
                id = hasil.getString("id_template");
            }
            System.out.println("id : "+ id);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan id: "+e);
        }
        if(id.equals("")){
            String sql = "INSERT INTO template_barang VALUES (?,?,?,?,?,?,?,?)";
            String jenis = cb_jenis.getSelectedItem().toString();
            String satuan = cb_satuan.getSelectedItem().toString();
        
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, lb_id.getText());
                stat.setString(2, jenis);
                stat.setString(3, tf_nama.getText());
                stat.setString(4, satuan);
                stat.setString(5, lb_satu.getText());
                stat.setString(6, tf_harga_beli.getText());
                stat.setString(7, tf_harga_jual.getText());
            
                String status="";
                if(rb_aktif.isSelected()) status="Aktif";
                else status="Nonaktif";
                stat.setString(8, status);
            
                stat.executeUpdate();
                clear();            
                tabelBarang();
            
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data tidak bisa dimasukkan"+e);
            }
        }else{
            String jenis = cb_jenis.getSelectedItem().toString();
            String satuan = cb_satuan.getSelectedItem().toString();
            
            try {
                String sql = "UPDATE template_barang SET id_template=?, jenis_template=?, nama_template=?, satuan_template=?, konversi_satuan=?, harga_beli_template=?, harga_jual_template=?, status=? WHERE id_template=?";
                PreparedStatement stat = conn.prepareStatement(sql);
                System.out.println("id : "+lb_id.getText()+"\njenis : "+jenis);
                stat.setString(1, lb_id.getText());
                stat.setString(2, jenis);
                stat.setString(3, tf_nama.getText());
                stat.setString(4, satuan);
                stat.setString(5, lb_satu.getText());
                stat.setString(6, tf_harga_beli.getText());
                stat.setString(7, lb_harga_jual.getText());
                String status="";
                if(rb_aktif.isSelected()) status="Aktif";
                else status="Nonaktif";
                stat.setString(8, status);
                stat.setString(9, lb_id.getText());
            
                stat.executeUpdate();
                clear();            
                tabelBarang();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
            }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        String id = lb_id.getText();
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "DELETE FROM template_barang WHERE id_template='"+id+"'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                clear();                
                tabelBarang();
                
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_bersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bersihkanActionPerformed
        clear();
    }//GEN-LAST:event_btn_bersihkanActionPerformed

    private void tbl_templateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_templateMouseClicked
        int bar = tbl_template.getSelectedRow();
        String id = tabmode.getValueAt(bar, 0).toString();
        
        String sql = "SELECT * FROM template_barang "
                     + "WHERE id_template = '"+id+"'";
        
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);            
            String a = "", b = "", c = "", d = "", e = "", f = "", g = "", h = "";
            
            while(hasil.next()){
                a = hasil.getString("id_template");
                b = hasil.getString("jenis_template");
                c = hasil.getString("nama_template");
                d = hasil.getString("satuan_template");
                e = hasil.getString("konversi_satuan");
                f = hasil.getString("harga_beli_template");
                g = hasil.getString("harga_jual_template");
                h = hasil.getString("status");
            }
                        
            cb_jenis.setSelectedItem(b);
            lb_id.setText(a);
            tf_nama.setText(c);
            cb_satuan.setSelectedItem(d);
            lb_satu.setText(e);
            tf_harga_beli.setText(f);
            lb_harga_jual.setText(g);
            switch (h) {
                case "Aktif":
                    rb_aktif.setSelected(true);
                    rb_nonaktif.setSelected(false);
                    break;
                case "Nonaktif":
                    rb_aktif.setSelected(false);
                    rb_nonaktif.setSelected(true);
                    break;
                default:
                    rb_aktif.setSelected(false);
                    rb_nonaktif.setSelected(false);
                    break;
            }
            
            int harga_total = Integer.valueOf(lb_harga_jual.getText());
            int konv = Integer.valueOf(lb_satu.getText());
            int harga_jual = harga_total/konv;
            tf_harga_jual.setText(""+harga_jual);
        }catch(SQLException ee){
            JOptionPane.showMessageDialog(null, "Data gagal diambil"+ee);
        }
    }//GEN-LAST:event_tbl_templateMouseClicked

    private void tf_harga_jualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_harga_jualKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_tf_harga_jualKeyTyped

    
    
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
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utility.bg2 bg21;
    private javax.swing.JButton btn_bersihkan;
    private javax.swing.JButton btn_change_jenis;
    private javax.swing.JButton btn_change_satuan;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_plus_jenis;
    private javax.swing.JButton btn_plus_satuan;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_ubah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_jenis;
    private javax.swing.JComboBox<String> cb_satuan;
    private javax.swing.JPanel header_menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lb_harga_jual;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_satu;
    private javax.swing.JButton menu_barang;
    private javax.swing.JButton menu_laporan;
    private javax.swing.JButton menu_pegawai;
    private javax.swing.JButton menu_pembelian;
    private javax.swing.JButton menu_penjualan;
    private javax.swing.JButton menu_penyesuaian;
    private javax.swing.JButton menu_stok;
    private javax.swing.JButton menu_supplier;
    private javax.swing.JRadioButton rb_aktif;
    private javax.swing.JRadioButton rb_nonaktif;
    private javax.swing.JTable tbl_template;
    private javax.swing.JTextField tf_harga_beli;
    private javax.swing.JTextField tf_harga_jual;
    private javax.swing.JTextField tf_nama;
    // End of variables declaration//GEN-END:variables
}
