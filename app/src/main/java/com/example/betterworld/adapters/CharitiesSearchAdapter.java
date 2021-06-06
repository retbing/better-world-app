package com.example.betterworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import com.example.betterworld.databinding.CharitySearchDataBinding;
import com.example.betterworld.models.Charity;

import java.util.List;

public class CharitiesSearchAdapter extends ArrayAdapter<Charity> {
    List<Charity> charityList;
    CharitySearchDataBinding charityDataBinding;
    public CharitiesSearchAdapter(@NonNull Context context, int resource, List<Charity> charityList) {
        super(context, resource, charityList);

        this.charityList = charityList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Charity charity = charityList.get(position);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        charityDataBinding = CharitySearchDataBinding.inflate(inflater);
        charityDataBinding.setCharity(charity);
        Glide.with(parent).load(charity.getImageUrl()).into(charityDataBinding.imageView);

        return charityDataBinding.getRoot();
    }
}