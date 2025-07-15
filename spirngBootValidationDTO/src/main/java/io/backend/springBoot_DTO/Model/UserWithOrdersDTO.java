package io.backend.springBoot_DTO.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;

import java.util.List;

public class UserWithOrdersDTO {
    private Long id;
    @NotBlank(message = "Email is required")
    @NotNull(message = "please provide the valid email")
    private String email;
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    @NotNull(message = "Location is required")
    @Valid
    private LocationDTO location;
    @Valid
    @Size(max = 100,message = "user cannot have more than 100 orders")
    private List<OrderDTO>orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public UserWithOrdersDTO() {
    }

    public UserWithOrdersDTO(Long id, String email, String firstName, String lastName, LocationDTO location, List<OrderDTO> orders) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.orders = orders;
    }
}
