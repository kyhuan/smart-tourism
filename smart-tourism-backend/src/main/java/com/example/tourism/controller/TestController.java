package com.example.tourism.controller;

import com.example.tourism.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Api(tags = "测试接口")
public class TestController {

    @GetMapping("/public")
    @ApiOperation("公开接口")
    public Result<Map<String, Object>> publicEndpoint() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "这是一个公开接口");
        data.put("timestamp", System.currentTimeMillis());
        return Result.success(data);
    }

    @GetMapping("/protected")
    @ApiOperation("需要认证的接口")
    public Result<Map<String, Object>> protectedEndpoint() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "这是一个需要认证的接口");
        data.put("timestamp", System.currentTimeMillis());
        return Result.success(data);
    }
} 