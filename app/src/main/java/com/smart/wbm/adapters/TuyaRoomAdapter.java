package com.smart.wbm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.wbm.R;
import com.smart.wbm.interfaces.OnRoomClickToAddDevice;
import com.smart.wbm.models.RoomsBean;

import java.util.ArrayList;
import java.util.List;

public class TuyaRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<RoomsBean> myList = new ArrayList<>();
    Context context;
    OnRoomClickToAddDevice onRoomClickToAddDevice;

    public TuyaRoomAdapter(Context context, List<RoomsBean> myList, OnRoomClickToAddDevice onRoomClickToAddDevice) {
        this.context = context;
        this.myList = myList;
        this.onRoomClickToAddDevice = onRoomClickToAddDevice;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView room;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            room = itemView.findViewById(R.id.roomText);
            cardView = itemView.findViewById(R.id.cardClick);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item_layout, parent, false);
        return new TuyaRoomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TuyaRoomAdapter.MyViewHolder bodyViewHolder = (TuyaRoomAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(TuyaRoomAdapter.MyViewHolder holder, final int position) {
        holder.room.setText(myList.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRoomClickToAddDevice.onRoomClickListner(myList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
