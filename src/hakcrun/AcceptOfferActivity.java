package hakcrun;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AcceptOfferActivity extends Activity {
    public void clickAcceptOffer(View paramView) {
        Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        localEditor.putInt("hr__current_level", 1);
        localEditor.commit();
        Toast.makeText(this, "目标达成\n\n你成功的接受了雇主的提议。\n\n正在向关卡 1 前进", 1).show();
        Intent localIntent = new Intent();
        localIntent.putExtra("intent_parm_id3", "intent_parm_id4");
        setResult(-1, localIntent);
        finish();
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2130903040);
        ((TextView) findViewById(2131296256)).setTypeface(Typeface.MONOSPACE);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131230720, paramMenu);
        return true;
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.AcceptOfferActivity
 * JD-Core Version:    0.6.0
 */