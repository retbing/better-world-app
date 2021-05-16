package com.example.betterworld.utils;

import android.net.Uri;
import android.util.Log;

import java.util.UUID;

import static com.example.betterworld.utils.Constants.TAG;

public class HelperClass {

    public static void logErrorMessage(String errorMessage) {
        Log.d(TAG, errorMessage);
    }

    public static String createUniqueImageName(Uri uri) {
        final String path = uri.getPath();
        final String ext = path.substring(path.lastIndexOf("."));
        final String name = UUID.randomUUID().toString();
        return name + ext;
    }
}
