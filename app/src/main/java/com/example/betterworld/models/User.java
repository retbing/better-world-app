package com.example.betterworld.models;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

    final String userId;
    final String username;
    final String firstname;
    final String lastname;
    final String phoneNumber;
    final String imageUrl;
    final String address;
    final String socialMediaAccount;
    final String nameOfInstution;

    public User( String username) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.firstname = "";
        this.lastname = "";
        this.phoneNumber = "";
        this.imageUrl = "";
        this.address = "";
        this.socialMediaAccount = "";
        this.nameOfInstution = "";
    }

    public User(String uuid, String username, String firstname,
                String lastname, String phoneNumber,
                String imageUrl, String address,
                String socialMediaAccount,
                String nameOfInstution) {
        this.userId = uuid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.address = address;
        this.socialMediaAccount = socialMediaAccount;
        this.nameOfInstution = nameOfInstution;
    }


    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getSocialMediaAccount() {
        return socialMediaAccount;
    }

    public String getNameOfInstution() {
        return nameOfInstution;
    }


}
