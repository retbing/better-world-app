package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.betterworld.R;
import com.example.betterworld.adapters.CategoryBottonAdapter;
import com.example.betterworld.adapters.CharitiesHomeAdapter;
import com.example.betterworld.databinding.ActivityHomeBinding;
import com.example.betterworld.models.Charity;
import com.example.betterworld.viewmodels.CharityViewModel;
import com.example.betterworld.viewmodels.HomeViewModel;
import com.example.betterworld.viewmodels.NotificationViewModel;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToLoginActivity;
import static com.example.betterworld.utils.Actions.goToCharityStartActivity;
import static com.example.betterworld.utils.Actions.gotoNotificationActivity;
import static com.example.betterworld.utils.Actions.gotoProfileActivity;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding activityHomeBinding;

    @Inject
    CharityViewModel charityViewModel;

    @Inject
    HomeViewModel homeViewModel;
    @Inject
    NotificationViewModel notificationModel;

    RecyclerView categoryBtnRecyclerView;

    // Array list for recycler view data source
    ArrayList<String> source;

    // Layout Manager
    RecyclerView.LayoutManager recyclerViewLayoutManagerBtn, recyclerViewLayoutManagerCard;

    // adapter class object
    CategoryBottonAdapter adapter;

    // Linear Layout Manager
    LinearLayoutManager horizontalLayoutBtn , horizontalLayoutCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        _initComponents();


    }

    private void _initComponents() {
        FirebaseUser fbUser = homeViewModel.checkIfUserIsAuthenticated();
        if (fbUser != null) {
            homeViewModel.getUserFromFirestore(fbUser.getUid(), fbUser.getEmail()).observe(this, dataOrException -> {
                if (dataOrException.data != null) {
                    activityHomeBinding.tvUsername.setText(dataOrException.data.getUsername());
                } else {
                    goToLoginActivity(this);
                }
            });
            _setupRecyclerView();
            charityViewModel.watchCharities("").observe(this, dataOrExp -> {
                if (dataOrExp.data != null) {
                    _setCharityAdapter(dataOrExp.data);
                }
            });

//            activityHomeBinding.notificationSize.setText(notificationModel.notificationSize());
            activityHomeBinding.notificationIcon.setOnClickListener(view->{
                gotoNotificationActivity(this);
            });

            activityHomeBinding.btnBlur.setOnClickListener(view -> {
                goToCharityStartActivity(this);
            });
            activityHomeBinding.btnProfile.setOnClickListener(view -> {
                gotoProfileActivity(this);
            });

        } else {
            Toast.makeText(this, "\"Not Authenticated: go login page\"", Toast.LENGTH_SHORT).show();
            goToLoginActivity(this);
        }


    }


    private void _setupRecyclerView() {
        source = new ArrayList<>();
        source.add("Education");
        source.add("Health");
        source.add("Animal");
        source.add("Environment");

        recyclerViewLayoutManagerBtn
                = new LinearLayoutManager(
                getApplicationContext());
        recyclerViewLayoutManagerCard
                = new LinearLayoutManager(
                getApplicationContext());

        horizontalLayoutBtn
                = new LinearLayoutManager(
                HomeActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);

        horizontalLayoutCard
                = new LinearLayoutManager(
                HomeActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);

         activityHomeBinding.categoryBtnRecyclerView.setLayoutManager(recyclerViewLayoutManagerBtn);
        activityHomeBinding.categoryBtnRecyclerView.setLayoutManager(horizontalLayoutBtn);

        activityHomeBinding.rvCharityCard.setLayoutManager(recyclerViewLayoutManagerCard);
        activityHomeBinding.rvCharityCard.setLayoutManager(horizontalLayoutCard);



        adapter = new CategoryBottonAdapter(source);

        activityHomeBinding.categoryBtnRecyclerView.setAdapter(adapter);

    }

    private void _setCharityAdapter(List<Charity> charityList) {
        final CharitiesHomeAdapter adapter = new CharitiesHomeAdapter(charityList, this);
        activityHomeBinding.rvCharityCard.setAdapter(adapter);
    }
}