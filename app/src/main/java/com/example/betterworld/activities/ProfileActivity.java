
package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.pm.VersionedPackage;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.betterworld.R;
import com.example.betterworld.databinding.ActivityHomeBinding;
import com.example.betterworld.databinding.ActivityProfileBinding;
import com.example.betterworld.viewmodels.HomeViewModel;
import com.example.betterworld.viewmodels.ProfileViewModel;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToEditProfileActivity;
import static com.example.betterworld.utils.Actions.goToLoginActivity;
import static com.example.betterworld.utils.Actions.gotoMainActivity;


@AndroidEntryPoint
public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding activityProfileBinding;

    @Inject
    ProfileViewModel profileViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        activityProfileBinding  =  DataBindingUtil.setContentView(this, R.layout.activity_profile);

    }

    @Override
    protected void onStart() {
        super.onStart();
        _initComponents();
    }

    private void _initComponents() {
        checkIfUserIsAuthenticatedAndPopulateProfile();
    }

    private void checkIfUserIsAuthenticatedAndPopulateProfile() {
        FirebaseUser fbUser = profileViewModel.checkIfUserIsAuthenticated();
        if (fbUser != null) {
            Toast.makeText(this, "User Is Found", Toast.LENGTH_SHORT).show();
            profileViewModel.getUserFromFirestore(fbUser.getUid(), fbUser.getEmail()).observe(this, dataOrException -> {
                if (dataOrException.data != null) {
                    activityProfileBinding.userEmail.setText(fbUser.getEmail());
                    activityProfileBinding.userName.setText(dataOrException.data.getFirstName() +" "+dataOrException.data.getLastName());
                    activityProfileBinding.userId.setText(dataOrException.data.getUserId());
                    Glide.with(ProfileActivity.this).load(dataOrException.data.getImageUrl()).into(activityProfileBinding.userThumbnail);
                    activityProfileBinding.editUser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToEditProfileActivity(ProfileActivity.this);
                        }
                    });
                    activityProfileBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ProfileActivity.this, "Signing Out.... ", Toast.LENGTH_SHORT).show();
                            profileViewModel.logOut();
                            gotoMainActivity(ProfileActivity.this);
                        }
                    });
                } else {
                    gotoMainActivity(ProfileActivity.this);
                }
            });

        } else {
            Toast.makeText(this, "\"Not Authenticated: go login page\"", Toast.LENGTH_SHORT).show();
            goToLoginActivity(this);
        }
    }
}