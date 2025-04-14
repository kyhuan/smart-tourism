package com.example.tourism.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tourism.common.Result;
import com.example.tourism.entity.ScenicSpot;
import com.example.tourism.entity.ReviewInfo;
import com.example.tourism.service.ScenicSpotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scenic")
@Api(tags = "景点管理")
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    @GetMapping("/list")
    @ApiOperation("分页查询景点列表")
    public Result<IPage<ScenicSpot>> getScenicList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category) {
        System.out.println("接收到景点列表请求 - 参数: pageNum=" + pageNum + ", pageSize=" + pageSize 
                          + ", keyword=" + keyword + ", category=" + category);
        IPage<ScenicSpot> page = scenicSpotService.getScenicList(pageNum, pageSize, keyword, category);
        
        // 转换为前端需要的格式
        List<ScenicSpot> records = page.getRecords();
        if (records != null) {
            records.forEach(ScenicSpot::convertToFrontendFormat);
        }
        
        return Result.success(page);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("获取景点详情")
    public Result<ScenicSpot> getScenicDetail(@PathVariable String id) {
        System.out.println("接收到景点详情请求 - ID: " + id);
        ScenicSpot scenicSpot = scenicSpotService.getScenicDetail(id);
        
        if (scenicSpot != null) {
            // 转换为前端需要的格式
            scenicSpot.convertToFrontendFormat();
            
            // 转换images字段为数组，适配前端
            if (scenicSpot.getImages() != null && !scenicSpot.getImages().isEmpty()) {
                scenicSpot.setImages(scenicSpot.getImages().trim());
            } else {
                // 如果没有图片，设置默认图片
                scenicSpot.setImages(scenicSpot.getCoverImage());
            }
        }
        
        return Result.success(scenicSpot);
    }

    @PostMapping("/add")
    @ApiOperation("添加景点")
    public Result<Boolean> addScenic(@RequestBody ScenicSpot scenicSpot) {
        return Result.success(scenicSpotService.addScenic(scenicSpot));
    }

    @PutMapping("/update")
    @ApiOperation("更新景点信息")
    public Result<Boolean> updateScenic(@RequestBody ScenicSpot scenicSpot) {
        return Result.success(scenicSpotService.updateScenic(scenicSpot));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除景点")
    public Result<Boolean> deleteScenic(@PathVariable String id) {
        return Result.success(scenicSpotService.deleteScenic(id));
    }
    
    @GetMapping("/{id}/comments")
    @ApiOperation("获取景点评论")
    public Result<Map<String, Object>> getScenicComments(
            @PathVariable String id,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        // 这里应该调用评论服务获取评论列表
        // 暂时使用模拟数据
        List<ReviewInfo> comments = new ArrayList<>();
        // 创建评论对象并设置属性，而不是使用构造函数
        ReviewInfo review1 = new ReviewInfo();
        review1.setId("1");
        review1.setResourceId(id);
        review1.setUsername("用户10086");
        review1.setContent("这个景点非常漂亮，值得一游！风景如画，空气清新，十分推荐！");
        review1.setCreateTime("2023-06-15 14:30");
        review1.setAvatar("https://joeschmoe.io/api/v1/random");
        
        ReviewInfo review2 = new ReviewInfo();
        review2.setId("2");
        review2.setResourceId(id);
        review2.setUsername("旅行达人");
        review2.setContent("风景优美，环境舒适，服务态度好。是一个放松身心的好地方，唯一的缺点是人有点多。");
        review2.setCreateTime("2023-06-10 09:15");
        review2.setAvatar("https://joeschmoe.io/api/v1/random");
        
        comments.add(review1);
        comments.add(review2);
        
        Map<String, Object> result = new HashMap<>();
        result.put("records", comments);
        result.put("total", comments.size());
        result.put("pages", 1);
        result.put("current", pageNum);
        result.put("size", pageSize);
        
        return Result.success(result);
    }
    
    @PostMapping("/comment")
    @ApiOperation("添加景点评论")
    public Result<Boolean> addComment(@RequestBody Map<String, String> commentInfo) {
        // 这里应该调用评论服务添加评论
        // 暂时直接返回成功
        String scenicId = commentInfo.get("scenicId");
        String content = commentInfo.get("content");
        
        // 模拟添加评论的处理
        System.out.println("收到景点" + scenicId + "的评论：" + content);
        
        return Result.success(true);
    }
} 