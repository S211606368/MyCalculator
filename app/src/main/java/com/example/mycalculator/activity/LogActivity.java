package com.example.mycalculator.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.sqlite.DatabaseOpenHelper;

/**
 * 登录日志界面
 * @author LIN
 */
public class LogActivity extends AppCompatActivity {
    TextView logTextView;
    String logString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseOpenHelper.getInstance(LogActivity.this);

        setContentView(R.layout.login);

        logTextView = findViewById(R.id.logText);
    }
}
