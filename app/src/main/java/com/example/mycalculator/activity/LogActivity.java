package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.example.mycalculator.R;
import com.example.mycalculator.dao.impl.LogDaoImpl;
import com.example.mycalculator.pojo.Log;
import com.example.mycalculator.service.function.TableFunction;
import com.example.mycalculator.sqlite.DatabaseOpenHelper;

import java.util.List;

/**
 * 登录日志界面
 *
 * @author 林书浩
 * @date 2020/07/27
 */
public class LogActivity extends AppCompatActivity {
    ImageView goBackImageView;

    LogDaoImpl logDaoImpl;

    //SmartTable logInformationSmartTable;

    SmartTable<Log> logInformationSmartTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseOpenHelper.getInstance(LogActivity.this);

        setContentView(R.layout.log);

        goBackImageView = findViewById(R.id.go_back);
        goBackImageView.setOnClickListener(new GoBackOnClick());

        getLog();
        TableFunction<Log> tableFunction = new TableFunction<>();
        tableFunction.tableColor(logInformationSmartTable,0xAAAAAAAA);
    }

    /**
     * 表格显示
     */
    private void getLog(){
        final Column<Integer> logIdColumn = new Column<>("日志编码","logId");
        final Column<String> userNameColumn = new Column<>("用户账号","userName");

        List<Log> list;
        logDaoImpl = new LogDaoImpl();

        list = logDaoImpl.selectLog();

        logInformationSmartTable = findViewById(R.id.smart_table);

        float maxZoom = 2;
        float minZoom = (float) 0.5;
        logInformationSmartTable.setZoom(true,maxZoom, minZoom);

        logIdColumn.setFixed(true);
        userNameColumn.setFixed(true);

        logInformationSmartTable.setData(list);
    }

    /**
     * 返回上个界面
     */
    private class GoBackOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LogActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
