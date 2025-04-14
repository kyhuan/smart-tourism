package com.example.tourism.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tourism.entity.TravelRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 旅游路线Mapper接口
 */
@Mapper
public interface TravelRouteMapper extends BaseMapper<TravelRoute> {

    /**
     * 分页查询旅游路线
     * @param page 分页对象
     * @param keyword 关键词
     * @param duration 行程天数
     * @param theme 主题标签
     * @return 分页结果
     */
    IPage<TravelRoute> selectRoutePage(Page<TravelRoute> page, 
                                        @Param("keyword") String keyword,
                                        @Param("duration") Integer duration,
                                        @Param("theme") String theme);

    /**
     * 获取热门旅游路线
     * @param limit 数量限制
     * @return 热门路线列表
     */
    List<TravelRoute> selectHotRoutes(@Param("limit") Integer limit);
} 