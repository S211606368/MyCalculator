package com.example.mycalculator.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mycalculator.dao.LogDao;
import com.example.mycalculator.pojo.Log;
import com.example.mycalculator.sqlite.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志实现类
 *
 * @author 林书浩
 * @date 2020/07/27
 */
public class LogDaoImpl implements LogDao {

    private DatabaseOpenHelper dataBaseOpenHelper;

    private SQLiteDatabase sqLiteDatabase;

    Cursor cursor;

    public LogDaoImpl(){
        dataBaseOpenHelper = DatabaseOpenHelper.DB_HELPER_INSTANCE;
    }

    /**
     * 开启数据库
     */
    public void openDatabase(){
        sqLiteDatabase = dataBaseOpenHelper.openDatabase();
    }

    /**
     * 关闭数据库
     */
    public void closeDatabase(){
        dataBaseOpenHelper.close();
    }

    @Override
    public void addLog(String userName, String userIp, String loginDate) {
        openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name",userName);
        contentValues.put("user_ip",userIp);
        contentValues.put("login_date", loginDate);
        sqLiteDatabase.insert("Log",null,contentValues);

        closeDatabase();
    }

    @Override
    public List<Log> selectLog() {
        openDatabase();

        List<Log> logList = new ArrayList<>();


        String sql = "select * from log";

        try{
            cursor = sqLiteDatabase.rawQuery(sql,null);

            if(cursor.moveToFirst()){
                do {
                    Log log = new Log();
                    log.setLogId(cursor.getInt(cursor.getColumnIndex("log_id")));
                    log.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
                    log.setUserIp(cursor.getString(cursor.getColumnIndex("user_ip")));
                    log.setLoginDate(cursor.getString(cursor.getColumnIndex("login_date")));
                    logList.add(log);
                }while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeDatabase();

        return logList;
    }


}
