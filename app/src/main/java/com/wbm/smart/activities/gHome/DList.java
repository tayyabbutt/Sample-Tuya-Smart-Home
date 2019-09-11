package com.wbm.smart.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuya.smart.sdk.bean.DeviceBean;
import com.wbm.smart.R;
import com.wbm.smart.adapters.DAdapter;

import java.util.ArrayList;
import java.util.List;

public class DList extends AppCompatActivity {

    RecyclerView recyclerView;

    List<DeviceBean> devList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlist);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        recyclerView = findViewById(R.id.devList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        DeviceBean deviceBean = new DeviceBean();
        deviceBean.setDevId("vdevo156656560273173");
        devList.add(deviceBean);

        String devId = deviceBean.getDevId();


        setAdapter();
    }

    private void setAdapter() {
        DAdapter adapter = new DAdapter(DList.this, devList);
        recyclerView.setAdapter(adapter);

    }


}
