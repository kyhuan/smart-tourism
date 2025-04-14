package com.example.tourism.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tourism.common.Result;
import com.example.tourism.entity.TravelContent;
import com.example.tourism.entity.CommentContent;
import com.example.tourism.service.TravelContentService;
import com.example.tourism.service.CommentContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin/content")
@Api(tags = "管理后台内容管理")
public class ContentController {

    @Autowired
    private TravelContentService travelContentService;
    
    @Autowired
    private CommentContentService commentContentService;

    @GetMapping("/travels/list")
    @ApiOperation("获取旅游记录列表")
    public Result<Map<String, Object>> getTravelList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status) {
        
        // 构建分页查询参数
        Map<String, Object> params = new HashMap<>();
        if (keyword != null && !keyword.isEmpty()) {
            params.put("keyword", keyword);
        }
        if (status != null) {
            params.put("status", status);
        }
        
        // 调用服务层查询数据
        IPage<TravelContent> page = travelContentService.getTravelContentList(pageNum, pageSize, params);
        
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("records", page.getRecords());
        result.put("total", page.getTotal());
        result.put("pages", page.getPages());
        result.put("current", page.getCurrent());
        result.put("size", page.getSize());
        
        return Result.success(result);
    }
    
    @GetMapping("/travels/{id}")
    @ApiOperation("获取旅游记录详情")
    public Result<TravelContent> getTravelDetail(@PathVariable Long id) {
        TravelContent travelContent = travelContentService.getTravelContentDetail(id);
        if (travelContent == null) {
            return Result.error("记录不存在");
        }
        return Result.success(travelContent);
    }
    
    @PostMapping("/travels/audit")
    @ApiOperation("审核旅游记录")
    public Result<Boolean> auditTravel(@RequestBody Map<String, Object> auditInfo) {
        Long id = Long.valueOf(auditInfo.get("id").toString());
        Integer status = (Integer) auditInfo.get("status");
        String remark = (String) auditInfo.get("remark");
        
        boolean success = travelContentService.auditTravelContent(id, status, remark);
        if (success) {
            return Result.success(true);
        } else {
            return Result.error("审核失败");
        }
    }
    
    @DeleteMapping("/travels/{id}")
    @ApiOperation("删除旅游记录")
    public Result<Boolean> deleteTravel(@PathVariable Long id) {
        boolean success = travelContentService.deleteTravelContent(id);
        if (success) {
            return Result.success(true);
        } else {
            return Result.error("删除失败");
        }
    }
    
    // 评论管理相关API
    
    @GetMapping("/comments/list")
    @ApiOperation("获取评论列表")
    public Result<Map<String, Object>> getCommentList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String contentType) {
        
        // 构建分页查询参数
        Map<String, Object> params = new HashMap<>();
        if (keyword != null && !keyword.isEmpty()) {
            params.put("keyword", keyword);
        }
        if (status != null) {
            params.put("status", status);
        }
        if (contentType != null && !contentType.isEmpty()) {
            params.put("contentType", contentType);
        }
        
        // 调用服务层查询数据
        IPage<CommentContent> page = commentContentService.getCommentContentList(pageNum, pageSize, params);
        
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("records", page.getRecords());
        result.put("total", page.getTotal());
        result.put("pages", page.getPages());
        result.put("current", page.getCurrent());
        result.put("size", page.getSize());
        
        return Result.success(result);
    }
    
    @GetMapping("/comments/{id}")
    @ApiOperation("获取评论详情")
    public Result<CommentContent> getCommentDetail(@PathVariable Long id) {
        CommentContent commentContent = commentContentService.getCommentContentDetail(id);
        if (commentContent == null) {
            return Result.error("记录不存在");
        }
        return Result.success(commentContent);
    }
    
    @PostMapping("/comments/audit")
    @ApiOperation("审核评论")
    public Result<Boolean> auditComment(@RequestBody Map<String, Object> auditInfo) {
        Long id = Long.valueOf(auditInfo.get("id").toString());
        Integer status = (Integer) auditInfo.get("status");
        String remark = (String) auditInfo.get("remark");
        
        boolean success = commentContentService.auditCommentContent(id, status, remark);
        if (success) {
            return Result.success(true);
        } else {
            return Result.error("审核失败");
        }
    }
    
    @DeleteMapping("/comments/{id}")
    @ApiOperation("删除评论")
    public Result<Boolean> deleteComment(@PathVariable Long id) {
        boolean success = commentContentService.deleteCommentContent(id);
        if (success) {
            return Result.success(true);
        } else {
            return Result.error("删除失败");
        }
    }
} 