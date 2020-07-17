package com.example.mycalculator.pojo;

/**
 * 用户类
 * @author LIN
 */
public class User {

    /**
     * id账号编码
     * username账户
     * userPassword账号密码
     */
    private int userId;
    private String userName;
    private String userPassword;

    public User(){
        super();
    }

    public User(int userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
