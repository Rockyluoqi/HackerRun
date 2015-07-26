package hakcrun.Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

public class Game {
    private static final String TAG = "Game";
    int BEEP_DURATION_SECONDS = 30;
    public int CONST_LEVEL_ATTACKED = 25;
    int CONST_PRISONER_NUMBER = 339;
    String CONST_ROBOT_VERSION_PREFIX = "v37.50";
    public DBAdapter DB;
    boolean IS_PRODUCTION_MODE = true;
    String achDisplayMode = "";
    private final Context context;
    public int currentLevel = -1;
    private int failedPasswordAttempts = 0;
    public boolean isPaused = false;
    String lastInput = "";
    String loginName = "";
    public int mariesAlgorithm = 0;
    MediaPlayer mediaPlayer;
    public int pauseSegment = 0;
    public String[] pauseSegments;
    public boolean runningUnitTests = false;
    boolean sendCoordinates;
    public ArrayList<String> systemStack = new ArrayList();
    boolean transmitMessage;
    public EditText tvScreen;
    public ArrayList<String> userStack = new ArrayList();

    public Game(Context paramContext) {
        this.context = paramContext;
        resetState();
    }

    private String aliasCheck(String paramString) {
        if (paramString.equals("b"))
            paramString = "backdoor";
        do {
            return paramString;
            if (paramString.equals("d"))
                return "date";
            if (paramString.equals("e"))
                return "exit";
            if (paramString.equals("g"))
                return "gate";
            if (paramString.equals("h"))
                return "help";
            if (paramString.equals("j"))
                return "jump";
            if (paramString.startsWith("j "))
                return paramString.replace("j ", "jump ");
            if (paramString.equals("l"))
                return "ls";
            if (paramString.equals("m"))
                return "mail";
            if (paramString.equals("r"))
                return "run";
            if (paramString.equals("s"))
                return "show";
            if (paramString.startsWith("s "))
                return paramString.replace("s ", "show ");
            if (paramString.equals("t"))
                return "type";
            if (paramString.startsWith("t "))
                return paramString.replace("t ", "type ");
            if (paramString.equals("v"))
                return "voicemail";
            if (paramString.equals("w"))
                return "web";
        }
        while (!paramString.startsWith("w "));
        return paramString.replace("w ", "web ");
    }

    private String checkForAdminCommands(String paramString1, String paramString2) {
        if (!getCurrentSystem().startsWith("admin"))
            return "";
        String str1 = "";
        String str2 = getCurrentSystem();
        String str3 = aliasCheck(paramString2);
        if (str2.equals("admin"))
            if (str3.equals("exit")) {
                str1 = this.DB.getSystemMessage("admin", "exit");
                removeSystem();
                paramString1 = getCurrentPrompt();
                Utils.changeFont(this.context, this.tvScreen, "normal");
            }
        while (str1.equals("")) {
            return "";
            if (!str3.equals("exit"))
                continue;
            str1 = this.DB.getSystemMessage("admin_mail", "exit");
            removeSystem();
            paramString1 = getCurrentPrompt();
        }
        return new StringBuilder(String.valueOf(new StringBuilder("\n").append(str1).toString())).append("\n").toString() + paramString1;
    }

    private String checkForCommonCommands(String paramString1, String paramString2) {
        Object localObject = "";
        String str1 = getCurrentSystem();
        String str2 = aliasCheck(paramString2);
        if (str2.equals("ach"))
            return "[<ACHIEVEMENTS>]|\n" + getCurrentPrompt();
        if (str2.equals("alt")) {
            String str8 = this.DB.getHelpMenu("alt");
            return "\n" + str8 + "\n注解，这些命令所有系统通用。\n" + getCurrentPrompt();
        }
        if (str2.equals("answer"))
            localObject = "\n " + HintsAndAnswers.getAnswer(this.context, this.currentLevel);
        while (((String) localObject).equals("")) {
            return localObject;
            if (str2.equals("clr"))
                return "[<CLEAR_SCREEN>]\n" + getCurrentPrompt();
            if (str2.equals("hint")) {
                localObject = "\n " + HintsAndAnswers.getHint(this.context, this.currentLevel);
                continue;
            }
            if (str2.equals("notes"))
                return "\n正在启动...[OPEN_FILE_EDITOR]\n" + getCurrentPrompt();
            if (str2.equals("tips"))
                return "[<TIPS_HINTS_ANSWERS>]|\n" + getCurrentPrompt();
            if (str2.equals("exit")) {
                localObject = this.DB.getSystemMessage(str1, "exit").replace("[LOGIN_NAME]", getCurrentUser()).replace(" guest's", "");
                if (this.systemStack.size() == 1)
                    return "[<EXIT>]";
                removeSystem();
                paramString1 = getCurrentPrompt();
                continue;
            }
            if ((str2.equals("ls")) && (!str1.startsWith("mail"))) {
                String str7 = getCurrentUser();
                String[] arrayOfString = this.DB.getFilesBySDUF(str1, this.currentLevel, str7, "/");
                localObject = "文件列表:";
                for (int i = 0; ; i++) {
                    if (i >= arrayOfString.length) {
                        if (arrayOfString.length > 0)
                            break;
                        localObject = localObject + "\n 没有找到文件";
                        break;
                    }
                    localObject = new StringBuilder(String.valueOf(localObject)).append("\n ").toString() + arrayOfString[i];
                }
            }
            if (str2.equals("mail")) {
                String str6 = str1.replace("|", "^");
                addSystemWithUsername(str6 + "|" + getCurrentUser() + "|mail", getCurrentUser());
                localObject = "Launching " + getCurrentUser() + "'s mail...";
                if (str6.equals("local"))
                    localObject = this.DB.getSystemMessage("local_mail", "connection");
                if (str6.equals("admin"))
                    localObject = "Launching Admin mail";
                if (str6.equals("trace"))
                    localObject = "Launching mail";
                if (str6.equals("local"))
                    localObject = localObject + "[<REMOVE_EMAIL_ICON>]";
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("type")) {
                if ((str1.equals("local")) && (this.currentLevel >= this.CONST_LEVEL_ATTACKED)) {
                    localObject = "type command offline";
                    continue;
                }
                localObject = " usage: type [file]";
                continue;
            }
            if (str2.startsWith("type ")) {
                if ((str1.equals("local")) && (this.currentLevel >= this.CONST_LEVEL_ATTACKED)) {
                    localObject = "type command offline";
                    continue;
                }
                String str4 = str2.replace("type ", "");
                String str5 = this.DB.getFileContents(str1, getCurrentUser(), "/", str4, this.currentLevel);
                if (str5.equals("")) {
                    localObject = "No file: " + str4;
                    continue;
                }
                localObject = str5;
                continue;
            }
            if (str2.equals("web"))
                return "[<WEBBROWSER>]||\n" + getCurrentPrompt();
            if (!str2.startsWith("web "))
                continue;
            String str3 = str2.replace("web ", "").replace(" ", "");
            return "[<WEBBROWSER>]|" + str3 + "|\n" + getCurrentPrompt();
        }
        return (String) (new StringBuilder(String.valueOf(new StringBuilder("\n").append((String) localObject).toString())).append("\n").toString() + paramString1);
    }

