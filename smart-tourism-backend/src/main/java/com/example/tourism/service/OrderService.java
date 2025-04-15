package com.example.tourism.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.tourism.entity.OrderInfo;
import com.example.tourism.entity.OrderStatistics;

import java.util.Map;

public interface OrderService extends IService<OrderInfo> {
    /**
     * 分页查询订单列表
     */
    IPage<OrderInfo> getOrderList(Integer pageNum, Integer pageSize, String userId, String productType);

    /**
     * 获取订单详情
     */
    OrderInfo getOrderDetail(String id);

    /**
     * 创建订单
     */
    boolean createOrder(OrderInfo orderInfo);
    
    /**
     * 创建订单（前端接口使用）
     */
    Map<String, Object> createOrder(String userId, String productId, String productType, Integer quantity, Map<String, Object> orderInfo);

    /**
     * 取消订单
     */
    boolean cancelOrder(String id, String reason);
    
    /**
     * 用户取消订单
     */
    boolean cancelOrder(String orderId, String userId, String reason);

    /**
     * 支付订单
     */
    boolean payOrder(String id);
    
    /**
     * 用户支付订单
     */
    Map<String, Object> payOrder(String orderId, String userId, String payMethod, Map<String, Object> payInfo);

    /**
     * 更新订单状态
     */
    boolean updateOrderStatus(String id, Integer status);
    
    /**
     * 用户申请退款
     */
    boolean refundOrder(String orderId, String userId, String reason);
    
    /**
     * 获取订单状态
     */
    Map<String, Object> getOrderStatus(String orderId);
    
    /**
     * 获取用户订单列表
     */
    IPage<OrderInfo> getUserOrders(String userId, Integer pageNum, Integer pageSize, Integer status);

    /**
     * 获取用户订单统计
     */
    OrderStatistics getOrderStatistics(String userId);
    
    /**
     * 获取所有订单统计数据
     */
    OrderStatistics getAllOrderStatistics(String startDate, String endDate);
} 