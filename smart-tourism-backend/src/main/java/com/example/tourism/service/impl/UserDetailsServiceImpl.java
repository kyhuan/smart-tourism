package com.example.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.tourism.entity.Admin;
import com.example.tourism.entity.UserInfo;
import com.example.tourism.mapper.AdminMapper;
import com.example.tourism.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 先尝试查找普通用户
        UserInfo userInfo = userInfoMapper.selectOne(
            new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getPortalUserName, username)
        );
        
        if (userInfo != null) {
            return new User(userInfo.getPortalUserName(), userInfo.getPortalPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }
        
        // 如果普通用户不存在，尝试查找管理员用户
        Admin admin = adminMapper.selectOne(
            new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, username)
        );
        
        if (admin != null) {
            return new User(admin.getUsername(), admin.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        
        // 如果都不存在，抛出异常
        throw new UsernameNotFoundException("User not found: " + username);
    }
} 