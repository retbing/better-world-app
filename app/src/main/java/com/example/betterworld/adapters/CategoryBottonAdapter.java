package com.example.betterworld.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.betterworld.R;
import com.example.betterworld.databinding.CategoryBottonDataBinding;
import com.example.betterworld.databinding.CharityDataBinding;
import com.example.betterworld.models.Charity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.betterworld.utils.Actions.goToCharityDetailsActivity;

public class CategoryBottonAdapter extends  RecyclerView.Adapter {

        ArrayList<String> source;
        Activity activity;
        int RecyclerViewItemPosition = 0;

public CategoryBottonAdapter( Activity activity) {
        source = new ArrayList<>();
        source.add("Education");
        source.add("Health");
        source.add("Animal");
        source.add("Environment");
        this.activity = activity;
        }

static class CategoryBottonViewHolder extends RecyclerView.ViewHolder {

    public static CategoryBottonAdapter.CategoryBottonViewHolder create(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        CategoryBottonDataBinding categoryBottonDataBinding =  CategoryBottonDataBinding.inflate(inflater, parent, false);
        return new CategoryBottonAdapter.CategoryBottonViewHolder(categoryBottonDataBinding, parent.getContext(), activity);
    }

    public CategoryBottonDataBinding categoryBottonDataBinding;
    public Context context;
    public Activity activity;
    public CategoryBottonViewHolder(@NonNull @NotNull CategoryBottonDataBinding binding, Context context, Activity activity) {
        super(binding.getRoot());
        this.categoryBottonDataBinding = binding;
        this.context = context;
        this.activity = activity;
    }

    public void bindTo() {

    }
}

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return CategoryBottonAdapter.CategoryBottonViewHolder.create(LayoutInflater.from(parent.getContext()), parent, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ((CategoryBottonViewHolder) holder).categoryBottonDataBinding.categotyBtnTxt.setText(source.get(position));
    }

    @Override
    public int getItemCount() {
        return source.size();
    }
}
