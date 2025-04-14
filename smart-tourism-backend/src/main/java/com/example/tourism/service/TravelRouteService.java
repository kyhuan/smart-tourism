package com.example.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tourism.entity.TravelRoute;

import java.util.List;
import java.util.Map;

/**
 * 旅游路线服务接口
 */
public interface TravelRouteService extends IService<TravelRoute> {

    /**
     * 分页查询旅游路线
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param keyword 关键词
     * @param duration 行程天数
     * @param theme 主题标签
     * @return 分页结果
     */
    IPage<TravelRoute> getRouteList(Integer pageNum, Integer pageSize, String keyword, String duration, String theme);

    /**
     * 获取旅游路线详情
     * @param id 路线ID
     * @return 路线详情
     */
    TravelRoute getRouteDetail(String id);

    /**
     * 获取热门旅游路线
     * @param limit 数量限制
     * @return 热门路线列表
     */
    List<TravelRoute> getHotRoutes(Integer limit);

    /**
     * 添加旅游路线
     * @param travelRoute 路线信息
     * @return 是否成功
     */
    boolean addRoute(TravelRoute travelRoute);

    /**
     * 更新旅游路线
     * @param travelRoute 路线信息
     * @return 是否成功
     */
    boolean updateRoute(TravelRoute travelRoute);

    /**
     * 删除旅游路线
     * @param id 路线ID
     * @return 是否成功
     */
    boolean deleteRoute(String id);

    /**
     * 预订旅游路线
     * @param bookingInfo 预订信息
     * @return 订单信息
     */
    Map<String, Object> bookRoute(Map<String, Object> bookingInfo);
} 