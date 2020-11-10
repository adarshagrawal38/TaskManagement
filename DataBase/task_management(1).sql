-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2020 at 03:16 PM
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

INSERT INTO `task` (`task_id`, `client_code`, `file_name`, `Initiator`, `work_des`, `period`, `task_priority`, `task_due_date`, `task_priority_position`, `task_status`, `created_date`, `completed_date`, `year`) VALUES
(1, 'RKA-2002A', 'ABCDEF', 'RKA', 'ITR', '', '', '2018-07-31', 1, 'Pending', '2020-06-01', NULL, '2017-18'),
(2, 'RKA-2002A', 'Adarsh', 'RKA', 'ITR', '', '', '2018-07-31', 2, 'Pending', '2020-06-01', NULL, '2017-18'),
(3, 'akk', 'Ahghg', 'admin', 'Accounts', 'Q3', 'No', '2020-06-08', 3, 'Pending', '2020-06-08', NULL, '2020-21');

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
(1, 'a1', 'a', '8305648558', 'adars@gmail.com', 'a1', 'user'),
(2, 'admin', 'a', '1234567890', 'd@f.bg', 'admin1234', 'admin'),
(3, 'RKA', 'Ravi Agrawal', '8305648558', 'r@gmail.com', 'admin1234', 'admin'),
(4, 'a', 'a', '1234567890', 'a@gmail.com', 'a', 'user'),
(5, 'b', 'b', '1234567891', 'b@gmail.com', 'b', 'user'),
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
  `completed_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work_book`
--

INSERT INTO `work_book` (`wb_id`, `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`, `stage_status`, `completed_date`) VALUES
(1, 1, 1, 'Follow & Receive', 'a', '2018-07-31', 'Pending', '2020-06-01'),
(2, 1, 2, 'Prepare', 'b', '2018-07-31', 'Finished', '2020-06-01'),
(3, 1, 3, 'Fill in Software', 'a', '2018-07-31', 'Pending', NULL),
(4, 1, 4, 'Upload', 'RKA', '2018-07-31', 'Pending', NULL),
(5, 1, 5, 'E-Verify / CPC Dispatch', 'RKA', '2018-07-31', 'Pending', NULL),
(6, 1, 6, 'Billing & Approve', 'RKA', '2018-07-31', 'Pending', NULL),
(7, 2, 1, 'Follow & Receive', 'RKA', '2018-07-31', 'Pending', NULL),
(8, 2, 2, 'Prepare', 'RKA', '2018-07-31', 'Pending', NULL),
(9, 2, 3, 'Fill in Software', 'RKA', '2018-07-31', 'Pending', NULL),
(10, 2, 4, 'Upload', 'RKA', '2018-07-31', 'Pending', NULL),
(11, 2, 5, 'E-Verify / CPC Dispatch', 'RKA', '2018-07-31', 'Pending', NULL),
(12, 2, 6, 'Billing & Approve', 'RKA', '2018-07-31', 'Pending', NULL),
(13, 1, 7, 'New stage', 'admin', '2020-06-01', 'Pending', NULL),
(14, 3, 1, 'Follow & Receive', 'admin', '2020-06-08', 'Pending', NULL),
(15, 3, 2, 'Prepare', 'admin', '2020-06-08', 'Pending', NULL),
(16, 3, 3, 'Review', 'admin', '2020-06-08', 'Pending', NULL),
(17, 3, 4, 'Billing', 'admin', '2020-06-08', 'Pending', NULL);

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
  MODIFY `stage_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
  MODIFY `wb_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
