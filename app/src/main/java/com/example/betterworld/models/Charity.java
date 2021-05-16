package com.example.betterworld.models;


import com.google.type.DateTime;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Charity implements Serializable {
    final static String DEFAULT_IMAGE = "https://firebasestorage.googleapis.com/v0/b/better-world-app.appspot.com/o/default_charity_image.png?alt=media&token=39dea2da-d450-47d8-8aad-af2977d42925";
    final String charityId;
    final String title;
    final String description;
    final String whoBenefits;
    final String imageUrl;
    final float target;
    final float donated;
    final Date dueDate;
    final Date startDate;
    final String categoryId;
    final String categoryName;
    final String userId;
    final String userName;


    public Charity(String charityId, String title, String description, String whoBenefits,
                   String imageUrl, float target, float donated, long dueDate,
                   long startDate, String categoryId, String categoryName, String userId, String userName) {

        this.charityId = charityId;
        this.title = title;
        this.description = description;
        this.whoBenefits = whoBenefits;
        this.imageUrl = imageUrl;
        this.target = target;
        this.donated = donated;
        this.dueDate = new Date(dueDate);
        this.startDate = new Date(startDate);
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userId = userId;
        this.userName = userName;
    }

    public Map<String, Object> toMap(
    ) {
        Map<String, Object> charityMap = new HashMap<>();
        charityMap.put("charityId", charityId);
        charityMap.put("title", title);
        charityMap.put("description", description);
        charityMap.put("whoBenefits", whoBenefits);
        charityMap.put("imageUrl", imageUrl);
        charityMap.put("target", target);
        charityMap.put("donated", donated);
        charityMap.put("dueDate", dueDate.getTime());
        charityMap.put("startDate", startDate.getTime());
        charityMap.put("categoryId", categoryId);
        charityMap.put("categoryName", categoryName);
        charityMap.put("userId", userId);
        charityMap.put("userName", userName);

        return charityMap;
    }

    @Override
    public String toString() {
        return "Charity{" +
                "charityId='" + charityId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", whoBenefits='" + whoBenefits + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", target=" + target +
                ", donated=" + donated +
                ", dueDate='" + dueDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
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

    public Date getDueDate() {
        return dueDate;
    }

    public Date getStartDate() {
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

    public static Charity charityFromMap(Map<String, Object> userMap) {
        String charityId = (String) userMap.get("charityId");
        String title = (String) userMap.get("title");
        String description = (String) userMap.get("description");
        String whoBenefits = (String) userMap.get("whoBenefits");
        String imageUrl = (String) userMap.get("imageUrl");
        float target = (float) userMap.get("target");
        float donated = (float) userMap.get("donated");
        long dueDate = (Long) userMap.get("dueDate");
        long startDate = (Long) userMap.get("startDate");
        String categoryId = (String) userMap.get("categoryId");
        String categoryName = (String) userMap.get("categoryName");
        String userId = (String) userMap.get("userId");
        String userName = (String) userMap.get("userName");
        return new Charity(charityId, title, description, whoBenefits, imageUrl, target, donated, dueDate, startDate, categoryId, categoryName, userId, userName);
    }

    public static String[] categoryNames = {"Health", "Education", "Animals", "Environment"};

    public static String[] categoryIds = {"#health", "#education", "#animals", "#environment"};
}
