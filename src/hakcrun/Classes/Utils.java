package hakcrun.Classes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;

import java.util.Locale;

public class Utils {
    public static void VibratePhone(Context paramContext) {
        if (getIntFromPrefs(paramContext, "vibrate_phone") == 1)
            return;
        ((Vibrator) paramContext.getSystemService("vibrator")).vibrate(300L);//震动方法
    }

    public static void changeFont(Context paramContext, EditText paramEditText, String paramString) {
        if (paramString.equals("normal")) {
            paramEditText.setTextColor(-16711936);
            paramEditText.setBackgroundColor(-16777216);
        }
        int i;
        do
            while (true) {
                i = getIntFromPrefs(paramContext, "pref_text_color");
                if (i != 1)
                    break;
                paramEditText.setTextColor(-16777216);
                paramEditText.setBackgroundColor(-1);
                return;
                if (!paramString.equals("alien"))
                    continue;
                paramEditText.setTextColor(-65536);
                paramEditText.setBackgroundColor(-16777216);
            }
        while (i != 2);
        paramEditText.setTextColor(-1);
        paramEditText.setBackgroundColor(-16777216);
    }

    public static void displayAlert(Context paramContext, String paramString1, String paramString2, String paramString3) {
        new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).setIcon(17301543).setNegativeButton(17039370, null).show();
    }

    public static int getIntFromPrefs(Context paramContext, String paramString) {
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt(paramString, 0);
    }

    public static String getStringFromPrefs(Context paramContext, String paramString) {
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getString(paramString, "");
    }

    public static boolean isAppFree(Context paramContext) {
        return false;
    }

    public static boolean isConnected(Context paramContext) {
        try {
            NetworkInfo localNetworkInfo = ((ConnectivityManager) paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable())) {
                boolean bool = localNetworkInfo.isConnected();
                if (!bool) ;
            }
            for (int i = 1; ; i = 0)
                return i;
        } catch (Exception localException) {
            System.out.println("CheckConnectivity Exception:" + localException.getMessage());
            Log.e("connectivity", localException.toString());
        }
        return false;
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean isWebsiteValid(String paramString) {
        String str = paramString.toLowerCase(Locale.US);
        if (str.equals("http://php.pujiahh.com/overnitedynamite/")) ;
        do
            return true;
        while ((str.equals("http://php.pujiahh.com/reusingnature/")) || (str.equals("http://php.pujiahh.com/gotonote/")) || (str.equals("http://php.pujiahh.com/vrgb/")) || (str.equals("http://php.pujiahh.com/alienconspiracytheories/")) || (str.equals("http://www.axiselectricity.com")) || (str.equals("http://www.rentershelper.com")) || (str.equals("http://www.findsomeonelikeme.com")) || (str.equals("http://www.toucantelecom.com")) || (str.equals("http://www.zombieconspiracytheories.com")) || (str.equals("http://www.unitednationalities.com")) || (str.equals("http://www.nitedr.com")) || (str.equals("http://www.mymidnightrun.com")) || (str.equals("http://www.fobzilla.com")) || (str.equals("http://www.forgedtrust.com")) || (str.equals("http://www.mistxts.com")) || (str.equals("http://www.vampireconspiracytheories.com")));
        return false;
    }

    public static boolean resetGame(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean) {
        setIntInPrefs(paramContext, paramInt1, "hr__current_level");
        setIntInPrefs(paramContext, 0, "end_of_free_game");
        setIntInPrefs(paramContext, 1, "robot_version");
        setStringInPrefs(paramContext, "Active", "robot_status");
        setStringInPrefs(paramContext, "Active", "robot_coms_belt");
        setStringInPrefs(paramContext, "Active", "robot_coms_arms");
        setStringInPrefs(paramContext, "Active", "robot_coms_ramp");
        setStringInPrefs(paramContext, "Active", "robot_coms_load");
        return true;
    }

    public static void setIntInPrefs(Context paramContext, int paramInt, String paramString) {
        Editor localEditor = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
        localEditor.putInt(paramString, paramInt);
        localEditor.commit();
    }

    public static void setStringInPrefs(Context paramContext, String paramString1, String paramString2) {
        Editor localEditor = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
        localEditor.putString(paramString2, paramString1);
        localEditor.commit();
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.Classes.Utils
 * JD-Core Version:    0.6.0
 */