package com.smart.wbm.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.GroupBean;
import com.smart.wbm.R;
import com.smart.wbm.adapters.DeviceHomeAdapter;
import com.smart.wbm.interfaces.OnDeviceClickListner;
import com.smart.wbm.interfaces.OnDialogClickListner;
import com.smart.wbm.models.ItemBean;
import com.smart.wbm.models.RoomsBean;
import com.smart.wbm.utils.MyDialog;
import com.smart.wbm.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class AddDeviceToRoomActivity extends AppCompatActivity {

    KProgressHUD progress;
    RecyclerView deviceRecyclerView;
    RoomsBean roomsBean;
    Long homeId;
    DeviceHomeAdapter adapter;
    TextView search;
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_device_room_activity);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        deviceRecyclerView = findViewById(R.id.deviceRecyclerView);
        search = findViewById(R.id.search);
        back = findViewById(R.id.back);
        deviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        deviceRecyclerView.setHasFixedSize(true);
        if (getIntent().getSerializableExtra("roomObject") != null) {
            roomsBean = (RoomsBean) getIntent().getSerializableExtra("roomObject");
        }

        if (getIntent().getLongExtra("homeId", 1) != 0) {
            homeId = (Long) getIntent().getLongExtra("homeId", 1);
        }

        if (homeId != 0 && roomsBean != null) {
            getTuyaCurrentHomeDetail();
        } else {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
        }


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(AddDeviceToRoomActivity.this, WifiActivity.class, false, null);
            }
        });

    }

    private void getTuyaCurrentHomeDetail() {
        showProgress();
        TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(new ITuyaHomeResultCallback() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                hideProgress();
                List<ItemBean> beans = new ArrayList<>();
                for (GroupBean groupBean : homeBean.getGroupList()) {
                    beans.add(getItemBeanFromGroup(groupBean));
                }
                for (DeviceBean deviceBean : homeBean.getDeviceList()) {
                    beans.add(getItemBeanFromDevice(deviceBean));
                }
                if (beans.size() > 0) {
                    adapter = new DeviceHomeAdapter(AddDeviceToRoomActivity.this, beans, new OnDeviceClickListner() {
                        @Override
                        public void onDeviceClick(final ItemBean bean, int position) {
                            MyDialog.showWarningDialog(AddDeviceToRoomActivity.this, "Add Device", "are you sure to add " + bean.getTitle() + " to room", new OnDialogClickListner() {
                                @Override
                                public void onDialogClick(Boolean b) {
                                    if (b == true) {
                                        TuyaHomeSdk.newRoomInstance(roomsBean.getRoomId()).addDevice(bean.getDevId(), new IResultCallback() {
                                            @Override
                                            public void onError(String code, String error) {
                                                Toast.makeText(AddDeviceToRoomActivity.this, "code " + code + " error " + error, Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onSuccess() {
                                                finish();
                                                Toast.makeText(AddDeviceToRoomActivity.this, "device added", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });
                    deviceRecyclerView.setAdapter(adapter);
                    //   adapter.setData(beans);
                } else {
                    Toast.makeText(AddDeviceToRoomActivity.this, "No device found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String s, String s1) {
                hideProgress();
                Toast.makeText(AddDeviceToRoomActivity.this, s + "\n" + s1, Toast.LENGTH_LONG).show();
            }
        });
    }

    public ItemBean getItemBeanFromDevice(DeviceBean deviceBean) {
        ItemBean itemBean = new ItemBean();
        itemBean.setDevId(deviceBean.getDevId());
        itemBean.setIconUrl(deviceBean.getIconUrl());
        itemBean.setTitle(deviceBean.getName());
        itemBean.setOnline(deviceBean.getIsOnline());
        return itemBean;
    }

    public ItemBean getItemBeanFromGroup(GroupBean groupBean) {
        ItemBean itemBean = new ItemBean();
        itemBean.setGroupId(groupBean.getId());
        itemBean.setTitle(groupBean.getName());
        itemBean.setIconUrl(groupBean.getIconUrl());

        List<DeviceBean> deviceBeans = groupBean.getDeviceBeans();
        if (deviceBeans == null || deviceBeans.isEmpty()) {
            return null;
        } else {
            DeviceBean onlineDev = null;
            for (DeviceBean dev : deviceBeans) {
                if (dev != null) {
                    if (dev.getIsOnline()) {
                        onlineDev = dev;
                        break;
                    } else {
                        onlineDev = dev;
                    }
                }
            }
            itemBean.setDevId(onlineDev.getDevId());
            return itemBean;
        }
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


}
