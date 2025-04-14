-- 创建管理员表
CREATE TABLE IF NOT EXISTS `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `role` varchar(20) DEFAULT 'admin' COMMENT '角色（admin: 管理员, operator: 运营人员）',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态（0: 禁用, 1: 正常）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- 插入默认管理员账号(密码: admin123)
-- 注意: 这里预先使用了BCrypt加密的密码，实际应用中密码会在服务启动时自动创建
INSERT INTO `admin` (`username`, `password`, `phone`, `real_name`, `email`, `role`, `status`, `create_time`, `update_time`)
VALUES
('admin', '$2a$10$ixoc5Gj3eOZ2zcbSbp9K7O5CV.qf6f85zN5Gkvx1HZ0t3JKVqGtw6', '13800138000', '系统管理员', 'admin@example.com', 'admin', 1, NOW(), NOW())
ON DUPLICATE KEY UPDATE `update_time` = NOW(); 