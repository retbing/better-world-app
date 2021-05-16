package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.betterworld.R;
import com.example.betterworld.adapters.CategoryBottonAdapter;
import com.example.betterworld.databinding.ActivityHomeBinding;
import com.example.betterworld.viewmodels.HomeViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.betterworld.utils.Actions.goToPaymentActivity;


@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding activityHomeBinding;
    RecyclerView categoryBtnRecyclerView;

    // Array list for recycler view data source
    ArrayList<String> source;

    // Layout Manager
    RecyclerView.LayoutManager RecyclerViewLayoutManager;

    // adapter class object
    CategoryBottonAdapter adapter;

    // Linear Layout Manager
    LinearLayoutManager HorizontalLayout;

    View ChildView;
    int RecyclerViewItemPosition;

    @Inject
    HomeViewModel homeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        _initComponents();
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
        RecyclerViewLayoutManager
                = new LinearLayoutManager(
                getApplicationContext());
        activityHomeBinding.categoryBtnRecyclerView.setLayoutManager(RecyclerViewLayoutManager);

        adapter = new CategoryBottonAdapter(source);

        HorizontalLayout
                = new LinearLayoutManager(
                HomeActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);
        activityHomeBinding.categoryBtnRecyclerView.setLayoutManager(HorizontalLayout);

        activityHomeBinding.categoryBtnRecyclerView.setAdapter(adapter);
    }
}