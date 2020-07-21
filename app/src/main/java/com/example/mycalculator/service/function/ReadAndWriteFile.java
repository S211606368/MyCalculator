package com.example.mycalculator.service.function;

import android.content.Context;

import com.example.mycalculator.pojo.User;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 文件读取类
 * @author LIN
 */
public class ReadAndWriteFile {

    public void saveUserText(Context context,User user){
        String userText = "" + user.getUserId()+ "，" + user.getUserName() + "，" + user.getUserPassword() + "/n";
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try{
            fileOutputStream = context.openFileOutput("it is good file", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(userText);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (bufferedWriter!=null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
