package com.example.betterworld.models;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {

    final String userId;
    final String username;
    final String email;
    String firstName;
    String lastName;
    String phoneNumber;
    String imageUrl;
    String address;
    String socialMediaAccount;
    String nameOfInstitution;


    public User(String uuid, String username, String email) {
        this.userId = uuid;
        this.username = username;
        this.email = email;
    }
    public User(String uuid, String username, String email, String firstName, String lastName, String phoneNumber, String imageUrl, String address, String socialMediaAccount, String nameOfInstitution) {
        this.userId = uuid;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.address = address;
        this.socialMediaAccount = socialMediaAccount;
        this.nameOfInstitution = nameOfInstitution;
    }


    public static User userFromMap(String uuid, String email, Map<String, Object> userMap) {
        final String username = (String) userMap.get("username");
        final String firstName = (String) userMap.get("firstName");
        final String lastName = (String) userMap.get("lastName");
        final String phoneNumber = (String) userMap.get("phoneNumber");
        final String imageUrl = (String) userMap.get("imageUrl");
        final String address = (String) userMap.get("address");
        final String socialMediaAccount = (String) userMap.get("socialMediaAccount");
        final String nameOfInstitution = (String) userMap.get("nameOfInstitution");
        return new User(uuid, username, email, firstName, lastName, phoneNumber, imageUrl, address, socialMediaAccount, nameOfInstitution);
    }

    public static User FromMap( Map<String, Object> userMap) {
        final String username = (String) userMap.get("username");
        final String email = (String) userMap.get("email");
        final String userId = (String) userMap.get("userId");
        final String firstName = (String) userMap.get("firstName");
        final String lastName = (String) userMap.get("lastName");
        final String phoneNumber = (String) userMap.get("phoneNumber");
        final String imageUrl = (String) userMap.get("imageUrl");
        final String address = (String) userMap.get("address");
        final String socialMediaAccount = (String) userMap.get("socialMediaAccount");
        final String nameOfInstitution = (String) userMap.get("nameOfInstitution");
        return new User(userId, username, email, firstName, lastName, phoneNumber, imageUrl, address, socialMediaAccount, nameOfInstitution);
    }


    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getNameOfInstitution() {
        return nameOfInstitution;
    }


}
