package com.wbm.smart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wbm.smart.R;
import com.wbm.smart.models.Room;

import java.util.ArrayList;
import java.util.List;

public class DashboardRoomsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Room> myList = new ArrayList<>();
    Context context;


    public DashboardRoomsAdapter(Context context, List<Room> myList) {
        this.context = context;
        this.myList = myList;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView room;
        CardView parent;

        public MyViewHolder(View itemView) {
            super(itemView);
            room = itemView.findViewById(R.id.roomName);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_dashboard_layout, parent, false);
        return new DashboardRoomsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DashboardRoomsAdapter.MyViewHolder bodyViewHolder = (DashboardRoomsAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(DashboardRoomsAdapter.MyViewHolder holder, final int position) {
        holder.room.setText(myList.get(position).getName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, myList.get(position).getName() + " clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
