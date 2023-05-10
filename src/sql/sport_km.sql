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

 Date: 10/05/2023 15:13:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sport_km
-- ----------------------------
DROP TABLE IF EXISTS `sport_km`;
CREATE TABLE `sport_km` (
  `id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `valu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sport_km
-- ----------------------------
BEGIN;
INSERT INTO `sport_km` (`id`, `name`, `label`, `valu`) VALUES ('afeffbe6-0a49-4008-94b5-f6181bf1d97c', '30km', '30km', '30km');
INSERT INTO `sport_km` (`id`, `name`, `label`, `valu`) VALUES ('7f9fca65-ce99-4a37-9f3e-52dce90e1630', '50km', '50km', '50km');
INSERT INTO `sport_km` (`id`, `name`, `label`, `valu`) VALUES ('006fce23-e622-41d4-8565-311ca179a9af', '100km', '100km', '100km');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
