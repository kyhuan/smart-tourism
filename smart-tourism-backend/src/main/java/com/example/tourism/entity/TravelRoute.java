package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 旅游路线实体类
 */
@Data
@TableName("travel_route")
public class TravelRoute {

    @TableId(value = "route_id", type = IdType.ASSIGN_UUID)
    private String routeId;

    @TableField("route_name")
    private String routeName;

    @TableField("duration")
    private Integer duration;

    @TableField("price")
    private Double price;

    @TableField("start_point")
    private String startPoint;

    @TableField("highlights")
    private String highlights;

    @TableField("tags")
    private String tags;

    @TableField("rating")
    private Double rating;

    @TableField("sold_count")
    private Integer soldCount;

    @TableField("description")
    private String description;

    @TableField("spots")
    private String spots;

    @TableField("schedule")
    private String schedule;

    @TableField("inclusion")
    private String inclusion;

    @TableField("exclusion")
    private String exclusion;

    @TableField("notes")
    private String notes;

    @TableField("cover_image")
    private String coverImage;

    @TableField("images")
    private String images;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("status")
    private Integer status;

    /**
     * 将实体转换为前端需要的格式
     */
    public void convertToFrontendFormat() {
        // 如果没有设置封面图，使用默认图片
        if (coverImage == null || coverImage.isEmpty()) {
            coverImage = "default_route.jpg";
        }
        
        // 确保评分始终有值
        if (rating == null) {
            rating = 4.0;
        }
        
        // 确保销量有值
        if (soldCount == null) {
            soldCount = 0;
        }
    }
} 