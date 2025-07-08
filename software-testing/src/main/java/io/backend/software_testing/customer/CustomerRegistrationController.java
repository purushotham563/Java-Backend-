package io.backend.software_testing.customer;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/v1/customer-registration"})
public class CustomerRegistrationController {
    private final CustomerRegistrationService customerRegistrationService;

    @Autowired
    public CustomerRegistrationController(CustomerRegistrationService customerRegistrationService) {
        this.customerRegistrationService = customerRegistrationService;
    }

    @PutMapping
    public void registerNewCustomer(@RequestBody @Valid CustomerRegistrationRequest request) {
        this.customerRegistrationService.registerNewCustomer(request);
    }

    public String toString() {
        return "CustomerRegistrationController{}";
    }
}

