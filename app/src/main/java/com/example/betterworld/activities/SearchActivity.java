package com.example.betterworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.betterworld.R;
import com.example.betterworld.adapters.CharitiesSearchAdapter;
import com.example.betterworld.adapters.NotificationAdapter;
import com.example.betterworld.databinding.ActivitySearchBinding;
import com.example.betterworld.models.Charity;
import com.example.betterworld.models.DataOrException;
import com.example.betterworld.models.Notification;
import com.example.betterworld.viewmodels.CharityViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    @Inject
    CharityViewModel charityViewModel;

    ActivitySearchBinding activitySearchBinding;

    MutableLiveData<DataOrException<List<Charity>, Exception>> charityListLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        activitySearchBinding.etCharitySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                charityListLiveData = charityViewModel.watchCharitiesByTitle(charSequence.toString());
                charityListLiveData.observe(SearchActivity.this, dataOrException -> {
                    if (dataOrException.data != null) {
                        _setCharityListViewAdapter(dataOrException.data);
                    } else {
                        Log.d(TAG, "onCreate: " + "error");
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        charityListLiveData = charityViewModel.watchCharitiesByTitle("");

        charityListLiveData.observe(this, dataOrException -> {
            if (dataOrException.data != null) {
                _setCharityListViewAdapter(dataOrException.data);
            } else {
                Log.d(TAG, "onCreate: " + "error");
            }
        });


    }

    private void _setCharityListViewAdapter(List<Charity> charityList) {
        final CharitiesSearchAdapter adapter = new CharitiesSearchAdapter(this, R.layout.search_page_charity_card, charityList);
        activitySearchBinding.lvCharity.setAdapter(adapter);
    }
}