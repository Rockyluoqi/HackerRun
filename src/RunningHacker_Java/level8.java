package RunningHacker_Java;

import java.util.Scanner;



public class level8 {

	public level8() {
		// TODO Auto-generated constructor stub
	}
	
	Out out = new Out();
	
	
	public int level8() {
		
		
	    String input_para;
	    String input_passwd;
		Boolean falg=false;
		while(true){
			out.out_head();
			UserFile userFile = new UserFile();
	        userFile.permission = 333;
	        userFile.fileName = "md5.txt";
	        userFile.content =  "aaeee1a063ed2833 ---> [790000]\n"+ 
			"8ad9902aecba32e2 ---> [000000] \n"+
			"3f1f1045499df681 ---> [110001] \n"+
			"7efe184ae6dd2420 ---> [000002] \n"+
			"380b579fd1bccaa6 ---> [thinkpad] \n"+
			"5f488073e2c35330 ---> [750101] \n"+
			"23e6bcd98e951e08 ---> [790101] \n"+
			"b3558aad09283d86 ---> [luoqi0708]\n"+
			"693475accb03fe1f ---> [820106] \n"+
			"aa6b63c3c33a92f1 ---> [840110] \n"+
			"3c0c800d824dfc52 ---> [325118] \n"+
			"ed9e44c80768f075 ---> [760323] \n"+
			"ab725c39f008b613 ---> [yuankuangshan]\n"+
			"d0b7bf0bd1458712 ---> [810406] \n"+
			"380b579fd1bccaa6 ---> [parkcoffee] \n"+
			"d561b4c777d3899e ---> [000606] \n"+
			"274f6c49b3e31a0c ---> [apple] \n"+
			"09db748ce3773165 ---> [505707] \n"+
			"c0f4b0ffcaac767c ---> [850721] \n"+
			"380b579fd1bccaa6 ---> [hackathon] \n"+
			"6d78dbcc4eda2d98 ---> [830809] \n"+
			"965eb72c92a549dd ---> [111111] \n"+
			"4b4c05dc7d89733a ---> [xlyle123]";
	        userFile.userFile = null;
			Scanner scanner = new Scanner(System.in);
            input_para = scanner.nextLine();
            
            if("help".equals(input_para) ||"h".equals(input_para)) {
            	out.out_help_ls();
                out.out_help_mail();
                out.out_help_ssh();
                out.out_help_exit();
                continue;
            }
            
            else if("ls".equals(input_para)) {
                System.out.println(userFile.fileName);
                continue;
            }
            else if("mail".equals(input_para)) {
            	out.modify_userName("hack");
            	out.modify_hostName("mailbox");
            	return 11;
            }
            else if("exit".equals(input_para) || "e".equals(input_para)){
            	out.modify_userName("user");
            	return 0;
            }
            else if("cat md5.txt".equals(input_para)){
            	System.out.println(userFile.content);
            }
            else if("ssh 52.52.52.52".equals(input_para)){
            	System.out.print("请输入密码:");
                for(int i=0;i<3;i++){
                	scanner = new Scanner(System.in);
                    input_passwd = scanner.nextLine();
                    if("jimoxuan".equals(input_passwd)||"JIMOXUAN".equals(input_passwd)){
                    	out.modify_userName("shaoming");
                    	out.modify_hostName("52.52.52.52");
                    	return 3;
                    }
                    else {
                    	int j=2-i;
                    	System.out.println("密码错误，请重新输入，剩余"+j+"次");
                    }
            	}
                System.out.println("连续三次输错密码，请重新登录");
                continue;
            }
            else if("ssh hack.neuibm.club".equals(input_para)){
            	System.out.print("请输入密码:");
                for(int i=0;i<3;i++){
                	scanner = new Scanner(System.in);
                    input_passwd = scanner.nextLine();
                    if("yuankuangshan".equals(input_passwd)){
                    	out.modify_userName("root");
                    	out.modify_hostName("hack.neuibm.club");
                    	return 9;
                    }
                    else {
                    	int j=2-i;
                    	System.out.println("密码错误，请重新输入，剩余"+j+"次");
                    }
            	}
                System.out.println("连续三次输错密码，请重新登录");
                continue;
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
