package com.example.mycalculator.pojo;

/**
 * log实体对象
 * @author LIN
 */
public class Log {
    private int logId;
    private String userName;
    private String userIp;
    private String loginDate;

    public Log(){
        super();
    }

    public Log(String userName,String userIp,String loginDate) {
        super();
        this.userName = userName;
        this.userIp = userIp;
        this.loginDate = loginDate;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }
}
