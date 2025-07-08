package io.backend.software_testing.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.backend.software_testing.customer.Customer;
import io.backend.software_testing.customer.CustomerRegistrationRequest;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PaymentRepository paymentRepository;

    PaymentIntegrationTest() {
    }

    @Test
    void itShouldCreatePaymentSuccessfully() throws Exception {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "James", "+447000000000");
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(customer);
        Payment payment = new Payment((Long)null, customerId, new BigDecimal("100.00"), Currency.GBP, "x0x0x0x0", "zakat");
        PaymentRequest paymentRequest = new PaymentRequest(payment);
        ResultActions customerRegResultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer-registration", new Object[0]).contentType(MediaType.APPLICATION_JSON).content((String)Objects.requireNonNull(this.objectToJson(customerRegistrationRequest))));
        ResultActions payemntResultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payment", new Object[0]).contentType(MediaType.APPLICATION_JSON).content((String)Objects.requireNonNull(this.objectToJson(paymentRequest))));
        customerRegResultActions.andExpect(MockMvcResultMatchers.status().isOk());
        Payment savedPayment = (Payment)this.paymentRepository.findAll().iterator().next();
        Assertions.assertThat(savedPayment.getCustomerId()).isEqualTo(customerId);
        Assertions.assertThat(savedPayment.getAmount()).isEqualTo(new BigDecimal("100.00"));
        Assertions.assertThat(savedPayment.getCurrency()).isEqualTo(Currency.GBP);
        Assertions.assertThat(savedPayment.getSource()).isEqualTo("x0x0x0x0");
        Assertions.assertThat(savedPayment.getDescription()).isEqualTo("zakat");
    }

    private String objectToJson(Object object) {
        try {
            return (new ObjectMapper()).writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            Fail.fail("Failed to covert object to json");
            return null;
        }
    }
}

