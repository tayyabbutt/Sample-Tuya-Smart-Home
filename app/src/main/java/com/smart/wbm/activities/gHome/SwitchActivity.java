package com.smart.wbm.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.smart.wbm.R;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;

import java.util.HashMap;

public class SwitchActivity extends AppCompatActivity {
    ImageView iv_switch;
    String devId;
    ITuyaDevice mDevice;
    boolean isClick = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_layout);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        iv_switch = findViewById(R.id.iv_switch);

        if (getIntent().getStringExtra("devId") != null) {
            devId = getIntent().getStringExtra("devId");
        }
        mDevice = TuyaHomeSdk.newDeviceInstance(devId);

        iv_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick == false) {
                    isClick = true;
                    Glide.with(SwitchActivity.this).load(ContextCompat.getDrawable(SwitchActivity.this, R.drawable.switch_on)).into(iv_switch);
                    HashMap<String, Object> dps = new HashMap<>();
                    dps.put("1", true);
                    mDevice.publishDps(JSONObject.toJSONString(dps), new IResultCallback() {
                        @Override
                        public void onError(String code, String error) {
                            Toast.makeText(SwitchActivity.this, "error code " + code + " error " + error, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess() {
                            Toast.makeText(SwitchActivity.this, "success light off", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (isClick == true) {
                    isClick = false;
                    Glide.with(SwitchActivity.this).load(ContextCompat.getDrawable(SwitchActivity.this, R.drawable.switch_off)).into(iv_switch);
                    HashMap<String, Object> dps = new HashMap<>();
                    dps.put("1", false);
                    mDevice.publishDps(JSONObject.toJSONString(dps), new IResultCallback() {
                        @Override
                        public void onError(String code, String error) {
                            Toast.makeText(SwitchActivity.this, "error code " + code + " error " + error, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess() {
                            Toast.makeText(SwitchActivity.this, "success light off", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}
