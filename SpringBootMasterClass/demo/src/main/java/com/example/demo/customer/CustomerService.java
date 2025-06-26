package com.example.demo.customer;

import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    @Autowired
    public CustomerService(CustomerRepo customerRepo){
        this.customerRepo=customerRepo;
    }
    List<Customer>getCustomer(){
        return customerRepo.getCustomer();
    }
    Customer getCustomer(Long id){
        return getCustomer().stream().filter(customer -> customer.getId().equals(id)).
                findFirst().orElseThrow(()->new NotFoundException("customer id not found"));
    }
}
