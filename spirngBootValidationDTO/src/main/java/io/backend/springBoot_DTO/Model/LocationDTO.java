package io.backend.springBoot_DTO.Model;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;

public class LocationDTO {
    //Should have included the message for each but for demo I am not doing
    private Long id;
    @NotBlank(message = "Place name is required")
    @Size(min=2,max = 100)
    private String place;
    @Size(max = 500)
    private String description;
    @NotNull
    @DecimalMin(value="-180.0")
    @DecimalMax(value="+180.0")
    private Double longitude;
    @NotNull
    @DecimalMin(value="-90.0")
    @DecimalMax(value="+90.0")
    private Double latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public LocationDTO() {
    }

    public LocationDTO(Long id, String place, String description, Double longitude, Double latitude) {
        this.id = id;
        this.place = place;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
