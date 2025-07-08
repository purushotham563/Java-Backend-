package io.backend.software_testing.customer;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRegistrationRequest {
    private final Customer customer;

    public CustomerRegistrationRequest(@JsonProperty("customer") Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public String toString() {
        return "CustomerRegistrationRequest{customer=" + String.valueOf(this.customer) + "}";
    }
}
