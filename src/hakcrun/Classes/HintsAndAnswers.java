package hakcrun.Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class HintsAndAnswers {
    public static String getAnswer(Context paramContext, int paramInt) {
        int i = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("end_of_free_game", -1);
        if ((Utils.isAppFree(paramContext)) && (paramInt == 16) && (i == 1))
            return "你已经完成了免费版本的所有关卡。\n下载虚拟入侵完整版，还有超过50个关卡等着你哦！\n";
        if ((!Utils.isAppFree(paramContext)) && (paramInt == 52))
            return "你获胜了！你成功的赶走了外星人并向世界揭露了RUN的秘密。游戏结束！\n";
        String str = getRawHintOrAnswer(false, paramInt);
        if (str.equals(""))
            return "此关卡没有答案。请根据已知信息完成关卡。";
        return "Answer to reach level " + String.valueOf(paramInt + 1) + ":\n\n" + str + "\n";
    }

    public static String getHint(Context paramContext, int paramInt) {
        int i = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("end_of_free_game", -1);
        if ((Utils.isAppFree(paramContext)) && (paramInt == 16) && (i == 1))
            return "你已经完成了免费版本的所有关卡。\n下载虚拟入侵完整版，还有超过50个关卡等着你哦！\n";
        if ((!Utils.isAppFree(paramContext)) && (paramInt == 52))
            return "你获胜了！你成功的赶走了外星人并向世界揭露了RUN的秘密。游戏结束！\n";
        String str = getRawHintOrAnswer(true, paramInt);
        if (str.equals(""))
            return "此关卡没有答案。请根据已知信息完成关卡。";
        return "Hint to reach level " + String.valueOf(paramInt + 1) + ":\n\n" + str + "\n";
    }

    public static String getRawHintOrAnswer(boolean paramBoolean, int paramInt) {
        if (paramInt == 0) {
            if (paramBoolean)
                return "运行app并接受雇主提议。";
            return "运行app并接受雇主提议。";
        }
        int i = 0 + 1;
        if (paramInt == i) {
            if (paramBoolean)
                return "点击'开始入侵'按钮。";
            return "点击'开始入侵'按钮。";
        }
        int j = i + 1;
        if (paramInt == j) {
            if (paramBoolean)
                return "你开启了你的本地基地，现在你需要在上面输入命令。\n现在输入'help'查看命令列表。";
            return "在本地系统界面，输入'help'或'h'（无需引号）。";
        }
        int k = j + 1;
        if (paramInt == k) {
            if (paramBoolean)
                return "你已经在你的本地机器上运行了'help'命令，现在你需要继续输入命令以查找你需要的信息。帮助命令中有一个'ls'命令可以列出文件，另一个'type'命令可以输出或显示文件的内容。有的命令无需任何参数，例如'help'或'ls'。而另一些命令则需要参数，例如'type'，所以你需要输入'type 文件名'，你可以再文件列表中查找到你需要的文件名。";
            return "在本地系统界面中，输入'type readme'或't readme'（无需引号）。";
        }
        int m = k + 1;
        if (paramInt == m) {
            if (paramBoolean)
                return "你的雇主刚刚在你的本地系统安装了一个黑客程序（一个电脑程序）。你需要运行或'run'该程序。";
            return "在本地系统界面，输入'run'或'r'（无需引号）。";
        }
        int n = m + 1;
        if (paramInt == n) {
            if (paramBoolean)
                return "这个黑客程序是一个可以让你连接到组织系统的电脑程序。你可以在这个黑客程序是使用一些命令，输入'help'查看使用该程序的方法。";
            return "在本地系统界面，输入'gate'或'g'（无需引号）。";
        }
        int i1 = n + 1;
        if (paramInt == i1) {
            if (paramBoolean)
                return "你已经连接了组织的电脑系统，但是你还需要登陆。你的雇主在黑客程序中留下了一个名叫'atip'的贴士。这应该可以帮你找到登陆的方法。如果你还是不能找到密码（password），那就想想人们一般都会使用什么作为密码（password）。";
            return "在网关登录界面中，输入'alice'和'password'（无需引号）。";
        }
        int i2 = i1 + 1;
        if (paramInt == i2) {
            if (paramBoolean)
                return "请先阅读网关的'welcome'文件。这个文件里面有关于你下一步需要做的内容。";
            return "在网关系统中，输入'jump alice'或'j alice'，然后输入'password'作为她的密码（无需引号）。 ";
        }
        int i3 = i2 + 1;
        if (paramInt == i3) {
            if (paramBoolean)
                return "现在你已经知道Alice在系统上有个工作站，那别的员工应该也有。试着猜测他们的密码然后连接他们的工作站吧。她的邮件里面有所有这个关卡需要的密码。";
            return "回到网关系统，输入'jump brian'或'j brian'，然后输入'baseball'作为密码（无需引号）。 还有注意要输入的是'brian'不是'brain'。";
        }
        int i4 = i3 + 1;
        if (paramInt == i4) {
            if (paramBoolean)
                return "通过阅读Brian的邮件，你知道了Cathy的喜好，继续挖掘Cathy的密码吧。";
            return "在网关系统中，输入'jump cathy'或'j cathy'，然后输入'love'作为她的密码（无需引号）。";
        }
        int i5 = i4 + 1;
        if (paramInt == i5) {
            if (paramBoolean)
                return "在Cahty的一封邮件中，她提到了一位名叫David的同事。她相信他想要向Brian看齐，快去看看他们两个现在到底有多么相像了。";
            return "在网关系统中，输入'jump david'或'j david'，然后输入'baseball'作为他的密码（无需引号）。";
        }
        int i6 = i5 + 1;
        if (paramInt == i6) {
            if (paramBoolean)
                return "在David的工作站，你应该能发现他提到说公司的网站是php.pujiahh.com/reusingnature/。你登陆了那个网站后应该可以找到Elise的密码然后登陆她的工作站。";
            return "在网关系统中，输入'jump elise'然后'hireme' （无需引号）。";
        }
        int i7 = i6 + 1;
        if (paramInt == i7) {
            if (paramBoolean)
                return "查看Elise的系统去找你的下一个线索。没有你想象的那么难哦。";
            return "在Elise的邮箱程序中，输入'show frank'或's frank' （无需引号）。";
        }
        int i8 = i7 + 1;
        if (paramInt == i8) {
            if (paramBoolean)
                return "你刚刚收到了来自雇主的邮件。回到你的本地系统然后运行你的邮箱程序查看吧。这个消息中就有关于你下一步的线索。";
            return "在你的本地系统中，输入'run'或'r'，然后'hr'，'elise'，'hireme' （无需引号）。";
        }
        int i9 = i8 + 1;
        if (paramInt == i9) {
            if (paramBoolean)
                return "你已经进入了RUN的人力资源系统，使用'help'命令查看这个系统上有什么程序。回顾Elise给Frank的邮件查找你的下一个线索。还有，别忘了Frank也有自己的工作站。";
            return "在黑客界面中，模仿Alice登入网关系统。然后输入'jump frank'，然后输入'11111971' （无需引号）跳转至Frank的工作站。";
        }
        int i10 = i9 + 1;
        if (paramInt == i10) {
            if (paramBoolean)
                return "查看Frank的系统去找你的下一个线索。没有你想象的那么难哦。";
            return "在Frank的邮箱程序中，输入'show elise'或's elise' （无需引号）。";
        }
        int i11 = i10 + 1;
        if (paramInt == i11) {
            if (paramBoolean)
                return "从Frank的邮件你能看出所有销售代表的用户名不是他们的名。现在想想他们的用户名还能是什么组合呢（首字母，结合名/姓...）。";
            return "在黑客程序中，输入'reps'然后'gford'然后'may' （无需引号）。";
        }
        int i12 = i11 + 1;
        if (paramInt == i12) {
            if (paramBoolean)
                return "通过查看Gayle给Hank的邮件，你会发现一个线索。别忘了你能通过使用'web'或'w'命令开启浏览器。如果你不清楚用户名的话，试着根据之前的用户名推理一下。注意，以后的用户名可能就不是这些了...";
            return "Hank的用户名和密码分别是'hhamm'和'may' （无需引号）。 确保你使用'reps'连接了他的销售代表系统，而不是跳转到他的工作站。";
        }
        int i13 = i12 + 1;
        if (paramInt == i13) {
            if (paramBoolean)
                return "在Hank的邮件中有个关于下一个销售代表的线索。想想美国最常用的姓氏然后解开这个谜题吧。";
            return "Irene的用户名和密码分别是'ismith'和'may' （无需引号）。";
        }
        int i14 = i13 + 1;
        if (paramInt == i14) {
            if (paramBoolean)
                return "查看Irene的系统去找你的下一个线索。没有你想象的那么难哦。";
            return "Julian的用户名和密码分别是'jrusso'和'may' （无需引号）。";
        }
        int i15 = i14 + 1;
        if (paramInt == i15) {
            if (paramBoolean)
                return "你需要找到下一位销售代表的用户名。他们的姓名都在其他的销售代表的邮件中提到了，还有你应该能从之前的用户名推断出首字母。";
            return "Kate的用户名和密码分别是'kpiet'和'may' （无需引号）。";
        }
        int i16 = i15 + 1;
        if (paramInt == i16) {
            if (paramBoolean)
                return "查看Kate的系统去找你的下一个线索。没有你想象的那么难哦。";
            return "在Kate的邮箱程序中输入'show larry'或's larry' （无需引号）。";
        }
        int i17 = i16 + 1;
        if (paramInt == i17) {
            if (paramBoolean)
                return "你的雇主又给你本地系统发了一封邮件，这封邮件和Kate给Larry的邮件应该可以带你走进下一关卡。";
            return "在你的黑客程序中输入'admin'，'larry'和'sex' （无需引号）。";
        }
        int i18 = i17 + 1;
        if (paramInt == i18) {
            if (paramBoolean)
                return "查看Larry的管理系统去找你的下一个线索。没有你想象的那么难哦。";
            return "在Larry的邮箱程序中输入'show alert'或's alert' （无需引号）。";
        }
        int i19 = i18 + 1;
        if (paramInt == i19) {
            if (paramBoolean)
                return "你的本地系统又收到了一封邮件。查看邮件进入下一关卡。";
            return "在本地邮箱程序中输入'show urgent'或's urgent' （无需引号）。";
        }
        int i20 = i19 + 1;
        if (paramInt == i20) {
            if (paramBoolean)
                return "Larry刚刚攻击了你的本地系统！他的攻击关闭了你的本地系统，所以你现在需要重新运行备用系统。";
            return "在主界面点击'开始入侵'按钮。";
        }
        int i21 = i20 + 1;
        if (paramInt == i21) {
            if (paramBoolean)
                return "查看本地系统去找你的下一个线索。没有你想象的那么难哦。";
            return "在你的本地邮箱程序中输入'show playme'或's playme'。";
        }
        int i22 = i21 + 1;
        if (paramInt == i22) {
            if (paramBoolean)
                return "你可以在Marie给你发的邮件中找到这关的线索。别忘了你可以使用'web'或'w'命令开启浏览器。你可能还需要在本地系统中输入'help'查看命令列表。";
            return "在你的本地系统输入'backdoor'或'b'，'govt'还有在Marie的php.pujiahh.com/gotonote/上找到的地址。";
        }
        int i23 = i22 + 1;
        if (paramInt == i23) {
            if (paramBoolean)
                return "Nigel给Olive的邮件里面有个'隐藏的'线索。仔细查看然后找线索吧。";
            return "在你的本地系统输入'backdoor'或'b'，'govt'还有在Nigel给Olive的消息上面找到的地址。向上滚动查看。";
        }
        int i24 = i23 + 1;
        if (paramInt == i24) {
            if (paramBoolean)
                return "Olive给Peter的邮件里面有通往下一关的线索。";
            return "在你的本地系统中输入'backdoor'或'b'，'govt'还有Olive给Peter的消息中的地址。";
        }
        int i25 = i24 + 1;
        if (paramInt == i25) {
            if (paramBoolean)
                return "Peter给Sasha的邮件里面有通往下一关的线索。";
            return "在你的本地系统输入'backdoor'或'b'，'govt'还有Peter给Sasha的消息中的地址。";
        }
        int i26 = i25 + 1;
        if (paramInt == i26) {
            if (paramBoolean)
                return "政府的QUERY系统包含了一个供RUN使用的数据库。为了在数据库中检索数据，你需要提交一个sql指令。输入'help sql'查看如何在这个系统中使用sql指令。";
            return "输入'select * from regions' （无需引号）以在数据库中检索数据。在结果中找寻地址以前往下一关。";
        }
        int i27 = i26 + 1;
        if (paramInt == i27) {
            if (paramBoolean)
                return "这关可能稍微有点儿难度，所以请准备好多花些时间。通往下一关需要两大步骤。首先，在php.pujiahh.com/vrgb/网站上收集到所有命令。 确保你查询的模型型号是正确的，还有就是并不是所有列出的命令都用得上。然后，使用那些命令关闭系统。那些命令需要以正确的顺序输入，还有就是系统消息对要进行的步骤非常有帮助。";
            return "输入这些命令：'upgr v37.502' 'togl load' 'togl ramp' 'togl arms' 'togl belt' 'sdwn'。然后使用显示的地址进入下一关。";
        }
        int i28 = i27 + 1;
        if (paramInt == i28) {
            if (paramBoolean)
                return "查看Sasha的系统去找你的下一个线索。没有你想象的那么难哦。";
            return "在Sasha的邮箱程序输入'show peter'或's peter'。";
        }
        int i29 = i28 + 1;
        if (paramInt == i29) {
            if (paramBoolean)
                return "你刚刚又从Marie那里收到一封邮件。查看以进入下一关卡。";
            return "在你的本地邮箱程序中输入'show idea'或's idea'。";
        }
        int i30 = i29 + 1;
        if (paramInt == i30) {
            if (paramBoolean)
                return "Marie给你名为'idea'的邮件里面有通往下一关的线索。";
            return "在你的本地系统中输入'backdoor'或'b'，然后'trace'。";
        }
        int i31 = i30 + 1;
        if (paramInt == i31) {
            if (paramBoolean)
                return "查看你的雇主的系统去找你的下一个线索。没有你想象的那么难哦。";
            return "在你的雇主的邮箱程序中输入'show status'或's status'。";
        }
        int i32 = i31 + 1;
        if (paramInt == i32) {
            if (paramBoolean)
                return "你刚刚又从Marie那里收到一封邮件。查看以进入下一关卡。";
            return "在你的本地系统中输入'backdoor'或'b'，'trace'，'uplink'，等待密码出现完全后点击返回。";
        }
        int i33 = i32 + 1;
        if (paramInt == i33) {
            if (paramBoolean)
                return "上行系统中没有邮箱程序，所有再看看还有什么能挖掘到的信息吧。使用'help'命令查看可以在此系统上使用的命令。";
            return "在上行系统中输入'jump victor'或'j victor'然后'gpsvxz'。";
        }
        int i34 = i33 + 1;
        if (paramInt == i34) {
            if (paramBoolean)
                return "查看Victor的系统去找你的下一个线索。没有你想象的那么难哦。";
            return "在Victor的邮箱程序中输入'show elise'或's elise'。";
        }
        int i35 = i34 + 1;
        if (paramInt == i35) {
            if (paramBoolean)
                return "你刚刚又从Marie那里收到一封邮件。查看以进入下一关卡。";
            return "在你的本地邮箱程序中输入'show dead'或's dead'。";
        }
        int i36 = i35 + 1;
        if (paramInt == i36) {
            if (paramBoolean)
                return "从Marie最后一封邮件'dead'中，你得知了你的全名(名，中间名，姓)和Marie网站的首字母是一样的。她希望你可以去那里查看她的下一条信息。有很多地方都提到了你的名，最初是和你约会的Alice那里提到的。你可以在HR数据库中找到你的中间名。而Kate给Larry的邮件中则提到了你的姓：Larry，我需要你运用你那讨厌的人肉技能帮我尽可能找到正和Alice约会的人的信息。我要想办法把他干掉，这样我才能邀请Alice跟我一起去罗马玩。我现在唯一知道的信息就是Alex的姓氏Thomason。放手干吧，我不会亏待你的。我可知道你对上床的渴求哦。快！在php.pujiahh.com/alienconspiracytheories/中的'快来看看你的外星名字'页面中输入你的全名以查看下一个线索。";
            return "在php.pujiahh.com/alienconspiracytheories/中的'快来看看你的外星名字'页面中输入你的全名 'Alex Chris Thomason'。然后在本地系统输入Marie提到的那个命令。";
        }
        int i37 = i36 + 1;
        if (paramInt == i37) {
            if (paramBoolean)
                return "Marie的隐藏信息中，她提到了一个对二进制有着特殊癖好的名叫Wanda的人。二进制数是由0和1组成的数字还有可以转换成八进制的独立字符。试着破解Wanda以查看她的密码。";
            return "在backdoor程序中，输入'ufo'，'wanda'和'01110111'。";
        }
        int i38 = i37 + 1;
        if (paramInt == i38) {
            if (paramBoolean)
                return "如果你的版本在1.2以上：那么来自Wanda的带有'hidden routine'的信息会给你提供线索。那条线索里面你会得知有一个雇员一年改12次密码。想想他的密码会是什么然后用它进入Xander的'ufo'系统。如果你的版本是1.0或1.1，那Xander的密码就是'harris'。";
            return "1.2版本或以上：在backdoor程序中输入'ufo'，'xander'还有当前的月份(不要跳转至Xander的工作站)。如果今天是1月1日，那密码就是'january' （无需引号）。如果无效那就将所有月份试一遍(january, february,...)。最后你可能需要将你的设备的'地区'改为'美国'。1.0版本或1.1版本，Xander的密码是'harris'。";
        }
        int i39 = i38 + 1;
        if (paramInt == i39) {
            if (paramBoolean)
                return "查看Xander的系统去找你的下一个线索。输入'help'查看可用命令列表。";
            return "在Xander的系统中输入'pdb'。";
        }
        int i40 = i39 + 1;
        if (paramInt == i40) {
            if (paramBoolean)
                return "你的目标是释放Alice，但首先你要弄清她的囚犯编号是多少。你可以从1慢慢试，但也有个比较快捷的方式。如果你试一个较小的数字，那你就会看到这个囚犯已经被处决了（好可怜）。输入一个较大的数字你就会发现这个囚犯根本不存在。而Alice的编号就在这大数和小数之间。使用这个方法很快就能搞定Alice的囚犯编号。";
            return "Alice的囚犯编号是339。在囚犯数据库中释放她以进入下一关卡。";
        }
        int i41 = i40 + 1;
        if (paramInt == i41) {
            if (paramBoolean)
                return "你的雇主Yurgon给你发送了一封语音信息，请将设备音量调到合适的大小。";
            return "在本地系统中输入'voicemail'或'v'。";
        }
        int i42 = i41 + 1;
        if (paramInt == i42) {
            if (paramBoolean)
                return "你的雇主Yurgon给你发送了一封语音信息，请将设备音量调到合适的大小。在语音信息中，她告诉你她的旧密码是'phone'。";
            return "在backdoor程序中，输入'ufo'，'yurgon'还有'phone'。";
        }
        int i43 = i42 + 1;
        if (paramInt == i43) {
            if (paramBoolean)
                return "Yurgon是它们种族的外交主管，她认为向地球发送信息可以拯救你的星球。她的无线电程序可以将信息发送到指定的频率，所以请弄清需要使用的频率。你可以通过在prompt后输入信息id查看传送日志。日志中包括很多可以帮助你了解整个故事的信息。你还会在这些日志中发现一些其他的频率...";
            return "在Yurgon的无线电程序中，向zyrgorkn种族发送信息。";
        }
        int i44 = i43 + 1;
        if (paramInt == i44) {
            if (paramBoolean)
                return "Zyrgorkn要求你与它们进行外交谈话，你需要使用他们提到的'hidden program'。运行那个程序以进行下一步；你可以通过点击主界面的“系统状态”回顾你的游戏进度。Yurgon的密码已经和之前的不同了，所以你需要再查找她现在的密码。还有，所有输入都要保持小写。";
            return "在Yurgon的系统中输入'diplomacy'，'zyrgorkn' (全部小写)还有Yurgon邮箱程序中的消息名称。";
        }
        int i45 = i44 + 1;
        if (paramInt == i45) {
            if (paramBoolean)
                return "查看外交系统以查找你的下一个线索。输入'help'查看可用命令列表。";
            return "在外交系统上运行这个程序以进入下一关卡。";
        }
        int i46 = i45 + 1;
        if (paramInt == i46) {
            if (paramBoolean)
                return "你的本地系统刚刚收到了Yurgon发给你的邮件。查看以进入下一关卡。";
            return "在你的当地邮箱程序中查看Yurgon最后的消息。";
        }
        (i46 + 1);
        return "";
    }
}

/* Location:           D:\豌豆荚\Apps\WDJDownload\Apps\虚拟入侵 汉化版\classes_dex2jar.jar
 * Qualified Name:     com.i273.hackrun.Classes.HintsAndAnswers
 * JD-Core Version:    0.6.0
 */