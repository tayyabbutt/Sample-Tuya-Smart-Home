package com.smart.wbm;


import android.app.Application;
import android.content.Context;

import com.tuya.smart.home.sdk.TuyaHomeSdk;

import java.io.File;

public class WbmSmartApp extends Application {

    private static Context context;

    private static WbmSmartApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;
        TuyaHomeSdk.init(this);
    }

    public static Context getAppContext() {
        return context;
    }

    public static WbmSmartApp getInstance() {
        return instance;
    }


    public void clearApplicationData() {
        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }

        return deletedAll;
    }

}