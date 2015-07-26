package hakcrun;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import com.i273.hackrun.Classes.DBAdapter;
import com.i273.hackrun.Classes.Utils;

public class StatusActivity extends Activity {
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2130903045);
        EditText localEditText = (EditText) findViewById(2131296275);
        Utils.changeFont(getApplicationContext(), localEditText, "normal");
        int i = Utils.getIntFromPrefs(getApplicationContext(), "hr__current_level");
        String[] arrayOfString = new DBAdapter(getApplicationContext()).getAchievements(i);
        localEditText.setText("");
        for (int j = 0; ; j++) {
            if (j >= arrayOfString.length) {
                if (localEditText.getText().equals(""))
                    localEditText.setText("你暂时还没有达成任何成就。返回主菜单开始你的探险吧！");
                return;
            }
            localEditText.append(String.valueOf(j + 1) + ". " + arrayOfString[j] + "\n\n");
        }
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131230725, paramMenu);
        return true;
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.StatusActivity
 * JD-Core Version:    0.6.0
 */