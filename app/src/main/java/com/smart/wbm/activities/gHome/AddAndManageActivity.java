package com.smart.wbm.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smart.wbm.R;
import com.smart.wbm.utils.Utility;

public class AddAndManageActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView speakerGroup, newHome, setUpDevice;
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_and_manage);
        toolbar = findViewById(R.id.toolbar);
        // back = findViewById(R.id.back);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        speakerGroup = findViewById(R.id.speakerGroup);
        newHome = findViewById(R.id.newHome);
        setUpDevice = findViewById(R.id.setUpDevice);

        newHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(AddAndManageActivity.this, ShowHomeActivity.class, false, null);
                // MyDialog.showDialog(AddAndManageActivity.this, "Make new home", null);
            }
        });

        setUpDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(AddAndManageActivity.this, DeviceListActivity.class, false, null);
            }
        });

    }
}
