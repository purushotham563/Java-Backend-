package com.example.demo.customer;

import com.example.demo.exception.NotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerService {

    private final static org.slf4j.Logger LOGGER=
             LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    List<Customer>getCustomer(){
        LOGGER.info("GetCustomer was called");
        return customerRepository.findAll();
    }
    public Customer getCustomer(Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException=new NotFoundException("customer with id"+id+" not found");
                    LOGGER.error("Error in getting customer with id {}", id,notFoundException);
                    return notFoundException;
                });
    }

}
