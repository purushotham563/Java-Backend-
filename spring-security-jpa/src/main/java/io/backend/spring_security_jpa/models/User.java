package io.backend.spring_security_jpa.models;


import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;

   private String userName;
   private String password;
   private boolean active;
   private String roles;
   public User(){

   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
