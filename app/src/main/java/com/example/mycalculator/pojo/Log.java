package com.example.mycalculator.pojo;

import java.sql.Date;

/**
 * log实体对象
 * @author LIN
 */
public class Log {
    private int logId;
    private String userName;
    private String userIp;
    private Date loginDate;

    public Log(String userName,String userIp,Date loginDate) {
        super();
        this.userName = userName;
        this.userIp = userIp;
        this.loginDate = loginDate;
    }

    public int getLogId() {
        return logId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserIp() {
        return userIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }
}
