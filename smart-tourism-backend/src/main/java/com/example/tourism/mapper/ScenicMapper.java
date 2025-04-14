package com.example.tourism.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tourism.entity.Scenic;
import org.apache.ibatis.annotations.Mapper;

/**
 * 景点Mapper接口
 */
@Mapper
public interface ScenicMapper extends BaseMapper<Scenic> {
} 