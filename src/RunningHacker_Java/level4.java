package RunningHacker_Java;

import java.util.Scanner;

public class level4 {

	public level4() {
		// TODO Auto-generated constructor stub
	}
	
	Out out = new Out();
	
	public int level4() {
		String input_para;
	    String input_passwd;
	    UserFile userFile = new UserFile();
        userFile.permission = 733;
        userFile.fileName = "dairy.txt";
        userFile.content =  "2014年12月13日 周几不重要 天气大概不好\n" +
        "今天袁翊又和我抢网，" +
        "我打lol的时候看片，还能不能快乐的两个人共享一个路由器了？" +
        "迟早把TP-Link拔了！！！";
        userFile.userFile = null;
		Boolean falg=false;
		
		while(true){
			out.out_head();
			Scanner scanner = new Scanner(System.in);
			input_para = scanner.nextLine();

            if("help".equals(input_para) ||"h".equals(input_para)) {
            	out.out_help_ls();
                out.out_help_ssh();
                out.out_help_cat();
                out.out_help_exit();
                continue;
            }

            else if("ls".equals(input_para)) {
                System.out.println(userFile.fileName);
                continue;
            }
            else if("cat dairy.txt".equals(input_para)) {
                System.out.println(userFile.content);
                continue;
            }
            else if("ssh 192.168.1.253".equals(input_para)) {
                //System.out.println(userFile.content);
                System.out.print("请输入密码:");
                for(int i=0;i<3;i++){
                	scanner = new Scanner(System.in);
                    input_passwd = scanner.nextLine();
                    if("ADMIN".equals(input_passwd)||"admin".equals(input_passwd)){
                    	out.modify_userName("route");
                    	out.modify_hostName("192.168.1.253");
                    	return 5;
                    }
                    else {
                    	int j=2-i;
                    	System.out.println("密码错误，请重新输入，剩余"+j+"次");
                    }
                }
                System.out.println("连续三次输错密码，请重新登录");
                continue;
            }
            else if("ssh 192.168.1.101".equals(input_para)) {
                //System.out.println(userFile.content);
                System.out.print("请输入密码:");
                for(int i=0;i<3;i++){
                	scanner = new Scanner(System.in);
                    input_passwd = scanner.nextLine();
                    if("woyoukuangshanworenxing".equals(input_passwd)){
                    	out.modify_userName("yuanyi");
                    	out.modify_hostName("192.168.1.101");
                    	return 6;
                    }
                    else {
                    	int j=2-i;
                    	System.out.println("密码错误，请重新输入，剩余"+j+"次");
                    }
                }
                System.out.println("连续三次输错密码，请重新登录");
                continue;
            }
            else if("exit".equals(input_para) || "e".equals(input_para)){
                return 3;
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
