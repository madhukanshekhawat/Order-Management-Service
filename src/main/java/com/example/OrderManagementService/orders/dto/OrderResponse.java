package com.example.OrderManagementService.orders.dto;

public class OrderResponse {
    private int orderId;
    private boolean isDelivered;
    private String message;

    public OrderResponse(int orderId, boolean isDelivered, String message) {
        this.orderId = orderId;
        this.isDelivered = isDelivered;
        this.message = message;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
