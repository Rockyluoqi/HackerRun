package hakcrun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.i273.hackrun.Classes.Utils;

public class WebTemplateActivity extends Activity {
    private void surf(String paramString) {
        if (!Utils.isConnected(getApplicationContext()))
            Utils.displayAlert(this, "No Internet Connection", "You are not connected to the internet and may not be able to view the content on this screen. Please ensure you have an active connection.", "ok");
        do
            return;
        while ((paramString == null) || (paramString == ""));
        ((WebView) findViewById(2131296280)).loadUrl(paramString);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2130903048);
        WebView localWebView = (WebView) findViewById(2131296280);
        localWebView.setWebViewClient(new WebViewClient());
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.getSettings().setBuiltInZoomControls(true);
        localWebView.getSettings().setSupportZoom(true);
        localWebView.getSettings().setLoadWithOverviewMode(true);
        localWebView.getSettings().setUseWideViewPort(true);
        surf(getIntent().getStringExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN"));
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131230727, paramMenu);
        return true;
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.WebTemplateActivity
 * JD-Core Version:    0.6.0
 */