package com.wbm.smart.activities.gHome;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.tuya.smart.android.user.api.IRegisterCallback;
import com.tuya.smart.android.user.bean.User;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.IResultCallback;
import com.wbm.smart.R;
import com.wbm.smart.utils.Utility;

public class RegisterUser extends AppCompatActivity {

    KProgressHUD progress;

    EditText email, code, password;
    Button getCodeBtn, codeBtn;

    CountryCodePicker countryNamePicker;
    static String countryName;

    LinearLayout getCodeLayout, codeLayout;
    static String emailStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen_layout);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        init();
    }

    private void init() {
        getCodeLayout = findViewById(R.id.getCodeLayout);
        codeLayout = findViewById(R.id.codeLayout);
        email = findViewById(R.id.regEmail);
        code = findViewById(R.id.code);
        getCodeBtn = findViewById(R.id.getCodeBtn);
        password = findViewById(R.id.password);
        codeBtn = findViewById(R.id.codeBtn);
        countryNamePicker = findViewById(R.id.country);
        countryName = countryNamePicker.getSelectedCountryCodeWithPlus();
        countryNamePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                countryName = selectedCountry.getPhoneCode();
                Toast.makeText(RegisterUser.this, "selected " + selectedCountry.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText() != null && email.getText().length() != 0) {
                    showProgress();
                    emailStr = email.getText().toString();
                    TuyaHomeSdk.getUserInstance().getRegisterEmailValidateCode(countryName, email.getText().toString(), new IResultCallback() {
                        @Override
                        public void onError(String code, String error) {
                            hideProgress();
                            getCodeLayout.setVisibility(View.VISIBLE);
                            codeLayout.setVisibility(View.GONE);
                            Toast.makeText(RegisterUser.this, "something went wrong while sending code", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess() {
                            hideProgress();
                            getCodeLayout.setVisibility(View.GONE);
                            codeLayout.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    Toast.makeText(RegisterUser.this, "please  enter email ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        codeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                TuyaHomeSdk.getUserInstance().registerAccountWithEmail(countryName, email.getText().toString(), password.getText().toString(), code.getText().toString(), new IRegisterCallback() {
                    @Override
                    public void onSuccess(User user) {
                        hideProgress();
                        Toast.makeText(RegisterUser.this, "The registration succeeds.", Toast.LENGTH_SHORT).show();
                        Utility.launchActivity(RegisterUser.this, LoginActivity.class, false, null);
                    }

                    @Override
                    public void onError(String code, String error) {
                        hideProgress();
                        Toast.makeText(RegisterUser.this, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
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
