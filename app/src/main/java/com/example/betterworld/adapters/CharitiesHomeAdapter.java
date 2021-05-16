package com.example.betterworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betterworld.R;
import com.example.betterworld.databinding.NotificationDataBinding;
import com.example.betterworld.models.Charity;
import com.example.betterworld.models.Notification;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//
//public class CharitiesHomeAdapter extends RecyclerView.Adapter {
////    List<Charity> charityList;
////
////    CharitiesHomeAdapter(List<Charity> charityList) {
////        this.charityList = charityList;
////    }
////
////    static class CharityViewHolder extends RecyclerView.ViewHolder {
////
////        public static CharityViewHolder create(LayoutInflater inflater, ViewGroup parent) {
////            CharityDataBinding binding = CharityDataBinding.inflate(inflater, parent, false);
////            return new CharityViewHolder(binding);
////        }
////
////        public CharityDataBinding binding;
////
////        public CharityViewHolder(@NonNull @NotNull CharityDataBinding binding) {
////            super(binding.getRoot());
////            this.binding = binding;
////        }
////
////        public void bindTo(Charity charity) {
////            binding.setCharity(charity);
////            binding.executePendingBindings();
////        }
////    }
////
////    @NonNull
////    @NotNull
////    @Override
////    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
////       return CharityViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
////        ((CharityViewHolder) holder).bindTo(charityList.get(position));
////    }
////
////    @Override
////    public int getItemCount() {
////        return 0;
////    }
//}
