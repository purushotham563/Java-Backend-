package io.backend.software_testing.payment;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PaymentRequest {
    private final Payment payment;

    public PaymentRequest(@JsonProperty("payment") Payment payment) {
        this.payment = payment;
    }

    public String toString() {
        return "PaymentRequest{payment=" + String.valueOf(this.payment) + "}";
    }

    public Payment getPayment() {
        return this.payment;
    }
}
