package hakcrun;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import com.i273.hackrun.Classes.Utils;

public class EditorActivity extends Activity {
    private EditText tvScreen;

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2130903041);
        this.tvScreen = ((EditText) findViewById(2131296258));
        this.tvScreen.setTypeface(Typeface.MONOSPACE); //等宽字体
        String str = Utils.getStringFromPrefs(this, "notepad.text");
        if (str != null)
            this.tvScreen.setText(str);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131230721, paramMenu);
        return true;
    }

    protected void onDestroy() {
        Utils.setStringInPrefs(this, this.tvScreen.getText().toString(), "notepad.text");
        super.onDestroy();
    }
}