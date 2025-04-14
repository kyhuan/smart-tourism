package com.example.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tourism.entity.TravelContent;
import com.example.tourism.mapper.TravelContentMapper;
import com.example.tourism.service.TravelContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 旅游内容服务实现类
 */
@Service
public class TravelContentServiceImpl extends ServiceImpl<TravelContentMapper, TravelContent> implements TravelContentService {

    @Autowired
    private TravelContentMapper travelContentMapper;

    @Override
    public IPage<TravelContent> getTravelContentList(Integer pageNum, Integer pageSize, Map<String, Object> params) {
        Page<TravelContent> page = new Page<>(pageNum, pageSize);
        
        try {
            // 尝试使用自定义SQL查询，带用户信息
            return travelContentMapper.selectTravelContentWithUser(page, params);
        } catch (Exception e) {
            // 如果自定义查询出错，使用基础查询
            LambdaQueryWrapper<TravelContent> queryWrapper = new LambdaQueryWrapper<>();
            
            // 关键词搜索
            if (params.containsKey("keyword") && params.get("keyword") != null) {
                String keyword = params.get("keyword").toString();
                queryWrapper.like(TravelContent::getTitle, keyword)
                        .or()
                        .like(TravelContent::getContent, keyword)
                        .or()
                        .like(TravelContent::getTags, keyword);
            }
            
            // 状态过滤
            if (params.containsKey("status") && params.get("status") != null) {
                Integer status = Integer.parseInt(params.get("status").toString());
                queryWrapper.eq(TravelContent::getStatus, status);
            }
            
            // 按创建时间降序排序
            queryWrapper.orderByDesc(TravelContent::getCreateTime);
            
            return page(page, queryWrapper);
        }
    }

    @Override
    public TravelContent getTravelContentDetail(Long id) {
        return getById(id);
    }

    @Override
    @Transactional
    public boolean auditTravelContent(Long id, Integer status, String remark) {
        TravelContent content = getById(id);
        if (content == null) {
            return false;
        }
        
        // 只能审核待审核状态的内容
        if (content.getStatus() != 0) {
            return false;
        }
        
        // 获取当前登录的管理员用户名
        String adminUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // 更新审核信息
        content.setStatus(status);
        content.setAuditRemark(remark);
        content.setAuditTime(LocalDateTime.now());
        content.setAuditUserId(adminUsername);
        content.setUpdateTime(LocalDateTime.now());
        
        return updateById(content);
    }

    @Override
    @Transactional
    public boolean deleteTravelContent(Long id) {
        // 判断记录是否存在
        TravelContent content = getById(id);
        if (content == null) {
            return false;
        }
        
        // 删除记录
        return removeById(id);
    }
} 