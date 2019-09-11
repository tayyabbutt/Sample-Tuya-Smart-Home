package com.wbm.smart.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.wbm.smart.WbmSmartApp;
import com.wbm.smart.models.HomeModel;
import com.wbm.smart.utils.Constants;
import com.wbm.smart.utils.SharedPrefUtility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SmartManager {
    private static SmartManager instance;

    private SmartManager() {
    }

    public static SmartManager getInstance() {
        if (instance == null) {
            instance = new SmartManager();
        }
        return instance;
    }


    public void saveListofHome(List<HomeModel> list) {
        Gson gson = new Gson();
        String s = gson.toJson(list);
        SharedPrefUtility.getInstance(WbmSmartApp.getAppContext()).savePrefrences(Constants.LISTOFHOME, s);
    }

    public void saveListofTuyaHome(List<HomeBean> list) {
        Gson gson = new Gson();
        String s = gson.toJson(list);
        SharedPrefUtility.getInstance(WbmSmartApp.getAppContext()).savePrefrences(Constants.LISTOFHOME, s);
    }

    public List<HomeModel> getListofHome() {
        List<HomeModel> list = new ArrayList<>();
        String json = SharedPrefUtility.getInstance(WbmSmartApp.getAppContext()).getStringValue(Constants.LISTOFHOME);
        Gson gson = new Gson();
        if (json != null) {
            Type type = new TypeToken<List<HomeModel>>() {
            }.getType();
            list = gson.fromJson(json, type);

        }
        return list;
    }


    public List<HomeBean> getListofTuyaHome() {
        List<HomeBean> list = new ArrayList<>();
        String json = SharedPrefUtility.getInstance(WbmSmartApp.getAppContext()).getStringValue(Constants.LISTOFHOME);
        Gson gson = new Gson();
        if (json != null) {
            Type type = new TypeToken<List<HomeBean>>() {
            }.getType();
            list = gson.fromJson(json, type);

        }
        return list;
    }


    public void saveSelectedHome(Long id) {
        SharedPrefUtility.getInstance(WbmSmartApp.getAppContext()).savePrefrences(Constants.SELECTEDHOME, id);
    }

    public Long getSelectedHome() {
        return SharedPrefUtility.getInstance(WbmSmartApp.getAppContext()).getLongValue(Constants.SELECTEDHOME);
    }


    public void saveSelectedHomeName(String name) {
        SharedPrefUtility.getInstance(WbmSmartApp.getAppContext()).savePrefrences(Constants.SELECTEDHOMENAME, name);
    }

    public String getSelectedHomeName() {
        return SharedPrefUtility.getInstance(WbmSmartApp.getAppContext()).getStringValue(Constants.SELECTEDHOMENAME);
    }


}
