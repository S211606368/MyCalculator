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
     * @param userName 用户账号
     * @param userPassword 用户密码
     */
    public void addUser(String userName,String userPassword);

    /**
     * 删除用户
     * @param userId 用户编号
     */
    public void deleteUser(int userId);

    /**
     * 登录后修改用户数据
     * @param user 用户信息
     */
    public void updateUser(User user);

    /**
     * 忘记密码时，根据用户账号信息修改用户密码
     * @param userName 用户账号
     * @param userPassword 用户密码
     */
    public void updateUser(String userName,String userPassword);

    /**
     * 查询指定账号的用户信息
     * @param userName 用户账号
     * @return 指定用户
     */
    public List<User> selectUser(String userName);

    /**
     * 查询全部用户信息
     * @return 返回数据库中的所有用户
     */
    public List<User> selectUser();
}
