-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 02, 2022 at 12:21 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fcopy`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(10) NOT NULL,
  `nama_admin` varchar(30) NOT NULL,
  `pass_admin` varchar(20) NOT NULL,
  `kode_penjualan` varchar(10) NOT NULL,
  `singkatan_admin` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `pass_admin`, `kode_penjualan`, `singkatan_admin`) VALUES
(1, 'admin', '123', 'PA', 'ADM'),
(2, 'mimin', '321', 'PA', 'MMN');

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_brg` varchar(10) NOT NULL,
  `nama_brg` varchar(100) NOT NULL,
  `satuan_brg` varchar(20) NOT NULL,
  `qty_brg` int(10) NOT NULL,
  `harga_beli_brg` int(10) NOT NULL,
  `harga_jual_brg` int(10) NOT NULL,
  `supplier_brg` int(10) NOT NULL,
  `tgl_brg` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_brg`, `nama_brg`, `satuan_brg`, `qty_brg`, `harga_beli_brg`, `harga_jual_brg`, `supplier_brg`, `tgl_brg`) VALUES
('B00001', 'Penggaris Matrix 30cm', 'pack (10)', 2, 30000, 40000, 1, '2022-05-18'),
('K00001', 'Kertas Double Folio Bergaris Sidu', 'rim', 2, 18000, 23000, 2, '2022-05-18'),
('Y00001', 'Pensil Faber Castell 2B', 'pcs', 43, 3750, 5000, 1, '2022-06-19'),
('Y00002', 'Pensil Joyko hitam 2B', 'pcs', 30, 1750, 2500, 1, '2022-06-19');

-- --------------------------------------------------------

--
-- Table structure for table `jasa`
--

CREATE TABLE `jasa` (
  `id_jasa` int(10) NOT NULL,
  `nama_jasa` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jasa`
--

INSERT INTO `jasa` (`id_jasa`, `nama_jasa`) VALUES
(1, 'Fotocopy'),
(2, 'Laminating'),
(3, 'Jilid'),
(4, 'Print'),
(5, 'Scan'),
(7, 'Edit Foto');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `id_peg` int(10) NOT NULL,
  `nama_peg` varchar(50) NOT NULL,
  `tmpt_lahir_peg` varchar(20) NOT NULL,
  `tgl_lahir_peg` date NOT NULL,
  `jns_kel_peg` enum('Pria','Wanita') NOT NULL,
  `alamat_peg` text NOT NULL,
  `telp_peg` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id_peg`, `nama_peg`, `tmpt_lahir_peg`, `tgl_lahir_peg`, `jns_kel_peg`, `alamat_peg`, `telp_peg`) VALUES
(1, 'Putra Prasetya', 'Wonogiri', '2000-01-28', 'Pria', 'Jl. Bangka Raya RT 11 RW 03 Pela Mampang, Mampang Prapatan, Jakarta Selatan. 12720.', '089661371635'),
(5, 'aza', 'b', '2022-06-01', 'Wanita', 'c', 'de');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `no_faktur_beli` varchar(20) NOT NULL,
  `id_sup_beli` int(10) NOT NULL,
  `ppn` int(10) NOT NULL,
  `total_beli` int(10) NOT NULL,
  `tgl_beli` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`no_faktur_beli`, `id_sup_beli`, `ppn`, `total_beli`, `tgl_beli`) VALUES
('A019-06-22', 6, 60000, 183800, '2022-06-01'),
('AP012-19-22', 1, 0, 154800, '2022-05-02'),
('P010-10-20', 1, 30000, 273000, '2022-05-10'),
('Q11-09-19', 1, 20000, 194000, '2022-05-30'),
('S009-02-22', 6, 50000, 485000, '2022-04-10'),
('S01-11-21', 6, 50000, 203000, '2022-06-30'),
('S09-10-21', 2, 50000, 142400, '2022-06-18'),
('SA001-11-20', 1, 15000, 300000, '2022-06-18'),
('W013-01-21', 1, 40000, 301000, '2022-06-18');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian_detail`
--

