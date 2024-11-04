package com.example.OrderManagementService.customer.controller;

import com.example.OrderManagementService.customer.dto.CustomerDTO;
import com.example.OrderManagementService.customer.entity.Customer;
import com.example.OrderManagementService.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CustomerDTO>> getAll(){
        List<CustomerDTO> customers = customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> add(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedCustomer = customerService.add(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        customerService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable int id){
        CustomerDTO customerDTO = customerService.getById(id);
        return ResponseEntity.ok(customerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerRequest(@PathVariable int id, @RequestBody Customer updated){
        CustomerDTO customerDTO = customerService.update(id,updated);
        return ResponseEntity.ok(customerDTO);
    }
}
