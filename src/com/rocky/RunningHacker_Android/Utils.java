package com.rocky.RunningHacker_Android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Luoqi on 2015/1/5.
 */
public class Utils {

    public static int getIntFromPrefs(Context context,String string) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(string,0);
    }

    public static String getStringFromPrefs(Context context,String string) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(string,"");
    }

    public static void setIntInPrefs(Context context, int paramInt, String string) {
        SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        localEditor.putInt(string, paramInt);
        localEditor.commit();
    }

    public static void setStringInPrefs(Context context, String string1, String string2) {
        SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        localEditor.putString(string2, string1);
        localEditor.commit();
    }


}
