package com.example.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tourism.entity.CommentContent;

import java.util.Map;

/**
 * 评论内容服务接口
 */
public interface CommentContentService extends IService<CommentContent> {

    /**
     * 获取评论内容列表（分页）
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param params   查询参数
     * @return 分页结果
     */
    IPage<CommentContent> getCommentContentList(Integer pageNum, Integer pageSize, Map<String, Object> params);

    /**
     * 获取评论内容详情
     *
     * @param id 内容ID
     * @return 内容详情
     */
    CommentContent getCommentContentDetail(Long id);

    /**
     * 审核评论内容
     *
     * @param id     内容ID
     * @param status 审核状态 1-通过 2-拒绝
     * @param remark 审核备注
     * @return 是否成功
     */
    boolean auditCommentContent(Long id, Integer status, String remark);

    /**
     * 删除评论内容
     *
     * @param id 内容ID
     * @return 是否成功
     */
    boolean deleteCommentContent(Long id);
} 