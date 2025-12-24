-- 淘票票影评社区数据库脚本
-- 创建数据库
CREATE DATABASE IF NOT EXISTS movie_community DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE movie_community;

-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    nickname VARCHAR(50) COMMENT '昵称',
    avatar VARCHAR(255) DEFAULT '/static/images/default-avatar.png' COMMENT '头像',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 电影表
DROP TABLE IF EXISTS t_movie;
CREATE TABLE t_movie (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '电影ID',
    title VARCHAR(100) NOT NULL COMMENT '电影名称',
    director VARCHAR(50) COMMENT '导演',
    actors VARCHAR(500) COMMENT '主演',
    genre VARCHAR(50) COMMENT '类型',
    region VARCHAR(50) COMMENT '地区',
    release_date DATE COMMENT '上映日期',
    duration INT COMMENT '片长(分钟)',
    poster VARCHAR(255) COMMENT '海报图片',
    synopsis TEXT COMMENT '剧情简介',
    avg_rating DECIMAL(2,1) DEFAULT 0.0 COMMENT '平均评分',
    rating_count INT DEFAULT 0 COMMENT '评分人数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电影表';

-- 评论评分表
DROP TABLE IF EXISTS t_review;
CREATE TABLE t_review (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '评论ID',
    user_id INT NOT NULL COMMENT '用户ID',
    movie_id INT NOT NULL COMMENT '电影ID',
    rating TINYINT NOT NULL COMMENT '评分(1-5星)',
    content TEXT COMMENT '评论内容',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
    UNIQUE KEY uk_user_movie (user_id, movie_id),
    FOREIGN KEY (user_id) REFERENCES t_user(id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES t_movie(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论评分表';

-- 插入测试用户数据
INSERT INTO t_user (username, password, nickname) VALUES
('admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员'),  -- 密码: 123456
('zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三'),
('lisi', 'e10adc3949ba59abbe56e057f20f883e', '李四');

-- 插入测试电影数据
INSERT INTO t_movie (title, director, actors, genre, region, release_date, duration, poster, synopsis, avg_rating, rating_count) VALUES
('肖申克的救赎', '弗兰克·德拉邦特', '蒂姆·罗宾斯,摩根·弗里曼', '剧情', '美国', '1994-09-23', 142, '/static/images/movies/shawshank.jpg', '一场谋杀案使银行家安迪蒙冤入狱，谋杀妻子及其情人的指控将囚禁他终生。在肖申克监狱的时光中，他与瑞德建立起深厚的友谊，并凭着对自由的渴望逐步救赎。', 4.8, 100),
('霸王别姬', '陈凯歌', '张国荣,张丰毅,巩俐', '剧情', '中国', '1993-01-01', 171, '/static/images/movies/farewell.jpg', '段小楼与程蝶衣是一对打小一起长大的师兄弟，两人一个演生，一个饰旦，一向配合天衣无缝，尤其一出《霸王别姬》，更是誉满京城。', 4.7, 85),
('阿甘正传', '罗伯特·泽米吉斯', '汤姆·汉克斯,罗宾·怀特', '剧情', '美国', '1994-07-06', 142, '/static/images/movies/forrest.jpg', '阿甘是个智商只有75的低能儿。在学校里为了躲避别的孩子的欺侮，听从一个朋友珍妮的话而开始"跑"。', 4.6, 78),
('泰坦尼克号', '詹姆斯·卡梅隆', '莱昂纳多·迪卡普里奥,凯特·温斯莱特', '爱情', '美国', '1997-12-19', 194, '/static/images/movies/titanic.jpg', '1912年，泰坦尼克号从英国南安普顿出发驶往美国纽约。富家女罗丝与穷画家杰克在船上相遇并坠入爱河。', 4.5, 120),
('千与千寻', '宫崎骏', '柊瑠美,入野自由,夏木真理', '动画', '日本', '2001-07-20', 125, '/static/images/movies/spirited.jpg', '千寻和爸爸妈妈一同驱车前往新家，在郊外的小路上不慎进入了神秘的隧道——Loss之旅由此开始。', 4.7, 95),
('盗梦空间', '克里斯托弗·诺兰', '莱昂纳多·迪卡普里奥,约瑟夫·高登-莱维特', '科幻', '美国', '2010-07-16', 148, '/static/images/movies/inception.jpg', '道姆·柯布是一位经验老道的窃取者，他在梦境中进行窃取行动，从人们的潜意识中窃取有价值的秘密。', 4.6, 88),
('星际穿越', '克里斯托弗·诺兰', '马修·麦康纳,安妮·海瑟薇', '科幻', '美国', '2014-11-07', 169, '/static/images/movies/interstellar.jpg', '在不远的未来，地球气候发生巨大变化，农作物相继枯萎，人类面临灭绝的威胁。', 4.8, 92),
('流浪地球', '郭帆', '吴京,屈楚萧,李光洁', '科幻', '中国', '2019-02-05', 125, '/static/images/movies/wandering.jpg', '太阳即将毁灭，人类在地球表面建造出巨大的推进器，寻找新家园。', 4.4, 110),
('我不是药神', '文牧野', '徐峥,王传君,周一围', '剧情', '中国', '2018-07-05', 117, '/static/images/movies/dying.jpg', '一位不速之客的意外到访，打破了神油店老板程勇的平凡人生。', 4.6, 105),
('哪吒之魔童降世', '饺子', '吕艳婷,囧森瑟夫,瀚墨', '动画', '中国', '2019-07-26', 110, '/static/images/movies/nezha.jpg', '天地灵气孕育出一颗能量巨大的混元珠，元始天尊将混元珠提炼成灵珠和魔丸。', 4.5, 130),
('疯狂动物城', '拜恩·霍华德,瑞奇·摩尔', '金妮弗·古德温,杰森·贝特曼', '动画', '美国', '2016-03-04', 109, '/static/images/movies/zootopia.jpg', '故事发生在一个所有哺乳类动物和谐共存的美好世界中。', 4.6, 75),
('你的名字', '新海诚', '神木隆之介,上白石萌音', '动画', '日本', '2016-12-02', 106, '/static/images/movies/yourname.jpg', '在远离大都会的小山村，住着巫女世家出身的高中女孩宫水三叶。', 4.5, 82);

-- 插入测试评论数据
INSERT INTO t_review (user_id, movie_id, rating, content) VALUES
(2, 1, 5, '这是一部关于希望和自由的伟大电影，每次看都有新的感悟。'),
(2, 2, 5, '张国荣的表演令人叹为观止，这是华语电影的巅峰之作。'),
(2, 4, 4, '经典的爱情故事，配乐很美。'),
(3, 1, 5, '肖申克的救赎告诉我们，希望是一个好东西。'),
(3, 5, 5, '宫崎骏的想象力太丰富了，每一帧都是艺术品。'),
(3, 6, 4, '烧脑神作，看了三遍才看懂。');

