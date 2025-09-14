package io.backend.software_testing.payment.stripe;

import io.backend.software_testing.payment.CardPaymentCharge;
import io.backend.software_testing.payment.CardPaymentCharger;
import io.backend.software_testing.payment.Currency;
import java.math.BigDecimal;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        value = {"stripe.enabled"},
        havingValue = "false"
)
public class MockStripeService implements CardPaymentCharger {
    public MockStripeService() {
    }

    public CardPaymentCharge chargeCard(String cardSource,
                                        BigDecimal amount,

                                        Currency currency, String description) {
        return new CardPaymentCharge(true);
    }
}
