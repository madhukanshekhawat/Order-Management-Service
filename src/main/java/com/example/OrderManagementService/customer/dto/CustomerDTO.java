package com.example.OrderManagementService.customer.dto;

import com.example.OrderManagementService.orders.dto.OrderDto;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerDTO {

    private int customerId;
    private String firstName;
    private String lastName;
    private boolean isPremium;
    private LocalDateTime createdAt;
    private String emailAddress;
    private String address;
    private List<OrderDto> orders;

    public CustomerDTO(int customerId, String firstName, String lastName, boolean isPremium, LocalDateTime createdAt, String emailAddress, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isPremium = isPremium;
        this.createdAt = createdAt;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public CustomerDTO() {

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderDto> getOrderDtoList() {
        return orders;
    }

    public void setOrderDtoList(List<OrderDto> orderDtoList) {
        this.orders = orderDtoList;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
