/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : team_work

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-05-25 20:55:24
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_data
-- ----------------------------
INSERT INTO `account_data` VALUES ('-1', '0', '60', '60', '6', '3', '0');
INSERT INTO `account_data` VALUES ('1', '0', '1640', '1078', '14', '38', '49');
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
INSERT INTO `account_data` VALUES ('18', '0', '90', '88', '1', '2', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attention
-- ----------------------------
INSERT INTO `attention` VALUES ('25', '20', '57', '2020-05-09 15:55:23', '1');
INSERT INTO `attention` VALUES ('27', '2', '61', '2020-05-20 09:51:50', '1');
INSERT INTO `attention` VALUES ('29', '2', '57', '2020-05-14 08:16:14', '1');

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
INSERT INTO `block` VALUES ('6', '1', '1');

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('132', '不要\n单词课、APP啥的就不用回答了，网络时代大家都清楚。\n\n要\n要最有效果的，真能把单词背下来的，同时效率很高的。\n确实做到相见恨晚的。其他都是白费。\n\n也要\n当然质量上乘，可以持续调动学习荷尔蒙的鸡汤也算。虽然这种鸡汤不多见。');
INSERT INTO `content` VALUES ('134', '本人不太自觉，希望有个有效的学习方法');
INSERT INTO `content` VALUES ('136', '本人大一，上课经常走神，自习总会不自觉拿起手机，求能够帮助专心学习的方法');
INSERT INTO `content` VALUES ('139', '本人大一，不知道怎么投分比较合理');
INSERT INTO `content` VALUES ('140', '首先，结合了场景的应用，让单词更加容易记忆，这点就不累赘了其次，拓展了单词的词性，延伸了更多地词义，所谓的的一词多义，在书中最容易体现再者，可读性，阅读是人类的优秀天性，能够让人增长知识，开拓视野，阅读英文文章就更不用说了，背单词可能10分钟就睡着了，阅读一整天可能都还津津有味。最后，阅读让英文水平提升一个档次，大部分英语学习者，都害怕阅读英语名著，怕这怕那的，其实根本提不高英语。（这里我找到了方法）');
INSERT INTO `content` VALUES ('141', '有些人在背单词的时间，没有时间观念，总是喜欢用一大段时间来背单词，当时感觉已经记住了，但是过不了多久，又全部忘记了，这可能是记单词的通病，所以，学姐建议你，以后记单词，使用零碎时间，用不到10分钟的时间，抽空记住几个，然后一有零碎空闲时间，无论什么地方，无论做什么，马上背几个单词，这样记忆效率是比较高的哦');
INSERT INTO `content` VALUES ('142', '不知道');
INSERT INTO `content` VALUES ('144', '1');
INSERT INTO `content` VALUES ('145', 'test');
INSERT INTO `content` VALUES ('146', '1111');
INSERT INTO `content` VALUES ('147', '111');
INSERT INTO `content` VALUES ('148', '111');
INSERT INTO `content` VALUES ('149', 'haah');
INSERT INTO `content` VALUES ('150', 'lalala');
INSERT INTO `content` VALUES ('151', 'content');
INSERT INTO `content` VALUES ('152', 'content');
INSERT INTO `content` VALUES ('153', 'content');
INSERT INTO `content` VALUES ('154', 'content');
INSERT INTO `content` VALUES ('155', '222');
INSERT INTO `content` VALUES ('156', '222');
INSERT INTO `content` VALUES ('157', '222');
INSERT INTO `content` VALUES ('159', '111');
INSERT INTO `content` VALUES ('161', '22');
INSERT INTO `content` VALUES ('162', '22');
INSERT INTO `content` VALUES ('163', 'llaa');
INSERT INTO `content` VALUES ('164', 'lala');
INSERT INTO `content` VALUES ('165', 'xixi');
INSERT INTO `content` VALUES ('168', '3');
INSERT INTO `content` VALUES ('171', '1');
INSERT INTO `content` VALUES ('172', '1');
INSERT INTO `content` VALUES ('173', '2');
INSERT INTO `content` VALUES ('174', '1');
INSERT INTO `content` VALUES ('175', '1');
INSERT INTO `content` VALUES ('176', '1');
INSERT INTO `content` VALUES ('178', '3');
INSERT INTO `content` VALUES ('181', '1\n');
INSERT INTO `content` VALUES ('182', '2');
INSERT INTO `content` VALUES ('183', '3');
INSERT INTO `content` VALUES ('184', '4');
INSERT INTO `content` VALUES ('185', '2');
INSERT INTO `content` VALUES ('186', '3\n\n');
INSERT INTO `content` VALUES ('187', '1');
INSERT INTO `content` VALUES ('188', '2');
INSERT INTO `content` VALUES ('189', '3');
INSERT INTO `content` VALUES ('190', '4');
INSERT INTO `content` VALUES ('191', '5');
INSERT INTO `content` VALUES ('192', '6');
INSERT INTO `content` VALUES ('193', '2');
INSERT INTO `content` VALUES ('194', '3');
INSERT INTO `content` VALUES ('195', '4');
INSERT INTO `content` VALUES ('196', '5');
INSERT INTO `content` VALUES ('197', '1');
INSERT INTO `content` VALUES ('198', '1');
INSERT INTO `content` VALUES ('199', '1');
INSERT INTO `content` VALUES ('200', '1');
INSERT INTO `content` VALUES ('201', '1');
INSERT INTO `content` VALUES ('202', '2');
INSERT INTO `content` VALUES ('203', '1');
INSERT INTO `content` VALUES ('204', '1');
INSERT INTO `content` VALUES ('205', '1');
INSERT INTO `content` VALUES ('206', '1');
INSERT INTO `content` VALUES ('207', '1');
INSERT INTO `content` VALUES ('208', '2');
INSERT INTO `content` VALUES ('209', '3');
INSERT INTO `content` VALUES ('210', '4');
INSERT INTO `content` VALUES ('211', '1');
INSERT INTO `content` VALUES ('212', '1');
INSERT INTO `content` VALUES ('216', '1');
INSERT INTO `content` VALUES ('218', '1');
INSERT INTO `content` VALUES ('221', '2121212');
INSERT INTO `content` VALUES ('222', '212121');
INSERT INTO `content` VALUES ('223', 'content');
INSERT INTO `content` VALUES ('224', 'content');
INSERT INTO `content` VALUES ('225', 'content');
INSERT INTO `content` VALUES ('226', 'content1');
INSERT INTO `content` VALUES ('227', 'content1');
INSERT INTO `content` VALUES ('228', 'content1');
INSERT INTO `content` VALUES ('229', 'content1');
INSERT INTO `content` VALUES ('230', 'content1');
INSERT INTO `content` VALUES ('231', 'content1');
INSERT INTO `content` VALUES ('232', 'content1');

-- ----------------------------
-- Table structure for `kind`
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kind
-- ----------------------------
INSERT INTO `kind` VALUES ('0', '综合');
INSERT INTO `kind` VALUES ('1', '生活');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES ('3', '77', '20', '0');
INSERT INTO `likes` VALUES ('4', '78', '2', '-1');
INSERT INTO `likes` VALUES ('5', '77', '2', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('15', '2', 'createQuestion', '您在Sat May 02 16:30:43 CST 2020创建了\"英语语法极差，怎样快速学习？\"问题');
INSERT INTO `message` VALUES ('17', '2', 'createQuestion', '您在Sat May 02 17:02:45 CST 2020创建了\"C语言要怎么学？\"问题');
INSERT INTO `message` VALUES ('18', '2', 'response', '老师1回复了您\"C语言要怎么学？\"的问题');
INSERT INTO `message` VALUES ('28', '2', 'createQuestion', '您在Tue May 05 10:40:04 CST 2020创建了\"1\"问题');
INSERT INTO `message` VALUES ('29', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('30', '2', 'createQuestion', '您在Tue May 05 14:02:28 CST 2020创建了\"1\"问题');
INSERT INTO `message` VALUES ('31', '2', 'createQuestion', '您在Tue May 05 14:02:34 CST 2020创建了\"2\"问题');
INSERT INTO `message` VALUES ('32', '2', 'createQuestion', '您在Tue May 05 14:02:41 CST 2020创建了\"3\"问题');
INSERT INTO `message` VALUES ('33', '2', 'createQuestion', '您在Tue May 05 14:02:53 CST 2020创建了\"4\"问题');
INSERT INTO `message` VALUES ('34', '2', 'createQuestion', '您在Tue May 05 14:03:00 CST 2020创建了\"5\"问题');
INSERT INTO `message` VALUES ('35', '2', 'createQuestion', '您在Tue May 05 14:13:51 CST 2020创建了\"2\"问题');
INSERT INTO `message` VALUES ('36', '2', 'createQuestion', '您在Tue May 05 14:13:57 CST 2020创建了\"1\"问题');
INSERT INTO `message` VALUES ('37', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('38', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('39', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('40', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('41', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('42', '2', 'createQuestion', '您在Tue May 05 19:28:37 CST 2020创建了\"3\"问题');
INSERT INTO `message` VALUES ('43', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('44', '2', 'response', '学生1回复了您\"3\"的问题');
INSERT INTO `message` VALUES ('45', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('46', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('47', '2', 'createQuestion', '您在Thu May 07 17:47:35 CST 2020创建了\"啦啦\"问题');
INSERT INTO `message` VALUES ('48', '2', 'createQuestion', '您在Thu May 07 17:48:46 CST 2020创建了\"嘻嘻\"问题');
INSERT INTO `message` VALUES ('49', '2', 'createQuestion', '您在Thu May 07 18:56:35 CST 2020创建了\"1\"问题');
INSERT INTO `message` VALUES ('50', '2', 'createQuestion', '您在Thu May 07 18:57:20 CST 2020创建了\"2\"问题');
INSERT INTO `message` VALUES ('51', '2', 'createQuestion', '您在2020-05-07 19:03:35创建了\"19\"问题');
INSERT INTO `message` VALUES ('52', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('53', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('54', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('55', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('56', '2', 'createQuestion', '您在2020-05-08 17:31:44创建了\"2\"问题');
INSERT INTO `message` VALUES ('57', '2', 'createQuestion', '您在2020-05-08 17:32:48创建了\"test1\"问题');
INSERT INTO `message` VALUES ('58', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('59', '2', 'createQuestion', '您在2020-05-09 15:11:53创建了\"有什么相见恨晚的背单词方法？\"问题');
INSERT INTO `message` VALUES ('60', '2', 'createQuestion', '您在2020-05-09 15:12:14创建了\"有什么相见恨晚的背单词方法？\"问题');
INSERT INTO `message` VALUES ('61', '2', 'createQuestion', '您在2020-05-09 15:12:50创建了\"有什么相见恨晚的背单词方法？\"问题');
INSERT INTO `message` VALUES ('62', '2', 'createQuestion', '您在2020-05-09 15:19:42创建了\"有什么相见恨晚的背单词方法？\"问题');
INSERT INTO `message` VALUES ('63', '2', 'createQuestion', '您在2020-05-09 15:20:55创建了\"大一一个学期学多少编程算正常?\"问题');
INSERT INTO `message` VALUES ('64', '2', 'createQuestion', '您在2020-05-09 15:21:31创建了\"不想学习的时候如何逼迫自己学习？\"问题');
INSERT INTO `message` VALUES ('65', '20', 'createQuestion', '您在2020-05-09 15:36:37创建了\"一个女生该有哪些优秀习惯？\"问题');
INSERT INTO `message` VALUES ('66', '20', 'createQuestion', '您在2020-05-09 15:38:46创建了\"怎么才能心无旁骛地学习？\"问题');
INSERT INTO `message` VALUES ('67', '20', 'createQuestion', '您在2020-05-09 15:46:46创建了\"非计算机专业学生怎么走上计算机技术之路？\"问题');
INSERT INTO `message` VALUES ('68', '20', 'createQuestion', '您在2020-05-09 15:48:00创建了\"如何戒掉手机去认真学习？\"问题');
INSERT INTO `message` VALUES ('70', '2', 'response', '学生3回复了您\"有什么相见恨晚的背单词方法？\"的问题');
INSERT INTO `message` VALUES ('71', '2', 'response', '学生2回复了您\"有什么相见恨晚的背单词方法？\"的问题');
INSERT INTO `message` VALUES ('73', '2', 'response', '学生1回复了您\"不想学习的时候如何逼迫自己学习？\"的问题');
INSERT INTO `message` VALUES ('74', '2', 'createQuestion', '您在2020-05-11 23:45:18创建了\"机械行业和自动化行业该如何与互联网衔接？\"问题');
INSERT INTO `message` VALUES ('75', '2', 'response', '学生1回复了您\"有什么相见恨晚的背单词方法？\"的问题');
INSERT INTO `message` VALUES ('76', '2', 'createQuestion', '您在2020-05-12 07:32:51创建了\"test\"问题');
INSERT INTO `message` VALUES ('77', '2', 'response', '学生1回复了您\"有什么相见恨晚的背单词方法？\"的问题');
INSERT INTO `message` VALUES ('78', '2', 'createQuestion', '您在2020-05-12 09:16:32创建了\"111\"问题');
INSERT INTO `message` VALUES ('79', '2', 'likeResponse', '学生1点赞了您的回复');
INSERT INTO `message` VALUES ('80', '2', 'likeResponse', '学生1点赞了您的回复');
INSERT INTO `message` VALUES ('81', '2', 'response', '学生1回复了您\"有什么相见恨晚的背单词方法？\"的问题');
INSERT INTO `message` VALUES ('82', '20', 'likeResponse', '学生1点赞了您的回复');
INSERT INTO `message` VALUES ('83', '2', 'createQuestion', '您在2020-05-12 21:46:37创建了\"haha\"问题');
INSERT INTO `message` VALUES ('84', '2', 'response', '学生1回复了您\"有什么相见恨晚的背单词方法？\"的问题');
INSERT INTO `message` VALUES ('85', '2', 'createQuestion', '您在2020-05-14 08:20:01创建了\"222\"问题');
INSERT INTO `message` VALUES ('86', '2', 'response', '学生1回复了您\"有什么相见恨晚的背单词方法？\"的问题');
INSERT INTO `message` VALUES ('87', '2', 'createQuestion', '您在2020-05-14 08:33:26创建了\"2\"问题');
INSERT INTO `message` VALUES ('88', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('89', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('90', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('91', '2', 'response', '学生1回复了您\"2\"的问题');
INSERT INTO `message` VALUES ('92', '2', 'createQuestion', '您在2020-05-14 08:43:55创建了\"xixi\"问题');
INSERT INTO `message` VALUES ('93', '2', 'createQuestion', '您在2020-05-19 22:33:14创建了\"1\"问题');
INSERT INTO `message` VALUES ('94', '2', 'deleteQuestion', '您的问题\"1\"已被管理员删除');
INSERT INTO `message` VALUES ('95', '2', 'deleteQuestion', '您的问题\"机械行业和自动化行业该如何与互联网衔接？\"已被管理员删除');
INSERT INTO `message` VALUES ('96', '20', 'deleteQuestion', '您的问题\"非计算机专业学生怎么走上计算机技术之路？\"已被管理员删除');
INSERT INTO `message` VALUES ('97', '20', 'deleteQuestion', '您的问题\"如何戒掉手机去认真学习？\"已被管理员删除');
INSERT INTO `message` VALUES ('98', '2', 'createQuestion', '您在2020-05-19 23:17:00创建了\"2\"问题');
INSERT INTO `message` VALUES ('99', '2', 'createQuestion', '您在2020-05-19 23:17:09创建了\"3\"问题');
INSERT INTO `message` VALUES ('100', '2', 'createQuestion', '您在2020-05-19 23:17:13创建了\"4\"问题');
INSERT INTO `message` VALUES ('101', '2', 'createQuestion', '您在2020-05-19 23:17:20创建了\"5\"问题');
INSERT INTO `message` VALUES ('102', '2', 'deleteQuestion', '您的问题\"5\"已被管理员删除');
INSERT INTO `message` VALUES ('103', '2', 'deleteQuestion', '您的问题\"4\"已被管理员删除');
INSERT INTO `message` VALUES ('104', '2', 'deleteQuestion', '您的问题\"2\"已被管理员删除');
INSERT INTO `message` VALUES ('105', '2', 'createQuestion', '您在2020-05-20 10:54:04创建了\"1\"问题');
INSERT INTO `message` VALUES ('106', '2', 'createQuestion', '您在2020-05-20 10:55:31创建了\"1\"问题');
INSERT INTO `message` VALUES ('107', '2', 'createQuestion', '您在2020-05-20 10:55:49创建了\"2\"问题');
INSERT INTO `message` VALUES ('108', '2', 'createQuestion', '您在2020-05-20 10:56:35创建了\"11\"问题');
INSERT INTO `message` VALUES ('109', '2', 'createQuestion', '您在2020-05-20 10:57:18创建了\"1\"问题');
INSERT INTO `message` VALUES ('110', '2', 'createQuestion', '您在2020-05-20 11:38:23创建了\"1\"问题');
INSERT INTO `message` VALUES ('111', '2', 'createQuestion', '您在2020-05-20 11:38:28创建了\"2\"问题');
INSERT INTO `message` VALUES ('112', '2', 'createQuestion', '您在2020-05-20 11:38:34创建了\"3\"问题');
INSERT INTO `message` VALUES ('113', '2', 'createQuestion', '您在2020-05-20 11:38:40创建了\"4\"问题');
INSERT INTO `message` VALUES ('114', '2', 'createQuestion', '您在2020-05-20 11:38:45创建了\"5\"问题');
INSERT INTO `message` VALUES ('115', '2', 'deleteQuestion', '您的问题\"5\"已被管理员删除');
INSERT INTO `message` VALUES ('116', '2', 'deleteQuestion', '您的问题\"2\"已被管理员删除');
INSERT INTO `message` VALUES ('117', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('118', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('119', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('120', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('121', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('122', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('123', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('124', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('125', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('126', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('127', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('128', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('129', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('130', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('131', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('132', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('133', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('134', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('135', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('136', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('137', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('138', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('139', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('140', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('141', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('142', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('143', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('144', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('145', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('146', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('147', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('148', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('149', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('150', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('151', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('152', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('153', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('154', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('155', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('156', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('157', '2', 'deleteResponse', '您关于\"1\"的回复被管理员删除');
INSERT INTO `message` VALUES ('158', '2', 'deleteQuestion', '您的问题\"4\"已被管理员删除');
INSERT INTO `message` VALUES ('159', '2', 'createQuestion', '您在2020-05-20 23:46:26创建了\"1\"问题');
INSERT INTO `message` VALUES ('160', '2', 'deleteQuestion', '您的问题\"1\"已被管理员删除');
INSERT INTO `message` VALUES ('161', '2', 'createQuestion', '您在2020-05-20 23:57:32创建了\"2\"问题');
INSERT INTO `message` VALUES ('162', '2', 'deleteQuestion', '您的问题\"2\"已被管理员删除');
INSERT INTO `message` VALUES ('163', '2', 'createQuestion', '您在2020-05-21 08:47:07创建了\"11\"问题');
INSERT INTO `message` VALUES ('164', '2', 'response', '学生1回复了您\"11\"的问题');
INSERT INTO `message` VALUES ('165', '2', 'createQuestion', '您在2020-05-21 09:30:16创建了\"234\"问题');
INSERT INTO `message` VALUES ('166', '2', 'response', '学生1回复了您\"234\"的问题');
INSERT INTO `message` VALUES ('167', '2', 'deleteResponse', '您关于\"234\"的回复被管理员删除');
INSERT INTO `message` VALUES ('168', '2', 'deleteQuestion', '您的问题\"234\"已被管理员删除');
INSERT INTO `message` VALUES ('169', '2', 'createQuestion', '您在2020-05-21 09:42:13创建了\"2121\"问题');
INSERT INTO `message` VALUES ('170', '2', 'deleteQuestion', '您的问题\"2121\"已被管理员删除');
INSERT INTO `message` VALUES ('171', '2', 'createQuestion', '您在2020-05-21 09:49:50创建了\"2121212\"问题');
INSERT INTO `message` VALUES ('172', '2', 'deleteQuestion', '您的问题\"2121212\"已被管理员删除');
INSERT INTO `message` VALUES ('173', '2', 'response', '学生1回复了您\"11\"的问题');
INSERT INTO `message` VALUES ('174', '2', 'likeResponse', '学生1点赞了您的回复');
INSERT INTO `message` VALUES ('175', '2', 'deleteResponse', '您关于\"11\"的回复被管理员删除');
INSERT INTO `message` VALUES ('176', '2', 'response', '学生1回复了您\"11\"的问题');
INSERT INTO `message` VALUES ('177', '2', 'deleteQuestion', '您的问题\"11\"已被管理员删除');
INSERT INTO `message` VALUES ('178', '1', 'createQuestion', '您在2020-05-22 08:08:05创建了\"title\"问题');
INSERT INTO `message` VALUES ('179', '1', 'createQuestion', '您在2020-05-22 08:09:17创建了\"title\"问题');
INSERT INTO `message` VALUES ('180', '1', 'createQuestion', '您在2020-05-23 10:07:09创建了\"title\"问题');

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
  `kind_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `question_content_id` (`content_id`),
  KEY `question_author_id` (`author_id`),
  KEY `question_kind_id` (`kind_id`),
  CONSTRAINT `question_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`),
  CONSTRAINT `question_kind_id` FOREIGN KEY (`kind_id`) REFERENCES `kind` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('57', '2', '7', '1', '2020-05-09 15:19:42', '132', '0', '1');
INSERT INTO `question` VALUES ('59', '2', '1', '1', '2020-05-09 15:21:31', '134', '0', '0');
INSERT INTO `question` VALUES ('61', '20', '0', '1', '2020-05-09 15:38:46', '136', '0', '0');
INSERT INTO `question` VALUES ('82', '2', '24', '1', '2020-05-20 11:38:23', '176', '0', '0');
INSERT INTO `question` VALUES ('93', '1', '0', '0', '2020-05-22 08:08:05', '223', '0', '0');
INSERT INTO `question` VALUES ('94', '1', '0', '0', '2020-05-22 08:09:17', '224', '0', '0');
INSERT INTO `question` VALUES ('95', '1', '0', '0', '2020-05-23 10:07:09', '225', '0', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_title
-- ----------------------------
INSERT INTO `question_title` VALUES ('52', '57', '52');
INSERT INTO `question_title` VALUES ('54', '59', '54');
INSERT INTO `question_title` VALUES ('56', '61', '56');
INSERT INTO `question_title` VALUES ('77', '82', '77');
INSERT INTO `question_title` VALUES ('88', '93', '88');
INSERT INTO `question_title` VALUES ('89', '94', '89');
INSERT INTO `question_title` VALUES ('90', '95', '90');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_question
-- ----------------------------
INSERT INTO `report_question` VALUES ('2', '2', '61', '1');
INSERT INTO `report_question` VALUES ('3', '2', '59', '1');
INSERT INTO `report_question` VALUES ('4', '2', '57', '1');
INSERT INTO `report_question` VALUES ('5', '2', '82', '1');

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
INSERT INTO `report_response` VALUES ('1', '2', '78', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of response
-- ----------------------------
INSERT INTO `response` VALUES ('77', '57', '20', '141', '1', '0', '0', '2020-05-09 15:55:16', '1');
INSERT INTO `response` VALUES ('78', '59', '2', '142', '0', '1', '-1', '2020-05-11 23:43:02', '0');
INSERT INTO `response` VALUES ('79', '57', '2', '144', '0', '0', '0', '2020-05-12 07:32:32', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reward
-- ----------------------------
INSERT INTO `reward` VALUES ('34', '2', 'SyntheticTest', '0.1', '2020-05-02 14:48:59');
INSERT INTO `reward` VALUES ('35', '2', 'ServiceTime', '20', '2020-05-02 14:49:04');
INSERT INTO `reward` VALUES ('36', '2', 'ServiceTime', '2', '2020-05-02 14:49:18');
INSERT INTO `reward` VALUES ('37', '2', 'ServiceTime', '2', '2020-05-02 14:49:19');
INSERT INTO `reward` VALUES ('38', '2', 'SyntheticTest', '0.1', '2020-05-02 14:49:27');
INSERT INTO `reward` VALUES ('39', '2', 'ServiceTime', '2', '2020-05-02 14:49:30');
INSERT INTO `reward` VALUES ('40', '2', 'ServiceTime', '2', '2020-05-02 14:49:31');
INSERT INTO `reward` VALUES ('41', '2', 'ServiceTime', '2', '2020-05-02 14:59:49');
INSERT INTO `reward` VALUES ('42', '2', 'ServiceTime', '1', '2020-05-02 14:59:52');
INSERT INTO `reward` VALUES ('44', '2', 'SyntheticTest', '0.1', '2020-05-05 10:48:39');
INSERT INTO `reward` VALUES ('45', '2', 'SyntheticTest', '0.1', '2020-05-05 10:48:40');
INSERT INTO `reward` VALUES ('46', '2', 'SyntheticTest', '0.1', '2020-05-05 10:48:41');
INSERT INTO `reward` VALUES ('47', '2', 'ServiceTime', '1', '2020-05-05 10:48:44');
INSERT INTO `reward` VALUES ('48', '2', 'SyntheticTest', '0.1', '2020-05-05 10:48:49');
INSERT INTO `reward` VALUES ('49', '2', 'SyntheticTest', '5', '2020-05-08 16:15:26');
INSERT INTO `reward` VALUES ('50', '2', 'SyntheticTest', '0.1', '2020-05-08 16:30:21');
INSERT INTO `reward` VALUES ('51', '2', 'SyntheticTest', '0.1', '2020-05-08 16:30:29');
INSERT INTO `reward` VALUES ('52', '2', 'SyntheticTest', '0.1', '2020-05-08 17:48:28');
INSERT INTO `reward` VALUES ('53', '2', 'ServiceTime', '1', '2020-05-08 17:48:34');
INSERT INTO `reward` VALUES ('54', '2', 'ServiceTime', '1', '2020-05-08 17:48:38');
INSERT INTO `reward` VALUES ('55', '2', 'ServiceTime', '1', '2020-05-08 17:48:41');
INSERT INTO `reward` VALUES ('56', '2', 'SyntheticTest', '0.1', '2020-05-08 17:49:21');
INSERT INTO `reward` VALUES ('57', '2', 'ServiceTime', '1', '2020-05-08 17:49:27');
INSERT INTO `reward` VALUES ('58', '2', 'ServiceTime', '1', '2020-05-08 17:49:31');
INSERT INTO `reward` VALUES ('59', '2', 'ServiceTime', '5', '2020-05-08 17:49:49');
INSERT INTO `reward` VALUES ('60', '2', 'ServiceTime', '2', '2020-05-08 17:50:01');
INSERT INTO `reward` VALUES ('61', '2', 'ServiceTime', '2', '2020-05-08 17:50:24');
INSERT INTO `reward` VALUES ('62', '2', 'SyntheticTest', '0.1', '2020-05-11 23:46:06');
INSERT INTO `reward` VALUES ('63', '2', 'ServiceTime', '1', '2020-05-11 23:46:14');
INSERT INTO `reward` VALUES ('64', '2', 'SyntheticTest', '0.1', '2020-05-12 07:33:59');
INSERT INTO `reward` VALUES ('65', '2', 'SyntheticTest', '0.1', '2020-05-12 07:34:03');
INSERT INTO `reward` VALUES ('66', '2', 'SyntheticTest', '0.1', '2020-05-12 09:17:53');

-- ----------------------------
-- Table structure for `title`
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of title
-- ----------------------------
INSERT INTO `title` VALUES ('21', '1');
INSERT INTO `title` VALUES ('22', '大学新生怎样自然的自我介绍？');
INSERT INTO `title` VALUES ('23', '四级英语考试如何准备呢？');
INSERT INTO `title` VALUES ('24', '2');
INSERT INTO `title` VALUES ('25', '3');
INSERT INTO `title` VALUES ('26', '4');
INSERT INTO `title` VALUES ('27', '5');
INSERT INTO `title` VALUES ('28', '6');
INSERT INTO `title` VALUES ('29', '7');
INSERT INTO `title` VALUES ('52', '有什么相见恨晚的背单词方法？');
INSERT INTO `title` VALUES ('54', '不想学习的时候如何逼迫自己学习？');
INSERT INTO `title` VALUES ('56', '怎么才能心无旁骛地学习？');
INSERT INTO `title` VALUES ('59', '请问选课投分要怎么投？');
INSERT INTO `title` VALUES ('61', 'test');
INSERT INTO `title` VALUES ('62', '111');
INSERT INTO `title` VALUES ('63', 'haha');
INSERT INTO `title` VALUES ('66', 'xixi');
INSERT INTO `title` VALUES ('69', '3');
INSERT INTO `title` VALUES ('72', '1');
INSERT INTO `title` VALUES ('73', '1');
INSERT INTO `title` VALUES ('74', '2');
INSERT INTO `title` VALUES ('75', '11');
INSERT INTO `title` VALUES ('76', '1');
INSERT INTO `title` VALUES ('77', '1');
INSERT INTO `title` VALUES ('79', '3');
INSERT INTO `title` VALUES ('88', 'title');
INSERT INTO `title` VALUES ('89', 'title');
INSERT INTO `title` VALUES ('90', 'title');
INSERT INTO `title` VALUES ('91', 'title1');
INSERT INTO `title` VALUES ('92', 'title1');
INSERT INTO `title` VALUES ('93', 'title1');
INSERT INTO `title` VALUES ('94', 'title1');
INSERT INTO `title` VALUES ('95', 'title1');
INSERT INTO `title` VALUES ('96', 'title1');

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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'MTIz', '管理员1', 'MTIzNA==', 'administrator', null, '-1');
INSERT INTO `user` VALUES ('2', 's1', 'MjM0', '学生1', 'MTIzNA==', 'student', null, '1');
INSERT INTO `user` VALUES ('20', 's2', 'MjM0', '学生2', 'MTIzNA==', 'student', null, '18');
INSERT INTO `user` VALUES ('22', 's9', 'MzQ1', '学生9', 'MTIzNDU=', 'teacher', null, '20');
INSERT INTO `user` VALUES ('23', 's10', 'MzQ1', '学生10', 'MTIzNDU=', 'student', null, '21');
INSERT INTO `user` VALUES ('24', 't3', '3', '教师3', '123', 'teacher', null, '22');
INSERT INTO `user` VALUES ('25', 't3', '3', '教师3', '123', 'teacher', null, '23');
INSERT INTO `user` VALUES ('26', 't3', '3', '教师3', '123', 'teacher', null, '24');
INSERT INTO `user` VALUES ('39', '1', 'MzQ0', 'wsh', 'MTIzNDQ=', 'student', null, '31');
INSERT INTO `user` VALUES ('40', '2', 'MzQ1', 'wsh2', 'MTIzNDU=', 'administrator', null, '-1');
INSERT INTO `user` VALUES ('41', '1', 'MzQ0', 'wsh', 'MTIzNDQ=', 'student', null, '32');
INSERT INTO `user` VALUES ('42', '2', 'MzQ1', 'wsh2', 'MTIzNDU=', 'administrator', null, '-1');
INSERT INTO `user` VALUES ('43', '1', 'MzQ0', 'wsh', 'MTIzNDQ=', 'student', null, '33');
INSERT INTO `user` VALUES ('44', '2', 'MzQ1', 'wsh2', 'MTIzNDU=', 'administrator', null, '-1');
