package com.example.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tourism.entity.TravelRoute;
import com.example.tourism.mapper.TravelRouteMapper;
import com.example.tourism.service.TravelRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 旅游路线服务实现类
 */
@Service
public class TravelRouteServiceImpl extends ServiceImpl<TravelRouteMapper, TravelRoute> implements TravelRouteService {

    @Autowired
    private TravelRouteMapper travelRouteMapper;

    @Override
    public IPage<TravelRoute> getRouteList(Integer pageNum, Integer pageSize, String keyword, String duration, String theme) {
        Page<TravelRoute> page = new Page<>(pageNum, pageSize);
        
        // 尝试将duration转换为整数
        Integer durationNum = null;
        if (duration != null && !duration.isEmpty()) {
            try {
                durationNum = Integer.parseInt(duration);
            } catch (NumberFormatException e) {
                // 转换失败，忽略此参数
                System.out.println("行程天数格式错误: " + duration);
            }
        }
        
        // 使用自定义的Mapper方法进行查询
        return travelRouteMapper.selectRoutePage(page, keyword, durationNum, theme);
    }

    @Override
    public TravelRoute getRouteDetail(String id) {
        return getById(id);
    }

    @Override
    public List<TravelRoute> getHotRoutes(Integer limit) {
        if (limit == null || limit <= 0) {
            limit = 3; // 默认返回3条
        }
        return travelRouteMapper.selectHotRoutes(limit);
    }

    @Override
    @Transactional
    public boolean addRoute(TravelRoute travelRoute) {
        if (travelRoute.getCreateTime() == null) {
            travelRoute.setCreateTime(new Date());
        }
        if (travelRoute.getUpdateTime() == null) {
            travelRoute.setUpdateTime(new Date());
        }
        if (travelRoute.getStatus() == null) {
            travelRoute.setStatus(1); // 默认状态为启用
        }
        return save(travelRoute);
    }

    @Override
    @Transactional
    public boolean updateRoute(TravelRoute travelRoute) {
        travelRoute.setUpdateTime(new Date());
        return updateById(travelRoute);
    }

    @Override
    @Transactional
    public boolean deleteRoute(String id) {
        // 实际上是逻辑删除，将状态设置为0
        TravelRoute route = new TravelRoute();
        route.setRouteId(id);
        route.setStatus(0);
        route.setUpdateTime(new Date());
        return updateById(route);
    }

    @Override
    public Map<String, Object> bookRoute(Map<String, Object> bookingInfo) {
        String routeId = (String) bookingInfo.get("routeId");
        
        // 查找路线信息
        TravelRoute route = getById(routeId);
        if (route == null) {
            return null;
        }
        
        // 创建订单信息
        Map<String, Object> orderInfo = new HashMap<>();
        orderInfo.put("orderId", UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16));
        orderInfo.put("routeId", routeId);
        orderInfo.put("routeName", route.getRouteName());
        orderInfo.put("price", route.getPrice());
        orderInfo.put("status", "待支付");
        orderInfo.put("createTime", new Date());
        
        // TODO: 实际项目中，这里应该调用订单服务，将订单信息保存到数据库
        
        return orderInfo;
    }
} 