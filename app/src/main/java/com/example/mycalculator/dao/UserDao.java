package com.example.mycalculator.dao;

import com.example.mycalculator.pojo.User;

import java.util.List;

/**
 * User对象持久化类的接口
 * @author LIN
 */
public interface UserDao {
    /**
     * 新增用户
     * @param userName
     * @param userPassword
     */
    public void addUser(String userName,String userPassword);

    /**
     * 删除用户
     * @param userId
     */
    public void deleteUser(int userId);

    /**
     * 登录后修改用户数据
     * @param user
     */
    public void updateUser(User user);

    /**
     * 忘记密码时，根据用户账号信息修改用户密码
     * @param userName
     * @param userPassword
     */
    public void updateUser(String userName,String userPassword);

    /**
     * 查询用户信息
     * @param userName
     * @return ArrayList<User>
     */
    public List<User> selectUser(String userName);
}
