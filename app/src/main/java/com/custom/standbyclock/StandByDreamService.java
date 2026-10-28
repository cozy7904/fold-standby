package com.custom.standbyclock;

import android.annotation.SuppressLint;
import android.service.dreams.DreamService;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.graphics.Color;

public class StandByDreamService extends DreamService {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 버튼 터치 및 스와이프 조작 허용
        setInteractive(true);
        setFullscreen(true);
        setScreenBright(true);

        webView = new WebView(this);
        webView.setBackgroundColor(Color.BLACK);
        
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.loadUrl("file:///android_asset/index.html");
        setContentView(webView);
    }

    @Override
    public void onDreamingStarted() {
        super.onDreamingStarted();
        if (webView != null) {
            webView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
        }
    }

    @Override
    public void onDetachedFromWindow() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDetachedFromWindow();
    }
}
