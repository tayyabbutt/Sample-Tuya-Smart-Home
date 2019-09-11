package com.wbm.smart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wbm.smart.R;
import com.wbm.smart.models.DummyData;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DummyData> myList = new ArrayList<>();
    Context context;


    public DeviceAdapter(Context context, List<DummyData> myList) {
        this.context = context;
        this.myList = myList;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView deviceImage;
        TextView deviceName;

        public MyViewHolder(View itemView) {
            super(itemView);
            deviceImage = itemView.findViewById(R.id.deviceImage);
            deviceName = itemView.findViewById(R.id.deviceName);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_adapter_item, parent, false);
        return new DeviceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DeviceAdapter.MyViewHolder bodyViewHolder = (DeviceAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(DeviceAdapter.MyViewHolder holder, final int position) {
        holder.deviceName.setText(myList.get(position).getName());

        Glide.with(context)
                .load(myList.get(position).getImage()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_image_loading_placeholder))
                .into(holder.deviceImage);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
