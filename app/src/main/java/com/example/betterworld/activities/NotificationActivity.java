package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.example.betterworld.R;
import com.example.betterworld.adapters.NotificationAdapter;
import com.example.betterworld.databinding.ActivityNotificationBinding;
import com.example.betterworld.models.Notification;
import com.example.betterworld.models.User;
import com.example.betterworld.viewmodels.NotificationViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Constants.USER;

@AndroidEntryPoint
public class NotificationActivity extends AppCompatActivity {
    @Inject
    NotificationViewModel notificationViewModel;

    private ActivityNotificationBinding activityNotificationBinding;

    private static final String TAG = "NotificationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNotificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        _initComponents();

        notificationViewModel.watchNotifications();

        notificationViewModel.notificationLiveData.observe(this, dataOrException -> {
            if(dataOrException.data != null) {
                _setNotificationListViewAdapter(dataOrException.data);
            } else {
                Log.d(TAG, "onCreate: "  + "error");
            }
        });
    }

    private void _initComponents() {
    }

    private void _setNotificationListViewAdapter(List<Notification> notifications) {
        final NotificationAdapter adapter = new NotificationAdapter(this, R.layout.notification_tile, notifications);
        activityNotificationBinding.lvNotification.setAdapter(adapter);
    }
}