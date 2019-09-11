package com.wbm.smart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuya.smart.sdk.bean.DeviceBean;
import com.wbm.smart.R;

import java.util.ArrayList;
import java.util.List;

public class DAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DeviceBean> myList = new ArrayList<>();
    Context context;


    public DAdapter(Context context, List<DeviceBean> myList) {
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
        return new DAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DAdapter.MyViewHolder bodyViewHolder = (DAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(DAdapter.MyViewHolder holder, final int position) {
        holder.deviceName.setText(myList.get(position).getDevId());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
