package io.backend.springBoot_DTO.Model;

import jakarta.validation.constraints.*;

public class OrderDTO {
    private Long id;

    @NotBlank(message = "Order number is required")
    @Pattern(regexp = "^ORD-\\d{6}$", message = "Order number must follow pattern ORD-XXXXXX")
    private String orderNumber;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.01", message = "Total amount must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Total amount must have max 10 integer digits and 2 decimal places")
    private Double totalAmount;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(PENDING|PROCESSING|SHIPPED|DELIVERED|CANCELLED)$",
            message = "Status must be one of: PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED")
    private String status;


    public OrderDTO() {}

    public OrderDTO(Long id, String orderNumber, Double totalAmount, String status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
