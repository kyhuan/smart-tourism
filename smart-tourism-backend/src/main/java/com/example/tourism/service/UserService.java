package com.example.tourism.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tourism.entity.UserInfo;

public interface UserService extends IService<UserInfo> {
    /**
     * 用户注册
     */
    boolean register(UserInfo userInfo);

    /**
     * 用户登录
     */
    String login(String username, String password);

    /**
     * 获取用户信息
     */
    UserInfo getUserInfo(String username);

    /**
     * 更新用户信息
     */
    boolean updateUserInfo(UserInfo userInfo);

    /**
     * 修改密码
     */
    boolean changePassword(String username, String oldPassword, String newPassword);
} 