package com.example.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {          // REQUIRED
        return name;
    }

    public void setName(String name) { // REQUIRED
        this.name = name;
    }

    public String getEmail() {         // REQUIRED
        return email;
    }

    public void setEmail(String email) { // REQUIRED
        this.email = email;
    }

    public String getPassword() {      // REQUIRED
        return password;
    }

    public void setPassword(String password) { // REQUIRED
        this.password = password;
    }
}
