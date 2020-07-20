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
 * @author LIN
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "my_calculator.db";
    public static final String PACKAGE_NAME = "com.example.mycalculator";
    private static final int DATABASE_VERSION = 1;

    private String dataBasePath;
    private final Context context;


    private static final String TABLE_NAME = "User";
    private static final String TABLE_ID = "USER_ID";
    private static final String TABLE_USER_NAME = "USER_NAME";
    private static final String TABLE_USER_PASSWORD = "USER_PASSWORD";

    public String sql;

    /**
     * DatabaseOpenHelper的构造方法
     * @param context
     */
    public DatabaseOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
        dataBasePath = "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/"
                + PACKAGE_NAME;
        System.out.println("==========database路径==========");
        System.out.println(dataBasePath);
    }

    /**
     * 创建数据库
     * @throws IOException
     */
    public void createDatabase(){
        isDatabaseExist();
        this.getReadableDatabase();
    }

    /**
     * 复制本地数据库
     * @throws IOException
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
        SQLiteDatabase sqLiteDatabase = null;
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
     * @return 返回开启数据库方法
     * @throws SQLException
     */
    public SQLiteDatabase openDatabase() throws SQLException{
        String path = dataBasePath +"/"+ DATABASE_NAME;
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}