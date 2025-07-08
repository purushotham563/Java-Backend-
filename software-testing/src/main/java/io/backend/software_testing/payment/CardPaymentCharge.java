package io.backend.software_testing.payment;

public class CardPaymentCharge {
    private final boolean isCardDebited;

    public CardPaymentCharge(boolean isCardDebited) {
        this.isCardDebited = isCardDebited;
    }

    public boolean isCardDebited() {
        return this.isCardDebited;
    }

    public String toString() {
        return "CardPaymentCharge{isCardDebited=" + this.isCardDebited + "}";
    }
}
