package com.example.tourism.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.tourism.entity.OrderInfo;
import com.example.tourism.entity.OrderStatistics;
import com.example.tourism.mapper.OrderInfoMapper;
import com.example.tourism.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderService {

    @Override
    public IPage<OrderInfo> getOrderList(Integer pageNum, Integer pageSize, String userId, String productType) {
        Page<OrderInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        
        if (userId != null && !userId.isEmpty()) {
            queryWrapper.eq(OrderInfo::getUserId, userId);
        }
        
        if (productType != null && !productType.isEmpty()) {
            queryWrapper.eq(OrderInfo::getProductType, productType);
        }
        
        queryWrapper.orderByDesc(OrderInfo::getOrderDate);
        
        return page(page, queryWrapper);
    }

    @Override
    public OrderInfo getOrderDetail(String id) {
        return getById(id);
    }

    @Override
    @Transactional
    public boolean createOrder(OrderInfo orderInfo) {
        // 设置订单创建时间
        if (orderInfo.getOrderDate() == null) {
            orderInfo.setOrderDate(LocalDateTime.now());
        }
        
        // 设置订单更新时间
        orderInfo.setUpdateTime(LocalDateTime.now());
        
        // 设置初始订单状态
        if (orderInfo.getOrderStatus() == null) {
            orderInfo.setOrderStatus(0); // 待处理
        }
        
        // 设置初始支付状态
        if (orderInfo.getPaymentStatus() == null) {
            orderInfo.setPaymentStatus(0); // 未支付
        }
        
        return save(orderInfo);
    }

    @Override
    @Transactional
    public boolean cancelOrder(String id, String reason) {
        OrderInfo orderInfo = getById(id);
        
        if (orderInfo == null) {
            return false;
        }
        
        // 只有未支付或已支付待处理的订单可以取消
        if (orderInfo.getOrderStatus() != 0) {
            return false;
        }
        
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getOrderId, id)
                    .set(OrderInfo::getOrderStatus, 2) // 已取消
                    .set(OrderInfo::getRefundReason, reason)
                    .set(OrderInfo::getRefundTime, LocalDateTime.now())
                    .set(OrderInfo::getUpdateTime, LocalDateTime.now());
        
        return update(updateWrapper);
    }

    @Override
    @Transactional
    public boolean payOrder(String id) {
        OrderInfo orderInfo = getById(id);
        
        if (orderInfo == null || orderInfo.getPaymentStatus() == 1) {
            return false;
        }
        
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getOrderId, id)
                    .set(OrderInfo::getPaymentStatus, 1) // 已支付
                    .set(OrderInfo::getPaymentTime, LocalDateTime.now())
                    .set(OrderInfo::getUpdateTime, LocalDateTime.now());
        
        return update(updateWrapper);
    }

    @Override
    @Transactional
    public boolean updateOrderStatus(String id, Integer status) {
        OrderInfo orderInfo = getById(id);
        
        if (orderInfo == null) {
            return false;
        }
        
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getOrderId, id)
                    .set(OrderInfo::getOrderStatus, status)
                    .set(OrderInfo::getUpdateTime, LocalDateTime.now());
        
        return update(updateWrapper);
    }

    @Override
    public OrderStatistics getOrderStatistics(String userId) {
        OrderStatistics statistics = new OrderStatistics();
        
        // 使用LambdaQueryWrapper构建查询条件
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getUserId, userId);
        
        // 查询总订单数
        long totalOrders = this.count(wrapper);
        statistics.setTotalOrders((int) totalOrders);
        
        // 查询待处理订单数
        LambdaQueryWrapper<OrderInfo> pendingWrapper = new LambdaQueryWrapper<>();
        pendingWrapper.eq(OrderInfo::getUserId, userId);
        pendingWrapper.eq(OrderInfo::getOrderStatus, 0);
        long pendingOrders = this.count(pendingWrapper);
        statistics.setPendingOrders((int) pendingOrders);
        
        // 查询已完成订单数
        LambdaQueryWrapper<OrderInfo> completedWrapper = new LambdaQueryWrapper<>();
        completedWrapper.eq(OrderInfo::getUserId, userId);
        completedWrapper.eq(OrderInfo::getOrderStatus, 1);
        long completedOrders = this.count(completedWrapper);
        statistics.setCompletedOrders((int) completedOrders);
        
        // 查询已取消订单数
        LambdaQueryWrapper<OrderInfo> canceledWrapper = new LambdaQueryWrapper<>();
        canceledWrapper.eq(OrderInfo::getUserId, userId);
        canceledWrapper.eq(OrderInfo::getOrderStatus, 2);
        long canceledOrders = this.count(canceledWrapper);
        statistics.setCanceledOrders((int) canceledOrders);
        
        // 查询已完成订单的总金额
        List<OrderInfo> completedOrderList = this.list(completedWrapper);
        BigDecimal totalAmount = completedOrderList.stream()
            .map(OrderInfo::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        statistics.setTotalAmount(totalAmount);
        
        return statistics;
    }

    @Override
    public Map<String, Object> createOrder(String userId, String productId, String productType, Integer quantity, Map<String, Object> orderData) {
        // 创建订单对象
        OrderInfo orderInfo = new OrderInfo();
        
        // 生成订单ID
        String orderId = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        orderInfo.setOrderId(orderId);
        
        // 设置基本信息
        orderInfo.setUserId(userId);
        orderInfo.setProductId(productId);
        orderInfo.setProductType(productType);
        orderInfo.setQuantity(quantity);
        
        // 设置产品名称
        if (orderData.containsKey("productName")) {
            orderInfo.setProductName((String) orderData.get("productName"));
        }
        
        // 设置订单总价
        if (orderData.containsKey("totalPrice")) {
            if (orderData.get("totalPrice") instanceof BigDecimal) {
                orderInfo.setTotalPrice((BigDecimal) orderData.get("totalPrice"));
            } else if (orderData.get("totalPrice") instanceof Number) {
                orderInfo.setTotalPrice(new BigDecimal(orderData.get("totalPrice").toString()));
            }
        }
        
        // 设置联系人信息
        if (orderData.containsKey("contactName")) {
            orderInfo.setContactName((String) orderData.get("contactName"));
        }
        
        if (orderData.containsKey("contactPhone")) {
            orderInfo.setContactPhone((String) orderData.get("contactPhone"));
        }
        
        // 设置备注
        if (orderData.containsKey("remark")) {
            orderInfo.setRemark((String) orderData.get("remark"));
        }
        
        // 设置订单状态和时间
        orderInfo.setOrderStatus(0); // 待处理
        orderInfo.setPaymentStatus(0); // 未支付
        orderInfo.setOrderDate(LocalDateTime.now());
        orderInfo.setUpdateTime(LocalDateTime.now());
        
        // 保存订单
        boolean saveResult = save(orderInfo);
        
        if (saveResult) {
            // 返回订单信息
            Map<String, Object> result = new HashMap<>();
            result.put("orderId", orderId);
            result.put("totalPrice", orderInfo.getTotalPrice());
            result.put("orderStatus", orderInfo.getOrderStatus());
            result.put("paymentStatus", orderInfo.getPaymentStatus());
            result.put("createTime", orderInfo.getOrderDate());
            
            return result;
        }
        
        return null;
    }
    
    @Override
    public boolean cancelOrder(String orderId, String userId, String reason) {
        // 查询订单并验证所有者
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getOrderId, orderId)
                   .eq(OrderInfo::getUserId, userId);
        
        OrderInfo orderInfo = getOne(queryWrapper);
        
        if (orderInfo == null) {
            return false;
        }
        
        // 只有未支付或已支付待处理的订单可以取消
        if (orderInfo.getOrderStatus() != 0) {
            return false;
        }
        
        // 更新订单状态为已取消
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getOrderId, orderId)
                    .set(OrderInfo::getOrderStatus, 2) // 已取消
                    .set(OrderInfo::getUpdateTime, LocalDateTime.now());
        
        // 如果提供了取消原因，则记录
        if (reason != null && !reason.isEmpty()) {
            // 实际项目中可能需要将原因存储到数据库中或日志中
            System.out.println("订单取消原因: " + reason);
        }
        
        return update(updateWrapper);
    }
    
    @Override
    public Map<String, Object> payOrder(String orderId, String userId, String payMethod, Map<String, Object> payInfo) {
        // 查询订单并验证所有者
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getOrderId, orderId)
                   .eq(OrderInfo::getUserId, userId);
        
        OrderInfo orderInfo = getOne(queryWrapper);
        
        if (orderInfo == null) {
            return null;
        }
        
        // 订单已支付，返回订单信息
        if (orderInfo.getPaymentStatus() == 1) {
            Map<String, Object> result = new HashMap<>();
            result.put("orderId", orderId);
            result.put("paymentStatus", orderInfo.getPaymentStatus());
            result.put("paymentTime", orderInfo.getPaymentTime());
            result.put("message", "订单已支付");
            
            return result;
        }
        
        // 模拟支付处理
        // 实际项目中，这里应该调用支付网关API
        boolean paySuccess = simulatePayment(orderId, payMethod, payInfo);
        
        if (paySuccess) {
            // 更新订单支付状态
            LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(OrderInfo::getOrderId, orderId)
                        .set(OrderInfo::getPaymentStatus, 1) // 已支付
                        .set(OrderInfo::getPaymentTime, LocalDateTime.now())
                        .set(OrderInfo::getUpdateTime, LocalDateTime.now());
            
            update(updateWrapper);
            
            // 返回支付结果
            Map<String, Object> result = new HashMap<>();
            result.put("orderId", orderId);
            result.put("paymentStatus", 1);
            result.put("paymentTime", LocalDateTime.now());
            result.put("message", "支付成功");
            
            return result;
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("orderId", orderId);
            result.put("message", "支付失败");
            
            return result;
        }
    }
    
    /**
     * 模拟支付处理
     */
    private boolean simulatePayment(String orderId, String payMethod, Map<String, Object> payInfo) {
        // 模拟90%的支付成功率
        return Math.random() < 0.9;
    }
    
    @Override
    public boolean refundOrder(String orderId, String userId, String reason) {
        // 查询订单并验证所有者
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getOrderId, orderId)
                   .eq(OrderInfo::getUserId, userId);
        
        OrderInfo orderInfo = getOne(queryWrapper);
        
        if (orderInfo == null) {
            return false;
        }
        
        // 只有已支付的订单才能申请退款
        if (orderInfo.getPaymentStatus() != 1) {
            return false;
        }
        
        // 已取消的订单不能申请退款
        if (orderInfo.getOrderStatus() == 2) {
            return false;
        }
        
        // 模拟退款申请处理
        // 实际项目中，这里应该更新订单状态为"退款中"，并创建退款记录
        
        // 更新订单状态
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getOrderId, orderId)
                    .set(OrderInfo::getOrderStatus, 3) // 退款中
                    .set(OrderInfo::getUpdateTime, LocalDateTime.now());
        
        // 记录退款原因
        if (reason != null && !reason.isEmpty()) {
            // 实际项目中需要将原因存储到数据库中
            System.out.println("退款原因: " + reason);
        }
        
        return update(updateWrapper);
    }
    
    @Override
    public Map<String, Object> getOrderStatus(String orderId) {
        OrderInfo orderInfo = getById(orderId);
        
        if (orderInfo == null) {
            return null;
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("orderId", orderId);
        result.put("orderStatus", orderInfo.getOrderStatus());
        result.put("paymentStatus", orderInfo.getPaymentStatus());
        
        // 添加状态描述
        result.put("orderStatusText", getOrderStatusText(orderInfo.getOrderStatus()));
        result.put("paymentStatusText", getPaymentStatusText(orderInfo.getPaymentStatus()));
        
        // 添加时间信息
        result.put("createTime", orderInfo.getOrderDate());
        result.put("updateTime", orderInfo.getUpdateTime());
        if (orderInfo.getPaymentStatus() == 1) {
            result.put("paymentTime", orderInfo.getPaymentTime());
        }
        
        return result;
    }
    
    /**
     * 获取订单状态文本描述
     */
    private String getOrderStatusText(Integer status) {
        switch (status) {
            case 0: return "待处理";
            case 1: return "已完成";
            case 2: return "已取消";
            case 3: return "退款中";
            case 4: return "已退款";
            default: return "未知状态";
        }
    }
    
    /**
     * 获取支付状态文本描述
     */
    private String getPaymentStatusText(Integer status) {
        switch (status) {
            case 0: return "未支付";
            case 1: return "已支付";
            default: return "未知状态";
        }
    }
    
    @Override
    public IPage<OrderInfo> getUserOrders(String userId, Integer pageNum, Integer pageSize, Integer status) {
        Page<OrderInfo> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        
        // 查询指定用户的订单
        queryWrapper.eq(OrderInfo::getUserId, userId);
        
        // 如果指定了状态，则按状态筛选
        if (status != null) {
            queryWrapper.eq(OrderInfo::getOrderStatus, status);
        }
        
        // 按下单时间降序排序
        queryWrapper.orderByDesc(OrderInfo::getOrderDate);
        
        return page(page, queryWrapper);
    }
} 