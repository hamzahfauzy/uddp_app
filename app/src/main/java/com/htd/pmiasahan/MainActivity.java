package com.htd.pmiasahan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.htd.pmiasahan.R;

public class MainActivity extends AppCompatActivity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        myWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setAllowContentAccess(true);

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // When user clicks a hyperlink, load in the existing WebView
                if(url.contains("maps")) {
                    Uri location = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, location);
                    startActivity(intent);
                    return false;
                }
                view.loadUrl(url);
                return true;
            }
        });

        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.loadUrl("file:///android_asset/index.html");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (myWebView.canGoBack()) {
                        myWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}