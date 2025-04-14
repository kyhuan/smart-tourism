package com.example.tourism.config;

import com.example.tourism.entity.Admin;
import com.example.tourism.entity.UserInfo;
import com.example.tourism.mapper.AdminMapper;
import com.example.tourism.mapper.UserInfoMapper;
import com.example.tourism.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AdminMapper adminMapper;
    private final UserInfoMapper userInfoMapper;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, AdminMapper adminMapper, UserInfoMapper userInfoMapper) {
        this.jwtUtil = jwtUtil;
        this.adminMapper = adminMapper;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // 排除登录接口和允许匿名访问的接口
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/api/admin/login") || requestURI.equals("/api/user/login") || 
            requestURI.equals("/api/user/register") || requestURI.contains("/api/scenic/list")) {
            chain.doFilter(request, response);
            return;
        }
        
        try {
        String token = jwtUtil.getTokenFromRequest(request);
        if (token != null && jwtUtil.validateToken(token)) {
                String userId = jwtUtil.getUserIdFromToken(token);
                String role = jwtUtil.getRoleFromToken(token);
                
                UserDetails userDetails = null;
                // 根据角色确定用户类型并获取用户详情
                if ("ROLE_ADMIN".equals(role)) {
                    Admin admin = adminMapper.selectById(userId);
                    if (admin != null) {
                        userDetails = new User(admin.getUsername(), admin.getPassword(),
                            Collections.singletonList(new SimpleGrantedAuthority(role)));
                    }
                } else {
                    UserInfo userInfo = userInfoMapper.selectById(userId);
                    if (userInfo != null) {
                        userDetails = new User(userInfo.getPortalUserName(), userInfo.getPortalPassword(),
                            Collections.singletonList(new SimpleGrantedAuthority(role)));
                    }
                }
                
                if (userDetails != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
            }
        } catch (Exception e) {
            System.err.println("JWT认证过滤器异常: " + e.getMessage());
            e.printStackTrace();
            // 出现异常继续过滤器链，由后续过滤器或控制器处理
        }
        
        chain.doFilter(request, response);
    }
} 