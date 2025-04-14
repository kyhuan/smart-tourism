-- 创建旅游路线表
CREATE TABLE IF NOT EXISTS `travel_route` (
  `route_id` varchar(64) NOT NULL COMMENT '路线ID',
  `route_name` varchar(255) NOT NULL COMMENT '路线名称',
  `duration` int(11) DEFAULT NULL COMMENT '行程天数',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `start_point` varchar(255) DEFAULT NULL COMMENT '出发地',
  `highlights` varchar(500) DEFAULT NULL COMMENT '亮点',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签，多个用逗号分隔',
  `rating` decimal(3,1) DEFAULT '4.5' COMMENT '评分',
  `sold_count` int(11) DEFAULT '0' COMMENT '销量',
  `description` text COMMENT '描述',
  `spots` varchar(500) DEFAULT NULL COMMENT '景点，多个用逗号分隔',
  `schedule` text COMMENT '行程安排',
  `inclusion` varchar(500) DEFAULT NULL COMMENT '费用包含项',
  `exclusion` varchar(500) DEFAULT NULL COMMENT '费用不包含项',
  `notes` varchar(500) DEFAULT NULL COMMENT '注意事项',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `images` varchar(1000) DEFAULT NULL COMMENT '图片，多个用逗号分隔',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  PRIMARY KEY (`route_id`),
  KEY `idx_route_name` (`route_name`),
  KEY `idx_tags` (`tags`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='旅游路线表';

-- 插入示例数据
INSERT INTO `travel_route` (`route_id`, `route_name`, `duration`, `price`, `start_point`, `highlights`, `tags`, `rating`, `sold_count`, `description`, `spots`, `schedule`, `inclusion`, `exclusion`, `notes`, `cover_image`, `images`, `status`)
VALUES
('TR001', '杭州西湖三日游', 3, 1288.00, '杭州', '西湖美景、宋城千古情、灵隐寺、龙井茶园', '文化,自然,休闲', 4.8, 1256, '杭州西湖，中国著名的风景名胜区，被誉为人间天堂。此行程带您游览西湖十景，体验江南水乡风情。', '西湖,宋城,灵隐寺,龙井茶园,千岛湖', '第一天：西湖游船、断桥残雪、雷峰塔\n第二天：灵隐寺、飞来峰、龙井茶园\n第三天：宋城千古情、千岛湖', '往返交通、住宿、门票、导游服务、旅游保险', '个人消费、自由活动期间用餐', '请穿舒适的鞋子，带好防晒用品', 'hangzhou.jpg', 'hangzhou1.jpg,hangzhou2.jpg,hangzhou3.jpg', 1),

('TR002', '北京故宫长城五日游', 5, 2599.00, '北京', '故宫、长城、颐和园、天坛、奥林匹克公园', '文化,历史,经典', 4.9, 2345, '北京是中国的首都，拥有悠久的历史文化。此行程带您领略古都风采，感受中华文明的博大精深。', '故宫,长城,颐和园,天坛,王府井', '第一天：天安门广场、故宫\n第二天：八达岭长城、鸟巢水立方\n第三天：颐和园、圆明园\n第四天：天坛公园、王府井\n第五天：恭王府、什刹海', '往返交通、住宿、门票、导游服务、旅游保险', '个人消费、自由活动期间用餐', '爬长城需要较好的体力，请做好准备', 'beijing.jpg', 'beijing1.jpg,beijing2.jpg,beijing3.jpg', 1),

('TR003', '三亚阳光海滩度假5日游', 5, 3599.00, '三亚', '亚龙湾、天涯海角、南山寺、蜈支洲岛', '海滩,休闲,度假', 4.7, 1876, '三亚被誉为"东方夏威夷"，拥有全国最好的海滩和气候。此行程让您尽情享受阳光、沙滩、海浪。', '亚龙湾,天涯海角,南山寺,蜈支洲岛,大小洞天', '第一天：亚龙湾沙滩、海底世界\n第二天：蜈支洲岛一日游\n第三天：天涯海角、大小洞天\n第四天：南山寺、亚特兰蒂斯水世界\n第五天：自由活动、返程', '往返交通、住宿、部分景点门票、导游服务、旅游保险', '个人消费、部分景点门票、自由活动期间用餐', '请带好泳衣、防晒用品', 'sanya.jpg', 'sanya1.jpg,sanya2.jpg,sanya3.jpg', 1),

('TR004', '成都熊猫基地+九寨沟7日游', 7, 4999.00, '成都', '大熊猫繁育基地、九寨沟、黄龙、都江堰、青城山', '自然,文化,美食', 4.9, 1562, '成都是一座来了就不想走的城市，有大熊猫、有美食、有美景。此行程带您领略天府之国的独特魅力。', '大熊猫繁育基地,九寨沟,黄龙,都江堰,青城山,锦里古街', '第一天：抵达成都、锦里古街\n第二天：大熊猫繁育基地、宽窄巷子\n第三天：都江堰、青城山\n第四-五天：九寨沟\n第六天：黄龙\n第七天：返回成都、自由活动', '往返交通、住宿、门票、导游服务、旅游保险', '个人消费、自由活动期间用餐', '九寨沟海拔较高，请注意高原反应', 'chengdu.jpg', 'chengdu1.jpg,chengdu2.jpg,chengdu3.jpg', 1); 