package com.example.betterworld.models;

public class Attendance {
    final String attendanceId;
    final String socialEventId;
    final String userId;
    final String userName;
    final String userImageUrl;

    public Attendance(String attendanceId, String socialEventId, String userId, String userName, String userImageUrl) {
        this.attendanceId = attendanceId;
        this.socialEventId = socialEventId;
        this.userId = userId;
        this.userName = userName;
        this.userImageUrl = userImageUrl;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public String getSocialEventId() {
        return socialEventId;
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

}
