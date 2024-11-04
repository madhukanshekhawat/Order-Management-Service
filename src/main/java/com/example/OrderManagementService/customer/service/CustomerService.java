package com.example.OrderManagementService.customer.service;

import com.example.OrderManagementService.ResourceNotFoundException;
import com.example.OrderManagementService.customer.dto.CustomerDTO;
import com.example.OrderManagementService.customer.entity.Customer;
import com.example.OrderManagementService.customer.repository.CustomerRepository;
import com.example.OrderManagementService.orders.dto.OrderDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAll(){
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for(Customer customer : customers){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setFirstName(customer.getFirstName());
            customerDTO.setLastName(customer.getLastName());
            customerDTO.setEmailAddress(customer.getEmailAddress());
            customerDTO.setAddress(customer.getAddress());
            customerDTO.setCreatedAt(customer.getCreatedAt());
            customer.setPremium(customer.isPremium());

            List<OrderDto> orderDtos = customer.getOrders().stream()
                    .map(orders -> {
                        OrderDto orderDto = new OrderDto();
                        orderDto.setOrderId(orders.getOrderId());
                        orderDto.setDeliveryAddress(orders.getDeliveryAddress());
                        orderDto.setWareHouseDistance(orders.getWareHouseDistance());
                        orderDto.setPlacedTime(orders.getPlacedTime());
                        orderDto.setCustomerId(customer.getCustomerId());
                        orderDto.setDelivered(orderDto.isDelivered());
                        return orderDto;
                    })
                    .collect(Collectors.toList());
            customerDTO.setOrderDtoList(orderDtos);
            customerDTOS.add(customerDTO);

        }
        return customerDTOS;
    }

    public CustomerDTO add(CustomerDTO customerDTO){

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());

        customerRepository.save(customer);

        CustomerDTO responseDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer , responseDTO);
        return responseDTO;
    }

    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    public CustomerDTO update(int customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with ID %d not found", customerId)));
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmailAddress(updatedCustomer.getEmailAddress());
        existingCustomer.setPremium(updatedCustomer.isPremium());
        existingCustomer.setUpdatedAt(LocalDateTime.now());

        Customer savedCustomer = customerRepository.save(existingCustomer);

        return new CustomerDTO(
                savedCustomer.getCustomerId(),
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.isPremium(),
                savedCustomer.getEmailAddress()
        );
    }

    public CustomerDTO getById(int id) {
       Customer customer = customerRepository.findById(id)
               .orElseThrow( ()-> new ResourceNotFoundException(String.format("Customer with Id %d not found",id)));

       return new CustomerDTO(
               customer.getCustomerId(),
               customer.getFirstName(),
               customer.getLastName(),
               customer.isPremium(),
               customer.getEmailAddress()
       );

    }
}


