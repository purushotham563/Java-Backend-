package io.backend.software_testing.customer;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@JsonIgnoreProperties(
        allowGetters = true
)
public class Customer {
    @Id
    private UUID id;
    @Column(
            nullable = false
    )
    private @NotBlank String name;
    @Column(
            nullable = false,
            unique = true
    )
    private @NotBlank String phoneNumber;

    public Customer(UUID id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String toString() {
        String var10000 = String.valueOf(this.id);
        return "Customer{id=" + var10000 + ", name='" + this.name + "', phoneNumber='" + this.phoneNumber + "'}";
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}