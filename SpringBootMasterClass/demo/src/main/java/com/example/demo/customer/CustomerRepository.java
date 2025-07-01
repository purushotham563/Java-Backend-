package com.example.demo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.Collections;
//import java.util.List;


//@Repository
//@Primary
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
//    @Override
//    public List<Customer> getCustomer() {
//        return Collections.singletonList(new Customer(3L,"TODO implement real db", "todo", "email@gmial.com"));
//    }

}
