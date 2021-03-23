package com.example.betterworld.models;

import com.google.type.DateTime;

public class Charity {

    final String charityId;
    final String title;
    final String description;
    final String whoBenefits;
    final String imageUrl;
    final float target;
    final float donated;
    final DateTime dueDate;
    final DateTime startDate;
    final String categoryId;
    final String categoryName;
    final String userId;
    final String userName;

    public Charity(String charityId, String title, String description, String whoBenefits, String imageUrl, float target, float donated, DateTime dueDate, DateTime startDate, String categoryId, String categoryName, String userId, String userName) {
        this.charityId = charityId;
        this.title = title;
        this.description = description;
        this.whoBenefits = whoBenefits;
        this.imageUrl = imageUrl;
        this.target = target;
        this.donated = donated;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userId = userId;
        this.userName = userName;
    }

    public String getCharityId() {
        return charityId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getWhoBenefits() {
        return whoBenefits;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getTarget() {
        return target;
    }

    public float getDonated() {
        return donated;
    }

    public DateTime getDueDate() {
        return dueDate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
