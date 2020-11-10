-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2020 at 07:09 AM
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
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `conctact_person_1` varchar(255) NOT NULL,
  `conctact_person_2` varchar(255) NOT NULL,
  `contact_no_1` varchar(255) NOT NULL,
  `contact_no_2` varchar(255) NOT NULL,
  `contact_email_1` varchar(255) NOT NULL,
  `contact_email_2` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `code`, `file_name`, `group_name`, `conctact_person_1`, `conctact_person_2`, `contact_no_1`, `contact_no_2`, `contact_email_1`, `contact_email_2`) VALUES
(11, 'RKA-2001A', 'PARMANAND GUPTA', 'ALOCHAN', 'Arjun - Acct', 'Alochan Agrawal', '9981891558', '9893551073', 'balaji_handloom@yahoo.co.in', 'shrisilk@gmail.com'),
(12, 'RKA-2001B', 'ALOCHAN AGRAWAL', 'ALOCHAN', 'Arjun - Acct', 'Alochan Agrawal', '9981891558', '9893551073', 'balaji_handloom@yahoo.co.in', 'shrisilk@gmail.com'),
(13, 'RKA-2001C', 'SUSHMA DEVI GUPTA', 'ALOCHAN', 'Arjun - Acct', 'Alochan Agrawal', '9981891558', '9893551073', 'balaji_handloom@yahoo.co.in', 'shrisilk@gmail.com'),
(14, 'RKA-2001D', 'RAMLAL PARMANAND HUF ', 'ALOCHAN', 'Arjun - Acct', 'Alochan Agrawal', '9981891558', '9893551073', 'balaji_handloom@yahoo.co.in', 'shrisilk@gmail.com'),
(15, 'RKA-2001E', 'SHEETAL AGRAWAL', 'ALOCHAN', 'Arjun - Acct', 'Alochan Agrawal', '9981891558', '9893551073', 'balaji_handloom@yahoo.co.in', 'shrisilk@gmail.com'),
(16, 'RKA-2001F', 'ALOCHAN AGRAWAL HUF', 'ALOCHAN', 'Arjun - Acct', 'Alochan Agrawal', '9981891558', '9893551073', 'balaji_handloom@yahoo.co.in', 'shrisilk@gmail.com'),
(17, 'RKA-2002A', 'MAHAVIR AGRAWAL', 'MAHAVIR', 'Mahavir Agrawal', '', '9425274700', '', '', ''),
(18, 'RKA-2002B', 'ADEETYA FINANCE AGENCY', 'MAHAVIR', 'Mahavir Agrawal', '', '9425274700', '', '', ''),
(19, 'RKA-2003A', 'HANUMAN PRASAD AGRAWAL', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(20, 'RKA-2003B', 'GEETA DEVI AGRAWAL', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(21, 'RKA-2003C', 'HANUMAN PRASAD AGRAWAL HUF', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(22, 'RKA-2003D', 'AMIT KUMAR AGRAWAL (HUF)', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(23, 'RKA-2003E', 'PRITI AGRAWAL', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(24, 'RKA-2003F', 'HANUMAN PRASAD AMIT KUMAR HUF', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(25, 'RKA-2003G', 'HANUMAN PRASAD NARAYAN AGRAWAL', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(26, 'RKA-2003H', 'AMIT NARAYAN HUF', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(27, 'RKA-2003I', 'NARAYAN AGRAWAL (HUF)', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(28, 'RKA-2003J', 'NARAYAN AGRAWAL', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(29, 'RKA-2003K', 'AMIT KUMAR AGRAWAL', 'VOPL', 'Nimay Bareth - Acct', 'H P Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'gases.v@gmail.com'),
(30, 'RKA-2004A', 'ASHISH AGRAWAL', 'ASHU', 'Ashish Agrawal', '', '9425250025', '', 'kunalashu2002@gmail.com', ''),
(31, 'RKA-2004B', 'SHARDA FINANCE COMPANY', 'ASHU', 'Ashish Agrawal', '', '9425250025', '', 'kunalashu2002@gmail.com', ''),
(32, 'RKA-2004C', 'SAGARMAL HANUMAN PRASAD ', 'ASHU', 'Ashish Agrawal', '', '9425250025', '', 'kunalashu2002@gmail.com', ''),
(33, 'RKA-2004D', 'GOPIRAM MULCHAND HUF ', 'ASHU', 'Ashish Agrawal', '', '9425250025', '', 'kunalashu2002@gmail.com', ''),
(34, 'RKA-2004E', 'LAXMINARAYAN SAGARMAL HUF  ', 'ASHU', 'Ashish Agrawal', '', '9425250025', '', 'kunalashu2002@gmail.com', ''),
(35, 'RKA-2004F', 'LAXMINARAYAN HANUMANPRASAD HUF ', 'ASHU', 'Ashish Agrawal', '', '9425250025', '', 'kunalashu2002@gmail.com', ''),
(36, 'RKA-2004G', 'MOHIT AGRAWAL', 'ASHU', 'Ashish Agrawal', '', '9425250025', '', 'kunalashu2002@gmail.com', ''),
(37, 'RKA-2004H', 'SAROJ AGRAWAL', 'ASHU', 'Ashish Agrawal', '', '9425250025', '', 'kunalashu2002@gmail.com', ''),
(38, 'RKA-2006A', 'ANJU DEVI AGRAWAL', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(39, 'RKA-2006B', 'BRIJMOHAN AGRAWAL', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(40, 'RKA-2006C', 'BRIJ MOHAN AGRAWAL & FAMILY HUF', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(41, 'RKA-2006D', 'RAJESH AGRAWAL', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(42, 'RKA-2006E', 'RAJESH AGRAWAL & FAMILY HUF', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(43, 'RKA-2006F', 'RAVI AGRAWAL & FAMILY HUF', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(44, 'RKA-2006G', 'RAVI KUMAR AGRAWAL', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(45, 'RKA-2006H', 'SANGEETA DEVI AGRAWAL', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(46, 'RKA-2006I', 'SHAKUNTALA AGRAWAL', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(47, 'RKA-2007A', 'KAVITA AGRAWAL', 'DR VIKAS', 'Dr Vikas Agrawal', '', '9827193750', '', '', ''),
(48, 'RKA-2007B', 'VIKAS AGRAWAL', 'DR VIKAS', 'Dr Vikas Agrawal', '', '9827193750', '', '', ''),
(49, 'RKA-2008A', 'SHREE KESHAV DEVELOPERS ', 'KTPL', 'Naveen Goyal - Acct', 'Pankaj Agrawal', '9425277004', '9300473589', 'naveengoyals.rgh@gmail.com', 'pankaj51811@gmail.com'),
(50, 'RKA-2008B', 'SHREE KESHAV TOWNSHIP PRIVATE LIMITED ', 'KTPL', 'Naveen Goyal - Acct', 'Pankaj Agrawal', '9425277004', '9300473589', 'naveengoyals.rgh@gmail.com', 'pankaj51811@gmail.com'),
(51, 'RKA-2009A', 'KOUSIK DAS', 'KOUSIK', 'Kousik Das - Self', 'Kousik Das', '9329772840', '', 'kousik.moon@gmail.com', ''),
(52, 'RKA-2009B', 'MOUSUMI DAS', 'KOUSIK', 'Kousik Das - Self', 'Kousik Das', '9329772840', '', 'kousik.moon@gmail.com', ''),
(53, 'RKA-2010A', 'MUKTESHWAR PANDA', 'MUKTESHWAR', 'S N Panda', '', '7898903377', '', 'snpanda61@gmail.com', ''),
(54, 'RKA-2010B', 'MUKTESHWAR PANDA & FAMILY', 'MUKTESHWAR', 'S N Panda', '', '7898903377', '', 'snpanda61@gmail.com', ''),
(55, 'RKA-2011', 'RUCHI JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(56, 'RKA-2011A', 'NANAK CHAND AGRAWAL & SONS', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(57, 'RKA-2011B', 'NANGI DEVI AGRAWAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(58, 'RKA-2011C', 'KAMAL JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(59, 'RKA-2011D', 'KAMAL JINDAL & SONS', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(60, 'RKA-2011E', 'MUNNI DEVI JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(61, 'RKA-2011F', 'VISHNU PRASAD JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(62, 'RKA-2011G', 'VISHNU JINDAL & SONS', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(63, 'RKA-2011H', 'RADHIKA DEVI AGRAWAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(64, 'RKA-2011I', 'AMAR JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Amar jindal', '9893596255', '9993027101', 'sksaluja_ca@rediffmail.com', 'jindalraigarh@gmail.com'),
(65, 'RKA-2011J', 'SHWETA JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(66, 'RKA-2011K', 'ALOK JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Alok Jindal', '9893596255', '9993000081', 'sksaluja_ca@rediffmail.com', 'cgply2002@gmail.com'),
(67, 'RKA-2011L', 'MONIKA JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(68, 'RKA-2011M', 'ASHISH JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(69, 'RKA-2011N', 'NOOPUR JINDAL', 'JINDAL', 'S K Saluja - Acct', 'Kamal Jindal', '9893596255', '9993036000', 'sksaluja_ca@rediffmail.com', 'sksaluja_ca@rediffmail.com'),
(70, 'RKA-2011O', 'SANJAY KUMAR AGRAWAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(71, 'RKA-2011P', 'SANJAY JINDAL & SONS', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(72, 'RKA-2011Q', 'SARITA DEVI AGRAWAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(73, 'RKA-2011R', 'RAMAN KUMAR AGRAWAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(74, 'RKA-2011S', 'RAMAN JINDAL & SONS', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(75, 'RKA-2011T', 'NEEMA DEVI AGRAWAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(76, 'RKA-2011U', 'MANSIH JINDAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(77, 'RKA-2011V', 'MANSIH JINDAL & SONS', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(78, 'RKA-2011X', 'MITALI JINDAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(79, 'RKA-2011Y', 'SOURABH JINDAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(80, 'RKA-2011Z', 'SAMEER JINDAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(81, 'RKA-2011W', 'LAXMAN DAS AGRAWAL & SONS', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(82, 'RKA-2011AB', 'RICHA JINDAL', 'JINDAL', 'Acct', 'Manish Jindal', '9039346660', '9827193730', 'jindalply@gmail.com', 'jindalply@gmail.com'),
(83, 'RKA-2012A', 'KAMAL AGRAWAL & FAMILY (HUF) ', 'KAMAL', 'Harsh s/o Kamal Agrawal', 'Kamal Agrawal', '8770363884', '9755147800', 'harshagrawal34@gmail.com', 'harshagrawal34@gmail.com'),
(84, 'RKA-2012B', 'SEEMA AGRAWAL', 'KAMAL', 'Harsh s/o Kamal Agrawal', 'Kamal Agrawal', '8770363884', '9755147800', 'harshagrawal34@gmail.com', 'harshagrawal34@gmail.com'),
(85, 'RKA-2012C', 'KAMAL KUMAR AGRAWAL', 'KAMAL', 'Harsh s/o Kamal Agrawal', 'Kamal Agrawal', '8770363884', '9755147800', 'harshagrawal34@gmail.com', 'harshagrawal34@gmail.com'),
(86, 'RKA-2013', 'CHINTA MANI TRIPATHI', 'IND', 'C M Tripathi - Self', '', '9131740317', '', '-', ''),
(87, 'RKA-2014B', 'AJAY SHARMA HUF', 'AJAY', 'Ajay Sharma', '', '9827196655', '', '', ''),
(88, 'RKA-2014A', 'SANDHYA SHARMA', 'AJAY', 'Ajay Sharma', '', '9827196655', '', '', ''),
(89, 'RKA-2015A', 'MURARI LAL GUPTA', 'MLG', 'Nimay Bareth - Acct', 'Maurari Lal Gupta', '9893604600', '8516870811, 9826840811', 'nimaybareth@gmail.com', 'Kanha.gupta862@gmail.com'),
(90, 'RKA-2015B', 'MURARI LAL & SONS (HUF)', 'MLG', 'Kanha Gupta', 'Maurari Lal Gupta', '9826840811', '8516870811, 9826840811', 'Kanha.gupta862@gmail.com', 'Kanha.gupta862@gmail.com'),
(91, 'RKA-2015F', 'SANDHYA GUPTA', 'MLG', 'Kanha Gupta', 'Maurari Lal Gupta', '9826840811', '8516870811, 9826840811', 'Kanha.gupta862@gmail.com', 'Kanha.gupta862@gmail.com'),
(92, 'RKA-2015D', 'PRIYANKA AGRAWAL', 'MLG', 'Kanha Gupta', 'Maurari Lal Gupta', '9826840811', '8516870811, 9826840811', 'Kanha.gupta862@gmail.com', 'Kanha.gupta862@gmail.com'),
(93, 'RKA-2015E', 'SANJEEV AGRAWAL & SONS(HUF)', 'MLG', 'Kanha Gupta', 'Maurari Lal Gupta', '9826840811', '8516870811, 9826840811', 'Kanha.gupta862@gmail.com', 'Kanha.gupta862@gmail.com'),
(94, 'RKA-2015H', 'KANHA KAMAL GUPTA', 'MLG', 'Kanha Gupta', 'Maurari Lal Gupta', '9826840811', '8516870811, 9826840811', 'Kanha.gupta862@gmail.com', 'Kanha.gupta862@gmail.com'),
(95, 'RKA-2015G', 'SANJEEV AGRAWAL', 'MLG', 'Kanha Gupta', 'Maurari Lal Gupta', '9826840811', '8516870811, 9826840811', 'Kanha.gupta862@gmail.com', 'Kanha.gupta862@gmail.com'),
(96, 'RKA-2015C', 'NAMRATA GUPTA', 'MLG', 'Kanha Gupta', 'Maurari Lal Gupta', '9826840811', '8516870811, 9826840811', 'Kanha.gupta862@gmail.com', 'Kanha.gupta862@gmail.com'),
(97, 'RKA-2016A', 'AMIT KUMAR NAYAK', 'AMIT', 'Amit Nayak', '', '9179080900', '', 'prajnyaenterprisesrgh@gmail.com', ''),
(98, 'RKA-2016B', 'RAM SWAROOP NAYAK', 'AMIT', 'Amit Nayak', '', '9179080900', '', 'amit.nayak27@gmail.com', ''),
(99, 'RKA-2016C', 'PRIYANKA NAYAK', 'AMIT', 'Amit Nayak', '', '9179080900', '', 'amit.nayak27@gmail.com', ''),
(100, 'RKA-2016D', 'SONIYA NAYAK', 'AMIT', 'Amit Nayak', '', '7999274886', '', 'amit.nayak27@gmail.com', ''),
(101, 'RKA-2016E', 'ARCHANA NAYAK', 'AMIT', 'Amit Nayak', '', '9179080900', '', 'amit.nayak27@gmail.com', ''),
(102, 'RKA-2016F', 'BARKHA PANDEY', 'AMIT', 'Amit Nayak', '', '7999274886', '', 'amit.nayak27@gmail.com', ''),
(103, 'RKA-2017D', 'SANGITA AGRAWAL', 'VBD', 'Vinod Agrawal', '', '8770156711', '', '', ''),
(104, 'RKA-2017F', 'SUMAN AGRAWAL', 'VBD', 'Vinod Agrawal', '', '9827460834', '', '', ''),
(105, 'RKA-2017H', 'VINOD AGRAWAL', 'VBD', 'Vinod Agrawal', '', '9827460834', '', '', ''),
(106, 'RKA-2017B', 'NARESH AGRAWAL', 'VBD', 'Narayan Agrawal', '', '9425251470', '', '', ''),
(107, 'RKA-2017G', 'MEENU AGRAWAL', 'VBD', 'Narayan Agrawal', '', '9425251470', '', '', ''),
(108, 'RKA-2017A', 'NARAYAN AGRAWAL', 'VBD', 'Narayan Agrawal', '', '9827151470', '', '', ''),
(109, 'RKA-2017C', 'OMPRAKASH AGRAWAL', 'VBD', 'Narayan Agrawal', '', '8770488750', '', 'vijaybookdepo@yahoo.com', ''),
(110, 'RKA-2017E', 'SAROJ AGRAWAL', 'VBD', 'Narayan Agrawal', '', '9827151470', '', '', ''),
(111, 'RKA-2019A', 'NAVEEN KUMAR AGRAWAL', 'NAVEEN', 'Naveen Agrawal', '', '9425251713', '', '', ''),
(112, 'RKA-2019B', 'KUSUMLATA AGRAWAL', 'NAVEEN', 'Naveen Agrawal', '', '9425251713', '', '', ''),
(113, 'RKA-2019C', 'MURLI DHAR AGRAWAL (HUF)', 'NAVEEN', 'Naveen Agrawal', '', '9993598800', '', '', ''),
(114, 'RKA-2019D', 'MURLIDHAR AGRAWAL', 'NAVEEN', 'Naveen Agrawal', '', '9425251713', '', '', ''),
(115, 'RKA-2019E', 'NAVEEN KUMAR AGRAWAL & FAMILY (HUF) ', 'NAVEEN', 'Naveen Agrawal', '', '9993598800', '', '', ''),
(116, 'RKA-2019F', 'REETU AGRAWAL', 'NAVEEN', 'Naveen Agrawal', '', '9993598800', '', '', ''),
(117, 'RKA-2020A', 'PAWAN KUMAR AGRAWAL', 'PAWAN K', 'Pawan Agrawal', '', '9993291414', '', '', ''),
(118, 'RKA-2020B', 'PAWAN KUMAR AGRAWAL HUF', 'PAWAN K', 'Pawan Agrawal', '', '9993291414', '', '', ''),
(120, 'RKA-2020D', 'ARTI AGRAWAL', 'PAWAN K', 'Archit Agrawal', 'Pawan Agrawal', '9981186222', '9993291414', 'agrawal.archit2002@gmail.com, mju.prince@gmail.com', ''),
(121, 'RKA-2020E', 'MAMTA AGRAWAL', 'PAWAN K', 'Archit Agrawal', 'Pawan Agrawal', '9981186222', '9993291414', 'agrawal.archit2002@gmail.com, mju.prince@gmail.com', ''),
(122, 'RKA-2020F', 'UMANG AGRAWAL', 'PAWAN K', 'Archit Agrawal', 'Pawan Agrawal', '9981186222', '9993291414', 'agrawal.archit2002@gmail.com, mju.prince@gmail.com', ''),
(123, 'RKA-2020G', 'KASHI RAM FAKIR CHAND ', 'PAWAN K', 'Archit Agrawal', 'Pawan Agrawal', '9981186222', '9993291414', 'agrawal.archit2002@gmail.com, mju.prince@gmail.com', ''),
(124, 'RKA-2020K', 'NIRMALA DEVI AGRAWAL', 'PAWAN K', 'Archit Agrawal', 'Pawan Agrawal', '9981186222', '9993291414', 'agrawal.archit2002@gmail.com, mju.prince@gmail.com', ''),
(125, 'RKA-2020L', 'PINKY AGRAWAL', 'PAWAN K', 'Archit Agrawal', 'Pawan Agrawal', '9981186222', '9993291414', 'agrawal.archit2002@gmail.com, mju.prince@gmail.com', ''),
(126, 'RKA-2020N', 'RUCHI AGRAWAL', 'PAWAN K', 'Archit Agrawal', 'Pawan Agrawal', '9981186222', '9993291414', 'agrawal.archit2002@gmail.com, mju.prince@gmail.com', ''),
(127, 'RKA-2020O', 'ARCHIT AGRAWAL HUF', 'PAWAN K', 'Archit Agrawal', 'Pawan Agrawal', '9981186222', '9993291414', 'agrawal.archit2002@gmail.com, mju.prince@gmail.com', ''),
(128, 'RKA-2020J', 'NEERAJ KUMAR AGRAWAL', 'IND', 'Neeraj Agrawal', '', '9425252251', '', '', ''),
(129, 'RKA-2021A', 'PAWAN KUMAR SINGHANIA', 'PAWAN S', 'Pawan Singhania', '', '9893293940', '', 'pawankumarsinghania1@gmail.com', ''),
(130, 'RKA-2021B', 'SANGEETA SINGHANIA', 'PAWAN S', 'Pawan Singhania', '', '9893293940', '', 'pawankumarsinghania1@gmail.com', ''),
(131, 'RKA-2021D', 'BHUPENDRA SINGHANIA', 'PAWAN S', 'Pawan Singhania', '', '9770447587', '', 'pawankumarsinghania1@gmail.com', ''),
(132, 'RKA-2021E', 'HARSHITA AGRAWAL', 'PAWAN S', 'Gaurav Agrawal', '', '9039804100', '', '', ''),
(133, 'RKA-2022A', 'VINEET SHARAN PANDEY', 'VSP', 'Vineet Sharan Pandey', '', '9406212465', '', 'vineeetspandey1972@gmail.com', ''),
(134, 'RKA-2022B', 'VIJAY LAXMI PANDEY', 'VSP', 'Vineet Sharan Pandey', '', '9406212465', '', 'vineeetspandey1972@gmail.com', ''),
(135, 'RKA-2023A', 'PRITHVIPAL SINGHANIA', 'PRITHVI', 'Prithvipal Singhnia ', '', '9425252555', '', 'prithvisteels@gmail.com', ''),
(136, 'RKA-2023B', 'POOJA SINGHANIA', 'PRITHVI', 'Prithvipal Singhnia ', '', '9425252555', '', 'prithvisteels@gmail.com', ''),
(137, 'RKA-2023C', 'SAVITRI AGRAWAL', 'PRITHVI', 'Prithvipal Singhnia ', '', '9425252555', '', 'prithvisteels@gmail.com', ''),
(138, 'RKA-2023D', 'ARTH DEVELOPERS', 'PRITHVI', 'Prithvipal Singhnia ', '', '9425252555', '', 'prithvisteels@gmail.com', ''),
(139, 'RKA-2023E', 'SHARDA MOHAN AGRAWAL', 'PRITHVI', 'Prithvipal Singhnia ', '', '9754020555', '', 'prithvisteels@gmail.com', ''),
(140, 'RKA-2023F', 'NEHA AGRAWAL', 'PRITHVI', 'Prithvipal Singhnia ', '', '9425252555', '', 'prithvisteels@gmail.com', ''),
(141, 'RKA-2024A', 'GOVIND RAM AGRAWAL', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(142, 'RKA-2024B', 'GOVIND RAM AGRAWAL HUF', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(143, 'RKA-2024C', 'SARLA DEVI AGRAWAL', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(144, 'RKA-2024D', 'SAURABH AGRAWAL', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(145, 'RKA-2024E', 'ANURADHA AGRAWAL', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(146, 'RKA-2024F', 'GAURABH AGRAWAL', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(147, 'RKA-2024G', 'SAURABH AGRAWAL & FAMILY (HUF)', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(148, 'RKA-2024H', 'RAIGARH BUILDCON  ', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(149, 'RKA-2024I', 'SAROJ KUMAR PATHY', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(150, 'RKA-2024J', 'OM BUILDERS     ', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(151, 'RKA-2024K', 'GAURABH AGRAWAL (HUF)', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(152, 'RKA-2024L', 'BAL KUMAR SHARMA', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(153, 'RKA-2024M', 'Neha  Agrawal', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(154, 'RKA-2024N', 'SUNIL DEWANGAN', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(155, 'RKA-2024O', 'RAJIV KUMAR JHA', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(156, 'RKA-2024P', 'RAMDEO JHA', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(157, 'RKA-2024Q', 'SAI BUILDERS AND DEVELOPERS', 'RB', 'Gaurabh Agrawal - Self', '', '9425522500', '', 'gaurabh5@gmail.com', ''),
(158, 'RKA-2025A', 'RAMESH KUMAR AGRAWAL', 'RSF', 'Rahul Agrawal', '', '9977646060', '', 'rahuldabhara6060@gmail.com', ''),
(159, 'RKA-2025B', 'RAMESH KUMAR AGRAWAL AND FAMILY HUF', 'RSF', 'Rahul Agrawal', '', '9977646060', '', 'rahuldabhara6060@gmail.com', ''),
(160, 'RKA-2025C', 'SARITA DEVI AGRAWAL', 'RSF', 'Rahul Agrawal', '', '9977646060', '', 'rahuldabhara6060@gmail.com', ''),
(161, 'RKA-2025D', 'RAHUL AGRAWAL', 'RSF', 'Rahul Agrawal', '', '9977646060', '', 'rahuldabhara6060@gmail.com', ''),
(162, 'RKA-2026A', 'GOPAL CHAND AGRAWAL', 'RJ', 'Vishwesh Agrawal', '', '9754850132', '', 'vishveshagrawal@gmail.com', ''),
(163, 'RKA-2026B', 'GOVIND KUMAR AGRAWAL', 'RJ', 'Vishwesh Agrawal', '', '9754850132', '', 'vishveshagrawal@gmail.com', ''),
(164, 'RKA-2026C', 'HARISHCHANDRA AGRAWAL & SONS', 'RJ', 'Vishwesh Agrawal', '', '9754850132', '', 'vishveshagrawal@gmail.com', ''),
(165, 'RKA-2026D', 'NATWAR LAL AGRAWAL', 'RJ', 'Vishwesh Agrawal', '', '9754850132', '', 'vishveshagrawal@gmail.com', ''),
(166, 'RKA-2026E', 'VISHWESH AGRAWAL', 'RJ', 'Vishwesh Agrawal', '', '9754850132', '', 'rj.raigarh@gmail.com', ''),
(167, 'RKA-2027A', 'ROSHAN LAL AGRAWAL', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(168, 'RKA-2027B', 'ROSHAN LAL AGRAWAL & SONS (HUF)  ', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(169, 'RKA-2027H', 'RAMNIVAS AGRAWAL & SONS (HUF)', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(170, 'RKA-2027D', 'GAUTAM AGRAWAL', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(171, 'RKA-2027E', 'GAUTAM AGRAWAL AND SONS HUF', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(172, 'RKA-2027F', 'JYOTI AGRAWAL', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(173, 'RKA-2027G', 'MADHVIKA GOYAL', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(174, 'RKA-2027C', 'SAHODARA DEVI AGRAWAL', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(175, 'RKA-CO9', 'SHOURYA MINERALS PRIVATE LIMITED', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(176, 'RKA-2027I', 'PRAKASH SINGH', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(177, 'RKA-CO8', 'MATESHWARI REALTORS PRIVATE LIMITED ', 'RLA', 'Prakash Hinduja - Acct', '', '9179507784', '', 'shouryaminerals@gmail.com', ''),
(178, 'RKA-2028A', 'VIKRAM GUPTA', 'PARMA', 'Vikram Gupta', '', '9907116000', '', 'vikramkanha@gmail.com', ''),
(179, 'RKA-2028B', 'RAJKUMARI GUPTA', 'PARMA', 'Vikram Gupta', '', '9907116000', '', 'vikramkanha@gmail.com', ''),
(180, 'RKA-2028C', 'MONICA GUPTA', 'PARMA', 'Vikram Gupta', '', '9907116000', '', 'vikramkanha@gmail.com', ''),
(181, 'RKA-2029B', 'POONAM CHAND AGRWAL', 'RSRM', 'Rajiv Agrawal', '', '9617993606', '', 'rajivkhs@gmail.com', ''),
(182, 'RKA-2029D', 'POOJA AGRAWAL', 'RSRM', 'Rajiv Agrawal', '', '9617993606', '', 'rajivkhs@gmail.com', ''),
(183, 'RKA-2029E', 'RAJIV KUMAR AGRAWAL', 'RSRM', 'Rajiv Agrawal', '', '9617993606', '', 'rajivkhs@gmail.com', ''),
(184, 'RKA-2029F', 'ALOK AGRAWAL', 'RSRM', 'Rajiv Agrawal', '', '9617993606', '', 'rajivkhs@gmail.com', ''),
(185, 'RKA-2029G', 'AMBRISH AGRAWAL AND SONS (HUF)', 'RSRM', 'Rajiv Agrawal', '', '9617993606', '', 'rajivkhs@gmail.com', ''),
(186, 'RKA-2029N1', 'KAILASH CHAND AGRAWAL', 'RSRM', 'Rajiv Agrawal', '', '9617993606', '', 'rajivkhs@gmail.com', ''),
(187, 'RKA-2030A', 'SANGEETA RATERIA', 'SAKHI', 'Manoj Rateria', '', '9329751020', '', 'majjusakhi@gmail.com', ''),
(188, 'RKA-2030B', 'MANAV RATERIA', 'SAKHI', 'Manoj Rateria', '', '9329751020', '', 'majjusakhi@gmail.com', ''),
(189, 'RKA-2030C', 'SHREYA RATERIA', 'SAKHI', 'Manoj Rateria', '', '9329751020', '', 'majjusakhi@gmail.com', ''),
(190, 'RKA-2030D', 'KEJRIWAL CRUSHER UDYOG', 'SAKHI', 'Manoj Rateria', '', '9329751020', '', 'majjusakhi@gmail.com', ''),
(191, 'SAKHI-SB', 'SAKHI BUILDCON', 'SAKHI', 'Manoj Rateria', '', '9329751020', '', 'majjusakhi@gmail.com', ''),
(192, 'RKA-2031A', 'NISHA AGRAWAL', 'SANTOSH RJ', 'Santosh Agrawal', '', '9981164651', '', '', ''),
(193, 'RKA-2031B', 'SANTOSH AGRAWAL & FAMILY (HUF) ', 'SANTOSH RJ', 'Santosh Agrawal', '', '9981164651', '', '', ''),
(194, 'RKA-2031C', 'SANTOSH KUMAR AGRAWAL', 'SANTOSH RJ', 'Santosh Agrawal', '', '9981164651', '', '', ''),
(195, 'RKA-2032A', 'GOPAL DAS AGRAWAL', 'GOPAL KHS', 'Manish Agrawal', '', '9522722211', '', '', ''),
(196, 'RKA-2032B', 'KAUSHALAYA DEVI AGRAWAL', 'GOPAL KHS', 'Manish Agrawal', '', '9522722211', '', '', ''),
(197, 'RKA-2032H', 'GOPAL DAS AGRAWAL (HUF) ', 'GOPAL KHS', 'Manish Agrawal', '', '9522722211', '', '', ''),
(198, 'RKA-2032D', 'MANISH KUMAR AGRAWAL', 'GOPAL KHS', 'Manish Agrawal', '', '9977471371', '', '', ''),
(199, 'RKA-2032J', 'MANISH KUMAR AGRAWAL (HUF) ', 'GOPAL KHS', 'Manish Agrawal', '', '9977471371', '', '', ''),
(200, 'RKA-2032G', 'SHEETAL AGRAWAL', 'GOPAL KHS', 'Manish Agrawal', '', '9977471371', '', '', ''),
(201, 'RKA-2032E', 'ANJANI KUMAR AGRAWAL', 'GOPAL KHS', 'Manish Agrawal', '', '7583074206', '', '', ''),
(202, 'RKA-2032C', 'MANOJ AGRAWAL', 'GOPAL KHS', 'Manish Agrawal', '', '9584069149', '', '', ''),
(203, 'RKA-2032F', 'HANSA DEVI AGRAWAL', 'GOPAL KHS', 'Manish Agrawal', '', '9584069149', '', '', ''),
(204, 'RKA-2032I', 'MANOJ KUMAR AGRAWAL (HUF) ', 'GOPAL KHS', 'Manish Agrawal', '', '9584069149', '', '', ''),
(205, 'RKA-2033A', 'AJAY AGRAWAL & FAMILY (HUF) ', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9981702000', '', '', ''),
(206, 'RKA-2033B', 'ANJU AGRAWAL', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9981701000', '', '', ''),
(207, 'RKA-2033C', 'JYOTI AGRAWAL', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9981702000', '', '', ''),
(208, 'RKA-2033D', 'SANJAY AGRAWAL & FAMILY(HUF) ', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9981701000', '', '', ''),
(209, 'RKA-2033E', 'AYUSHI AGRAWAL', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '7000385351', '', '', ''),
(210, 'RKA-2033F', 'SRIKRISHNA AGRAWAL & SONS ', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9425250421', '', '', ''),
(211, 'RKA-2033G', 'AJAY AGRAWAL', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9981702000', '', '', ''),
(212, 'RKA-2033H', 'SANJAY KUMAR AGRAWAL', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9981701000', '', '', ''),
(213, 'RKA-2033I', 'SHRIKRISHAN AGRAWAL', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9425250421', '', '', ''),
(214, 'RKA-2033J', 'URMILA  DEVI AGRAWAL', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '9425250421', '', '', ''),
(215, 'RKA-2033K', 'SHUBHAM AGRAWAL', 'SHRIKISHAN', 'Shrikishan Agrawal', '', '7000385351', '', '', ''),
(216, 'RKA-2034A', 'SUBHASH CHANDRA AGRAWAL', 'SUBHASH KHS', 'Subhash C Agrawal', '', '9826181271', '', '', ''),
(217, 'RKA-2034B', 'NIRMALA DEVI AGRAWAL', 'SUBHASH KHS', 'Subhash C Agrawal', '', '9826181271', '', '', ''),
(218, 'RKA-2034C', 'SHARAD KUMAR AGRAWAL', 'SUBHASH KHS', 'Subhash C Agrawal', '', '9826359508', '', '', ''),
(219, 'RKA-2034D', 'SUBHASH CHAND AGRAWAL (HUF)', 'SUBHASH KHS', 'Subhash C Agrawal', '', '9826181271', '', '', ''),
(220, 'RKA-2034E', 'KRITI AGRAWAL', 'SUBHASH KHS', 'Subhash C Agrawal', '', '9826359508', '', '', ''),
(221, 'RKA-2034F', 'SHARAD AGRAWAL (HUF)', 'SUBHASH KHS', 'Subhash C Agrawal', '', '9826359508', '', '', ''),
(222, 'RKA-2036A', 'HANUMAN PRASAD JAIN', 'VKJ', 'Navin Kumar', '', '8103302844', '', 'vinodkmrjain@gmail.com', 'vinodkmr_jain@yahoo.com'),
(223, 'RKA-2036B', 'SANJANA DEVI JAIN', 'VKJ', 'Navin Kumar', '', '8103302844', '', 'vinodkmrjain@gmail.com', 'vinodkmr_jain@yahoo.com'),
(224, 'RKA-2036C', 'VINOD KUMAR JAIN HUF', 'VKJ', 'Navin Kumar', '', '8103302844', '', 'vinodkmrjain@gmail.com', 'vinodkmr_jain@yahoo.com'),
(225, 'RKA-2036D', 'VINOD KUMAR JAIN', 'VKJ', 'Navin Kumar', '', '8103302844', '', 'vinodkmrjain@gmail.com', 'vinodkmr_jain@yahoo.com'),
(226, 'RKA-2036E', 'VIDYASAGAR INFRASTRUCTURES     ', 'VKJ', 'Navin Kumar', '', '8103302844', '', 'vinodkmrjain@gmail.com', 'vinodkmrjain@gmail.com'),
(227, 'RKA-2036F', 'VALECHA VKJ (JV)', 'VKJ', 'Navin Kumar', '', '8103302844', '', 'vinodkmrjain@gmail.com', 'vinodkmrjain@gmail.com'),
(228, 'RKA-2036G', 'M/S VINOD KUMAR JAIN', 'VKJ', 'Navin Kumar', '', '8103302844', '', 'vinodkmrjain@gmail.com', 'vinodkmrjain@gmail.com'),
(229, 'RKA-2037', 'REKHA AGRAWAL', 'IND', 'Sajan Agrawal', '', '9986873511', '', 'sajan@netapp.com', ''),
(230, 'RKA-2038A', 'ANIL GOYAL', 'ANIL', 'Anil Goyal', '', '9826333682', '', 'anilkumarkharsia@gmail.com', ''),
(231, 'RKA-2038B', 'BANDANA GOYAL', 'ANIL', 'Anil Goyal', '', '9826333682', '', 'anilkumarkharsia@gmail.com', ''),
(232, 'RKA-2039', 'SEETAL KUMAR SAO', 'IND', 'Seetal Sao', '', '9981052951', '', '', ''),
(233, 'RKA-2040', 'SURESH KUMAR CHAINI', 'IND', 'Suresh Chaini', '', '9406028443', '', '', ''),
(234, 'RKA-2041B', 'SANTOSH KUMAR GUPTA', 'IND', 'Santosh Gupta', '', '9425250002', '', 'vikramg278@gmail.com', ''),
(235, 'RKA-2042A', 'UTPAL BHATTACHARJEE', 'UTPAL', 'Utpal Bhattacharjee', '', '9425573679', '', 'utpal_089@yahoo.com ', ''),
(236, 'RKA-2042B', 'SUTAPA BHATTACHARYA', 'UTPAL', 'Utpal Bhattacharjee', '', '9777308715', '', '', ''),
(237, 'RKA-2042C', 'UJWAL BHATTACHARJEE', 'UTPAL', 'Ujwal Bhattacharjee', '', '9777308715', '', '', ''),
(238, 'RKA-2043', 'AKSHAT BUILDERS', 'KTPL', 'Pankaj Agrawal', '', '9300473589', '', '', ''),
(239, 'RKA-2044', 'VISHNU SHARMA', 'IND', 'Vishnu Sharma', '', '9993775587', '', '', ''),
(240, 'RKA-2045A', 'Suresh Kumar Agrawal', 'SURESH', 'Gaurav Agrawal', '', '9826488300', '', 'gauravag.rgh@gmail.com', ''),
(241, 'RKA-2045B', 'SHOBHA AGRAWAL', 'SURESH', 'Gaurav Agrawal', '', '9826488300', '', 'gauravag.rgh@gmail.com', ''),
(242, 'RKA-2045C', 'Gaurav Kumar Agrawal', 'SURESH', 'Gaurav Agrawal', '', '9826488300', '', 'gauravag.rgh@gmail.com', ''),
(243, 'RKA-2045D', 'SWETA AGRAWAL', 'SURESH', 'Gaurav Agrawal', '', '9826488300', '', 'gauravag.rgh@gmail.com', ''),
(244, 'RKA-2045E', 'SURESH KUMAR AGRAWAL SONS HUF ', 'SURESH', 'Gaurav Agrawal', '', '9826488300', '', 'gauravag.rgh@gmail.com', ''),
(245, 'RKA-2045F', 'GAURAV KUMAR AGRAWAL HUF   ', 'SURESH', 'Gaurav Agrawal', '', '9826488300', '', 'gauravag.rgh@gmail.com', ''),
(246, 'RKA-2046', 'BABOO RAM YADAV', 'IND', 'Baboo Ram Yadav', '', '9893969369', '', '', ''),
(247, 'RKA-2047A', 'ANKIT AGRAWAL', 'ANKIT', 'Ankit Agrawal', '', '8889851851', '', 'ankeetgoel@gmail.com', ''),
(248, 'RKA-2047B', 'AYUSH AGRAWAL', 'ANKIT', 'Ankit Agrawal', '', '8889851851', '', 'ankeetgoel@gmail.com', ''),
(249, 'RKA-2047C', 'CHANDANI AGRAWAL', 'ANKIT', 'Ankit Agrawal', '', '8889851851', '', 'ankeetgoel@gmail.com', ''),
(250, 'RKA-CO1', 'AJAY INGOT ROLLING MILL PRIVATE LIMITED ', 'JINDAL', 'Vishnu Sharma - Acct', 'Ashish Jindal', '9109121001', '9179050000', 'vishnuairmpl@gmail.com', 'vishnuairmpl@gmail.com'),
(251, 'RKA-CO7', 'MANISH ISPAT PRIVATE LIMITED ', 'JINDAL', 'Vishnu Sharma - Acct', 'Kamal Jindal', '9109121001', '9993036000', 'vishnuairmpl@gmail.com', 'snsc_rgh@rediffmail.com'),
(252, 'RKA-CO11', 'SHRI DADU DAYAL METALS PRIVATE LIMITED', 'DAYAL', 'Laxmi - Acct', 'Rajesh Agrawal', '9826133247', '9893042914', 'sddmpl@yahoo.in', 'sddmpl@yahoo.in'),
(253, 'RKA-CO13', 'SHRI NIRMLANAND STEELS CASTING PRIVATE LIMITED', 'JINDAL', 'Vishnu Sharma - Acct', 'Kamal Jindal', '9109121001', '9993036000', 'snsc_rgh@rediffmail.com', 'snsc_rgh@rediffmail.com'),
(254, 'RKA-CO15', 'SURAJ ROLLING PRIVATE LIMITED', 'SRPL', 'Kamlesh Agrawal - Acct', 'Pawan Agrawal', '9644955504', '9993291414', 'kamleshnaku@gmail.com', 'suraj_rolling@yahoo.com'),
(255, 'RKA-CO16', 'SURYODAY STEEL PLANT PRIVATE LIMITED  ', 'SSPPL', 'Mahesh Agrawal - Director', 'Mahesh Agrawal, Prem Agrawal', '9303640410', '9303640410, 9893077002', 'ssppl17@yahoo.co.in', 'ssppl17@yahoo.co.in'),
(256, 'RKA-CO17', 'VINDHYACHAL OXYGEN PRIVATE LIMITED ', 'VOPL', 'Nimay Bareth - Acct', 'Hanuman Agrawal', '9893604600', '9329604005', 'gases.v@gmail.com', 'voplamit@gmail.com'),
(257, 'RKA-CO18', 'VINOD KUMAR JAIN AGRO EXIM PRIVATE LIMITED', 'VKJ', 'Navin Kumar - CA', 'Navin Kumar', '8103302844', '8103302844', 'vinodkmrjain@gmail.com', 'vinodkmrjain@gmail.com'),
(258, 'RKA-CO19', 'PRIKOYA AGRO INDIA PRIVATE LIMITED  ', 'PRIKOYA', 'Prince Bhartish Bhardwaj', '', '9755369008', '', 'prince.bhartish@gmail.com', ''),
(259, 'RKA-CO4', 'HPRJ AGRI TRADERS PRIVATE LIMITED  ', 'HPRJ', 'Dipesh - Acct', 'Rahul Jain', '9145767454', '9329440105', 'hprj_agri@yahoo.com', 'jainjashpur@yahoo.co.in'),
(260, 'RKA-CO6', 'KALP AGRI FARMS PRIVATE LIMITED  ', 'RB', 'Gaurabh Agrawal', '', '9425522500', '', 'kalpagrifarms@gmail.com', ''),
(261, 'RKA-JSPL2', 'SIDDHARTH SHANKAR BEHERA', 'IND', 'Siddharth Shanker Behra', '', '8827394983', '', 'siddharth_behera@yahoo.com', ''),
(262, 'RKA-JSPL3', 'SUBHASH CHANDRA THAKUR', 'IND', 'Subhash Chandra Thakur', '', '9827477342', '', 'subhash.thakur@jspl.com', ''),
(263, 'RKA-OA1', 'SETH RAMGOPALN LUXMI NARAYAN CHARITY TRUST', 'SRLNCT', 'Hari Agrawal', '', '9826198515', '', 'hari.agr@gmail.com', ''),
(264, 'RKA-OA3', 'GOPAL JI MAHAPRABHU AND CHANDRAHASINI MANDIR TRUST', 'CHANDRAHASINI', 'Rudra Upadhyay - Acct', '', '7000462537', '', 'infochandrahasini@gmail.com', ''),
(265, 'RKA-OWN11', 'SUDHIR GUPTA', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(266, 'RKA-OWN12', 'SUDHIR GUPTA & FAMILY', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(267, 'RKA-OWN13', 'SUMAN GUPTA', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(268, 'RKA-OWN8', 'SANJAY AGRAWAL', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(269, 'RKA-OWN9', 'SANJAY AGRAWAL & FAMILY', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(270, 'RKA-OWN14', 'SUNITA AGRAWAL', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(271, 'RKA-OWN1', 'HEENA AGRAWAL', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(272, 'RKA-OWN18', 'GAURAV AGRAWAL', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(273, 'RKA-OWN19', 'ADARSH AGRAWAL', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(274, 'RKA-OWN17', 'ASHITA GUPTA', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(275, 'RKA-OWN2', 'ISHWARI DEVI AGRAWAL', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(276, 'RKA-OWN3', 'MONIKA AGRAWAL', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(277, 'RKA-OWN5', 'RAJKUMAR AGRAWAL & FAMILY (HUF)', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(278, 'RKA-OWN6', 'RAVI KUMAR AGRAWAL', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(279, 'RKA-OWN7', 'RAVI KUMAR AGRAWAL & FAMILY (HUF)', 'OWN', 'Ravi Agrawal', '', '932935152', '', '', ''),
(280, 'RKA-2045G', 'AGROHA IRON & STEEL INDUSTRIES', 'SURESH', 'Gaurav Agrawal', '', '9826488300', '', 'gauravag.rgh@gmail.com', ''),
(281, 'RKA-DOR53', 'MEERA & SONS', 'MSONS', 'Rajkumar Agrawal - Self', '', '9425250251', '', 'rsplrgh@rediffmail.com', ''),
(282, 'RKA-TA10', 'DR PRASHANT AGRAWAL', 'DR PRASHANT', 'Kamal Adhikari - Acct', 'Prashant Agrawal', '9827195626', '9425501788', 'kamalraigarh@yahoo.com', 'drprashantagrawal@yahoo.co.in'),
(283, 'RKA-TA17', 'PURUSHOTTAM AGRAWAL', 'SANJEEVANI', 'Shambhu Beriwal - Acct', 'Purushottam Agrawal', '9893299860', '9425251365', '', 'agrawalpappu63@gmail.com'),
(284, 'TAR-VGS', 'VISHNU GENERAL STORES', 'VGS', 'Vishnu Bhagwan Agrawal - Self', '', '9424185322', '', '', ''),
(285, 'TAR-LGS', 'LAXMI GENERAL STORES', 'LGS', 'Vishnu Bhagwan Agrawal - Self', '', '9424185322', '', '', ''),
(286, 'RKA-2048', 'CHANDU LAL URANV', 'IND', 'Baboo Ram Yadav', '', '9893969369', '', '', ''),
(287, 'RKA-CIPL', 'CARBACT INDIA PRIVATE LIMITED   ', 'ATUL', 'Ankita Mundhra', 'Tarun Prakash Mundhra', '7610691111', '8103391111', 'mundhraankita.ca@gmail.com', 'tarunmundhra1209@gmail.com'),
(288, 'RKA-MHPL', 'MUNDHRA HOLDING PRIVATE LIMITED     ', 'ATUL', 'Ankita Mundhra', 'Tarun Prakash Mundhra', '7610691111', '8103391111', 'mundhraankita.ca@gmail.com', 'tarunmundhra1209@gmail.com'),
(289, 'RKA-PN', 'PADMANAV NAYAK', 'IND', 'Padmanav Nayak', '', '7509764600', '', '', ''),
(290, 'RKA-TABPL', 'TIRUMALA BALAJI ALLOYS PRIVATE LIMITED  ', 'TBAPL', 'J N Pradhan', 'Anil Gupta', '9893446934', '7980077614', 'tbaplrgh123@gmail.com', 'anil@rungtaindustries.com'),
(291, 'RKA-JSPL4', 'SANDEEP SINHA', 'IND', 'Sandeep Sinha', '', '8827478311', '', '', ''),
(292, 'RKA-2020C', 'ARCHIT AGRAWAL', 'PAWAN K', 'Prashant Agrawal', 'Archit Agrawal', '9752108128', '9981186222', 'agrawal.archit2002@gmail.com', 'mju.prince@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `client_work`
--

CREATE TABLE `client_work` (
  `client_id` int(11) NOT NULL,
  `work_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client_work`
--

INSERT INTO `client_work` (`client_id`, `work_id`) VALUES
(11, 23),
(11, 17),
(12, 23),
(12, 17),
(12, 28),
(13, 23),
(14, 23),
(15, 23),
(16, 23),
(16, 17),
(16, 28),
(17, 23),
(18, 23),
(19, 23),
(20, 23),
(21, 23),
(22, 23),
(23, 23),
(24, 23),
(25, 23),
(26, 23),
(27, 23),
(28, 23),
(29, 23),
(29, 17),
(29, 28),
(31, 23),
(31, 28),
(32, 23),
(33, 23),
(34, 23),
(35, 23),
(38, 23),
(39, 23),
(39, 17),
(39, 28),
(40, 23),
(41, 23),
(41, 17),
(41, 28),
(42, 23),
(43, 23),
(44, 23),
(44, 17),
(44, 28),
(44, 22),
(45, 23),
(46, 23),
(47, 23),
(48, 23),
(49, 23),
(49, 28),
(50, 23),
(50, 18),
(50, 17),
(50, 28),
(50, 26),
(51, 23),
(52, 23),
(53, 23),
(54, 23),
(55, 23),
(56, 23),
(57, 23),
(58, 23),
(59, 23),
(60, 23),
(61, 23),
(62, 23),
(63, 23),
(64, 23),
(64, 17),
(64, 28),
(64, 22),
(65, 23),
(66, 23),
(66, 17),
(66, 28),
(66, 22),
(67, 23),
(68, 23),
(69, 23),
(70, 23),
(71, 23),
(72, 23),
(73, 23),
(74, 23),
(75, 23),
(76, 23),
(76, 17),
(76, 28),
(77, 23),
(78, 23),
(79, 23),
(80, 23),
(81, 23),
(82, 23),
(83, 23),
(83, 22),
(84, 23),
(85, 23),
(85, 17),
(85, 28),
(85, 22),
(86, 23),
(87, 23),
(88, 23),
(89, 23),
(89, 17),
(89, 28),
(90, 23),
(91, 23),
(92, 23),
(93, 23),
(95, 23),
(96, 23),
(97, 23),
(97, 28),
(97, 22),
(98, 23),
(99, 23),
(100, 23),
(101, 23),
(102, 23),
(103, 23),
(104, 23),
(105, 23),
(106, 23),
(106, 22),
(107, 23),
(108, 23),
(109, 23),
(109, 17),
(109, 28),
(109, 22),
(110, 23),
(111, 23),
(112, 23),
(113, 23),
(114, 23),
(115, 23),
(116, 23),
(117, 23),
(118, 23),
(120, 23),
(121, 23),
(122, 23),
(123, 23),
(124, 23),
(125, 23),
(126, 23),
(127, 23),
(128, 23),
(129, 23),
(129, 22),
(130, 23),
(131, 23),
(132, 23),
(133, 23),
(134, 23),
(135, 23),
(135, 17),
(136, 23),
(137, 23),
(138, 23),
(139, 23),
(140, 23),
(141, 23),
(142, 23),
(143, 23),
(144, 23),
(145, 23),
(146, 23),
(147, 23),
(148, 23),
(148, 17),
(148, 28),
(149, 23),
(150, 23),
(150, 17),
(150, 28),
(151, 23),
(152, 23),
(153, 23),
(154, 23),
(155, 23),
(156, 23),
(157, 23),
(157, 17),
(157, 28),
(158, 23),
(158, 28),
(159, 23),
(160, 23),
(161, 23),
(161, 17),
(161, 28),
(162, 23),
(163, 23),
(164, 23),
(165, 23),
(165, 17),
(165, 28),
(165, 22),
(166, 23),
(167, 23),
(168, 23),
(169, 23),
(170, 23),
(170, 17),
(170, 28),
(171, 23),
(172, 23),
(173, 23),
(174, 23),
(175, 23),
(175, 18),
(175, 17),
(175, 28),
(175, 26),
(176, 23),
(177, 23),
(177, 18),
(177, 22),
(177, 26),
(178, 23),
(179, 23),
(180, 23),
(181, 23),
(181, 17),
(181, 28),
(182, 23),
(183, 23),
(184, 23),
(185, 23),
(186, 23),
(187, 23),
(188, 23),
(189, 23),
(190, 17),
(190, 28),
(190, 16),
(191, 17),
(191, 28),
(191, 16),
(192, 23),
(193, 23),
(194, 23),
(194, 22),
(194, 16),
(195, 23),
(196, 23),
(197, 23),
(198, 23),
(199, 23),
(200, 23),
(201, 23),
(202, 23),
(203, 23),
(204, 23),
(205, 23),
(206, 23),
(207, 23),
(208, 23),
(209, 23),
(210, 23),
(211, 23),
(212, 23),
(213, 23),
(214, 23),
(215, 23),
(216, 23),
(217, 23),
(218, 23),
(219, 23),
(220, 23),
(221, 23),
(222, 23),
(223, 23),
(224, 23),
(225, 23),
(226, 23),
(226, 17),
(227, 23),
(227, 17),
(228, 23),
(228, 17),
(229, 23),
(230, 23),
(231, 23),
(232, 23),
(233, 23),
(234, 23),
(235, 23),
(236, 23),
(237, 23),
(238, 23),
(239, 23),
(240, 23),
(240, 22),
(241, 23),
(241, 22),
(242, 23),
(243, 23),
(244, 23),
(245, 23),
(245, 22),
(246, 23),
(247, 23),
(248, 23),
(249, 23),
(250, 23),
(250, 18),
(250, 17),
(250, 28),
(250, 26),
(251, 23),
(251, 18),
(251, 26),
(252, 23),
(252, 18),
(252, 17),
(252, 28),
(252, 26),
(253, 23),
(253, 18),
(253, 17),
(253, 28),
(253, 26),
(254, 23),
(254, 18),
(254, 17),
(254, 28),
(254, 26),
(255, 23),
(255, 18),
(255, 17),
(255, 28),
(255, 26),
(256, 23),
(256, 18),
(256, 17),
(256, 28),
(256, 26),
(257, 23),
(257, 18),
(257, 17),
(257, 26),
(258, 23),
(258, 18),
(258, 26),
(259, 23),
(259, 18),
(259, 17),
(259, 28),
(259, 26),
(260, 23),
(260, 18),
(260, 28),
(260, 26),
(261, 23),
(262, 23),
(263, 23),
(263, 17),
(264, 23),
(264, 17),
(264, 28),
(264, 16),
(265, 23),
(266, 23),
(267, 23),
(267, 22),
(268, 23),
(269, 23),
(270, 23),
(271, 23),
(272, 23),
(273, 23),
(274, 23),
(275, 23),
(276, 23),
(277, 23),
(278, 23),
(278, 22),
(279, 23),
(280, 23),
(280, 22),
(281, 17),
(282, 17),
(282, 28),
(283, 17),
(283, 28),
(284, 17),
(285, 17),
(286, 23),
(287, 23),
(287, 18),
(287, 28),
(287, 26),
(288, 28),
(288, 26),
(289, 23),
(290, 28),
(291, 23),
(292, 17),
(292, 23),
(292, 28);

-- --------------------------------------------------------

--
-- Table structure for table `colors`
--

CREATE TABLE `colors` (
  `color_id` int(11) NOT NULL,
  `color_des` varchar(255) NOT NULL,
  `color_code` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `colors`
--

INSERT INTO `colors` (`color_id`, `color_des`, `color_code`) VALUES
(1, 'Priority(Yes)', '#52708F'),
(2, 'Today Last Date', '#FF003D'),
(3, 'Due date within 7 days', '#F14B0C'),
(4, 'Normal', '#000000');

-- --------------------------------------------------------

--
-- Table structure for table `remarks`
--

CREATE TABLE `remarks` (
  `remark_id` int(11) NOT NULL,
  `remark` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `remarks`
--

INSERT INTO `remarks` (`remark_id`, `remark`) VALUES
(7, 'Yet to Start'),
(8, 'In Process'),
(9, 'Hold'),
(10, 'Queries Pending'),
(11, 'Review Pending'),
(12, 'Complete');

-- --------------------------------------------------------

--
-- Table structure for table `stage`
--

CREATE TABLE `stage` (
  `stage_id` int(11) NOT NULL,
  `work_id` int(11) NOT NULL,
  `stage_num` int(11) NOT NULL,
  `stage_des` varchar(255) NOT NULL,
  `sub_work_id` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stage`
--

INSERT INTO `stage` (`stage_id`, `work_id`, `stage_num`, `stage_des`, `sub_work_id`) VALUES
(180, 16, 1, 'Receive', 17),
(181, 16, 2, 'Prepare', 17),
(182, 16, 3, 'Review', 17),
(183, 16, 4, 'Billing', 17),
(184, 16, 5, 'Return Data', 17),
(185, 17, 1, 'Receive', 0),
(186, 17, 2, 'Finalise Audit', 0),
(187, 17, 3, 'Prepare Report', 0),
(188, 17, 4, 'Generate UDIN', 0),
(189, 17, 5, 'Upload', 0),
(190, 17, 6, 'Print Report', 0),
(191, 17, 7, 'Receive Off Copy', 0),
(192, 17, 8, 'Billing', 0),
(193, 18, 1, 'Receive', 0),
(194, 18, 2, 'Finalise Audit', 0),
(195, 18, 3, 'Prepare Report', 0),
(196, 18, 4, 'Generate UDIN', 0),
(197, 18, 5, 'Print Report', 0),
(198, 18, 6, 'Sd Office Copy', 0),
(199, 18, 7, 'Billing', 0),
(200, 19, 1, 'Prepare', 0),
(201, 19, 2, 'Generate UDIN', 0),
(202, 19, 3, 'Print', 0),
(203, 19, 4, 'Billing', 0),
(204, 20, 1, 'Prepare', 0),
(205, 20, 2, 'Print', 0),
(206, 20, 3, 'Billing', 0),
(207, 20, 4, 'Send Copy', 0),
(208, 21, 1, 'Prepare', 0),
(209, 21, 2, 'Print', 0),
(210, 21, 3, 'Billing', 0),
(211, 21, 4, 'Send Copy', 0),
(212, 22, 1, 'Receive', 18),
(213, 22, 2, 'Prepare', 18),
(214, 22, 3, 'Review', 18),
(215, 22, 4, 'File', 18),
(216, 22, 5, 'Save Ack, Rtn, Challan', 18),
(217, 22, 6, 'Return Data', 18),
(218, 22, 7, 'Billing', 18),
(219, 22, 1, 'Receive', 19),
(220, 22, 2, 'Prepare', 19),
(221, 22, 3, 'Review', 19),
(222, 22, 4, 'File', 19),
(223, 22, 5, 'Save Ack, Rtn, Challan', 19),
(224, 22, 6, 'Return Data', 19),
(225, 22, 7, 'Billing', 19),
(226, 22, 1, 'Receive', 20),
(227, 22, 2, 'Prepare', 20),
(228, 22, 3, 'Review', 20),
(229, 22, 4, 'File', 20),
(230, 22, 5, 'Save Ack, Rtn, Challan', 20),
(231, 22, 6, 'Return Data', 20),
(232, 22, 7, 'Billing', 20),
(233, 22, 1, 'Receive', 21),
(234, 22, 2, 'Prepare', 21),
(235, 22, 3, 'Review', 21),
(236, 22, 4, 'File', 21),
(237, 22, 5, 'Save Ack, Rtn, Challan', 21),
(238, 22, 6, 'Return Data', 21),
(239, 22, 7, 'Billing', 21),
(240, 22, 1, 'Receive', 22),
(241, 22, 2, 'Prepare', 22),
(242, 22, 3, 'Review', 22),
(243, 22, 4, 'File', 22),
(244, 22, 5, 'Save Ack, Rtn, Challan', 22),
(245, 22, 6, 'Return Data', 22),
(246, 22, 7, 'Billing', 22),
(247, 22, 1, 'Receive', 23),
(248, 22, 2, 'Prepare', 23),
(249, 22, 3, 'Review', 23),
(250, 22, 4, 'File', 23),
(251, 22, 5, 'Save Ack, Rtn, Challan', 23),
(252, 22, 6, 'Return Data', 23),
(253, 22, 7, 'Billing', 23),
(254, 22, 1, 'Receive', 24),
(255, 22, 2, 'Prepare', 24),
(256, 22, 3, 'Review', 24),
(257, 22, 4, 'File', 24),
(258, 22, 5, 'Save Ack, Rtn, Challan', 24),
(259, 22, 6, 'Return Data', 24),
(260, 22, 7, 'Billing', 24),
(261, 22, 1, 'Receive', 25),
(262, 22, 2, 'Prepare', 25),
(263, 22, 3, 'Review', 25),
(264, 22, 4, 'File', 25),
(265, 22, 5, 'Billing', 25),
(266, 22, 1, 'Receive', 26),
(267, 22, 2, 'Prepare', 26),
(268, 22, 3, 'Review', 26),
(269, 22, 4, 'File', 26),
(270, 22, 5, 'Billing', 26),
(271, 22, 1, 'Receive', 27),
(272, 22, 2, 'Prepare', 27),
(273, 22, 3, 'Review', 27),
(274, 22, 4, 'Reply', 27),
(275, 22, 5, 'Billing', 27),
(276, 22, 6, 'Order', 27),
(277, 22, 1, 'Prepare', 28),
(278, 22, 2, 'Review', 28),
(279, 22, 3, 'Billing', 28),
(280, 23, 1, 'Receive', 29),
(281, 23, 2, 'Prepare Computation', 29),
(282, 23, 3, 'Prepare ITR', 29),
(283, 23, 4, 'E-verify', 29),
(284, 23, 5, 'Print/Mail', 29),
(285, 23, 6, 'Billing', 29),
(286, 23, 1, 'Receive', 30),
(287, 23, 2, 'Prepare', 30),
(288, 23, 3, 'File', 30),
(289, 23, 4, 'Billing', 30),
(290, 23, 1, 'Receive', 31),
(291, 23, 2, 'Prepare', 31),
(292, 23, 3, 'File', 31),
(293, 23, 4, 'Billing', 31),
(294, 23, 1, 'Receive', 32),
(295, 23, 2, 'Prepare', 32),
(296, 23, 3, 'Review', 32),
(297, 23, 4, 'Reply', 32),
(298, 23, 5, 'Billing', 32),
(299, 23, 6, 'Order', 32),
(300, 24, 1, 'Prepare', 0),
(301, 24, 2, 'Send to NSDL', 0),
(302, 24, 3, 'Billing', 0),
(303, 24, 4, 'Issue Date', 0),
(304, 25, 1, 'Prepare', 0),
(305, 25, 2, 'Send to NSDL', 0),
(306, 25, 3, 'Billing', 0),
(307, 25, 4, 'Issue Date', 0),
(308, 26, 1, 'Prepare', 33),
(309, 26, 2, 'Review', 33),
(310, 26, 3, 'Upload', 33),
(311, 26, 4, 'Generate UDIN', 33),
(312, 26, 5, 'Billing', 33),
(313, 26, 1, 'Prepare', 34),
(314, 26, 2, 'Review', 34),
(315, 26, 3, 'Upload', 34),
(316, 26, 4, 'Generate UDIN', 34),
(317, 26, 5, 'Billing', 34),
(318, 27, 1, 'Receive', 0),
(319, 27, 2, 'Prepare', 0),
(320, 27, 3, 'File/Upload', 0),
(321, 27, 4, 'Billing', 0),
(322, 27, 5, 'Download Certificate', 0),
(323, 27, 6, 'Mail Certificate', 0),
(324, 28, 1, 'Receive', 0),
(325, 28, 2, 'Prepare', 0),
(326, 28, 3, 'File/Upload', 0),
(327, 28, 4, 'Billing', 0),
(328, 28, 5, 'Download Certificate', 0),
(329, 28, 6, 'Mail Certificate', 0),
(330, 29, 1, 'Mail', 0),
(331, 30, 1, 'Prepare', 0),
(332, 30, 2, 'Billing', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sub_work`
--

CREATE TABLE `sub_work` (
  `sub_work_id` int(11) NOT NULL,
  `work_id` int(11) NOT NULL,
  `sub_work_des` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sub_work`
--

INSERT INTO `sub_work` (`sub_work_id`, `work_id`, `sub_work_des`) VALUES
(17, 16, 'Accounts'),
(18, 22, 'GSTR-3B'),
(19, 22, 'GSTR-1'),
(20, 22, 'CMP-08'),
(21, 22, 'GSTR-4'),
(22, 22, 'GSTTR-9'),
(23, 22, 'GSTR-9A'),
(24, 22, 'GSTR-9C'),
(25, 22, 'GST-REG'),
(26, 22, 'GST-Other'),
(27, 22, 'Notice'),
(28, 22, '2A Reconciliation'),
(29, 23, 'Return'),
(30, 23, 'Rectification'),
(31, 23, 'Revision'),
(32, 23, 'Notice'),
(33, 26, 'Annual Return'),
(34, 26, 'Others');

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
  `created_date` date default NULL,
  `completed_date` date DEFAULT NULL,
  `year` varchar(100) DEFAULT NULL,
  `sub_work_des` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_id`, `client_code`, `file_name`, `Initiator`, `work_des`, `period`, `task_priority`, `task_due_date`, `task_priority_position`, `task_status`, `created_date`, `completed_date`, `year`, `sub_work_des`) VALUES
(48, 'RKA-2030D', 'KEJRIWAL CRUSHER UDYOG', 'RKA', 'Accounts', 'Q1', 'Yes', '2020-07-25', 49, 'Finished', '2020-07-24', NULL, '2020-21', 'Accounts'),
(79, 'RKA-2011I', 'AMAR JINDAL', 'admin', 'GST', 'Q1', 'No', '2020-07-26', 80, 'Pending', '2020-07-26', NULL, '2020-21', 'GSTR-1'),
(80, 'RKA-2001F', 'ALOCHAN AGRAWAL HUF', 'admin', 'Tax Audit', 'Q1', 'No', '2020-07-26', 82, 'Pending', '2020-07-26', NULL, '2020-21', ''),
(81, 'RKA-OA3', 'GOPAL JI MAHAPRABHU AND CHANDRAHASINI MANDIR TRUST', 'admin', 'Accounts', 'Q2', 'No', '2020-08-19', 81, 'Pending', '2020-07-26', NULL, '2020-21', 'Accounts'),
(82, 'RKA-2030D', 'KEJRIWAL CRUSHER UDYOG', 'admin', 'Accounts', 'Q2', 'No', '2020-08-19', 83, 'Pending', '2020-07-26', NULL, '2020-21', 'Accounts'),
(83, 'SAKHI-SB', 'SAKHI BUILDCON', 'admin', 'Accounts', 'Q2', 'No', '2020-08-19', 84, 'Pending', '2020-07-26', NULL, '2020-21', 'Accounts'),
(84, 'RKA-CO1', 'AJAY INGOT ROLLING MILL PRIVATE LIMITED ', 'RKA', 'Tax Audit', '', 'Yes', '2020-07-26', 79, 'Pending', '2020-07-26', NULL, '2020-21', ''),
(85, 'RKA-2011K', 'ALOK JINDAL', 'RKA', 'Tax Audit', '', 'Yes', '2020-07-26', 85, 'Pending', '2020-07-26', NULL, '2020-21', ''),
(86, 'RKA-2011K', 'ALOK JINDAL', 'admin2', 'GST', 'Q3', 'Yes', '2020-08-29', 86, 'Pending', '2020-07-26', NULL, '2020-21', 'CMP-08'),
(87, 'RKA-CO8', 'MATESHWARI REALTORS PRIVATE LIMITED ', 'admin2', 'GST', 'Q3', 'Yes', '2020-08-29', 87, 'Pending', '2020-07-26', NULL, '2020-21', 'CMP-08'),
(88, 'RKA-2026D', 'NATWAR LAL AGRAWAL', 'admin2', 'GST', 'Q3', 'Yes', '2020-08-29', 88, 'Pending', '2020-07-26', NULL, '2020-21', 'CMP-08'),
(89, 'RKA-2011K', 'ALOK JINDAL', 'RKA', 'GST', '', 'Yes', '2020-08-28', 89, 'Pending', '2020-07-26', NULL, '2020-21', 'GSTR-4'),
(90, 'RKA-OWN13', 'SUMAN GUPTA', 'RKA', 'GST', '', 'Yes', '2020-08-28', 90, 'Pending', '2020-07-26', NULL, '2020-21', 'GSTR-4'),
(91, 'RKA-OWN19', 'ADARSH AGRAWAL', 'admin', 'ITR', 'Q1', 'No', '2020-08-27', 91, 'Pending', '2020-07-27', NULL, '2020-21', 'Return'),
(92, 'RKA-OA3', 'GOPAL JI MAHAPRABHU AND CHANDRAHASINI MANDIR TRUST', 'admin', 'Accounts', 'Q1', 'No', '2020-07-27', 92, 'Pending', '2020-07-27', NULL, '2020-21', 'Accounts');

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
(2, 'admin', 'a', '1234567890', 'd@f.bg', '1234', 'admin'),
(3, 'RKA', 'Ravi Agrawal', '8305648558', 'r@gmail.com', 'admin1234', 'admin'),
(6, 'c', 'c', '1234567899', 'c@gmail.com', 'c', 'user'),
(7, 'd', 'd', '1234567890', 'd@gmail.com', 'd', 'user'),
(8, 'karan', 'karan ag', '8305648559', 'k@gmail.vom', '1234', 'user'),
(9, 'admin2', 'ramu', '', '', '1234', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `work`
--

CREATE TABLE `work` (
  `work_id` int(11) NOT NULL,
  `work_des` varchar(255) NOT NULL,
  `isSubWork` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work`
--

INSERT INTO `work` (`work_id`, `work_des`, `isSubWork`) VALUES
(16, 'Accounts', 1),
(17, 'Tax Audit', 0),
(18, 'Stat. Audit', 0),
(19, 'Certificate', 0),
(20, 'CMA', 0),
(21, 'Project', 0),
(22, 'GST', 1),
(23, 'ITR', 1),
(24, 'PAN', 0),
(25, 'TAN', 0),
(26, 'ROC', 1),
(27, 'TDS-C', 0),
(28, 'TDS-R', 0),
(29, 'MAIL', 0),
(30, 'Others', 0);

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
  `remark` varchar(225) DEFAULT NULL,
  `assigned_date` date DEFAULT NULL,
  `sub_work_des` varchar(255) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work_book`
--

INSERT INTO `work_book` (`wb_id`, `task_id`, `stage_num`, `stage_des`, `assigned_to`, `due_date`, `stage_status`, `completed_date`, `remark`, `assigned_date`, `sub_work_des`) VALUES
(298, 48, 2, 'Receive', 'c', '2020-07-25', 'Finished', '2020-07-26', NULL, '2020-07-24', 'Accounts'),
(299, 48, 1, 'Prepare', 'c', '2020-07-25', 'Finished', '2020-07-26', NULL, '2020-07-24', 'Accounts'),
(300, 48, 3, 'Review', 'c', '2020-07-25', 'Finished', '2020-07-26', NULL, '2020-07-24', 'Accounts'),
(301, 48, 4, 'Billing', 'c', '2020-07-25', 'Finished', '2020-07-26', NULL, '2020-07-24', 'Accounts'),
(302, 48, 5, 'Return Data', 'c', '2020-07-25', 'Finished', '2020-07-26', NULL, '2020-07-24', 'Accounts'),
(1195, 79, 1, 'Receive', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-1'),
(1196, 79, 2, 'Prepare', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-1'),
(1197, 79, 3, 'Review', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-1'),
(1198, 79, 4, 'File', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-1'),
(1199, 79, 5, 'Save Ack, Rtn, Challan', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-1'),
(1200, 79, 6, 'Return Data', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-1'),
(1201, 79, 7, 'Billing', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-1'),
(1202, 80, 1, 'Receive', 'karan', '2020-07-26', 'Finished', '2020-07-26', NULL, '2020-07-26', ''),
(1203, 80, 2, 'Finalise Audit', 'karan', '2020-07-26', 'Finished', '2020-07-26', NULL, '2020-07-26', ''),
(1204, 80, 3, 'Prepare Report', 'karan', '2020-07-26', 'Finished', '2020-07-26', NULL, '2020-07-26', ''),
(1205, 80, 4, 'Generate UDIN', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1206, 80, 5, 'Upload', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1207, 80, 6, 'Print Report', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1208, 80, 7, 'Receive Off Copy', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1209, 80, 8, 'Billing', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1210, 81, 1, 'Receive', 'karan', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1211, 81, 2, 'Prepare', 'karan', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1212, 81, 3, 'Review', 'karan', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1213, 81, 4, 'Billing', 'karan', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1214, 81, 5, 'Return Data', 'karan', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1215, 82, 1, 'Receive', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1216, 82, 2, 'Prepare', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1217, 82, 3, 'Review', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1218, 82, 4, 'Billing', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1219, 82, 5, 'Return Data', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1220, 83, 1, 'Receive', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1221, 83, 2, 'Prepare', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1222, 83, 3, 'Review', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1223, 83, 4, 'Billing', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1224, 83, 5, 'Return Data', 'admin', '2020-08-19', 'Pending', NULL, NULL, '2020-07-26', 'Accounts'),
(1225, 84, 1, 'Receive', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1226, 84, 2, 'Finalise Audit', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1227, 84, 3, 'Prepare Report', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1228, 84, 4, 'Generate UDIN', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1229, 84, 5, 'Upload', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1230, 84, 6, 'Print Report', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1231, 84, 7, 'Receive Off Copy', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1232, 84, 8, 'Billing', 'karan', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1233, 85, 1, 'Receive', 'RKA', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1234, 85, 2, 'Finalise Audit', 'RKA', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1235, 85, 3, 'Prepare Report', 'RKA', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1236, 85, 4, 'Generate UDIN', 'RKA', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1237, 85, 5, 'Upload', 'RKA', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1238, 85, 6, 'Print Report', 'RKA', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1239, 85, 7, 'Receive Off Copy', 'RKA', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1240, 85, 8, 'Billing', 'RKA', '2020-07-26', 'Pending', NULL, NULL, '2020-07-26', ''),
(1241, 86, 1, 'Receive', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1242, 86, 2, 'Prepare', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1243, 86, 3, 'Review', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1244, 86, 4, 'File', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1245, 86, 5, 'Save Ack, Rtn, Challan', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1246, 86, 6, 'Return Data', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1247, 86, 7, 'Billing', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1248, 87, 1, 'Receive', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1249, 87, 2, 'Prepare', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1250, 87, 3, 'Review', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1251, 87, 4, 'File', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1252, 87, 5, 'Save Ack, Rtn, Challan', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1253, 87, 6, 'Return Data', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1254, 87, 7, 'Billing', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1255, 88, 1, 'Receive', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1256, 88, 2, 'Prepare', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1257, 88, 3, 'Review', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1258, 88, 4, 'File', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1259, 88, 5, 'Save Ack, Rtn, Challan', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1260, 88, 6, 'Return Data', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1261, 88, 7, 'Billing', 'admin2', '2020-08-29', 'Pending', NULL, NULL, '2020-07-26', 'CMP-08'),
(1262, 89, 1, 'Receive', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1263, 89, 2, 'Prepare', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1264, 89, 3, 'Review', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1265, 89, 4, 'File', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1266, 89, 5, 'Save Ack, Rtn, Challan', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1267, 89, 6, 'Return Data', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1268, 89, 7, 'Billing', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1269, 90, 1, 'Receive', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1270, 90, 2, 'Prepare', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1271, 90, 3, 'Review', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1272, 90, 4, 'File', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1273, 90, 5, 'Save Ack, Rtn, Challan', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1274, 90, 6, 'Return Data', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1275, 90, 7, 'Billing', 'RKA', '2020-08-28', 'Pending', NULL, NULL, '2020-07-26', 'GSTR-4'),
(1276, 91, 1, 'Receive', 'admin', '2020-08-27', 'Pending', NULL, NULL, '2020-07-27', 'Return'),
(1277, 91, 2, 'Prepare Computation', 'admin', '2020-08-27', 'Pending', NULL, NULL, '2020-07-27', 'Return'),
(1278, 91, 3, 'Prepare ITR', 'admin', '2020-08-27', 'Pending', NULL, NULL, '2020-07-27', 'Return'),
(1279, 91, 4, 'E-verify', 'admin', '2020-08-27', 'Pending', NULL, NULL, '2020-07-27', 'Return'),
(1280, 91, 5, 'Print/Mail', 'admin', '2020-08-27', 'Pending', NULL, NULL, '2020-07-27', 'Return'),
(1281, 91, 6, 'Billing', 'admin', '2020-08-27', 'Pending', NULL, NULL, '2020-07-27', 'Return'),
(1282, 92, 1, 'Receive', 'admin', '2020-07-27', 'Pending', NULL, NULL, '2020-07-27', 'Accounts'),
(1283, 92, 2, 'Prepare', 'admin', '2020-07-27', 'Pending', NULL, NULL, '2020-07-27', 'Accounts'),
(1284, 92, 3, 'Review', 'admin', '2020-07-27', 'Pending', NULL, NULL, '2020-07-27', 'Accounts'),
(1285, 92, 4, 'Billing', 'admin', '2020-07-27', 'Pending', NULL, NULL, '2020-07-27', 'Accounts'),
(1286, 92, 5, 'Return Data', 'admin', '2020-07-27', 'Pending', NULL, NULL, '2020-07-27', 'Accounts');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `client_work`
--
ALTER TABLE `client_work`
  ADD KEY `fk1` (`client_id`),
  ADD KEY `fk2` (`work_id`);

--
-- Indexes for table `colors`
--
ALTER TABLE `colors`
  ADD PRIMARY KEY (`color_id`);

--
-- Indexes for table `remarks`
--
ALTER TABLE `remarks`
  ADD PRIMARY KEY (`remark_id`);

--
-- Indexes for table `stage`
--
ALTER TABLE `stage`
  ADD PRIMARY KEY (`stage_id`),
  ADD KEY `work_id` (`work_id`);

--
-- Indexes for table `sub_work`
--
ALTER TABLE `sub_work`
  ADD PRIMARY KEY (`sub_work_id`),
  ADD KEY `fk` (`work_id`);

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
  ADD PRIMARY KEY (`wb_id`),
  ADD KEY `work_book_fk` (`task_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=293;

--
-- AUTO_INCREMENT for table `colors`
--
ALTER TABLE `colors`
  MODIFY `color_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `remarks`
--
ALTER TABLE `remarks`
  MODIFY `remark_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `stage`
--
ALTER TABLE `stage`
  MODIFY `stage_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=333;

--
-- AUTO_INCREMENT for table `sub_work`
--
ALTER TABLE `sub_work`
  MODIFY `sub_work_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `work`
--
ALTER TABLE `work`
  MODIFY `work_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `work_book`
--
ALTER TABLE `work_book`
  MODIFY `wb_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1287;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client_work`
--
ALTER TABLE `client_work`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk2` FOREIGN KEY (`work_id`) REFERENCES `work` (`work_id`) ON DELETE CASCADE;

--
-- Constraints for table `stage`
--
ALTER TABLE `stage`
  ADD CONSTRAINT `stage_ibfk_1` FOREIGN KEY (`work_id`) REFERENCES `work` (`work_id`) ON DELETE CASCADE;

--
-- Constraints for table `sub_work`
--
ALTER TABLE `sub_work`
  ADD CONSTRAINT `fk` FOREIGN KEY (`work_id`) REFERENCES `work` (`work_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
