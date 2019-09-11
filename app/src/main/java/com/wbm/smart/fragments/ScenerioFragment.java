package com.wbm.smart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wbm.smart.R;
import com.wbm.smart.activities.ScenerioActionActivity;
import com.wbm.smart.utils.Utility;

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
