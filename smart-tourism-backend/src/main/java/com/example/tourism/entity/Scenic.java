package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 景点实体类
 */
@Data
@TableName("scenic_spot")
public class Scenic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 景点ID
     */
    @TableId(value = "scenic_id", type = IdType.INPUT)
    private String scenicId;

    /**
     * 景点名称
     */
    @TableField("name")
    private String scenicName;

    /**
     * 景点地址
     */
    @TableField("location")
    private String scenicAddress;

    /**
     * 景点介绍
     */
    @TableField("description")
    private String scenicDesc;

    /**
     * 景点图片
     */
    @TableField(exist = false)
    private String scenicImg;

    /**
     * 景点价格
     */
    @TableField("ticket_price")
    private BigDecimal scenicPrice;

    /**
     * 景点状态（0：下架，1：上架）
     */
    @TableField("status")
    private Integer scenicStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;
} 