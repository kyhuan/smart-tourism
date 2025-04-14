package com.example.tourism.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tourism.entity.Admin;

/**
 * 管理员服务接口
 */
public interface AdminService extends IService<Admin> {

    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return 管理员信息
     */
    Admin login(String username, String password);
} 