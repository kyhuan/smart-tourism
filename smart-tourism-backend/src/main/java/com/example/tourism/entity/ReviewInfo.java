package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("review_info")
public class ReviewInfo {
    @TableId(type = IdType.AUTO)
    private String id;
    
    private String resourceId;  // 评论对象ID（景点、酒店等）
    private String username;    // 评论用户名
    private String content;     // 评论内容
    private String createTime;  // 评论时间
    
    // 非数据库字段
    private String avatar;      // 用户头像
}
