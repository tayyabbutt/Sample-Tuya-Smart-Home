package com.wbm.smart.activities.gHome;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wbm.smart.R;
import com.wbm.smart.utils.ApiKeys;

public class AboutUs extends AppCompatActivity {

    WebView aboutWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutWeb = findViewById(R.id.aboutWeb);
        aboutWeb.setWebViewClient(new CustomWebViewClient());

        aboutWeb.getSettings().setJavaScriptEnabled(true);
        aboutWeb.getSettings().setDomStorageEnabled(true);
        aboutWeb.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        aboutWeb.loadUrl(ApiKeys.BASE_URL+"aboutUs");

    }

    private class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            webview.setVisibility(webview.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            view.setVisibility(aboutWeb.VISIBLE);
            super.onPageFinished(view, url);

        }
    }

}
