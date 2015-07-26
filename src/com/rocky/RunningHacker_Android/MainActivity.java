package com.rocky.RunningHacker_Android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
//    public static final String MESSAGE_PAGE_TO_OPEN;
    private static final String TAG = "MainActivity";
    private int currentLevel = 0;

    private void initApp() {
        this.currentLevel = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("rh_current_level",-1);
        if(this.currentLevel <= 0) {
            Utils.setIntInPrefs(getApplicationContext(),0,"rh_current_level");
            Utils.setStringInPrefs(getApplicationContext(),"popup","pref_popups_display");
            Log.w("MainActivity", new DBAdapter(this).getDatabaseName());
            startActivityForResult(new Intent(this, AcceptOfferActivity.class), 1001);
        }

        Log.w("MainActivity",new DBAdapter(this).getDatabaseName());
        TextView localTextView = (TextView)findViewById(R.id.textViewLevel);
        localTextView.setText("Level" + String.valueOf(this.currentLevel));
//

    }

    public void clickInformation(View paramView) {
        Intent localIntent = new Intent(this,null);
        //localIntent.putextra
        //startActivity(localIntent);
    }

    public void clickSettings(View view) {
//        startActivityForResult(new Intent(this,SettingsActivity.class),1003);
    }

    public void clickStart(View view) {
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.currentLevel = localSharedPreferences.getInt("rh_current_level",-1);
        if(this.currentLevel <= 1) {
            SharedPreferences.Editor localEditor = localSharedPreferences.edit();
            localEditor.putInt("rh_current_level",1);
            localEditor.commit();
        }

        startActivityForResult(new Intent(this,TerminalActivity.class),1002);
    }

   /* public void onActivityResult(int paramInt1,int paramInt2,Intent paramIntent) {
        super.onActivityResult(paramInt1,paramInt2,paramIntent);
        switch (paramInt1) {
            default:
            case 1001:
            case 1002:
            case 1003:
            case 1004:
        }

        do {
            do {
                do {
                    do {
                        return;
                        while(paramInt2 !=-1);
                        Log.w("MainActivity","returned from accept initial offer");
                        return
                    }
                }
            }
        }
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.TextViewHeader)).setTypeface(Typeface.MONOSPACE);
        ((TextView)findViewById(R.id.textViewLevel)).setTypeface(Typeface.MONOSPACE);
        TextView localTextView = (TextView)findViewById(R.id.TextViewVersion);
        localTextView.setTypeface(Typeface.MONOSPACE);
    }

    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initApp();
    }
}
