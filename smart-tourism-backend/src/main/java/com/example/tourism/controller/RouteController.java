package com.example.tourism.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tourism.common.Result;
import com.example.tourism.entity.TravelRoute;
import com.example.tourism.service.TravelRouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/route")
@Api(tags = "旅游路线管理")
public class RouteController {

    @Autowired
    private TravelRouteService travelRouteService;

    @GetMapping("/list")
    @ApiOperation("分页查询旅游路线列表")
    public Result<Map<String, Object>> getRouteList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String duration,
            @RequestParam(required = false) String theme) {
        
        System.out.println("接收到路线列表请求 - 参数: pageNum=" + pageNum + ", pageSize=" + pageSize 
                + ", keyword=" + keyword + ", duration=" + duration + ", theme=" + theme);
        
        // 调用服务层获取分页数据
        IPage<TravelRoute> page = travelRouteService.getRouteList(pageNum, pageSize, keyword, duration, theme);
        
        // 转换为前端需要的格式
        if (page.getRecords() != null) {
            page.getRecords().forEach(TravelRoute::convertToFrontendFormat);
        }
        
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("records", page.getRecords());
        result.put("total", page.getTotal());
        result.put("pages", page.getPages());
        result.put("current", page.getCurrent());
        result.put("size", page.getSize());
        
        return Result.success(result);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("获取旅游路线详情")
    public Result<TravelRoute> getRouteDetail(@PathVariable String id) {
        System.out.println("接收到路线详情请求 - ID: " + id);
        
        TravelRoute route = travelRouteService.getRouteDetail(id);
        if (route != null) {
            route.convertToFrontendFormat();
            return Result.success(route);
        } else {
            return Result.error("未找到指定路线");
        }
    }

    @GetMapping("/hot")
    @ApiOperation("获取热门旅游路线")
    public Result<List<TravelRoute>> getHotRoutes() {
        // 获取热门路线（销量最高的前3个）
        List<TravelRoute> hotRoutes = travelRouteService.getHotRoutes(3);
        
        // 转换为前端需要的格式
        hotRoutes.forEach(TravelRoute::convertToFrontendFormat);
        
        return Result.success(hotRoutes);
    }
    
    @PostMapping("/add")
    @ApiOperation("添加旅游路线")
    public Result<Boolean> addRoute(@RequestBody TravelRoute travelRoute) {
        return Result.success(travelRouteService.addRoute(travelRoute));
    }
    
    @PutMapping("/update")
    @ApiOperation("更新旅游路线信息")
    public Result<Boolean> updateRoute(@RequestBody TravelRoute travelRoute) {
        return Result.success(travelRouteService.updateRoute(travelRoute));
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除旅游路线")
    public Result<Boolean> deleteRoute(@PathVariable String id) {
        return Result.success(travelRouteService.deleteRoute(id));
    }

    @PostMapping("/booking")
    @ApiOperation("预订旅游路线")
    public Result<Map<String, Object>> bookRoute(@RequestBody Map<String, Object> bookingInfo) {
        String routeId = (String) bookingInfo.get("routeId");
        System.out.println("接收到路线预订请求 - 路线ID: " + routeId);
        
        Map<String, Object> orderInfo = travelRouteService.bookRoute(bookingInfo);
        if (orderInfo != null) {
            return Result.success(orderInfo);
        } else {
            return Result.error("未找到指定路线");
        }
    }
} 