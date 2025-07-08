package io.backend.software_testing.payment;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.OptionalAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(
        properties = {"spring.jpa.properties.javax.persistence.validation.mode=none"}
)
class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository underTest;

    PaymentRepositoryTest() {
    }

    @Test
    void itShouldInsertPayment() {
        Payment payment = new Payment((Long)null, UUID.randomUUID(), new BigDecimal("10.00"), Currency.USD, "card123", "donation");
        Payment savedPayment = (Payment)this.underTest.save(payment);
        Optional<Payment> paymentOptional = this.underTest.findById(savedPayment.getPaymentId());
        ((OptionalAssert)AssertionsForClassTypes.assertThat(paymentOptional).isPresent()).hasValueSatisfying((p) -> {
            AssertionsForClassTypes.assertThat(p).usingRecursiveComparison().isEqualTo(payment);
        });
    }

    @AfterEach
    void tearDown() {
        this.underTest.deleteAll();
    }
}

