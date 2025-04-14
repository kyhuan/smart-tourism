package com.example.tourism.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * 安全配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CorsFilter corsFilter;
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 安全过滤链配置
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 添加CORS过滤器到安全链最前面
            .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
            // 添加JWT认证过滤器
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            // 启用CORS支持
            .cors().and()
            // 禁用CSRF保护
            .csrf().disable()
            // 不创建会话(JWT不依赖会话)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // 请求授权
            .authorizeRequests()
            // 允许所有OPTIONS请求
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 允许匿名访问的接口
            .antMatchers(
                "/api/admin/login",
                "/api/admin/info",
                "/api/user/login", 
                "/api/user/register", 
                "/api/test/public", 
                "/api/hotel/list", 
                "/api/member/**",
                "/api/scenic/list", 
                "/api/scenic/detail/**", 
                "/api/scenic/*/comments",
                "/api/scenic/comment",
                "/api",
                "/api/user/info" // 获取用户信息接口
            ).permitAll()
            .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**").permitAll()
            // 其他所有请求需要认证
            .anyRequest().permitAll(); // 开发阶段先允许所有请求，后续改为authenticated()
            
        return http.build();
    }

    /**
     * 密码编码器
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
} 