package com.wbm.smart.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wbm.smart.R;
import com.wbm.smart.activities.DashBoardActivity;
import com.wbm.smart.activities.DevicesActivity;
import com.wbm.smart.utils.Utility;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    DashBoardActivity parentActivity;


    Button addDevice;
    LinearLayout addDeviceLayout;

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (DashBoardActivity) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addDevice = view.findViewById(R.id.addDeviceBtn);
        addDeviceLayout = view.findViewById(R.id.addDeviceLayout);


        addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(getContext(), DevicesActivity.class,false,null);
                //Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


}
