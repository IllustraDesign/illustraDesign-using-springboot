package com.illustraDesign.ecommerce.store.model;

import jakarta.persistence.*;

@Entity
public class admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    public admin() {}

    public admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public Long getId() { 
        return id; 
    }

    public String getUsername() { 
        return username; 
    }

    public void setUsername(String username) { 
        this.username = username; 
    }

    public String getPassword() { 
        return password; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }
}
