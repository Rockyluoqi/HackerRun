package RunningHacker_Java;

import java.util.Scanner;

public class level7{
	
	public int level7(){
		Out out = new Out();

		String input_para;
		String input_passwd;
		String dbCom;
		
		// Boolean flag = false;
		System.out.println("请输入数据库相关命令来查看数据库内部信息");
		System.out.println("输入 \"h (help)获得帮助\"");
		while (true) {
			out.out_head();
			Scanner scanner = new Scanner(System.in);
			dbCom = scanner.nextLine();
			
			if("show tables".equals(dbCom)){
				System.out.println("+---------------------+");
				System.out.println("|Tables_in_hackathon  |");
				System.out.println("+---------------------+");
				System.out.println("|   user              |");
				System.out.println("|   number            |");
				System.out.println("|   学习资料-你懂的                 |");
				System.out.println("+---------------------+");
				continue;
			}
			else if ("help".equals(dbCom) || "h".equals(dbCom)) {
				out.out_help_mysql_show();
				out.out_help_mysql_select();
				out.out_help_mysql_desc();
				out.out_help_exit();
				continue;
			}


			else if ("exit".equals(dbCom) || "e".equals(dbCom)) {
				out.modify_userName("yuanyi");
                out.modify_hostName("192.168.1.101");
				return 6;
			}
			
			
			else if("select * from admin".equals(dbCom)){
				
				
				System.out.println("+--------------+--------------------+");
				System.out.println("| adminName    |    adminPsw        |");
				System.out.println("+--------------+--------------------+");
				System.out.println("| admin        |   ab725c39f008b613 |");
				System.out.println("| #000000      |   274f6c49b3e31a0c |");
				System.out.println("| yuan矿山                |   fbfc89c22d4aa1b4 |");
				System.out.println("+--------------+--------------------+");
				System.out.println("请返回本地将MD5解密");
				continue;
			}
			
			
			else if("desc admin".equals(dbCom)){
				
				System.out.println("+--------------+--------------+");
				System.out.println("|  Field       |  Type        |");
				System.out.println("+--------------+--------------+");
				System.out.println("|  adminName   |  varchar(255)|");
				System.out.println("|  adminPsw    |  varchar(255)|");
				System.out.println("+--------------+--------------+");
				continue;
			}
			
			else if("select * from user".equals(dbCom)){
				System.out.println("+--------------+-------------------------------------------+");
				System.out.println("| userName     |    userPsw                                |");
				System.out.println("+--------------+-------------------------------------------+");
				System.out.println("| RunningHacker|   77AA87C9A2E08760DA290B240BD94914        |");
				System.out.println("| peach        |   12       |");
				System.out.println("| sujunwei     |   123      |");
				System.out.println("| #000000      |   1234     |");
				System.out.println("| teg          |   12345    |");
				System.out.println("| 袁四合&袁矿山        |   123456   |");
				System.out.println("+--------------+------------+");
				continue;
			}
			
			else if("select * from 学习资料-你懂的".equals(dbCom)){
				System.out.println("+----------------+-------------+");
				System.out.println("| courseId       | courseName  |");
				System.out.println("+----------------+-------------+");
				System.out.println("|    Y0655654    |   数据结构          |");
				System.out.println("|    Z1234566    |   软件工程          |");
				System.out.println("|    W0345345    |   编译方法          |");
				System.out.println("|    B0912343    |   毛概                  |");
				System.out.println("|    B0023675    |   计算机组成原理|");
				System.out.println("+----------------+-------------+");
				continue;
			}
			
			else{
				System.out.println("输入的数据库命令无效");
			}
			

		}
	}
}
