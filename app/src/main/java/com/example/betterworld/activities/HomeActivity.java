package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.example.betterworld.R;
import com.example.betterworld.adapters.CategoryBottonAdapter;
import com.example.betterworld.adapters.CharitiesHomeAdapter;
import com.example.betterworld.adapters.NotificationAdapter;
import com.example.betterworld.databinding.ActivityHomeBinding;
import com.example.betterworld.models.Charity;
import com.example.betterworld.models.Notification;
import com.example.betterworld.viewmodels.CharityViewModel;
import com.example.betterworld.viewmodels.HomeViewModel;
import com.example.betterworld.utils.Actions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToPaymentActivity;
import static com.example.betterworld.utils.Actions.goToCharityStartActivity;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding activityHomeBinding;

    @Inject
    CharityViewModel charityViewModel;

    RecyclerView categoryBtnRecyclerView;

    // Array list for recycler view data source
    ArrayList<String> source;

    // Layout Manager
    RecyclerView.LayoutManager recyclerViewLayoutManagerBtn, recyclerViewLayoutManagerCard;

    // adapter class object
    CategoryBottonAdapter adapter;

    // Linear Layout Manager
    LinearLayoutManager horizontalLayoutBtn , horizontalLayoutCard;

    View ChildView;
    int RecyclerViewItemPosition;

    @Inject
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        _initComponents();

        charityViewModel.watchCharities("").observe(this, dataOrExp -> {
            if (dataOrExp.data != null) {
                _setCharityAdapter(dataOrExp.data);
            }
        });
    }

    private void _initComponents() {
        _setupRecyclerView();
        activityHomeBinding.btnDonate.setOnClickListener(view->goToPaymentActivity(this));
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

        activityHomeBinding.btnBlur.setOnClickListener(view -> {
            goToCharityStartActivity(this);
        });
    }

    private void _setCharityAdapter(List<Charity> charityList) {
        final CharitiesHomeAdapter adapter = new CharitiesHomeAdapter(charityList, this);
        activityHomeBinding.rvCharityCard.setAdapter(adapter);
    }
}