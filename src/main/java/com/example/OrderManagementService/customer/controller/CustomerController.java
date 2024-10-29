package com.example.OrderManagementService.customer.controller;

import com.example.OrderManagementService.customer.entity.Customer;
import com.example.OrderManagementService.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAll();
    }

    @PostMapping
    public void add(@RequestBody Customer customer){
        customerService.add(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        customerService.delete(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable int id, @RequestBody Customer updated){
        return customerService.update(id,updated);
    }
}
