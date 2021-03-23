package com.example.betterworld.models;

public class Donation {

    final String donationId;
    final String userId;
    final String userName;
    final String userImageUrl;
    final String charityId;
    final float amount;
    final boolean isAnonymous;

    public Donation(String donationId, String userId, String userName, String userImageUrl, String charityId, float amount, boolean isAnonymous) {
        this.donationId = donationId;
        this.userId = userId;
        this.userName = userName;
        this.userImageUrl = userImageUrl;
        this.charityId = charityId;
        this.amount = amount;
        this.isAnonymous = isAnonymous;
    }

    public String getDonationId() {
        return donationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public String getCharityId() {
        return charityId;
    }

    public float getAmount() {
        return amount;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }
}
