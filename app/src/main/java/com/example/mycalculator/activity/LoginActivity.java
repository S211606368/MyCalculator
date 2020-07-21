package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.dao.impl.UserDaoImpl;
import com.example.mycalculator.pojo.User;
import com.example.mycalculator.service.function.PasswordFunction;

import java.io.IOException;
import java.util.List;

/**
 * 登录界面
 * @author LIN
 */
public class LoginActivity extends AppCompatActivity {

    Button login;
    Button register;
    TextView forgetPassword;
    TextView log;

    EditText userNameText;
    EditText userPasswordText;

    ImageView showPassword;

    ImageView clearPassword;

    String userName;
    String userPassword;


    User user;
    UserDaoImpl userDaoImpl;

    boolean isShowPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        userNameText = findViewById(R.id.user);
        userPasswordText = findViewById(R.id.password);

        login = findViewById(R.id.login);
        login.setOnClickListener(new LoginButtonOnClick());

        register = findViewById(R.id.register);
        register.setOnClickListener(new RegisterButtonOnclick());

        forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new ForgetPasswordOnClick());

        log = findViewById(R.id.log);
        log.setOnClickListener(new LogOnClick());

        showPassword = findViewById(R.id.hidePassword);
        showPassword.setOnClickListener(new ShowPasswordOnClick());

        clearPassword = findViewById(R.id.clearPassword);
        clearPassword.setOnClickListener(new ClearPasswordOnClick());

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
            try {
                userDaoImpl = new UserDaoImpl(LoginActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isLogin()){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 判断是否可以登录
     * @return boolean
     */
    private boolean isLogin(){
        boolean isLogin = true;
        userName = userNameText.getText().toString();
        userPassword = userPasswordText.getText().toString();

        List<User> arrayList = null;
        try {
            arrayList = userDaoImpl.selectUser(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (arrayList == null || arrayList.isEmpty()){
            isLogin = false;
            Toast.makeText(LoginActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
        } else {
            user = arrayList.get(0);
            if (!userPassword.equals(user.getUserPassword())){
                isLogin = false;
                Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            }
        }

        return isLogin;
    }

    private class ForgetPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        }
    }

    private class LogOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }

    private class ShowPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            PasswordFunction.showPassword(userPasswordText,isShowPassword,showPassword);
        }
    }

    private class ClearPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userPasswordText.setText(PasswordFunction.clearPassword());
        }
    }
}
