package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.pojo.User;

/**
 * 登录界面
 * @author LIN
 */
public class Login extends AppCompatActivity {

    Button login;
    Button register;
    TextView forgetPassword;
    TextView log;

    String userId;
    String userPassword;
    EditText userIdText;
    EditText userPasswordText;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        userIdText = findViewById(R.id.user);
        userPasswordText = findViewById(R.id.password);

        login = findViewById(R.id.login);
        login.setOnClickListener(new LoginButtonOnClick());

        register = findViewById(R.id.register);
        register.setOnClickListener(new RegisterButtonOnclick());

        forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new ForgetPasswordOnClick());

        log = findViewById(R.id.log);
        log.setOnClickListener(new LogOnClick());
    }

    public class RegisterButtonOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Login.this,Register.class);
            startActivity(intent);
        }
    }

    public class LoginButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (isLogin()){
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(Login.this,"登录成功",Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(Login.this,Login.class);
                startActivity(intent);
                Toast.makeText(Login.this,"账号不存在或密码错误",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isLogin(){
        boolean isLogin = true;
        userId = userIdText.getText().toString();
        userPassword = userPasswordText.getText().toString();

        if (userId.length() < 6){
            isLogin = false;
        } else if (userPassword.length() < 6){
            isLogin = false;
        }
        return isLogin;
    }

    public class ForgetPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Login.this,ForgetPassword.class);
            startActivity(intent);
        }
    }

    public class LogOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }
}
