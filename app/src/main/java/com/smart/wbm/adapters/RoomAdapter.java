package com.smart.wbm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.wbm.R;
import com.smart.wbm.models.RoomModel;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<RoomModel> myList = new ArrayList<>();
    Context context;


    public RoomAdapter(Context context, List<RoomModel> myList) {
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
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_items, parent, false);
        return new RoomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RoomAdapter.MyViewHolder bodyViewHolder = (RoomAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(RoomAdapter.MyViewHolder holder, final int position) {
        holder.room.setText(myList.get(position).getRoomName());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
