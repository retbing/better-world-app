package com.example.betterworld.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.betterworld.R;

import java.util.List;

public class CategoryBottonAdapter extends RecyclerView.Adapter<CategoryBottonAdapter.MyView> {

    private List<String> list;

    public class MyView
            extends RecyclerView.ViewHolder {

        Button categoryBtn;

        public MyView(View view)
        {
            super(view);

            categoryBtn = (Button) view
                    .findViewById(R.id.categotyBtnTxt);
        }
    }

    public CategoryBottonAdapter(List<String> horizontalList)
    {
        this.list = horizontalList;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent,
                                     int viewType)
    {

        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.categorybutton,
                        parent,
                        false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder,
                                 final int position)
    {

        holder.categoryBtn.setText(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}