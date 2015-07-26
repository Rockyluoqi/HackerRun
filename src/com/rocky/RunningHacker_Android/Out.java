package com.rocky.RunningHacker_Android;

public class Out {

    public Out() {
        // TODO Auto-generated constructor stub
    }

    static String userName = "user";
    static String hostName = "localhost";
    static String dirName = "~";

    public String out_err() {
        return "程序出现错误，请联系管理员";
    }

    public String out_head() {
        return userName + "@" + hostName + " " + dirName + " $ ";
    }

    public String out_help() {
        return "欢迎使用帮助系统，以下是命令提示帮助";
    }

    public String out_help_su() {
        return "su\t\t\t\t进入超级管理员权限";
    }

    public String out_help_ls() {
        return "ls\t\t\t\t查看当前目录下所有文件信息";
    }

    public String out_help_cat() {
        return "cat <filename>\t\t\t查看文件内容";
    }

    public String out_help_sh() {
        return "<filename>.sh\t\t\t直接运行shell文件";
    }

    public String out_help_exit() {
        return "exit\t\t\t\t退出";
    }

    public String out_help_mail() {
        return "mail\t\t\t\t进入邮箱";
    }

    public String out_help_ssh() {
        return "ssh <ip-address>\t\t连接到服务器";
    }

    public String out_help_read() {
        return "read\t\t\t\t阅读邮件";
    }

    public String out_help_changeDNS() {
        return "c (change DNS)\t\t\t修改DNS";
    }

    public String out_help_mysql() {
        return "mysql -h<url> -u<username> -p<passwd>\t连接mysql";
    }

    public String out_help_mysql_select() {
        return "select <属性> from <表>\t\t选取数据";
    }

    public String out_help_mysql_desc() {
        return "desc <table>\t\t\t显示表的内容";
    }

    public String out_help_mysql_update() {
        return "update <tableName> set <fieldName> = '<value>' where <fieldname> = '<value>'\n\t\t\t\t更新数据库值";
    }

    public String out_help_mysql_show() {
        return "show tables\t\t\t显示所有表";
    }

    public String out_help_cd() {
        return "cd <dir>\t\t\t进入文件夹";
    }

    public String out_help_trojans() {
        return "t (trojans)\t\t\t植入木马";
    }

    public String out_help_disconnect() {
        return "disconnect\t\t\t断开连接回到本地帐户";
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
