-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 17, 2020 at 10:55 AM
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
(1, 1, 7, 'Follow & Receive'),
(2, 1, 8, 'Prepare'),
(3, 1, 9, 'Fill in Software'),
(4, 1, 10, 'Upload'),
(5, 1, 11, 'E-Verify / CPC Dispatch'),
(6, 1, 12, 'Billing & Approve'),
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
(43, 9, 2, 'Billing & Approve'),
(44, 1, 19, 'TestDown'),
(45, 1, 14, 'TestDown'),
(47, 1, 7, 'TestUp2'),
(48, 1, 6, 'TestUp2'),
(49, 1, 5, 'TestUp3'),
(50, 1, 4, 'TestUp3'),
(51, 1, 3, 'TestUp4'),
(52, 1, 1, 'Add to all');

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

INSERT INTO `task` (`task_id`, `client_code`, `file_name`, `Initiator`, `work_des`, `period`, `task_priority`, `task_due_date`, `task_priority_position`, `task_status`, `created_date`, `completed_date`, `year`) VALUES
(13, 'code1', 'acd_file', 'RKA', 'ITR', 'Q1', 'Yes', '2020-06-18', 13, 'Pending', '2020-06-17', NULL, '2020-21');

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

INSERT INTO `user` (`user_id`, `user_name`, `name`, `phone`, `email`, `password`, `role`) VALUES
(2, 'admin', 'a', '1234567890', 'd@f.bg', 'admin1234', 'admin'),
(3, 'RKA', 'Ravi Agrawal', '8305648558', 'r@gmail.com', 'admin1234', 'admin'),
(6, 'c', 'c', '1234567899', 'c@gmail.com', 'c', 'user'),
(7, 'd', 'd', '1234567890', 'd@gmail.com', 'd', 'user');

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
  `completed_date` date DEFAULT NULL,
  `remark` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work_book`
--

INSERT INTO `work_book` (`wb_id`, `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`, `stage_status`, `completed_date`, `remark`) VALUES
(109, 13, 1, 'Add to all', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(110, 13, 3, 'TestUp4', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(111, 13, 4, 'TestUp3', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(112, 13, 5, 'TestUp3', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(113, 13, 6, 'TestUp2', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(114, 13, 7, 'Follow & Receive', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(115, 13, 7, 'TestUp2', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(116, 13, 8, 'Prepare', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(117, 13, 9, 'Fill in Software', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(118, 13, 10, 'Upload', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(119, 13, 11, 'E-Verify / CPC Dispatch', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(120, 13, 12, 'Billing & Approve', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(121, 13, 14, 'TestDown', 'RKA', '2020-06-18', 'Pending', NULL, NULL),
(122, 13, 19, 'TestDown', 'RKA', '2020-06-18', 'Pending', NULL, NULL);

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
  MODIFY `stage_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `work`
--
ALTER TABLE `work`
  MODIFY `work_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `work_book`
--
ALTER TABLE `work_book`
  MODIFY `wb_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
