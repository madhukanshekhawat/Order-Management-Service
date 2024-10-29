package com.example.OrderManagementService.customer.service;

import com.example.OrderManagementService.customer.entity.Customer;
import com.example.OrderManagementService.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer add(Customer customer){

        if(customer.getOrders() != null && customer.getOrders().size() >= 1){
            customer.setPremium(true);
        } else {
            customer.setPremium(false);
        }
        return customerRepository.save(customer);
    }

    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    public Customer update(int Id, Customer updated){
        return customerRepository.findById(Id).map(customer -> {
            customer.setFirstName(updated.getFirstName());
            customer.setLastName(updated.getLastName());
            customer.setEmailAddress(updated.getEmailAddress());
            customer.setAddress(updated.getAddress());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer with Id" + Id + "not found"));
    }
}
