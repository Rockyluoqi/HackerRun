package RunningHacker_Java;

import java.util.Scanner;


public class Level12{
	
	public int level12(){

		Out out = new Out();

		String input_para;
		String input_passwd;
		
		System.out.println("请输入数据库相关命令来查看数据库内部信息：");
		System.out.println("输入 \"h (help)获得帮助\"");
		while (true) {
			out.out_head();
			Scanner scanner = new Scanner(System.in);
			input_para = scanner.nextLine();

			if ("help".equals(input_para) || "h".equals(input_para)) {
				out.out_help_mysql_show();
				out.out_help_mysql_desc();
				out.out_help_mysql_select();
				out.out_help_mysql_update();
				out.out_help_exit();
				continue;
			}

			else if ("exit".equals(input_para) || "e".equals(input_para)) {
				out.modify_userName("root");
				out.modify_hostName("localhost");
				return 10;
			}

			else if("show tables".equals(input_para)){
				System.out.println("+---------------+");
				System.out.println("|Tables_in_peach|");
				System.out.println("+---------------+");
				System.out.println("|   user        |");
				System.out.println("|   number      |");
				System.out.println("|   学习资料-你懂的  |");
				System.out.println("+---------------+");
				
				continue;
			}
			
			else if("select * from number".equals(input_para)){
				
				System.out.println("+--------------+------------+");
				System.out.println("| numberName   |    grade   |");
				System.out.println("+--------------+------------+");
				System.out.println("| RunningHacker|   54.5     |");
				System.out.println("| peach        |   60.5     |");
				System.out.println("| sujunwei     |   52       |");
				System.out.println("| #000000      |   63       |");
				System.out.println("| teg          |   57       |");
				System.out.println("| 袁四合&袁矿山         |   66       |");
				System.out.println("+--------------+------------+");
				
				continue;
			}
			
			else if("desc number".equals(input_para)){
				System.out.println("+--------------+--------------+");
				System.out.println("|  Field       |  Type        |");
				System.out.println("+--------------+--------------+");
				System.out.println("|  numberName  |  varchar(255)|");
				System.out.println("|  grade       |  varchar(255)|");
				System.out.println("+--------------+------------+");

				continue;
			}
			
			else if("update number set grade = '100' where numberName = 'RunningHacker'".equals(input_para)){
				System.out.println("Query OK, 1 row affected (0.666 sec)");
				System.out.println("Rows matched: 1 Changed: 1 Warnings: 0");
				System.out.println("修改RunningHacker团队信息成功，恭喜您通关！！！");
				return -1;
			}
			
			else if("select * from user".equals(input_para)){
				System.out.println("+--------------+------------+");
				System.out.println("| userName     |    userPsw   |");
				System.out.println("+--------------+------------+");
				System.out.println("| RunningHacker|   1        |");
				System.out.println("| peach        |   12       |");
				System.out.println("| sujunwei     |   123      |");
				System.out.println("| #000000      |   1234     |");
				System.out.println("| teg          |   12345    |");
				System.out.println("| 袁四合&袁矿山         |   123456   |");
				System.out.println("+--------------+------------+");
				continue;
			}
			
			else if("select * from 学习资料-你懂的".equals(input_para)){
				System.out.println("+----------------+-------------+");
				System.out.println("| courseId       | courseName  |");
				System.out.println("+----------------+-------------+");
				System.out.println("|    Y0655654    |   数据结构     |");
				System.out.println("|    Z1234566    |   软件工程     |");
				System.out.println("|    W0345345    |   编译方法     |");
				System.out.println("|    B0912343    |   毛概         |");
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
