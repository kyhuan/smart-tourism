package com.example.tourism.controller.admin;

import com.example.tourism.common.Result;
import com.example.tourism.entity.Order;
import com.example.tourism.entity.Scenic;
import com.example.tourism.entity.UserInfo;
import com.example.tourism.mapper.OrderMapper;
import com.example.tourism.mapper.ScenicMapper;
import com.example.tourism.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 管理仪表盘控制器
 */
@RestController
@RequestMapping("/api/admin/dashboard")
public class DashboardController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private ScenicMapper scenicMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 获取仪表盘基本统计数据
     */
    @GetMapping("/stats")
    public Result<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // 查询总游客数
        long visitorCount = userInfoMapper.selectCount(null);
        stats.put("visitorCount", visitorCount);
        
        // 查询总景点数
        long scenicCount = scenicMapper.selectCount(null);
        stats.put("scenicCount", scenicCount);
        
        // 查询总订单数
        long orderCount = orderMapper.selectCount(null);
        stats.put("orderCount", orderCount);
        
        // 计算总收入（简化处理，实际项目中需要更复杂的计算）
        BigDecimal totalIncome = BigDecimal.valueOf(orderCount * 1000); // 假设每个订单平均1000元
        stats.put("totalIncome", totalIncome);
        
        return Result.success(stats);
    }

    /**
     * 获取近7天游客流量趋势
     */
    @GetMapping("/visitor-trend")
    public Result<Map<String, Object>> getVisitorTrend() {
        List<String> dates = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        
        // 生成近7天的日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        for (int i = 6; i >= 0; i--) {
            LocalDate date = LocalDate.now().minusDays(i);
            dates.add(date.format(formatter));
            
            // 生成随机访客数据（实际项目中应该从数据库查询）
            int baseCount = 800; // 基础访客数
            int randomVariation = (int) (Math.random() * 500); // 随机波动
            counts.add(baseCount + randomVariation);
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("dates", dates);
        result.put("counts", counts);
        
        return Result.success(result);
    }

    /**
     * 获取热门景点TOP5
     */
    @GetMapping("/hot-scenics")
    public Result<List<Map<String, Object>>> getHotScenics() {
        // 简化处理，实际项目中应该查询订单量最高的景点
        List<Scenic> allScenics = scenicMapper.selectList(null);
        
        // 取前5个景点，并为每个景点生成一个随机访问量
        List<Map<String, Object>> hotScenics = allScenics.stream()
                .limit(5)
                .map(scenic -> {
                    Map<String, Object> scenicData = new HashMap<>();
                    scenicData.put("name", scenic.getScenicName());
                    scenicData.put("id", scenic.getScenicId());
                    // 生成300到1500之间的随机访问量
                    int visits = 300 + (int) (Math.random() * 1200);
                    scenicData.put("visits", visits);
                    return scenicData;
                })
                .sorted((a, b) -> Integer.compare((int) b.get("visits"), (int) a.get("visits")))
                .collect(Collectors.toList());
        
        return Result.success(hotScenics);
    }

    /**
     * 获取订单类型分布
     */
    @GetMapping("/order-types")
    public Result<Map<String, Object>> getOrderTypes() {
        // 简化处理，生成四种类型的订单分布
        Map<String, Object> orderTypes = new HashMap<>();
        
        List<String> types = Arrays.asList("门票", "住宿", "餐饮", "交通");
        List<Integer> values = new ArrayList<>();
        
        // 生成随机比例
        int total = 100;
        for (int i = 0; i < 3; i++) {
            int value = (int) (Math.random() * (total - (3 - i)));
            values.add(value);
            total -= value;
        }
        values.add(total); // 最后一个类型占剩余比例
        
        orderTypes.put("types", types);
        orderTypes.put("values", values);
        
        return Result.success(orderTypes);
    }
} 