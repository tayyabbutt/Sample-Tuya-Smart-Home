package com.smart.wbm.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.tuya.smart.android.common.utils.ValidatorUtil;
import com.tuya.smart.android.user.api.ILoginCallback;
import com.tuya.smart.android.user.bean.User;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.smart.wbm.R;
import com.smart.wbm.utils.SharedPrefUtility;
import com.smart.wbm.utils.Utility;

public class LoginActivity extends AppCompatActivity implements TextWatcher {

    CountryCodePicker countryNamePicker;
    String countryName;
    Button loginBtn;
    TextView register;
    EditText email, password;
    KProgressHUD progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_layout);
        init();
    }

    private void init() {
        countryNamePicker = findViewById(R.id.country);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        loginBtn = findViewById(R.id.button);
        email = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        register = findViewById(R.id.register);
        countryName = countryNamePicker.getSelectedCountryCodeWithPlus();
        countryNamePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                countryName = selectedCountry.getPhoneCode();
                Toast.makeText(LoginActivity.this, "selected " + selectedCountry.getName(), Toast.LENGTH_SHORT).show();
            }
        });
      /*  skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(LoginActivity.this, MainDashBoardHome.class, false, null);
            }
        });*/

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.launchActivity(LoginActivity.this, RegisterUser.class, false, null);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                TuyaHomeSdk.getUserInstance().loginWithEmail(countryName, email.getText().toString(), password.getText().toString(), new ILoginCallback() {
                    @Override
                    public void onSuccess(User user) {
                        hideProgress();
                        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        SharedPrefUtility.getInstance(LoginActivity.this).savePrefrences("isLogin", true);
                        Utility.launchActivity(LoginActivity.this, MainDashBoardHome.class, true, null);
                    }

                    @Override
                    public void onError(String code, String error) {
                        hideProgress();
                        Toast.makeText(LoginActivity.this, "Fail to login bcoz of " + error + " " + code, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String userName = email.getText().toString();
        String password1 = password.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password1)) {
            disableLogin();
        } else {
            if (ValidatorUtil.isEmail(userName)) {
                enableLogin();
            } else {
                try {
                    Long.valueOf(userName);
                    enableLogin();
                } catch (Exception e) {
                    disableLogin();
                }
            }
        }
    }

    public void enableLogin() {
        if (!loginBtn.isEnabled()) {
            loginBtn.setEnabled(true);
        }
    }

    public void disableLogin() {
        if (loginBtn.isEnabled()) loginBtn.setEnabled(false);
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
