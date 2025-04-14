package com.example.tourism.controller.frontend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tourism.common.Result;
import com.example.tourism.entity.OrderInfo;
import com.example.tourism.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/frontend/order")
@Api(tags = "前端订单管理")
public class FrontendOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @ApiOperation("创建订单")
    public Result<Map<String, Object>> createOrder(@RequestBody Map<String, Object> orderInfo) {
        String userId = (String) orderInfo.get("userId");
        String productId = (String) orderInfo.get("productId");
        String productType = (String) orderInfo.get("productType"); // scenic, hotel, route
        Integer quantity = (Integer) orderInfo.get("quantity");
        
        if (userId == null || productId == null || productType == null || quantity == null) {
            return Result.error("缺少必要的订单信息");
        }
        
        // 调用服务创建订单
        Map<String, Object> orderResult = orderService.createOrder(userId, productId, productType, quantity, orderInfo);
        if (orderResult != null) {
            return Result.success(orderResult);
        } else {
            return Result.error("创建订单失败");
        }
    }

    @GetMapping("/list")
    @ApiOperation("获取用户订单列表")
    public Result<Map<String, Object>> getOrderList(
            @RequestParam String userId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer status) {
        
        IPage<OrderInfo> orderPage = orderService.getUserOrders(userId, pageNum, pageSize, status);
        
        Map<String, Object> result = new HashMap<>();
        result.put("total", orderPage.getTotal());
        result.put("pages", orderPage.getPages());
        result.put("current", orderPage.getCurrent());
        result.put("size", orderPage.getSize());
        result.put("records", orderPage.getRecords());
        
        return Result.success(result);
    }

    @GetMapping("/detail/{orderId}")
    @ApiOperation("获取订单详情")
    public Result<OrderInfo> getOrderDetail(@PathVariable String orderId) {
        OrderInfo order = orderService.getOrderDetail(orderId);
        if (order != null) {
            return Result.success(order);
        } else {
            return Result.error("订单不存在");
        }
    }

    @PostMapping("/cancel")
    @ApiOperation("取消订单")
    public Result<Boolean> cancelOrder(@RequestBody Map<String, Object> cancelInfo) {
        String orderId = (String) cancelInfo.get("orderId");
        String userId = (String) cancelInfo.get("userId");
        String reason = (String) cancelInfo.get("reason");
        
        if (orderId == null || userId == null) {
            return Result.error("缺少必要的订单信息");
        }
        
        boolean success = orderService.cancelOrder(orderId, userId, reason);
        if (success) {
            return Result.success(true);
        } else {
            return Result.error("取消订单失败");
        }
    }

    @PostMapping("/pay")
    @ApiOperation("支付订单")
    public Result<Map<String, Object>> payOrder(@RequestBody Map<String, Object> payInfo) {
        String orderId = (String) payInfo.get("orderId");
        String userId = (String) payInfo.get("userId");
        String payMethod = (String) payInfo.get("payMethod"); // alipay, wxpay
        
        if (orderId == null || userId == null || payMethod == null) {
            return Result.error("缺少必要的支付信息");
        }
        
        Map<String, Object> payResult = orderService.payOrder(orderId, userId, payMethod, payInfo);
        if (payResult != null) {
            return Result.success(payResult);
        } else {
            return Result.error("支付失败");
        }
    }

    @PostMapping("/refund")
    @ApiOperation("申请退款")
    public Result<Boolean> refundOrder(@RequestBody Map<String, Object> refundInfo) {
        String orderId = (String) refundInfo.get("orderId");
        String userId = (String) refundInfo.get("userId");
        String reason = (String) refundInfo.get("reason");
        
        if (orderId == null || userId == null) {
            return Result.error("缺少必要的退款信息");
        }
        
        boolean success = orderService.refundOrder(orderId, userId, reason);
        if (success) {
            return Result.success(true);
        } else {
            return Result.error("申请退款失败");
        }
    }

    @GetMapping("/status/{orderId}")
    @ApiOperation("查询订单状态")
    public Result<Map<String, Object>> getOrderStatus(@PathVariable String orderId) {
        Map<String, Object> statusInfo = orderService.getOrderStatus(orderId);
        if (statusInfo != null) {
            return Result.success(statusInfo);
        } else {
            return Result.error("订单不存在");
        }
    }
}
