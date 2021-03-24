package com.example.betterworld.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.betterworld.models.User;
import static com.example.betterworld.utils.Constants.USER;

public class Actions {
    private static final String TAG = "Actions";
    public static void gotoMainActivity(Activity activity, User user) {
//        Intent intent = new Intent(activity, MainActivity.class);
//        intent.putExtra(USER, user);
//        activity.startActivity(intent);
//        activity.finish();
    }

    public static void gotoAuthActivity(Activity activity) {
//        Intent intent = new Intent(activity, AuthActivity.class);
//        activity.startActivity(intent);
//        activity.finish();
        Log.d(TAG, "gotoAuthActivity: Hey");
    }
}
