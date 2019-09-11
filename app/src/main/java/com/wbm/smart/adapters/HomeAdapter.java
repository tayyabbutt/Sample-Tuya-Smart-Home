package com.wbm.smart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wbm.smart.R;
import com.wbm.smart.models.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<HomeModel> myList = new ArrayList<>();
    Context context;


    public HomeAdapter(Context context, List<HomeModel> myList) {
        this.context = context;
        this.myList = myList;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView room;

        public MyViewHolder(View itemView) {
            super(itemView);
            room = itemView.findViewById(R.id.roomText);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_items, parent, false);
        return new HomeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeAdapter.MyViewHolder bodyViewHolder = (HomeAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(HomeAdapter.MyViewHolder holder, final int position) {
        holder.room.setText(myList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
