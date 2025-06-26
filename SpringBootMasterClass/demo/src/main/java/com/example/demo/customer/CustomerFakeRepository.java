package com.example.demo.customer;

import java.util.Arrays;
import java.util.List;

//@Repository(value ="fake")
public class CustomerFakeRepository implements CustomerRepo{
    @Override
    public List<Customer> getCustomer() {
        return Arrays.asList(new Customer(2L,"puli", "password123", "email@gmial.com"),new Customer(3L,"budhi", "password", "email@gmial.com"));
    }
}
