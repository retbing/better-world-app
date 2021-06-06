package com.example.betterworld.models;


import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.betterworld.R;
import com.google.firebase.Timestamp;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class Notification {

    public static final String DONATION = "donation";
    public static final String ACHIEVEMENT = "achievement";
    public static final String REMINDER = "reminder";

    final String notificationId;
    final String charityId;
    final String type;
    final String content;
    final Date createdAt;
    final boolean seen;

    public Notification(String notificationId, String charityId, String content, String type, long createdAt, boolean seen) {
        this.notificationId = notificationId;
        this.createdAt = new Date(createdAt);
        this.seen = seen;
        this.type = type;
        this.charityId = charityId;
        this.content = content;
    }

    public static Notification fromMap(Map<String, Object> map) {
        final String notificationId = (String) map.get("notificationId");
        final String charityId = (String) map.get("charityId");
        long createdAt =  (long) map.get("createdAt");
        final boolean seen = (Boolean) map.get("seen");
        final String type = (String) map.get("type");
        final String content = (String) map.get("content");

        return new Notification(notificationId, charityId, content, type, createdAt, seen);
    }


    public int getNotificationIcon() {
        if (type.equals(DONATION)) {
            return R.drawable.ic_icon_material_attach_money;
        } else if (type.equals(ACHIEVEMENT)) {
            return R.drawable.ic_icon_material_check_circle;
        } else if (type.equals(REMINDER)) {
            return R.drawable.ic_icon_material_date_range;
        } else {
            return R.drawable.ic_notification;
        }
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat();

        return sdf.format(createdAt);
    }

    public String getCharityId() {
        return charityId;
    }

    public String getContent() {
        return content;
    }


    public Map<String, Object> toMap(
    ) {
        Map<String, Object> charityMap = new HashMap<>();
        charityMap.put("content", content);
        charityMap.put("type", type);
        charityMap.put("seen", seen);
        charityMap.put("createdAt", createdAt.getTime());
        charityMap.put("charityId", charityId);
        charityMap.put("notificationId", notificationId);

        return charityMap;
    }
}
