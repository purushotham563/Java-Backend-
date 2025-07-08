package io.backend.software_testing.customer;

import io.backend.software_testing.utils.PhoneNumberValidator;
import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class CustomerRegistrationServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PhoneNumberValidator phoneNumberValidator;
    private CustomerRegistrationService underTest;
    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;
    private AutoCloseable closeable;

    CustomerRegistrationServiceTest() {
    }

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.underTest = new CustomerRegistrationService(this.customerRepository, this.phoneNumberValidator);
    }

    @Test
    void itShouldSaveNewCustomer() {
        String phoneNumber = "0000999";
        Customer customer = new Customer(UUID.randomUUID(), "Puli", phoneNumber);
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        BDDMockito.given(this.customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.empty());
        BDDMockito.given(this.phoneNumberValidator.test(phoneNumber)).willReturn(true);
        this.underTest.registerNewCustomer(request);
        ((CustomerRepository)BDDMockito.then(this.customerRepository).should()).save((Customer)this.customerArgumentCaptor.capture());
        Customer customer1 = (Customer)this.customerArgumentCaptor.getValue();
        Assertions.assertThat(customer1).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    void itShouldNotSaveCustomerWhenCustomerExits() {
        String phoneNumber = "0000999";
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Puli", phoneNumber);
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        BDDMockito.given(this.customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.of(customer));
        BDDMockito.given(this.phoneNumberValidator.test(phoneNumber)).willReturn(true);
        this.underTest.registerNewCustomer(request);
        ((CustomerRepository)BDDMockito.then(this.customerRepository).should()).selectCustomerByPhoneNumber(phoneNumber);
        BDDMockito.then(this.customerRepository).shouldHaveNoMoreInteractions();
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void itShouldThrowWhenPhoneNumberIsTaken() {
        String phoneNumber = "0000999";
        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, "Puli", phoneNumber);
        Customer customer1 = new Customer(id, "Jhon", phoneNumber);
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        BDDMockito.given(this.customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.of(customer1));
        BDDMockito.given(this.phoneNumberValidator.test(phoneNumber)).willReturn(true);
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            this.underTest.registerNewCustomer(request);
        }).isInstanceOf(IllegalStateException.class)).hasMessageContaining(String.format("Phone number [%s] is taken", phoneNumber));
        ((CustomerRepository)BDDMockito.then(this.customerRepository).should(Mockito.never())).save((Customer)ArgumentMatchers.any(Customer.class));
    }

    @Test
    void itShouldSaveNewCustomerWhenIdIsNull() {
        String phoneNumber = "0000999";
        Customer customer = new Customer((UUID)null, "Puli", phoneNumber);
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        BDDMockito.given(this.customerRepository.selectCustomerByPhoneNumber(phoneNumber)).willReturn(Optional.empty());
        BDDMockito.given(this.phoneNumberValidator.test(phoneNumber)).willReturn(true);
        this.underTest.registerNewCustomer(request);
        ((CustomerRepository)BDDMockito.then(this.customerRepository).should()).save((Customer)this.customerArgumentCaptor.capture());
        Customer customer1 = (Customer)this.customerArgumentCaptor.getValue();
        Assertions.assertThat(customer1).usingRecursiveComparison().ignoringFields(new String[]{"id"}).isEqualTo(customer);
        Assertions.assertThat(customer1.getId()).isNotNull();
    }

    @Test
    void itShouldNotSaveNewCustomerWhenPhoneNumberIsInvalid() {
        String phoneNumber = "0000999";
        Customer customer = new Customer(UUID.randomUUID(), "Puli", phoneNumber);
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(customer);
        BDDMockito.given(this.phoneNumberValidator.test(phoneNumber)).willReturn(false);
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            this.underTest.registerNewCustomer(request);
        }).isInstanceOf(IllegalStateException.class)).hasMessageContaining(String.format("Phone Number [%s] is not valid", phoneNumber));
        BDDMockito.then(this.customerRepository).shouldHaveNoInteractions();
    }
}
