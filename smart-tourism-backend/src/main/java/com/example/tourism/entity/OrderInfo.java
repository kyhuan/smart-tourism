package com.example.tourism.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("order_info")
public class OrderInfo {
    @TableId(value = "order_id", type = IdType.INPUT)
    private String orderId;
    
    private String userId;
    private String productType; // 产品类型：SCENIC-景点 HOTEL-酒店
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal totalPrice;
    private Integer paymentStatus; // 0-未支付 1-已支付
    private LocalDateTime paymentTime;
    private Integer orderStatus; // 0-待处理 1-已完成 2-已取消
    private LocalDateTime orderDate;
    private LocalDateTime updateTime;
    
    @TableField(exist = false)
    private String contactName; // 联系人
    
    @TableField(exist = false)
    private String contactPhone; // 联系电话
    
    @TableField(exist = false)
    private String remark; // 备注
    
    @TableField(exist = false)
    private String refundReason; // 退款原因
    
    @TableField(exist = false)
    private LocalDateTime refundTime; // 退款时间
}
