package com.example.OrderManagementService.orders.dto;

import java.time.LocalDateTime;

public class OrderDto {

    private int orderId;
    private boolean priority;
    private LocalDateTime placedTime;
    private String deliveryAddress;
    private double wareHouseDistance;
    private boolean isDelivered;
    private int customerId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public LocalDateTime getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(LocalDateTime placedTime) {
        this.placedTime = placedTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getWareHouseDistance() {
        return wareHouseDistance;
    }

    public void setWareHouseDistance(double wareHouseDistance) {
        this.wareHouseDistance = wareHouseDistance;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
