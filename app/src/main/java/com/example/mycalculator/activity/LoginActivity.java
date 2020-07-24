package com.example.mycalculator.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.dao.impl.LogDaoImpl;
import com.example.mycalculator.dao.impl.UserDaoImpl;
import com.example.mycalculator.pojo.User;
import com.example.mycalculator.service.function.IpFunction;
import com.example.mycalculator.service.function.PasswordFunction;
import com.example.mycalculator.sqlite.DatabaseOpenHelper;

import java.io.IOException;
import java.util.List;

/**
 * 登录界面
 * @author LIN
 */
public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    TextView forgetPasswordTextView;
    TextView logTextView;

    EditText userNameEditText;
    EditText userPasswordEditText;



    ImageView showPasswordImageView;

    ImageView clearUserNameImageView;
    ImageView clearPasswordImageView;

    String userNameString;
    String userPasswordString;

    SharedPreferences sharedPreferences;

    User user;
    UserDaoImpl userDaoImpl;

    LogDaoImpl logDaoImpl;

    boolean isShowPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);

        DatabaseOpenHelper.getInstance(LoginActivity.this);

        setContentView(R.layout.login);

        sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);

        userNameEditText = findViewById(R.id.user);
        userPasswordEditText = findViewById(R.id.password);

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new LoginButtonOnClick());

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new RegisterButtonOnclick());

        forgetPasswordTextView = findViewById(R.id.forgetPassword);
        forgetPasswordTextView.setOnClickListener(new ForgetPasswordOnClick());

        logTextView = findViewById(R.id.log);
        logTextView.setOnClickListener(new LogOnClick());

        showPasswordImageView = findViewById(R.id.hidePassword);
        showPasswordImageView.setOnClickListener(new ShowPasswordOnClick());

        clearUserNameImageView = findViewById(R.id.clearUserName);
        clearUserNameImageView.setOnClickListener(new ClearUserNameOnClick());
        clearPasswordImageView = findViewById(R.id.clearPassword);
        clearPasswordImageView.setOnClickListener(new ClearPasswordOnClick());

        PasswordFunction.watcherText(userNameEditText,clearUserNameImageView);
        PasswordFunction.watcherText(userPasswordEditText,clearPasswordImageView);

        try {
            userDaoImpl = new UserDaoImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }

        autoLogin();
    }

    /**
     * 注册按钮
     */
    private class RegisterButtonOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 登录按钮
     */
    private class LoginButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            if (isLogin()){
                saveUser();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                loginLog();
            }
        }
    }

    /**
     * 生成登录日志写入数据库
     */
    private void loginLog(){
        try {
            logDaoImpl = new LogDaoImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ip = "";
        ip += IpFunction.getNetIp();
        ip += "(" + IpFunction.getLocalIp() + ")";

        String loginDate = "";
        loginDate = IpFunction.getLoginDate();

        logDaoImpl.addLog(userNameString,ip,loginDate);
    }

    /**
     * 判断是否可以登录
     * @return boolean
     */
    private boolean isLogin(){
        boolean isLogin = true;
        userNameString = userNameEditText.getText().toString();
        userPasswordString = userPasswordEditText.getText().toString();
        String password = PasswordFunction.encryptedPassword(userPasswordString);
        List<User> arrayList = null;
        try {
            arrayList = userDaoImpl.selectUser(userNameString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (arrayList == null || arrayList.isEmpty()){
            isLogin = false;
            Toast.makeText(LoginActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
        } else {
            user = arrayList.get(0);
            if (!password.equals(PasswordFunction.isEncryptedPassword(user.getUserPassword()))){
                isLogin = false;
                Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            }
        }

        return isLogin;
    }

    /**
     *忘记密码按钮
     */
    private class ForgetPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 日志按钮
     */
    private class LogOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, LogActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 显示密码按钮
     */
    private class ShowPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            isShowPassword = PasswordFunction.showPassword(userPasswordEditText,isShowPassword,showPasswordImageView);
        }
    }

    /**
     * 清除密码按钮
     */
    private class ClearPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userPasswordEditText.setText(PasswordFunction.clearPassword());
        }
    }

    /**
     * 清除账号按钮
     */
    private class ClearUserNameOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userNameEditText.setText(PasswordFunction.clearPassword());
        }
    }

    /**
     * 记住密码
     */
    private void saveUser() {
        SharedPreferences .Editor editor = sharedPreferences.edit();
        editor.putInt("userId",user.getUserId());
        editor.putString("userName",userNameString);
        editor.putString("userPassword",userPasswordString);
        editor.apply();
    }

    /**
     * 自动登录
     */
    private void autoLogin(){
        userNameString = sharedPreferences.getString("userName","");
        userPasswordString = sharedPreferences.getString("userPassword","");
        userNameEditText.setText(userNameString);
        userPasswordEditText.setText(userPasswordString);
        if (!"".equals(userNameString) || !"".equals(userPasswordString)){
            if (isLogin()){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
