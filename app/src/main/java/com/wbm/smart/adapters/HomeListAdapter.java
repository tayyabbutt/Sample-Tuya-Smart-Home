package com.wbm.smart.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tuya.smart.home.sdk.bean.HomeBean;
import com.wbm.smart.R;
import com.wbm.smart.interfaces.HomeNameClickListner;
import com.wbm.smart.models.HomeModel;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.viewholder> {
    Context context;
    List<HomeBean> list;
    HomeNameClickListner listner;
    Dialog dialog;

    public HomeListAdapter(Context context, Dialog dialog, List<HomeBean> list, HomeNameClickListner listner) {
        this.context = context;
        this.list = list;
        this.dialog = dialog;
        this.listner = listner;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_dialog_item_name, viewGroup, false);
        viewholder vh = new viewholder(mView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {

        holder.homeName.setText(list.get(position).getName());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.homeListner(list.get(position));
                dialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewholder extends RecyclerView.ViewHolder {

        TextView homeName;
        LinearLayout parent;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            homeName = itemView.findViewById(R.id.homeName);
            parent = itemView.findViewById(R.id.parent);

        }
    }


}
