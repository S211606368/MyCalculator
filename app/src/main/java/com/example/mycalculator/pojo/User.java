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
    private int id;
    private String userName;
    private String userPassword;

    public User(){
        super();
    }

    public User(int id,String userName,String userPassword) {
        super();
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
