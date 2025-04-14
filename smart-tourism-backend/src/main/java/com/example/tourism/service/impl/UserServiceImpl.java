package com.example.tourism.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tourism.entity.UserInfo;
import com.example.tourism.mapper.UserInfoMapper;
import com.example.tourism.service.UserService;
import com.example.tourism.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public boolean register(UserInfo userInfo) {
        // 检查用户名是否已存在
        if (lambdaQuery().eq(UserInfo::getPortalUserName, userInfo.getPortalUserName()).count() > 0) {
            return false;
        }

        // 加密密码
        userInfo.setPortalPassword(passwordEncoder.encode(userInfo.getPortalPassword()));
        // 设置默认状态
        userInfo.setStatus(1);
        return save(userInfo);
    }

    @Override
    public String login(String username, String password) {
        UserInfo userInfo = lambdaQuery()
                .eq(UserInfo::getPortalUserName, username)
                .one();
        
        if (userInfo != null && passwordEncoder.matches(password, userInfo.getPortalPassword())) {
            return jwtUtil.generateToken(userInfo.getPortalUserName(), "user");
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(String username) {
        return lambdaQuery()
                .eq(UserInfo::getPortalUserName, username)
                .one();
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        return updateById(userInfo);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        UserInfo userInfo = lambdaQuery()
                .eq(UserInfo::getPortalUserName, username)
                .one();
        
        if (userInfo != null && passwordEncoder.matches(oldPassword, userInfo.getPortalPassword())) {
            userInfo.setPortalPassword(passwordEncoder.encode(newPassword));
            return updateById(userInfo);
        }
        return false;
    }
} 