package com.example.betterworld.models;

import java.util.HashMap;
import java.util.Map;

public class Donation {

    final String donationId;
    final String userId;
    final String userName;
    final String userImageUrl;
    final String charityId;
    final double amount;
    final boolean isAnonymous;

    public Donation(String donationId, String userId, String userName, String userImageUrl, String charityId, double amount, boolean isAnonymous) {
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

    public double getAmount() {
        return amount;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public Map<String, Object> toMap(
    ) {
        Map<String, Object> donationMap = new HashMap<>();
        donationMap.put("donationId", donationId);
        donationMap.put("userId", userId);
        donationMap.put("userName", userName);
        donationMap.put("amount", amount);
        donationMap.put("charityId", charityId);
        donationMap.put("isAnonymous", isAnonymous);

        return donationMap;
    }
}
