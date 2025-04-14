package com.example.tourism.controller;

import com.example.tourism.common.Result;
import com.example.tourism.entity.UserInfo;
import com.example.tourism.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result<?> register(@RequestBody UserInfo userInfo) {
        boolean success = userService.register(userInfo);
        if (success) {
            // 注册成功后获取用户信息并返回
            UserInfo registeredUser = userService.getUserInfo(userInfo.getPortalUserName());
            // 出于安全考虑，清除密码字段
            registeredUser.setPortalPassword(null);
            return Result.success(registeredUser);
        } else {
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<String> login(@RequestBody UserInfo userInfo) {
        String token = userService.login(userInfo.getPortalUserName(), userInfo.getPortalPassword());
        return token != null ? Result.success(token) : Result.error("用户名或密码错误");
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result<UserInfo> getUserInfo(@RequestParam String username) {
        UserInfo userInfo = userService.getUserInfo(username);
        return Result.success(userInfo);
    }

    @PutMapping("/info")
    @ApiOperation("更新用户信息")
    public Result<Boolean> updateUserInfo(@RequestBody UserInfo userInfo) {
        boolean success = userService.updateUserInfo(userInfo);
        return success ? Result.success() : Result.error("更新失败");
    }

    @PutMapping("/password")
    @ApiOperation("修改密码")
    public Result<Boolean> changePassword(@RequestParam String username,
                                        @RequestParam String oldPassword,
                                        @RequestParam String newPassword) {
        boolean success = userService.changePassword(username, oldPassword, newPassword);
        return success ? Result.success() : Result.error("修改密码失败");
    }
} 