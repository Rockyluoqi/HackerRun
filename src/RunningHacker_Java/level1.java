package RunningHacker_Java;

import java.util.Scanner;


public class level1 {

	public level1() {
		// TODO Auto-generated constructor stub
	}
	
	Out out = new Out();
	
	
	public int level1() {
		
		
	    String input_para;
	    String input_passwd;
		Boolean falg=false;
		while(true){
			out.out_head();
			
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
                System.out.println("");
                continue;
            }
            else if("mail".equals(input_para)) {
            	out.modify_userName("hack");
            	out.modify_hostName("mailbox");
            	return 2;
            }
            else if("exit".equals(input_para) || "e".equals(input_para)){
            	out.modify_userName("user");
            	return 0;
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
            else{
				System.out.println("输入的命令无效");
			}
		}
	}

}
