package com.example.betterworld.validatorRules.createCharity;

public class CharityErrorFields {
    private Integer email;
    private Integer password;
    private Integer username;

    public Integer getUsername() { return username; }

    public void setUsername(Integer username) { this.username = username; }

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }
}
