package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论内容实体类
 */
@Data
@TableName("comment_content")
public class CommentContent {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private String userId;

    @TableField("content_id")
    private Long contentId;
    
    @TableField("content_type")
    private String contentType; // 评论内容类型：scenic-景点 hotel-酒店 route-路线 travel-游记

    @TableField("content")
    private String content;

    @TableField("status")
    private Integer status; // 0-待审核 1-审核通过 2-审核拒绝

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("audit_time")
    private LocalDateTime auditTime;

    @TableField("audit_user_id")
    private String auditUserId;

    @TableField("audit_remark")
    private String auditRemark;

    @TableField(exist = false)
    private String userName; // 用户名，非数据库字段

    @TableField(exist = false)
    private String userAvatar; // 用户头像，非数据库字段

    @TableField(exist = false)
    private String auditUserName; // 审核人员名称，非数据库字段
    
    @TableField(exist = false)
    private String contentTitle; // 评论内容标题，非数据库字段
} 