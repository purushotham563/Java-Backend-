package com.example.demo.customer;

import java.util.Collections;
import java.util.List;


//@Repository
//@Primary
public class CustomerRepository implements CustomerRepo{
    @Override
    public List<Customer> getCustomer() {
        return Collections.singletonList(new Customer(3L,"TODO implement real db", "todo", "email@gmial.com"));
    }
}
