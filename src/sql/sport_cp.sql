/*
 Navicat Premium Data Transfer

 Source Server         : 文静的滑降绳
 Source Server Type    : MySQL
 Source Server Version : 50562 (5.5.62-log)
 Source Host           : 1.12.238.58:3306
 Source Schema         : myadmin

 Target Server Type    : MySQL
 Target Server Version : 50562 (5.5.62-log)
 File Encoding         : 65001

 Date: 10/05/2023 15:12:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sport_cp
-- ----------------------------
DROP TABLE IF EXISTS `sport_cp`;
CREATE TABLE `sport_cp` (
  `id` varchar(255) DEFAULT NULL,
  `cpname` varchar(255) DEFAULT NULL,
  `cpendtime` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  `indexid` varchar(255) DEFAULT NULL,
  `gpstime` varchar(255) DEFAULT NULL,
  `sumtime` varchar(255) DEFAULT NULL,
  `sw` varchar(255) DEFAULT NULL,
  `cpkm` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sport_cp
-- ----------------------------
BEGIN;
INSERT INTO `sport_cp` (`id`, `cpname`, `cpendtime`, `userid`, `bz`, `starttime`, `endtime`, `indexid`, `gpstime`, `sumtime`, `sw`, `cpkm`) VALUES ('d1db8ed0d88b4a4e96c969b11712b923', 'cp1', '2023-05-08 14:53:19', '0', NULL, '2023-05-08 14:54:00', '2023-05-08 14:54:03', '0', NULL, NULL, NULL, '10');
INSERT INTO `sport_cp` (`id`, `cpname`, `cpendtime`, `userid`, `bz`, `starttime`, `endtime`, `indexid`, `gpstime`, `sumtime`, `sw`, `cpkm`) VALUES ('10d5fe1604ac411c80e4840ec0ba3064', 'cp2', '2023-05-08 14:53:29', '0', NULL, '2023-05-08 14:54:00', '2023-05-08 14:54:03', '1', NULL, NULL, NULL, '12.5');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