    private String checkForDEBUGGINGCommands(String paramString1, String paramString2) {
        if (this.IS_PRODUCTION_MODE)
            return "";
        String str1 = "";
        if (paramString2.equals("ut")) {
            this.runningUnitTests = true;
            this.achDisplayMode = "inline";
            this.currentLevel = 2;
            resetState();
            Utils.resetGame(this.context, this.currentLevel, -1, this.runningUnitTests);
            while (true) {
                if (this.systemStack.size() <= 1) {
                    Utils.changeFont(this.context, this.tvScreen, "normal");
                    return "[<RUN_UNIT_TESTS>]";
                }
                removeSystem();
            }
        }
        if (paramString2.equals("level"))
            str1 = "当前关卡: " + String.valueOf(this.currentLevel);
        while (str1.equals("")) {
            return "";
            if (paramString2.startsWith("reset ")) {
                this.currentLevel = Integer.parseInt(paramString2.replace("reset ", ""));
                resetState();
                Utils.resetGame(this.context, this.currentLevel, -1, this.runningUnitTests);
                while (true) {
                    if (this.systemStack.size() <= 1) {
                        Utils.changeFont(this.context, this.tvScreen, "normal");
                        paramString1 = getCurrentPrompt();
                        str1 = "重置游戏至关卡" + String.valueOf(this.currentLevel) + ".\n正在返回本地系统。";
                        break;
                    }
                    removeSystem();
                }
            }
            if (paramString2.equals("sw")) {
                for (int i = 0; i <= 273; i++)
                    str1 = str1 + String.valueOf(i % 10);
                continue;
            }
            if (paramString2.equals("sys")) {
                str1 = getCurrentSystem();
                continue;
            }
            if (paramString2.equals("user")) {
                str1 = getCurrentUser();
                continue;
            }
            if (paramString2.equals("web"))
                return "[<WEBBROWSER>]||\n" + getCurrentPrompt();
            if (!paramString2.startsWith("web "))
                continue;
            String str2 = paramString2.replace("web ", "").replace(" ", "");
            return "[<WEBBROWSER>]|" + str2 + "|\n" + getCurrentPrompt();
        }
        return new StringBuilder(String.valueOf(new StringBuilder("\n").append(str1).toString())).append("\n").toString() + paramString1;
    }

    private String checkForDatabaseCommands(String paramString1, String paramString2) {
        Object localObject = "";
        String str1 = getCurrentSystem();
        String str2 = aliasCheck(paramString2);
        if (str2.equals("admin")) {
            this.loginName = "";
            localObject = this.DB.getSystemMessage("admin", "connection");
            addSystem("admin");
            paramString1 = getCurrentPrompt();
        }
        while (((String) localObject).equals("")) {
            return "";
            if ((str2.equals("atip")) && (str1.equals("hackroutine"))) {
                localObject = this.DB.getFileContents(str1, getCurrentUser(), "/", str2, this.currentLevel);
                continue;
            }
            if (str2.equals("backdoor")) {
                if ((str1.equals("local")) && (this.currentLevel >= 52)) {
                    localObject = "backdoor command offline";
                    continue;
                }
                localObject = this.DB.getSystemMessage("backdoor", "connection");
                addSystem("backdoor");
                this.loginName = "Nigel";
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("exit")) {
                if (str1.equals("local|hacker|mail"))
                    localObject = this.DB.getSystemMessage("local_mail", "exit");
                while (this.systemStack.size() == 1) {
                    return "[<EXIT>]";
                    if (str1.equals("trace|emp|mail")) {
                        localObject = this.DB.getSystemMessage("trace|emp|mail", "exit");
                        continue;
                    }
                    if (str1.startsWith("|mail")) {
                        String[] arrayOfString2 = str1.split(Pattern.quote("|"), -1);
                        localObject = this.DB.getSystemMessage("workstation_mail", "exit").replace("[LOGIN_NAME]", arrayOfString2[1]);
                        continue;
                    }
                    if (str1.startsWith("ws_")) {
                        String str9 = str1.replace("ws_", "");
                        localObject = this.DB.getSystemMessage("workstation", "exit").replace("[LOGIN_NAME]", str9);
                        continue;
                    }
                    if (str1.startsWith("govt")) {
                        localObject = this.DB.getSystemMessage("govt", "exit");
                        continue;
                    }
                    if (str1.startsWith("ufo_")) {
                        String str8 = str1.replace("ufo_", "");
                        localObject = this.DB.getSystemMessage("ufo", "exit").replace("[LOGIN_NAME]", str8);
                        Utils.changeFont(this.context, this.tvScreen, "normal");
                        continue;
                    }
                    localObject = this.DB.getSystemMessage(str1, "exit");
                }
                removeSystem();
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("gate")) {
                this.loginName = "";
                localObject = this.DB.getSystemMessage("gateway", "connection");
                addSystem("gateway");
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("govt")) {
                this.loginName = "";
                localObject = this.DB.getSystemMessage("govt", "connection");
                addSystem("govt");
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("help")) {
                localObject = this.DB.getHelpMenu(getCurrentSystem());
                continue;
            }
            if ((str2.equals("hr")) && (this.currentLevel >= 12)) {
                this.loginName = "";
                localObject = this.DB.getSystemMessage("hr", "connection");
                addSystem("hr");
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("pdb")) {
                localObject = this.DB.getSystemMessage("pdb", "connection");
                addSystemWithUsername("pdb", getCurrentUser());
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("jump")) {
                localObject = " usage: (j)ump [username]";
                continue;
            }
            if (str2.startsWith("jump ")) {
                this.loginName = "";
                String str6 = str2.replace("jump ", "").replace(" ", "");
                this.loginName = str6;
                String str7 = "ws_" + str6;
                localObject = this.DB.getSystemMessage(str7, "connection").replace("[LOGIN_NAME]", str6);
                addSystem(str7);
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("ls")) {
                if ((str1.equals("local")) && (this.currentLevel >= this.CONST_LEVEL_ATTACKED)) {
                    localObject = "ls command offline";
                    continue;
                }
                String str5 = getCurrentUser();
                String[] arrayOfString1 = this.DB.getFilesBySDUF(str1, this.currentLevel, str5, "/");
                localObject = "文件列表:";
                if (getCurrentSystem().endsWith("|mail"))
                    localObject = "消息列表：";
                for (int i = 0; ; i++) {
                    if (i >= arrayOfString1.length) {
                        if (arrayOfString1.length > 0)
                            break;
                        localObject = localObject + "\n 没有找到文件";
                        if (!getCurrentSystem().endsWith("|mail"))
                            break;
                        localObject = localObject + "\n 没有找到消息";
                        break;
                    }
                    localObject = new StringBuilder(String.valueOf(localObject)).append("\n ").toString() + arrayOfString1[i];
                }
            }
            if (str2.equals("news")) {
                localObject = this.DB.getFileContents(getCurrentSystem(), getCurrentUser(), "/", "news", this.currentLevel).replace("|", "\n");
                continue;
            }
            if ((str2.equals("note")) && (getCurrentSystem().equals("hackroutine"))) {
                localObject = this.DB.getFileContents(str1, getCurrentUser(), "/", str2, this.currentLevel);
                continue;
            }
            if (str2.equals("ping")) {
                localObject = "正在开始ping...[ALLPAUSE][FILE_CONTENTS:ping!]";
                continue;
            }
            if (str2.equals("radio")) {
                addSystemWithUsername("radio", getCurrentUser());
                localObject = this.DB.getSystemMessage("radio", "connection");
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("reps")) {
                this.loginName = "";
                localObject = this.DB.getSystemMessage("reps", "connection");
                addSystem("reps");
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("run")) {
                if ((str1.equals("local")) && (this.currentLevel >= this.CONST_LEVEL_ATTACKED)) {
                    localObject = "run command offline";
                    continue;
                }
                localObject = this.DB.getSystemMessage("hackroutine", "connection");
                addSystem("hackroutine");
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("show")) {
                localObject = " usage: show [message]";
                continue;
            }
            if (str2.startsWith("show ")) {
                String str3 = str2.replace("show ", "").replace(" ", "");
                String str4 = this.DB.getFileContents(str1, getCurrentUser(), "/", str3, this.currentLevel);
                if (str4.equals("")) {
                    localObject = "No message: " + str3;
                    continue;
                }
                localObject = str4;
                continue;
            }
            if (str2.equals("trace")) {
                this.loginName = "";
                localObject = this.DB.getSystemMessage("trace", "connection");
                addSystemWithUsername("trace", "emp");
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("ufo")) {
                if ((str1.equals("local")) && (this.currentLevel >= 51)) {
                    localObject = "ufo command offline";
                    continue;
                }
                this.loginName = "";
                localObject = this.DB.getSystemMessage("ufo", "connection");
                addSystem("ufo");
                paramString1 = getCurrentPrompt();
                continue;
            }
            if (str2.equals("uplink")) {
                this.loginName = "emp";
                localObject = this.DB.getSystemMessage("uplink", "connection");
                addSystem("uplink");
                paramString1 = getCurrentPrompt();
                if (this.currentLevel < 37)
                    continue;
                this.mariesAlgorithm = 100;
                continue;
            }
            if (!str2.equals("voicemail"))
                continue;
            localObject = "no voice messages";
        }
        return (String) (new StringBuilder(String.valueOf(new StringBuilder("\n").append((String) localObject).toString())).append("\n").toString() + paramString1);
    }

