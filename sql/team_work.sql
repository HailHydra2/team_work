/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : team_work

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-05-28 10:56:37
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
) ENGINE=InnoDB AUTO_INCREMENT=469 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_data
-- ----------------------------
INSERT INTO `account_data` VALUES ('-1', '0', '60', '0', '6', '0', '0');
INSERT INTO `account_data` VALUES ('1', '0', '1840', '1284', '16', '35', '55');
INSERT INTO `account_data` VALUES ('2', '0', '100', '92', '1', '1', '8');
INSERT INTO `account_data` VALUES ('3', '0', '10', '10', '1', '0', '1');
INSERT INTO `account_data` VALUES ('4', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('5', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('6', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('7', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('8', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('9', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('10', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('11', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('12', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('13', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('14', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('15', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('16', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('17', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('18', '0', '90', '88', '1', '1', '1');
INSERT INTO `account_data` VALUES ('19', '0', '30', '31', '0', '1', '1');
INSERT INTO `account_data` VALUES ('20', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('21', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('22', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('23', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('24', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('25', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('26', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('27', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('28', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('29', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('30', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('31', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('32', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('33', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('34', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('35', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('36', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('37', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('38', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('39', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('40', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('41', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('42', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('43', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('44', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('45', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('46', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('47', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('48', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('49', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('50', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('51', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('52', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('53', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('54', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('55', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('56', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('57', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('58', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('59', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('60', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('61', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('62', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('63', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('64', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('65', '0', '200', '200', '0', '0', '0');
INSERT INTO `account_data` VALUES ('66', '0', '200', '200', '0', '0', '0');
INSERT INTO `account_data` VALUES ('67', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('68', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('69', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('70', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('71', '0', '200', '200', '0', '0', '0');
INSERT INTO `account_data` VALUES ('72', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('73', '0', '200', '200', '0', '0', '0');
INSERT INTO `account_data` VALUES ('74', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('75', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('76', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('77', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('78', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('79', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('80', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('81', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('82', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('83', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('84', '0', '40', '40', '0', '2', '0');
INSERT INTO `account_data` VALUES ('85', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('86', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('87', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('88', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('89', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('90', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('91', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('92', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('93', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('94', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('95', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('96', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('97', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('98', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('99', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('100', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('101', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('102', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('103', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('104', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('105', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('106', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('107', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('108', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('109', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('110', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('111', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('112', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('113', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('114', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('115', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('116', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('117', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('118', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('119', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('120', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('121', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('122', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('123', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('124', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('125', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('126', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('127', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('128', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('129', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('130', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('131', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('132', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('133', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('134', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('135', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('136', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('137', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('138', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('139', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('140', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('141', '0', '20', '17', '-1', '1', '0');
INSERT INTO `account_data` VALUES ('142', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('143', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('144', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('145', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('146', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('147', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('148', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('149', '0', '20', '17', '-1', '1', '0');
INSERT INTO `account_data` VALUES ('150', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('151', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('152', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('153', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('154', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('155', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('156', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('157', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('158', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('159', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('160', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('161', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('162', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('163', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('164', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('165', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('166', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('167', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('168', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('169', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('170', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('171', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('172', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('173', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('174', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('175', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('176', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('177', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('178', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('179', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('180', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('181', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('182', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('183', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('184', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('185', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('186', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('187', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('188', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('189', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('190', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('191', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('192', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('193', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('194', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('195', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('196', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('197', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('198', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('199', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('200', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('201', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('202', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('203', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('204', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('205', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('206', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('207', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('208', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('209', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('210', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('211', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('212', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('213', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('214', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('215', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('216', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('217', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('218', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('219', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('220', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('221', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('222', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('223', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('224', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('225', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('226', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('227', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('228', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('229', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('230', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('231', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('232', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('233', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('234', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('235', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('236', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('237', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('238', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('239', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('240', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('241', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('242', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('243', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('244', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('245', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('246', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('247', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('248', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('249', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('250', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('251', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('252', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('253', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('254', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('255', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('256', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('257', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('258', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('259', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('260', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('261', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('262', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('263', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('264', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('265', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('266', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('267', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('268', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('269', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('270', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('271', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('272', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('273', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('274', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('275', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('276', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('277', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('278', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('279', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('280', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('281', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('282', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('283', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('284', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('285', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('286', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('287', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('288', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('289', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('290', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('291', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('292', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('293', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('294', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('295', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('296', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('297', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('298', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('299', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('300', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('301', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('302', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('303', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('304', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('305', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('306', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('307', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('308', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('309', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('310', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('311', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('312', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('313', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('314', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('315', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('316', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('317', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('318', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('319', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('320', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('321', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('322', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('323', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('324', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('325', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('326', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('327', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('328', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('329', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('330', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('331', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('332', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('333', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('334', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('335', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('336', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('337', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('338', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('339', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('340', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('341', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('342', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('343', '0', '20', '20', '1', '0', '0');
INSERT INTO `account_data` VALUES ('344', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('345', '0', '20', '20', '1', '0', '0');
INSERT INTO `account_data` VALUES ('346', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('347', '0', '30', '31', '0', '0', '0');
INSERT INTO `account_data` VALUES ('348', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('349', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('350', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('351', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('352', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('353', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('354', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('355', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('356', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('357', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('358', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('359', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('360', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('361', '0', '20', '20', '1', '0', '0');
INSERT INTO `account_data` VALUES ('362', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('363', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('364', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('365', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('366', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('367', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('368', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('369', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('370', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('371', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('372', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('373', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('374', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('375', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('376', '0', '30', '31', '0', '0', '0');
INSERT INTO `account_data` VALUES ('377', '0', '30', '31', '0', '0', '0');
INSERT INTO `account_data` VALUES ('378', '0', '20', '20', '1', '0', '0');
INSERT INTO `account_data` VALUES ('379', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('380', '0', '30', '30', '0', '0', '1');
INSERT INTO `account_data` VALUES ('381', '0', '40', '40', '0', '0', '2');
INSERT INTO `account_data` VALUES ('382', '0', '30', '30', '0', '0', '0');
INSERT INTO `account_data` VALUES ('383', '0', '50', '50', '0', '0', '0');
INSERT INTO `account_data` VALUES ('384', '0', '30', '30', '0', '0', '1');
INSERT INTO `account_data` VALUES ('385', '0', '30', '31', '0', '0', '0');
INSERT INTO `account_data` VALUES ('386', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('387', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('388', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('389', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('390', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('391', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('392', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('393', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('394', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('395', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('396', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('397', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('398', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('399', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('400', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('401', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('402', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('403', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('404', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('405', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('406', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('407', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('408', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('409', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('410', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('411', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('412', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('413', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('414', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('415', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('416', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('417', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('418', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('419', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('420', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('421', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('422', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('423', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('424', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('425', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('426', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('427', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('428', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('429', '0', '200', '200', '10', '10', '0');
INSERT INTO `account_data` VALUES ('430', '0', '20', '20', '0', '0', '0');
INSERT INTO `account_data` VALUES ('431', '0', '400', '400', '0', '0', '0');
INSERT INTO `account_data` VALUES ('432', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('433', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('434', '0', '400', '400', '0', '20', '0');
INSERT INTO `account_data` VALUES ('435', '0', '40', '37', '0', '2', '0');
INSERT INTO `account_data` VALUES ('436', '0', '300', '300', '0', '10', '10');
INSERT INTO `account_data` VALUES ('437', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('438', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('439', '0', '20', '20', '0', '1', '0');
INSERT INTO `account_data` VALUES ('440', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('441', '0', '200', '200', '0', '10', '0');
INSERT INTO `account_data` VALUES ('442', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('443', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('444', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('445', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('446', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('447', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('448', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('449', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('450', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('451', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('452', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('453', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('454', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('455', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('456', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('457', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('458', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('459', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('460', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('461', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('462', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('463', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('464', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('465', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('466', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('467', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('468', '0', '190', '190', '0', '2', '5');

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
  KEY `attention_question_id` (`question_id`),
  KEY `attention_user_id` (`user_id`),
  CONSTRAINT `attention_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attention_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attention
-- ----------------------------

-- ----------------------------
-- Table structure for `block`
-- ----------------------------
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `block_name` varchar(50) NOT NULL,
  `key_word` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of block
-- ----------------------------

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------

-- ----------------------------
-- Table structure for `kind`
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kind
-- ----------------------------
INSERT INTO `kind` VALUES ('1', '学业问答');
INSERT INTO `kind` VALUES ('2', '就业指导');
INSERT INTO `kind` VALUES ('3', '学习资源');
INSERT INTO `kind` VALUES ('4', '生活相关');
INSERT INTO `kind` VALUES ('5', '其他问题');

-- ----------------------------
-- Table structure for `likes`
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `response_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `flag` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `like_response_id` (`response_id`),
  KEY `like_user_id` (`user_id`),
  CONSTRAINT `like_response_id` FOREIGN KEY (`response_id`) REFERENCES `response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of likes
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
  CONSTRAINT `message_object_id` FOREIGN KEY (`object_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2472 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('178', '1', 'createQuestion', '您在2020-05-22 08:08:05创建了\"title\"问题');
INSERT INTO `message` VALUES ('179', '1', 'createQuestion', '您在2020-05-22 08:09:17创建了\"title\"问题');
INSERT INTO `message` VALUES ('180', '1', 'createQuestion', '您在2020-05-23 10:07:09创建了\"title\"问题');
INSERT INTO `message` VALUES ('2449', '1', 'deleteQuestion', '您的问题\"title\"已被管理员删除');
INSERT INTO `message` VALUES ('2450', '1', 'deleteQuestion', '您的问题\"title\"已被管理员删除');
INSERT INTO `message` VALUES ('2451', '1', 'deleteQuestion', '您的问题\"title\"已被管理员删除');

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
  `anonymous` int(11) NOT NULL DEFAULT '0',
  `kind_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `question_content_id` (`content_id`),
  KEY `question_author_id` (`author_id`),
  KEY `question_kind_id` (`kind_id`),
  CONSTRAINT `question_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  CONSTRAINT `question_kind_id` FOREIGN KEY (`kind_id`) REFERENCES `kind` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1787 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for `question_title`
-- ----------------------------
DROP TABLE IF EXISTS `question_title`;
CREATE TABLE `question_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `title_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `question_title_id` (`title_id`),
  KEY `title_question_id` (`question_id`),
  CONSTRAINT `question_title_id` FOREIGN KEY (`title_id`) REFERENCES `title` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `title_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1758 DEFAULT CHARSET=utf8;

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
  KEY `question_reportor_id` (`reportor_id`),
  KEY `report_question_id` (`question_id`),
  CONSTRAINT `question_reportor_id` FOREIGN KEY (`reportor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

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
  KEY `report_response_id` (`response_id`),
  KEY `response_reportor_id` (`reportor_id`),
  CONSTRAINT `report_response_id` FOREIGN KEY (`response_id`) REFERENCES `response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_reportor_id` FOREIGN KEY (`reportor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

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
  `anonymous` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `response_author_id` (`author_id`),
  KEY `response_content_id` (`content_id`),
  KEY `response_question_id` (`question_id`),
  CONSTRAINT `response_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=295 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of response
-- ----------------------------

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
  CONSTRAINT `reward_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reward
-- ----------------------------

-- ----------------------------
-- Table structure for `title`
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1758 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of title
-- ----------------------------
INSERT INTO `title` VALUES ('1756', '1');
INSERT INTO `title` VALUES ('1757', 'e');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `id_card` varchar(50) NOT NULL,
  `identity` varchar(20) NOT NULL DEFAULT 'student',
  `phone_num` varchar(12) DEFAULT NULL,
  `account_data_id` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`),
  KEY `user_account_data_id` (`account_data_id`),
  CONSTRAINT `user_account_data_id` FOREIGN KEY (`account_data_id`) REFERENCES `account_data` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=492 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'MTIz', '管理员1', 'MTIzNA==', 'administrator', null, '-1');
