package com.example.mycalculator.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper的帮助类
 * @author LIN
 */
public class SqLiteConnect extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "my_calculator.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "User";
    private static final String TABLE_ID = "USER_ID";
    private static final String TABLE_USER_NAME = "USER_NAME";
    private static final String TABLE_USER_PASSWORD = "USER_PASSWORD";

    public String sql;

    public SqLiteConnect(Context context, int version) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    /**
     * 创建表
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists ");
        stringBuffer.append(TABLE_NAME + "(");
        stringBuffer.append(TABLE_ID + " integer primary key autoincrement,");
        stringBuffer.append(TABLE_USER_NAME + " varchar(16) not null unique,");
        stringBuffer.append(TABLE_USER_PASSWORD + " varchar(16) not null");
        stringBuffer.append(")");

        sqLiteDatabase.execSQL(stringBuffer.toString());

    }

    /**
     * 更新数据库
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sql = ("drop table if exists " + TABLE_NAME);
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}