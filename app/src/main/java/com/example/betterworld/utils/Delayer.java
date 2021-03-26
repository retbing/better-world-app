package com.example.betterworld.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import java.util.concurrent.Executor;

public class Delayer {
    public interface DelayCallback {
        void callback();
    }

    public static void delay(int millis, final DelayCallback delayCallback) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.callback();
            }
        }, millis);
    }
}

