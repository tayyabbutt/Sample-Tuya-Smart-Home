package com.smart.wbm.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuya.smart.home.sdk.bean.HomeBean;
import com.smart.wbm.R;
import com.smart.wbm.adapters.HomeListAdapter;
import com.smart.wbm.interfaces.HomeNameClickListner;
import com.smart.wbm.interfaces.OnDialogClickListner;
import com.smart.wbm.interfaces.OnLogoutClickListner;
import com.smart.wbm.interfaces.OnRoomClick;
import com.smart.wbm.manager.SmartManager;

import java.util.List;


public class MyDialog {

    public static void showDialog(final Activity activity, String title, final OnRoomClick roomClick) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_layout);

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.dialog_bg));

        TextView titleTv = dialog.findViewById(R.id.title_tv);
        final EditText msgTv = dialog.findViewById(R.id.msg_tv);
        titleTv.setText(title);


        Button dialogButton = dialog.findViewById(R.id.ok_btn);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roomClick != null) {
                    roomClick.onRoomClickListner(msgTv.getText().toString());
                }
                Toast.makeText(activity, msgTv.getText().toString() + " added to room", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public static void showWarningDialog(final Activity activity, String title, String msg, final OnDialogClickListner dialogClickListner) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_warning);

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.dialog_bg));

        TextView titleTv = dialog.findViewById(R.id.title_tv);
        TextView msgTv = dialog.findViewById(R.id.msg_tv);
        titleTv.setText(title);
        msgTv.setText(msg);
        Button dialogButton = dialog.findViewById(R.id.ok_btn);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogClickListner != null) {
                    dialogClickListner.onDialogClick(true);
                }
                Toast.makeText(activity, "device added to room", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showLogOutDialog(final Activity activity, final OnLogoutClickListner onLogoutClickListner) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.dialog_bg));


        Button dialogButton = dialog.findViewById(R.id.ok_btn);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onLogoutClickListner != null) {
                    onLogoutClickListner.onLogoutClickListner();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public static void showHomeDialog(Context context, HomeNameClickListner onHomeClickListner) {
        List<HomeBean> list = SmartManager.getInstance().getListofTuyaHome();

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.home_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
      /*  WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = (int) (Utility.getScreenWidth(context) - Utility.convertDpToPixel(Utility.DIALOG_WIDTH_MARGIN, context));
        //  lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height =(int) (Utility.getScreenHeight(context) - Utility.convertDpToPixel(Utility.DIALOG_HEIGHT_MARGIN,context));
        window.setAttributes(lp);*/
        // dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.people_dialog_bg));

        RecyclerView mRecyclerView = dialog.findViewById(R.id.homeView);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(manager);
        TextView noHomeFound = dialog.findViewById(R.id.nohomeFound);
        if (list.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            noHomeFound.setVisibility(View.GONE);
            if (SmartManager.getInstance().getListofTuyaHome().size() <= 0) {
                list = SmartManager.getInstance().getListofTuyaHome();
            }
            HomeListAdapter adaptor = new HomeListAdapter(context, dialog, list, onHomeClickListner);
            mRecyclerView.setAdapter(adaptor);
            adaptor.notifyDataSetChanged();

        } else if (list.size() == 0) {
            mRecyclerView.setVisibility(View.GONE);
            noHomeFound.setVisibility(View.VISIBLE);
        }
        dialog.show();
    }






/*

    public static void showWarningDialog(Context context, String title, String msg, final OnDialogBtnClick listener) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = (int) (Utility.getScreenWidth(context) - Utility.convertDpToPixel(Utility.DIALOG_WIDTH_MARGIN, context));
        window.setAttributes(lp);
        TextView titleTv = dialog.findViewById(R.id.title_tv);
        TextView msgTv = dialog.findViewById(R.id.msg_tv);
        titleTv.setText(title);
        msgTv.setText(msg);
        Button okBtn = dialog.findViewById(R.id.yes_btn);
        Button noBtn = dialog.findViewById(R.id.no_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onYesBtnClick();
                }
                dialog.dismiss();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
*/


}
