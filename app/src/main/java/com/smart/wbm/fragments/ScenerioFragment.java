package com.smart.wbm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smart.wbm.R;
import com.smart.wbm.activities.ScenerioActionActivity;
import com.smart.wbm.utils.Utility;

public class ScenerioFragment extends Fragment {

    Button addScenarioBtn;

    public ScenerioFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scenario, container, false);
        addScenarioBtn = view.findViewById(R.id.addScenarioBtn);
        addScenarioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(getContext(), ScenerioActionActivity.class,false,null);
            }
        });

        return view;
    }


}