    private String checkForDiplomacyCommands(String paramString1, String paramString2) {
        String str1 = getCurrentSystem();
        if (!str1.equals("diplomacy"))
            return "";
        String str2 = "";
        String str3 = getCurrentPrompt();
        String str4 = aliasCheck(paramString2);
        if (str4.equals("exit")) {
            str2 = this.DB.getSystemMessage("diplomacy", "exit");
            removeSystem();
            paramString1 = getCurrentPrompt();
            Utils.changeFont(this.context, this.tvScreen, "normal");
        }
        while (str2.equals("")) {
            return "";
            if (str4.equals("send")) {
                this.sendCoordinates = true;
                str2 = "正在准备向Zyrgorkn种族发送当前坐标...";
                paramString1 = getCurrentPrompt();
                continue;
            }
            if ((str3.endsWith("(y/n)? ")) && (str4.equals("n"))) {
                this.sendCoordinates = false;
                str2 = "发送坐标已取消。";
                paramString1 = getCurrentPrompt();
                continue;
            }
            if ((str3.endsWith("(y/n)? ")) && (str4.equals(""))) {
                this.sendCoordinates = false;
                str2 = " ";
                paramString1 = getCurrentPrompt();
                continue;
            }
            if ((str3.endsWith("(y/n)? ")) && (str4.equals("y"))) {
                this.sendCoordinates = false;
                str2 = this.DB.getFileContents(str1, getCurrentUser(), "/", "send", this.currentLevel);
                continue;
            }
            if (!str3.endsWith("(y/n)? "))
                continue;
            str2 = "Enter y or n";
        }
        return new StringBuilder(String.valueOf(new StringBuilder("\n").append(str2).toString())).append("\n").toString() + paramString1;
    }

    private String checkForHrCommands(String paramString1, String paramString2) {
        if (!getCurrentSystem().startsWith("hr"))
            return "";
        Object localObject = "";
        String str1 = getCurrentSystem();
        String str2 = aliasCheck(paramString2);
        if (str1.equals("hr"))
            if (str2.equals("search")) {
                addSystemWithUsername(getCurrentSystem() + "|" + getCurrentUser() + "|search", getCurrentUser());
                localObject = "正在搜索HR雇员和同事数据库\n输入'exit'可以退出。\n请输入雇员或同事的用户名。";
                paramString1 = getCurrentPrompt();
            }
        while (((String) localObject).equals("")) {
            return "";
            if (!str2.equals("exit"))
                continue;
            localObject = this.DB.getSystemMessage("hr", "exit");
            removeSystem();
            paramString1 = getCurrentPrompt();
            Utils.changeFont(this.context, this.tvScreen, "normal");
            continue;
            if (str2.equals("exit")) {
                localObject = this.DB.getSystemMessage("hr_search", "exit");
                removeSystem();
                paramString1 = getCurrentPrompt();
                continue;
            }
            String str3 = this.DB.getFileContents("hr_search", "elise", "/", str2, this.currentLevel);
            if ((str3 == null) || (str3.equals("")))
                continue;
            localObject = str3;
        }
        return (String) (new StringBuilder(String.valueOf(new StringBuilder("\n").append((String) localObject).toString())).append("\n").toString() + paramString1);
    }

