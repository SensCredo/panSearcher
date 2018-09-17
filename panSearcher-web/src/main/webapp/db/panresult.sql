/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80003
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80003
File Encoding         : 65001

Date: 2018-06-08 19:19:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for panresult
-- ----------------------------
DROP TABLE IF EXISTS `panresult`;
CREATE TABLE `panresult` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `searchItem` varchar(50) NOT NULL,
  `searchDeepth` int(3) NOT NULL,
  `sourceUrl` varchar(50) NOT NULL,
  `panUrl` varchar(50) NOT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
