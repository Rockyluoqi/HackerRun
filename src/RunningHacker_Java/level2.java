package RunningHacker_Java;

import java.util.Scanner;

/**
 * Created by Luoqi on 2014/12/27.
 */
public class level2 {
    String input_para;
    String user_game;
    String user_password;
    
    Out out =new Out();
    
    public level2(){
    	
    }
    
	public int level2() {
    	
        UserFile userFile = new UserFile();
        userFile.permission = 333;
        userFile.fileName = "һ�������ߵ�����";
        userFile.content =  "���ã�\n    ��˵����һ���������ĺڿ�,"+
        "��ϣ�����ܺڽ�����������ɡ��ĺ�̨�ɼ�����ϵͳ��"+
        "����ʥ�ǡ����롢��������һ��ĳɼ��ĳ���߷֡������Ѵ������������ʺš�\n"+
        "    ��������ʱ���յ��鱨�У�\n"+
        "\t���Ӻ㣨���λ����Ա���ǳ���������������,ͬʱҲ�Ǻ�̨�ɼ�����ϵͳΨһ����Կӵ���ߣ�\n"+
        "\t������ӵ��һ̨��������ip��52.52.52.52�������Ǽ�ī���������������Զ��������ԣ�\n"+
        "\t���Ԭ����ѣ���Ԭ��ϵ�ܺã�\n"+
        "\tԬ񴣨���α�����ҳ��OP��������Ա��Ҫ��������鿴��Ϣ��\n"+
        "    ���ͬʱ����Ϊ��׼����һ��DNS��141.141.141.141��" +
        "    �κ����Ӹ�DNS�ĵ��Զ�����Ⱦ�������������ƻ������ǽ���õ��Խ����ױ�Զ�̿��ơ�\n" +
        "Ԥף�����ɹ����ɹ���ʣ��β������������������˻�\n";
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
            	return 1;
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