CREATE TABLE `pembelian_detail` (
  `no_faktur_beli_detail` varchar(20) NOT NULL,
  `id_brg_beli_detail` varchar(10) NOT NULL,
  `qty_beli` int(10) NOT NULL,
  `jumlah_beli` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pembelian_detail`
--

INSERT INTO `pembelian_detail` (`no_faktur_beli_detail`, `id_brg_beli_detail`, `qty_beli`, `jumlah_beli`) VALUES
('A019-06-22', 'P00002', 4, 70800),
('A019-06-22', 'R00001', 2, 53000),
('AP012-19-22', 'B00001', 2, 60000),
('AP012-19-22', 'C00001', 3, 70500),
('P010-10-20', 'Y00001', 4, 180000),
('P010-10-20', 'Y00002', 3, 63000),
('Q11-09-19', 'Y00001', 2, 90000),
('Q11-09-19', 'Y00002', 4, 84000),
('S009-02-22', 'Y00001', 5, 225000),
('S009-02-22', 'Y00002', 10, 210000),
('S01-11-21', 'Y00001', 2, 90000),
('S01-11-21', 'Y00002', 3, 63000),
('S09-10-21', 'K00001', 4, 72000),
('S09-10-21', 'K00002', 2, 20400),
('SA001-11-20', 'B00001', 3, 90000),
('SA001-11-20', 'Y00001', 2, 90000),
('SA001-11-20', 'Y00002', 5, 105000),
('W013-01-21', 'Y00001', 3, 135000),
('W013-01-21', 'Y00002', 6, 126000);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `no_faktur_jual` varchar(20) NOT NULL,
  `total_jual` int(10) NOT NULL,
  `tgl_jual` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`no_faktur_jual`, `total_jual`, `tgl_jual`) VALUES
('PA0001/06/22-ADM', 10000, '2022-06-23'),
('PA0002/06/22-ADM', 24500, '2022-06-25'),
('PA0003/06/22-ADM', 22000, '2022-06-25'),
('PA0004/06/22-ADM', 59500, '2022-06-25'),
('PA0005/06/22-ADM', 2000, '2022-06-25'),
('PA0006/06/22-ADM', 25000, '2022-06-25');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan_detail`
--

CREATE TABLE `penjualan_detail` (
  `no_faktur_jual_detail` varchar(20) NOT NULL,
  `id_brg_jual_detail` varchar(10) NOT NULL,
  `jasa` varchar(20) NOT NULL,
  `qty_jual` int(10) NOT NULL,
  `jumlah_jual` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan_detail`
--

INSERT INTO `penjualan_detail` (`no_faktur_jual_detail`, `id_brg_jual_detail`, `jasa`, `qty_jual`, `jumlah_jual`) VALUES
('PA0001/06/22-ADM', 'Y00001', '-', 1, 5000),
('PA0001/06/22-ADM', 'Y00002', '-', 2, 5000),
('PA0002/06/22-ADM', 'Y00001', '-', 2, 10000),
('PA0002/06/22-ADM', 'Y00002', '-', 5, 12500),
('PA0003/06/22-ADM', 'Y00001', '-', 3, 15000),
('PA0003/06/22-ADM', 'Y00002', '-', 2, 5000),
('PA0004/06/22-ADM', 'B00001', '-', 1, 40000),
('PA0004/06/22-ADM', 'K00001', 'Fotocopy', 1, 2000),
('PA0004/06/22-ADM', 'Y00001', '-', 2, 10000),
('PA0004/06/22-ADM', 'Y00002', '-', 3, 7500),
('PA0005/06/22-ADM', 'K00001', 'Fotocopy', 1, 2000),
('PA0006/06/22-ADM', 'K00001', '-', 1, 23000),
('PA0006/06/22-ADM', 'K00001', 'Fotocopy', 1, 2000);

-- --------------------------------------------------------

--
-- Table structure for table `penyesuaian`
--

CREATE TABLE `penyesuaian` (
  `id_suai` int(10) NOT NULL,
  `id_brg_suai` varchar(10) NOT NULL,
  `id_sup_suai` int(10) NOT NULL,
  `no_faktur_beli_suai` varchar(20) NOT NULL,
  `qty_suai` int(10) NOT NULL,
  `ket_suai` text NOT NULL,
  `tgl_suai` date NOT NULL,
  `status_suai` enum('Beres','Belum Sesuai') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penyesuaian`
--

INSERT INTO `penyesuaian` (`id_suai`, `id_brg_suai`, `id_sup_suai`, `no_faktur_beli_suai`, `qty_suai`, `ket_suai`, `tgl_suai`, `status_suai`) VALUES
(1, 'B00001', 1, 'S09-10-21', 2, 'Cacat dari toko', '2022-06-26', 'Beres'),
(2, 'Y00002', 1, 'W013-01-21', 2, 'Tidak ada batang hitam', '2022-06-25', 'Belum Sesuai'),
(3, 'K00001', 2, 'S09-10-21', 1, 'Dimakan tikus', '2022-06-27', 'Belum Sesuai');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_sup` int(10) NOT NULL,
  `nama_sup` varchar(50) NOT NULL,
  `alamat_sup` text NOT NULL,
  `telp_sup` varchar(20) NOT NULL,
  `ket_sup` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_sup`, `nama_sup`, `alamat_sup`, `telp_sup`, `ket_sup`) VALUES
(1, 'Bina Mandiri Stationery', 'Jl. Pangeran Jayakarta Komplek Ruko 141 Blok A No. 17, RT.8/RW.10, Mangga Dua Sel. Kec Sawah Besar, Kota Jakarta Pusat.', '(021) 62320815', 'Peralatan Kantor'),
(2, 'Jual Kertas', 'Jl.Galur Sari Timur No.5, RT.11/RW.5, Utan Kayu Sel., Kec. Matraman, Kota Jakarta Timur.', '089661371635', 'Supplier Kertas'),
(4, 'az', 'a', 'b', 'b'),
(6, 'aza', 'a', 'b', 'b'),
(7, 'a', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `template_barang`
--

CREATE TABLE `template_barang` (
  `id_template` varchar(10) NOT NULL,
  `jenis_template` varchar(20) NOT NULL,
  `nama_template` varchar(100) NOT NULL,
  `satuan_template` varchar(20) NOT NULL,
  `konversi_satuan` int(10) NOT NULL,
  `harga_beli_template` int(10) NOT NULL,
  `harga_jual_template` int(10) NOT NULL,
  `status` enum('Aktif','Nonaktif') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `template_barang`
--

INSERT INTO `template_barang` (`id_template`, `jenis_template`, `nama_template`, `satuan_template`, `konversi_satuan`, `harga_beli_template`, `harga_jual_template`, `status`) VALUES
('B00001', 'Besi', 'Penggaris Matrix 30cm', 'pack (10)', 10, 30000, 40000, 'Nonaktif'),
('C00001', 'Cairan', 'Tip X Joyko Warna Warni', 'pack (6)', 6, 23500, 30000, 'Aktif'),
('K00001', 'Kertas', 'Kertas Double Folio Bergaris Sidu', 'rim', 50, 18000, 25000, 'Aktif'),
('K00002', 'Kertas', 'Amplop Putih B_gain', 'box (100)', 100, 10200, 20000, 'Nonaktif'),
('P00001', 'Plastik', 'Joyko bolpoint BRIZ bp-250', 'lusin', 12, 6000, 12000, 'Aktif'),
('P00002', 'Plastik', 'Pulpen Standard AE7 (Hitam)', 'lusin', 12, 17700, 30000, 'Aktif'),
('P00003', 'Plastik', 'Penggaris Nariko 30cm transparan', 'lusin', 12, 13000, 24000, 'Aktif'),
('R00001', 'Karet', 'Penghapus Joyko 526 B40 (Putih)', 'pack (40)', 40, 26500, 60000, 'Aktif'),
('R00002', 'Karet', 'Penghapus Faber Castel Dust Free Hitam', 'pack (40)', 40, 63000, 10000, 'Aktif'),
('Y00001', 'Kayu', 'Pensil Faber Castell 2B', 'lusin', 12, 45000, 60000, 'Aktif'),
('Y00002', 'Kayu', 'Pensil Joyko hitam 2B', 'lusin', 12, 21000, 30000, 'Aktif');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_brg`),
  ADD KEY `fk_barang_supplier` (`supplier_brg`);

--
-- Indexes for table `jasa`
--
ALTER TABLE `jasa`
  ADD PRIMARY KEY (`id_jasa`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_peg`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`no_faktur_beli`),
  ADD KEY `fk_pembelian_supplier` (`id_sup_beli`);

--
-- Indexes for table `pembelian_detail`
--
ALTER TABLE `pembelian_detail`
  ADD PRIMARY KEY (`no_faktur_beli_detail`,`id_brg_beli_detail`),
  ADD KEY `fk_pembelian_detail_barang` (`id_brg_beli_detail`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`no_faktur_jual`);

--
-- Indexes for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
  ADD PRIMARY KEY (`no_faktur_jual_detail`,`id_brg_jual_detail`,`jasa`) USING BTREE,
  ADD KEY `fk_penjualan_detail_barang` (`id_brg_jual_detail`);

--
-- Indexes for table `penyesuaian`
--
ALTER TABLE `penyesuaian`
  ADD PRIMARY KEY (`id_suai`) USING BTREE,
  ADD KEY `fk_penyesuaian_supplier` (`id_sup_suai`),
  ADD KEY `fk_penyesuaian_pembelian` (`no_faktur_beli_suai`),
  ADD KEY `fk_penyesuaian_barang` (`id_brg_suai`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_sup`);

--
-- Indexes for table `template_barang`
--
ALTER TABLE `template_barang`
  ADD PRIMARY KEY (`id_template`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `jasa`
--
ALTER TABLE `jasa`
  MODIFY `id_jasa` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `id_peg` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `penyesuaian`
--
ALTER TABLE `penyesuaian`
  MODIFY `id_suai` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id_sup` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `fk_barang_supplier` FOREIGN KEY (`supplier_brg`) REFERENCES `supplier` (`id_sup`);

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `fk_pembelian_supplier` FOREIGN KEY (`id_sup_beli`) REFERENCES `supplier` (`id_sup`);

--
-- Constraints for table `pembelian_detail`
--
ALTER TABLE `pembelian_detail`
  ADD CONSTRAINT `fk_pembelian_detail_barang` FOREIGN KEY (`id_brg_beli_detail`) REFERENCES `template_barang` (`id_template`),
  ADD CONSTRAINT `fk_pembelian_detail_pembelian` FOREIGN KEY (`no_faktur_beli_detail`) REFERENCES `pembelian` (`no_faktur_beli`);

--
-- Constraints for table `penjualan_detail`
--
ALTER TABLE `penjualan_detail`
  ADD CONSTRAINT `fk_penjualan_detail_barang` FOREIGN KEY (`id_brg_jual_detail`) REFERENCES `barang` (`id_brg`),
  ADD CONSTRAINT `fk_penjualan_detail_penjualan` FOREIGN KEY (`no_faktur_jual_detail`) REFERENCES `penjualan` (`no_faktur_jual`);

--
-- Constraints for table `penyesuaian`
--
ALTER TABLE `penyesuaian`
  ADD CONSTRAINT `fk_penyesuaian_barang` FOREIGN KEY (`id_brg_suai`) REFERENCES `barang` (`id_brg`),
  ADD CONSTRAINT `fk_penyesuaian_pembelian` FOREIGN KEY (`no_faktur_beli_suai`) REFERENCES `pembelian` (`no_faktur_beli`),
  ADD CONSTRAINT `fk_penyesuaian_supplier` FOREIGN KEY (`id_sup_suai`) REFERENCES `supplier` (`id_sup`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
