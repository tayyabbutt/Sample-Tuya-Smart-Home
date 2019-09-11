package com.smart.wbm.activities.gHome;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smart.wbm.R;

public class SmartDeviceListActivity extends AppCompatActivity {

    TextView ssid;
    EditText ssidPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smart_devices_list);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        ssid = findViewById(R.id.ssid);
        ssidPassword = findViewById(R.id.password);

      /*  ITuyaUser tuyaUser = new ITuyaUser() {
            @Nullable
            @Override
            public User getUser() {
                return null;
            }

            @Override
            public boolean isLogin() {
                return false;
            }

            @Override
            public void loginWithPhone(String countryCode, String phone, String code, ILoginCallback callback) {

            }

            @Override
            public void loginWithEmail(String countryCode, String email, String passwd, ILoginCallback callback) {

            }

            @Override
            public void loginWithPhonePassword(String countryCode, String phone, String passwd, ILoginCallback callback) {

            }

            @Override
            public void getEmailValidateCode(String countryCode, String email, IValidateCallback callback) {

            }

            @Override
            public void registerAccountWithEmail(String countryCode, String email, String passwd, IRegisterCallback callback) {

            }

            @Override
            public void registerAccountWithPhone(String countryCode, String phone, String passwd, String code, IRegisterCallback callback) {

            }

            @Override
            public void resetEmailPassword(String countryCode, String email, String emailCode, String passwd, IResetPasswordCallback callback) {

            }

            @Override
            public void resetPhonePassword(String countryCode, String phone, String code, String newPasswd, IResetPasswordCallback callback) {

            }

            @Override
            public void logout(ILogoutCallback callback) {

            }

            @Override
            public void getValidateCode(String countryCode, String phoneNumber, IValidateCallback callback) {

            }

            @Override
            public void getRegisterEmailValidateCode(String countryCode, String email, IResultCallback callback) {

            }

            @Override
            public void registerAccountWithEmail(String countryCode, String email, String passwd, String code, IRegisterCallback callback) {

            }

            @Override
            public void reRickName(String name, IReNickNameCallback callback) {

            }

            @Override
            public void loginByTwitter(String countryCode, String key, String secret, ILoginCallback callback) {

            }

            @Override
            public void loginByQQ(String countryCode, String userId, String accessToken, ILoginCallback callback) {

            }

            @Override
            public void loginByWechat(String countryCode, String code, ILoginCallback callback) {

            }

            @Override
            public void loginByFacebook(String countryCode, String token, ILoginCallback callback) {

            }

            @Override
            public boolean removeUser() {
                return false;
            }

            @Override
            public void checkPhoneCode(String countryCode, String phoneNumber, String code, ICheckAccountCallback callback) {

            }

            @Override
            public void checkEmailPassword(String pwd, ICheckAccountCallback callback) {

            }

            @Override
            public void loginWithUid(String countryCode, String uid, String passwd, ILoginCallback callback) {

            }

            @Override
            public void loginOrRegisterWithUid(String countryCode, String uid, String passwd, ILoginCallback callback) {

            }

            @Override
            public void loginOrRegisterWithUid(String countryCode, String uid, String passwd, boolean isCreateHome, IUidLoginCallback callback) {

            }

            @Override
            public void registerAccountWithUid(String countryCode, String uid, String passwd, IRegisterCallback callback) {

            }

            @Override
            public boolean saveUser(User user) {
                return false;
            }

            @Override
            public void sendBindVerifyCode(String countryCode, String phoneNumber, IResultCallback callback) {

            }

            @Override
            public void bindMobile(String countryCode, String phoneNumber, String code, IResultCallback callback) {

            }

            @Override
            public void updateTimeZone(String timezoneId, IResultCallback callback) {

            }

            @Override
            public void setTempUnit(TempUnitEnum unit, IResultCallback callback) {

            }

            @Override
            public void uploadUserAvatar(File file, IBooleanCallback callback) {

            }

            @Override
            public boolean checkVersionUpgrade() {
                return false;
            }

            @Override
            public void upgradeVersion(IResultCallback callback) {

            }

            @Override
            public void cancelAccount(IResultCallback callback) {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void switchUserRegion(String region, ILoginCallback callback) {

            }

            @Override
            public void sendVerifyCodeWithUserName(String userName, String region, String countryCode, int type, IResultCallback callback) {

            }

            @Override
            public void checkCodeWithUserName(String userName, String region, String countryCode, String code, int type, IResultCallback callback) {

            }

            @Override
            public void registerWithUserName(String userName, String region, String countryCode, String code, String password, IRegisterCallback callback) {

            }

            @Override
            public void getRegionListWithCountryCode(String countryCode, IGetRegionCallback callback) {

            }

            @Override
            public void queryDomainByBizCodeAndKey(String bizCode, String key, IQurryDomainCallback callback) {

            }

            @Override
            public String queryDomainByBizCodeAndKeyFromCache(String bizCode, String key) {
                return null;
            }

            @Override
            public void queryAllBizDomains(IQurryDomainCallback callback) {

            }

            @Override
            public void getCommonServices(ICommonConfigCallback callback) {

            }
        };*/
       /* DevicesBean deviceBean = new DevicesBean();
        String devId = deviceBean.setDevId("v");*/
      /*  ITuyaDevice tuyaDevice = new ITuyaDevice() {
            @Override
            public void removeDevice(IResultCallback callback) {

            }

            @Override
            public void renameDevice(String name, IResultCallback callback) {

            }

            @Override
            public void publishDps(String dps, IResultCallback callback) {

            }

            @Override
            public void publishDps(String dps, TYDevicePublishModeEnum publishModeEnum, IResultCallback callback) {

            }

            @Override
            public void registerDevListener(IDevListener listener) {

            }

            @Override
            public void unRegisterDevListener() {

            }

            @Override
            public void getDp(String dpId, IResultCallback callback) {

            }

            @Override
            public void getDpList(List<String> dpId, IResultCallback callback) {

            }

            @Override
            public void resetFactory(IResultCallback callback) {
                callback.onSuccess();
            }

            @Override
            public void getDeviceProperty(IPropertyCallback<Map> callback) {

            }

            @Override
            public void saveDeviceProperty(String code, String value, IResultCallback callback) {

            }

            @Override
            public void getDataPointStat(DataPointTypeEnum type, long startTime, int number, String dpId, IGetDataPointStatCallback callback) {

            }

            @Override
            public void queryData(String data, IResultCallback callback) {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void requestWifiSignal(WifiSignalListener listener) {

            }

            @Override
            public void getInitiativeQueryDpsInfoWithDpsArray(List<Integer> dps, IResultCallback callback) {

            }

            @Override
            public void registerWarnMessageListener(IWarningMsgListener iWarningMsgListener) {

            }
        }*/
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String ssidStr = info.getSSID();
        ssid.setText(ssidStr);
       /* String token = TuyaActivator.


        ITuyaSmartActivatorListener listener = new ITuyaSmartActivatorListener() {
            @Override
            public void onError(String errorCode, String errorMsg) {

            }

            @Override
            public void onActiveSuccess(DevicesBean devResp) {

            }

            @Override
            public void onStep(String step, Object data) {

            }
        };*/



    }
}
