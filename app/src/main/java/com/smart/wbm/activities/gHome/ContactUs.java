package com.smart.wbm.activities.gHome;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smart.wbm.R;
import com.smart.wbm.utils.ApiKeys;

public class ContactUs extends AppCompatActivity {

    WebView aboutWeb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutWeb = findViewById(R.id.aboutWeb);
        aboutWeb.setWebViewClient(new WebViewClient());
        WebSettings webSettings = aboutWeb.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        aboutWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
            }
        });
        aboutWeb.loadUrl(ApiKeys.CONTACTUS);
    }


}
