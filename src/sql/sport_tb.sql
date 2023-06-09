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

 Date: 09/06/2023 09:40:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sport_tb
-- ----------------------------
DROP TABLE IF EXISTS `sport_tb`;
CREATE TABLE `sport_tb` (
  `wxm` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `zb` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gj` varchar(255) DEFAULT NULL,
  `sfzhm` varchar(255) DEFAULT NULL,
  `jjlxr` varchar(255) DEFAULT NULL,
  `jjlxrdh` varchar(255) DEFAULT NULL,
  `yfcm` varchar(255) DEFAULT NULL,
  `zz` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `sportname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
