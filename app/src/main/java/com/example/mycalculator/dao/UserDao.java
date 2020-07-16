package com.example.mycalculator.dao;

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
public class UserDao implements UserDaoImpl{
    private SqLiteConnect sqLiteConnect;

    public UserDao(Context context){

        sqLiteConnect = new SqLiteConnect(context,1);
    }

    /**
     * 添加用户
     * @param user 用户类
     */
    @Override
    public void addUser(User user) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_NAME",user.getUserName());
        contentValues.put("USER_PASSWORD",user.getUserPassword());

        sqLiteDatabase.insert("User",null,contentValues);
        sqLiteDatabase.close();
    }

    /**
     * 删除用户，没有管理员界面暂时没用
     * @param id 用户编号
     */
    @Override
    public void deleteUser(int id) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        sqLiteDatabase.delete("User","id=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    /**
     * 更改账号密码，没有用户界面暂时没用
     * @param user 用户类
     */
    @Override
    public void updateUser(User user) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_NAME", user.getUserName());
        contentValues.put("USER_PASSWORD", user.getUserPassword());
        sqLiteDatabase.update("User", contentValues, "ID", new String[]{user.getUserName()});
        sqLiteDatabase.close();
    }

    /**
     * 忘记密码时可以重置密码
     * @param userName 用户账号
     * @param userPassword 用户密码
     */
    @Override
    public void updateUser(String userName, String userPassword) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        String sql = "update User set user_password = ? where user_name = ?";
        sqLiteDatabase.execSQL(sql,new String[]{userPassword,userName});
        sqLiteDatabase.close();
    }

    @Override
    public List<User> selectUser(String userName) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getReadableDatabase();

        List<User> arrayList = new ArrayList<>();

        User user = new User();

        String sql = "select * from User where user_name = ?";
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery(sql,new String[]{userName});

        if(cursor.moveToFirst()){
            user.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            user.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
            user.setUserPassword(cursor.getString(cursor.getColumnIndex("user_password")));
            arrayList.add(user);
        }

        closeDatabase(sqLiteDatabase,cursor);

        return arrayList;
    }

    public void closeDatabase(SQLiteDatabase sqLiteDatabase,Cursor cursor){
        cursor.close();
        sqLiteDatabase.close();
    }
}
