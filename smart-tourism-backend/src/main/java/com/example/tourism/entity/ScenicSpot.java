package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("scenic_spot")
public class ScenicSpot {
    @TableId(value = "scenic_id", type = IdType.INPUT)
    private String scenicId;
    
    private String name;
    private String location;
    private BigDecimal ticketPrice;
    private String description;
    private String openingHours;
    private String contactInfo;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer status; // 0-停用 1-启用
    
    @TableField(exist = false)
    private String images; // 景点图片，多个图片URL用逗号分隔
    
    @TableField(exist = false)
    private String category; // 景点分类
    
    @TableField(exist = false)
    private String features; // 特色
    
    @TableField(exist = false)
    private Integer visitDuration; // 游玩时长（小时）
    
    @TableField(exist = false)
    private String transportation; // 交通信息
    
    // 适配前端字段
    @TableField(exist = false)
    private String id; // 用于前端的id，对应scenicId
    
    @TableField(exist = false)
    private String address; // 对应location
    
    @TableField(exist = false)
    private Double price; // 对应ticketPrice
    
    @TableField(exist = false)
    private Double rating; // 评分
    
    @TableField(exist = false)
    private String coverImage; // 封面图片，取images的第一张
    
    @TableField(exist = false)
    private String phone; // 对应contactInfo
    
    @TableField(exist = false)
    private String trafficInfo; // 对应transportation
    
    /**
     * 转换为前端需要的格式
     */
    public void convertToFrontendFormat() {
        this.id = this.scenicId;
        this.address = this.location;
        this.phone = this.contactInfo;
        this.trafficInfo = this.transportation;
        
        // 价格转换
        if (this.ticketPrice != null) {
            this.price = this.ticketPrice.doubleValue();
        }
        
        // 评分默认值
        if (this.rating == null) {
            this.rating = 4.5;
        }
        
        // 处理图片
        if (this.images != null && !this.images.isEmpty()) {
            String[] imageArray = this.images.split(",");
            if (imageArray.length > 0) {
                this.coverImage = imageArray[0].trim();
            }
        }
        
        // 如果没有图片，设置默认图片
        if (this.coverImage == null || this.coverImage.isEmpty()) {
            this.coverImage = "https://via.placeholder.com/300x200?text=景点图片";
        }
    }
}
