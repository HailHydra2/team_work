/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : team_work

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-05-02 10:03:33
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_data
-- ----------------------------
INSERT INTO `account_data` VALUES ('-1', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('1', '0', '180', '172', '12', '6', '6');
INSERT INTO `account_data` VALUES ('2', '0', '0', '1', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('4', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('5', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('6', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('7', '0', '0', '0', '0', '0', '0');

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
  CONSTRAINT `attention_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `attention_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attention
-- ----------------------------
INSERT INTO `attention` VALUES ('13', '2', '28', '2020-05-02 01:00:12', '0');
INSERT INTO `attention` VALUES ('14', '2', '26', '2020-05-02 10:00:12', '0');
INSERT INTO `attention` VALUES ('15', '2', '27', '2020-05-02 10:02:17', '1');

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
INSERT INTO `block` VALUES ('6', '开学板块', '开学');

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('55', '谐音记忆法\n\n这可能是最多人使用的一种背单词方法了，比如“三克油，爱老虎油”之类的。比如单词 ambition 谐音“俺必胜”，表示要有“雄心”和“野心”才能取得胜利。电影《中国合伙人》也用了“安不能死”的谐音来记单词“ambulance”。但这种能用搞笑谐音来记的单词毕竟是少数，而且副作用大，特别容易导致发音不准。如果你有志于把英语学好，这种方法瞄一眼就好，没看到也一点不吃亏。');
INSERT INTO `content` VALUES ('56', '联想记忆法\n\n通过联想进行记忆是利用事物间的联系，在大脑里想到 A 事物就引起想到 B 事物。联想记忆法通常会和谐音记忆法结合着使用，这种方法对于初学英语的人来说的确有帮助，比如高频词“一月到十二月”的英文单词，这些单词的起源与西方神话故事有关，对于初学英语的人来说，信息量有些太大，使用联想编故事的方法可以降低记忆难度。同样，这种方法只能当做是背单词的调节剂，用在部分单词上是有效的，如果单词数量增大，把编的故事记住，难度也不亚于直接背这些单词。');
INSERT INTO `content` VALUES ('60', '充实大学生活是一个不断积累的过程，因为大学本身就是一个黄金积累时期，从技能的学习（ps、pr、office、编程语言），交际能力的提升，知识的存储，兴趣爱好的发展（摄影、骑行、吉他、跳舞），甚至包括爱情经验的收获一共五个板块入手。\n');
INSERT INTO `content` VALUES ('61', '不要让宿舍成为你堕落的温床，本来计划看书一小时，室友一叫开黑，你连犹豫都没有直接打开了游戏，在宿舍的学习效率是真的很低，而且那些“教室——宿舍”两点一线，三餐都吃外卖的大学生不在少数。你要走出去！学习去图书馆，跑步去操场，弹吉他去草坪，让你的时间效益最大化。。');
INSERT INTO `content` VALUES ('62', '嘻嘻');
INSERT INTO `content` VALUES ('63', '1');
INSERT INTO `content` VALUES ('64', '给大一小白一点建议吧，救救孩子～');
INSERT INTO `content` VALUES ('65', '低调奢华有内涵，不要刻意表现自己，那样显得很傻。当年的我也不懂这个道理，其实人都有嫉妒之心，千万不要在你还没做到的时候把话说得太满。我记得我刚上大学的时候，老师让我们每个人说自己今后的理想。我同学说，想当高中老师，想考公务员等等，我当时想这些人是土鳖吧，我说我的理想是走遍世界。然后被我寝室长记了四年，经常笑我。不过可惜我现在真的走遍世界咯，因为我拥有了一本全球通行的护照～');
INSERT INTO `content` VALUES ('66', '1、姓名来源，例如：因为XX，所以叫XX名字2、从爱好上，例如：我是喜欢打篮球的XXX3、从性格标签上，例如：古灵精怪就是我，我就是XXX（要开车的自行车 ）4、最近发生的事情，例如：我就是一个暑假胖了5斤的XXX☝️相信先抑后扬的开场白会令同学们印象更深刻噢~');
INSERT INTO `content` VALUES ('67', '想问一下大家，备考的时候，用买什么资料吗？考前如何调整心态？如果没考好，如何进行复盘和备考下一次，可以极大的提升通过率？');
INSERT INTO `content` VALUES ('68', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('69', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('70', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('71', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('72', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('73', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('74', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('75', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('76', '虽然裸考很爽，但绝不建议! 你可以不五点起床开始背单词，因为我总觉得背单词这件事，可能并不适合所有人。但是! 既然不想突击记单词，那就得做好长期和单词厮磨的准备，不然真以为卷子是自己出的? ? 单词的重要性，毋庸置疑!无论是听力，阅读，还是写作，都得听得懂，认得来，写的出，对不对 !2.针对底子一般的同学，想清楚你要从哪拿分? 由此你的时间侧重点在哪?良心建议：听力提分的空间，拉分的空间要大很多；其次是阅读，再是写作。');
INSERT INTO `content` VALUES ('77', '2');
INSERT INTO `content` VALUES ('78', '2');
INSERT INTO `content` VALUES ('79', '3');
INSERT INTO `content` VALUES ('80', '2');
INSERT INTO `content` VALUES ('81', '3');
INSERT INTO `content` VALUES ('82', '4');
INSERT INTO `content` VALUES ('83', '5');
INSERT INTO `content` VALUES ('84', '6');
INSERT INTO `content` VALUES ('85', '7');

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
  CONSTRAINT `like_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_response_id` FOREIGN KEY (`response_id`) REFERENCES `response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES ('12', '49', '2', '-1');
INSERT INTO `likes` VALUES ('13', '50', '2', '-1');
INSERT INTO `likes` VALUES ('14', '51', '2', '-1');
INSERT INTO `likes` VALUES ('15', '38', '2', '-1');
INSERT INTO `likes` VALUES ('16', '39', '2', '-1');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('3', '2', 'response', '学生1回复了您\"四级英语考试如何准备呢？\"的问题');
INSERT INTO `message` VALUES ('4', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('5', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('6', '2', 'response', '学生1回复了您\"1\"的问题');
INSERT INTO `message` VALUES ('7', '3', 'likeResponse', '学生1点赞了您的回复');
INSERT INTO `message` VALUES ('8', '3', 'likeResponse', '学生1点赞了您的回复');
INSERT INTO `message` VALUES ('9', '2', 'createQuestion', '您在Sat May 02 09:13:59 CST 2020创建了\"2\"问题');
INSERT INTO `message` VALUES ('10', '2', 'createQuestion', '您在Sat May 02 09:14:36 CST 2020创建了\"3\"问题');
INSERT INTO `message` VALUES ('11', '2', 'createQuestion', '您在Sat May 02 09:44:05 CST 2020创建了\"4\"问题');
INSERT INTO `message` VALUES ('12', '2', 'createQuestion', '您在Sat May 02 09:44:18 CST 2020创建了\"5\"问题');
INSERT INTO `message` VALUES ('13', '2', 'createQuestion', '您在Sat May 02 09:45:36 CST 2020创建了\"6\"问题');
INSERT INTO `message` VALUES ('14', '2', 'createQuestion', '您在Sat May 02 09:45:52 CST 2020创建了\"7\"问题');

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
  CONSTRAINT `question_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  CONSTRAINT `question_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('26', '2', '2', '2', '2020-05-01 23:29:01', '63');
INSERT INTO `question` VALUES ('27', '2', '0', '0', '2020-05-01 23:29:23', '64');
INSERT INTO `question` VALUES ('28', '3', '0', '0', '2020-05-01 23:54:07', '67');
INSERT INTO `question` VALUES ('29', '2', '0', '0', '2020-05-02 09:13:59', '80');
INSERT INTO `question` VALUES ('30', '2', '0', '0', '2020-05-02 09:14:36', '81');
INSERT INTO `question` VALUES ('31', '2', '0', '0', '2020-05-02 09:44:05', '82');
INSERT INTO `question` VALUES ('32', '2', '0', '0', '2020-05-02 09:44:18', '83');
INSERT INTO `question` VALUES ('33', '2', '0', '0', '2020-05-02 09:45:36', '84');
INSERT INTO `question` VALUES ('34', '2', '0', '0', '2020-05-02 09:45:52', '85');

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
  CONSTRAINT `title_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_title_id` FOREIGN KEY (`title_id`) REFERENCES `title` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_title
-- ----------------------------
INSERT INTO `question_title` VALUES ('21', '26', '21');
INSERT INTO `question_title` VALUES ('22', '27', '22');
INSERT INTO `question_title` VALUES ('23', '28', '23');
INSERT INTO `question_title` VALUES ('24', '29', '24');
INSERT INTO `question_title` VALUES ('25', '30', '25');
INSERT INTO `question_title` VALUES ('26', '31', '26');
INSERT INTO `question_title` VALUES ('27', '32', '27');
INSERT INTO `question_title` VALUES ('28', '33', '28');
INSERT INTO `question_title` VALUES ('29', '34', '29');

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
  CONSTRAINT `report_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_reportor_id` FOREIGN KEY (`reportor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_question
-- ----------------------------
INSERT INTO `report_question` VALUES ('7', '2', '26', '0');
INSERT INTO `report_question` VALUES ('8', '2', '27', '0');

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
  CONSTRAINT `response_reportor_id` FOREIGN KEY (`reportor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_response_id` FOREIGN KEY (`response_id`) REFERENCES `response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_response
-- ----------------------------
INSERT INTO `report_response` VALUES ('7', '2', '38', '0');
INSERT INTO `report_response` VALUES ('8', '2', '49', '0');

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
  KEY `response_content_id` (`content_id`),
  KEY `response_question_id` (`question_id`),
  CONSTRAINT `response_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `response_content_id` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of response
-- ----------------------------
INSERT INTO `response` VALUES ('38', '27', '3', '65', '0', '1', '0', '2020-05-01 23:37:40');
INSERT INTO `response` VALUES ('39', '27', '3', '66', '0', '1', '0', '2020-05-01 23:40:10');
INSERT INTO `response` VALUES ('40', '28', '2', '68', '0', '0', '0', '2020-05-02 00:32:07');
INSERT INTO `response` VALUES ('41', '28', '2', '69', '0', '0', '0', '2020-05-02 00:32:14');
INSERT INTO `response` VALUES ('42', '28', '2', '70', '0', '0', '0', '2020-05-02 00:35:17');
INSERT INTO `response` VALUES ('43', '28', '2', '71', '0', '0', '0', '2020-05-02 00:36:52');
INSERT INTO `response` VALUES ('44', '28', '2', '72', '0', '0', '0', '2020-05-02 00:38:10');
INSERT INTO `response` VALUES ('45', '28', '2', '73', '0', '0', '0', '2020-05-02 00:39:45');
INSERT INTO `response` VALUES ('46', '28', '2', '74', '0', '0', '0', '2020-05-02 00:41:16');
INSERT INTO `response` VALUES ('47', '28', '2', '75', '0', '0', '0', '2020-05-02 00:43:56');
INSERT INTO `response` VALUES ('48', '28', '2', '76', '0', '0', '0', '2020-05-02 00:46:19');
INSERT INTO `response` VALUES ('49', '26', '2', '77', '0', '3', '0', '2020-05-02 00:46:54');
INSERT INTO `response` VALUES ('50', '26', '2', '78', '0', '1', '0', '2020-05-02 00:51:43');
INSERT INTO `response` VALUES ('51', '26', '2', '79', '0', '0', '0', '2020-05-02 00:51:48');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123', '管理员1', '123', 'administrator', null, '-1');
INSERT INTO `user` VALUES ('2', 's1', '1', '学生1', '112', 'student', null, '1');
INSERT INTO `user` VALUES ('3', 's2', '2', '学生2', '123', 'student', null, '2');
INSERT INTO `user` VALUES ('4', 't1', '3', '老师1', '123', 'teacher', null, '3');
INSERT INTO `user` VALUES ('5', 's3', '3', '学生3', '123', 'student', null, '4');
INSERT INTO `user` VALUES ('7', 's4', '234', '学生4', '1234', 'student', null, '6');
INSERT INTO `user` VALUES ('8', 'admin2', '345', '管理员2', '12345', 'administrator', null, '-1');
INSERT INTO `user` VALUES ('9', 't2', '234', '教师2', '1234', 'teacher', null, '7');
