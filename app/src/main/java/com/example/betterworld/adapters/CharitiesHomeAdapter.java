package com.example.betterworld.adapters;

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


public class CharitiesHomeAdapter extends RecyclerView.Adapter {
    List<Charity> charityList;

    public CharitiesHomeAdapter(List<Charity> charityList) {
        this.charityList = charityList;
    }

    static class CharityViewHolder extends RecyclerView.ViewHolder {

        public static CharityViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            CharityDataBinding  charityDataBinding =  CharityDataBinding.inflate(inflater, parent, false);
            return new CharityViewHolder(charityDataBinding, parent.getContext());
        }

        public CharityDataBinding charityDataBinding;
        public Context context;
        public CharityViewHolder(@NonNull @NotNull CharityDataBinding binding, Context context) {
            super(binding.getRoot());
            this.charityDataBinding = binding;
            this.context = context;
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
       return CharityViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ((CharityViewHolder) holder).bindTo(charityList.get(position));
    }

    @Override
    public int getItemCount() {
        return charityList.size();
    }
}
