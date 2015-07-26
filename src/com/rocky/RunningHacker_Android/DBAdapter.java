package com.rocky.RunningHacker_Android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Luoqi on 2015/1/5.
 */
public class DBAdapter {
    private static final String DATABASE_NAME = "runningHacker_DB";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBAdapter";
    private DatabaseHelper DBHelper;
    private final Context context;
    private SQLiteOpenHelper db;

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }
    public DBAdapter(Context context) {
        this.context = context;
        this.DBHelper = new DatabaseHelper(this.context);
    }

    public void  close() {
        this.DBHelper.close();
    }





    public static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("DBAdapter","数据库升级自版本"+oldVersion+"至版本"+newVersion+"，这将损坏之前的所有数据");
            db.execSQL("drop table if exists contacts");
            onCreate(db);
        }
    }
}
