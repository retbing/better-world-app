package com.example.betterworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.betterworld.R;
import com.example.betterworld.databinding.NotificationDataBinding;
import com.example.betterworld.models.Charity;
import com.example.betterworld.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class CagetoryFilterAdapter extends ArrayAdapter<String> {
    String[] categories;
    public CagetoryFilterAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        categories = Charity.categoryNames;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String categoryName = categories[position];
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cateogry_filter_button, parent);

       Button button  = view.findViewById(R.id.btn_category_filter);
       button.setText(categoryName);
       return button;
    }

    @Override
    public int getCount() {
        return categories.length;
    }
}
