/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : team_work

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-06-04 11:53:20
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
) ENGINE=InnoDB AUTO_INCREMENT=3415 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_data
-- ----------------------------
INSERT INTO `account_data` VALUES ('-1', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3406', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3407', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3409', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3410', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3411', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3412', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3413', '0', '0', '0', '0', '0', '0');
INSERT INTO `account_data` VALUES ('3414', '0', '300', '300', '0', '15', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=479 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of block
-- ----------------------------
INSERT INTO `block` VALUES ('101', '考研板块', '考研');

-- ----------------------------
-- Table structure for `content`
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(10000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7012 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('5195', '2');
INSERT INTO `content` VALUES ('5196', '');
INSERT INTO `content` VALUES ('5197', '2');
INSERT INTO `content` VALUES ('5198', '今年被录取为某中上985自动化院的研究生，导师给我的方向是行人重识别。我是很喜欢CV这方面，但知乎上都说CV入门门槛低，落地难，不要学这个。我的职业目标就是能进个大厂搞算法或者开发都行，各位大佬有什么建议吗？');
INSERT INTO `content` VALUES ('5199', '今年被录取为某中上985自动化院的研究生，导师给我的方向是行人重识别。我是很喜欢CV这方面，但知乎上都说CV入门门槛低，落地难，不要学这个。我的职业目标就是能进个大厂搞算法或者开发都行，各位大佬有什么建议吗？');
INSERT INTO `content` VALUES ('5200', '看到一个问题：国内研究生跟了一个水货老师是什么样的体验。笑劈叉了，可是觉得很多人都还蛮苦大仇深的。\n个人感觉读研是很重要的，如果问我从小到大做的最英明的决定是什么，那一定是考上了研究生，而且重要的是要考一个大城市的研究生。\n大家要吐槽是有趣的，但是不希望很多徘徊在工作和考研这个时间节点上的宝宝们，因为看了大家的吐槽对于考研这个事情失去敬畏，失去信心。\n所以，我们来畅所欲言一下吧，讲讲你的关于青眼有加、关于知遇之恩的故事。');
INSERT INTO `content` VALUES ('5201', '学生的那些行为或者细节会被导师讨厌？\n\n导师有哪些行为的时候意味着你被讨厌了？\n\n请说些细节上的。');
INSERT INTO `content` VALUES ('5202', '既然农民那么惨，为什么会有农民？既然奴隶那么惨，为什么会有奴隶？开设机械专业，为什么要管你是否惨呢？就像养猪的把猪养大然后卖了杀掉，他会管猪惨不惨吗？他的目的是能够通过这个过程获得利益。同样的道理，为什么要开设机械这个专业？因为这个行业需要人去干活，而且准确的说，是需要以比较低的价格，雇到干比较脏累差的活。但是这样可能雇得到人吗？有点难，所以采取了一个办法：招更多的的人来机械专业。反正你爱干不干，总有的是人来干这个工作。这就形成了一条看似悖论的道理：一个专业越是差，反而越要招更多的人，以保证有充足的廉价劳动力供应。');
INSERT INTO `content` VALUES ('5203', '1');
INSERT INTO `content` VALUES ('5204', '');
INSERT INTO `content` VALUES ('5205', '2');
INSERT INTO `content` VALUES ('5206', '2');
INSERT INTO `content` VALUES ('5207', '1');
INSERT INTO `content` VALUES ('5208', '');
INSERT INTO `content` VALUES ('5209', '');
INSERT INTO `content` VALUES ('5210', '11111');
INSERT INTO `content` VALUES ('6997', '我是一个web前端开发者和rails程序员，计算机专业出身，掌握Python、Ruby、C、Java编程语言，具有较为扎实的计算机理论基础。\n现在工作之外的时间里想学习一下数据分析或者数据挖掘。现在大数据这个词搞得蛮火的，不少公司也有在招聘数据分析员。\n抱着「技多不压身」的想法我也想稍微学习一下。说不定以后的工作也会需要。\n\n我稍微了解了一下，数据分析最基础就是用excel来工作。不过我计算机专业出身的人，还是想通过用编程语言的方法来学习。听说R语言不错，我也稍微了解了一下。\n\n不过现在要学数据分析的话，我可以从哪里着手？\n\n从哪里弄到有分析价值的数据？\n\n在开源社区有没有开源项目适合用来学习数据分析？\n\n可以自己动手做个什么项目来实践？');
INSERT INTO `content` VALUES ('6998', '想考华中科大电气工程的考研电气狗一只，各路大佬有没有恰好知道谁有专业课资料的，望分享，感激不胜，感恩戴德！(^_^)');
INSERT INTO `content` VALUES ('6999', '早在 70 多年前，阿西莫夫曾经提出过著名的机器人三定律，也指出了未来科技高速发展下人工智能带来的愿景与隐忧。\n\n如今诸多互联网企业在人工智能上都有深耕，也助推着整个行业技术高速地向前推演，人工智能的成本降低、大众化普及终将成为一种必然趋势。\n\n在未来的某一天，当高居云端的人工智能和云计算真正走入寻常百姓家，对普通人的生活会产生怎样的改变呢？');
INSERT INTO `content` VALUES ('7000', '住在城市里的，多数都不愿意开学。舒服的床，随时随地洗澡，快递想有就有。\n\n而农村呢？炕，没卫生间，旱厕，快递不方便，外卖不通，没有私人空间。\n\n更极端的是可能会有部分同学网络状况不好，也或许有老师要求连麦，却不想让大家看见家里的破旧与城里孩子富丽堂皇干净整洁的家做出鲜明的对比...\n\n个人表示，是想开学的。原因如上。');
INSERT INTO `content` VALUES ('7001', '本人即将高三，有意向参加英语保送。可是高考英语听力水平蛮吃力，每次大考听力都至少错2个，最多6个。想在一个月内把英语至少提到大学四级水平，最好六级。求大神推荐方法。\n本人现在每天听两篇NCE3正常语速，听两遍，对着翻译看一遍再听一遍，感觉效果不是很大。准备做四六级真题的听力题。\n\n另外，看到很多评论说重要的是词汇量。这方面我也在练，现在正在背专四单词，同时四六级滚动复习。也是结合例句背诵记忆的。');
INSERT INTO `content` VALUES ('7002', '最近刚刚应届毕业入职公司，午休的时候想起自己之前的实习以及找工作生活等，觉得遗憾还是蛮多的，如果当时我能怎么怎么样，现在是不是就不同了呢？\n\n所以说，为了多了解一些前辈们的经验，以及为了让自己在三五年后的某一个午后，摸鱼的时候，不后悔。\n\n先谢过各位大佬啦');
INSERT INTO `content` VALUES ('7003', '目前想找一个211、985毕业的前端工程师为什么那么难？');
INSERT INTO `content` VALUES ('7004', '我最近学python，不过不知道怎么练习，不知道用来写什么。大家都用来干嘛的？都说说python可以用来写什么好玩的东东。');
INSERT INTO `content` VALUES ('7005', '有哪些软件就是坑，还没入坑能够避雷，或者已经入坑怎么出坑，分享一下经验和心得');
INSERT INTO `content` VALUES ('7006', 'k近邻、贝叶斯、决策树、svm、逻辑斯蒂回归和最大熵模型、隐马尔科夫、条件随机场、adaboost、em 这些在一般工作中分别用到的频率多大？一般用途是什么？需要注意什么？');
INSERT INTO `content` VALUES ('7007', '请分享经验之前首先告诉大家你本科是哪个大学，目标专业，是不是跨专业，谢谢');
INSERT INTO `content` VALUES ('7008', '美国的这次暴乱有可能演变为革命吗？');
INSERT INTO `content` VALUES ('7009', '不知道有没有人和我一样，毕业想回老家，不想去北上广闯荡…工作想做喜欢的，挣得少也没关系…找对象找不着也无所谓，一个人轻松自在，财务压力还小…不太想认识新的人，喜欢和家人朋友待一起，不是很喜欢“目的性社交”…\n\n最近又看了一遍瓦尔登湖，又有了好多关于欲望和生活方式的思考，在今天这个消费时代，究竟有多少东西是我们真正需要的，大众意义上的“成功”又是否适合我们所有人，精神与物质上的富足该如何取舍，总是很难取舍，迷迷糊糊\n\n我提出这个问题只是想在三观重塑的人生阶段看看更多人对待生活人生的方法，成功学也好，三和也罢，都是生活方式，我没有任何偏见\n\n我相信有很多出生于县城，或者小镇，考到大城市的大学生朋友都会有和我相似的困惑，这个问题以下的几千条回答应该可以在某种程度上帮助到大家');
INSERT INTO `content` VALUES ('7010', '本人一名大二三本专科学校学生，我知道自己不优秀，所以我十分渴望自己能变优秀，唯有努力学习才能改变命运是我一直以来所深信不疑的信念。\n\n虽然心中有坚不可摧的信念，但自己做的确实差强人意。在高中时，我强迫自己熬夜，强迫自己早晨第一个到教室，晚上最后一个出教室，强迫自己边吃饭边看书……可，我的成绩依旧不好。我觉得像我这样的人的生活很悲哀，学没学好，玩没玩好，一事无成，还让自己的内心每天都备受煎熬和打击。听别人说，学习要讲究效率，不要打时间战。我开始不这么努力了，对于学习这件事开始放松了，可结果依旧不如人意，甚至还没之前的好。我开始彷徨了，又开始像之前那样打时间战了，如此循环往复。\n\n我分不清休息和堕落的区别，我也分不清努力和伪勤奋的区别。\n\n上了大学，我依旧“努力”读书，强迫自己早起去班里读书，强迫自己下了课就直接飞奔到图书馆，强迫自己把大部分时间都花在学习上，我心里知道，我早晨读书时好困，我的眼皮在打架，我的思维也在漫无目的地神游，我心里知道，到了图书馆的第一件事是玩手机，我心里知道，很多时候我根本不想学习，我承认我厌恶学习，我感受不到学习的快乐，学习这件事就像一个沉重的包袱一样压在我身上，让我喘不过气来。但我没有办法做到我不学习，因为我要依靠学习来实现我的梦想。\n\n矛盾，纠结充盈着我的全部，迷茫，无措，让我找不到前行的方向。\n\n我不想学习，我很懒，我喜欢躺在床上玩手机，我喜欢睡懒觉，我喜欢追剧看电影，我喜欢和别人聊天出去玩，我喜欢一个普通人喜欢的一切娱乐方式!!!可，学习就像一个枷锁，限制了这些欢乐，只留下沉重在我的肩膀。\n\n我想问，怎么才能找到学习的快乐?怎么才能告别伪勤奋?');
INSERT INTO `content` VALUES ('7011', '在每年的考研大军中，为什么同样努力的考研狗，有的人可以逆袭，有的人确成为了炮灰。');

-- ----------------------------
-- Table structure for `kind`
-- ----------------------------
DROP TABLE IF EXISTS `kind`;
CREATE TABLE `kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=12969 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('12954', '2997', 'createQuestion', '您在2020-06-04 11:32:23创建了\"如何在业余时学数据分析？\"问题');
INSERT INTO `message` VALUES ('12955', '2997', 'createQuestion', '您在2020-06-04 11:32:58创建了\"考研资料哪里找？\"问题');
INSERT INTO `message` VALUES ('12956', '2997', 'createQuestion', '您在2020-06-04 11:33:51创建了\"未来十年，人工智能和云计算会让世界发生哪些改变？\"问题');
INSERT INTO `message` VALUES ('12957', '2997', 'createQuestion', '您在2020-06-04 11:34:31创建了\"家住农村的各位同学期待开学吗?\"问题');
INSERT INTO `message` VALUES ('12958', '2997', 'createQuestion', '您在2020-06-04 11:35:11创建了\"怎么在一个月内让英语听力有明显的提高？\"问题');
INSERT INTO `message` VALUES ('12959', '2997', 'createQuestion', '您在2020-06-04 11:39:13创建了\"作为一个程序员，有什么想对新人说的吗?\"问题');
INSERT INTO `message` VALUES ('12960', '2997', 'createQuestion', '您在2020-06-04 11:40:30创建了\"为什么 211/985 毕业的前端工程师那么难招？\"问题');
INSERT INTO `message` VALUES ('12961', '2997', 'createQuestion', '您在2020-06-04 11:41:02创建了\"学 Python 都用来干嘛的？\"问题');
INSERT INTO `message` VALUES ('12962', '2997', 'createQuestion', '您在2020-06-04 11:41:36创建了\"Windows 有哪些强烈不建议装的软件？\"问题');
INSERT INTO `message` VALUES ('12963', '2997', 'createQuestion', '您在2020-06-04 11:41:54创建了\"各种机器学习算法的应用场景分别是什么（比如朴素贝叶斯、决策树、K 近邻、SVM、逻辑回归最大熵模型）？\"问题');
INSERT INTO `message` VALUES ('12964', '2997', 'createQuestion', '您在2020-06-04 11:42:51创建了\"考完研，你发现了考研的哪些坑?\"问题');
INSERT INTO `message` VALUES ('12965', '2997', 'createQuestion', '您在2020-06-04 11:44:31创建了\"美国的暴乱有可能演变为社会主义革命吗？\"问题');
INSERT INTO `message` VALUES ('12966', '2997', 'createQuestion', '您在2020-06-04 11:44:52创建了\"为什么有的人年纪轻轻就没了上进心和欲望，躲在舒适区只想安逸地生存？\"问题');
INSERT INTO `message` VALUES ('12967', '2997', 'createQuestion', '您在2020-06-04 11:45:29创建了\"如何告别「伪勤奋」？\"问题');
INSERT INTO `message` VALUES ('12968', '2997', 'createQuestion', '您在2020-06-04 11:46:31创建了\"为什么有人考研付出了巨大努力，还是成为炮灰？\"问题');

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
) ENGINE=InnoDB AUTO_INCREMENT=5635 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('5620', '2997', '0', '0', '2020-06-04 11:32:23', '6997', '0', '1');
INSERT INTO `question` VALUES ('5621', '2997', '0', '0', '2020-06-04 11:32:58', '6998', '1', '3');
INSERT INTO `question` VALUES ('5622', '2997', '0', '0', '2020-06-04 11:33:51', '6999', '0', '5');
INSERT INTO `question` VALUES ('5623', '2997', '0', '0', '2020-06-04 11:34:31', '7000', '1', '4');
INSERT INTO `question` VALUES ('5624', '2997', '0', '0', '2020-06-04 11:35:11', '7001', '1', '1');
INSERT INTO `question` VALUES ('5625', '2997', '0', '0', '2020-06-04 11:39:13', '7002', '0', '2');
INSERT INTO `question` VALUES ('5626', '2997', '0', '0', '2020-06-04 11:40:30', '7003', '0', '2');
INSERT INTO `question` VALUES ('5627', '2997', '0', '0', '2020-06-04 11:41:02', '7004', '0', '1');
INSERT INTO `question` VALUES ('5628', '2997', '0', '0', '2020-06-04 11:41:36', '7005', '0', '5');
INSERT INTO `question` VALUES ('5629', '2997', '0', '0', '2020-06-04 11:41:54', '7006', '0', '1');
INSERT INTO `question` VALUES ('5630', '2997', '0', '0', '2020-06-04 11:42:51', '7007', '0', '2');
INSERT INTO `question` VALUES ('5631', '2997', '0', '0', '2020-06-04 11:44:31', '7008', '0', '5');
INSERT INTO `question` VALUES ('5632', '2997', '0', '0', '2020-06-04 11:44:52', '7009', '0', '5');
INSERT INTO `question` VALUES ('5633', '2997', '0', '0', '2020-06-04 11:45:29', '7010', '1', '4');
INSERT INTO `question` VALUES ('5634', '2997', '0', '0', '2020-06-04 11:46:31', '7011', '0', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=5635 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_title
-- ----------------------------
INSERT INTO `question_title` VALUES ('5620', '5620', '7385');
INSERT INTO `question_title` VALUES ('5621', '5621', '7386');
INSERT INTO `question_title` VALUES ('5622', '5622', '7387');
INSERT INTO `question_title` VALUES ('5623', '5623', '7388');
INSERT INTO `question_title` VALUES ('5624', '5624', '7389');
INSERT INTO `question_title` VALUES ('5625', '5625', '7390');
INSERT INTO `question_title` VALUES ('5626', '5626', '7391');
INSERT INTO `question_title` VALUES ('5627', '5627', '7392');
INSERT INTO `question_title` VALUES ('5628', '5628', '7393');
INSERT INTO `question_title` VALUES ('5629', '5629', '7394');
INSERT INTO `question_title` VALUES ('5630', '5630', '7395');
INSERT INTO `question_title` VALUES ('5631', '5631', '7396');
INSERT INTO `question_title` VALUES ('5632', '5632', '7397');
INSERT INTO `question_title` VALUES ('5633', '5633', '7398');
INSERT INTO `question_title` VALUES ('5634', '5634', '7399');

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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=1276 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=365 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=7400 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of title
-- ----------------------------
INSERT INTO `title` VALUES ('7385', '如何在业余时学数据分析？');
INSERT INTO `title` VALUES ('7386', '考研资料哪里找？');
INSERT INTO `title` VALUES ('7387', '未来十年，人工智能和云计算会让世界发生哪些改变？');
INSERT INTO `title` VALUES ('7388', '家住农村的各位同学期待开学吗?');
INSERT INTO `title` VALUES ('7389', '怎么在一个月内让英语听力有明显的提高？');
INSERT INTO `title` VALUES ('7390', '作为一个程序员，有什么想对新人说的吗?');
INSERT INTO `title` VALUES ('7391', '为什么 211/985 毕业的前端工程师那么难招？');
INSERT INTO `title` VALUES ('7392', '学 Python 都用来干嘛的？');
INSERT INTO `title` VALUES ('7393', 'Windows 有哪些强烈不建议装的软件？');
INSERT INTO `title` VALUES ('7394', '各种机器学习算法的应用场景分别是什么（比如朴素贝叶斯、决策树、K 近邻、SVM、逻辑回归最大熵模型）？');
INSERT INTO `title` VALUES ('7395', '考完研，你发现了考研的哪些坑?');
INSERT INTO `title` VALUES ('7396', '美国的暴乱有可能演变为社会主义革命吗？');
INSERT INTO `title` VALUES ('7397', '为什么有的人年纪轻轻就没了上进心和欲望，躲在舒适区只想安逸地生存？');
INSERT INTO `title` VALUES ('7398', '如何告别「伪勤奋」？');
INSERT INTO `title` VALUES ('7399', '为什么有人考研付出了巨大努力，还是成为炮灰？');

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
) ENGINE=InnoDB AUTO_INCREMENT=2998 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'MTIz', '管理员1', 'MTEwMTAxMTk5MDAzMDcwMTUw', 'administrator', null, '-1');
INSERT INTO `user` VALUES ('2989', '021700613', 'MTIz', '张三', 'MzUwMTAyMTk5MDAzMDc4MjVY', 'student', null, '3406');
INSERT INTO `user` VALUES ('2990', '221701219', 'MTIz', '李四', 'MzUwNDAyMTk5MDA4MDc3ODM0', 'student', null, '3407');
INSERT INTO `user` VALUES ('2992', '221600313', 'MTIz', '王五', 'MzUwMTAyMjAwMjAzMDc0OTUz', 'student', null, '3409');
INSERT INTO `user` VALUES ('2993', '221701118', 'MTIz', '小红', 'MzUwMTAyMjAwMjAzMDcyODUx', 'student', null, '3410');
INSERT INTO `user` VALUES ('2994', '221701136', 'MTIz', '小李', 'MzUwMTAyMjAwMjAzMDcwOTM2', 'student', null, '3411');
INSERT INTO `user` VALUES ('2995', '221701316', 'MTIz', '小王', 'MzUwMTAyMjAwMjAzMDczODM4', 'student', null, '3412');
INSERT INTO `user` VALUES ('2996', '221701335', 'MTIz', '小明', 'MzUwMTAyMjAwMjAzMDc3MTkz', 'student', null, '3413');
INSERT INTO `user` VALUES ('2997', '221701421', 'MTFY', '小白', 'MTMwNzI2MTk5OTA3MTM2MTFY', 'student', null, '3414');
