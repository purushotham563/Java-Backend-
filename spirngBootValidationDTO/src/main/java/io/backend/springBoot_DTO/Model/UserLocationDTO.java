package io.backend.springBoot_DTO.Model;

public class UserLocationDTO {
    private Long userId;
    private String email;
    private String place;
    private double longitude;
    private double latitude;

    public UserLocationDTO(Long userId,
                           String email,
                           String place,
                           double longitude,
                           double latitude) {
        this.userId = userId;
        this.email = email;
        this.place = place;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public UserLocationDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
