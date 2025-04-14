-- 添加旅游内容表
CREATE TABLE IF NOT EXISTS `travel_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `images` text COMMENT '图片集合（JSON格式）',
  `location` varchar(100) DEFAULT NULL COMMENT '地点',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态：0-待审核 1-审核通过 2-审核拒绝',
  `view_count` int(11) DEFAULT '0' COMMENT '查看次数',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_user_id` varchar(32) DEFAULT NULL COMMENT '审核人ID',
  `audit_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='旅游内容表';

-- 添加评论内容表
CREATE TABLE IF NOT EXISTS `comment_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `content_id` bigint(20) DEFAULT NULL COMMENT '内容ID',
  `content_type` varchar(20) DEFAULT NULL COMMENT '内容类型：scenic-景点 hotel-酒店 route-路线 travel-游记',
  `content` text COMMENT '评论内容',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态：0-待审核 1-审核通过 2-审核拒绝',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_user_id` varchar(32) DEFAULT NULL COMMENT '审核人ID',
  `audit_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_content` (`content_id`,`content_type`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论内容表'; 