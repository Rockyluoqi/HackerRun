package com.rocky.RunningHacker_Android;

public class Out {

    public Out() {
        // TODO Auto-generated constructor stub
    }

    static String userName = "user";
    static String hostName = "localhost";
    static String dirName = "~";

    public String out_err() {
        return "������ִ�������ϵ����Ա";
    }

    public String out_head() {
        return userName + "@" + hostName + " " + dirName + " $ ";
    }

    public String out_help() {
        return "��ӭʹ�ð���ϵͳ��������������ʾ����";
    }

    public String out_help_su() {
        return "su\t\t\t\t���볬������ԱȨ��";
    }

    public String out_help_ls() {
        return "ls\t\t\t\t�鿴��ǰĿ¼�������ļ���Ϣ";
    }

    public String out_help_cat() {
        return "cat <filename>\t\t\t�鿴�ļ�����";
    }

    public String out_help_sh() {
        return "<filename>.sh\t\t\tֱ������shell�ļ�";
    }

    public String out_help_exit() {
        return "exit\t\t\t\t�˳�";
    }

    public String out_help_mail() {
        return "mail\t\t\t\t��������";
    }

    public String out_help_ssh() {
        return "ssh <ip-address>\t\t���ӵ�������";
    }

    public String out_help_read() {
        return "read\t\t\t\t�Ķ��ʼ�";
    }

    public String out_help_changeDNS() {
        return "c (change DNS)\t\t\t�޸�DNS";
    }

    public String out_help_mysql() {
        return "mysql -h<url> -u<username> -p<passwd>\t����mysql";
    }

    public String out_help_mysql_select() {
        return "select <����> from <��>\t\tѡȡ����";
    }

    public String out_help_mysql_desc() {
        return "desc <table>\t\t\t��ʾ�������";
    }

    public String out_help_mysql_update() {
        return "update <tableName> set <fieldName> = '<value>' where <fieldname> = '<value>'\n\t\t\t\t�������ݿ�ֵ";
    }

    public String out_help_mysql_show() {
        return "show tables\t\t\t��ʾ���б�";
    }

    public String out_help_cd() {
        return "cd <dir>\t\t\t�����ļ���";
    }

    public String out_help_trojans() {
        return "t (trojans)\t\t\tֲ��ľ��";
    }

    public String out_help_disconnect() {
        return "disconnect\t\t\t�Ͽ����ӻص������ʻ�";
    }

    public int modify_userName(String arg) {
        userName = arg;
        return 0;
    }

    public int modify_hostName(String arg) {
        hostName = arg;
        return 0;
    }

    public int modify_dirName(String arg) {
        dirName = arg;
        return 0;
    }

}
