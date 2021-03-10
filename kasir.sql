-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: database:3306
-- Generation Time: Mar 10, 2021 at 06:16 AM
-- Server version: 5.7.32
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasir`
--

-- --------------------------------------------------------

--
-- Table structure for table `level`
--

CREATE TABLE `level` (
  `id_level` int(11) NOT NULL,
  `nama_level` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `level`
--

INSERT INTO `level` (`id_level`, `nama_level`) VALUES
(1, 'administrator'),
(2, 'kasir'),
(3, 'owner'),
(4, 'pelanggan');

-- --------------------------------------------------------

--
-- Table structure for table `masakan`
--

CREATE TABLE `masakan` (
  `id_masakan` varchar(11) NOT NULL,
  `nama_masakan` varchar(64) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `status_masakan` enum('Tersedia','Habis') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `masakan`
--

INSERT INTO `masakan` (`id_masakan`, `nama_masakan`, `harga`, `stok`, `status_masakan`) VALUES
('A001', 'Chicken Steak', 20000, 68, 'Tersedia'),
('A002', 'Ayam Lada Hitam', 40000, 5, 'Tersedia'),
('A003', 'Gurame Asam Manis', 30000, 18, 'Tersedia'),
('A004', 'Ayam Goreng Serundeng', 20000, 5, 'Tersedia'),
('A005', 'Oseng Cumi Asin Pedas', 35000, 25, 'Tersedia'),
('A006', 'Gurame Bakar', 25000, 8, 'Tersedia'),
('A007', 'Udang Pedas Gurih', 10000, 0, 'Habis'),
('A008', 'Something', 20000, 0, 'Habis');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id_order` varchar(11) NOT NULL,
  `id_transaksi` varchar(11) NOT NULL,
  `id_user` varchar(11) NOT NULL,
  `no_meja` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `id_masakan` varchar(11) NOT NULL,
  `jumlah_masakan` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `keterangan` text NOT NULL,
  `status_order` enum('Lunas','Belum Dibayar') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id_order`, `id_transaksi`, `id_user`, `no_meja`, `tanggal`, `id_masakan`, `jumlah_masakan`, `total_harga`, `keterangan`, `status_order`) VALUES
('OR001', 'T001', 'U002', 2, '2021-03-16', 'A001', 4, 80000, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ut metus ut elit ornare fermentum.', 'Belum Dibayar'),
('OR002', 'T001', 'U002', 2, '2021-03-16', 'A002', 3, 120000, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ut metus ut elit ornare fermentum.', 'Belum Dibayar'),
('OR003', 'T002', 'U002', 3, '2021-03-17', 'A004', 4, 80000, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ut metus ut elit ornare fermentum.', 'Lunas'),
('OR159', 'T240', 'U002', 2, '2021-03-10', 'A002', 3, 120000, 'TODO', 'Lunas'),
('OR180', 'T240', 'U002', 2, '2021-03-10', 'A004', 1, 20000, 'TODO', 'Lunas'),
('OR429', 'T302', 'U002', 3, '2021-03-09', 'A006', 2, 50000, 'TODO', 'Lunas'),
('OR453', 'T240', 'U002', 2, '2021-03-10', 'A001', 2, 40000, 'TODO', 'Lunas'),
('OR689', 'T302', 'U002', 3, '2021-03-09', 'A001', 5, 100000, 'TODO', 'Lunas'),
('OR773', 'T240', 'U002', 2, '2021-03-10', 'A006', 2, 50000, 'TODO', 'Lunas');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(11) NOT NULL,
  `id_user` varchar(11) NOT NULL,
  `tanggal` date NOT NULL,
  `total_bayar` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `tanggal`, `total_bayar`, `total_harga`, `kembalian`) VALUES
('T001', 'U002', '2021-03-16', 200000, 200000, 0),
('T002', 'U002', '2021-03-17', 100000, 120000, 20000),
('T240', 'U002', '2021-03-10', 650000, 610000, 40000),
('T302', 'U002', '2021-03-09', 300000, 250000, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` varchar(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id_level` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `name`, `username`, `password`, `id_level`) VALUES
('U001', 'Nama Admin', 'admin', 'admin123', 1),
('U002', 'Nama Kasir', 'kasir', 'kasir123', 2),
('U003', 'Nama Owner', 'owner', 'owner123', 3),
('U004', 'Nama Pelanggan', 'pelanggan', 'pelanggan123', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`id_level`);

--
-- Indexes for table `masakan`
--
ALTER TABLE `masakan`
  ADD PRIMARY KEY (`id_masakan`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `fk_order_id_masakan` (`id_masakan`),
  ADD KEY `fk_order_id_user` (`id_user`) USING BTREE,
  ADD KEY `fk_order_id_transaksi` (`id_transaksi`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_transaksi_id_user` (`id_user`) USING BTREE;

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `fk_user_id_level` (`id_level`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_order_id_masakan` FOREIGN KEY (`id_masakan`) REFERENCES `masakan` (`id_masakan`),
  ADD CONSTRAINT `fk_order_id_order` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_order_id_transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_transaksi_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_id_level` FOREIGN KEY (`id_level`) REFERENCES `level` (`id_level`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
