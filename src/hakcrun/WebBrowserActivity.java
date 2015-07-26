package hakcrun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.i273.hackrun.Classes.Utils;

import java.util.Locale;
import java.util.regex.Pattern;

public class WebBrowserActivity extends Activity {
    OnEditorActionListener locationListener = new OnEditorActionListener() {
        public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent) {
            String str = ((EditText) WebBrowserActivity.this.findViewById(2131296277)).getText().toString();
            WebBrowserActivity.this.surf(str);
            return true;
        }
    };

    private void displayOfflinePage(String paramString) {
        ((WebView) findViewById(2131296278)).loadUrl("file:///android_asset/websites/" + paramString + ".html");
    }

    @SuppressLint({"DefaultLocale"})
    private void surf(String paramString) {
        if ((paramString == null) || (paramString.equals(null)) || (paramString.equals("")) || (paramString.equals("null"))) {
            Utils.displayAlert(this, "URL无效", "请检查url然后再试", "ok");
            return;
        }
        if (paramString.toLowerCase(Locale.US).startsWith("http://"))
            paramString = paramString.substring(7);
        if (paramString.split(Pattern.quote(".")).length == 1)
            paramString = "http://www." + paramString + ".com";
        while (!Utils.isWebsiteValid(paramString)) {
            Utils.displayAlert(this, "URL无效", "请检查url然后再试", "ok");
            return;
            if (paramString.split(Pattern.quote(".")).length == 2) {
                paramString = "http://www." + paramString;
                continue;
            }
            if ((paramString.startsWith("http://")) || (paramString.split(Pattern.quote(".")).length != 3))
                continue;
            paramString = "http://" + paramString;
        }
        EditText localEditText = (EditText) findViewById(2131296277);
        localEditText.setText(paramString);
        if (Utils.isConnected(this))
            ((WebView) findViewById(2131296278)).loadUrl(paramString);
        while (true) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(localEditText.getWindowToken(), 0);
            return;
            String str = "";
            if (paramString.endsWith("php.pujiahh.com/overnitedynamite/"))
                str = "od__index";
            if (paramString.endsWith("php.pujiahh.com/reusingnature/"))
                str = "rn__index";
            if (paramString.endsWith("php.pujiahh.com/gotonote/"))
                str = "gn__index";
            if (paramString.endsWith("php.pujiahh.com/vrgb/"))
                str = "vr__index";
            if (paramString.endsWith("php.pujiahh.com/alienconspiracytheories/"))
                str = "ac__index";
            if (str.equals(""))
                continue;
            displayOfflinePage(str);
        }
    }

    public void clickGo(View paramView) {
        surf(((EditText) findViewById(2131296277)).getText().toString());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2130903047);
        ((EditText) findViewById(2131296277)).setOnEditorActionListener(this.locationListener);
        WebView localWebView = (WebView) findViewById(2131296278);
        localWebView.setWebViewClient(new WebViewClient());
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.getSettings().setBuiltInZoomControls(true);
        localWebView.getSettings().setSupportZoom(true);
        localWebView.getSettings().setLoadWithOverviewMode(true);
        localWebView.getSettings().setUseWideViewPort(true);
        localWebView.requestFocus();
        localWebView.getSettings().setLightTouchEnabled(true);
        localWebView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
                switch (paramMotionEvent.getAction()) {
                    default:
                    case 0:
                    case 1:
                }
                while (true) {
                    return false;
                    if (paramView.hasFocus())
                        continue;
                    paramView.requestFocus();
                }
            }
        });
        String str = getIntent().getStringExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN");
        if (str == null)
            str = "";
        if (!str.equals(""))
            surf(str);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131230726, paramMenu);
        return true;
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.WebBrowserActivity
 * JD-Core Version:    0.6.0
 */