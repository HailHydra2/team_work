/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : team_work

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-04-19 15:30:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_data`
-- ----------------------------
DROP TABLE IF EXISTS `account_data`;
CREATE TABLE `account_data` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL,
  `experience_value` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `focus_num` int(11) NOT NULL,
  `question_num` int(11) NOT NULL,
  `response_num` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_data
-- ----------------------------

-- ----------------------------
-- Table structure for `attention`
-- ----------------------------
DROP TABLE IF EXISTS `attention`;
CREATE TABLE `attention` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `flag` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `attention_user_id` (`user_id`),
  KEY `attention_question_id` (`question_id`),
  CONSTRAINT `attention_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `attention_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attention
-- ----------------------------
INSERT INTO `attention` VALUES ('1', '1', '1', '2020-10-21 20:19:08', '1');
INSERT INTO `attention` VALUES ('2', '1', '2', '2020-10-21 20:19:08', '1');

-- ----------------------------
-- Table structure for `block`
-- ----------------------------
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `block_name` varchar(50) NOT NULL,
  `key_word` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of block
-- ----------------------------

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('1', 'content1');
INSERT INTO `content` VALUES ('2', 'content2');
INSERT INTO `content` VALUES ('3', 'content3');

-- ----------------------------
-- Table structure for `like`
-- ----------------------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `response_id` int(11) NOT NULL,
  `flag` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `like_response_id` (`response_id`),
  CONSTRAINT `like_response_id` FOREIGN KEY (`response_id`) REFERENCES `response` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of like
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` int(11) NOT NULL,
  `way` varchar(30) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `message_object_id` (`object_id`),
  CONSTRAINT `message_object_id` FOREIGN KEY (`object_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `author_id` int(11) NOT NULL,
  `response_num` int(11) NOT NULL DEFAULT '0',
  `report_num` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `question_author_id` (`author_id`),
  KEY `question_content_id` (`content_id`),
  CONSTRAINT `question_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  CONSTRAINT `question_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '1', '5', '0', '2020-04-07 21:31:21', '2');
INSERT INTO `question` VALUES ('2', '1', '1', '0', '2020-04-14 21:31:41', '1');

-- ----------------------------
-- Table structure for `question_title`
-- ----------------------------
DROP TABLE IF EXISTS `question_title`;
CREATE TABLE `question_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `title_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `title_question_id` (`question_id`),
  KEY `question_title_id` (`title_id`),
  CONSTRAINT `title_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `question_title_id` FOREIGN KEY (`title_id`) REFERENCES `title` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_title
-- ----------------------------

-- ----------------------------
-- Table structure for `report_question`
-- ----------------------------
DROP TABLE IF EXISTS `report_question`;
CREATE TABLE `report_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportor_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `flag` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `report_question_id` (`question_id`),
  KEY `question_reportor_id` (`reportor_id`),
  CONSTRAINT `question_reportor_id` FOREIGN KEY (`reportor_id`) REFERENCES `user` (`id`),
  CONSTRAINT `report_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_question
-- ----------------------------

-- ----------------------------
-- Table structure for `report_response`
-- ----------------------------
DROP TABLE IF EXISTS `report_response`;
CREATE TABLE `report_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reportor_id` int(11) NOT NULL,
  `response_id` int(11) NOT NULL,
  `flag` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `response_reportor_id` (`reportor_id`),
  KEY `report_response_id` (`response_id`),
  CONSTRAINT `report_response_id` FOREIGN KEY (`response_id`) REFERENCES `response` (`id`),
  CONSTRAINT `response_reportor_id` FOREIGN KEY (`reportor_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_response
-- ----------------------------

-- ----------------------------
-- Table structure for `response`
-- ----------------------------
DROP TABLE IF EXISTS `response`;
CREATE TABLE `response` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `content_id` int(11) NOT NULL,
  `like_num` int(11) NOT NULL DEFAULT '0',
  `dislike_num` int(11) NOT NULL DEFAULT '0',
  `report_num` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `response_author_id` (`author_id`),
  KEY `response_question_id` (`question_id`),
  KEY `response_content_id` (`content_id`),
  CONSTRAINT `response_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `response_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  CONSTRAINT `response_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of response
-- ----------------------------
INSERT INTO `response` VALUES ('1', '1', '1', '1', '3', '2', '72', '2020-04-22 21:32:38');
INSERT INTO `response` VALUES ('2', '2', '1', '2', '1', '32', '18', '2020-04-01 21:33:11');

-- ----------------------------
-- Table structure for `reward`
-- ----------------------------
DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(30) NOT NULL,
  `reward_num` double NOT NULL,
  `apply_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reward_user_id` (`user_id`),
  CONSTRAINT `reward_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reward
-- ----------------------------
INSERT INTO `reward` VALUES ('1', '1', 'SyntheticTest', '10', '2020-04-22 21:06:37');
INSERT INTO `reward` VALUES ('2', '1', 'ServiceTime', '21', '2020-04-15 21:07:39');
INSERT INTO `reward` VALUES ('3', '1', 'SyntheticTest', '2', '2020-03-12 01:12:42');
INSERT INTO `reward` VALUES ('4', '1', 'SyntheticTest', '13', '2020-05-31 21:13:13');

-- ----------------------------
-- Table structure for `title`
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of title
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `id_card` varchar(20) NOT NULL,
  `identity` varchar(10) NOT NULL DEFAULT '学生',
  `phone_num` varchar(12) DEFAULT NULL,
  `account_data_id` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`),
  KEY `user_account_data_id` (`account_data_id`),
  CONSTRAINT `user_account_data_id` FOREIGN KEY (`account_data_id`) REFERENCES `account_data` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '221701421', '1234', 'wsh', '123', 'student', null, null);
