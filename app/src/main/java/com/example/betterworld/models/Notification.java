package com.example.betterworld.models;


import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.betterworld.R;
import com.google.firebase.Timestamp;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Date;

public class Notification {

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
        final long createdAt = (Long) map.get("createdAt");
        final boolean seen = (Boolean) map.get("seen");
        final String type = (String) map.get("type");
        final String content = (String) map.get("content");

        return new Notification(notificationId, charityId, content, type, createdAt, seen);
    }

    public String getNotificationId() {
        return notificationId;
    }


    public boolean isSeen() {
        return seen;
    }


    public int getNotificationIcon() {
        if (type.equals("donation")) {
            return R.drawable.ic_icon_material_attach_money;
        } else if (type.equals("achievement")) {
            return R.drawable.ic_icon_material_check_circle;
        } else {
            return R.drawable.ic_icon_material_date_range;
        }
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, YYYY");

        return sdf.format(createdAt);
    }

    public String getCharityId() {
        return charityId;
    }

    public String getContent() {
        return content;
    }

}
