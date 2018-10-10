package com.ilab.user.e_lora.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ilab.user.e_lora.R;

public class ilabwebSite extends AppCompatActivity {
    //views
    private WebView ilab_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilabweb_site);

        ilab_webview = findViewById(R.id.ilab_webview);
        WebSettings webSettings = ilab_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        ilab_webview.loadUrl("http://www.ilabafrica.ac.ke/index.php/challenge-based-learning/");
        ilab_webview.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(ilab_webview.canGoBack()){
            ilab_webview.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
