package com.example.betterworld.models;


import com.google.type.DateTime;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Charity implements Serializable {
    final public static String DEFAULT_IMAGE = "https://firebasestorage.googleapis.com/v0/b/better-world-app.appspot.com/o/default_charity_image.png?alt=media&token=39dea2da-d450-47d8-8aad-af2977d42925";
    final String charityId;
    final String title;
    final String description;
    final String whoBenefits;
    final String imageUrl;
    final double target;
    final double donated;
    final Date dueDate;
    final Date startDate;
    final String categoryId;
    final String categoryName;
    final String userId;
    final String userName;


    public Charity(String charityId, String title, String description, String whoBenefits,
                   String imageUrl, double target, double donated, long dueDate,
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

    public static Charity fromMap(Map<String, Object> userMap) {
        String charityId = (String) userMap.get("charityId");
        String title = (String) userMap.get("title");
        String description = (String) userMap.get("description");
        String whoBenefits = (String) userMap.get("whoBenefits");
        String imageUrl = (String) userMap.get("imageUrl");
        double target = (double) userMap.get("target");
        double donated = (double) userMap.get("donated");
        long dueDate = (Long) userMap.get("dueDate");
        long startDate = (Long) userMap.get("startDate");
        String categoryId = (String) userMap.get("categoryId");
        String categoryName = (String) userMap.get("categoryName");
        String userId = (String) userMap.get("userId");
        String userName = (String) userMap.get("userName");
        return new Charity(charityId, title, description, whoBenefits, imageUrl, target, donated, dueDate, startDate, categoryId, categoryName, userId, userName);
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

    public double getTarget() {
        return target;
    }

    public double getDonated() {
        return donated;
    }




    public int getPercentToInteger(){
        return (int) Math.round((donated*100/target));
    }

    public String getPercentToString(){
        return "%" + String.valueOf(Math.round((donated*100/target)));
    }

    public String targetToString(){
        return String.valueOf(target) +"$";
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
}
