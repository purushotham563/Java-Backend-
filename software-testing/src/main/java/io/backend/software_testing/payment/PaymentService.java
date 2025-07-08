package io.backend.software_testing.payment;

import io.backend.software_testing.customer.CustomerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    private final CardPaymentCharger cardPaymentCharger;
    private static final List<Currency> ACCEPTED_CURRENCIES;

    @Autowired
    public PaymentService(CustomerRepository customerRepository, PaymentRepository paymentRepository, CardPaymentCharger cardPaymentCharger) {
        this.customerRepository = customerRepository;
        this.paymentRepository = paymentRepository;
        this.cardPaymentCharger = cardPaymentCharger;
    }

    void chargeCard(UUID customerId, PaymentRequest paymentRequest) {
        boolean isCustomerFound = this.customerRepository.findById(customerId).isPresent();
        if (!isCustomerFound) {
            throw new IllegalStateException(String.format("Customer with id [%s] not found", customerId));
        } else {
            boolean isCurrencySupported = ACCEPTED_CURRENCIES.stream().anyMatch((c) -> {
                return c.equals(paymentRequest.getPayment().getCurrency());
            });
            if (!isCurrencySupported) {
                String message = String.format("Currency[%s] not supported", paymentRequest.getPayment().getCurrency());
                throw new IllegalStateException(message);
            } else {
                CardPaymentCharge cardPaymentCharge = this.cardPaymentCharger.chargeCard(paymentRequest.getPayment().getSource(), paymentRequest.getPayment().getAmount(), paymentRequest.getPayment().getCurrency(), paymentRequest.getPayment().getDescription());
                if (!cardPaymentCharge.isCardDebited()) {
                    throw new IllegalStateException(String.format("Card not debited for customer %s", customerId));
                } else {
                    paymentRequest.getPayment().setCustomerId(customerId);
                    this.paymentRepository.save(paymentRequest.getPayment());
                }
            }
        }
    }

    static {
        ACCEPTED_CURRENCIES = List.of(Currency.USD, Currency.GBP);
    }
}

