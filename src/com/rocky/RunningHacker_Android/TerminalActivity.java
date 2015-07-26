package com.rocky.RunningHacker_Android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by Luoqi on 2014/12/30.
 **/


public class TerminalActivity extends Activity{

    private String cPompt = "";
    private int promptEndPos = 0;
    private boolean settingText = false;
    private String termContents;//
    private EditText tvScreen;//Textview
    private boolean useInput = false;
    private String userInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal);
        this.tvScreen = (EditText)findViewById(R.id.editTextTerminal);
        this.tvScreen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence paramCharSequence, int paramStart, int paramCount, int paramAfter) {
                if(TerminalActivity.this.settingText) {
                    return;
                }

                TerminalActivity.this.tvScreen.setSelection(TerminalActivity.this.tvScreen.getText().length());
            }

            @Override
            public void onTextChanged(CharSequence paramCharSequence, int paramStart, int paramBefore, int paramCount) {
                if(TerminalActivity.this.settingText);
                do {
                    TerminalActivity.this.tvScreen.setSelection(TerminalActivity.this.tvScreen.getText().length());
                    TerminalActivity.this.useInput = true;
                    return;
                } while(paramStart >=TerminalActivity.this.promptEndPos);
//                TerminalActivity.this.useInput = false;
            }

            @Override
            public void afterTextChanged(Editable paramEditable) {
                if(TerminalActivity.this.settingText);
                int i;
                do {
                    return;
//                    if(!TerminalActivity.this.useInput) {
//                        TerminalActivity.this.displayText(true,TerminalActivity.this.termContents);
//                        return;
//                    }
                    boolean bool = paramEditable.toString().endsWith("\n");
                    i=0;
                    if(bool) {
                        TerminalActivity.this.settingText = true;
                        paramEditable = paramEditable.delete(-1 + paramEditable.toString().length(),paramEditable.toString().length());
                        TerminalActivity.this.settingText = false;
                        i = 1;
                    }

                    TerminalActivity.this.termContents = paramEditable.toString();
                    TerminalActivity.this.tvScreen.setSelection(TerminalActivity.this.tvScreen.getText().length());
                    String[] arrayOfString = paramEditable.toString().split(Pattern.quote("\n"),-1);
                    String str = arrayOfString[(-1+arrayOfString.length)];
                    TerminalActivity.this.userInput = str.replace(TerminalActivity.this.cPompt,"");
                    if(TerminalActivity.this.userInput.startsWith("localhost>"))
                        TerminalActivity.this.userInput = TerminalActivity.this.userInput.replace("localhost>","");

                    if(!TerminalActivity.this.userInput.equals("-点击回车继续-")){
                        continue;
                    }
                    TerminalActivity.this.userInput = "";
                } while(i == 0);
                UserEnteredCommand(userInput);
            }
        });
//      this.DB = new DBAdapter(getApplicationContext());
        initTerminal();
    }

    private void initTerminal() {
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int currentLevel = localSharedPreferences.getInt("rh_current_level",-1);

        String str = null;
        while(true) {
            String str1 = String.valueOf(Calendar.getInstance().get(1));
            String str8 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
            str = str.replace("[CURRENT_YEAR]",str1);
            displayText(true,str);
            if(currentLevel != 1) {
                break;
            }
            String str3 = this.tvScreen.getText()+str;
            displayText(true,str3);

            displayText(true, "Unable to initialize system...");
        }
        displayText(true,"\n- 点击回车继续 -\n");

    }

    private void tick() {

    }

    private void displayText(boolean paramBoolean,String paramString){
        if(paramBoolean) {
            this.settingText = true;
            this.tvScreen.setText("");
            this.settingText = false;
        }
        if(paramString.contains("[OPEN_FILE_EDITER]")) {
            startActivity(new Intent(this,EditorActivity.class));
            paramString = paramString.replace("[OPEN_FILE_EDITER]","");
        }
        do {
            while(true) {
                this.settingText = true;
                this.tvScreen.setText(this.tvScreen.getText().toString() + paramString);
                this.settingText = false;
                this.termContents = this.tvScreen.getText().toString();
                this.tvScreen.setSelection(this.termContents.length());
                return;
            }
        } while(!paramString.startsWith("localhost"));
    }

    private boolean UserEnteredCommand(String paramString) {
        String str_show;
        if(paramString.equals("exit")) {
            new AlertDialog.Builder(this).setTitle("Confirm system shutdown").setMessage("确定要退出游戏吗?")
                    .setIcon(R.drawable.ic_launcher).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent localIntent = new Intent();
                    localIntent.putExtra("Intent_id5", "Intent_id6");
                    TerminalActivity.this.setResult(-1, localIntent);
                    TerminalActivity.this.finish();
                }
            }).setNegativeButton("取消", null).show();
            str_show = "Attempting complete system shut down" + " localhost >";
        }

//        while(true) {
//            TerminalActivity.this.tvScreen.getId();
//
//        }

        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
