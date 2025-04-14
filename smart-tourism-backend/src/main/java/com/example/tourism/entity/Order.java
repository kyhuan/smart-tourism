package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体类
 */
@Data
@TableName("order_info")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    private String orderId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 景点ID
     */
    @TableField("product_id")
    private String scenicId;

    /**
     * 订单金额
     */
    @TableField("total_price")
    private BigDecimal orderAmount;

    /**
     * 订单状态（0：待支付，1：已支付，2：已取消，3：已完成）
     */
    @TableField("order_status")
    private Integer orderStatus;

    /**
     * 订单类型（1：门票，2：住宿，3：餐饮，4：交通）
     */
    @TableField("product_type")
    private Integer orderType;

    /**
     * 创建时间
     */
    @TableField("order_date")
    private Date createTime;

    /**
     * 支付时间
     */
    @TableField("payment_time")
    private Date payTime;

    /**
     * 备注
     */
    @TableField("product_name")
    private String remark;
} 