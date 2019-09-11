package com.wbm.smart.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class Utility {
    public static final int DIALOG_WIDTH_MARGIN = 50;
    public static final int DIALOG_HEIGHT_MARGIN = 90;

    public static void launchActivity(Context mContext, Class<?> mClass, boolean shouldFinish, Bundle bundle) {
        Intent intent = new Intent(mContext, mClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
        if (shouldFinish) {
            ((Activity) mContext).finish();
        }
    }

    public static boolean isOnline(Context context) {
        if (context != null) {
            ConnectivityManager connMgr = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr != null ? connMgr.getActiveNetworkInfo() : null;
            return (networkInfo != null && networkInfo.isConnected());
        } else return false;
    }

    public static void hideSystemUI(View mDecorView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }


}
