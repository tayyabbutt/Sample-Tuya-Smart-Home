package com.smart.wbm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuya.smart.home.sdk.bean.HomeBean;
import com.smart.wbm.R;

import java.util.ArrayList;
import java.util.List;

public class TuyaHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<HomeBean> myList = new ArrayList<>();
    Context context;


    public TuyaHomeAdapter(Context context, List<HomeBean> myList) {
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
        return new TuyaHomeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TuyaHomeAdapter.MyViewHolder bodyViewHolder = (TuyaHomeAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(TuyaHomeAdapter.MyViewHolder holder, final int position) {
        holder.room.setText(myList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
