package RunningHacker_Java;

public class Out {

	public Out() {
		// TODO Auto-generated constructor stub
	}
	
	static String userName="user";
	static String hostName="localhost";
	static String dirName="~";
	
	public int out_err(){
		System.out.println("������ִ�������ϵ����Ա");
		return 0;
	}
	public int out_head(){
		System.out.print(userName+"@"+hostName+" "+dirName+" $ ");
		return 0;
	}
	public int out_help(){
		System.out.println("��ӭʹ�ð���ϵͳ��������������ʾ����");
		return 0;
	}
	public int out_help_su(){
		System.out.println("su\t\t\t\t���볬������ԱȨ��");
		return 0;
	}
	public int out_help_ls(){
		System.out.println("ls\t\t\t\t�鿴��ǰĿ¼�������ļ���Ϣ");
		return 0;
	}
	public int out_help_cat() {
		System.out.println("cat <filename>\t\t\t�鿴�ļ�����");
		return 0;
	}
	public int out_help_sh() {
		System.out.println("<filename>.sh\t\t\tֱ������shell�ļ�");
		return 0;
	}
	public int out_help_exit() {
		System.out.println("exit\t\t\t\t�˳�");
		return 0;
	}
	public int out_help_mail(){
		System.out.println("mail\t\t\t\t��������");
		return 0;
	}
	public int out_help_ssh(){
		System.out.println("ssh <ip-address>\t\t���ӵ�������");
		return 0;
	}
	public int out_help_read(){
		System.out.println("read\t\t\t\t�Ķ��ʼ�");
		return 0;
	}
	public int out_help_changeDNS(){
		System.out.println("c (change DNS)\t\t\t�޸�DNS");
		return 0;
	}
	public int out_help_mysql(){
		System.out.println("mysql -h<url> -u<username> -p<passwd>\t����mysql");
		return 0;
	}
	public int out_help_mysql_select(){
		System.out.println("select <����> from <��>\t\tѡȡ����");
		return 0;
	}
	public int out_help_mysql_desc(){
		System.out.println("desc <table>\t\t\t��ʾ�������");
		return 0;
	}
	public int out_help_mysql_update() {
		System.out.println("update <tableName> set <fieldName> = '<value>' where <fieldname> = '<value>'\n\t\t\t\t�������ݿ�ֵ");
		return 0;
	}
	public int out_help_mysql_show(){
		System.out.println("show tables\t\t\t��ʾ���б�");
		return 0;
	}
	public int out_help_cd(){
		System.out.println("cd <dir>\t\t\t�����ļ���");
		return 0;
	}
	public int out_help_trojans(){
		System.out.println("t (trojans)\t\t\tֲ��ľ��");
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
		System.out.println("disconnect\t\t\t�Ͽ����ӻص������ʻ�");
		return 0;
	}
}
