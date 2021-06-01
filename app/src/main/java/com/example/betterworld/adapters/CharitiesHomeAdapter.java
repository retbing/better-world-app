package com.example.betterworld.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.betterworld.databinding.CharityDataBinding;
import com.example.betterworld.models.Charity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.example.betterworld.utils.Actions.goToCharityDetailsActivity;


public class CharitiesHomeAdapter extends RecyclerView.Adapter {
    
    List<Charity> charityList;
    Activity activity;
    int RecyclerViewItemPosition = 0;

    public CharitiesHomeAdapter(List<Charity> charityList, Activity activity) {
        this.charityList = charityList;
        this.activity = activity;
    }

    static class CharityViewHolder extends RecyclerView.ViewHolder {

        public static CharityViewHolder create(LayoutInflater inflater, ViewGroup parent, Activity activity) {
            CharityDataBinding  charityDataBinding =  CharityDataBinding.inflate(inflater, parent, false);
            return new CharityViewHolder(charityDataBinding, parent.getContext(), activity);
        }

        public CharityDataBinding charityDataBinding;
        public Context context;
        public Activity activity;
        public CharityViewHolder(@NonNull @NotNull CharityDataBinding binding, Context context, Activity activity) {
            super(binding.getRoot());
            this.charityDataBinding = binding;
            this.context = context;
            this.activity = activity;
        }

        public void bindTo(Charity charity) {
            charityDataBinding.setCharity(charity);
            Glide.with(context).load(charity.getImageUrl()).into(charityDataBinding.imageView);

        }
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       return CharityViewHolder.create(LayoutInflater.from(parent.getContext()), parent, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ((CharityViewHolder) holder).charityDataBinding.getRoot().setOnClickListener(view -> goToCharityDetailsActivity(activity,charityList.get(position).getCharityId()));
        ((CharityViewHolder) holder).bindTo(charityList.get(position));
    }

    @Override
    public int getItemCount() {
        return charityList.size();
    }
}
