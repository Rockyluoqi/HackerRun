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
        userFile.content =  "2014��12��13�� �ܼ�����Ҫ ������Ų���\n" +
        "����Ԭ��ֺ���������" +
        "�Ҵ�lol��ʱ��Ƭ�����ܲ��ܿ��ֵ������˹���һ��·�����ˣ�" +
        "�����TP-Link���ˣ�����";
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
                System.out.print("����������:");
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
                    	System.out.println("����������������룬ʣ��"+j+"��");
                    }
                }
                System.out.println("��������������룬�����µ�¼");
                continue;
            }
            else if("ssh 192.168.1.101".equals(input_para)) {
                //System.out.println(userFile.content);
                System.out.print("����������:");
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
                    	System.out.println("����������������룬ʣ��"+j+"��");
                    }
                }
                System.out.println("��������������룬�����µ�¼");
                continue;
            }
            else if("exit".equals(input_para) || "e".equals(input_para)){
                return 3;
            }
            else{
				System.out.println("�����������Ч");
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
