package com.wbm.smart.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.wbm.smart.R;
import com.wbm.smart.utils.Utility;

public class ScenerioActionActivity extends BaseActivity {

    Switch homeSwitch;
    TextView addActionText;
    ImageView addActionImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frame_hloder);
        getLayoutInflater().inflate(R.layout.add_scenario_layout, contentFrameLayout);
        init();
        gettitleview().setText("Smart Settings");
    }

    private void init() {
        addActionText = findViewById(R.id.addActionText);
        addActionImage = findViewById(R.id.addActionImage);
        homeSwitch = findViewById(R.id.homeSwitch);

        addActionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("scenario","scenario");
                Utility.launchActivity(ScenerioActionActivity.this, AddActionScrenarioActivity.class, false, bundle);
            }
        });

        addActionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(ScenerioActionActivity.this, AddActionScrenarioActivity.class, false, null);
            }
        });

    }
}
