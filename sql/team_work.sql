/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : team_work

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-05-14 15:09:16
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_data
-- ----------------------------
INSERT INTO `account_data` VALUES ('-1', '0', '0', '0', '6', '0', '0');
INSERT INTO `account_data` VALUES ('1', '0', '870', '320', '12', '31', '25');
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
INSERT INTO `account_data` VALUES ('18', '0', '90', '88', '1', '4', '1');
INSERT INTO `account_data` VALUES ('19', '0', '30', '31', '0', '1', '1');
INSERT INTO `account_data` VALUES ('20', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('21', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('22', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('23', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('24', '0', '0', '0', '0', '0', '0');

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
INSERT INTO `attention` VALUES ('27', '2', '61', '2020-05-11 23:45:38', '0');
INSERT INTO `attention` VALUES ('28', '2', '65', '2020-05-11 23:45:31', '1');
INSERT INTO `attention` VALUES ('29', '2', '57', '2020-05-14 08:16:14', '1');
INSERT INTO `attention` VALUES ('30', '2', '67', '2020-05-12 09:17:17', '0');

-- ----------------------------
-- Table structure for `block`
-- ----------------------------
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `block_name` varchar(50) NOT NULL,
  `key_word` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of block
-- ----------------------------
INSERT INTO `block` VALUES ('5', '期末板块', '期末');

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('132', '不要\n单词课、APP啥的就不用回答了，网络时代大家都清楚。\n\n要\n要最有效果的，真能把单词背下来的，同时效率很高的。\n确实做到相见恨晚的。其他都是白费。\n\n也要\n当然质量上乘，可以持续调动学习荷尔蒙的鸡汤也算。虽然这种鸡汤不多见。');
INSERT INTO `content` VALUES ('134', '本人不太自觉，希望有个有效的学习方法');
INSERT INTO `content` VALUES ('136', '本人大一，上课经常走神，自习总会不自觉拿起手机，求能够帮助专心学习的方法');
INSERT INTO `content` VALUES ('137', '非计算机专业学生怎么走上技术之路？来自211一般大学，高考错失计算机专业。目前工科。应该考研吗？还是寻找其他成长之路？');
INSERT INTO `content` VALUES ('138', '想好好学习，但是戒不掉手机，请问怎么办');
INSERT INTO `content` VALUES ('139', '本人大一，不知道怎么投分比较合理');
INSERT INTO `content` VALUES ('140', '首先，结合了场景的应用，让单词更加容易记忆，这点就不累赘了其次，拓展了单词的词性，延伸了更多地词义，所谓的的一词多义，在书中最容易体现再者，可读性，阅读是人类的优秀天性，能够让人增长知识，开拓视野，阅读英文文章就更不用说了，背单词可能10分钟就睡着了，阅读一整天可能都还津津有味。最后，阅读让英文水平提升一个档次，大部分英语学习者，都害怕阅读英语名著，怕这怕那的，其实根本提不高英语。（这里我找到了方法）');
INSERT INTO `content` VALUES ('141', '有些人在背单词的时间，没有时间观念，总是喜欢用一大段时间来背单词，当时感觉已经记住了，但是过不了多久，又全部忘记了，这可能是记单词的通病，所以，学姐建议你，以后记单词，使用零碎时间，用不到10分钟的时间，抽空记住几个，然后一有零碎空闲时间，无论什么地方，无论做什么，马上背几个单词，这样记忆效率是比较高的哦');
INSERT INTO `content` VALUES ('142', '不知道');
INSERT INTO `content` VALUES ('143', '感觉机械自动化行业目前一直在走下坡路，虽然在这一块算得上是国家的支助行业，现在却鲜少有年轻人愿意停留在这一块领域，大批的人才向it方向流走。机械与自动化这一块已经风雨飘摇了，再不和互联网衔接，等到老一辈工程师退休，那么这一块将面临重大的人才危（我不知道这是不是杞人忧天，但以目前知乎对于这一块的诸多回答来看，来说我感觉应该是的）。\n\n今天我斗胆提这么一个问题，希望集众人之智，大家一起探讨如何推动机械自动化和互联网衔接，希望每一位在从事在这一块工作的知乎用户，提成各自宝贵的意见。');
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
INSERT INTO `content` VALUES ('158', '222');
INSERT INTO `content` VALUES ('159', '111');
INSERT INTO `content` VALUES ('160', '2');
INSERT INTO `content` VALUES ('161', '22');
INSERT INTO `content` VALUES ('162', '22');
INSERT INTO `content` VALUES ('163', 'llaa');
INSERT INTO `content` VALUES ('164', 'lala');
INSERT INTO `content` VALUES ('165', 'xixi');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES ('3', '77', '20', '0');
INSERT INTO `likes` VALUES ('4', '78', '2', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

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
  CONSTRAINT `question_kind_id` FOREIGN KEY (`kind_id`) REFERENCES `kind` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('57', '2', '7', '0', '2020-05-09 15:19:42', '132', '0', '1');
INSERT INTO `question` VALUES ('59', '2', '1', '1', '2020-05-09 15:21:31', '134', '0', '0');
INSERT INTO `question` VALUES ('61', '20', '0', '1', '2020-05-09 15:38:46', '136', '0', '0');
INSERT INTO `question` VALUES ('62', '20', '0', '0', '2020-05-09 15:46:46', '137', '0', '0');
INSERT INTO `question` VALUES ('63', '20', '0', '0', '2020-05-09 15:48:00', '138', '0', '1');
INSERT INTO `question` VALUES ('65', '2', '0', '0', '2020-05-11 23:45:18', '143', '0', '0');
INSERT INTO `question` VALUES ('66', '2', '0', '0', '2020-05-12 07:32:51', '145', '0', '1');
INSERT INTO `question` VALUES ('67', '2', '0', '0', '2020-05-12 09:16:32', '147', '0', '0');
INSERT INTO `question` VALUES ('68', '2', '0', '0', '2020-05-12 21:46:37', '149', '0', '0');
INSERT INTO `question` VALUES ('69', '2', '0', '0', '2020-05-14 08:20:01', '158', '0', '0');
INSERT INTO `question` VALUES ('70', '2', '4', '1', '2020-05-14 08:33:26', '160', '1', '0');
INSERT INTO `question` VALUES ('71', '2', '0', '0', '2020-05-14 08:43:55', '165', '1', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_title
-- ----------------------------
INSERT INTO `question_title` VALUES ('52', '57', '52');
INSERT INTO `question_title` VALUES ('54', '59', '54');
INSERT INTO `question_title` VALUES ('56', '61', '56');
INSERT INTO `question_title` VALUES ('57', '62', '57');
INSERT INTO `question_title` VALUES ('58', '63', '58');
INSERT INTO `question_title` VALUES ('60', '65', '60');
INSERT INTO `question_title` VALUES ('61', '66', '61');
INSERT INTO `question_title` VALUES ('62', '67', '62');
INSERT INTO `question_title` VALUES ('63', '68', '63');
INSERT INTO `question_title` VALUES ('64', '69', '64');
INSERT INTO `question_title` VALUES ('65', '70', '65');
INSERT INTO `question_title` VALUES ('66', '71', '66');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_question
-- ----------------------------
INSERT INTO `report_question` VALUES ('2', '2', '61', '1');
INSERT INTO `report_question` VALUES ('3', '2', '59', '1');
INSERT INTO `report_question` VALUES ('4', '2', '57', '0');
INSERT INTO `report_question` VALUES ('5', '2', '70', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of response
-- ----------------------------
INSERT INTO `response` VALUES ('77', '57', '20', '141', '1', '0', '0', '2020-05-09 15:55:16', '1');
INSERT INTO `response` VALUES ('78', '59', '2', '142', '1', '0', '0', '2020-05-11 23:43:02', '0');
INSERT INTO `response` VALUES ('79', '57', '2', '144', '0', '0', '0', '2020-05-12 07:32:32', '0');
INSERT INTO `response` VALUES ('81', '57', '2', '148', '0', '0', '0', '2020-05-12 09:25:59', '0');
INSERT INTO `response` VALUES ('82', '57', '2', '150', '0', '0', '0', '2020-05-12 21:46:54', '0');
INSERT INTO `response` VALUES ('83', '57', '2', '159', '0', '0', '0', '2020-05-14 08:20:16', '0');
INSERT INTO `response` VALUES ('84', '70', '2', '161', '0', '0', '0', '2020-05-14 08:33:41', '0');
INSERT INTO `response` VALUES ('85', '70', '2', '162', '0', '0', '0', '2020-05-14 08:33:48', '0');
INSERT INTO `response` VALUES ('86', '70', '2', '163', '0', '0', '0', '2020-05-14 08:33:53', '0');
INSERT INTO `response` VALUES ('87', '70', '2', '164', '0', '0', '0', '2020-05-14 08:43:45', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

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
INSERT INTO `title` VALUES ('57', '非计算机专业学生怎么走上计算机技术之路？');
INSERT INTO `title` VALUES ('58', '如何戒掉手机去认真学习？');
INSERT INTO `title` VALUES ('59', '请问选课投分要怎么投？');
INSERT INTO `title` VALUES ('60', '机械行业和自动化行业该如何与互联网衔接？');
INSERT INTO `title` VALUES ('61', 'test');
INSERT INTO `title` VALUES ('62', '111');
INSERT INTO `title` VALUES ('63', 'haha');
INSERT INTO `title` VALUES ('64', '222');
INSERT INTO `title` VALUES ('65', '2');
INSERT INTO `title` VALUES ('66', 'xixi');

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
  `identity` varchar(20) NOT NULL DEFAULT 'student',
  `phone_num` varchar(12) DEFAULT NULL,
  `account_data_id` int(11) DEFAULT '-1',
  PRIMARY KEY (`id`),
  KEY `user_account_data_id` (`account_data_id`),
  CONSTRAINT `user_account_data_id` FOREIGN KEY (`account_data_id`) REFERENCES `account_data` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

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
