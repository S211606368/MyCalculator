package com.example.mycalculator.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mycalculator.pojo.User;
import com.example.mycalculator.sqlite.SqLiteConnect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LIN
 */
public class UserDaoImpl implements com.example.mycalculator.dao.UserDao {
    private SqLiteConnect sqLiteConnect;

    SQLiteDatabase sqLiteDatabase = null;

    List<User> arrayList;
    Cursor cursor;

    public UserDaoImpl(Context context){

        sqLiteConnect = new SqLiteConnect(context,1);
    }

    /**
     * 添加用户
     * @param userName 用户账号
     * @param userPassword 用户密码
     */
    @Override
    public void addUser(String userName,String userPassword) {
        sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_name",userName);
            contentValues.put("user_password",userPassword);

            sqLiteDatabase.insert("User",null,contentValues);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
    }

    /**
     * 删除用户，没有管理员界面暂时没用
     * @param userId 用户编号
     */
    @Override
    public void deleteUser(int userId) {
        sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try{
            sqLiteDatabase.delete("User","user_id=?",new String[]{String.valueOf(userId)});
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }


    }

    /**
     * 更改账号密码，没有用户界面暂时没用
     * @param user 用户类
     */
    @Override
    public void updateUser(User user) {
        sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("USER_NAME", user.getUserName());
            contentValues.put("USER_PASSWORD", user.getUserPassword());
            sqLiteDatabase.update("User", contentValues, "User_ID", new String[]{user.getUserName()});
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
    }

    /**
     * 忘记密码时可以重置密码
     * @param userName 用户账号
     * @param userPassword 用户密码
     */
    @Override
    public void updateUser(String userName, String userPassword) {
        sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try{
            String sql = "update User set user_password = ? where user_name = ?";
            sqLiteDatabase.execSQL(sql,new String[]{userPassword,userName});
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();
        }
    }

    /**
     * 更具账号查找用户
     * @param userName
     * @return List<User>
     */
    @Override
    public List<User> selectUser(String userName) {
        sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try{
            List<User> arrayList = new ArrayList<>();

            User user = new User();

            String sql = "select * from user where user_name = ?";

            cursor = sqLiteDatabase.rawQuery(sql,new String[]{userName});

            if(cursor.moveToFirst()){
                System.out.println("???");
                user.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
                user.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
                user.setUserPassword(cursor.getString(cursor.getColumnIndex("user_password")));
                arrayList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqLiteDatabase.endTransaction();
            cursor.close();
            sqLiteDatabase.close();
        }
        return arrayList;
    }

}
