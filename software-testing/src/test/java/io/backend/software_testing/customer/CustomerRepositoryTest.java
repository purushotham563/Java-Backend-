package io.backend.software_testing.customer;

import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.OptionalAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest(
        properties = {"spring.jpa.properties.javax.persistence.validation.mode=none"}
)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository underTest;

    CustomerRepositoryTest() {
    }

    @Test
    void itShouldSelectCustomerByPhoneNumber() {
        UUID id = UUID.randomUUID();
        String phoneNumber = "0000";
        Customer customer = new Customer(id, "Puli", phoneNumber);
        this.underTest.save(customer);
        Optional<Customer> optionalCustomer = this.underTest.selectCustomerByPhoneNumber(phoneNumber);
        ((OptionalAssert)Assertions.assertThat(optionalCustomer).isPresent()).hasValueSatisfying((c) -> {
            Assertions.assertThat(c).usingRecursiveComparison().isEqualTo(customer);
        });
    }

    @Test
    void itNotShouldSelectCustomerByPhoneNumberWhenNumberDoesNotExists() {
        String phoneNumber = "0000";
        Optional<Customer> optionalCustomer = this.underTest.selectCustomerByPhoneNumber(phoneNumber);
        Assertions.assertThat(optionalCustomer).isNotPresent();
    }

    @Test
    void itShouldSaveCustomer() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Puli", "0000");
        this.underTest.save(customer);
        Optional<Customer> optionalCustomer = this.underTest.findById(id);
        ((OptionalAssert)Assertions.assertThat(optionalCustomer).isPresent()).hasValueSatisfying((c) -> {
            Assertions.assertThat(c).usingRecursiveComparison().isEqualTo(customer);
        });
    }

    @Test
    void itShouldShouldNotSaveCustomerWhenNameIsNull() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, (String)null, "0000");
        Assertions.assertThatThrownBy(() -> {
            this.underTest.save(customer);
        }).hasMessageContaining("not-null property references a null or transient value: io.backend.software_testing.customer.Customer.name").isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void itShouldNotSaveCustomerWhenPhoneNumberIsNull() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Alex", (String)null);
        Assertions.assertThatThrownBy(() -> {
            this.underTest.save(customer);
        }).hasMessageContaining("not-null property references a null or transient value: io.backend.software_testing.customer.Customer.phoneNumber").isInstanceOf(DataIntegrityViolationException.class);
    }
}

