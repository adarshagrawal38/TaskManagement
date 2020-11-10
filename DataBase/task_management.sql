-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2020 at 06:51 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `task_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `stage`
--

CREATE TABLE `stage` (
  `stage_id` int(11) NOT NULL,
  `work_id` int(11) NOT NULL,
  `stage_num` int(11) NOT NULL,
  `stage_des` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stage`
--

INSERT INTO `stage` (`stage_id`, `work_id`, `stage_num`, `stage_des`) VALUES
(1, 1, 1, 'Follow & Receive'),
(2, 1, 2, 'Prepare'),
(3, 1, 3, 'Fill in Software'),
(4, 1, 4, 'Upload'),
(5, 1, 5, 'E-Verify / CPC Dispatch'),
(6, 1, 6, 'Billing & Approve'),
(7, 2, 1, 'Follow & Receive'),
(8, 2, 2, 'Prepare'),
(9, 2, 3, 'Upload TAR/29B/10A'),
(10, 2, 4, 'Upload ITR'),
(11, 2, 5, 'Billing'),
(12, 2, 6, 'Print'),
(13, 2, 7, 'Approve'),
(14, 3, 1, 'Follow & Receive'),
(15, 3, 2, 'Prepare'),
(16, 3, 3, 'Review'),
(17, 3, 4, 'Billing'),
(18, 4, 1, 'Follow & Receive'),
(19, 4, 2, 'Prepare'),
(20, 4, 3, 'Upload TAR/29B/10A'),
(21, 4, 4, 'Billing'),
(22, 4, 5, 'Print'),
(23, 4, 6, 'Approve'),
(24, 5, 1, 'Follow up & Receive'),
(25, 5, 2, 'Prepare'),
(26, 5, 3, 'File & Save Ack/Form/Challan'),
(27, 5, 4, 'Billing & Approve'),
(28, 6, 1, 'Follow up & Receive'),
(29, 6, 2, 'Prepare'),
(30, 6, 3, 'File & Save Ack/Form/Challan'),
(31, 6, 4, 'Billing & Approve'),
(32, 7, 1, 'Follow up & Receive'),
(33, 7, 2, 'Prepare'),
(34, 7, 3, 'File & Save Ack/Form/Challan'),
(35, 7, 4, 'Billing & Approve'),
(36, 8, 1, 'Follow & Receive'),
(37, 8, 2, 'Generate 27A'),
(38, 8, 3, 'File, Fill Ack in TP'),
(39, 8, 4, 'Billing'),
(40, 8, 5, 'Generate TDS Cert'),
(41, 8, 6, 'Approve'),
(42, 9, 1, 'Prepare'),
(43, 9, 2, 'Billing & Approve');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `client_code` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `Initiator` varchar(255) NOT NULL,
  `work_des` varchar(255) NOT NULL,
  `period` varchar(50) DEFAULT NULL,
  `task_priority` varchar(10) NOT NULL,
  `task_due_date` date NOT NULL,
  `task_priority_position` int(11) DEFAULT NULL,
  `task_status` varchar(10) NOT NULL DEFAULT 'Pending',
  `created_date` date NOT NULL DEFAULT current_timestamp(),
  `completed_date` date DEFAULT NULL,
  `year` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `task`
--


-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--
-- --------------------------------------------------------

--
-- Table structure for table `work`
--

CREATE TABLE `work` (
  `work_id` int(11) NOT NULL,
  `work_des` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work`
--

INSERT INTO `work` (`work_id`, `work_des`) VALUES
(3, 'Accounts'),
(4, 'Audit'),
(2, 'Audit+ITR'),
(5, 'GSTR-1'),
(6, 'GSTR-3B'),
(7, 'GSTR-4'),
(1, 'ITR'),
(9, 'Other'),
(8, 'TDS-R');

-- --------------------------------------------------------

--
-- Table structure for table `work_book`
--

CREATE TABLE `work_book` (
  `wb_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `stage_num` int(11) NOT NULL,
  `stage_des` varchar(255) NOT NULL,
  `assigned_to` varchar(255) NOT NULL,
  `due_date` date DEFAULT NULL,
  `stage_status` varchar(255) NOT NULL DEFAULT 'Pending',
  `completed_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work_book`
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stage`
--
ALTER TABLE `stage`
  ADD PRIMARY KEY (`stage_id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `work`
--
ALTER TABLE `work`
  ADD PRIMARY KEY (`work_id`),
  ADD UNIQUE KEY `work_des` (`work_des`);

--
-- Indexes for table `work_book`
--
ALTER TABLE `work_book`
  ADD PRIMARY KEY (`wb_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stage`
--
ALTER TABLE `stage`
  MODIFY `stage_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `work`
--
ALTER TABLE `work`
  MODIFY `work_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `work_book`
--
ALTER TABLE `work_book`
  MODIFY `wb_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
