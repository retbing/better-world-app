package com.example.betterworld.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.betterworld.activities.LoginActivity;
import com.example.betterworld.activities.RegisterActivity;
import com.example.betterworld.models.User;

import static com.example.betterworld.utils.Constants.USER;

public class Actions {
    private static final String TAG = "Actions";
    public static void gotoMainActivity(Activity activity, User user) {
//        Intent intent = new Intent(activity, MainActivity.class);
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra(USER, user);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void goToLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }


    public static void goToRegisterActivity(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }
}
