package io.backend.software_testing.payment;

import io.backend.software_testing.customer.Customer;
import io.backend.software_testing.customer.CustomerRepository;
import java.math.BigDecimal;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class PaymentServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private CardPaymentCharger cardPaymentCharger;
    private PaymentService underTest;
    private AutoCloseable closeable;

    PaymentServiceTest() {
    }

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.underTest = new PaymentService(this.customerRepository, this.paymentRepository, this.cardPaymentCharger);
    }

    @Test
    void itShouldChargeCardSuccessfully() {
        UUID customerId = UUID.randomUUID();
        BDDMockito.given(this.customerRepository.findById(customerId)).willReturn(Optional.of((Customer)Mockito.mock(Customer.class)));
        Currency currency = Currency.USD;
        PaymentRequest paymentRequest = new PaymentRequest(new Payment((Long)null, (UUID)null, new BigDecimal("100.00"), currency, "card123xxx", "Donation"));
        BDDMockito.given(this.cardPaymentCharger.chargeCard(paymentRequest.getPayment().getSource(), paymentRequest.getPayment().getAmount(), paymentRequest.getPayment().getCurrency(), paymentRequest.getPayment().getDescription())).willReturn(new CardPaymentCharge(true));
        this.underTest.chargeCard(customerId, paymentRequest);
        ArgumentCaptor<Payment> paymentRequestArgumentCaptor = ArgumentCaptor.forClass(Payment.class);
        ((PaymentRepository)BDDMockito.then(this.paymentRepository).should()).save((Payment)paymentRequestArgumentCaptor.capture());
        Payment paymentRequestArgumentCaptorValue = (Payment)paymentRequestArgumentCaptor.getValue();
        Assertions.assertThat(paymentRequestArgumentCaptorValue).usingRecursiveComparison().ignoringFields(new String[]{"customerId"}).isEqualTo(paymentRequest.getPayment());
        Assertions.assertThat(paymentRequestArgumentCaptorValue.getCustomerId()).isEqualTo(customerId);
    }

    @Test
    void itShouldThrowWhenCardIsNotCharged() {
        UUID customerId = UUID.randomUUID();
        BDDMockito.given(this.customerRepository.findById(customerId)).willReturn(Optional.of((Customer)Mockito.mock(Customer.class)));
        Currency currency = Currency.USD;
        PaymentRequest paymentRequest = new PaymentRequest(new Payment((Long)null, (UUID)null, new BigDecimal("100.00"), currency, "card123xxx", "Donation"));
        BDDMockito.given(this.cardPaymentCharger.chargeCard(paymentRequest.getPayment().getSource(), paymentRequest.getPayment().getAmount(), paymentRequest.getPayment().getCurrency(), paymentRequest.getPayment().getDescription())).willReturn(new CardPaymentCharge(false));
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            this.underTest.chargeCard(customerId, paymentRequest);
        }).isInstanceOf(IllegalStateException.class)).hasMessageContaining(String.format("Card not debited for customer %s", customerId));
        ((PaymentRepository)BDDMockito.then(this.paymentRepository).should(Mockito.never())).save((Payment)ArgumentMatchers.any(Payment.class));
    }

    @Test
    void itShouldNotAndThrowChargeWhenCurrencyNotSupported() {
        UUID customerId = UUID.randomUUID();
        BDDMockito.given(this.customerRepository.findById(customerId)).willReturn(Optional.of((Customer)Mockito.mock(Customer.class)));
        Currency currency = Currency.INR;
        PaymentRequest paymentRequest = new PaymentRequest(new Payment((Long)null, (UUID)null, new BigDecimal("100.00"), currency, "card123xxx", "Donation"));
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            this.underTest.chargeCard(customerId, paymentRequest);
        }).isInstanceOf(IllegalStateException.class)).hasMessageContaining(String.format("Currency[%s] not supported", paymentRequest.getPayment().getCurrency()));
        BDDMockito.then(this.cardPaymentCharger).shouldHaveNoInteractions();
        BDDMockito.then(this.paymentRepository).shouldHaveNoInteractions();
    }

    @Test
    void itShouldNotChargeAndThrowWhenCustomerNotFound() {
        UUID customerId = UUID.randomUUID();
        BDDMockito.given(this.customerRepository.findById(customerId)).willReturn(Optional.empty());
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            this.underTest.chargeCard(customerId, new PaymentRequest(new Payment()));
        }).isInstanceOf(IllegalStateException.class)).hasMessageContaining(String.format("Customer with id [%s] not found", customerId));
        BDDMockito.then(this.cardPaymentCharger).shouldHaveNoInteractions();
        BDDMockito.then(this.paymentRepository).shouldHaveNoInteractions();
    }

    @AfterEach
    void tearUp() throws Exception {
        this.closeable.close();
    }
}

