package com.wbm.smart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wbm.smart.R;
import com.wbm.smart.models.DummyAction;

import java.util.ArrayList;
import java.util.List;

public class ActionSccenarioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DummyAction> myList = new ArrayList<>();
    Context context;

    public ActionSccenarioAdapter(Context context, List<DummyAction> myList) {
        this.context = context;
        this.myList = myList;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView deviceName;

        public MyViewHolder(View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.actionItemText);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_action_item, parent, false);
        return new ActionSccenarioAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ActionSccenarioAdapter.MyViewHolder bodyViewHolder = (ActionSccenarioAdapter.MyViewHolder) holder;
        bindBodyComponent(bodyViewHolder, position);
    }

    private void bindBodyComponent(ActionSccenarioAdapter.MyViewHolder holder, final int position) {
        holder.deviceName.setText(myList.get(position).getAction());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}

