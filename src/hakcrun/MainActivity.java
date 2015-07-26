package hakcrun;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.feiwo.appwall.AppWallManager;
import com.i273.hackrun.Classes.DBAdapter;
import com.i273.hackrun.Classes.Utils;

public class MainActivity extends Activity {
    public static final String MESSAGE_PAGE_TO_OPEN = "com.i273.hackrun.MESSAGE_PAGE_TO_OPEN";
    private static final String TAG = "MainActivity";
    private int currentLevel = 0;
    private boolean freeButtonExists = false;

    private void initApp() {
        this.currentLevel = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("hr__current_level", -1);
        if (this.currentLevel <= 0) {
            Utils.setIntInPrefs(getApplicationContext(), 0, "end_of_free_game");
            Utils.setIntInPrefs(getApplicationContext(), 0, "hr__current_level");
            Utils.setIntInPrefs(getApplicationContext(), 0, "pref_text_color");
            Utils.setIntInPrefs(getApplicationContext(), 1, "robot_version");
            Utils.setIntInPrefs(getApplicationContext(), 0, "show_buy_button");
            Utils.setIntInPrefs(getApplicationContext(), 0, "show_end_pay_buttons");
            Utils.setIntInPrefs(getApplicationContext(), 0, "vibrate_phone");
            Utils.setStringInPrefs(getApplicationContext(), "popup", "pref_popups_display");
            new DBAdapter(getApplicationContext()).initDB(getPackageName());
            Log.w("MainActivity", new DBAdapter(this).getDBName());
            startActivityForResult(new Intent(this, AcceptOfferActivity.class), 1001);
            return;
        }
        Log.w("MainActivity", new DBAdapter(this).getDBName());
        TextView localTextView = (TextView) findViewById(2131296267);
        localTextView.setText("Level " + String.valueOf(this.currentLevel));
        if ((Utils.isAppFree(getApplicationContext())) && (this.currentLevel >= 16) && (Utils.getIntFromPrefs(getApplicationContext(), "end_of_free_game") == 1))
            localTextView.setText("End of Free Game");
        while (true) {
            Utils.getIntFromPrefs(getApplicationContext(), "show_end_pay_buttons");
            if ((this.freeButtonExists) || (!Utils.isAppFree(getApplicationContext())) || (Utils.getIntFromPrefs(getApplicationContext(), "show_buy_button") != 1))
                break;
            this.freeButtonExists = true;
            LayoutParams localLayoutParams = new LayoutParams(-1, -2);
            ImageButton localImageButton = new ImageButton(this);
            localImageButton.setId(273);
            localImageButton.setBackgroundColor(-16777216);
            localImageButton.setImageResource(2130837507);
            ((LinearLayout) findViewById(2131296261)).addView(localImageButton, 0, localLayoutParams);
            localImageButton.setOnClickListener(new OnClickListener() {
                public void onClick(View paramView) {
                    Intent localIntent = new Intent(MainActivity.this.getApplicationContext(), WebTemplateActivity.class);
                    localIntent.putExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN", "http://www.i273.com/HackRUN/GooglePlay/");
                    MainActivity.this.startActivity(localIntent);
                }
            });
            return;
            if ((Utils.isAppFree(getApplicationContext())) || (this.currentLevel < 52))
                continue;
            localTextView.setText("游戏结束\n敬请期待我们的下一部作品'Hack Run ZERO'！");
            Utils.setIntInPrefs(getApplicationContext(), 1, "show_end_pay_buttons");
        }
    }

    public void clickInformation(View paramView) {
        Intent localIntent = new Intent(this, WebTemplateActivity.class);
        localIntent.putExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN", "http://www.i273.com/apps/hackrun/minfo.html");
        startActivity(localIntent);
    }

    public void clickSettings(View paramView) {
        startActivityForResult(new Intent(this, SettingsActivity.class), 1003);
    }

    public void clickStart(View paramView) {
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.currentLevel = localSharedPreferences.getInt("hr__current_level", -1);
        if (this.currentLevel <= 1) {
            Editor localEditor = localSharedPreferences.edit();
            localEditor.putInt("hr__current_level", 1);
            localEditor.commit();
        }
        startActivityForResult(new Intent(this, TerminalActivity.class), 1002);
    }

    public void clickStatus(View paramView) {
        startActivityForResult(new Intent(this, StatusActivity.class), 1004);
    }

    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
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
                    do
                        return;
                    while (paramInt2 != -1);
                    Log.w("MainActivity", "returned from accept initial offer");
                    return;
                }
                while (paramInt2 != -1);
                Log.w("MainActivity", "returned from settings");
                return;
            }
            while (paramInt2 != -1);
            Log.w("MainActivity", "returned from settings");
            return;
        }
        while (paramInt2 != -1);
        Log.w("MainActivity", "returned from status");
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        AppWallManager.init(getApplicationContext(), "7tIXUAXre0Nt2aRtXXmxJgX0");
        setContentView(2130903042);
        ((TextView) findViewById(2131296260)).setTypeface(Typeface.MONOSPACE);
        ((TextView) findViewById(2131296267)).setTypeface(Typeface.MONOSPACE);
        TextView localTextView = (TextView) findViewById(2131296266);
        localTextView.setTypeface(Typeface.MONOSPACE);
        try {
            String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            if (Utils.isAppFree(getApplicationContext())) {
                localTextView.setText("Version " + str + " free");
                return;
            }
            localTextView.setText("Version " + str);
            return;
        } catch (Exception localException) {
            Log.e("MainActivity", localException.getMessage());
        }
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131230727, paramMenu);
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        AppWallManager.showAppWall(this);
    }

    protected void onResume() {
        super.onResume();
        initApp();
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.MainActivity
 * JD-Core Version:    0.6.0
 */