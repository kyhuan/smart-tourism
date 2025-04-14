package com.example.tourism.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.tourism.common.Result;
import com.example.tourism.entity.UserInfo;
import com.example.tourism.mapper.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@Api(tags = "会员管理")
public class MemberController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/getAllMember")
    @ApiOperation("获取所有会员信息")
    public Result<List<UserInfo>> getAllMember() {
        // 查询所有会员信息
        List<UserInfo> members = userInfoMapper.selectList(new LambdaQueryWrapper<>());
        return Result.success(members);
    }
} 