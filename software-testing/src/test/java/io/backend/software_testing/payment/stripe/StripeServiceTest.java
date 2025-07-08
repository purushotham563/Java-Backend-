package io.backend.software_testing.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import io.backend.software_testing.payment.CardPaymentCharge;
import io.backend.software_testing.payment.Currency;
import java.math.BigDecimal;
import java.util.Map;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.MapAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class StripeServiceTest {
    private StripeService underTest;
    @Mock
    private StripeApi stripeApi;
    private AutoCloseable closeable;

    StripeServiceTest() {
    }

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.underTest = new StripeService(this.stripeApi);
    }

    @Test
    void itShouldChargeCardSuccessfully() throws StripeException {
        String cardSource = "pm_test_card";
        BigDecimal amount = new BigDecimal("100.00");
        Currency currency = Currency.USD;
        String description = "Test charge";
        Charge charge = new Charge();
        charge.setPaid(true);
        BDDMockito.given(this.stripeApi.create(ArgumentMatchers.anyMap(), (RequestOptions)ArgumentMatchers.any())).willReturn(charge);
        CardPaymentCharge cardPaymentCharge = this.underTest.chargeCard(cardSource, amount, currency, description);
        ArgumentCaptor<Map<String, Object>> mapCaptor = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<RequestOptions> optionsCaptor = ArgumentCaptor.forClass(RequestOptions.class);
        ((StripeApi)BDDMockito.then(this.stripeApi).should()).create((Map)mapCaptor.capture(), (RequestOptions)optionsCaptor.capture());
        Map<String, Object> requestMap = (Map)mapCaptor.getValue();
        Assertions.assertThat(requestMap.keySet()).hasSize(5);
        ((MapAssert)((MapAssert)((MapAssert)((MapAssert)Assertions.assertThat(requestMap).containsEntry("amount", 10000L)).containsEntry("currency", "USD")).containsEntry("payment_method", cardSource)).containsEntry("description", description)).containsEntry("confirm", true);
        RequestOptions options = (RequestOptions)optionsCaptor.getValue();
        Assertions.assertThat(options).isNotNull();
        Assertions.assertThat(cardPaymentCharge).isNotNull();
        Assertions.assertThat(cardPaymentCharge.isCardDebited()).isTrue();
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    void itShouldNotChargeWhenApiThrowsException() throws StripeException {
        String cardSource = "pm_test_card";
        BigDecimal amount = new BigDecimal("100.00");
        Currency currency = Currency.USD;
        String description = "Test charge";
        StripeException stripeException = (StripeException)Mockito.mock(StripeException.class);
        ((StripeApi)Mockito.doThrow(new Throwable[]{stripeException}).when(this.stripeApi)).create(ArgumentMatchers.anyMap(), (RequestOptions)ArgumentMatchers.any());
        ((AbstractThrowableAssert)Assertions.assertThatThrownBy(() -> {
            this.underTest.chargeCard(cardSource, amount, currency, description);
        }).isInstanceOf(IllegalStateException.class)).hasRootCause(stripeException).hasMessageContaining("Cannot make stripe charge");
    }
}
