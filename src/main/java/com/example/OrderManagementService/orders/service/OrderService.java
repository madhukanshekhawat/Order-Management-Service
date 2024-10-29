package com.example.OrderManagementService.orders.service;

import com.example.OrderManagementService.customer.repository.CustomerRepository;
import com.example.OrderManagementService.orders.entity.Orders;
import com.example.OrderManagementService.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Orders> getUndeliveredOrders(){
        return orderRepository.findUndeliveredOrders();
    }

    public List<Orders> getDeliveredOrdersForDay(LocalDate date){
        return orderRepository.findByIsDeliveredTrueAndPlacedTimeBetween(date.atStartOfDay(),date.plusDays(1).atStartOfDay());
    }

    public void markOrderAsDelivered(int orderId){
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("order not found"));
        orders.setDelivered(true);
        orders.setUpdatedTime(LocalDateTime.now());
        orderRepository.save(orders);
    }

    public void add(Orders orders) {

        customerRepository.findById(orders.getCustomer().getCustomerId())
                        .orElseThrow(() -> new RuntimeException("Customer not found"));

        orders.setPriority(orders.getWareHouseDistance() < 20);
        orders.setDelivered(false);

        orderRepository.save(orders);
    }

    public void delete(int id){
        orderRepository.deleteById(id);
    }
}
