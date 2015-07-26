package RunningHacker_Java;

public class Out {

	public Out() {
		// TODO Auto-generated constructor stub
	}
	
	static String userName="user";
	static String hostName="localhost";
	static String dirName="~";
	
	public int out_err(){
		System.out.println("程序出现错误，请联系管理员");
		return 0;
	}
	public int out_head(){
		System.out.print(userName+"@"+hostName+" "+dirName+" $ ");
		return 0;
	}
	public int out_help(){
		System.out.println("欢迎使用帮助系统，以下是命令提示帮助");
		return 0;
	}
	public int out_help_su(){
		System.out.println("su\t\t\t\t进入超级管理员权限");
		return 0;
	}
	public int out_help_ls(){
		System.out.println("ls\t\t\t\t查看当前目录下所有文件信息");
		return 0;
	}
	public int out_help_cat() {
		System.out.println("cat <filename>\t\t\t查看文件内容");
		return 0;
	}
	public int out_help_sh() {
		System.out.println("<filename>.sh\t\t\t直接运行shell文件");
		return 0;
	}
	public int out_help_exit() {
		System.out.println("exit\t\t\t\t退出");
		return 0;
	}
	public int out_help_mail(){
		System.out.println("mail\t\t\t\t进入邮箱");
		return 0;
	}
	public int out_help_ssh(){
		System.out.println("ssh <ip-address>\t\t连接到服务器");
		return 0;
	}
	public int out_help_read(){
		System.out.println("read\t\t\t\t阅读邮件");
		return 0;
	}
	public int out_help_changeDNS(){
		System.out.println("c (change DNS)\t\t\t修改DNS");
		return 0;
	}
	public int out_help_mysql(){
		System.out.println("mysql -h<url> -u<username> -p<passwd>\t连接mysql");
		return 0;
	}
	public int out_help_mysql_select(){
		System.out.println("select <属性> from <表>\t\t选取数据");
		return 0;
	}
	public int out_help_mysql_desc(){
		System.out.println("desc <table>\t\t\t显示表的内容");
		return 0;
	}
	public int out_help_mysql_update() {
		System.out.println("update <tableName> set <fieldName> = '<value>' where <fieldname> = '<value>'\n\t\t\t\t更新数据库值");
		return 0;
	}
	public int out_help_mysql_show(){
		System.out.println("show tables\t\t\t显示所有表");
		return 0;
	}
	public int out_help_cd(){
		System.out.println("cd <dir>\t\t\t进入文件夹");
		return 0;
	}
	public int out_help_trojans(){
		System.out.println("t (trojans)\t\t\t植入木马");
		return 0;
	}
	public int modify_userName(String arg) {
		userName=arg;
		return 0;
	}
	public int modify_hostName(String arg) {
		hostName=arg;
		return 0;
	}
	public int modify_dirName(String arg) {
		dirName=arg;
		return 0;
	}
	
	public int out_help_disconnect(){
		System.out.println("disconnect\t\t\t断开连接回到本地帐户");
		return 0;
	}
}
