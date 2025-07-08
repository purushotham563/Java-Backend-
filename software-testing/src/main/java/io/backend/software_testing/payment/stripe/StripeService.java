package io.backend.software_testing.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import io.backend.software_testing.payment.CardPaymentCharge;
import io.backend.software_testing.payment.CardPaymentCharger;
import io.backend.software_testing.payment.Currency;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(
        value = {"stripe.enabled"},
        havingValue = "true"
)
@Service
public class StripeService implements CardPaymentCharger {
    private final StripeApi stripeApi;
    private static final RequestOptions requestOptions = RequestOptions.builder().
            setApiKey("your_sec_key").build();

    @Autowired
    public StripeService(StripeApi stripeApi) {
        this.stripeApi = stripeApi;
    }

    public CardPaymentCharge chargeCard(String cardSource, BigDecimal amount, Currency currency, String description) {
        Map<String, Object> params = new HashMap();
        params.put("amount", amount.multiply(new BigDecimal(100)).longValue());
        params.put("currency", currency.name());
        params.put("description", description);
        params.put("payment_method", cardSource);
        params.put("confirm", true);

        try {
            Charge charge = this.stripeApi.create(params, requestOptions);
            boolean chargePaid = charge.getPaid();
            return new CardPaymentCharge(chargePaid);
        } catch (StripeException var8) {
            throw new IllegalStateException("Cannot make stripe charge", var8);
        }
    }
}

