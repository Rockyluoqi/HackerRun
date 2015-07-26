package RunningHacker_Java;

import java.util.Scanner;

/**
 * Created by Luoqi on 2014/12/27.
 */
public class level3 {
    String input_para;
    String user_game;
    String user_password;
    
    Out out =new Out();
    
    public level3(){
    	
    }
    
	public int level3() {
    	
        UserFile userFile = new UserFile();
        userFile.permission = 111;
        userFile.fileName = "connect_to_lijianfu.sh";
        userFile.content =  "你没有权限读取文件";
        userFile.userFile = null;
        while(true){
        	out.out_head();
        	
            Scanner scanner = new Scanner(System.in);
            input_para = scanner.nextLine();

            if("help".equals(input_para) ||"h".equals(input_para)) {
            	out.out_help_ls();
                out.out_help_sh();
                out.out_help_cat();
                out.out_help_exit();
            }

            else if("ls".equals(input_para)) {
                System.out.println(userFile.fileName+"  "+userFile.permission);
            }
            
            else if("cat connect_to_lijianfu.sh".equals(input_para)){
            	System.out.println(userFile.content);
            }

            else if("connect_to_lijianfu.sh".equals(input_para)) {
                //System.out.println(userFile.content);
                System.out.println("成功进入李建夫的电脑");
                out.modify_userName("lijianfu");
                out.modify_hostName("PC_lijianfu");
                return 4;
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
