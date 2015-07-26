package RunningHacker_Java;

import java.util.Scanner;

public class level9 {

	public level9() {
		// TODO Auto-generated constructor stub
	}
	Out out = new Out();
	
	public int level9() {
		String input_para;
		Boolean flag=false;
		
		while(true){
			out.out_head();
			Scanner scanner = new Scanner(System.in);
            input_para = scanner.nextLine();
            
            if("help".equals(input_para) ||"h".equals(input_para)) {
            	out.out_help_ls();
                out.out_help_cat();
                out.out_help_trojans();
                out.out_help_exit();
                continue;
            }

            else if("ls".equals(input_para)) {
                System.out.println("");
                continue;
            }

            else if("exit".equals(input_para) || "e".equals(input_para)){
            	out.modify_userName("root");
            	out.modify_hostName("localhost");
            	if(flag==false)
            		return 8;
            	if(flag==true)
            		return 10;
            }
            else if ("t".equals(input_para)||"trojans".equals(input_para)) {
    			System.out.println("已成功在网站中植入木马，任何人登录网站即会感染木马，木马会记录寄主的键盘输入，将结果发送到你的邮箱");
    			flag=true;
            }
            else{
				System.out.println("输入的命令无效");
			}

		}

	
		

	}

}
