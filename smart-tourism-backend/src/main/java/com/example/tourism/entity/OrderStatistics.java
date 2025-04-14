package com.example.tourism.entity;

import java.math.BigDecimal;

public class OrderStatistics {
    private Integer totalOrders;
    private Integer pendingOrders;
    private Integer completedOrders;
    private Integer canceledOrders;
    private BigDecimal totalAmount;

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Integer getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Integer pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public Integer getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Integer completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Integer getCanceledOrders() {
        return canceledOrders;
    }

    public void setCanceledOrders(Integer canceledOrders) {
        this.canceledOrders = canceledOrders;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
} 