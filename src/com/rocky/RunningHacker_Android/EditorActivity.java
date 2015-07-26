package com.rocky.RunningHacker_Android;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class EditorActivity extends Activity {
    private EditText tvScreen;

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_editor);
        this.tvScreen = ((EditText) findViewById(R.id.etEditor));
        this.tvScreen.setTypeface(Typeface.MONOSPACE); //等宽字体
        String str = Utils.getStringFromPrefs(this, "notepad.text");
        if (str != null)
            this.tvScreen.setText(str);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.activity_editor, paramMenu);
        return true;
    }

    protected void onDestroy() {
        Utils.setStringInPrefs(this, this.tvScreen.getText().toString(), "notepad.text");
        super.onDestroy();
    }
}