package com.example.tourism.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tourism.entity.TravelContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 旅游内容Mapper接口
 */
@Mapper
public interface TravelContentMapper extends BaseMapper<TravelContent> {
    
    /**
     * 查询带用户信息的旅游内容
     * 
     * @param page 分页参数
     * @param params 查询参数
     * @return 分页结果
     */
    IPage<TravelContent> selectTravelContentWithUser(Page<TravelContent> page, @Param("params") Map<String, Object> params);
} 