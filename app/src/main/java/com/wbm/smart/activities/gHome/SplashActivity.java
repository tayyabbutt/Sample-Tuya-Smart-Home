package com.wbm.smart.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.wbm.smart.R;
import com.wbm.smart.utils.SharedPrefUtility;
import com.wbm.smart.utils.Utility;

public class SplashActivity extends AppCompatActivity {


    int PERMISSION_ALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        Utility.hideSystemUI(getWindow().getDecorView());
        // ImageView gif = findViewById(R.id.splash);
      /*  String[] PERMISSIONS = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
        };
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }*/
        // Glide.with(this).asGif().load(R.drawable.gif_splash).into(gif);

        Utility.hideSystemUI(getWindow().getDecorView());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                if (SharedPrefUtility.getInstance(SplashActivity.this).getBooleanValue("isLogin") == true) {
                    Utility.launchActivity(SplashActivity.this, MainDashBoardHome.class, true, null);
                } else {
                    Utility.launchActivity(SplashActivity.this, LoginActivity.class, true, null);
                }

            }
        }, 2000);
    }

  /*  public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }*/
}
