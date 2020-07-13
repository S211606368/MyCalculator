package com.example.mycalculator.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mycalculator.pojo.User;
import com.example.mycalculator.sqlite.SqLietConnect;

/**
 * @author LIN
 */
public class UserDao implements UserDaoImpl{
    private SqLietConnect sqLiteConnect;

    public UserDao(Context context){
        sqLiteConnect = new SqLietConnect(context,1);
    }


    @Override
    public void addUser(User user) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_NAME",user.getUserName());
        contentValues.put("USER_ID",user.getUserId());
        contentValues.put("USER_PASSWORD",user.getUserPassword());

        sqLiteDatabase.insert("User",null,contentValues);
        sqLiteDatabase.close();
    }

    @Override
    public void deleteUser(int id) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        sqLiteDatabase.delete("User","id=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    @Override
    public void updateUser(User user) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER_NAME",user.getUserName());
        contentValues.put("USER_PASSWORD",user.getUserPassword());
        sqLiteDatabase.update("User",contentValues,"ID",new String[]{user.getId()});
    }


    @Override
    public void updateUser(String userID, String userPassword) {
        SQLiteDatabase sqLiteDatabase = sqLiteConnect.getWritableDatabase();
        String sql = "update User set user_password = ? where user_id = ?";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void selectUser(int id) {

    }
}
