package com.example.tourism.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tourism.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
