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

 Date: 10/05/2023 15:12:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sport_gps
-- ----------------------------
DROP TABLE IF EXISTS `sport_gps`;
CREATE TABLE `sport_gps` (
  `gpsid` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL,
  `cpid` varchar(255) DEFAULT NULL,
  `gpstime` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `sumtime` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `groupzb` varchar(255) DEFAULT NULL,
  `cpname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sport_gps
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
