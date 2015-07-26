package com.rocky.RunningHacker_Android;

/**
 *
 */
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
        localEditor.putInt("rh_current_level", 1);
        localEditor.commit();
        //测试记录：这里的Toast乱码是为什么？
        Toast.makeText(this, "你成功的接受了雇主的提议。\n\n正在向关卡 1 前进", 1).show();
        Intent localIntent = new Intent();
        localIntent.putExtra("intent_parm_id3", "intent_parm_id4");
        setResult(-1, localIntent);
        finish();
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_accept_offer);
        ((TextView) findViewById(R.id.textViewInitialOffer)).setTypeface(Typeface.MONOSPACE);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.activity_accept_offer, paramMenu);
        return true;
    }
}