package hakcrun;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.i273.hackrun.Classes.DBAdapter;
import com.i273.hackrun.Classes.Game;
import com.i273.hackrun.Classes.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class TerminalActivity extends Activity {
    DBAdapter DB;
    private String cPrompt = "";
    private Game game;
    private int promptEndPos = 0;
    private boolean settingText = false;
    private String termContents;
    private EditText tvScreen;
    private boolean useInput = false;
    private String userInput = "";

    private boolean UserEnteredCommand(String paramString) {
        if (this.game.isPaused) {
            Game localGame2 = this.game;
            localGame2.pauseSegment = (1 + localGame2.pauseSegment);
            String str11 = this.game.pauseSegments[this.game.pauseSegment];
            if (this.game.pauseSegment < -1 + this.game.pauseSegments.length)
                str11 = str11 + "\n- 点击回车继续 -\n";
            while (true) {
                displayText(false, str11);
                return false;
                this.game.isPaused = false;
                this.game.pauseSegment = 0;
                this.game.pauseSegments = null;
            }
        }
        String[] arrayOfString1 = this.tvScreen.getText().toString().split(Pattern.quote("\n"), -1);
        String str1 = arrayOfString1[(-1 + arrayOfString1.length)].replace(paramString, "");
        String str2 = this.game.processInput(str1, paramString);
        if (str2.contains("[<EXIT>]")) {
            new AlertDialog.Builder(this).setTitle("Confirm System Shutdown").setMessage("你确定要返回主菜单吗？").setIcon(17301543).setPositiveButton(17039379, new OnClickListener() {
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent localIntent = new Intent();
                    localIntent.putExtra("intent_parm_id5", "intent_parm_id6");
                    TerminalActivity.this.setResult(-1, localIntent);
                    TerminalActivity.this.finish();
                }
            }).setNegativeButton(17039369, null).show();
            str2 = "\nAttempting complete system shutdown...\nlocalhost> ";
        }
        while (true) {
            String str3 = str2.replace("'|'", "'[PIPE]'").replace("|", "\n").replace("'[PIPE]'", "'|'").replace("[ALLPAUSE]", "[PAUSE]");
            if (str3.contains("[PAUSE]")) {
                Game localGame1 = this.game;
                String str4 = Pattern.quote("[PAUSE]");
                localGame1.pauseSegments = str3.split(str4, -1);
                if (this.game.pauseSegments.length > 1) {
                    this.game.isPaused = true;
                    this.game.pauseSegment = 0;
                    str3 = this.game.pauseSegments[0] + "\n- 点击回车继续 -\n";
                }
            }
            displayText(false, str3);
            if (this.game.mariesAlgorithm > 0)
                new CountDownTimer(10000L, 100L) {
                    public void onFinish() {
                        TerminalActivity.this.tick(true);
                    }

                    public void onTick(long paramLong) {
                        TerminalActivity.this.tick(false);
                    }
                }
                        .start();
            this.userInput = "";
            return true;
            if (str2.contains("[<BUY_FULL_VERSION>]")) {
                new AlertDialog.Builder(this).setTitle("End of Free Version").setMessage("恭喜！你已经成功的完成了免费版本中的所有关卡！\n\n如果想要购买完整版本，请点击购买。感谢支持！").setIcon(17301543).setPositiveButton("购买", new OnClickListener() {
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent localIntent = new Intent(TerminalActivity.this.getApplicationContext(), WebTemplateActivity.class);
                        localIntent.putExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN", "http://www.i273.com/HackRUN/GooglePlay/");
                        TerminalActivity.this.startActivity(localIntent);
                    }
                }).setNegativeButton(17039369, null).show();
                String str10 = str2.replace("[<BUY_FULL_VERSION>]", "");
                str2 = "\n" + str10 + "\n" + this.game.getCurrentPrompt();
                continue;
            }
            if (str2.startsWith("[<WEBBROWSER>]|")) {
                String str7 = Pattern.quote("|");
                String[] arrayOfString3 = str2.split(str7, -1);
                String str8 = arrayOfString3[1];
                Intent localIntent2 = new Intent(this, WebBrowserActivity.class);
                localIntent2.putExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN", str8);
                startActivity(localIntent2);
                String str9 = arrayOfString3[2];
                str2 = "\nlaunching web browser" + str9;
                continue;
            }
            if (str2.startsWith("[<ACHIEVEMENTS>]|")) {
                String[] arrayOfString2 = this.DB.getAchievements(this.game.currentLevel);
                String str5 = "";
                for (int i = 0; ; i++) {
                    if (i >= arrayOfString2.length) {
                        if (str5.equals(""))
                            str5 = "你还没有达成任何成就。回主菜单开始你的探险吧！";
                        str2 = "\n" + str5 + "\n" + this.game.getCurrentPrompt();
                        break;
                    }
                    String str6 = "\n" + String.valueOf(i + 1) + ". " + arrayOfString2[i] + "\n";
                    str5 = str5 + str6;
                }
            }
            if (str2.startsWith("[<CLEAR_SCREEN>]")) {
                displayText(true, "");
                str2 = str2.replace("[<CLEAR_SCREEN>]", "已清屏");
                continue;
            }
            if (str2.startsWith("[<TIPS_HINTS_ANSWERS>]|")) {
                Intent localIntent1 = new Intent(this, WebTemplateActivity.class);
                localIntent1.putExtra("com.i273.hackrun.MESSAGE_PAGE_TO_OPEN", "http://php.pujiahh.com/hackrun/mtips.html");
                startActivity(localIntent1);
                str2 = "\n正在显示提醒，线索&答案\n" + this.game.getCurrentPrompt();
                continue;
            }
            if (!str2.equals("[<RUN_UNIT_TESTS>]"))
                continue;
            str2 = "\n\nRUNNING UNIT TESTS...";
        }
    }

    private void displayText(boolean paramBoolean, String paramString) {
        if (paramBoolean) {
            this.settingText = true;
            this.tvScreen.setText("");
            this.settingText = false;
        }
        if (paramString.contains("[OPEN_FILE_EDITOR]")) {
            startActivity(new Intent(this, EditorActivity.class));
            paramString = paramString.replace("[OPEN_FILE_EDITOR]", "");
        }
        do
            while (true) {
                if (paramString.contains("[<REMOVE_EMAIL_ICON>]"))
                    paramString = paramString.replace("[<REMOVE_EMAIL_ICON>]", "");
                /**
                 * 这一段比较重要
                 */
                this.settingText = true;
                this.tvScreen.setText(this.tvScreen.getText().toString() + paramString);
                this.settingText = false;
                this.termContents = this.tvScreen.getText().toString();
                this.tvScreen.setSelection(this.termContents.length());
                this.promptEndPos = this.termContents.length();
                this.cPrompt = this.game.getCurrentPrompt();
                return;
                if (paramString.startsWith("[ATTACK]")) {
                    Utils.VibratePhone(getApplicationContext());
                    if (this.game.currentLevel == 24)
                        this.game.advanceToNextLevel();
                    Intent localIntent = new Intent();
                    localIntent.putExtra("intent_parm_id3", "intent_parm_id4");
                    setResult(-1, localIntent);
                    finish();
                    return;
                }
                if (paramString.startsWith("[ADVANCE_LEVEL]")) {
                    if (this.game.currentLevel == 25)
                        this.game.advanceToNextLevel();
                    paramString = this.game.getCurrentPrompt();
                    continue;
                }
                if (paramString.contains("[<RECEIVED_EMAIL>]")) {
                    paramString = paramString.replace("[<RECEIVED_EMAIL>]", "\n");
                    continue;
                }
                if ((paramString.startsWith("[SUCCESS:")) || ((this.game.runningUnitTests) && (paramString.contains("SUCCESS:")))) {
                    String str1 = paramString.split("]")[0].replace("[SUCCESS:", "");
                    String str2 = this.game.checkForSuccess(str1);
                    if (str2.equals("")) {
                        paramString = "\n" + this.game.getCurrentPrompt();
                        continue;
                    }
                    if (str2.contains("[<RECEIVED_EMAIL>]"))
                        str2 = str2.replace("[<RECEIVED_EMAIL>]", "");
                    String str3 = str2.replace("[ALLPAUSE]", "");
                    paramString = "\n" + str3 + "\n" + this.game.getCurrentPrompt();
                    continue;
                }
                if (!paramString.startsWith("[FILE_CONTENTS:"))
                    break;
                String str4 = paramString.split(Pattern.quote("]"), -1)[0].replace("[FILE_CONTENTS:", "");
                String str5 = (this.DB.getFileContents(this.game.getCurrentSystem(), this.game.getCurrentUser(), "/", str4, this.game.currentLevel) + "\n" + this.game.getCurrentPrompt()).replace("[ALLPAUSE]", "[PAUSE]");
                this.game.pauseSegments = str5.split(Pattern.quote("[PAUSE]"), -1);
                if (this.game.pauseSegments.length <= 1)
                    continue;
                this.game.isPaused = true;
                this.game.pauseSegment = 0;
                paramString = this.game.pauseSegments[0] + "\n- 点击回车继续 -\n";
            }
        while (!paramString.startsWith("[LOCALHOST]"));
        while (true) {
            if (this.game.systemStack.size() <= 1) {
                Utils.changeFont(getApplicationContext(), this.tvScreen, "normal");
                paramString = "Exiting to localhost\n" + this.game.getCurrentPrompt();
                break;
            }

          }
    }

    private void initTerminal() {
        Utils.changeFont(getApplicationContext(), this.tvScreen, "normal");
        this.game = new Game(getApplicationContext());
        this.game.tvScreen = this.tvScreen;
        this.game.DB = this.DB;
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.game.currentLevel = localSharedPreferences.getInt("hr__current_level", -1);
        this.game.addSystem("local");
        String str5;
        if (this.game.getCurrentSystem().equals("local"))
            str5 = this.DB.getSystemMessage("local", "connection").replace("|", "\n");
        do
            while (true) {
                try {
                    String str7 = String.valueOf(Calendar.getInstance().get(1));
                    String str8 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
                    str5 = str5.replace("[CURRENT_YEAR]", str7);
                    String str9 = str5.replace("[DATETIME]", str8);
                    str6 = str9;

                    displayText(true, str6 + this.game.getCurrentPrompt());
                    if (this.game.currentLevel != 1)
                        break;
                    String str2 = this.game.checkForSuccess("");
                    String str3 = this.tvScreen.getText() + str2;
                    Game localGame = this.game;
                    String str4 = Pattern.quote("[ALLPAUSE]");
                    localGame.pauseSegments = str3.split(str4, -1);
                    if (this.game.pauseSegments.length <= 1)
                        continue;
                    this.game.isPaused = true;
                    this.game.pauseSegment = 0;
                    str3 = this.game.pauseSegments[0] + "\n- 点击回车继续 -\n";
                    displayText(true, str3);
                    return;
                } catch (Exception localException) {
                    String str6 = str5.replace("[CURRENT_YEAR]", "").replace("[DATETIME]", "");
                    continue;
                }
                displayText(true, "Unable to initialize system...");
            }
        while (this.game.currentLevel != this.game.CONST_LEVEL_ATTACKED);
        String str1 = this.DB.getFileContents(this.game.getCurrentSystem(), this.game.getCurrentUser(), "/", "attacked", this.game.currentLevel).replace("|", "\n");
        this.game.pauseSegments = str1.split(Pattern.quote("[PAUSE]"), -1);
        this.game.isPaused = true;
        this.game.pauseSegment = 0;
        displayText(true, new StringBuilder(String.valueOf(this.tvScreen.getText().toString())).append(this.game.pauseSegments[0]).toString() + "\n- 点击回车继续 -\n");
    }

    private void tick(boolean paramBoolean) {
        if (paramBoolean) {
            this.settingText = true;
            this.termContents += "8847384621";
            this.tvScreen.append("\npasscode: " + "8847384621");
            this.settingText = false;
            this.userInput = "8847384621";
            return;
        }
        String str1;
        String str2;
        label142:
        String str3;
        label176:
        String str4;
        label211:
        String str5;
        label246:
        String str6;
        label281:
        String str7;
        label316:
        String str8;
        label351:
        String str9;
        if (this.game.mariesAlgorithm < 100) {
            str1 = "" + "8";
            if (this.game.mariesAlgorithm >= 90)
                break label531;
            str2 = str1 + "8";
            if (this.game.mariesAlgorithm >= 80)
                break label555;
            str3 = str2 + "4";
            if (this.game.mariesAlgorithm >= 70)
                break label580;
            str4 = str3 + "7";
            if (this.game.mariesAlgorithm >= 60)
                break label606;
            str5 = str4 + "3";
            if (this.game.mariesAlgorithm >= 50)
                break label632;
            str6 = str5 + "8";
            if (this.game.mariesAlgorithm >= 40)
                break label658;
            str7 = str6 + "4";
            if (this.game.mariesAlgorithm >= 30)
                break label684;
            str8 = str7 + "6";
            if (this.game.mariesAlgorithm >= 20)
                break label710;
            str9 = str8 + "2";
            label386:
            if (this.game.mariesAlgorithm >= 10)
                break label736;
        }
        label531:
        label555:
        label580:
        label606:
        label736:
        for (String str10 = str9 + "1"; ; str10 = str9 + "*") {
            if (!str10.equals("8847384621")) {
                this.settingText = true;
                this.tvScreen.append("\npasscode: " + str10);
                this.settingText = false;
            }
            if (this.game.mariesAlgorithm < 10)
                this.game.mariesAlgorithm = 1;
            Game localGame = this.game;
            localGame.mariesAlgorithm = (-1 + localGame.mariesAlgorithm);
            return;
            str1 = "" + "*";
            break;
            str2 = str1 + "*";
            break label142;
            str3 = str2 + "*";
            break label176;
            str4 = str3 + "*";
            break label211;
            str5 = str4 + "*";
            break label246;
            label632:
            str6 = str5 + "*";
            break label281;
            str7 = str6 + "*";
            break label316;
            str8 = str7 + "*";
            break label351;
            str9 = str8 + "*";
            break label386;
        }
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2130903046);
        this.tvScreen = ((EditText) findViewById(2131296276));
        this.tvScreen.setTypeface(Typeface.MONOSPACE);
        this.tvScreen.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable paramEditable) {
                if (TerminalActivity.this.settingText) ;
                int i;
                do {
                    return;
                    if (!TerminalActivity.this.useInput) {
                        TerminalActivity.this.displayText(true, TerminalActivity.this.termContents);
                        return;
                    }
                    boolean bool = paramEditable.toString().endsWith("\n");
                    i = 0;
                    if (bool) {
                        TerminalActivity.this.settingText = true;
                        paramEditable = paramEditable.delete(-1 + paramEditable.toString().length(), paramEditable.toString().length());
                        TerminalActivity.this.settingText = false;
                        i = 1;
                    }
                    TerminalActivity.this.termContents = paramEditable.toString();
                    TerminalActivity.this.tvScreen.setSelection(TerminalActivity.this.tvScreen.getText().length());
                    String[] arrayOfString = paramEditable.toString().split(Pattern.quote("\n"), -1);
                    String str = arrayOfString[(-1 + arrayOfString.length)];
                    TerminalActivity.this.userInput = str.replace(TerminalActivity.this.cPrompt, "");
                    if ((TerminalActivity.this.game.currentLevel == 46) && (TerminalActivity.this.userInput.startsWith("localhost> ")))
                        TerminalActivity.this.userInput = TerminalActivity.this.userInput.replace("localhost> ", "");
                    if (!TerminalActivity.this.userInput.equals("- 点击回车继续 -"))
                        continue;
                    TerminalActivity.this.userInput = "";
                }
                while (i == 0);
                TerminalActivity.this.UserEnteredCommand(TerminalActivity.this.userInput);
            }

            public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
                if (TerminalActivity.this.settingText)
                    return;
                TerminalActivity.this.tvScreen.setSelection(TerminalActivity.this.tvScreen.getText().length());
            }

            public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
                if (TerminalActivity.this.settingText) ;
                do {
                    return;
                    TerminalActivity.this.tvScreen.setSelection(TerminalActivity.this.tvScreen.getText().length());
                    TerminalActivity.this.useInput = true;
                }
                while (paramInt1 >= TerminalActivity.this.promptEndPos);
                TerminalActivity.this.useInput = false;
            }
        });
        this.DB = new DBAdapter(getApplicationContext());
        initTerminal();
    }

    protected void onDestroy() {
        if (this.game != null)
            this.game.stopAudio();
        super.onDestroy();
    }
}
