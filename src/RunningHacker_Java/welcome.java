package RunningHacker_Java;

import java.util.Scanner;


public class welcome {

	public welcome() {
		// TODO Auto-generated constructor stub
	}
	
	Out out = new Out();
	
	@SuppressWarnings("resource")
	public int welcome() {
	    String input_para;
	    String input_passwd;
	    UserFile userFile = new UserFile();
        userFile.permission = 333;
        userFile.fileName = "README.MD";
        userFile.content =  "������ѧ�ٰ���һ���ǰ�ı�������ɴ�����HACKATHON�������˼������£��������Ϸ���ǵ������ⳡ�����С�\n��һ�غ����ף�����Ѳ����� :D";
        userFile.userFile = null;
		Boolean falg=false;
		
		while(true){
			out.out_head();
			Scanner scanner = new Scanner(System.in);
            input_para = scanner.nextLine();

            if("help".equals(input_para) ||"h".equals(input_para)) {
            	out.out_help_ls();
                out.out_help_su();
                out.out_help_cat();
                out.out_help_exit();
                continue;
            }

            else if("ls".equals(input_para)) {
                System.out.println(userFile.fileName);
                continue;
            }
            
            else if("cat README.MD".equals(input_para)||"cat readme.md".equals(input_para)){
            	System.out.println(userFile.content);
            	continue;
            }

            else if("su".equals(input_para)) {
                //System.out.println(userFile.content);
                System.out.print("����������:");
                for(int i=0;i<3;i++){
                	scanner = new Scanner(System.in);
                    input_passwd = scanner.nextLine();
                    if("HACKATHON".equals(input_passwd)||"hackathon".equals(input_passwd)){
                    	out.modify_userName("root");
                    	return 1;
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
                return -1;
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
