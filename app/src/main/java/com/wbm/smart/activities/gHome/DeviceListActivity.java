package com.wbm.smart.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wbm.smart.R;
import com.wbm.smart.adapters.DeviceAdapter;
import com.wbm.smart.models.DummyData;

import java.util.ArrayList;
import java.util.List;

public class DeviceListActivity extends AppCompatActivity {

    TextView electricalBtn, lightingBtn, lHomeAppliances, sHomeAppliances, kAppliances, securityAndSensors, exerciseAndHealth, others;

    List<DummyData> dummyData = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_devices);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        /*FrameLayout contentFrameLayout = findViewById(R.id.frame_hloder);
        getLayoutInflater().inflate(R.layout.activity_devices, contentFrameLayout);*/
        electricalBtn = findViewById(R.id.electricalBtn);
        lightingBtn = findViewById(R.id.lightingBtn);
        lHomeAppliances = findViewById(R.id.lHomeAppliances);
        sHomeAppliances = findViewById(R.id.sHomeAppliances);
        kAppliances = findViewById(R.id.kAppliances);
        securityAndSensors = findViewById(R.id.securityAndSensors);
        exerciseAndHealth = findViewById(R.id.exerciseAndHealth);
        others = findViewById(R.id.others);
        recyclerView = findViewById(R.id.deviceRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        electricalBtn.setBackgroundColor(getResources().getColor(R.color.white));
        electricalBtn.setTextColor(getResources().getColor(R.color.red));
        setDummyData();

        electricalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDummyData();
                electricalBtn.setBackgroundColor(getResources().getColor(R.color.white));
                electricalBtn.setTextColor(getResources().getColor(R.color.red));

                lightingBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lightingBtn.setTextColor(getResources().getColor(R.color.black));
                exerciseAndHealth.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                exerciseAndHealth.setTextColor(getResources().getColor(R.color.black));
                others.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                others.setTextColor(getResources().getColor(R.color.black));
                electricalBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                electricalBtn.setTextColor(getResources().getColor(R.color.black));
                lHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                sHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                sHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                securityAndSensors.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                securityAndSensors.setTextColor(getResources().getColor(R.color.black));
                kAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                kAppliances.setTextColor(getResources().getColor(R.color.black));
            }
        });
        lightingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDummyData1();
                lightingBtn.setBackgroundColor(getResources().getColor(R.color.white));
                lightingBtn.setTextColor(getResources().getColor(R.color.red));

                exerciseAndHealth.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                exerciseAndHealth.setTextColor(getResources().getColor(R.color.black));
                others.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                others.setTextColor(getResources().getColor(R.color.black));
                electricalBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                electricalBtn.setTextColor(getResources().getColor(R.color.black));
                lHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                sHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                sHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                securityAndSensors.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                securityAndSensors.setTextColor(getResources().getColor(R.color.black));
                kAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                kAppliances.setTextColor(getResources().getColor(R.color.black));
            }
        });


        exerciseAndHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDummyData();
                exerciseAndHealth.setBackgroundColor(getResources().getColor(R.color.white));
                exerciseAndHealth.setTextColor(getResources().getColor(R.color.red));
                others.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                others.setTextColor(getResources().getColor(R.color.black));
                electricalBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                electricalBtn.setTextColor(getResources().getColor(R.color.black));
                lightingBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lightingBtn.setTextColor(getResources().getColor(R.color.black));
                lHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                sHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                sHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                securityAndSensors.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                securityAndSensors.setTextColor(getResources().getColor(R.color.black));
                kAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                kAppliances.setTextColor(getResources().getColor(R.color.black));
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDummyData1();
                others.setBackgroundColor(getResources().getColor(R.color.white));
                others.setTextColor(getResources().getColor(R.color.red));
                electricalBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                electricalBtn.setTextColor(getResources().getColor(R.color.black));
                lightingBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lightingBtn.setTextColor(getResources().getColor(R.color.black));
                lHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                exerciseAndHealth.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                exerciseAndHealth.setTextColor(getResources().getColor(R.color.black));
                sHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                sHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                securityAndSensors.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                securityAndSensors.setTextColor(getResources().getColor(R.color.black));
                kAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                kAppliances.setTextColor(getResources().getColor(R.color.black));
            }
        });

        kAppliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDummyData();
                kAppliances.setBackgroundColor(getResources().getColor(R.color.white));
                kAppliances.setTextColor(getResources().getColor(R.color.red));
                others.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                others.setTextColor(getResources().getColor(R.color.black));
                electricalBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                electricalBtn.setTextColor(getResources().getColor(R.color.black));
                lightingBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lightingBtn.setTextColor(getResources().getColor(R.color.black));
                lHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                exerciseAndHealth.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                exerciseAndHealth.setTextColor(getResources().getColor(R.color.black));
                sHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                sHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                securityAndSensors.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                securityAndSensors.setTextColor(getResources().getColor(R.color.black));

            }
        });
        securityAndSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDummyData1();
                securityAndSensors.setBackgroundColor(getResources().getColor(R.color.white));
                securityAndSensors.setTextColor(getResources().getColor(R.color.red));

                electricalBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                electricalBtn.setTextColor(getResources().getColor(R.color.black));
                lightingBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lightingBtn.setTextColor(getResources().getColor(R.color.black));
                lHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                exerciseAndHealth.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                exerciseAndHealth.setTextColor(getResources().getColor(R.color.black));
                sHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                sHomeAppliances.setTextColor(getResources().getColor(R.color.black));

            }
        });

        sHomeAppliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDummyData();
                sHomeAppliances.setBackgroundColor(getResources().getColor(R.color.white));
                sHomeAppliances.setTextColor(getResources().getColor(R.color.red));
                others.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                others.setTextColor(getResources().getColor(R.color.black));
                electricalBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                electricalBtn.setTextColor(getResources().getColor(R.color.black));
                lightingBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lightingBtn.setTextColor(getResources().getColor(R.color.black));
                securityAndSensors.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                securityAndSensors.setTextColor(getResources().getColor(R.color.black));
                lHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                exerciseAndHealth.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                exerciseAndHealth.setTextColor(getResources().getColor(R.color.black));
            }
        });
        lHomeAppliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDummyData1();
                lHomeAppliances.setBackgroundColor(getResources().getColor(R.color.white));
                lHomeAppliances.setTextColor(getResources().getColor(R.color.red));
                others.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                others.setTextColor(getResources().getColor(R.color.black));
                electricalBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                electricalBtn.setTextColor(getResources().getColor(R.color.black));
                lightingBtn.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                lightingBtn.setTextColor(getResources().getColor(R.color.black));
                sHomeAppliances.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                sHomeAppliances.setTextColor(getResources().getColor(R.color.black));
                securityAndSensors.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                securityAndSensors.setTextColor(getResources().getColor(R.color.black));
                exerciseAndHealth.setBackgroundColor(getResources().getColor(R.color.lightGrey));
                exerciseAndHealth.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }


    private void setDummyData() {
        dummyData.add(0, new DummyData("https://images.tuyacn.com/smart/solution/15001/66ba22c327bfbf3d_cover.png", "Socket"));
        dummyData.add(1, new DummyData("https://images.tuyacn.com/smart/solution/18003/b8bb0466b861046d_cover.png", "Switch"));
        dummyData.add(2, new DummyData("https://images.tuyacn.com/smart/solution/28001/37e69c2be34b7642_cover.png", "Bulb"));
        dummyData.add(3, new DummyData("https://images.tuyacn.com/smart/program_category_icon/sp.png", "Camera"));
        dummyData.add(4, new DummyData("https://images.tuyacn.com/smart/program_category_icon/rqbj.png", "Gas Detector"));
        dummyData.add(5, new DummyData("https://images.tuyacn.com/smart/program_category_icon/smd.png", "Sleep Band"));
        setAdapter();

    }

    private void setDummyData1() {
        dummyData.add(0, new DummyData("https://images.tuyacn.com/smart/program_category_icon/smd.png", "Sleep Band"));
        dummyData.add(1, new DummyData("https://images.tuyacn.com/smart/program_category_icon/sp.png", "Camera"));
        dummyData.add(2, new DummyData("https://images.tuyacn.com/smart/program_category_icon/rqbj.png", "Gas Detector"));
        dummyData.add(3, new DummyData("https://images.tuyacn.com/smart/solution/18003/b8bb0466b861046d_cover.png", "Switch"));
        dummyData.add(4, new DummyData("https://images.tuyacn.com/smart/solution/28001/37e69c2be34b7642_cover.png", "Bulb"));
        dummyData.add(5, new DummyData("https://images.tuyacn.com/smart/solution/15001/66ba22c327bfbf3d_cover.png", "Socket"));
        setAdapter();

    }

    private void setAdapter() {

        DeviceAdapter adapter = new DeviceAdapter(DeviceListActivity.this, dummyData);
        recyclerView.setAdapter(adapter);

    }
}

