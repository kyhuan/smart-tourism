package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("portal_hotel_info")
public class HotelInfo {
    @TableId(value = "portal_hotel_id", type = IdType.INPUT)
    private String portalHotelId;
    
    private String hotelName;
    private String hotelAddress;
    private String roomTypes;
    private BigDecimal price;
    private BigDecimal rating;
    private String amenities;
    private String description;
    private String contactInfo;
    private LocalDateTime hotelCreateTime;
    private LocalDateTime hotelUpdateTime;
    private Integer status; // 0-停用 1-启用
    
    @TableField(exist = false)
    private String images; // 酒店图片，多个图片URL用逗号分隔
    
    @TableField(exist = false)
    private String facilities; // 设施服务，多个用逗号分隔
    
    @TableField(exist = false)
    private String checkInTime; // 入住时间
    
    @TableField(exist = false)
    private String checkOutTime; // 退房时间
    
    @TableField(exist = false)
    private String cancellationPolicy; // 取消政策
}
