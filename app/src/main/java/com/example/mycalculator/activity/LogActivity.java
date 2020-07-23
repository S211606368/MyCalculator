package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.dao.impl.LogDaoImpl;
import com.example.mycalculator.pojo.Log;
import com.example.mycalculator.sqlite.DatabaseOpenHelper;

import java.io.IOException;
import java.util.List;

/**
 * 登录日志界面
 * @author LIN
 */
public class LogActivity extends AppCompatActivity {
    TableLayout logInformationTableLayout;
    ImageView goBackImageView;

    LogDaoImpl logDaoImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseOpenHelper.getInstance(LogActivity.this);

        setContentView(R.layout.log);

        logInformationTableLayout = findViewById(R.id.log_information);

        goBackImageView = findViewById(R.id.go_back);
        goBackImageView.setOnClickListener(new GoBackOnClick());

        getLog();
    }

    private void getLog(){
        List<Log> list;
        try {
            logDaoImpl = new LogDaoImpl();

            list = logDaoImpl.selectLog();

            for (Log log:list) {
                TableRow tableRow = new TableRow(getBaseContext());
                int count = 4;

                TextView logIdTextView = new TextView(getBaseContext());
                logIdTextView.setBackgroundResource(R.drawable.table_frame);
                logIdTextView.setPadding(1, 1, 1, 1);
                logIdTextView.setText(""+log.getLogId());
                logIdTextView.setGravity(Gravity.CENTER);
                tableRow.addView(logIdTextView,0);
                System.out.println(""+log.getLogId());

                TextView userNameTextView = new TextView(getBaseContext());
                userNameTextView.setBackgroundResource(R.drawable.table_frame);
                userNameTextView.setPadding(1, 1, 1, 1);
                userNameTextView.setText(log.getUserName());
                userNameTextView.setGravity(Gravity.CENTER);
                tableRow.addView(userNameTextView,1);
                System.out.println(log.getUserName());

                TextView userIpTextView = new TextView(getBaseContext());
                userIpTextView.setBackgroundResource(R.drawable.table_frame);
                userIpTextView.setPadding(1, 1, 1, 1);
                userIpTextView.setText(log.getUserIp());
                userIpTextView.setGravity(Gravity.CENTER);
                tableRow.addView(userIpTextView,2);
                System.out.println(log.getUserIp());

                TextView loginDateTextView = new TextView(getBaseContext());
                loginDateTextView.setBackgroundResource(R.drawable.table_frame);
                loginDateTextView.setPadding(1, 1, 1, 1);
                loginDateTextView.setText(log.getLoginDate());
                loginDateTextView.setGravity(Gravity.CENTER);
                tableRow.addView(loginDateTextView,3);
                System.out.println(log.getLoginDate());

                logInformationTableLayout.addView(tableRow);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class GoBackOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LogActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
