package com.example.betterworld.models;


import com.google.firebase.Timestamp;

public class Notification {

    final String notificationId;
    final Timestamp createdAt;
    final Timestamp updatedAt;
    final boolean seen;
    final String type;
    final float donationAmount;
    final String userImageUrl;
    final String charityId;
    final String charityName;

    public Notification(String notificationId, Timestamp createdAt, Timestamp updatedAt, boolean seen, String type, float donationAmount, String userImageUrl, String charityId, String charityName) {
        this.notificationId = notificationId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.seen = seen;
        this.type = type;
        this.donationAmount = donationAmount;
        this.userImageUrl = userImageUrl;
        this.charityId = charityId;
        this.charityName = charityName;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public boolean isSeen() {
        return seen;
    }

    public String getType() {
        return type;
    }

    public float getDonationAmount() {
        return donationAmount;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public String getCharityId() {
        return charityId;
    }

    public String getCharityName() {
        return charityName;
    }
}
