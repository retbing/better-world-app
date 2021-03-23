package com.example.betterworld.models;

import com.google.firebase.Timestamp;

public class UserReport {

    final String userReportId;
    final String type;
    final String message;
    final Timestamp createdAt;
    final String userId;
    final String username;
    final String charityId;
    final String reportedUserId;

    public UserReport(String userReportId, String type, String message, Timestamp createdAt, String userId, String username, String charityId, String reportedUserId) {
        this.userReportId = userReportId;
        this.type = type;
        this.message = message;
        this.createdAt = createdAt;
        this.userId = userId;
        this.username = username;
        this.charityId = charityId;
        this.reportedUserId = reportedUserId;
    }

    public String getUserReportId() {
        return userReportId;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getCharityId() {
        return charityId;
    }

    public String getReportedUserId() {
        return reportedUserId;
    }
}
