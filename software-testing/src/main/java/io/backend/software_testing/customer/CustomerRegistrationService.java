package io.backend.software_testing.customer;

import io.backend.software_testing.utils.PhoneNumberValidator;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerRegistrationService {
    private final CustomerRepository customerRepository;
    private final PhoneNumberValidator phoneNumberValidator;

    @Autowired
    public CustomerRegistrationService(CustomerRepository customerRepository, PhoneNumberValidator phoneNumberValidator) {
        this.customerRepository = customerRepository;
        this.phoneNumberValidator = phoneNumberValidator;
    }

    public void registerNewCustomer(CustomerRegistrationRequest request) {
        String phoneNumber = request.getCustomer().getPhoneNumber();
        if (!this.phoneNumberValidator.test(phoneNumber)) {
            throw new IllegalStateException(String.format("Phone Number [%s] is not valid", phoneNumber));
        } else {
            Optional<Customer> customerOptional = this.customerRepository.selectCustomerByPhoneNumber(phoneNumber);
            if (customerOptional.isPresent()) {
                Customer customer = (Customer)customerOptional.get();
                if (!customer.getName().equals(request.getCustomer().getName())) {
                    throw new IllegalStateException(String.format("Phone number [%s] is taken", phoneNumber));
                }
            } else {
                if (request.getCustomer().getId() == null) {
                    request.getCustomer().setId(UUID.randomUUID());
                }

                this.customerRepository.save(request.getCustomer());
            }
        }
    }
}

