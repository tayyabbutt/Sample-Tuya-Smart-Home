package com.wbm.smart.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wbm.smart.R;
import com.wbm.smart.adapters.ActionSccenarioAdapter;
import com.wbm.smart.models.DummyAction;

import java.util.ArrayList;
import java.util.List;

public class AutomationConditionsActivity extends BaseActivity {

    RecyclerView conditionRecyclerView;

    List<DummyAction> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frame_hloder);
        getLayoutInflater().inflate(R.layout.automation_conditions_layout, contentFrameLayout);
        init();
        gettitleview().setText("Select Condition");
    }

    private void init() {

        conditionRecyclerView = findViewById(R.id.conditionRecyclerView);
        conditionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        conditionRecyclerView.setHasFixedSize(true);

        setDummyData();
    }

    private void setDummyData() {
        list.add(0, new DummyAction("Temperature"));
        list.add(1, new DummyAction("Humidity"));
        list.add(2, new DummyAction("Weather"));
        list.add(3, new DummyAction("PM2.5"));
        list.add(4, new DummyAction("Air Quality"));
        list.add(5, new DummyAction("Sunset/Sunrise"));
        list.add(6, new DummyAction("Schedule"));
        list.add(7, new DummyAction("Device"));
        setAdapter();
    }

    private void setAdapter() {
        ActionSccenarioAdapter adapter = new ActionSccenarioAdapter(AutomationConditionsActivity.this, list);
        conditionRecyclerView.setAdapter(adapter);
    }
}
