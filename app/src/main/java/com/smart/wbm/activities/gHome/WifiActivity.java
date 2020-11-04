package com.smart.wbm.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.smart.wbm.R;
import com.smart.wbm.utils.Utility;
import com.tuya.smart.android.common.utils.WiFiUtil;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.builder.ActivatorBuilder;
import com.tuya.smart.home.sdk.callback.ITuyaGetHomeListCallback;
import com.tuya.smart.sdk.api.ITuyaActivator;
import com.tuya.smart.sdk.api.ITuyaActivatorGetToken;
import com.tuya.smart.sdk.api.ITuyaSmartActivatorListener;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.enums.ActivatorModelEnum;

import java.util.List;

public class WifiActivity extends AppCompatActivity implements ITuyaSmartActivatorListener {
    KProgressHUD progress;
    EditText password;
    TextView wifiName;
    Button next;
    protected ITuyaActivator mTuyaActivator;
    String ssid, passwordStr;
    Long homeId = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_detail_layout);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        next = findViewById(R.id.next);
        password = findViewById(R.id.password);
        wifiName = findViewById(R.id.wifiName);
        ssid = WiFiUtil.getCurrentSSID(WifiActivity.this);
        wifiName.setText(ssid);
        passwordStr = password.getText().toString();
        if (getIntent().getLongExtra("homeId", 1) != 0) {
            homeId = getIntent().getLongExtra("homeId", 1);
        }

        //getTuyaHomeList();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!password.getText().toString().isEmpty()) {
                    if (homeId == null && homeId != 0 && homeId != 1) {
                        getToken(homeId);
                    } else {
                        getTuyaHomeList();
                    }

                } else {
                    Toast.makeText(WifiActivity.this, "enter password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getTuyaHomeList() {
        showProgress();
        TuyaHomeSdk.getHomeManagerInstance().queryHomeList(new ITuyaGetHomeListCallback() {
            @Override
            public void onSuccess(List<HomeBean> list) {
                if (!list.isEmpty()) {
                    HomeBean homeBean = list.get(0);
                    Long mCurrentHomeId = homeBean.getHomeId();
                    getToken(mCurrentHomeId);

                } else {
                    Toast.makeText(WifiActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    hideProgress();
                }
            }

            @Override
            public void onError(String s, String s1) {
                hideProgress();
                Toast.makeText(WifiActivity.this, s + "\n" + s1, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getToken(Long mCurrentHomeId) {
        showProgress();
        TuyaHomeSdk.getActivatorInstance().getActivatorToken(mCurrentHomeId, new ITuyaActivatorGetToken() {
            @Override
            public void onSuccess(String token) {
                //  hideProgress();
                hideProgress();
                Log.i(token, "onSuccess token is : " + token);
                mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newMultiActivator(new ActivatorBuilder()
                        .setSsid(ssid)
                        .setContext(WifiActivity.this)
                        .setPassword(password.getText().toString())
                        .setActivatorModel(ActivatorModelEnum.TY_EZ)
                        .setTimeOut(100)
                        .setToken(token)
                        .setListener(WifiActivity.this));
                showProgress();
                mTuyaActivator.start();

            }

            @Override
            public void onFailure(String s, String s1) {
                hideProgress();
                Toast.makeText(WifiActivity.this, "s " + s + "s1 " + s1, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void showProgress() {
        progress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait...")
                .setAnimationSpeed(2)
                .show();
    }

    public void hideProgress() {
        progress.dismiss();
    }

    @Override
    public void onStep(String step, Object data) {        // device found
        hideProgress();
        Toast.makeText(WifiActivity.this, "Step " + step, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActiveSuccess(DeviceBean devResp) {   //initializing device
        hideProgress();
        mTuyaActivator.stop();
        Bundle bundle = new Bundle();
        bundle.putString("devId", devResp.getDevId());
        Utility.launchActivity(WifiActivity.this, SwitchActivity.class, false, bundle);
        Toast.makeText(WifiActivity.this, "device activate " + devResp.getDevId(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String errorCode, String errorMsg) {
        hideProgress();
        Toast.makeText(WifiActivity.this, "" + errorCode + " " + errorMsg, Toast.LENGTH_SHORT).show();
    }


}
