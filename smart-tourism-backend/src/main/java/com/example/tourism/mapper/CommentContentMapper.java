package com.example.tourism.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.tourism.entity.CommentContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 评论内容Mapper接口
 */
@Mapper
public interface CommentContentMapper extends BaseMapper<CommentContent> {
    
    /**
     * 查询带用户信息的评论内容
     * 
     * @param page 分页参数
     * @param params 查询参数
     * @return 分页结果
     */
    IPage<CommentContent> selectCommentContentWithInfo(Page<CommentContent> page, @Param("params") Map<String, Object> params);
} 