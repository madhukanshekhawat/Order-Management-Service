package com.example.OrderManagementService.orders.entity;

import com.example.OrderManagementService.customer.entity.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private boolean priority;
    private LocalDateTime placedTime;
    private LocalDateTime updatedTime;
    private String deliveryAddress;
    private double wareHouseDistance;
    private boolean isDelivered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
//    @JsonIgnore
    @JsonBackReference
    private Customer customer;

    public Orders() {
    }

    public Orders(int orderId, boolean priority, LocalDateTime placedTime, LocalDateTime updatedTime, String deliveryAddress, double wareHouseDistance, boolean isDelivered) {
        this.orderId = orderId;
        this.priority = priority;
        this.placedTime = placedTime;
        this.updatedTime = updatedTime;
        this.deliveryAddress = deliveryAddress;
        this.wareHouseDistance = wareHouseDistance;
        this.isDelivered = isDelivered;
    }

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

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", priority=" + priority +
                ", placedTime=" + placedTime +
                ", updatedTime=" + updatedTime +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", wareHouseDistance=" + wareHouseDistance +
                ", isDelivered=" + isDelivered +
                '}';
    }
}
