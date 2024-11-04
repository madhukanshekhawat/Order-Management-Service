package com.example.OrderManagementService.customer.repository;

import com.example.OrderManagementService.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
