package com.example.OrderManagementService.orders.repository;

import com.example.OrderManagementService.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query("SELECT o FROM Orders o WHERE o.isDelivered = false ORDER BY o.wareHouseDistance ASC, o.priority DESC, o.placedTime ASC")
    List<Orders> findUndeliveredOrders();

    List<Orders> findByIsDeliveredTrueAndPlacedTimeBetween(LocalDateTime start, LocalDateTime end);

}