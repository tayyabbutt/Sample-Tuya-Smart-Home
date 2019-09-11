package com.wbm.smart.activities.gHome;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.api.ITuyaHomeChangeListener;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.bean.RoomBean;
import com.tuya.smart.home.sdk.callback.ITuyaGetHomeListCallback;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.GroupBean;
import com.wbm.smart.R;
import com.wbm.smart.adapters.DeviceHomeAdapter;
import com.wbm.smart.adapters.DevicesHomeAdapter;
import com.wbm.smart.adapters.TuyaRoomAdapter;
import com.wbm.smart.interfaces.HomeNameClickListner;
import com.wbm.smart.interfaces.OnDeviceClickListner;
import com.wbm.smart.interfaces.OnRoomClickToAddDevice;
import com.wbm.smart.manager.SmartManager;
import com.wbm.smart.models.DevicesBean;
import com.wbm.smart.models.HomeModel;
import com.wbm.smart.models.ItemBean;
import com.wbm.smart.models.Room;
import com.wbm.smart.models.RoomsBean;
import com.wbm.smart.utils.MyDialog;
import com.wbm.smart.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainDashBoardHome extends AppCompatActivity {
    KProgressHUD progress;
    private SpaceNavigationView spaceNavigationView;
    LinearLayout addLayout, settingsLayout, routineLayout;
    TextView homeText;
    RecyclerView deviceRecyclerView, horizontalRoomView;
    List<HomeModel> homeList = new ArrayList<>();
    List<Room> roomList = new ArrayList<>();
    DeviceHomeAdapter deviceAdapter;
    private long mCurrentHomeId;
    private long mCurrentRoomId;
    Button addDeviceBtn;
    Boolean isButtonActive = false;

    private ITuyaHomeChangeListener mHomeChangeListener = new ITuyaHomeChangeListener() {
        @Override
        public void onHomeAdded(long homeId) {

        }

        @Override
        public void onHomeInvite(long homeId, String homeName) {

        }

        @Override
        public void onHomeRemoved(long l) {

        }

        @Override
        public void onHomeInfoChanged(long l) {

        }

        @Override
        public void onSharedDeviceList(List<DeviceBean> list) {

        }

        @Override
        public void onSharedGroupList(List<GroupBean> list) {

        }

        @Override
        public void onServerConnectSuccess() {
            getTuyaHomeList();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_dashboard_home_layout);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        /*FrameLayout contentFrameLayout = findViewById(R.id.frame_hloder);
        getLayoutInflater().inflate(R.layout.main_dashboard_home_layout, contentFrameLayout);*/


        horizontalRoomView = findViewById(R.id.roomRecyclerView);
        deviceRecyclerView = findViewById(R.id.deviceRecyclerView);
        homeText = findViewById(R.id.homeText);
        addDeviceBtn = findViewById(R.id.addDeviceBtn);
        addDeviceBtn.setEnabled(false);
        addDeviceBtn.setBackground(ContextCompat.getDrawable(MainDashBoardHome.this, R.drawable.disabled_btn_background));

        horizontalRoomView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        deviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        deviceRecyclerView.setHasFixedSize(true);
        horizontalRoomView.setHasFixedSize(true);

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String ssidStr = info.getSSID();

        routineLayout = findViewById(R.id.routines);
        addLayout = findViewById(R.id.add);
        settingsLayout = findViewById(R.id.settingsLayout);
        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("home", R.drawable.home));
        spaceNavigationView.addSpaceItem(new SpaceItem("discover", R.drawable.home));
        spaceNavigationView.addSpaceItem(new SpaceItem("smart", R.drawable.smart_home));
        spaceNavigationView.addSpaceItem(new SpaceItem("me", R.drawable.user));
        spaceNavigationView.showIconOnly();
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(MainDashBoardHome.this, "onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(MainDashBoardHome.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                if (itemIndex == 2) {
                    Utility.launchActivity(MainDashBoardHome.this, WifiActivity.class, false, null);
                }
                if (itemIndex == 3) {
                    Utility.launchActivity(MainDashBoardHome.this, MeActivity.class, false, null);
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(MainDashBoardHome.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
        // getHome();
        addLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(MainDashBoardHome.this, AddAndManageActivity.class, false, null);
            }
        });


        homeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.showHomeDialog(MainDashBoardHome.this, new HomeNameClickListner() {
                    @Override
                    public void homeListner(HomeBean homeObject) {
                        SmartManager.getInstance().saveSelectedHome(homeObject.getHomeId());
                        SmartManager.getInstance().saveSelectedHomeName(homeObject.getName());
                        homeText.setText(homeObject.getName());
                        getCurrentTuyaRoom(homeObject.getHomeId());
                        mCurrentHomeId = homeObject.getHomeId();
                        //getTuyaCurrentHomeDetail();
                    }
                });
            }
        });

        if (SmartManager.getInstance().getSelectedHomeName() != null) {
            homeText.setText(SmartManager.getInstance().getSelectedHomeName());
            mCurrentHomeId = SmartManager.getInstance().getSelectedHome();
            getCurrentTuyaRoom(SmartManager.getInstance().getSelectedHome());

            // getTuyaCurrentHomeDetail();
        } else {
            if (homeList != null && homeList.size() > 0)
                homeText.setText(homeList.get(0).getName());

        }

        /*addDeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("homeId", mCurrentHomeId);
                Utility.launchActivity(MainDashBoardHome.this, WifiActivity.class, false, bundle);
            }
        });*/


      /*  adapter = new DeviceHomeAdapter(MainDashBoardHome.this, null, null);
        deviceRecyclerView.setAdapter(adapter);*/

        TuyaHomeSdk.getHomeManagerInstance().registerTuyaHomeChangeListener(mHomeChangeListener);
        // getTuyaHomeList();


       /* List<String> room = new ArrayList<>();
        room.add(0, "dining");
        room.add(1, "kitchen");
        TuyaHomeSdk.getHomeManagerInstance().createHome("Tayyab", 3.7485, 3.3257, "Lahore", room, new ITuyaHomeResultCallback() {
            @Override
            public void onSuccess(HomeBean bean) {
                Toast.makeText(MainDashBoardHome.this, "Sucess home creates " + bean, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(MainDashBoardHome.this, "failure in home creation " + errorMsg, Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    @Override
    protected void onResume() {
        super.onResume();
        getTuyaHomeList();
        if (SmartManager.getInstance().getSelectedHomeName() != null) {
            homeText.setText(SmartManager.getInstance().getSelectedHomeName());
        } else {
            if (homeList != null && homeList.size() > 0)
                homeText.setText(homeList.get(0).getName());
        }
    }

    /*private void getHomeList() {
        showProgress();
        TuyaHomeSdk.getHomeManagerInstance().queryHomeList(new ITuyaGetHomeListCallback() {
            @Override
            public void onSuccess(List<HomeBean> list) {
                hideProgress();
                if (!list.isEmpty()) {
                    homeText.setText(list.get(0).getName());
                    SmartManager.getInstance().saveListofTuyaHome(list);
                } else {
                    Toast.makeText(MainDashBoardHome.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String s, String s1) {
                hideProgress();
                Toast.makeText(MainDashBoardHome.this, s + "\n" + s1, Toast.LENGTH_LONG).show();
            }
        });
    }*/

   /* private void getHome() {
        if (Utility.isOnline(this)) {
            showProgress();
            Call<List<HomeModel>> call = RetrofitClient.getInstance().getApiService().getHome(ApiKeys.BASE_URL + ApiKeys.getAllHome);
            call.enqueue(new Callback<List<HomeModel>>() {
                @Override
                public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                    hideProgress();
                    if (response.isSuccessful()) {
                        if (homeList.size() > 0) {
                            homeList.clear();
                        }
                        homeList = response.body();
                        homeText.setText(homeList.get(0).getName());
                        SmartManager.getInstance().saveListofHome(homeList);
                    }
                }

                @Override
                public void onFailure(Call<List<HomeModel>> call, Throwable t) {
                    hideProgress();
                    Toast.makeText(MainDashBoardHome.this, "server error", Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(this, "No internet available", Toast.LENGTH_SHORT).show();
        }

    }*/

  /*  private void getRoomsByHomeId(String homeId) {
        if (Utility.isOnline(this)) {
            showProgress();
            Call<RoomDto> call = RetrofitClient.getInstance().getApiService().getRooms(ApiKeys.BASE_URL + ApiKeys.getRoomAgainstHome + homeId);
            call.enqueue(new Callback<RoomDto>() {
                @Override
                public void onResponse(Call<RoomDto> call, Response<RoomDto> response) {
                    if (response.isSuccessful()) {
                        hideProgress();
                        if (roomList.size() > 0) {
                            roomList.clear();
                        }
                        roomList = response.body().getRooms();
                        DashboardRoomsAdapter adapter = new DashboardRoomsAdapter(MainDashBoardHome.this, roomList);

                        horizontalRoomView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<RoomDto> call, Throwable t) {
                    hideProgress();
                }
            });
        } else {
            Toast.makeText(this, "No internet available", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void getCurrentTuyaRoom(final Long homeId) {
        //   showProgress();
        TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(new ITuyaHomeResultCallback() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                //          hideProgress();
                List<RoomsBean> beans = new ArrayList<>(8);
                for (RoomBean roomBean : homeBean.getRooms()) {
                    beans.add(getRooms(roomBean));
                }

                TuyaRoomAdapter adapter = new TuyaRoomAdapter(MainDashBoardHome.this, beans, new OnRoomClickToAddDevice() {
                    @Override
                    public void onRoomClickListner(final RoomsBean roomsBean) {

                        // getTuyaCurrentHomeDetail(homeId);
                        if (roomsBean.getDeviceList().size() != 0) {
                            getTuyaDevicesAgainstRoom(roomsBean);
                        } else {
                            getTuyaCurrentHomeDetail(homeId);
                        }

                        //  getTuyaCurrentHomeDetail(roomsBean.getRoomId());
                        addDeviceBtn.setEnabled(true);
                        addDeviceBtn.setBackground(ContextCompat.getDrawable(MainDashBoardHome.this, R.drawable.button_background));

                        if (addDeviceBtn.isEnabled()) {
                            //  addDeviceBtn.setBackground(ContextCompat.getDrawable(MainDashBoardHome.this, R.drawable.button_background));
                            addDeviceBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("roomObject", roomsBean);
                                    bundle.putLong("homeId", homeId);
                                    Utility.launchActivity(MainDashBoardHome.this, AddDeviceToRoomActivity.class, false, bundle);
                                }
                            });
                        }




                        /*if (isButtonActive == true) {
                            isButtonActive = false;
                            addDeviceBtn.setEnabled(false);
                            addDeviceBtn.setBackground(ContextCompat.getDrawable(MainDashBoardHome.this, R.drawable.button_background));

                        } else if (isButtonActive == false) {
                            isButtonActive = true;
                            addDeviceBtn.setEnabled(false);
                            addDeviceBtn.setBackground(ContextCompat.getDrawable(MainDashBoardHome.this, R.drawable.disabled_btn_background));
                        } else {
                            return;
                        }*/

                       /* Toast.makeText(MainDashBoardHome.this, "clicked", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("roomObject", roomsBean);
                        bundle.putLong("homeId", mCurrentHomeId);
                        Utility.launchActivity(MainDashBoardHome.this, AddDeviceToRoomActivity.class, false, bundle);*/
                    }
                });
                horizontalRoomView.setAdapter(adapter);
               // deviceAdapter.notifyDataSetChanged();
                //             getTuyaCurrentHomeDetail();

            }

            @Override
            public void onError(String s, String s1) {
                //           hideProgress();
                Toast.makeText(MainDashBoardHome.this, s + "\n" + s1, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
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

    private void getTuyaDevicesAgainstRoom(RoomsBean roomsBean) {
        List<DevicesBean> beans = new ArrayList<>(8);
        for (DeviceBean devicesBean : roomsBean.getDeviceList()) {
            beans.add(getDevices(devicesBean));
        }
        if (beans.size() > 0) {
            DevicesHomeAdapter adapter = new DevicesHomeAdapter(MainDashBoardHome.this, beans, null);
            deviceRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //   adapter.setData(beans);
        } else {
            Toast.makeText(MainDashBoardHome.this, "No device found", Toast.LENGTH_SHORT).show();
        }


    }


    public DevicesBean getDevices(DeviceBean deviceBean) {
        DevicesBean devicesBean = new DevicesBean();
        devicesBean.setDevId(deviceBean.getDevId());
        devicesBean.setName(deviceBean.getName());
        devicesBean.setIconUrl(deviceBean.getIconUrl());
        return devicesBean;
    }

    private void getTuyaHomeList() {
        //   showProgress();
        TuyaHomeSdk.getHomeManagerInstance().queryHomeList(new ITuyaGetHomeListCallback() {
            @Override
            public void onSuccess(List<HomeBean> list) {
                //        hideProgress();
                if (!list.isEmpty()) {
                    SmartManager.getInstance().saveListofTuyaHome(list);
                    HomeBean homeBean = list.get(0);
                   /* mCurrentHomeId = homeBean.getHomeId();
                    getTuyaCurrentHomeDetail();*/
                    //mCurrentRoomId = homeBean.getRooms().get(0).getRoomId();

                    /*adapter = new DeviceHomeAdapter(con,list);
                    deviceRecyclerView.setAdapter(adapter);*/

                } else {
                    Toast.makeText(MainDashBoardHome.this, "Success", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onError(String s, String s1) {
                //            hideProgress();
                Toast.makeText(MainDashBoardHome.this, s + "\n" + s1, Toast.LENGTH_LONG).show();
            }
        });
    }

    /*private void getToken() {
        showProgress();
        TuyaHomeSdk.getActivatorInstance().getActivatorToken(mCurrentHomeId, new ITuyaActivatorGetToken() {
            @Override
            public void onSuccess(String token) {
                //  hideProgress();
                hideProgress();
                Log.i(token, "onSuccess token is : " + token);
                mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newMultiActivator(new ActivatorBuilder()
                        .setSsid(WiFiUtil.getCurrentSSID(MainDashBoardHome.this))
                        .setContext(MainDashBoardHome.this)
                        .setPassword("iotdevices")
                        .setActivatorModel(ActivatorModelEnum.TY_EZ)
                        .setTimeOut(100)
                        .setToken(token)
                        .setListener(MainDashBoardHome.this));

            }

            @Override
            public void onFailure(String s, String s1) {
                hideProgress();
                Toast.makeText(MainDashBoardHome.this, "s " + s + "s1 " + s1, Toast.LENGTH_SHORT).show();
            }
        });*/


    private void getTuyaCurrentHomeDetail(Long homeId) {
        //    TuyaHomeSdk.newHomeInstance(mCurrentHomeId).getHomeDetail(new ITuyaHomeResultCallback() {
        TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(new ITuyaHomeResultCallback() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                //   hideProgress();
                List<ItemBean> beans = new ArrayList<>();
                for (GroupBean groupBean : homeBean.getGroupList()) {
                    homeBean.getRooms().get(0).getDeviceList();
                    beans.add(getItemBeanFromGroup(groupBean));
                }
                for (DeviceBean deviceBean : homeBean.getDeviceList()) {
                    beans.add(getItemBeanFromDevice(deviceBean));
                }
                if (beans.size() > 0) {
                    deviceAdapter = new DeviceHomeAdapter(MainDashBoardHome.this, beans, new OnDeviceClickListner() {
                        @Override
                        public void onDeviceClick(ItemBean bean, int position) {
                            Toast.makeText(MainDashBoardHome.this, "Clicked", Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("devId", bean.getDevId());
                            Utility.launchActivity(MainDashBoardHome.this, SwitchActivity.class, false, bundle);
                        }
                    });
                    deviceRecyclerView.setAdapter(deviceAdapter);
                    deviceAdapter.notifyDataSetChanged();
                    //   adapter.setData(beans);
                } else {
                    Toast.makeText(MainDashBoardHome.this, "No device found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String s, String s1) {
                //      hideProgress();
                Toast.makeText(MainDashBoardHome.this, s + "\n" + s1, Toast.LENGTH_LONG).show();
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

    public RoomsBean getRooms(RoomBean roomBean) {
        RoomsBean roomsBean = new RoomsBean();
        roomsBean.setRoomId(roomBean.getRoomId());
        roomsBean.setBackground(roomBean.getBackground());
        roomsBean.setDeviceList(roomBean.getDeviceList());
        roomsBean.setDisplayOrder(roomBean.getDisplayOrder());
        roomsBean.setName(roomBean.getName());
        roomsBean.setSel(roomBean.isSel());
        return roomsBean;
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
}
