package com.example.OrderManagementService.orders.controller;

import com.example.OrderManagementService.ResourceNotFoundException;
import com.example.OrderManagementService.orders.dto.OrderDto;
import com.example.OrderManagementService.orders.dto.OrderResponse;
import com.example.OrderManagementService.orders.entity.Orders;
import com.example.OrderManagementService.orders.service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrderRequest(@RequestBody OrderDto orderDto){
        OrderDto savedOrder = orderService.add(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getById(@PathVariable int id){
        Orders orders = orderService.getById(id);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/undelivered")
    public List<Orders> getUndeliveredOrders(){
        return orderService.getUndeliveredOrders();
    }

    @GetMapping("/delivered/{date}")
    public List<Orders> getDeliveredOrdersForDay(@PathVariable String date){
        return orderService.getDeliveredOrdersForDay(LocalDate.parse(date));
    }

    @PutMapping("/delivered/{id}")
    public ResponseEntity<OrderResponse> markOrdersAsDelivered(@PathVariable int id){
        OrderResponse response = orderService.markOrderAsDelivered(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        orderService.delete(id);
    }
}
