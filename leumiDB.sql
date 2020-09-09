-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 09, 2020 at 01:11 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `leumi`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `ID` int(11) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `AccountNumber` int(11) NOT NULL,
  `Balance` float NOT NULL,
  `Status` varchar(10) NOT NULL,
  `ClientID` int(11) NOT NULL,
  `CardCode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`ID`, `FullName`, `AccountNumber`, `Balance`, `Status`, `ClientID`, `CardCode`) VALUES
(1, 'סאמר אלעטאונה', 1234, 1112250, 'ACTIVE', 26480996, 6666),
(2, 'לירון אשואל', 555, 1371, 'ACTIVE', 2648845, 2312),
(3, 'מאיר', 6555448, 33021, 'ACTIVE', 254684, 2233),
(4, 'בדיקה', 54846, -28786, 'ACTIVE', 222222, 1212),
(5, 'test', 1354, 24289, 'ACTIVE', 26480996, 1111),
(6, 'test2', 5555, 1111970, 'ACTIVE', 123123, 2222),
(7, 'www', 2312, 33986, 'ACTIVE', 123123, 1111),
(8, 'בדיקה', 32548, 170269, 'ACTIVE', 99999, 2233),
(9, 'רפי', 123654, 108, 'ACTIVE', 123456, 2222),
(10, 'asd', 12333, 81, 'ACTIVE', 123, 3333),
(11, 'sdfgsdfg', 4444, 333, 'ACTIVE', 234234, 1222),
(12, 'asdasd', 123123, -277, 'ACTIVE', 123123, 222),
(13, 'zzz', 333, 0, 'ACTIVE', 111, 222);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `ClientID` int(11) NOT NULL,
  `Action` varchar(20) NOT NULL,
  `Amount` float NOT NULL,
  `Date` datetime NOT NULL,
  `Location` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`ClientID`, `Action`, `Amount`, `Date`, `Location`) VALUES
(1, 'WITHDRAW', 100, '2015-03-31 00:00:00', 'ATM'),
(6, 'WITHDRAW', 200, '2020-06-19 00:00:00', 'ATM'),
(1, 'WITHDRAW', 100, '2020-06-19 00:00:00', 'ATM'),
(2, 'WITHDRAW', 1000, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 2000, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 2000, '2020-06-19 00:00:00', 'BANK'),
(2, 'DISPOSE', 50, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 12, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 12, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 22, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 22, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 1222, '2020-06-19 00:00:00', 'BANK'),
(1, 'WITHDRAW', 23, '2020-06-19 00:00:00', 'BANK'),
(2, 'DISPOSE', 22, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 1234, '2020-06-19 00:00:00', 'BANK'),
(7, 'DISPOSE', 444, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 1000, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 1, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 222, '2020-06-19 00:00:00', 'BANK'),
(1, 'WITHDRAW', 1000, '2020-06-19 00:00:00', 'BANK'),
(7, 'DISPOSE', 1500, '2020-06-19 00:00:00', 'BANK'),
(7, 'DISPOSE', 1500, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 111, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(4, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 2000, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 111, '2020-06-19 00:00:00', 'BANK'),
(1, 'WITHDRAW', 2000, '2020-06-19 00:00:00', 'BANK'),
(1, 'WITHDRAW', 2000, '2020-06-19 00:00:00', 'BANK'),
(1, 'WITHDRAW', 2000, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 2000, '2020-06-19 00:00:00', 'BANK'),
(3, 'WITHDRAW', 22, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(2, 'WITHDRAW', 2000, '2020-06-19 00:00:00', 'BANK'),
(2, 'DISPOSE', 500, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 400, '2020-06-19 00:00:00', 'BANK'),
(2, 'DISPOSE', 500, '2020-06-19 00:00:00', 'BANK'),
(3, 'WITHDRAW', 400, '2020-06-19 00:00:00', 'BANK'),
(5, 'DISPOSE', 500, '2020-06-19 00:00:00', 'BANK'),
(6, 'DISPOSE', 234, '2020-06-19 00:00:00', 'BANK'),
(3, 'WITHDRAW', 123, '2020-06-19 00:00:00', 'BANK'),
(4, 'WITHDRAW', 123, '2020-06-19 00:00:00', 'BANK'),
(7, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(7, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(3, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(9, 'DISPOSE', 1000, '2020-06-19 00:00:00', 'BANK'),
(2, 'DISPOSE', 222, '2020-06-19 00:00:00', 'BANK'),
(9, 'WITHDRAW', 900, '2020-06-19 00:00:00', 'BANK'),
(9, 'DISPOSE', 2000, '2020-06-19 00:00:00', 'BANK'),
(1, 'WITHDRAW', 1234, '2020-06-19 00:00:00', 'BANK'),
(3, 'DISPOSE', 29975, '2020-06-19 00:00:00', 'BANK'),
(8, 'WITHDRAW', 10000, '2020-06-19 00:00:00', 'BANK'),
(7, 'DISPOSE', 29975, '2020-06-19 00:00:00', 'BANK'),
(3, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(8, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(4, 'WITHDRAW', 33333, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 100, '2020-06-19 00:00:00', 'ATM'),
(6, 'WITHDRAW', 200, '2020-06-19 00:00:00', 'ATM'),
(1, 'DISPOSE', 1, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 100, '2020-06-19 00:00:00', 'ATM'),
(1, 'WITHDRAW', 100, '2020-06-19 00:00:00', 'ATM'),
(1, 'WITHDRAW', 200, '2020-06-19 00:00:00', 'ATM'),
(6, 'WITHDRAW', 200, '2020-06-19 00:00:00', 'ATM'),
(3, 'DISPOSE', 1, '2020-06-19 00:00:00', 'BANK'),
(8, 'DISPOSE', 23, '2020-06-19 00:00:00', 'BANK'),
(5, 'DISPOSE', 222, '2020-06-19 00:00:00', 'BANK'),
(5, 'WITHDRAW', 222, '2020-06-19 00:00:00', 'BANK'),
(9, 'DISPOSE', 222, '2020-06-19 00:00:00', 'BANK'),
(4, 'WITHDRAW', 222, '2020-06-19 00:00:00', 'BANK'),
(7, 'DISPOSE', 222, '2020-06-19 00:00:00', 'BANK'),
(2, 'DISPOSE', 222, '2020-06-19 00:00:00', 'BANK'),
(9, 'WITHDRAW', 3213, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 3213, '2020-06-19 00:00:00', 'BANK'),
(3, 'DISPOSE', 222, '2020-06-19 00:00:00', 'BANK'),
(6, 'DISPOSE', 222, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 3213, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 100, '2020-06-19 00:00:00', 'ATM'),
(6, 'DISPOSE', 5000, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 100, '2020-06-19 00:00:00', 'ATM'),
(9, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(8, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(4, 'WITHDRAW', 1231, '2020-06-19 00:00:00', 'BANK'),
(1, 'WITHDRAW', 123, '2020-06-19 00:00:00', 'BANK'),
(1, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 200, '2020-06-19 00:00:00', 'ATM'),
(1, 'DISPOSE', 123, '2020-06-19 00:00:00', 'BANK'),
(1, 'WITHDRAW', 123, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 200, '2020-06-19 00:00:00', 'ATM'),
(1, 'WITHDRAW', 123, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 200, '2020-06-19 00:00:00', 'ATM'),
(1, 'WITHDRAW', 123, '2020-06-19 00:00:00', 'BANK'),
(6, 'WITHDRAW', 200, '2020-06-19 00:00:00', 'ATM'),
(5, 'WITHDRAW', 100, '2020-07-02 00:00:00', 'ATM'),
(5, 'WITHDRAW', 200, '2020-07-03 00:00:00', 'ATM'),
(3, 'WITHDRAW', 200, '2020-07-03 00:00:00', 'BANK'),
(3, 'DISPOSE', 122, '2020-07-03 00:00:00', 'BANK'),
(10, 'DISPOSE', 200, '2020-07-17 00:00:00', 'BANK'),
(10, 'WITHDRAW', 120, '2020-07-17 00:00:00', 'BANK'),
(9, 'DISPOSE', 200, '2020-07-17 00:00:00', 'BANK'),
(5, 'WITHDRAW', 12, '2020-07-23 00:00:00', 'BANK'),
(7, 'WITHDRAW', 12, '2020-07-23 00:00:00', 'BANK'),
(9, 'WITHDRAW', 12, '2020-07-23 00:00:00', 'BANK'),
(9, 'WITHDRAW', 12, '2020-07-23 00:00:00', 'BANK'),
(1, 'DISPOSE', 1111110, '2020-07-24 00:00:00', 'BANK'),
(6, 'DISPOSE', 1111110, '2020-07-24 00:00:00', 'BANK'),
(12, 'WITHDRAW', 100, '2020-07-24 00:00:00', 'ATM'),
(13, 'DISPOSE', 200, '2020-07-24 00:00:00', 'BANK'),
(2, 'DISPOSE', 288, '2020-07-24 00:00:00', 'BANK'),
(9, 'DISPOSE', 100, '2020-07-24 00:00:00', 'BANK'),
(9, 'DISPOSE', 100, '2020-07-24 00:00:00', 'BANK'),
(12, 'WITHDRAW', 200, '2020-08-01 00:00:00', 'BANK'),
(13, 'WITHDRAW', 200, '2020-08-01 00:00:00', 'BANK'),
(13, 'WITHDRAW', 300, '2020-08-01 00:00:00', 'BANK'),
(13, 'DISPOSE', 299, '2020-08-01 00:00:00', 'BANK'),
(5, 'WITHDRAW', 100, '2020-09-09 00:00:00', 'ATM');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `Permission` varchar(10) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `UserName`, `Permission`, `Password`) VALUES
(1, 'samer', 'Admin', '1234'),
(2, 'Avi', 'User', '111'),
(3, 'samer2', 'User', '1111'),
(4, 'user1', 'User', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
