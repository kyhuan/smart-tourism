package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 旅游内容实体类
 */
@Data
@TableName("travel_content")
public class TravelContent {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private String userId;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("cover_image")
    private String coverImage;

    @TableField("images")
    private String images;

    @TableField("location")
    private String location;

    @TableField("tags")
    private String tags;

    @TableField("status")
    private Integer status; // 0-待审核 1-审核通过 2-审核拒绝

    @TableField("view_count")
    private Integer viewCount;

    @TableField("like_count")
    private Integer likeCount;

    @TableField("comment_count")
    private Integer commentCount;

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
} 