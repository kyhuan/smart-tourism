/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012 (8.0.12)
 Source Host           : localhost:3306
 Source Schema         : smart_tourism

 Target Server Type    : MySQL
 Target Server Version : 80012 (8.0.12)
 File Encoding         : 65001

 Date: 16/04/2025 05:30:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `admin_username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '管理员用户名',
  `admin_password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `admin_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工号',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `admin_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限角色ID',
  PRIMARY KEY (`admin_username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理端用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('admin', 'admin123', 'A0001', 'admin@tourism.com', '13900001111', '2023-12-01 09:00:00', 'ROLE_ADMIN');
INSERT INTO `admin_info` VALUES ('customer_service1', 'cs123', 'A0005', 'cs1@tourism.com', '13900005555', '2023-12-05 16:00:00', 'ROLE_CS');
INSERT INTO `admin_info` VALUES ('customer_service2', 'cs123', 'A0006', 'cs2@tourism.com', '13900006666', '2023-12-06 09:30:00', 'ROLE_CS');
INSERT INTO `admin_info` VALUES ('finance1', 'finance123', 'A0007', 'finance1@tourism.com', '13900007777', '2023-12-07 10:45:00', 'ROLE_FINANCE');
INSERT INTO `admin_info` VALUES ('finance2', 'finance123', 'A0008', 'finance2@tourism.com', '13900008888', '2023-12-08 13:15:00', 'ROLE_FINANCE');
INSERT INTO `admin_info` VALUES ('manager', 'manager123', 'A0002', 'manager@tourism.com', '13900002222', '2023-12-02 10:15:00', 'ROLE_MANAGER');
INSERT INTO `admin_info` VALUES ('marketing', 'marketing123', 'A0009', 'marketing@tourism.com', '13900009999', '2023-12-09 15:30:00', 'ROLE_MARKETING');
INSERT INTO `admin_info` VALUES ('operator1', 'operator123', 'A0003', 'operator1@tourism.com', '13900003333', '2023-12-03 11:30:00', 'ROLE_OPERATOR');
INSERT INTO `admin_info` VALUES ('operator2', 'operator123', 'A0004', 'operator2@tourism.com', '13900004444', '2023-12-04 14:45:00', 'ROLE_OPERATOR');
INSERT INTO `admin_info` VALUES ('superadmin', 'super123', 'A0010', 'superadmin@tourism.com', '13900000000', '2023-12-10 10:00:00', 'ROLE_SUPER_ADMIN');

-- ----------------------------
-- Table structure for comment_content
-- ----------------------------
DROP TABLE IF EXISTS `comment_content`;
CREATE TABLE `comment_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户ID',
  `content_id` bigint(20) NULL DEFAULT NULL COMMENT '内容ID',
  `content_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容类型：scenic-景点 hotel-酒店 route-路线 travel-游记',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评论内容',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态：0-待审核 1-审核通过 2-审核拒绝',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `audit_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人ID',
  `audit_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_content`(`content_id` ASC, `content_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_content
-- ----------------------------
INSERT INTO `comment_content` VALUES (1, 'U20001', 1, 'travel', '文章写得很棒，让我对故宫有了更深入的了解，期待作者的下一篇游记！', 1, '2023-08-16 09:23:15', '2023-08-16 09:23:15', '2023-08-16 10:30:22', 'A1001', '正常评论');
INSERT INTO `comment_content` VALUES (2, 'U20002', 1, 'travel', '请问故宫的门票现在是多少钱？需要提前预约吗？', 1, '2023-08-16 11:45:33', '2023-08-16 11:45:33', '2023-08-16 14:15:28', 'A1002', '正常评论');
INSERT INTO `comment_content` VALUES (3, 'U20003', 2, 'travel', '桂林真的太美了，我上个月也去了，完全被那里的山水所震撼！', 1, '2023-09-04 13:22:45', '2023-09-04 13:22:45', '2023-09-04 15:30:12', 'A1001', '正常评论');
INSERT INTO `comment_content` VALUES (4, 'U20004', 3, 'travel', '丽江是我最喜欢的旅游城市之一，古城的夜景确实很美，推荐大家一定要去！', 1, '2023-10-14 08:33:27', '2023-10-14 08:33:27', '2023-10-14 10:45:36', 'A1003', '正常评论');
INSERT INTO `comment_content` VALUES (5, 'U20005', 5, 'travel', '环青海湖骑行一直是我的梦想，请问作者是几月份去的？天气如何？', 1, '2023-07-24 14:56:23', '2023-07-24 14:56:23', '2023-07-24 16:22:18', 'A1002', '正常评论');
INSERT INTO `comment_content` VALUES (6, 'U20006', 7, 'travel', '去西藏需要注意高原反应，建议提前服用红景天等药物适应高原环境。', 1, '2023-06-20 09:44:55', '2023-06-20 09:44:55', '2023-06-20 11:33:27', 'A1001', '正常评论');
INSERT INTO `comment_content` VALUES (7, 'U20007', 9, 'travel', '我也去过成都熊猫基地，建议大家早上去，因为熊猫那时候比较活跃！', 1, '2023-05-01 10:12:38', '2023-05-01 10:12:38', '2023-05-01 13:45:22', 'A1002', '正常评论');
INSERT INTO `comment_content` VALUES (8, 'U20008', 10, 'travel', '张家界的玻璃栈道我光看照片就腿软，作者真勇敢！', 1, '2023-05-17 16:28:44', '2023-05-17 16:28:44', '2023-05-17 18:15:33', 'A1003', '正常评论');
INSERT INTO `comment_content` VALUES (9, 'U20009', 5, 'travel', '这里有很多便宜的假货，大家别上当！联系我微信买正品：xyz123', 2, '2023-07-25 11:39:27', '2023-07-25 11:39:27', '2023-07-25 14:22:18', 'A1001', '含有广告信息，审核拒绝');
INSERT INTO `comment_content` VALUES (10, 'U20010', 4, 'travel', '香港迪士尼比上海的小，但是人少一些，更容易玩到热门项目，推荐！', 0, '2023-11-06 15:22:48', '2023-11-06 15:22:48', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for itinerary_info
-- ----------------------------
DROP TABLE IF EXISTS `itinerary_info`;
CREATE TABLE `itinerary_info`  (
  `itinerary_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '行程ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `start_location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '起始地点',
  `end_location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '终点地点',
  `via_locations` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '途经景点',
  `departure_time` datetime NOT NULL COMMENT '出发时间',
  `arrival_time` datetime NOT NULL COMMENT '到达时间',
  `duration` int(11) NULL DEFAULT NULL COMMENT '行程时长（天）',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '描述',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`itinerary_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '行程信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of itinerary_info
-- ----------------------------
INSERT INTO `itinerary_info` VALUES ('I0001', 'M10001', '北京', '西安', '洛阳,郑州', '2025-05-01 09:00:00', '2025-05-07 18:00:00', 7, '古都文化之旅，游览北京、洛阳、郑州、西安四大古都的文化遗产。', '2024-02-01 10:00:00', '2024-03-15 14:30:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0002', 'M10002', '上海', '杭州', '苏州,乌镇', '2025-05-10 08:00:00', '2025-05-15 19:00:00', 6, '江南水乡文化之旅，探索江南水乡的独特魅力。', '2024-02-05 11:15:00', '2024-03-16 15:45:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0003', 'M10003', '广州', '三亚', '深圳,珠海,海口', '2025-06-01 10:00:00', '2025-06-08 20:00:00', 8, '南方滨海休闲之旅，体验南中国海的美丽风光。', '2024-02-10 12:30:00', '2024-03-17 16:00:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0004', 'M10004', '成都', '九寨沟', '都江堰,黄龙', '2025-06-15 07:00:00', '2025-06-21 21:00:00', 7, '四川自然风光探索之旅，领略川西高原的壮美景色。', '2024-02-15 13:45:00', '2024-03-18 17:15:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0005', 'M10005', '昆明', '丽江', '大理,香格里拉', '2025-07-01 08:30:00', '2025-07-10 18:30:00', 10, '云南民族文化与自然风光之旅，探索云南多元文化。', '2024-02-20 14:00:00', '2024-03-19 09:30:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0006', 'M10006', '哈尔滨', '长春', '佳木斯,牡丹江', '2025-07-15 09:30:00', '2025-07-20 17:30:00', 6, '东北文化之旅，体验东北地区的特色文化和自然风光。', '2024-02-25 15:15:00', '2024-03-20 10:45:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0007', 'M10007', '南京', '黄山', '苏州,杭州', '2025-08-01 07:00:00', '2025-08-07 19:00:00', 7, '江浙名山名城之旅，领略江南山水与古城风韵。', '2024-03-01 16:30:00', '2024-03-21 11:00:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0008', 'M10008', '西安', '敦煌', '兰州,嘉峪关', '2025-08-15 06:00:00', '2025-08-25 20:00:00', 11, '丝绸之路探索之旅，追寻古代商贸之路的历史足迹。', '2024-03-05 17:45:00', '2024-03-22 13:15:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0009', 'M10009', '重庆', '宜昌', '奉节,巫山', '2025-09-01 09:00:00', '2025-09-05 17:00:00', 5, '长江三峡游轮之旅，欣赏长江三峡的壮美风光。', '2024-03-10 09:00:00', '2024-03-23 14:30:00', 1);
INSERT INTO `itinerary_info` VALUES ('I0010', 'M10010', '拉萨', '林芝', '山南,日喀则', '2025-09-15 08:00:00', '2025-09-25 18:00:00', 11, '西藏高原探索之旅，感受世界屋脊的神秘魅力。', '2024-03-15 10:15:00', '2024-03-24 15:45:00', 1);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `product_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品类型（如景点、酒店）',
  `product_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品ID',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `total_price` decimal(10, 2) NOT NULL COMMENT '总价格',
  `payment_status` tinyint(1) NULL DEFAULT 0 COMMENT '支付状态（0未支付，1已支付）',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint(1) NULL DEFAULT 0 COMMENT '订单状态（0待处理，1已完成，2已取消）',
  `order_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('O0001', 'M10001', '景点门票', 'S0001', '故宫博物院', 2, 120.00, 1, '2024-03-01 15:30:00', 1, '2024-03-01 15:00:00', '2024-03-01 15:30:00');
INSERT INTO `order_info` VALUES ('O0002', 'M10002', '酒店住宿', 'H0003', '杭州西子湖四季酒店', 3, 5400.00, 1, '2024-03-02 16:45:00', 1, '2024-03-02 16:00:00', '2024-03-02 16:45:00');
INSERT INTO `order_info` VALUES ('O0003', 'M10003', '景点门票', 'S0005', '桂林山水', 4, 480.00, 1, '2024-03-03 14:15:00', 1, '2024-03-03 14:00:00', '2024-03-03 14:15:00');
INSERT INTO `order_info` VALUES ('O0004', 'M10004', '酒店住宿', 'H0009', '三亚亚龙湾万豪度假酒店', 2, 2560.00, 1, '2024-03-04 17:30:00', 1, '2024-03-04 17:00:00', '2024-03-04 17:30:00');
INSERT INTO `order_info` VALUES ('O0005', 'M10005', '景点门票', 'S0010', '莫高窟', 2, 476.00, 1, '2024-03-05 11:45:00', 1, '2024-03-05 11:30:00', '2024-03-05 11:45:00');
INSERT INTO `order_info` VALUES ('O0006', 'M10006', '酒店住宿', 'H0002', '上海半岛酒店', 1, 2100.00, 1, '2024-03-06 13:00:00', 1, '2024-03-06 12:45:00', '2024-03-06 13:00:00');
INSERT INTO `order_info` VALUES ('O0007', 'M10007', '景点门票', 'S0008', '泰山', 3, 375.00, 0, NULL, 0, '2024-03-07 10:15:00', '2024-03-07 10:15:00');
INSERT INTO `order_info` VALUES ('O0008', 'M10008', '酒店住宿', 'H0004', '广州白天鹅宾馆', 4, 3192.00, 0, NULL, 0, '2024-03-08 11:30:00', '2024-03-08 11:30:00');
INSERT INTO `order_info` VALUES ('O0009', 'M10009', '景点门票', 'S0006', '九寨沟', 2, 380.00, 0, NULL, 0, '2024-03-09 14:45:00', '2024-03-09 14:45:00');
INSERT INTO `order_info` VALUES ('O0010', 'M10010', '酒店住宿', 'H0010', '西安凯悦酒店', 3, 2040.00, 0, NULL, 0, '2024-03-10 16:00:00', '2024-03-10 16:00:00');
INSERT INTO `order_info` VALUES ('O0011', 'M10001', '景点门票', 'S0002', '长城', 5, 200.00, 0, NULL, 2, '2024-03-11 09:30:00', '2024-03-12 10:00:00');
INSERT INTO `order_info` VALUES ('O0012', 'M10002', '酒店住宿', 'H0005', '成都香格里拉大酒店', 2, 1500.00, 0, NULL, 2, '2024-03-12 10:45:00', '2024-03-13 11:15:00');

-- ----------------------------
-- Table structure for portal_hotel_info
-- ----------------------------
DROP TABLE IF EXISTS `portal_hotel_info`;
CREATE TABLE `portal_hotel_info`  (
  `portal_hotel_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '酒店ID',
  `hotel_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '酒店名称',
  `hotel_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '酒店地址',
  `room_types` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '房型信息',
  `price` decimal(10, 2) NOT NULL COMMENT '房价',
  `rating` decimal(3, 1) NULL DEFAULT NULL COMMENT '评分',
  `amenities` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '酒店设施',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '酒店简介',
  `contact_info` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `hotel_create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `hotel_update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0停用，1启用）',
  PRIMARY KEY (`portal_hotel_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '酒店信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of portal_hotel_info
-- ----------------------------
INSERT INTO `portal_hotel_info` VALUES ('H0001', '北京国际饭店', '北京市东城区建国门内大街9号', '标准间,豪华间,套房', 688.00, 4.8, '免费WiFi,游泳池,健身中心,餐厅,商务中心', '北京国际饭店是一家五星级豪华酒店，提供舒适的住宿和优质的服务。', '010-65132345', '2024-01-01 13:00:00', '2024-03-01 11:30:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0002', '上海半岛酒店', '上海市黄浦区中山东一路32号', '高级间,豪华间,行政套房', 2100.00, 4.9, '免费WiFi,游泳池,SPA,健身中心,多家餐厅', '上海半岛酒店是一家豪华五星级酒店，位于外滩核心区域，拥有绝佳的黄浦江景观。', '021-23456789', '2024-01-02 13:30:00', '2024-03-02 12:45:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0003', '杭州西子湖四季酒店', '浙江省杭州市西湖区灵隐路5号', '湖景房,园景房,家庭套房', 1800.00, 4.7, '免费WiFi,游泳池,SPA,健身中心,湖景房', '杭州西子湖四季酒店坐落于西湖边，环境优美，设施一流。', '0571-87654321', '2024-01-03 14:00:00', '2024-03-03 14:00:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0004', '广州白天鹅宾馆', '广东省广州市越秀区沿江东路1号', '标准间,高级间,行政套房', 798.00, 4.6, '免费WiFi,游泳池,会议室,多家餐厅', '广州白天鹅宾馆是广州历史最悠久的五星级酒店之一，位于珠江畔。', '020-34567890', '2024-01-04 14:30:00', '2024-03-04 15:15:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0005', '成都香格里拉大酒店', '四川省成都市锦江区滨江东路9号', '豪华间,行政间,套房', 750.00, 4.7, '免费WiFi,游泳池,健身中心,SPA,会议设施', '成都香格里拉大酒店位于成都市中心，是商务和休闲旅客的理想选择。', '028-45678901', '2024-01-05 15:00:00', '2024-03-05 16:30:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0006', '深圳福田香格里拉大酒店', '广东省深圳市福田区益田路4088号', '豪华间,商务套房,总统套房', 880.00, 4.8, '免费WiFi,游泳池,健身中心,商务中心', '深圳福田香格里拉大酒店是一家豪华五星级酒店，靠近会展中心。', '0755-56789012', '2024-01-06 15:30:00', '2024-03-06 17:45:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0007', '南京金陵饭店', '江苏省南京市鼓楼区汉中路2号', '标准间,高级间,套房', 620.00, 4.5, '免费WiFi,餐厅,会议室,商务中心', '南京金陵饭店是南京的地标性建筑，提供高品质的服务和设施。', '025-67890123', '2024-01-07 16:00:00', '2024-03-07 09:00:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0008', '青岛海景花园大酒店', '山东省青岛市市南区燕儿岛路18号', '海景房,豪华间,套房', 550.00, 4.6, '免费WiFi,游泳池,海景房,餐厅', '青岛海景花园大酒店位于青岛海滨，拥有壮丽的海景和舒适的客房。', '0532-78901234', '2024-01-08 16:30:00', '2024-03-08 10:15:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0009', '三亚亚龙湾万豪度假酒店', '海南省三亚市亚龙湾国家旅游度假区', '海景房,花园房,别墅', 1280.00, 4.9, '免费WiFi,私人海滩,多个游泳池,SPA,水上运动', '三亚亚龙湾万豪度假酒店位于美丽的亚龙湾，提供豪华的度假体验。', '0898-89012345', '2024-01-09 17:00:00', '2024-03-09 11:30:00', 1);
INSERT INTO `portal_hotel_info` VALUES ('H0010', '西安凯悦酒店', '陕西省西安市碑林区南关正街98号', '标准间,高级间,套房', 680.00, 4.7, '免费WiFi,健身中心,SPA,餐厅,会议设施', '西安凯悦酒店位于西安古城墙附近，结合了现代豪华与古城魅力。', '029-90123456', '2024-01-10 17:30:00', '2024-03-10 12:45:00', 1);

-- ----------------------------
-- Table structure for review_info
-- ----------------------------
DROP TABLE IF EXISTS `review_info`;
CREATE TABLE `review_info`  (
  `review_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `product_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论对象类型',
  `product_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论对象ID',
  `rating` decimal(3, 1) NULL DEFAULT NULL COMMENT '评分',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `comment_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `reply_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '回复内容',
  `reply_date` datetime NULL DEFAULT NULL COMMENT '回复时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态（0待审核，1已审核）',
  PRIMARY KEY (`review_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '评论信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review_info
-- ----------------------------
INSERT INTO `review_info` VALUES ('R0001', 'M10001', '景点', 'S0001', 4.8, '故宫太震撼了，文化底蕴深厚，一天根本逛不完，下次还要再来。', '2024-03-05 16:30:00', '感谢您的评价，欢迎再次光临故宫博物院！', '2024-03-06 09:00:00', 1);
INSERT INTO `review_info` VALUES ('R0002', 'M10002', '酒店', 'H0003', 4.9, '西子湖四季酒店位置绝佳，服务一流，从房间就能看到西湖美景，非常棒！', '2024-03-07 17:45:00', '感谢您对我们酒店的肯定，期待您的再次光临！', '2024-03-08 10:15:00', 1);
INSERT INTO `review_info` VALUES ('R0003', 'M10003', '景点', 'S0005', 4.7, '桂林山水确实名不虚传，漓江两岸的喀斯特地貌太美了，坐竹筏漂流很舒服。', '2024-03-09 15:00:00', '谢谢您的评价，桂林欢迎您再次前来游玩！', '2024-03-10 11:30:00', 1);
INSERT INTO `review_info` VALUES ('R0004', 'M10004', '酒店', 'H0009', 5.0, '三亚亚龙湾万豪度假酒店简直是度假天堂，沙滩干净，设施齐全，服务周到。', '2024-03-11 18:15:00', '感谢您的高度评价，我们将继续努力提供卓越服务！', '2024-03-12 09:45:00', 1);
INSERT INTO `review_info` VALUES ('R0005', 'M10005', '景点', 'S0010', 4.8, '莫高窟的壁画太精彩了，导游讲解得非常详细，了解了很多佛教艺术知识。', '2024-03-13 16:30:00', '感谢您的好评，期待您的再次到访！', '2024-03-14 10:00:00', 1);
INSERT INTO `review_info` VALUES ('R0006', 'M10006', '酒店', 'H0002', 4.8, '上海半岛酒店的服务堪称完美，房间装修典雅，早餐丰盛，地理位置也很便捷。', '2024-03-15 17:45:00', '非常感谢您对我们酒店的认可，期待再次为您服务！', '2024-03-16 11:15:00', 1);
INSERT INTO `review_info` VALUES ('R0007', 'M10007', '景点', 'S0008', 4.6, '爬泰山很累但很值得，日出美景令人终生难忘，建议大家准备充足后再登山。', '2024-03-17 15:00:00', NULL, NULL, 0);
INSERT INTO `review_info` VALUES ('R0008', 'M10008', '酒店', 'H0004', 4.5, '白天鹅宾馆历史悠久，虽然设施不是最新的，但服务很到位，地理位置优越。', '2024-03-19 18:15:00', NULL, NULL, 0);
INSERT INTO `review_info` VALUES ('R0009', 'M10009', '景点', 'S0006', 4.9, '九寨沟的水色太美了，五彩池、熊猫海各具特色，秋天来观赏红叶更美。', '2024-03-21 16:30:00', NULL, NULL, 0);
INSERT INTO `review_info` VALUES ('R0010', 'M10010', '酒店', 'H0010', 4.7, '西安凯悦酒店地理位置优越，去钟楼鼓楼都很方便，房间舒适，服务一流。', '2024-03-23 17:45:00', NULL, NULL, 0);

-- ----------------------------
-- Table structure for scenic_category
-- ----------------------------
DROP TABLE IF EXISTS `scenic_category`;
CREATE TABLE `scenic_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_category_name`(`category_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '景点分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_category
-- ----------------------------
INSERT INTO `scenic_category` VALUES (1, '自然风光', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_category` VALUES (2, '人文历史', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_category` VALUES (3, '主题公园', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_category` VALUES (4, '宗教场所', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_category` VALUES (5, '博物馆', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_category` VALUES (6, '园林', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_category` VALUES (7, '特色小镇', 1, '2025-04-14 03:05:19');

-- ----------------------------
-- Table structure for scenic_category_relation
-- ----------------------------
DROP TABLE IF EXISTS `scenic_category_relation`;
CREATE TABLE `scenic_category_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `scenic_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '景点ID',
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_scenic_category`(`scenic_id` ASC, `category_id` ASC) USING BTREE,
  INDEX `idx_scenic_id`(`scenic_id` ASC) USING BTREE,
  INDEX `idx_category_id`(`category_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '景点分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_category_relation
-- ----------------------------
INSERT INTO `scenic_category_relation` VALUES (1, 'S001', 1);
INSERT INTO `scenic_category_relation` VALUES (2, 'S001', 6);
INSERT INTO `scenic_category_relation` VALUES (3, 'S002', 2);
INSERT INTO `scenic_category_relation` VALUES (4, 'S002', 5);
INSERT INTO `scenic_category_relation` VALUES (5, 'S003', 1);
INSERT INTO `scenic_category_relation` VALUES (6, 'S004', 2);
INSERT INTO `scenic_category_relation` VALUES (7, 'S005', 1);
INSERT INTO `scenic_category_relation` VALUES (8, 'S005', 4);

-- ----------------------------
-- Table structure for scenic_image
-- ----------------------------
DROP TABLE IF EXISTS `scenic_image`;
CREATE TABLE `scenic_image`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `scenic_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '景点ID',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片URL',
  `is_cover` tinyint(1) NULL DEFAULT 0 COMMENT '是否为封面：0-否，1-是',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_scenic_id`(`scenic_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '景点图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_image
-- ----------------------------
INSERT INTO `scenic_image` VALUES (1, 'S001', 'xihu_01.jpg', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (2, 'S001', 'xihu_02.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (3, 'S001', 'xihu_03.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (4, 'S002', 'gugong_01.jpg', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (5, 'S002', 'gugong_02.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (6, 'S002', 'gugong_03.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (7, 'S003', 'huangshan_01.jpg', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (8, 'S003', 'huangshan_02.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (9, 'S003', 'huangshan_03.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (10, 'S004', 'changcheng_01.jpg', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (11, 'S004', 'changcheng_02.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (12, 'S004', 'changcheng_03.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (13, 'S005', 'taishan_01.jpg', 1, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (14, 'S005', 'taishan_02.jpg', 0, '2025-04-14 03:05:19');
INSERT INTO `scenic_image` VALUES (15, 'S005', 'taishan_03.jpg', 0, '2025-04-14 03:05:19');

-- ----------------------------
-- Table structure for scenic_spot
-- ----------------------------
DROP TABLE IF EXISTS `scenic_spot`;
CREATE TABLE `scenic_spot`  (
  `scenic_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '景点ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '景点名称',
  `location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '地理位置',
  `ticket_price` decimal(10, 2) NOT NULL COMMENT '门票价格',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '景点描述',
  `opening_hours` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开放时间',
  `contact_info` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0停用，1启用）',
  PRIMARY KEY (`scenic_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '景点信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_spot
-- ----------------------------
INSERT INTO `scenic_spot` VALUES ('aa69a68bd7f94548b5d4', '11', '22', 0.00, '', '9:00-17:00', '400-123-4567', '2025-04-16 04:21:25', '2025-04-16 04:21:25', 1);
INSERT INTO `scenic_spot` VALUES ('S0001', '故宫博物院', '北京市东城区景山前街4号', 60.00, '故宫博物院建立于1925年10月10日，位于北京故宫紫禁城内。是在明朝、清朝两代皇宫及其收藏的基础上建立起来的中国综合性博物馆，也是中国最大的古代文化艺术博物馆。', '8:30-17:00', '010-65131892', '2024-01-01 08:00:00', '2024-03-01 10:00:00', 1);
INSERT INTO `scenic_spot` VALUES ('S0002', '长城', '北京市怀柔区', 40.00, '长城是中国古代的军事防御工程，是世界上最伟大的建筑之一，也是世界文化遗产。', '8:00-17:00', '010-61626022', '2024-01-02 08:30:00', '2024-03-02 11:15:00', 1);
INSERT INTO `scenic_spot` VALUES ('S0003', '西湖', '浙江省杭州市西湖区龙井路1号', 0.00, '西湖位于浙江省杭州市西湖区，是中国大陆首批国家重点风景名胜区和中国十大风景名胜之一。', '全天开放', '0571-87179617', '2024-01-03 09:00:00', '2024-03-03 12:30:00', 1);
INSERT INTO `scenic_spot` VALUES ('S0004', '黄山', '安徽省黄山市黄山区', 230.00, '黄山位于安徽省南部黄山市境内，为三山（即黄山、九华山、齐云山）之一，是安徽旅游的标志。', '6:30-17:30', '0559-5562111', '2024-01-04 09:30:00', '2024-03-04 13:45:00', 1);
INSERT INTO `scenic_spot` VALUES ('S0005', '桂林山水', '广西壮族自治区桂林市', 120.00, '桂林山水是对广西壮族自治区桂林市漓江景区的总称，是中国自然山水风光的代表，千百年来享有\"桂林山水甲天下\"的美誉。', '8:00-18:00', '0773-2826465', '2024-01-05 10:00:00', '2024-03-05 14:00:00', 1);
INSERT INTO `scenic_spot` VALUES ('S0006', '九寨沟', '四川省阿坝藏族羌族自治州九寨沟县漳扎镇', 190.00, '九寨沟位于四川省阿坝藏族羌族自治州九寨沟县漳扎镇，是中国第一个以保护自然风景为主要目的的自然保护区。', '7:00-18:00', '0837-7739753', '2024-01-06 10:30:00', '2024-03-06 15:15:00', 1);
INSERT INTO `scenic_spot` VALUES ('S0007', '圆明园', '北京市海淀区清华西路28号', 15.00, '圆明园，清代大型皇家园林，是清代帝王的行宫和花园，也是当时中国对外展示国力的重要场所。', '7:00-19:00', '010-62628501', '2024-01-07 11:00:00', '2024-03-07 16:30:00', 1);
INSERT INTO `scenic_spot` VALUES ('S0008', '泰山', '山东省泰安市泰山区', 125.00, '泰山是中国五岳之首，位于山东省中部，绵亘于泰安、济南、淄博三市之间，总面积2.42万公顷。', '全天开放', '0538-8066061', '2024-01-08 11:30:00', '2024-03-08 17:45:00', 1);
INSERT INTO `scenic_spot` VALUES ('S0009', '三亚湾', '海南省三亚市三亚湾路', 0.00, '三亚湾又称大东海湾，位于海南岛最南端的三亚市，是中国著名旅游胜地，是海南发展最早的海湾，也是三亚市区的海岸线。', '全天开放', '0898-88667788', '2024-01-09 12:00:00', '2024-03-09 09:00:00', 1);
INSERT INTO `scenic_spot` VALUES ('S001', '西湖', '浙江省杭州市西湖区', 0.00, '西湖，位于浙江省杭州市西面，是中国大陆首批国家重点风景名胜区和中国十大风景名胜之一。它是中国大陆主要的观赏性淡水湖泊之一，也是现今《世界遗产名录》中少数几个和中国唯一一个湖泊类文化遗产。', '全天开放', '0571-87179617', '2025-04-14 03:05:19', NULL, 1);
INSERT INTO `scenic_spot` VALUES ('S0010', '莫高窟', '甘肃省酒泉市敦煌市莫高镇', 238.00, '莫高窟，俗称千佛洞，位于甘肃省酒泉市敦煌市东南25公里处，是我国著名的四大石窟之一。', '8:00-17:00', '0937-8869852', '2024-01-10 12:30:00', '2024-03-10 10:15:00', 1);
INSERT INTO `scenic_spot` VALUES ('S002', '故宫', '北京市东城区景山前街4号', 60.00, '北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。', '8:30-17:00（4月1日-10月31日），8:30-16:30（11月1日-3月31日），周一闭馆', '010-85007421', '2025-04-14 03:05:19', NULL, 1);
INSERT INTO `scenic_spot` VALUES ('S003', '黄山', '安徽省黄山市黄山区', 230.00, '黄山是安徽省南部的一座山脉，为三山五岳中三山之一，是安徽省最高的山脉。黄山是世界文化与自然双重遗产，世界地质公园，国家重点风景名胜区，全国文明风景旅游区，国家5A级旅游景区。', '全天开放', '0559-5561111', '2025-04-14 03:05:19', NULL, 1);
INSERT INTO `scenic_spot` VALUES ('S004', '长城', '北京市怀柔区', 45.00, '长城是中国古代的军事防御工程，是世界上最伟大的建筑之一，被誉为世界七大奇迹之一。长城始建于春秋战国时期，距今已有2000多年的历史。现存的长城主要为明代所建。', '7:30-17:30（4月1日-10月31日），8:00-17:00（11月1日-3月31日）', '010-61626022', '2025-04-14 03:05:19', NULL, 1);
INSERT INTO `scenic_spot` VALUES ('S005', '泰山', '山东省泰安市泰山区', 125.00, '泰山是中国五岳之首，位于山东省中部，主峰玉皇顶海拔1545米，气势雄伟磅礴。泰山是世界自然与文化遗产，世界地质公园，国家重点风景名胜区，国家5A级旅游景区。', '全天开放', '0538-8066666', '2025-04-14 03:05:19', NULL, 1);

-- ----------------------------
-- Table structure for tour_group_info
-- ----------------------------
DROP TABLE IF EXISTS `tour_group_info`;
CREATE TABLE `tour_group_info`  (
  `group_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '团号',
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '团名称',
  `departure_date` datetime NOT NULL COMMENT '出发日期',
  `return_date` datetime NOT NULL COMMENT '返回日期',
  `max_participants` int(11) NOT NULL COMMENT '最大参团人数',
  `current_participants` int(11) NOT NULL COMMENT '当前参团人数',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '团费',
  `itinerary_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对应行程ID',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '团状态（0待开团，1进行中，2已结束）',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '旅游团信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tour_group_info
-- ----------------------------
INSERT INTO `tour_group_info` VALUES ('G0001', '北京-西安古都文化7日游', '2025-05-01 09:00:00', '2025-05-07 18:00:00', 30, 18, 4999.00, 'I0001', '2024-02-01 11:30:00', '2024-03-15 15:45:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0002', '江南水乡6日游', '2025-05-10 08:00:00', '2025-05-15 19:00:00', 25, 15, 3800.00, 'I0002', '2024-02-05 12:45:00', '2024-03-16 16:00:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0003', '南方滨海8日休闲游', '2025-06-01 10:00:00', '2025-06-08 20:00:00', 20, 12, 5600.00, 'I0003', '2024-02-10 14:00:00', '2024-03-17 17:15:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0004', '四川自然风光7日游', '2025-06-15 07:00:00', '2025-06-21 21:00:00', 25, 20, 4200.00, 'I0004', '2024-02-15 15:15:00', '2024-03-18 09:30:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0005', '云南民族风情10日游', '2025-07-01 08:30:00', '2025-07-10 18:30:00', 20, 16, 6800.00, 'I0005', '2024-02-20 16:30:00', '2024-03-19 10:45:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0006', '东北文化6日游', '2025-07-15 09:30:00', '2025-07-20 17:30:00', 30, 10, 3500.00, 'I0006', '2024-02-25 17:45:00', '2024-03-20 11:00:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0007', '江浙名山名城7日游', '2025-08-01 07:00:00', '2025-08-07 19:00:00', 25, 18, 4100.00, 'I0007', '2024-03-01 09:00:00', '2024-03-21 13:15:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0008', '丝绸之路11日探索游', '2025-08-15 06:00:00', '2025-08-25 20:00:00', 20, 15, 7200.00, 'I0008', '2024-03-05 10:15:00', '2024-03-22 14:30:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0009', '长江三峡5日游轮之旅', '2025-09-01 09:00:00', '2025-09-05 17:00:00', 40, 25, 3200.00, 'I0009', '2024-03-10 11:30:00', '2024-03-23 15:45:00', 0);
INSERT INTO `tour_group_info` VALUES ('G0010', '西藏高原11日探索之旅', '2025-09-15 08:00:00', '2025-09-25 18:00:00', 15, 8, 8500.00, 'I0010', '2024-03-15 12:45:00', '2024-03-24 17:00:00', 0);

-- ----------------------------
-- Table structure for travel_content
-- ----------------------------
DROP TABLE IF EXISTS `travel_content`;
CREATE TABLE `travel_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图片',
  `images` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '图片集合（JSON格式）',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地点',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态：0-待审核 1-审核通过 2-审核拒绝',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '查看次数',
  `like_count` int(11) NULL DEFAULT 0 COMMENT '点赞次数',
  `comment_count` int(11) NULL DEFAULT 0 COMMENT '评论次数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `audit_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核人ID',
  `audit_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '旅游内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_content
-- ----------------------------
INSERT INTO `travel_content` VALUES (1, 'U10001', '北京故宫一日游记', '今天参观了北京故宫，感受到了深厚的历史文化底蕴。故宫又称紫禁城，是中国明清两代的皇家宫殿，每一处建筑都传递着历史的声音...', '/images/travel/beijing_001.jpg', '[\"/images/travel/beijing_001.jpg\", \"/images/travel/beijing_002.jpg\", \"/images/travel/beijing_003.jpg\"]', '北京市东城区', '故宫,北京,文化遗产,明清', 1, 3560, 245, 56, '2023-08-15 10:23:45', '2023-08-15 10:23:45', '2023-08-15 14:30:22', 'A1001', '内容优质，通过审核');
INSERT INTO `travel_content` VALUES (2, 'U10002', '桂林山水甲天下', '桂林的山真是太美了，漓江两岸的喀斯特地貌让人惊叹。泛舟漓江，两岸的山峰倒映在清澈的江水中，宛如一幅水墨丹青...', '/images/travel/guilin_001.jpg', '[\"/images/travel/guilin_001.jpg\", \"/images/travel/guilin_002.jpg\", \"/images/travel/guilin_003.jpg\"]', '广西桂林市', '桂林,山水,漓江,自然风光', 1, 5280, 432, 78, '2023-09-03 08:45:30', '2023-09-03 10:15:40', '2023-09-03 15:20:18', 'A1002', '图文并茂，审核通过');
INSERT INTO `travel_content` VALUES (3, 'U10003', '丽江古城休闲之旅', '丽江古城依山傍水，青石板铺就的小路蜿蜒曲折，处处是水，家家有花。夜晚的丽江更是另一番景象，酒吧街热闹非凡...', '/images/travel/lijiang_001.jpg', '[\"/images/travel/lijiang_001.jpg\", \"/images/travel/lijiang_002.jpg\", \"/images/travel/lijiang_003.jpg\"]', '云南丽江市', '丽江,古城,纳西族,文化', 1, 4890, 356, 62, '2023-10-12 14:22:36', '2023-10-12 16:35:41', '2023-10-13 09:15:27', 'A1001', '内容真实有趣，通过');
INSERT INTO `travel_content` VALUES (4, 'U10004', '香港迪士尼亲子游', '带孩子去香港迪士尼乐园玩了一天，孩子们玩得非常开心。园区内设施齐全，演出精彩，工作人员服务态度也很好...', '/images/travel/hkdisney_001.jpg', '[\"/images/travel/hkdisney_001.jpg\", \"/images/travel/hkdisney_002.jpg\", \"/images/travel/hkdisney_003.jpg\"]', '香港', '香港,迪士尼,亲子游,主题乐园', 0, 2340, 189, 32, '2023-11-05 09:33:25', '2023-11-05 09:33:25', NULL, NULL, NULL);
INSERT INTO `travel_content` VALUES (5, 'U10005', '青海湖环湖骑行记', '完成了为期三天的青海湖环湖骑行，沿途风景如画。清晨的青海湖平静如镜，傍晚的晚霞映照在湖面上，美不胜收...', '/images/travel/qinghai_001.jpg', '[\"/images/travel/qinghai_001.jpg\", \"/images/travel/qinghai_002.jpg\", \"/images/travel/qinghai_003.jpg\"]', '青海省海南藏族自治州', '青海湖,骑行,自然风光,冒险', 1, 6780, 521, 95, '2023-07-22 16:44:38', '2023-07-23 10:12:15', '2023-07-23 14:30:42', 'A1003', '精彩的骑行体验，审核通过');
INSERT INTO `travel_content` VALUES (6, 'U10006', '三亚海滨度假攻略', '在三亚度过了一个完美的假期，这里分享我的经验和攻略。三亚的海滩非常干净，海水清澈，适合游泳和各种水上活动...', '/images/travel/sanya_001.jpg', '[\"/images/travel/sanya_001.jpg\", \"/images/travel/sanya_002.jpg\", \"/images/travel/sanya_003.jpg\"]', '海南三亚市', '三亚,海滩,度假,阳光', 2, 3210, 0, 0, '2023-12-08 11:28:59', '2023-12-08 13:45:23', '2023-12-09 10:22:18', 'A1002', '内容包含商业推广信息，审核拒绝');
INSERT INTO `travel_content` VALUES (7, 'U10007', '西藏拉萨朝圣之旅', '终于实现了去拉萨的梦想，参观了布达拉宫、大昭寺等地。高原上的蓝天白云格外美丽，藏族同胞的热情好客让我感动...', '/images/travel/lhasa_001.jpg', '[\"/images/travel/lhasa_001.jpg\", \"/images/travel/lhasa_002.jpg\", \"/images/travel/lhasa_003.jpg\"]', '西藏拉萨市', '西藏,拉萨,朝圣,布达拉宫', 1, 7890, 645, 110, '2023-06-18 07:55:42', '2023-06-18 15:30:10', '2023-06-19 08:45:33', 'A1001', '内容丰富，图片清晰，通过审核');
INSERT INTO `travel_content` VALUES (8, 'U10008', '杭州西湖游船体验', '西湖的美不仅在于山水，更在于与之相关的文化底蕴。乘船游览西湖，欣赏了雷峰塔、三潭印月等景点，还品尝了正宗的西湖醋鱼...', '/images/travel/hangzhou_001.jpg', '[\"/images/travel/hangzhou_001.jpg\", \"/images/travel/hangzhou_002.jpg\", \"/images/travel/hangzhou_003.jpg\"]', '浙江杭州市', '杭州,西湖,游船,美食', 0, 1890, 132, 28, '2024-01-05 13:22:44', '2024-01-05 13:22:44', NULL, NULL, NULL);
INSERT INTO `travel_content` VALUES (9, 'U10009', '成都熊猫基地一日游', '在成都大熊猫繁育研究基地看到了可爱的熊猫，它们憨态可掬，萌化了我的心。基地环境优美，工作人员对熊猫的照顾非常细心...', '/images/travel/chengdu_001.jpg', '[\"/images/travel/chengdu_001.jpg\", \"/images/travel/chengdu_002.jpg\", \"/images/travel/chengdu_003.jpg\"]', '四川成都市', '成都,熊猫,动物保护,生态旅游', 1, 4560, 378, 65, '2023-04-29 09:12:34', '2023-04-29 11:45:22', '2023-04-30 08:30:15', 'A1003', '内容生动有趣，通过审核');
INSERT INTO `travel_content` VALUES (10, 'U10010', '张家界玻璃栈道惊险体验', '挑战了张家界天门山玻璃栈道，真的是既刺激又惊险！站在透明的玻璃上，脚下就是万丈深渊，让人不禁冒冷汗...', '/images/travel/zhangjiajie_001.jpg', '[\"/images/travel/zhangjiajie_001.jpg\", \"/images/travel/zhangjiajie_002.jpg\", \"/images/travel/zhangjiajie_003.jpg\"]', '湖南张家界市', '张家界,玻璃栈道,极限挑战,自然风光', 1, 8920, 756, 124, '2023-05-15 15:43:21', '2023-05-15 18:22:34', '2023-05-16 09:15:42', 'A1002', '精彩的探险经历，审核通过');

-- ----------------------------
-- Table structure for travel_route
-- ----------------------------
DROP TABLE IF EXISTS `travel_route`;
CREATE TABLE `travel_route`  (
  `route_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路线ID',
  `route_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路线名称',
  `duration` int(11) NULL DEFAULT NULL COMMENT '行程天数',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `start_point` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出发地',
  `highlights` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '亮点',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签，多个用逗号分隔',
  `rating` decimal(3, 1) NULL DEFAULT 4.5 COMMENT '评分',
  `sold_count` int(11) NULL DEFAULT 0 COMMENT '销量',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '描述',
  `spots` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '景点，多个用逗号分隔',
  `schedule` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '行程安排',
  `inclusion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '费用包含项',
  `exclusion` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '费用不包含项',
  `notes` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '注意事项',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面图片',
  `images` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片，多个用逗号分隔',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  PRIMARY KEY (`route_id`) USING BTREE,
  INDEX `idx_route_name`(`route_name` ASC) USING BTREE,
  INDEX `idx_tags`(`tags` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '旅游路线表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel_route
-- ----------------------------
INSERT INTO `travel_route` VALUES ('TR001', '杭州西湖三日游', 3, 1288.00, '杭州', '西湖美景、宋城千古情、灵隐寺、龙井茶园', '文化,自然,休闲', 4.8, 1256, '杭州西湖，中国著名的风景名胜区，被誉为人间天堂。此行程带您游览西湖十景，体验江南水乡风情。', '西湖,宋城,灵隐寺,龙井茶园,千岛湖', '第一天：西湖游船、断桥残雪、雷峰塔\n第二天：灵隐寺、飞来峰、龙井茶园\n第三天：宋城千古情、千岛湖', '往返交通、住宿、门票、导游服务、旅游保险', '个人消费、自由活动期间用餐', '请穿舒适的鞋子，带好防晒用品', 'hangzhou.jpg', 'hangzhou1.jpg,hangzhou2.jpg,hangzhou3.jpg', '2025-04-14 03:05:19', '2025-04-14 03:05:19', 1);
INSERT INTO `travel_route` VALUES ('TR002', '北京故宫长城五日游', 5, 2599.00, '北京', '故宫、长城、颐和园、天坛、奥林匹克公园', '文化,历史,经典', 4.9, 2345, '北京是中国的首都，拥有悠久的历史文化。此行程带您领略古都风采，感受中华文明的博大精深。', '故宫,长城,颐和园,天坛,王府井', '第一天：天安门广场、故宫\n第二天：八达岭长城、鸟巢水立方\n第三天：颐和园、圆明园\n第四天：天坛公园、王府井\n第五天：恭王府、什刹海', '往返交通、住宿、门票、导游服务、旅游保险', '个人消费、自由活动期间用餐', '爬长城需要较好的体力，请做好准备', 'beijing.jpg', 'beijing1.jpg,beijing2.jpg,beijing3.jpg', '2025-04-14 03:05:19', '2025-04-14 03:05:19', 1);
INSERT INTO `travel_route` VALUES ('TR003', '三亚阳光海滩度假5日游', 5, 3599.00, '三亚', '亚龙湾、天涯海角、南山寺、蜈支洲岛', '海滩,休闲,度假', 4.7, 1876, '三亚被誉为\"东方夏威夷\"，拥有全国最好的海滩和气候。此行程让您尽情享受阳光、沙滩、海浪。', '亚龙湾,天涯海角,南山寺,蜈支洲岛,大小洞天', '第一天：亚龙湾沙滩、海底世界\n第二天：蜈支洲岛一日游\n第三天：天涯海角、大小洞天\n第四天：南山寺、亚特兰蒂斯水世界\n第五天：自由活动、返程', '往返交通、住宿、部分景点门票、导游服务、旅游保险', '个人消费、部分景点门票、自由活动期间用餐', '请带好泳衣、防晒用品', 'sanya.jpg', 'sanya1.jpg,sanya2.jpg,sanya3.jpg', '2025-04-14 03:05:19', '2025-04-14 03:05:19', 1);
INSERT INTO `travel_route` VALUES ('TR004', '成都熊猫基地+九寨沟7日游', 7, 4999.00, '成都', '大熊猫繁育基地、九寨沟、黄龙、都江堰、青城山', '自然,文化,美食', 4.9, 1562, '成都是一座来了就不想走的城市，有大熊猫、有美食、有美景。此行程带您领略天府之国的独特魅力。', '大熊猫繁育基地,九寨沟,黄龙,都江堰,青城山,锦里古街', '第一天：抵达成都、锦里古街\n第二天：大熊猫繁育基地、宽窄巷子\n第三天：都江堰、青城山\n第四-五天：九寨沟\n第六天：黄龙\n第七天：返回成都、自由活动', '往返交通、住宿、门票、导游服务、旅游保险', '个人消费、自由活动期间用餐', '九寨沟海拔较高，请注意高原反应', 'chengdu.jpg', 'chengdu1.jpg,chengdu2.jpg,chengdu3.jpg', '2025-04-14 03:05:19', '2025-04-14 03:05:19', 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `portal_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `portal_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `member_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员号',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `portal_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`portal_user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '门户端用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('chengyiyi', '$2a$10$DKPZsaIfHjQrCM8z5QKP.uHAjITVzqxW3rwMvFc4zNr0woGV9n4vi', 'M10009', 'chengyiyi@example.com', '13800138009', '2024-01-09 15:50:00');
INSERT INTO `user_info` VALUES ('chengyy', '$2a$10$4XtjDkHdpJoX0ZCMYOvnCeDk51sif.1mqSU85d.et0azEbU3/AQ8K', 'M05077', '1223@163.com', '13254432234', '2025-04-15 23:05:05');
INSERT INTO `user_info` VALUES ('liangerer', '123456', 'M10010', 'liangerer@example.com', '13800138010', '2024-01-10 17:00:00');
INSERT INTO `user_info` VALUES ('lisi', '123456', 'M10002', 'lisi@example.com', '13800138002', '2024-01-02 11:15:00');
INSERT INTO `user_info` VALUES ('sunqi', '123456', 'M10005', 'sunqi@example.com', '13800138005', '2024-01-05 09:20:00');
INSERT INTO `user_info` VALUES ('test1', '$2a$10$wrSPZHZkPJ.qns0EuljsJuZ66wCsdmS7zN86Rqw5JoRBc8ZCvXJye', 'M86202', '1223@163.com', '13254432234', '2025-04-12 03:03:06');
INSERT INTO `user_info` VALUES ('testuser', '$2a$10$AEoTFE8bion8XcXePgBqd.7AaadZEE.NDNGO1Hz2TzmhaxMJvyslu', 'M10099', 'testuser@example.com', '13800138000', '2025-04-12 02:04:47');
INSERT INTO `user_info` VALUES ('wangwu', '123456', 'M10003', 'wangwu@example.com', '13800138003', '2024-01-03 14:30:00');
INSERT INTO `user_info` VALUES ('wujiu', '123456', 'M10007', 'wujiu@example.com', '13800138007', '2024-01-07 10:30:00');
INSERT INTO `user_info` VALUES ('zhangsan', '123456', 'M10001', 'zhangsan@example.com', '13800138001', '2024-01-01 10:00:00');
INSERT INTO `user_info` VALUES ('zhaoliu', '123456', 'M10004', 'zhaoliu@example.com', '13800138004', '2024-01-04 16:45:00');
INSERT INTO `user_info` VALUES ('zhengshi', '123456', 'M10008', 'zhengshi@example.com', '13800138008', '2024-01-08 11:40:00');
INSERT INTO `user_info` VALUES ('zhouba', '123456', 'M10006', 'zhouba@example.com', '13800138006', '2024-01-06 13:10:00');

SET FOREIGN_KEY_CHECKS = 1;
