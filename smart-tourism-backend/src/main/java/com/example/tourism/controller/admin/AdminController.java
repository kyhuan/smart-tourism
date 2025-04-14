package com.example.tourism.controller.admin;

import com.example.tourism.common.Result;
import com.example.tourism.entity.Admin;
import com.example.tourism.service.AdminService;
import com.example.tourism.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin // 添加跨域支持
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 管理员登录
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> loginForm) {
        String username = loginForm.get("username");
        String password = loginForm.get("password");
        
        System.out.println("收到管理员登录请求: username=" + username + ", password长度=" + (password != null ? password.length() : 0));
        
        if (username == null || password == null) {
            System.out.println("用户名或密码为空");
            return Result.error("用户名或密码不能为空");
        }
        
        try {
            // 调用服务层进行登录验证
            Admin admin = adminService.login(username, password);
            
            if (admin != null) {
                // 登录成功，生成token
                String token = jwtUtil.generateToken(admin.getId(), "ROLE_ADMIN");
                
                Map<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("role", "ROLE_ADMIN"); // 使用固定格式的角色标识
                System.out.println("管理员登录成功: " + admin.getId() + ", 生成token: " + token);
                return Result.success(data);
            } else {
                System.out.println("用户名或密码错误: " + username);
                return Result.error("用户名或密码错误");
            }
        } catch (Exception e) {
            System.err.println("管理员登录异常: " + e.getMessage());
            e.printStackTrace();
            return Result.error("登录处理异常: " + e.getMessage());
        }
    }
    
    /**
     * 获取管理员信息
     */
    @GetMapping("/info")
    public Result<Map<String, Object>> getInfo(@RequestHeader(value = "Authorization", required = false) String token) {
        System.out.println("获取管理员信息: token=" + token);
        
        // 如果没有提供token或token格式不正确，返回错误
        if (token == null || !token.startsWith("Bearer ")) {
            System.out.println("无效的token格式");
            return Result.error("无效的token");
        }
        
        token = token.substring(7); // 去掉"Bearer "前缀
        
        try {
            String userId = jwtUtil.getUserIdFromToken(token);
            System.out.println("解析token获取userId: " + userId);
            
            Admin admin = adminService.getById(userId); // 直接使用String类型的ID
            
            if (admin != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("name", admin.getUsername());
                data.put("id", admin.getId());
                data.put("phone", admin.getPhone());
                data.put("role", "ROLE_ADMIN");
                data.put("email", admin.getEmail());
                System.out.println("成功获取管理员信息: " + admin.getUsername());
                return Result.success(data);
            } else {
                System.out.println("根据ID未找到管理员: " + userId);
                return Result.error("获取用户信息失败");
            }
        } catch (Exception e) {
            System.err.println("获取管理员信息异常: " + e.getMessage());
            e.printStackTrace();
            return Result.error("token解析失败: " + e.getMessage());
        }
    }
    
    /**
     * 更新管理员信息
     */
    @PutMapping("/info")
    public Result<String> updateInfo(@RequestBody Admin admin, @RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return Result.error("无效的token");
        }
        
        token = token.substring(7);
        
        try {
            String userId = jwtUtil.getUserIdFromToken(token);
            admin.setId(userId); // 直接设置String类型的ID
            
            boolean updated = adminService.updateById(admin);
            
            if (updated) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error("token解析失败");
        }
    }
    
    /**
     * 管理员登出
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success("登出成功");
    }
}
