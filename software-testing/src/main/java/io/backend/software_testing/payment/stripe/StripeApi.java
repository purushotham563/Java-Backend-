package io.backend.software_testing.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class StripeApi {
    public StripeApi() {
    }

    public Charge create(Map<String, Object> requestMap, RequestOptions options) throws StripeException {
        return PaymentIntent.create(requestMap, options).getLatestChargeObject();
    }
}
