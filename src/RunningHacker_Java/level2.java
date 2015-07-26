package RunningHacker_Java;

import java.util.Scanner;

/**
 * Created by Luoqi on 2014/12/27.
 */
public class level2 {
    String input_para;
    String user_game;
    String user_password;
    
    Out out =new Out();
    
    public level2(){
    	
    }
    
	public int level2() {
    	
        UserFile userFile = new UserFile();
        userFile.permission = 333;
        userFile.fileName = "一封匿名者的来信";
        userFile.content =  "您好：\n    听说您是一名很厉害的黑客,"+
        "我希望您能黑进“编程马拉松”的后台成绩管理系统，"+
        "将朱圣城、雒齐、梁臣三人一组的成绩改成最高分。定金已打入您的银行帐号。\n"+
        "    我现在暂时掌握的情报有：\n"+
        "\t陶子恒（本次活动管理员，非常警觉，很难侵入,同时也是后台成绩管理系统唯一的密钥拥有者）\n"+
        "\t邵明（拥有一台服务器，ip是52.52.52.52，对象是纪墨轩，经常帮助李健夫远程修理电脑）\n"+
        "\t李健夫（袁翊室友，和袁翊关系很好）\n"+
        "\t袁翊（本次比赛主页的OP，所有人员都要进入官网查看信息）\n"+
        "    与此同时，我为您准备了一组DNS：141.141.141.141，" +
        "    任何连接该DNS的电脑都将感染病毒，病毒将破坏其防火墙，该电脑将极易被远程控制。\n" +
        "预祝您马到成功。成功后剩余尾款将立即打入您的银行账户\n";
        userFile.userFile = null;
        while(true){
        	out.out_head();
        	
            Scanner scanner = new Scanner(System.in);
            input_para = scanner.nextLine();

            if("help".equals(input_para) ||"h".equals(input_para)) {
            	out.out_help_ls();
                out.out_help_read();
                out.out_help_exit();
            }

            else if("ls".equals(input_para)) {
                System.out.println(userFile.fileName+"  "+userFile.permission);
            }
            
            else if("read".equals(input_para)){
            	System.out.println(userFile.content);
            }

            else if("exit".equals(input_para) || "e".equals(input_para)){
                out.modify_userName("root");
                out.modify_hostName("localhost");
            	return 1;
            }
            else{
				System.out.println("输入的命令无效");
			}

        }


    }

    private class UserFile {
        int permission;
        String fileName;
        String content;
        UserFile userFile;
    }

}
