package com.validation.validation;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class User {
    @NotNull(message = "Name cannot be null")
    private String name;
    @AssertTrue(message = "working must be true")
    private boolean working;
    @Size(message = "about be must be between the 10 to 200 characters",min = 10,max = 200)
    private String aboutMe;
    @Min(value = 18,message = "age should  not be less than 18")
    @Max(value = 100,message = "age should not be greater than 100 ")
    private int age;
    @Email(message = "email should be valid")

    private String email;

    public User(String name, boolean working, String aboutMe, int age, String email) {
        this.name = name;
        this.working = working;
        this.aboutMe = aboutMe;
        this.age = age;
        this.email = email;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private List<@NotBlank String> pre;

    public String getName() {
        return name;
    }
    private LocalDate dateOfBirth;
    public Optional<@Past LocalDate>getDateOfBirth(){
        return Optional.of(dateOfBirth);
    }


    public boolean isWorking() {
        return working;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
