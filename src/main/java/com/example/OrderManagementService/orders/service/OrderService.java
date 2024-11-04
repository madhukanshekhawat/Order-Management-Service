package com.example.OrderManagementService.orders.service;

import com.example.OrderManagementService.ResourceNotFoundException;
import com.example.OrderManagementService.customer.entity.Customer;
import com.example.OrderManagementService.customer.repository.CustomerRepository;
import com.example.OrderManagementService.orders.dto.OrderDto;
import com.example.OrderManagementService.orders.entity.Orders;
import com.example.OrderManagementService.orders.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
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
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(String.format("order with ID %d not found",orderId)));
        orders.setDelivered(true);
        orders.setUpdatedTime(LocalDateTime.now());
        orderRepository.save(orders);
    }

    public OrderDto add(OrderDto orderdto) {

        Orders orders = new Orders();
        BeanUtils.copyProperties(orderdto, orders);
        orders.setPlacedTime(LocalDateTime.now());
        orders.setUpdatedTime(LocalDateTime.now());

        Customer customer = customerRepository.findById(orderdto.getCustomerId())
                        .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer not found with Id %d " + orderdto.getCustomerId())));

        orders.setCustomer(customer);
        orderRepository.save(orders);

        OrderDto responseDto = new OrderDto();
        BeanUtils.copyProperties(orders, responseDto);
        return responseDto;
    }

    public void delete(int id){
        orderRepository.deleteById(id);
    }

    public Orders getById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order with Id %d is not found.", id)));
    }
}
