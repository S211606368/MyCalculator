package com.example.mycalculator.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.mycalculator.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * SQLiteOpenHelper的帮助类
 *
 * @author 林书浩
 * @date 2020/07/27
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "my_calculator.db";
    public static final String PACKAGE_NAME = "com.example.mycalculator";
    private static final int DATABASE_VERSION = 11;

    public static DatabaseOpenHelper DB_HELPER_INSTANCE = null;


    private String dataBasePath;
    private final Context context;

    /**
     * DatabaseOpenHelper的构造方法
     *
     * @param context 场景对象（Activity）
     */
    public DatabaseOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
        dataBasePath = "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/"
                + PACKAGE_NAME;
        createDatabase();
    }

    /**
     * 创建数据库
     */
    public void createDatabase(){
        isDatabaseExist();
        this.getReadableDatabase();
    }

    /**
     * 复制本地数据库
     */
    private void copyDatabase() throws IOException{
        InputStream inputStream = context.getResources().openRawResource(R.raw.my_calculator);

        String outFileName = dataBasePath + "/" + DATABASE_NAME;
        OutputStream outputStream = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];

        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    private void isDatabaseExist(){
        String path = dataBasePath + "/" + DATABASE_NAME;

        File folder = new File(dataBasePath);
        if (!folder.exists()){
            folder.mkdir();
        }

        try{
            File databaseFile = new File(path);
            if (!databaseFile.exists()){
                copyDatabase();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启数据库
     *
     * @return 返回开启数据库方法
     */
    public SQLiteDatabase openDatabase() throws SQLException{
        String path = dataBasePath +"/"+ DATABASE_NAME;
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * 获取Database实例
     * @param context 场景对象（Activity）
     * @return 返回注入场景对象的Database
     */
    public static DatabaseOpenHelper getInstance(Context context){
        if (DB_HELPER_INSTANCE == null){
            DB_HELPER_INSTANCE = new DatabaseOpenHelper(context);
        }
        return DB_HELPER_INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion == 1){
            sqLiteDatabase.execSQL("drop table if exists log");
            sqLiteDatabase.execSQL("create table Log(\n" +
                    "log_id integer primary key autoincrement,\n" +
                    "user_name varchar(16) not null,\n" +
                    "user_ip varchar(16) not null,\n" +
                    "login_date varchar(16) not null);");
        }
    }
}