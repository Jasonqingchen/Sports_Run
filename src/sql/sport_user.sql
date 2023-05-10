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

 Date: 10/05/2023 15:13:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sport_user
-- ----------------------------
DROP TABLE IF EXISTS `sport_user`;
CREATE TABLE `sport_user` (
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `min` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `sumtime` varchar(255) DEFAULT NULL,
  `issignin` varchar(255) DEFAULT NULL,
  `groupzb` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sport_user
-- ----------------------------
BEGIN;
INSERT INTO `sport_user` (`name`, `sex`, `phone`, `bz`, `id`, `number`, `min`, `status`, `sumtime`, `issignin`, `groupzb`) VALUES ('刘清臣', '男', '13488604286', '', '3a98f64e5e044f56a2f940e122c5a2eb', 'M1579', NULL, '比赛中', NULL, '未签到', '100km');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
