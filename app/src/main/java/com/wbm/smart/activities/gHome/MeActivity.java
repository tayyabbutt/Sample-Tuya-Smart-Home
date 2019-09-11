package com.wbm.smart.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tuya.smart.android.user.api.ILogoutCallback;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.wbm.smart.R;
import com.wbm.smart.interfaces.OnLogoutClickListner;
import com.wbm.smart.utils.MyDialog;
import com.wbm.smart.utils.SharedPrefUtility;
import com.wbm.smart.utils.Utility;

public class MeActivity extends AppCompatActivity {
    KProgressHUD progress;
    TextView userName, userEmail, aboutUs, contactUs, homeManagement, privacyPolicy, termsAndCondition, logout;
    ImageView userDp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_layout);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        aboutUs = findViewById(R.id.aboutUs);
        contactUs = findViewById(R.id.contactUs);
        homeManagement = findViewById(R.id.homeManagement);
        privacyPolicy = findViewById(R.id.privacyPolicy);
        termsAndCondition = findViewById(R.id.termsAandCondition);
        logout = findViewById(R.id.logout);
        userDp = findViewById(R.id.userDp);

        if (TuyaHomeSdk.getUserInstance().getUser() != null) {
            if (TuyaHomeSdk.getUserInstance().getUser().getUsername() != null) {
                userName.setText(TuyaHomeSdk.getUserInstance().getUser().getUsername());
            }
            if (TuyaHomeSdk.getUserInstance().getUser().getEmail() != null) {
                userEmail.setText(TuyaHomeSdk.getUserInstance().getUser().getEmail());
            }
        }


        homeManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(MeActivity.this, ShowHomeActivity.class, false, null);
            }
        });

        userDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MeActivity.this, "not functioning rightNow", Toast.LENGTH_SHORT).show();
            }
        });
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MeActivity.this, "not functioning rightNow", Toast.LENGTH_SHORT).show();
            }
        });
        userEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MeActivity.this, "not functioning rightNow", Toast.LENGTH_SHORT).show();
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(MeActivity.this, AboutUs.class, false, null);
            }
        });
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MeActivity.this, "not functioning rightNow", Toast.LENGTH_SHORT).show();
            }
        });
        termsAndCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MeActivity.this, "not functioning rightNow", Toast.LENGTH_SHORT).show();
            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(MeActivity.this, ContactUs.class, false, null);
                //Toast.makeText(MeActivity.this, "not functioning rightNow", Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.showLogOutDialog(MeActivity.this, new OnLogoutClickListner() {
                    @Override
                    public void onLogoutClickListner() {
                        showProgress();
                        TuyaHomeSdk.getUserInstance().logout(new ILogoutCallback() {
                            @Override
                            public void onSuccess() {
                                hideProgress();
                                Toast.makeText(MeActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                                SharedPrefUtility.getInstance(MeActivity.this).clearPreferences();
                                Utility.launchActivity(MeActivity.this, SplashActivity.class, true, null);
                            }

                            @Override
                            public void onError(String errorCode, String errorMsg) {
                                hideProgress();
                                Toast.makeText(MeActivity.this, "logout failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });


    }

    public void showProgress() {
        progress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait...")
                .setAnimationSpeed(2)
                .show();
    }

    public void hideProgress() {
        progress.dismiss();
    }
}
