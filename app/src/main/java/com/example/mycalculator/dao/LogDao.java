package com.example.mycalculator.dao;

import com.example.mycalculator.pojo.Log;

import java.util.List;

/**
 * 日志实现类接口
 * @author LIN
 */
public interface LogDao {

    /**
     * 添加登录日志到数据库中
     * @param userName 登录用户的账号
     * @param userIp 登录用户的IP地址
     * @param loginDate 登录用户的登录时间
     */
    public void addLog(String userName, String userIp, String loginDate);

    /**
     * 查询登录日志
     * @return 日志的集合
     */
    public List<Log> selectLog();
}