    private String checkForLogin(String paramString1, String paramString2) {
        String str1 = getCurrentSystem();
        if (!getCurrentUser().equals("guest"))
            return "";
        if (paramString1.equals("username: ")) {
            if (paramString2.equals(""))
                return "exit";
            this.loginName = paramString2;
            String str15 = getCurrentPrompt();
            return "\n" + str15;
        }
        if (paramString1.equals("alien race: ")) {
            if (paramString2.equals(""))
                return "exit";
            if (!paramString2.equals("zyrgorkn"))
                return "\n种族名无效。请输入想要与其进行外交的外星种族名称。\n" + paramString1;
            this.loginName = paramString2;
            String str14 = getCurrentPrompt();
            return "\n" + str14;
        }
        if (this.DB.login(str1, this.loginName, paramString2)) {
            String str8 = str1;
            if (str8.equals("ufo"))
                str8 = "ufo_" + this.loginName;
            int k = this.DB.loginLevel(str8, this.loginName);
            if (this.currentLevel < k) {
                removeSystem();
                String str13 = getCurrentPrompt();
                Utils.VibratePhone(this.context);
                if ((this.currentLevel == 16) && (this.loginName.equals("hhamm")))
                    return "[<BUY_FULL_VERSION>]";
                return "\n你已经登入系统，无法建立其他连接。\n" + str13;
            }
            if (str1.equals("reps")) {
                replaceSystem("reps|" + this.loginName);
                replaceUser(this.loginName);
            }
            while (true) {
                replaceUser(this.loginName);
                String str9 = checkForSuccess("LOGGED IN");
                String str10 = getCurrentPrompt();
                String str11 = this.DB.getSystemMessage(str1, "login").replace("[LOGIN_NAME]", this.loginName).replace("[ROBOT_VERSION]", getCurrentRobotVersion());
                if (!str9.equals(""))
                    str11 = insertSuccessMessage(str11, str9);
                return "\n" + str11 + "\n" + str10;
                if (str1.equals("govt")) {
                    this.loginName = this.DB.getUsernameByPassword(str1, paramString2);
                    int m = this.DB.loginLevel(str1, this.loginName);
                    if (this.currentLevel < m) {
                        removeSystem();
                        String str12 = getCurrentPrompt();
                        Utils.VibratePhone(this.context);
                        return "\n你已经登入系统，无法建立其他连接。\n" + str12;
                    }
                    str1 = "govt_" + this.loginName;
                    replaceSystem(str1);
                    if (!str1.equals("govt_robot"))
                        continue;
                    Utils.setStringInPrefs(this.context, "Active", "robot_status");
                    Utils.setStringInPrefs(this.context, "Active", "robot_coms_belt");
                    Utils.setStringInPrefs(this.context, "Active", "robot_coms_arms");
                    Utils.setStringInPrefs(this.context, "Active", "robot_coms_ramp");
                    Utils.setStringInPrefs(this.context, "Active", "robot_coms_load");
                    continue;
                }
                if (!str1.equals("ufo"))
                    continue;
                replaceSystem("ufo_" + this.loginName);
            }
        }
        String str2;
        if ((str1.equals("ufo")) && (this.loginName.equals("xander")))
            str2 = "january";
        while (true) {
            int j;
            try {
                j = 1 + Calendar.getInstance().get(2);
                if (j != 1)
                    break label1256;
                str2 = "january";
                break label1256;
                if (j != 12)
                    continue;
                str2 = "december";
                if (!paramString2.equals(str2))
                    if (paramString2.equals(""))
                        return "exit";
            } catch (Exception localException) {
                Log.e("Game", "Month name caught " + localException.getMessage());
                continue;
                Utils.VibratePhone(this.context);
                this.failedPasswordAttempts = (1 + this.failedPasswordAttempts);
                if (this.failedPasswordAttempts >= 3)
                    return "exit_max_attempts";
                return "\n密码无效\n" + paramString1;
            }
            String str3 = "ufo_" + this.loginName;
            int i = this.DB.loginLevel(str3, this.loginName);
            if (this.currentLevel < i) {
                removeSystem();
                String str7 = getCurrentPrompt();
                Utils.VibratePhone(this.context);
                return "\n你已经登入系统，无法建立其他连接。\n" + str7;
            }
            replaceSystem("ufo_" + this.loginName);
            replaceUser(this.loginName);
            String str4 = checkForSuccess("LOGGED IN");
            String str5 = getCurrentPrompt();
            String str6 = this.DB.getSystemMessage(str1, "login").replace("[LOGIN_NAME]", this.loginName);
            if (!str4.equals(""))
                str6 = insertSuccessMessage(str6, str4);
            return "\n" + str6 + "\n" + str5;
            if (paramString2.equals(""))
                return "exit";
            if (paramString1.equals("地址：")) {
                Utils.VibratePhone(this.context);
                this.failedPasswordAttempts = (1 + this.failedPasswordAttempts);
                if (this.failedPasswordAttempts >= 3)
                    return "exit_max_attempts";
                return "\n地址无效\n" + paramString1;
            }
            if (paramString1.equals("passcode: ")) {
                Utils.VibratePhone(this.context);
                this.failedPasswordAttempts = (1 + this.failedPasswordAttempts);
                if (this.failedPasswordAttempts >= 3)
                    return "exit_max_attempts";
                return "\n密码无效\n" + paramString1;
            }
            if (paramString1.equals("外交密码：")) {
                Utils.VibratePhone(this.context);
                this.failedPasswordAttempts = (1 + this.failedPasswordAttempts);
                if (this.failedPasswordAttempts >= 3)
                    return "exit_max_attempts";
                return "\n外交密码无效。\n" + paramString1;
            }
            Utils.VibratePhone(this.context);
            this.failedPasswordAttempts = (1 + this.failedPasswordAttempts);
            if (this.failedPasswordAttempts >= 3)
                return "exit_max_attempts";
            return "\n密码无效\n" + paramString1;
            label1256:
            if (j == 2)
                str2 = "february";
            if (j == 3)
                str2 = "march";
            if (j == 4)
                str2 = "april";
            if (j == 5)
                str2 = "may";
            if (j == 6)
                str2 = "june";
            if (j == 7)
                str2 = "july";
            if (j == 8)
                str2 = "august";
            if (j == 9)
                str2 = "september";
            if (j == 10)
                str2 = "october";
            if (j != 11)
                continue;
            str2 = "november";
        }
    }

    private String checkForPrisonerCommands(String paramString1, String paramString2) {
        if (!getCurrentSystem().startsWith("pdb"))
            return "";
        String str1 = "";
        String str2 = getCurrentSystem();
        String str3 = aliasCheck(paramString2);
        if (str3.equals("exit")) {
            str1 = this.DB.getSystemMessage("pdb", "exit");
            removeSystem();
            paramString1 = getCurrentPrompt();
            Utils.changeFont(this.context, this.tvScreen, "normal");
        }
        while (str1.equals("")) {
            return "";
            if (str3.equals("release")) {
                str1 = " usage: release [prisoner number]";
                continue;
            }
            if (str3.startsWith("release ")) {
                int j = Integer.valueOf(str3.replace("release ", "").replace(" ", "")).intValue();
                if (j == this.CONST_PRISONER_NUMBER) {
                    str1 = this.DB.getFileContents(str2, getCurrentUser(), "/", "free!", this.currentLevel).replace("|", "\n");
                    continue;
                }
                if (j == 0) {
                    str1 = "囚犯编号无效";
                    continue;
                }
                if (j < this.CONST_PRISONER_NUMBER) {
                    str1 = "囚犯" + String.valueOf(j) + " 无法被释放，他们已经被处决了。";
                    continue;
                }
                if (j <= this.CONST_PRISONER_NUMBER)
                    continue;
                str1 = "无法释放的囚犯编号" + String.valueOf(j) + "。囚犯不存在。";
                continue;
            }
            if (str3.equals("term")) {
                str1 = " usage: term [prisoner number]";
                continue;
            }
            if (!str3.startsWith("term "))
                continue;
            int i = Integer.valueOf(str3.replace("term ", "").replace(" ", "")).intValue();
            if (i == this.CONST_PRISONER_NUMBER) {
                str1 = "囚犯" + String.valueOf(i) + " 已被排入处决日程。";
                continue;
            }
            if (i == 0) {
                str1 = "囚犯编号无效";
                continue;
            }
            if (i < this.CONST_PRISONER_NUMBER) {
                str1 = "囚犯" + String.valueOf(i) + " 已经被处决了。";
                continue;
            }
            if (i <= this.CONST_PRISONER_NUMBER)
                continue;
            str1 = "无法处决的囚犯编号" + String.valueOf(i) + "。囚犯不存在。";
        }
        return new StringBuilder(String.valueOf(new StringBuilder("\n").append(str1).toString())).append("\n").toString() + paramString1;
    }

    private String checkForQueryCommands(String paramString1, String paramString2) {
        String str1 = getCurrentSystem();
        if (!str1.startsWith("govt_query"))
            return "";
        Object localObject = "";
        String str2 = aliasCheck(paramString2);
        if (str2.startsWith("select * from "))
            if (str2.startsWith("select * from master"))
                localObject = this.DB.getFileContents(str1, getCurrentUser(), "/", "select * from master", this.currentLevel);
        while (((String) localObject).equals("")) {
            return "";
            String str4 = this.DB.getFileContents(str1, getCurrentUser(), "/", str2, this.currentLevel);
            if ((str4 != null) && (!str4.equals(""))) {
                localObject = str4;
                continue;
            }
            localObject = "Unknown table name";
            continue;
            if (str2.equals("exit")) {
                localObject = this.DB.getSystemMessage("govt_query", "exit");
                removeSystem();
                paramString1 = getCurrentPrompt();
                Utils.changeFont(this.context, this.tvScreen, "normal");
                continue;
            }
            String str3 = this.DB.getFileContents(str1, getCurrentUser(), "/", str2, this.currentLevel);
            if ((str3 == null) || (str3.equals("")))
                continue;
            localObject = str3;
        }
        return (String) (new StringBuilder(String.valueOf(new StringBuilder("\n").append((String) localObject).toString())).append("\n").toString() + paramString1);
    }

