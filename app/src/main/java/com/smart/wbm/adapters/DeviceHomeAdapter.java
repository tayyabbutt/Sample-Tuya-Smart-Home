package com.smart.wbm.adapters;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smart.wbm.R;
import com.smart.wbm.interfaces.OnDeviceClickListner;
import com.smart.wbm.models.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class DeviceHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ItemBean> myList = new ArrayList<>();
    Context context;
    OnDeviceClickListner listner;


    public DeviceHomeAdapter(Context context, List<ItemBean> myList, OnDeviceClickListner listner) {
        this.context = context;
        this.myList = myList;
        this.listner = listner;
    }

    public void setData(List<ItemBean> data) {
        this.myList = data;
        notifyDataSetChanged();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemLay;
        ImageView icon;
        TextView title, status;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemLay = itemView.findViewById(R.id.ll_item);
            icon = itemView.findViewById(R.id.sd_icon);
            title = itemView.findViewById(R.id.tv_title);
            status = itemView.findViewById(R.id.status);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_home_item_layout, parent, false);
        return new DeviceHomeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DeviceHomeAdapter.MyViewHolder bodyViewHolder = (DeviceHomeAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(final DeviceHomeAdapter.MyViewHolder holder, final int position) {
        if (myList.get(position) == null) {
            return;
        }

        if (!TextUtils.isEmpty(myList.get(position).getIconUrl())) {
            Uri uri = Uri.parse(myList.get(position).getIconUrl());
            //holder.icon.setImageURI(uri);
            Glide.with(context).load(uri).into(holder.icon);
        }
        holder.title.setText(myList.get(position).getTitle());
        holder.itemLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listner != null) {
                    listner.onDeviceClick(myList.get(position), holder.getAdapterPosition());
                }
            }
        });

        if (myList.get(position).getOnline()) {
            holder.status.setBackground(ContextCompat.getDrawable(context, R.drawable.green_dot));
        } else {
            holder.status.setBackground(ContextCompat.getDrawable(context, R.drawable.grey_dot));
        }
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
