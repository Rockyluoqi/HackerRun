package RunningHacker_Java;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Luoqi on 2014/12/27.
 */
public class level6 {

    String input_para;
    String input_passwd;
    Out out =new Out();

    public level6(){
    }

    @SuppressWarnings("resource")
    public int level6() {
        String fileName = "yy.php";
        String fileName2 = "sihe.html";
        String dir1_1 = "WEB";
        String dir1_2 = "root";
        String dir2 = "hackathonWeb";
        String dir3 = "nidongde";
        
        System.out.println("恭喜你，你成功进入了袁四合的矿山（电脑）！！！");
        while(true){
            out.out_head();
            Scanner scanner = new Scanner(System.in);
            input_para = scanner.nextLine();

            if("help".equals(input_para) ||"h".equals(input_para)) {
                out.out_help_ls();
                out.out_help_ssh();
                out.out_help_cd();
                out.out_help_cat();
                out.out_help_disconnect();
                out.out_help_exit();
            }

            else if("ls".equals(input_para)) {
                System.out.println(dir2 + " "+dir3);
            } 
            else if("cd nidongde".equals(input_para)) {
                out.modify_dirName("nidongde");
                while(true){
                	out.out_head();
                	scanner = new Scanner(System.in);
                    input_para = scanner.nextLine();
                    if("help".equals(input_para) ||"h".equals(input_para)) {
                        out.out_help_ls();
                        out.out_help_ssh();
                        out.out_help_cd();
                        out.out_help_cat();
                        out.out_help_disconnect();
                        out.out_help_exit();
                    }
                    else if("ls".equals(input_para))
                    	System.out.println("懂你妹的！真以为有答案啊！电脑傻啊！");
                    else if("cd ..".equals(input_para)){
                    	out.modify_dirName("~");
                    	break;
                    }
                    else{
        				System.out.println("输入的命令无效");
        			}
                }
                
            }
            else if("cd hackathonWeb".equals(input_para)) {
                out.modify_dirName("hackathonWeb");
                while(true){
                    out.out_head();
                    scanner = new Scanner(System.in);
                    input_para = scanner.nextLine();
                    if("help".equals(input_para) ||"h".equals(input_para)) {
                        out.out_help_ls();
                        out.out_help_ssh();
                        out.out_help_cd();
                        out.out_help_cat();
                        out.out_help_disconnect();
                        out.out_help_exit();
                    }
                    else if("ls".equals(input_para)) {
                        System.out.println(dir1_1+" "+dir1_2);
                    }
                    else if("cd WEB".equals(input_para)) {
                        out.modify_dirName("hackathonWeb/WEB");
                        while(true) {
                            out.out_head();
                            scanner = new Scanner(System.in);
                            input_para = scanner.nextLine();
                            if("help".equals(input_para) ||"h".equals(input_para)) {
                                out.out_help_ls();
                                out.out_help_ssh();
                                out.out_help_cd();
                                out.out_help_cat();
                                out.out_help_disconnect();
                                out.out_help_exit();
                            }
                            else if ("ls".equals(input_para) || "-l".equals(input_para)) {
                                System.out.println(fileName);
                            }
                            else if ("cat yy.php".equals(input_para)) {
                                //显示php
                                showFile("config.php");
                                continue;
                            }
                            else if ("cd ..".equals(input_para)) {
                                out.modify_dirName("hackathonWeb");
                                break;
                            }
                            else if("ssh 233.233.233.233".equals(input_para)){
                            	System.out.print("请输入密码:");
                                for(int i=0;i<3;i++){
                                	scanner = new Scanner(System.in);
                                    input_passwd = scanner.nextLine();
                                    if("yuankuangshan".equals(input_passwd)){
                                    	out.modify_userName("root");
                                    	out.modify_hostName("233.233.233.233");
                                    	out.modify_dirName("~");
                                    	return 7;
                                    }
                                    else {
                                    	int j=2-i;
                                    	System.out.println("密码错误，请重新输入，剩余"+j+"次");
                                    }
                                }
                                System.out.println("连续三次输错密码，请重新登录");
                                continue;
                            }
                            else if("disconnect".equals(input_para)){
                            	out.modify_userName("root");
                            	out.modify_hostName("localhost");
                            	out.modify_dirName("~");
                            	return 8;
                            }
                            else{
                				System.out.println("输入的命令无效");
                			}
                        }
                    }
                    else if("cd ..".equals(input_para)) {
                        out.modify_dirName("~");
                        break;
                    }
                    else if("disconnect".equals(input_para)){
                    	out.modify_userName("root");
                    	out.modify_hostName("localhost");
                    	out.modify_dirName("~");
                    	return 8;
                    }
                    else{
        				System.out.println("输入的命令无效");
        			}
                }
            }
            else  if("disconnect".equals(input_para)){
            	out.modify_userName("root");
            	out.modify_hostName("localhost");
            	out.modify_dirName("~");
            	return 8;
            }

            else if("exit".equals(input_para)||"e".equals(input_para)){
                out.modify_userName("lijianfu");
                out.modify_hostName("PC_lijianfu");
                out.modify_dirName("~");
            	return 4;
            }
            else{
				System.out.println("输入的命令无效");
			}
        }
    }

    public void showFile(String fileName) {
        File file = new File(fileName);

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String str = reader.readLine();
            while(str != null) {
                System.out.println(str);//打印出文件
                str = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
