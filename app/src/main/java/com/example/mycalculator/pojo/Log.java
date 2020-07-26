package com.example.mycalculator.pojo;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * log实体对象
 * @author LIN
 */
@SmartTable(name = "登录日志")
public class Log {
    @SmartColumn(id = 0,name = "日志编码")
    private int logId;
    @SmartColumn(id = 1,name = "用户账号")
    private String userName;
    @SmartColumn(id = 2,name = "用户登录时的ip")
    private String userIp;
    @SmartColumn(id = 3,name = "用户登录时间")
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
