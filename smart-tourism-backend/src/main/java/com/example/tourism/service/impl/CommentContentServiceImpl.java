package com.example.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tourism.entity.CommentContent;
import com.example.tourism.mapper.CommentContentMapper;
import com.example.tourism.service.CommentContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 评论内容服务实现类
 */
@Service
public class CommentContentServiceImpl extends ServiceImpl<CommentContentMapper, CommentContent> implements CommentContentService {

    @Autowired
    private CommentContentMapper commentContentMapper;

    @Override
    public IPage<CommentContent> getCommentContentList(Integer pageNum, Integer pageSize, Map<String, Object> params) {
        Page<CommentContent> page = new Page<>(pageNum, pageSize);
        
        try {
            // 尝试使用自定义SQL查询，带扩展信息
            return commentContentMapper.selectCommentContentWithInfo(page, params);
        } catch (Exception e) {
            // 如果自定义查询出错，使用基础查询
            LambdaQueryWrapper<CommentContent> queryWrapper = new LambdaQueryWrapper<>();
            
            // 关键词搜索
            if (params.containsKey("keyword") && params.get("keyword") != null) {
                String keyword = params.get("keyword").toString();
                queryWrapper.like(CommentContent::getContent, keyword);
            }
            
            // 状态过滤
            if (params.containsKey("status") && params.get("status") != null) {
                Integer status = Integer.parseInt(params.get("status").toString());
                queryWrapper.eq(CommentContent::getStatus, status);
            }
            
            // 内容类型过滤
            if (params.containsKey("contentType") && params.get("contentType") != null) {
                String contentType = params.get("contentType").toString();
                queryWrapper.eq(CommentContent::getContentType, contentType);
            }
            
            // 按创建时间降序排序
            queryWrapper.orderByDesc(CommentContent::getCreateTime);
            
            return page(page, queryWrapper);
        }
    }

    @Override
    public CommentContent getCommentContentDetail(Long id) {
        return getById(id);
    }

    @Override
    @Transactional
    public boolean auditCommentContent(Long id, Integer status, String remark) {
        CommentContent comment = getById(id);
        if (comment == null) {
            return false;
        }
        
        // 只能审核待审核状态的内容
        if (comment.getStatus() != 0) {
            return false;
        }
        
        // 获取当前登录的管理员用户名
        String adminUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        
        // 更新审核信息
        comment.setStatus(status);
        comment.setAuditRemark(remark);
        comment.setAuditTime(LocalDateTime.now());
        comment.setAuditUserId(adminUsername);
        comment.setUpdateTime(LocalDateTime.now());
        
        return updateById(comment);
    }

    @Override
    @Transactional
    public boolean deleteCommentContent(Long id) {
        // 判断记录是否存在
        CommentContent comment = getById(id);
        if (comment == null) {
            return false;
        }
        
        // 删除记录
        return removeById(id);
    }
} 