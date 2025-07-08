package io.backend.software_testing.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long paymentId;
    private UUID customerId;
    private BigDecimal amount;
    private Currency currency;
    private String source;
    private String description;

    public Payment() {
    }

    public Payment(Long paymentId, UUID customerId, BigDecimal amount, Currency currency, String source, String description) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
        this.source = source;
        this.description = description;
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        Long var10000 = this.paymentId;
        return "Payment{paymentId=" + var10000 + ", customerId=" + String.valueOf(this.customerId) + ", amount=" + String.valueOf(this.amount) + ", currency=" + String.valueOf(this.currency) + ", source='" + this.source + "', description='" + this.description + "'}";
    }
}

