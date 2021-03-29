package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityNotificationBinding;
import com.example.betterworld.models.User;

import static com.example.betterworld.utils.Constants.USER;

public class NotificationActivity extends AppCompatActivity {
    private ActivityNotificationBinding activityNotificationBinding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNotificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        _initComponents();
    }

    private void _initComponents() {
        user = (User) getIntent().getSerializableExtra(USER);
        activityNotificationBinding.textView.setText(user.getUsername());
    }
}