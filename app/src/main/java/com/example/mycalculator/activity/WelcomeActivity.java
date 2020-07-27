package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 启动界面
 *
 * @author 林书浩
 * @date 2020/07/27
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(1000);
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
