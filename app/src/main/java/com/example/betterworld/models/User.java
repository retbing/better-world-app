package com.example.betterworld.models;

public class User {
    final String uuid;
    final String username;
    final String firstname;
    final String lastname;
    final String phoneNumber;
    final String imageUrl;
    final String address;
    final String socialMediaAccount;
    final String nameOfInstution;

    public User(String uuid, String username, String firstname, String lastname, String phoneNumber, String imageUrl, String address, String socialMediaAccount, String nameOfInstution) {
        this.uuid = uuid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.address = address;
        this.socialMediaAccount = socialMediaAccount;
        this.nameOfInstution = nameOfInstution;
    }

    public String getUuid() {
        return uuid;
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
