package com.smart.wbm.activities.tuyaOldScreens;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.wbm.R;
import com.smart.wbm.adapters.ActionSccenarioAdapter;
import com.smart.wbm.models.DummyAction;

import java.util.ArrayList;
import java.util.List;

public class AddActionScrenarioActivity extends BaseActivity {

    RecyclerView recyclerView;
    List<DummyAction> actionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.frame_hloder);
        getLayoutInflater().inflate(R.layout.select_action_layout, contentFrameLayout);
        init();
        gettitleview().setText("Select Action");
        if (getIntent().getStringExtra("scenario") != null) {
            initDummyScenarioList();
        } else if (getIntent().getStringExtra("condition") != null) {
            initDummyConditionList();
        }


    }

    private void init() {
        recyclerView = findViewById(R.id.actionRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

    }

    private void initDummyScenarioList() {
        actionList.add(0, new DummyAction("Enable or Disable Automation"));
        actionList.add(1, new DummyAction("Time-lapse"));
        actionList.add(2, new DummyAction("Device"));
        setAdapter();
    }

    private void initDummyConditionList() {
        actionList.add(0, new DummyAction("Execute Scenario"));
        actionList.add(1, new DummyAction("Enable or Disable Automation"));
        actionList.add(2, new DummyAction("Send Notification"));
        actionList.add(3, new DummyAction("Time-lapse"));
        actionList.add(4, new DummyAction("Device"));
        setAdapter();
    }

    private void setAdapter() {
        ActionSccenarioAdapter adapter = new ActionSccenarioAdapter(AddActionScrenarioActivity.this, actionList);
        recyclerView.setAdapter(adapter);
    }


}
