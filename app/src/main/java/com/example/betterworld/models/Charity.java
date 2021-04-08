package com.example.betterworld.models;


import com.google.type.DateTime;

import java.util.HashMap;
import java.util.Map;

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

    public Charity(String charityId, String title, String description, String whoBenefits,
                   String imageUrl, float target, float donated, DateTime dueDate,
                   DateTime startDate, String categoryId, String categoryName, String userId, String userName)
    {
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

    public static Map<String,Object> charityToMap(Charity charity
    ) {
        Map<String,Object> charityMap = new HashMap<>();
          charityMap.put("charityId",charity.getCharityId());
         charityMap.put("title",charity.getTitle());
        charityMap.put("description",charity.getDescription());
        charityMap.put("whoBenefits",charity.getWhoBenefits());
         charityMap.put("imageUrl",charity.getImageUrl());
        charityMap.put("target",charity.getTarget());
         charityMap.put("donated",charity.getDonated());
        charityMap.put("dueDate",charity.getDueDate());
         charityMap.put("startDate",charity.getStartDate());
          charityMap.put("categoryId",charity.getCategoryId());
         charityMap.put("categoryName",charity.getCategoryName());
       charityMap.put("userId",charity.getUserId());
        charityMap.put("userName",charity.getUserName());

        return  charityMap;
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

    public static Charity charityFromMap( Map<String, Object> userMap) {

        String  charityId = (String) userMap.get("charityId");
        String  title = (String) userMap.get("title");
        String  description = (String) userMap.get("description");
        String  whoBenefits = (String) userMap.get("whoBenefits");
        String  imageUrl = (String) userMap.get("imageUrl");
        float target = (float) userMap.get("target");
        float donated = (float) userMap.get("donated");
        DateTime dueDate = (DateTime) userMap.get("dueDate");
        DateTime startDate = (DateTime) userMap.get("startDate");
        String  categoryId = (String) userMap.get("categoryId");
        String  categoryName = (String) userMap.get("categoryName");
        String  userId = (String) userMap.get("userId");
        String  userName = (String) userMap.get("userName");
        return new Charity(charityId,title,description,whoBenefits,imageUrl,target,donated,dueDate,startDate,categoryId,categoryName,userId,userName);
    }
}
