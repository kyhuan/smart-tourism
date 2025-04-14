-- 创建景点表
CREATE TABLE IF NOT EXISTS `scenic_spot` (
  `scenic_id` varchar(64) NOT NULL COMMENT '景点ID',
  `name` varchar(255) NOT NULL COMMENT '景点名称',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `ticket_price` decimal(10,2) DEFAULT NULL COMMENT '门票价格',
  `description` text COMMENT '景点描述',
  `opening_hours` varchar(255) DEFAULT NULL COMMENT '开放时间',
  `contact_info` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  PRIMARY KEY (`scenic_id`),
  KEY `idx_name` (`name`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='景点表';

-- 创建景点图片表
CREATE TABLE IF NOT EXISTS `scenic_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `scenic_id` varchar(64) NOT NULL COMMENT '景点ID',
  `image_url` varchar(255) NOT NULL COMMENT '图片URL',
  `is_cover` tinyint(1) DEFAULT '0' COMMENT '是否为封面：0-否，1-是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_scenic_id` (`scenic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='景点图片表';

-- 创建景点分类表
CREATE TABLE IF NOT EXISTS `scenic_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_name` varchar(50) NOT NULL COMMENT '分类名称',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_category_name` (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='景点分类表';

-- 创建景点分类关联表
CREATE TABLE IF NOT EXISTS `scenic_category_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `scenic_id` varchar(64) NOT NULL COMMENT '景点ID',
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_scenic_category` (`scenic_id`,`category_id`),
  KEY `idx_scenic_id` (`scenic_id`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='景点分类关联表';

-- 插入示例分类数据
INSERT INTO `scenic_category` (`category_name`) VALUES 
('自然风光'),
('人文历史'),
('主题公园'),
('宗教场所'),
('博物馆'),
('园林'),
('特色小镇');

-- 插入示例景点数据
INSERT INTO `scenic_spot` (`scenic_id`, `name`, `location`, `ticket_price`, `description`, `opening_hours`, `contact_info`, `status`)
VALUES
('S001', '西湖', '浙江省杭州市西湖区', 0.00, '西湖，位于浙江省杭州市西面，是中国大陆首批国家重点风景名胜区和中国十大风景名胜之一。它是中国大陆主要的观赏性淡水湖泊之一，也是现今《世界遗产名录》中少数几个和中国唯一一个湖泊类文化遗产。', '全天开放', '0571-87179617', 1),

('S002', '故宫', '北京市东城区景山前街4号', 60.00, '北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。', '8:30-17:00（4月1日-10月31日），8:30-16:30（11月1日-3月31日），周一闭馆', '010-85007421', 1),

('S003', '黄山', '安徽省黄山市黄山区', 230.00, '黄山是安徽省南部的一座山脉，为三山五岳中三山之一，是安徽省最高的山脉。黄山是世界文化与自然双重遗产，世界地质公园，国家重点风景名胜区，全国文明风景旅游区，国家5A级旅游景区。', '全天开放', '0559-5561111', 1),

('S004', '长城', '北京市怀柔区', 45.00, '长城是中国古代的军事防御工程，是世界上最伟大的建筑之一，被誉为世界七大奇迹之一。长城始建于春秋战国时期，距今已有2000多年的历史。现存的长城主要为明代所建。', '7:30-17:30（4月1日-10月31日），8:00-17:00（11月1日-3月31日）', '010-61626022', 1),

('S005', '泰山', '山东省泰安市泰山区', 125.00, '泰山是中国五岳之首，位于山东省中部，主峰玉皇顶海拔1545米，气势雄伟磅礴。泰山是世界自然与文化遗产，世界地质公园，国家重点风景名胜区，国家5A级旅游景区。', '全天开放', '0538-8066666', 1);

-- 插入示例景点图片数据
INSERT INTO `scenic_image` (`scenic_id`, `image_url`, `is_cover`)
VALUES
('S001', 'xihu_01.jpg', 1),
('S001', 'xihu_02.jpg', 0),
('S001', 'xihu_03.jpg', 0),
('S002', 'gugong_01.jpg', 1),
('S002', 'gugong_02.jpg', 0),
('S002', 'gugong_03.jpg', 0),
('S003', 'huangshan_01.jpg', 1),
('S003', 'huangshan_02.jpg', 0),
('S003', 'huangshan_03.jpg', 0),
('S004', 'changcheng_01.jpg', 1),
('S004', 'changcheng_02.jpg', 0),
('S004', 'changcheng_03.jpg', 0),
('S005', 'taishan_01.jpg', 1),
('S005', 'taishan_02.jpg', 0),
('S005', 'taishan_03.jpg', 0);

-- 插入示例景点分类关联数据
INSERT INTO `scenic_category_relation` (`scenic_id`, `category_id`)
VALUES
('S001', 1), -- 西湖 - 自然风光
('S001', 6), -- 西湖 - 园林
('S002', 2), -- 故宫 - 人文历史
('S002', 5), -- 故宫 - 博物馆
('S003', 1), -- 黄山 - 自然风光
('S004', 2), -- 长城 - 人文历史
('S005', 1), -- 泰山 - 自然风光
('S005', 4); -- 泰山 - 宗教场所 