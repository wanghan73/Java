/*
Navicat MySQL Data Transfer

Source Server         : xiangmu
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : life_assistant

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2018-01-22 16:56:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `accountId` int(11) NOT NULL,
  `account_money` decimal(10,0) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`accountId`),
  KEY `userId` (`userId`),
  CONSTRAINT `users` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1000', '501199', '1');
INSERT INTO `account` VALUES ('1014', '31001', '3');
INSERT INTO `account` VALUES ('2000', '100100', '2');

-- ----------------------------
-- Table structure for `account_log`
-- ----------------------------
DROP TABLE IF EXISTS `account_log`;
CREATE TABLE `account_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL,
  `transfer_account` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  `log_time` datetime NOT NULL,
  `money` decimal(10,0) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `account` (`accountId`),
  CONSTRAINT `account` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_log
-- ----------------------------
INSERT INTO `account_log` VALUES ('10001', '1014', '370', '转入', '2018-01-16 15:50:17', '730');
INSERT INTO `account_log` VALUES ('10002', '2000', '1000', '转出', '2018-01-03 16:08:27', '5000');
INSERT INTO `account_log` VALUES ('10003', '1000', '4900', '转入', '2018-01-12 16:12:47', '7800');
INSERT INTO `account_log` VALUES ('10004', '1014', '1014', '转入', '2018-01-19 17:23:41', '1000');
INSERT INTO `account_log` VALUES ('10005', '1014', '2000', '转出', '2018-01-19 17:25:04', '100');
INSERT INTO `account_log` VALUES ('10006', '2000', '1014', '转入', '2018-01-19 17:25:04', '100');
INSERT INTO `account_log` VALUES ('10007', '1000', '1000', '转入', '2018-01-19 21:12:28', '1000');
INSERT INTO `account_log` VALUES ('10008', '1000', '1014', '转出', '2018-01-19 21:12:51', '100');
INSERT INTO `account_log` VALUES ('10009', '1014', '1000', '转入', '2018-01-19 21:12:52', '100');
INSERT INTO `account_log` VALUES ('10010', '1000', '1000', '转入', '2018-01-20 16:55:07', '200');
INSERT INTO `account_log` VALUES ('10011', '1000', '1000', '转入', '2018-01-21 19:36:38', '100');
INSERT INTO `account_log` VALUES ('10012', '1000', '1014', '转出', '2018-01-22 09:40:11', '1');
INSERT INTO `account_log` VALUES ('10013', '1014', '1000', '转入', '2018-01-22 09:40:11', '1');

-- ----------------------------
-- Table structure for `memorandum`
-- ----------------------------
DROP TABLE IF EXISTS `memorandum`;
CREATE TABLE `memorandum` (
  `memorandumId` int(11) NOT NULL AUTO_INCREMENT,
  `memorandumTitle` varchar(50) NOT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(50) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`memorandumId`),
  KEY `users1` (`userId`),
  CONSTRAINT `users1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of memorandum
-- ----------------------------
INSERT INTO `memorandum` VALUES ('1', '约会', '2018-02-14 15:58:57', '看电影', '1');
INSERT INTO `memorandum` VALUES ('2', '面试', '2018-01-16 16:01:18', '考研面试', '1');
INSERT INTO `memorandum` VALUES ('3', '考试', '2018-01-21 19:32:49', 'java考试', '1');
INSERT INTO `memorandum` VALUES ('4', '逛街', '2018-01-21 19:56:04', 'maiyifma买衣服', '1');

-- ----------------------------
-- Table structure for `ranking`
-- ----------------------------
DROP TABLE IF EXISTS `ranking`;
CREATE TABLE `ranking` (
  `rankingId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `integral` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`rankingId`),
  KEY `users2` (`userId`),
  CONSTRAINT `users2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2018020 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ranking
-- ----------------------------
INSERT INTO `ranking` VALUES ('2018001', '1', '5000', '2018-01-18 16:02:11');
INSERT INTO `ranking` VALUES ('2018002', '3', '10002', '2018-01-10 16:14:03');
INSERT INTO `ranking` VALUES ('2018003', '3', '0', '2018-01-19 14:28:18');
INSERT INTO `ranking` VALUES ('2018004', '3', '2', '2018-01-19 14:34:43');
INSERT INTO `ranking` VALUES ('2018005', '3', '3', '2018-01-19 16:18:16');
INSERT INTO `ranking` VALUES ('2018006', '3', '0', '2018-01-19 17:46:31');
INSERT INTO `ranking` VALUES ('2018007', '3', '2', '2018-01-19 17:55:30');
INSERT INTO `ranking` VALUES ('2018008', '3', '1', '2018-01-19 20:30:35');
INSERT INTO `ranking` VALUES ('2018009', '1', '0', '2018-01-19 21:15:30');
INSERT INTO `ranking` VALUES ('2018012', '1', '0', '2018-01-21 19:28:45');
INSERT INTO `ranking` VALUES ('2018013', '1', '3', '2018-01-21 19:29:19');
INSERT INTO `ranking` VALUES ('2018014', '1', '1', '2018-01-21 19:40:20');
INSERT INTO `ranking` VALUES ('2018015', '1', '0', '2018-01-21 19:43:16');
INSERT INTO `ranking` VALUES ('2018016', '1', '0', '2018-01-21 19:44:49');
INSERT INTO `ranking` VALUES ('2018017', '17', '0', '2018-01-21 20:00:35');
INSERT INTO `ranking` VALUES ('2018018', '17', '6', '2018-01-21 20:00:50');
INSERT INTO `ranking` VALUES ('2018019', '17', '3', '2018-01-21 20:08:05');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `userPassword` varchar(50) NOT NULL,
  `realName` varchar(50) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'wanghan', '333333', '含含', '17853421111', '曲阜', '2018-01-16 11:54:07');
INSERT INTO `users` VALUES ('2', '小小', '123123', '哈哈', '4567890', '曲阜市', '2018-01-15 16:05:34');
INSERT INTO `users` VALUES ('3', '大大', '111111', '乐乐', '222222', '济宁', '2018-01-09 16:06:09');
INSERT INTO `users` VALUES ('17', 'admin', 'admin', '哈哈', '17853711111', 'niningn济宁', '2018-01-21 19:58:30');
