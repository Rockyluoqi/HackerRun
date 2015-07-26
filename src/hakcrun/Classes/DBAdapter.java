package hakcrun.Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBAdapter {
    private static final String DATABASE_NAME = "hackrun_dd.s3db";
    private static final int DATABASE_VERSION = 2;
    private static final String TAG = "DBAdapter";
    private DatabaseHelper DBHelper;
    private final Context context;
    private SQLiteDatabase db;

    public DBAdapter(Context paramContext) {
        this.context = paramContext;
        this.DBHelper = new DatabaseHelper(this.context);
    }

    private void CopyDB(InputStream paramInputStream, OutputStream paramOutputStream)
            throws IOException {
        byte[] arrayOfByte = new byte[1024];
        while (true) {
            int i = paramInputStream.read(arrayOfByte);
            if (i <= 0) {
                paramInputStream.close();
                paramOutputStream.close();
                return;
            }
            paramOutputStream.write(arrayOfByte, 0, i);
        }
    }

    public void close() {
        this.DBHelper.close();
    }

    public String[] getAchievements(int paramInt) {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add("你接受了你的雇主的提议。\n\n你好，\n\n你可能还不知道我是谁，但我已经悄悄观察你一阵子了。别担心，我跟当局没什么关系。我想要雇佣你做一些非常重要的事情。我需要一个组织内部电脑里面的某些信息，相信你知道应该怎么获得它们。接受我的提议，我会给你相应的巨额奖励。\n\n你未来的雇主");
        String str = "SELECT message FROM goal_messages " + " WHERE level < ? AND message_type = 'achievement' ORDER BY level ";
        open();
        String[] arrayOfString = new String[1];
        arrayOfString[0] = String.valueOf(paramInt + 1);
        Cursor localCursor = this.db.rawQuery(str, arrayOfString);
        for (boolean bool = localCursor.moveToFirst(); ; bool = localCursor.moveToNext()) {
            if (!bool) {
                localCursor.close();
                close();
                if ((Utils.isAppFree(this.context)) && (Utils.getIntFromPrefs(this.context, "end_of_free_game") == 1))
                    localArrayList.add("你已经完成了免费版本的所有关卡。下载虚拟入侵的完整版本，还有超过50个关卡在等你哦！");
                return (String[]) localArrayList.toArray(new String[localArrayList.size()]);
            }
            localArrayList.add(localCursor.getString(0));
        }
    }

    public String getDBName() {
        try {
            String str1 = getSystemContent("db_name");
            if ((str1 != null) && (!str1.equals(""))) {
                String str2 = "db_name=" + str1;
                return str2;
            }
        } catch (Exception localException) {
            Log.e("DBAdapter", localException.getMessage());
            return "";
        }
        return "";
    }

    public String getFileContents(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt) {
        String[] arrayOfString = new String[4];
        arrayOfString[0] = paramString1;
        arrayOfString[1] = paramString2;
        arrayOfString[2] = paramString3;
        arrayOfString[3] = String.valueOf(paramString4);
        String str = getStringFromArray("SELECT file_contents FROM files WHERE system_code=? AND user=? AND folder=? AND file_name=? ", arrayOfString);
        if ((str == null) || (str.equals("")))
            str = "";
        do
            return str;
        while (paramInt >= getIntFromArray("SELECT level FROM files WHERE system_code=? AND user=? AND folder=? AND file_name=? ", arrayOfString));
        return "";
    }

    public String[] getFilesBySDUF(String paramString1, int paramInt, String paramString2, String paramString3) {
        ArrayList localArrayList = new ArrayList();
        String str1 = "SELECT file_name, mailbox FROM files WHERE system_code=? AND level<=? AND user=? AND folder=? ORDER BY file_name ";
        if ((paramString1.startsWith("local")) || (paramString1.startsWith("trace|emp|mail")))
            str1 = "SELECT file_name, mailbox FROM files WHERE system_code=? AND level<=? AND user=? AND folder=? ";
        open();
        String[] arrayOfString = new String[4];
        arrayOfString[0] = paramString1;
        arrayOfString[1] = String.valueOf(paramInt);
        arrayOfString[2] = paramString2;
        arrayOfString[3] = paramString3;
        Cursor localCursor = this.db.rawQuery(str1, arrayOfString);
        boolean bool = localCursor.moveToFirst();
        if (!bool) {
            localCursor.close();
            close();
            return (String[]) localArrayList.toArray(new String[localArrayList.size()]);
        }
        String str2 = localCursor.getString(0);
        String str3 = localCursor.getString(1);
        String str4;
        if (!str3.equals(""))
            str4 = "";
        for (int i = 0; ; i++) {
            if (i >= 12 - str2.length()) {
                localArrayList.add(str2 + str4 + str3);
                bool = localCursor.moveToNext();
                break;
            }
            str4 = str4 + " ";
        }
    }

    String getGoalMessage(int paramInt, String paramString) {
        String[] arrayOfString = new String[2];
        arrayOfString[0] = String.valueOf(paramInt);
        arrayOfString[1] = paramString;
        return getStringFromArray("SELECT message FROM goal_messages WHERE level = ? AND message_type = ? ", arrayOfString);
    }

    public String getHelpCommands(String paramString1, String paramString2) {
        String str1 = paramString1;
        int i = PreferenceManager.getDefaultSharedPreferences(this.context).getInt("hr__current_level", -1);
        open();
        Cursor localCursor = this.db.rawQuery("SELECT command, help_command, desc_iphone, desc_ipad, level FROM commands WHERE system=? ORDER BY command ", new String[]{paramString2});
        boolean bool = localCursor.moveToFirst();
        if (!bool) {
            localCursor.close();
            close();
            return str1;
        }
        if (localCursor.getString(0).equals("ping")) ;
        do {
            bool = localCursor.moveToNext();
            break;
        }
        while (i < localCursor.getInt(4));
        String str2 = localCursor.getString(1);
        String str3 = localCursor.getString(2);
        String str4 = "";
        if (str2.length() >= 8)
            for (int k = 0; ; k++) {
                if (k >= 8) {
                    str1 = str1 + "\n " + str2 + "| " + str4 + str3;
                    break;
                }
                str4 = str4 + " ";
            }
        for (int j = 0; ; j++) {
            if (j >= 8 - str2.length()) {
                str1 = str1 + "\n " + str2 + str4 + str3;
                break;
            }
            str4 = str4 + " ";
        }
    }

    String getHelpMenu(String paramString) {
        String str1 = paramString;
        String str2 = getSystemMessage(paramString, "help");
        if (paramString.equals("local|hacker|mail")) {
            str2 = getSystemMessage("local_mail", "help");
            paramString = "mail";
        }
        while (true) {
            if ((str2 == null) || (str2.equals("")))
                str2 = paramString + " help menu";
            if (paramString.equals("alt"))
                str2 = "Alternate Commands";
            String str3 = getHelpCommands(str2, paramString);
            if (!paramString.equals(str1))
                str3 = getHelpCommands(str3, str1);
            return str3;
            if (paramString.endsWith("|mail")) {
                str2 = str2.replace("[LOGIN_NAME]", paramString.split("\\|", -1)[1]);
                paramString = "mail";
                continue;
            }
            if (paramString.startsWith("ws_")) {
                str2 = str2.replace("[LOGIN_NAME]", paramString.replace("ws_", ""));
                paramString = "workstation";
                continue;
            }
            if (paramString.startsWith("reps")) {
                str2 = str2.replace("[LOGIN_NAME]", paramString.split("\\|", -1)[1]);
                paramString = "reps";
                continue;
            }
            if ((paramString.startsWith("govt")) || (!paramString.startsWith("ufo_")))
                continue;
            str2 = paramString.replace("[LOGIN_NAME]", paramString.replace("ufo_", ""));
            paramString = "ufo";
        }
    }

    public int getIntFromArray(String paramString, String[] paramArrayOfString)
            throws SQLException {
        int i = -1;
        open();
        Cursor localCursor = this.db.rawQuery(paramString, paramArrayOfString);
        if (localCursor != null)
            localCursor.moveToFirst();
        try {
            int k = Integer.parseInt(localCursor.getString(0));
            i = k;
            localCursor.close();
            close();
            return i;
        } catch (Exception localException1) {
            while (true)
                try {
                    int j = localCursor.getInt(0);
                    i = j;
                } catch (Exception localException2) {
                    i = -1;
                }
        }
    }

    public String getPassword(String paramString1, String paramString2) {
        return getStringFromArray("SELECT password FROM employees WHERE system_code=? AND first_name=?", new String[]{paramString1, paramString2});
    }

    String getPromptBySystem(String paramString) {
        return getStringFromArray("SELECT prompt FROM systems WHERE system_code=? ", new String[]{paramString});
    }

    public String getStringFromArray(String paramString, String[] paramArrayOfString)
            throws SQLException {
        Object localObject1 = "";
        open();
        Cursor localCursor = this.db.rawQuery(paramString, paramArrayOfString);
        int i;
        if (localCursor != null) {
            localCursor.moveToFirst();
            i = 0;
        }
        try {
            while (true) {
                if (i >= localCursor.getColumnCount()) {
                    if (((String) localObject1).endsWith("|")) {
                        String str2 = ((String) localObject1).substring(0, -1 + ((String) localObject1).length());
                        localObject1 = str2;
                    }
                    localCursor.close();
                    close();
                    return ((String) localObject1).replace("[PAUSE5]", "");
                }
                String str1 = localObject1 + localCursor.getString(i) + "|";
                localObject1 = str1;
                i++;
            }
        } catch (Exception localException) {
            while (true) {
                localObject1 = "";
                localCursor.close();
            }
        } finally {
            localCursor.close();
        }
        throw localObject2;
    }

    public String getStringsWithInt(String paramString, int paramInt)
            throws SQLException {
        String[] arrayOfString = new String[1];
        arrayOfString[0] = String.valueOf(paramInt);
        return getStringFromArray(paramString, arrayOfString);
    }

    String getSuccessInfo(int paramInt) {
        return getStringsWithInt("SELECT level, system_code, prompt, employee_first_name, input FROM goals WHERE level=? ", paramInt);
    }

    public String getSystemContent(String paramString) {
        String str = getStringFromArray("select content from system_table where name='" + paramString + "'", null);
        if (str == null)
            str = "";
        return str;
    }

    public String getSystemMessage(String paramString1, String paramString2) {
        if (paramString1.equals("trace|emp|mail"))
            paramString1 = "trace|emp|mail";
        while (true) {
            return getStringFromArray("SELECT message FROM system_messages WHERE system_code=? AND message_type=? ", new String[]{paramString1, paramString2});
            if (paramString1.endsWith("|mail")) {
                paramString1 = "mail";
                continue;
            }
            if (paramString1.startsWith("ws_")) {
                paramString1 = "workstation";
                continue;
            }
            if ((!paramString1.startsWith("govt_")) || (paramString1.endsWith("robot")))
                continue;
            paramString1 = "govt";
        }
    }

    public String getUsernameByPassword(String paramString1, String paramString2) {
        return getStringFromArray("SELECT first_name FROM employees WHERE system_code=? AND password=?", new String[]{paramString1, paramString2});
    }

    // ERROR //
    public boolean initDB(String paramString) {
        // Byte code:
        //   0: new 67	java/lang/StringBuilder
        //   3: dup
        //   4: ldc_w 329
        //   7: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   10: aload_1
        //   11: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   14: ldc_w 331
        //   17: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   20: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   23: astore_2
        //   24: new 333	java/io/File
        //   27: dup
        //   28: aload_2
        //   29: invokespecial 334	java/io/File:<init>	(Ljava/lang/String;)V
        //   32: invokevirtual 337	java/io/File:exists	()Z
        //   35: ifne +60 -> 95
        //   38: aload_0
        //   39: invokevirtual 339	com/i273/hackrun/Classes/DBAdapter:getDBName	()Ljava/lang/String;
        //   42: pop
        //   43: aload_0
        //   44: aload_0
        //   45: getfield 27	com/i273/hackrun/Classes/DBAdapter:context	Landroid/content/Context;
        //   48: invokevirtual 345	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
        //   51: ldc_w 347
        //   54: invokevirtual 352	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
        //   57: new 354	java/io/FileOutputStream
        //   60: dup
        //   61: new 67	java/lang/StringBuilder
        //   64: dup
        //   65: aload_2
        //   66: invokestatic 75	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
        //   69: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   72: ldc 8
        //   74: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   77: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   80: invokespecial 355	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   83: invokespecial 357	com/i273/hackrun/Classes/DBAdapter:CopyDB	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
        //   86: ldc 14
        //   88: ldc_w 359
        //   91: invokestatic 362	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   94: pop
        //   95: iconst_1
        //   96: ireturn
        //   97: astore 15
        //   99: ldc 14
        //   101: new 67	java/lang/StringBuilder
        //   104: dup
        //   105: ldc_w 364
        //   108: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   111: aload 15
        //   113: invokevirtual 365	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
        //   116: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   122: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   125: pop
        //   126: new 333	java/io/File
        //   129: dup
        //   130: aload_2
        //   131: invokespecial 334	java/io/File:<init>	(Ljava/lang/String;)V
        //   134: invokevirtual 337	java/io/File:exists	()Z
        //   137: ifne -42 -> 95
        //   140: aload_0
        //   141: invokevirtual 339	com/i273/hackrun/Classes/DBAdapter:getDBName	()Ljava/lang/String;
        //   144: pop
        //   145: aload_0
        //   146: aload_0
        //   147: getfield 27	com/i273/hackrun/Classes/DBAdapter:context	Landroid/content/Context;
        //   150: invokevirtual 345	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
        //   153: ldc_w 347
        //   156: invokevirtual 352	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
        //   159: new 354	java/io/FileOutputStream
        //   162: dup
        //   163: new 67	java/lang/StringBuilder
        //   166: dup
        //   167: aload_2
        //   168: invokestatic 75	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
        //   171: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   174: ldc 8
        //   176: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   182: invokespecial 355	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   185: invokespecial 357	com/i273/hackrun/Classes/DBAdapter:CopyDB	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
        //   188: ldc 14
        //   190: ldc_w 367
        //   193: invokestatic 362	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   196: pop
        //   197: iconst_1
        //   198: ireturn
        //   199: astore 9
        //   201: ldc 14
        //   203: new 67	java/lang/StringBuilder
        //   206: dup
        //   207: ldc_w 364
        //   210: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   213: aload 9
        //   215: invokevirtual 365	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
        //   218: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   224: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   227: pop
        //   228: iconst_0
        //   229: ireturn
        //   230: astore 13
        //   232: ldc 14
        //   234: new 67	java/lang/StringBuilder
        //   237: dup
        //   238: ldc_w 369
        //   241: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   244: aload 13
        //   246: invokevirtual 370	java/io/IOException:getMessage	()Ljava/lang/String;
        //   249: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   255: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   258: pop
        //   259: goto -133 -> 126
        //   262: astore_3
        //   263: ldc 14
        //   265: new 67	java/lang/StringBuilder
        //   268: dup
        //   269: ldc_w 372
        //   272: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   275: aload_3
        //   276: invokevirtual 160	java/lang/Exception:getMessage	()Ljava/lang/String;
        //   279: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   285: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   288: pop
        //   289: goto -163 -> 126
        //   292: astore 7
        //   294: ldc 14
        //   296: new 67	java/lang/StringBuilder
        //   299: dup
        //   300: ldc_w 369
        //   303: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   306: aload 7
        //   308: invokevirtual 370	java/io/IOException:getMessage	()Ljava/lang/String;
        //   311: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   314: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   317: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   320: pop
        //   321: goto -93 -> 228
        //   324: astore 5
        //   326: ldc 14
        //   328: new 67	java/lang/StringBuilder
        //   331: dup
        //   332: ldc_w 372
        //   335: invokespecial 78	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   338: aload 5
        //   340: invokevirtual 160	java/lang/Exception:getMessage	()Ljava/lang/String;
        //   343: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   349: invokestatic 166	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   352: pop
        //   353: goto -125 -> 228
        //
        // Exception table:
        //   from	to	target	type
        //   24	95	97	java/io/FileNotFoundException
        //   126	197	199	java/io/FileNotFoundException
        //   24	95	230	java/io/IOException
        //   24	95	262	java/lang/Exception
        //   126	197	292	java/io/IOException
        //   126	197	324	java/lang/Exception
    }

    public boolean isInputValid(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt) {
        if (paramString1.equals("ach")) ;
        int n;
        do {
            int i;
            do {
                do
                    return true;
                while ((paramString1.equals("alt")) || (paramString1.equals("answer")) || (paramString1.equals("clr")) || (paramString1.equals("hint")) || (paramString1.equals("notes")) || (paramString1.equals("tips")));
                i = getIntFromArray("SELECT level FROM commands WHERE system=? AND command=? AND (user=? OR user='all') ", new String[]{paramString2, paramString1, paramString4});
            }
            while ((i > 0) && (paramInt >= i));
            if (!paramString2.endsWith("|mail"))
                break;
            n = getIntFromArray("SELECT level FROM commands WHERE system=? AND command=? AND (user=? OR user='all') ", new String[]{"mail", paramString1, paramString4});
        }
        while ((n > 0) && (paramInt >= n));
        int j;
        do {
            do
                while (true) {
                    return false;
                    if (paramString2.startsWith("ws_")) {
                        int m = getIntFromArray("SELECT level FROM commands WHERE system=? AND command=? AND (user=? OR user='all') ", new String[]{"workstation", paramString1, paramString4});
                        if ((m > 0) && (paramInt >= m))
                            return true;
                    }
                    if (!paramString2.startsWith("reps"))
                        break;
                    int k = getIntFromArray("SELECT level FROM commands WHERE system=? AND command=? AND (user=? OR user='all') ", new String[]{"reps", paramString1, paramString4});
                    if ((k > 0) && (paramInt >= k))
                        return true;
                }
            while (!paramString2.startsWith("ufo_"));
            j = getIntFromArray("SELECT level FROM commands WHERE system=? AND command=? AND (user=? OR user='all') ", new String[]{"ufo", paramString1, paramString4});
        }
        while ((j <= 0) || (paramInt < j));
        return true;
    }

    public boolean login(String paramString1, String paramString2, String paramString3) {
        if (paramString1.equals("govt")) {
            if (getIntFromArray("SELECT count(*) FROM employees WHERE system_code=? AND password=? ", new String[]{paramString1, paramString3}) != 1)
                ;
        } else
            do {
                return true;
                if (!paramString1.equals("ufo"))
                    break;
            }
            while (getIntFromArray("SELECT count(*) FROM employees WHERE system_code=? AND first_name=? AND password=? ", new String[]{"ufo_" + paramString2, paramString2, paramString3}) == 1);
        do
            return false;
        while (getIntFromArray("SELECT count(*) FROM employees WHERE system_code=? AND first_name=? AND password=? ", new String[]{paramString1, paramString2, paramString3}) != 1);
        return true;
    }

    public int loginLevel(String paramString1, String paramString2) {
        if ((paramString1.equals("govt")) && (paramString2.equals("")))
            return 27;
        return getIntFromArray("SELECT login_level FROM employees WHERE system_code=? AND first_name=? ", new String[]{paramString1, paramString2});
    }

    public DBAdapter open()
            throws SQLException {
        this.db = this.DBHelper.getReadableDatabase();
        return this;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context paramContext) {
            super("hackrun_dd.s3db", null, 2);
        }

        public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
        }

        public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
            Log.w("DBAdapter", "数据库升级 自版本 " + paramInt1 + " 至版本 " + paramInt2 + "，这将会损坏所有旧数据");
            paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(paramSQLiteDatabase);
        }
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.Classes.DBAdapter
 * JD-Core Version:    0.6.0
 */