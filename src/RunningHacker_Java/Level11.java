package RunningHacker_Java;

import java.util.Scanner;

/**
 * Created by Luoqi on 2014/12/27.
 */
public class Level11 {
    String input_para;
    String user_game;
    String user_password;
    
    Out out =new Out();
    
    public Level11(){
    	
    }
    
	public int level11() {
    	
        UserFile userFile = new UserFile();
        userFile.permission = 333;
        userFile.fileName = "键盘记录";
        userFile.content =  "13:23:23  3212123412 12u41 dsf" +
        " dg gddg gdgd woshitaojuju [enter] -h666666.6.6.6 -upeach -ppeach\n" +
        "00:00:02 3212123412  12u41  dsf  " +
        " w w w . gitcafe . c o m   gddg gdgd caogaoxiang [enter] 33333\n";
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
            	return 10;
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