    private String checkForRadioCommands(String paramString1, String paramString2) {
        if (!getCurrentSystem().startsWith("radio"))
            return "";
        Object localObject = "";
        String str1 = getCurrentSystem();
        String str2 = getCurrentPrompt();
        String str3 = aliasCheck(paramString2);
        if (str3.equals("logs"))
            localObject = this.DB.getFileContents(str1, getCurrentUser(), "/", "logs", this.currentLevel);
        while (((String) localObject).equals("")) {
            return "";
            if ((str3.startsWith("2")) || (str3.startsWith("3")) || (str3.startsWith("4"))) {
                String str4 = this.DB.getFileContents(str1, getCurrentUser(), "/", str3, this.currentLevel);
                if (str4.equals("")) ;
                for (String str5 = "没有消息的编号为：" + str3; ; str5 = str4) {
                    localObject = str5.replace("^", "\n");
                    break;
                }
            }
            if (str3.equals("transmit")) {
                localObject = " usage: transmit [frequency]";
                continue;
            }
            if (str3.startsWith("transmit ")) {
                String str6 = str3.replace("transmit ", "").replace(" ", "");
                if (str6.equals("883.34")) {
                    this.transmitMessage = true;
                    localObject = "正在请求向883.34发送";
                    paramString1 = getCurrentPrompt();
                    continue;
                }
                String str7 = this.DB.getFileContents(str1, getCurrentUser(), "/", str6, this.currentLevel);
                if (str7.equals("")) {
                    localObject = "没有响应的频率:  " + str6;
                    continue;
                }
                localObject = str7;
                continue;
            }
            if ((str2.endsWith("(y/n)? ")) && (str3.equals("n"))) {
                this.transmitMessage = false;
                localObject = "向限制频率883.34传送失败。";
                paramString1 = getCurrentPrompt();
                continue;
            }
            if ((str2.endsWith("(y/n)? ")) && (str3.equals(""))) {
                this.transmitMessage = false;
                localObject = " ";
                paramString1 = getCurrentPrompt();
                continue;
            }
            if ((str2.endsWith("(y/n)? ")) && (str3.equals("y"))) {
                this.transmitMessage = false;
                localObject = this.DB.getFileContents(str1, getCurrentUser(), "/", "883.34", this.currentLevel);
                continue;
            }
            if (!str2.endsWith("(y/n)? "))
                continue;
            localObject = "Enter y or n";
        }
        return (String) (new StringBuilder(String.valueOf(new StringBuilder("\n").append((String) localObject).toString())).append("\n").toString() + paramString1);
    }

    private String checkForRepsCommands(String paramString1, String paramString2) {
        if (!getCurrentSystem().startsWith("reps"))
            return "";
        String str1 = "";
        String str2 = getCurrentSystem();
        String str3 = aliasCheck(paramString2);
        if ((str2.endsWith("mail")) && (str3.equals("exit"))) {
            str1 = this.DB.getSystemMessage("reps_mail", "exit").replace("[LOGIN_NAME]", getCurrentUser());
            removeSystem();
            paramString1 = getCurrentPrompt();
        }
        while (str1.equals("")) {
            return "";
            if ((!str2.startsWith("reps")) || (!str3.equals("exit")))
                continue;
            str1 = this.DB.getSystemMessage("reps", "exit").replace("[LOGIN_NAME]", getCurrentUser());
            removeSystem();
            paramString1 = getCurrentPrompt();
            Utils.changeFont(this.context, this.tvScreen, "normal");
        }
        return new StringBuilder(String.valueOf(new StringBuilder("\n").append(str1).toString())).append("\n").toString() + paramString1;
    }

