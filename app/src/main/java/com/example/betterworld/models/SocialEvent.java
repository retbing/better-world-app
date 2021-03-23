package com.example.betterworld.models;

import com.google.type.DateTime;

public class SocialEvent {
    final String socialEventId;
    final String title;
    final String description;
    final String address;
    final String imageUrl;
    final int people;
    final int attendaned;
    final DateTime dueDate;
    final DateTime startDate;
    final String categoryId;
    final String categoryName;
    final String userId;
    final String userName;

    public SocialEvent(String socialEventId, String title, String description, String address, String imageUrl, int people, int attendaned, DateTime dueDate, DateTime startDate, String categoryId, String categoryName, String userId, String userName) {
        this.socialEventId = socialEventId;
        this.title = title;
        this.description = description;
        this.address = address;
        this.imageUrl = imageUrl;
        this.people = people;
        this.attendaned = attendaned;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userId = userId;
        this.userName = userName;
    }

    public String getSocialEventId() {
        return socialEventId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPeople() {
        return people;
    }

    public int getAttendaned() {
        return attendaned;
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
