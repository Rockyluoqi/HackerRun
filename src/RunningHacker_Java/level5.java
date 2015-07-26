package RunningHacker_Java;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;


/**
 * Created by Luoqi on 2014/12/27.
 */
public class level5 {
    public String input;
    Out out = new Out();
    //进入一个被感染的网页可以在前端代码中查找线索
    public void openWebPage() {
        try {
            URI uri = new URI("http://hack.neuibm.club/");
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            if (desktop != null)
                try {
                    desktop.browse(uri);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public int level5() {
        String input_para;
        String input_passwd;
        while(true){
        	out.out_head();

            Scanner scanner = new Scanner(System.in);
            input_para = scanner.nextLine();

            if(input_para.equals("help")||input_para.equals("h")) {
            	out.out_help_ls();
            	out.out_help_cat();
            	out.out_help_changeDNS();
                out.out_help_exit();
            }
            else if("c 141.141.141.141".equals(input_para)){
            	System.out.println("成功修改DNS，袁翊电脑已中枪，密码是：woyoukuangshanworenxing\n本回合还有个小彩蛋哦～");
            }
            else if("browser".equals(input_para)){
            	openWebPage();
            }
            
            else if("ls".equals(input_para)||"l".equals(input_para)) {
                System.out.println("");
            }
            else if("exit".equals(input_para) || "e".equals(input_para)){
            	out.modify_userName("lijianfu");
                out.modify_hostName("192.168.1.100");
            	return 4;
            }
            else if("ssh 192.168.1.101".equals(input_para)){
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
