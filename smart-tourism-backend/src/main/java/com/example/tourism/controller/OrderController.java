package com.example.tourism.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.tourism.common.Result;
import com.example.tourism.entity.OrderInfo;
import com.example.tourism.entity.OrderStatistics;
import com.example.tourism.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Api(tags = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    @ApiOperation("分页查询订单列表")
    public Result<IPage<OrderInfo>> getOrderList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String productType) {
        return Result.success(orderService.getOrderList(pageNum, pageSize, userId, productType));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("获取订单详情")
    public Result<OrderInfo> getOrderDetail(@PathVariable String id) {
        return Result.success(orderService.getOrderDetail(id));
    }

    @PostMapping("/create")
    @ApiOperation("创建订单")
    public Result<Boolean> createOrder(@RequestBody OrderInfo orderInfo) {
        return Result.success(orderService.createOrder(orderInfo));
    }

    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    public Result<Boolean> cancelOrder(@PathVariable String id, @RequestParam String reason) {
        return Result.success(orderService.cancelOrder(id, reason));
    }

    @PutMapping("/pay/{id}")
    @ApiOperation("支付订单")
    public Result<Boolean> payOrder(@PathVariable String id) {
        return Result.success(orderService.payOrder(id));
    }

    @PutMapping("/status/{id}")
    @ApiOperation("更新订单状态")
    public Result<Boolean> updateOrderStatus(@PathVariable String id, @RequestParam Integer status) {
        return Result.success(orderService.updateOrderStatus(id, status));
    }

    @GetMapping("/statistics/{userId}")
    @ApiOperation("获取用户订单统计")
    public Result<OrderStatistics> getOrderStatistics(@PathVariable String userId) {
        return Result.success(orderService.getOrderStatistics(userId));
    }
} 