    private String checkForRobotCommands(String paramString1, String paramString2) {
        if (!getCurrentSystem().startsWith("govt_robot"))
            return "";
        String str1 = "";
        String str2 = aliasCheck(paramString2);
        if (str2.equals("coms"))
            str1 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("系统组件和编号：|")).append("     传送带: belt|").toString())).append("      机械臂: arms|").toString())).append("   部署坡道: ramp|").toString() + " 装载机械: load";
        while (str1.equals("")) {
            return "";
            if (str2.equals("exit")) {
                str1 = this.DB.getSystemMessage("govt_robot", "exit");
                removeSystem();
                paramString1 = getCurrentPrompt();
                Utils.changeFont(this.context, this.tvScreen, "normal");
                continue;
            }
            if (str2.equals("sdwn")) {
                String str5 = this.CONST_ROBOT_VERSION_PREFIX + "1";
                if (getCurrentRobotVersion().equals(str5)) {
                    str1 = "此版本不支持的功能。";
                    continue;
                }
                if (Utils.getStringFromPrefs(this.context, "robot_status").equals("Active")) {
                    str1 = "无法成功关闭XJ5a12 R.O.B.O.T.系统。请确保系统所有组件都已经成功关闭。";
                    continue;
                }
                if (Utils.getStringFromPrefs(this.context, "robot_status").equals("Shutdown")) {
                    str1 = "无法成功关闭XJ5a12 R.O.B.O.T.系统。XJ5a12 R.O.B.O.T.系统已经关闭。";
                    continue;
                }
                if (Utils.getStringFromPrefs(this.context, "robot_status").equals("Pending")) {
                    str1 = "无法成功关闭XJ5a12 R.O.B.O.T.系统。XJ5a12 R.O.B.O.T.系统已经关闭。";
                    Log.e("Game", "不该来这儿...");
                    continue;
                }
                if (Utils.getStringFromPrefs(this.context, "robot_status").equals("Ready")) {
                    Utils.setStringInPrefs(this.context, "Shutdown", "robot_status");
                    str1 = "XJ5a12 R.O.B.O.T.系统已经成功关闭。|此次关闭属于警告，未授权或计划外的关闭。已向技术安保代表<16.2222.900>发送报告。";
                    continue;
                }
                Log.e("Game", "不该来这儿...");
                continue;
            }
            if (str2.equals("stus")) {
                str1 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("系统状态:|")).append(" R.O.B.O.T. 系统: ").append(Utils.getStringFromPrefs(this.context, "robot_status")).append("|").toString())).append("     传送带:：").append(Utils.getStringFromPrefs(this.context, "robot_coms_belt")).append("|").toString())).append("      机械臂：").append(Utils.getStringFromPrefs(this.context, "robot_coms_arms")).append("|").toString())).append("   部署坡道：").append(Utils.getStringFromPrefs(this.context, "robot_coms_ramp")).append("|").toString() + " 装载机械：" + Utils.getStringFromPrefs(this.context, "robot_coms_load");
                continue;
            }
            if (str2.startsWith("togl")) {
                if (str2.equals("togl")) {
                    str1 = "Usage: togl [component code]";
                    continue;
                }
                String str4 = str2.replace("togl ", "");
                if (str4.equals("belt"))
                    if (Utils.getStringFromPrefs(this.context, "robot_coms_belt").equals("Active")) {
                        int n = 1;
                        if (Utils.getStringFromPrefs(this.context, "robot_coms_arms").equals("Active"))
                            n = 0;
                        if (Utils.getStringFromPrefs(this.context, "robot_coms_ramp").equals("Active"))
                            n = 0;
                        if (Utils.getStringFromPrefs(this.context, "robot_coms_load").equals("Active"))
                            n = 0;
                        if (n == 0)
                            str1 = "组件操作失误。无法再相依系统还开启的情况下关闭该组件。";
                    }
                while (true) {
                    int j = 1;
                    if (Utils.getStringFromPrefs(this.context, "robot_coms_belt").equals("Active"))
                        j = 0;
                    if (Utils.getStringFromPrefs(this.context, "robot_coms_arms").equals("Active"))
                        j = 0;
                    if (Utils.getStringFromPrefs(this.context, "robot_coms_ramp").equals("Active"))
                        j = 0;
                    if (Utils.getStringFromPrefs(this.context, "robot_coms_load").equals("Active"))
                        j = 0;
                    if (j == 0)
                        break;
                    Utils.setStringInPrefs(this.context, "Ready", "robot_status");
                    str1 = str1 + "\nR.O.B.O.T已做好关闭准备";
                    break;
                    Utils.setStringInPrefs(this.context, "Shutdown", "robot_coms_belt");
                    str1 = "传送带已关闭";
                    continue;
                    Utils.setStringInPrefs(this.context, "Active", "robot_coms_belt");
                    str1 = "传送带已开启";
                    continue;
                    if (str4.equals("arms")) {
                        if (Utils.getStringFromPrefs(this.context, "robot_coms_arms").equals("Active")) {
                            int m = 1;
                            if (Utils.getStringFromPrefs(this.context, "robot_coms_ramp").equals("Active"))
                                m = 0;
                            if (Utils.getStringFromPrefs(this.context, "robot_coms_load").equals("Active"))
                                m = 0;
                            if (m == 0) {
                                str1 = "组件操作失误。无法再相依系统还开启的情况下关闭该组件。";
                                continue;
                            }
                            Utils.setStringInPrefs(this.context, "Shutdown", "robot_coms_arms");
                            str1 = "机械臂已关闭";
                            continue;
                        }
                        Utils.setStringInPrefs(this.context, "Active", "robot_coms_arms");
                        str1 = "机械臂已开启";
                        continue;
                    }
                    if (str4.equals("ramp")) {
                        if (Utils.getStringFromPrefs(this.context, "robot_coms_ramp").equals("Active")) {
                            int k = 1;
                            if (Utils.getStringFromPrefs(this.context, "robot_coms_load").equals("Active"))
                                k = 0;
                            if (k == 0) {
                                str1 = "组件操作失误。无法再相依系统还开启的情况下关闭该组件。";
                                continue;
                            }
                            Utils.setStringInPrefs(this.context, "Shutdown", "robot_coms_ramp");
                            str1 = "部署坡道已关闭";
                            continue;
                        }
                        Utils.setStringInPrefs(this.context, "Active", "robot_coms_ramp");
                        str1 = "部署坡道已开启";
                        continue;
                    }
                    if (str4.equals("load")) {
                        if (Utils.getStringFromPrefs(this.context, "robot_coms_load").equals("Active")) {
                            Utils.setStringInPrefs(this.context, "Shutdown", "robot_coms_load");
                            str1 = "装载机械已关闭";
                            continue;
                        }
                        Utils.setStringInPrefs(this.context, "Active", "robot_coms_load");
                        str1 = "装载机械已开启";
                        continue;
                    }
                    str1 = "位置组件编号";
                }
            }
            if (str2.startsWith("upgr")) {
                int i = Utils.getIntFromPrefs(this.context, "robot_version");
                String str3 = this.CONST_ROBOT_VERSION_PREFIX + "2";
                if (i == 1) {
                    if (str2.equals("upgr")) {
                        str1 = "Usage: upgr [version number] where version number is the release to upgrade to";
                        continue;
                    }
                    if (str2.endsWith(" " + str3)) {
                        Utils.setIntInPrefs(this.context, 2, "robot_version");
                        str1 = "R.O.B.O.T.系统已经升级至最新版本" + str3 + "!";
                        continue;
                    }
                    str1 = "无法升级R.O.B.O.T.系统。版本编号无效。";
                    continue;
                }
                str1 = "R.O.B.O.T.系统已经升级至最新版本";
                continue;
            }
            if (!str2.equals("vrsn"))
                continue;
            str1 = "当前版本：" + getCurrentRobotVersion();
        }
        return new StringBuilder(String.valueOf(new StringBuilder("\n").append(str1).toString())).append("\n").toString() + paramString1;
    }

    private String checkForUplinkCommands(String paramString1, String paramString2) {
        if (!getCurrentSystem().equals("uplink"))
            return "";
        String str = "";
        if (aliasCheck(paramString2).equals("exit")) {
            str = this.DB.getSystemMessage("uplink", "exit");
            removeSystem();
            paramString1 = getCurrentPrompt();
            Utils.changeFont(this.context, this.tvScreen, "normal");
        }
        if (str.equals(""))
            return "";
        return new StringBuilder(String.valueOf(new StringBuilder("\n").append(str).toString())).append("\n").toString() + paramString1;
    }

    private String getCurrentRobotVersion() {
        int i = PreferenceManager.getDefaultSharedPreferences(this.context).getInt("robot_version", 1);
        String str = this.CONST_ROBOT_VERSION_PREFIX + "1";
        if (i == 2)
            str = this.CONST_ROBOT_VERSION_PREFIX + "2";
        return str;
    }

    private String insertSuccessMessage(String paramString1, String paramString2) {
        if (paramString2 == null)
            return paramString1;
        if (paramString2.equals(""))
            return paramString1;
        String str1 = "\n";
        int i = paramString1.lastIndexOf(str1);
        if (i < 0) {
            str1 = "|";
            i = paramString1.lastIndexOf(str1);
        }
        if ((i > 0) && (i < paramString1.length())) ;
        for (String str2 = paramString1.substring(0, i) + paramString2 + paramString1.substring(i + str1.length(), paramString1.length()); ; str2 = paramString1 + paramString2)
            return str2;
    }

    public void addSystem(String paramString) {
        this.systemStack.add(paramString);
        this.failedPasswordAttempts = 0;
        String str = "guest";
        if (paramString.equals("local"))
            str = "hacker";
        if (paramString.equals("hackroutine"))
            str = "hacker";
        if (paramString.equals("backdoor"))
            str = "hacker";
        this.userStack.add(str);
    }

    void addSystemWithUsername(String paramString1, String paramString2) {
        this.systemStack.add(paramString1);
        this.userStack.add(paramString2);
    }

    public void advanceToNextLevel() {
        this.currentLevel = (1 + this.currentLevel);
        Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
        localEditor.putInt("hr__current_level", this.currentLevel);
        localEditor.commit();
    }

    public String checkForLevelCommands(String paramString1, String paramString2) {
        String str1 = "";
        String str2 = getCurrentSystem();
        if (this.currentLevel == 2)
            if ((!paramString2.equals("help")) && (!paramString2.equals("h"))) {
                if (!paramString2.equals(""))
                    break label63;
                str1 = "\n" + paramString1;
            }
        while (true) {
            return str1;
            label63:
            str1 = "\n无法识别的命令：" + paramString2 + "\n" + paramString1;
            continue;
            if (this.currentLevel == 3) {
                if ((!paramString2.equals("run")) && (!paramString2.equals("r")))
                    continue;
                str1 = "\n无法识别的命令：" + paramString2 + "\n" + paramString1;
                continue;
            }
            if ((this.currentLevel == 41) && (str2.equals("local"))) {
                if (!paramString2.equals("get ufo"))
                    continue;
                str1 = "\n已成功下载并安装UFO程序\n" + paramString1;
                String str3 = checkForSuccess(paramString2);
                if (str3.equals(""))
                    continue;
                str1 = insertSuccessMessage(str1, str3);
                continue;
            }
            if ((this.currentLevel >= 46) && ((paramString2.equals("voicemail")) || (paramString2.equals("v"))) && (str2.equals("local"))) {
                if (!this.runningUnitTests)
                    playAudio(this.context, "yurgon");
                if (this.currentLevel == 46)
                    advanceToNextLevel();
                str1 = "\n正在播放语音消息...[<REMOVE_EMAIL_ICON>]\n" + getCurrentPrompt();
                continue;
            }
            if (this.currentLevel == 46) {
                if ((paramString2.equals("exit")) || (paramString2.equals("e")))
                    return "[<EXIT>]";
                if ((paramString2.equals("help")) || (paramString2.equals("h")))
                    str1 = "\n" + this.DB.getHelpMenu("local");
                label393:
                if (this.systemStack.size() <= 1) ;
                while (true) {
                    if (this.userStack.size() <= 1) {
                        str1 = str1 + "\n*** 你有 (1) 封语音消息。输入'v'播放。\nlocalhost> ";
                        break;
                        this.systemStack.remove(-1 + this.systemStack.size());
                        break label393;
                    }
                    this.userStack.remove(-1 + this.userStack.size());
                }
            }
            if ((this.currentLevel < 49) || (!paramString2.equals("diplomacy")) || (!str2.startsWith("ufo")))
                continue;
            this.loginName = "";
            addSystemWithUsername("diplomacy", "guest");
            str1 = "\n正在连接星系外交系统\n" + getCurrentPrompt();
        }
    }

    public String checkForSuccess(String paramString) {
        String[] arrayOfString = this.DB.getSuccessInfo(this.currentLevel).split(Pattern.quote("|"), -1);
        String str11;
        if (arrayOfString.length != 5)
            str11 = "";
        String str7;
        String str8;
        while (true) {
            return str11;
            String str1 = arrayOfString[1];
            String str2 = arrayOfString[2];
            String str3 = arrayOfString[3];
            String str4 = arrayOfString[4];
            String str5 = str1.replace("^", "|");
            String str6 = getCurrentSystem();
            int i;
            if (this.currentLevel == 1)
                i = 1;
            while (i == 0) {
                return "";
                if (this.currentLevel == 2) {
                    boolean bool8 = paramString.equals("help");
                    i = 0;
                    if (!bool8)
                        continue;
                    i = 1;
                    continue;
                }
                if (paramString.equals("LOGGED IN")) {
                    boolean bool5 = getCurrentUser().equals(str3);
                    i = 0;
                    if (!bool5)
                        continue;
                    boolean bool6 = str6.equals(str5);
                    i = 0;
                    if (!bool6)
                        continue;
                    boolean bool7 = paramString.equals(str4);
                    i = 0;
                    if (!bool7)
                        continue;
                    i = 1;
                    continue;
                }
                if ((str6.equals(str5)) && (getCurrentPrompt().equals(str2))) {
                    boolean bool4 = str4.equals(paramString);
                    i = 0;
                    if (!bool4)
                        continue;
                    i = 1;
                    continue;
                }
                boolean bool1 = str6.replace("^", "|").equals(str5);
                i = 0;
                if (!bool1)
                    continue;
                boolean bool2 = getCurrentPrompt().equals(str2);
                i = 0;
                if (!bool2)
                    continue;
                boolean bool3 = str4.equals(paramString);
                i = 0;
                if (!bool3)
                    continue;
                i = 1;
            }
            if ((Utils.isAppFree(this.context)) && (this.currentLevel >= 16)) {
                Utils.setIntInPrefs(this.context, 1, "end_of_free_game");
                Utils.setIntInPrefs(this.context, 1, "show_buy_button");
                return "[<BUY_FULL_VERSION>]";
            }
            advanceToNextLevel();
            str7 = this.DB.getGoalMessage(this.currentLevel, "success").replace("^", "\n");
            str8 = "";
            if ((this.currentLevel == 13) || (this.currentLevel == 16) || (this.currentLevel == 22) || (this.currentLevel == 24) || (this.currentLevel == 34) || (this.currentLevel == 37) || (this.currentLevel == 40) || (this.currentLevel == 46) || (this.currentLevel == 51))
                str8 = "[<RECEIVED_EMAIL>]";
            if (this.currentLevel >= 51)
                break;
            String str10 = str7 + str8;
            str11 = "[ALLPAUSE]\n------------------------------\n成功到达关卡" + String.valueOf(this.currentLevel) + "\n" + str10 + "\n------------------------------\n";
            if (this.currentLevel == 2)
                return str11 + "\n" + getCurrentPrompt();
        }
        String str9 = str7 + str8;
        return "[ALLPAUSE]\n------------------------------\n达成最终目标\n" + str9 + "\n------------------------------\n";
    }

    public String getCurrentPrompt() {
        label20:
        String str2;
        if (this.currentLevel == 46)
            if (this.systemStack.size() <= 1) {
                if (this.userStack.size() > 1)
                    break label57;
                str2 = "*** 你有 (1) 封语音消息。输入'v'播放。\nlocalhost> ";
            }
        label57:
        do {
            do {
                return str2;
                this.systemStack.remove(-1 + this.systemStack.size());
                break;
                this.userStack.remove(-1 + this.userStack.size());
                break label20;
                if ((this.currentLevel >= 48) && (this.transmitMessage))
                    return "确定要向限制频率883.34发送信息 (y/n)? ";
                if ((this.currentLevel == 50) && (this.sendCoordinates))
                    return "确定要向Zyrgorkn种族发送当前坐标 (y/n)? ";
                String str1 = getCurrentSystem();
                str2 = this.DB.getPromptBySystem(str1);
                if ((str1.equals("govt")) && (getCurrentUser().equals("guest")))
                    return "地址：";
                if ((str1.equals("uplink")) && (getCurrentUser().equals("guest")))
                    return "passcode: ";
                if ((!str1.equals("diplomacy")) || (!getCurrentUser().equals("guest")))
                    continue;
                if (this.loginName.equals(""))
                    return "alien race: ";
                if (this.loginName.equals("zyrgorkn"))
                    return "外交密码：";
            }
            while (!getCurrentUser().equals("guest"));
            str2 = "username: ";
        }
        while ((this.loginName == null) || (this.loginName.equals("")));
        return "password: ";
    }

    public String getCurrentSystem() {
        if (this.systemStack.size() <= 0)
            return "";
        return (String) this.systemStack.get(-1 + this.systemStack.size());
    }

    public String getCurrentUser() {
        int i = this.userStack.size();
        if (i <= 0) {
            Log.e("Game", "不该来这儿——无用户");
            return "";
        }
        return (String) this.userStack.get(i - 1);
    }

    boolean isValidUser(String paramString1, String paramString2) {
        return (paramString2.equals("inst")) && (paramString1.equals("jan"));
    }

    public void playAudio(Context paramContext, String paramString) {
        try {
            this.mediaPlayer = MediaPlayer.create(paramContext, 2130968576);
            this.mediaPlayer.start();
            return;
        } catch (IllegalArgumentException localIllegalArgumentException) {
            localIllegalArgumentException.printStackTrace();
            Log.e("Game", "播放音频文件出现错误");
            return;
        } catch (SecurityException localSecurityException) {
            while (true)
                localSecurityException.printStackTrace();
        } catch (IllegalStateException localIllegalStateException) {
            while (true)
                localIllegalStateException.printStackTrace();
        }
    }

    public String processInput(String paramString1, String paramString2) {
        stopAudio();
        String str1 = getCurrentPrompt();
        String str2 = checkForLevelCommands(str1, paramString2);
        String str20;
        if (!str2.equals(""))
            str20 = str2;
        String str21;
        do {
            return str20;
            String str3 = "";
            String str4 = checkForLogin(str1, paramString2);
            if (str4.equals("exit")) {
                paramString2 = str4;
                str4 = "";
            }
            while (!str4.equals("")) {
                if ((getCurrentSystem().startsWith("diplomacy")) && (!getCurrentUser().equals("guest")))
                    Utils.changeFont(this.context, this.tvScreen, "alien");
                return str4;
                if (!str4.equals("exit_max_attempts"))
                    continue;
                str3 = "\n已达到最大尝试次数";
                paramString2 = "exit";
                str4 = "";
            }
            if (paramString2.equals(""))
                return "\n" + str1;
            String str5 = "unrecognized command: '" + paramString2 + "'";
            if (str1.equals("HR.search> "))
                str5 = "unknown employee or associate: '" + paramString2 + "'";
            String str6 = aliasCheck(paramString2);
            String str7 = checkForDiplomacyCommands(str1, str6);
            if (!str7.equals("")) {
                String str33 = checkForSuccess(str6);
                if (!str33.equals(""))
                    str7 = insertSuccessMessage(str7, str33);
                return str7;
            }
            String str8 = checkForRadioCommands(str1, str6);
            if (!str8.equals("")) {
                String str32 = checkForSuccess(str6);
                if (!str32.equals(""))
                    str8 = insertSuccessMessage(str8, str32);
                return str8;
            }
            String str9 = checkForPrisonerCommands(str1, str6);
            if (!str9.equals("")) {
                String str31 = checkForSuccess(str6);
                if (!str31.equals(""))
                    str9 = insertSuccessMessage(str9, str31);
                return str9;
            }
            String str10 = checkForUplinkCommands(str1, str6);
            if (!str10.equals("")) {
                String str30 = checkForSuccess(str6);
                if (!str30.equals(""))
                    str10 = insertSuccessMessage(str10, str30);
                return str10;
            }
            String str11 = checkForRobotCommands(str1, str6);
            if (!str11.equals("")) {
                String str29 = checkForSuccess(str6);
                if (!str29.equals(""))
                    str11 = insertSuccessMessage(str11, str29);
                return str11;
            }
            String str12 = checkForQueryCommands(str1, str6);
            if (!str12.equals("")) {
                String str28 = checkForSuccess(str6);
                if (!str28.equals(""))
                    str12 = insertSuccessMessage(str12, str28);
                return str12;
            }
            String str13 = checkForAdminCommands(str1, str6);
            if (!str13.equals("")) {
                String str27 = checkForSuccess(str6);
                if (!str27.equals(""))
                    str13 = insertSuccessMessage(str13, str27);
                return str13;
            }
            String str14 = checkForRepsCommands(str1, str6);
            if (!str14.equals("")) {
                String str26 = checkForSuccess(str6);
                if (!str26.equals(""))
                    str14 = insertSuccessMessage(str14, str26);
                return str14;
            }
            String str15 = checkForHrCommands(str1, str6);
            if (!str15.equals("")) {
                String str25 = checkForSuccess(str6);
                if (!str25.equals(""))
                    str15 = insertSuccessMessage(str15, str25);
                return str15;
            }
            String str16 = checkForDEBUGGINGCommands(str1, str6);
            if (!str16.equals("")) {
                String str24 = checkForSuccess(str6);
                if (!str24.equals(""))
                    str16 = insertSuccessMessage(str16, str24);
                return str16;
            }
            String str17 = str6.split(Pattern.quote(" "), -1)[0];
            boolean bool = this.DB.isInputValid(str17, getCurrentSystem(), str1, getCurrentUser(), this.currentLevel);
            if (str1.equals("password: "))
                bool = true;
            if ((str1.equals("地址：")) && (str17.equals("exit")))
                bool = true;
            if (!bool) {
                if (str6.startsWith("talk"))
                    str5 = "你是想要输入吗？";
                return new StringBuilder(String.valueOf(new StringBuilder("\n").append(str5).toString())).append("\n").toString() + str1;
            }
            String str18 = checkForDatabaseCommands(str1, str6);
            if (!str18.equals("")) {
                if (str6.equals("exit")) {
                    if (getCurrentSystem().equals("hackroutine"))
                        str3 = str3 + "\nType 'atip' for a tip";
                    str18 = str3 + str18;
                }
                String str23 = checkForSuccess(str6);
                if (!str23.equals(""))
                    str18 = insertSuccessMessage(str18, str23);
                return str18;
            }
            String str19 = checkForCommonCommands(str1, str6);
            if (!str19.equals("")) {
                String str22 = checkForSuccess(str6);
                if (!str22.equals(""))
                    return insertSuccessMessage(str19, str22);
                if (str6.equals("exit"))
                    str19 = str19 + str19;
                return str19;
            }
            Log.e("Game", "SHOULDN'T GET HERE - COMMAND EXISTS WITH NO LOGIC?");
            str20 = "\n" + str5;
            if (1 != 0)
                str20 = new StringBuilder(String.valueOf(str20)).append("\n").toString() + str1;
            str21 = checkForSuccess(str6);
        }
        while (str21.equals(""));
        return insertSuccessMessage(str20, str21);
    }

    public boolean removeSystem() {
        if (this.systemStack.size() > 0)
            this.systemStack.remove(-1 + this.systemStack.size());
        if (this.userStack.size() > 0)
            this.userStack.remove(-1 + this.userStack.size());
        return this.systemStack.size() <= 0;
    }

    void replaceSystem(String paramString) {
        this.systemStack.set(-1 + this.systemStack.size(), paramString);
    }

    void replaceUser(String paramString) {
        if (this.userStack.size() > 0)
            this.userStack.remove(-1 + this.userStack.size());
        this.userStack.add(paramString);
    }

    public void resetState() {
        if (this.runningUnitTests)
            this.achDisplayMode = "inline";
        this.failedPasswordAttempts = 0;
        this.isPaused = false;
        this.lastInput = "";
        this.loginName = "";
        this.pauseSegment = 0;
        this.pauseSegments = null;
        this.sendCoordinates = false;
        this.transmitMessage = false;
        while (true) {
            if (this.systemStack.size() <= 1)
                return;
            removeSystem();
        }
    }

    public void stopAudio() {
        if ((this.mediaPlayer != null) && (this.mediaPlayer.isPlaying()))
            this.mediaPlayer.stop();
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.Classes.Game
 * JD-Core Version:    0.6.0
 */