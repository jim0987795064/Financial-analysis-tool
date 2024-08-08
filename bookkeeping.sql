-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2021-06-22 03:53:39
-- 伺服器版本： 10.4.19-MariaDB
-- PHP 版本： 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫: `bookkeeping`
--

-- --------------------------------------------------------

--
-- 資料表結構 `count`
--

CREATE TABLE `count` (
  `year` int(20) DEFAULT NULL,
  `month` int(20) DEFAULT NULL,
  `date` int(20) DEFAULT NULL,
  `income` int(20) DEFAULT NULL,
  `outcome` int(20) DEFAULT NULL,
  `rest` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 傾印資料表的資料 `count`
--

INSERT INTO `count` (`year`, `month`, `date`, `income`, `outcome`, `rest`) VALUES
(2000, 9, 1, 1000, 3000, -2000),
(2000, 9, 2, 1000, 3000, -2000),
(2000, 9, 30, 10000, 5000, 5000),
(2000, 9, 22, 8000, 3000, 5000),
(2000, 9, 8, 10000, 100, 9900);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
