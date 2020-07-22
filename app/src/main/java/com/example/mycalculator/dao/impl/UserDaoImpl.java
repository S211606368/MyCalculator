package com.example.mycalculator.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mycalculator.pojo.User;
import com.example.mycalculator.sqlite.DatabaseOpenHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LIN
 */
public class UserDaoImpl implements com.example.mycalculator.dao.UserDao {
    private DatabaseOpenHelper dataBaseOpenHelper;

    private SQLiteDatabase sqLiteDatabase;

    Cursor cursor;

    public UserDaoImpl() throws IOException {
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

    /**
     * 添加用户
     * @param userName 用户账号
     * @param userPassword 用户密码
     */
    @Override
    public void addUser(String userName,String userPassword) {
        openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name",userName);
        contentValues.put("user_password",userPassword);

        sqLiteDatabase.insert("User",null,contentValues);

        closeDatabase();
    }

    /**
     * 删除用户，没有管理员界面暂时没用
     * @param userId 用户编号
     */
    @Override
    public void deleteUser(int userId) {
        openDatabase();

        sqLiteDatabase.delete("User","user_id=?",new String[]{String.valueOf(userId)});

        closeDatabase();


    }

    /**
     * 更改账号密码，没有用户界面暂时没用
     * @param user 用户类
     */
    @Override
    public void updateUser(User user) {
        openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_NAME", user.getUserName());
        contentValues.put("USER_PASSWORD", user.getUserPassword());
        sqLiteDatabase.update("User", contentValues, "User_ID", new String[]{user.getUserName()});

        closeDatabase();
    }

    /**
     * 忘记密码时可以重置密码
     * @param userName 用户账号
     * @param userPassword 用户更改后的密码
     */
    @Override
    public void updateUser(String userName, String userPassword) {
        openDatabase();

        String sql = "update User set user_password = ? where user_name = ?";
        sqLiteDatabase.execSQL(sql,new String[]{userPassword,userName});
        closeDatabase();

    }

    /**
     * 根具账号查找用户
     * @param userName 用户名称
     * @return List<User>
     */
    @Override
    public List<User> selectUser(String userName) {
        openDatabase();

        List<User> arrayList = new ArrayList<>();

        User user = new User();

        String sql = "select * from user where user_name = ?";

        try{
            cursor = sqLiteDatabase.rawQuery(sql,new String[]{userName});

            if(cursor.moveToFirst()){
                user.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
                user.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
                user.setUserPassword(cursor.getString(cursor.getColumnIndex("user_password")));
                arrayList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeDatabase();

        return arrayList;
    }

}
