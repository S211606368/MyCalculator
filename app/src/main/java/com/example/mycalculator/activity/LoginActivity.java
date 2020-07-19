package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.dao.impl.UserDaoImpl;
import com.example.mycalculator.pojo.User;

import java.util.List;

/**
 * 登录界面
 * @author LIN
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * login 确认登录按钮
     * register 注册按钮
     * forgetPassword 忘记密码按钮，点击可以进入忘记密码界面，可以通过输入账号和新密码更改密码
     * log 登录日志按钮，点击进入登录日志界面
     */
    Button login;
    Button register;
    TextView forgetPassword;
    TextView log;

    /**
     * userNameText 用户账号文本框
     * userPassword 用户密码文本框
     */
    EditText userNameText;
    EditText userPasswordText;

    /**
     * passwordShow 显示用户密码按钮，暂时放在密码文本框前面的密码两个字上，后面会改
     */
    TextView passwordSwitch;

    /**
     * userName 存储用户账号文本框中的字符串
     * userPassword 存储用户密码文本框中的字符串
     */
    String userName;
    String userPassword;


    User user;
    UserDaoImpl userDao;

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

        passwordSwitch = findViewById(R.id.passwordText);
        passwordSwitch.setOnClickListener(new PasswordSwitchOnClick());
    }

    /**
     * 注册按钮
     */
    public class RegisterButtonOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 登录按钮
     */
    public class LoginButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userDao = new UserDaoImpl(LoginActivity.this);
            if (isLogin()){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(LoginActivity.this,"账号不存在或密码错误",Toast.LENGTH_SHORT).show();
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
        List<User> arrayList;
        arrayList = userDao.selectUser(userName);
        if (arrayList == null || arrayList.size() == 0){
            isLogin = false;
        } else {
            user = arrayList.get(0);
            if (!userPassword.equals(user.getUserPassword())){
                isLogin = false;
            }
        }
        return isLogin;
    }

    public class ForgetPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        }
    }

    public class LogOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }

    public class PasswordSwitchOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            showPassword(userPasswordText);
        }
    }

    private void showPassword(EditText userPasswordText){
        if (isShowPassword){
            userPasswordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else{
            userPasswordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        isShowPassword = !isShowPassword;
        userPassword = userPasswordText.getText().toString();
        userPasswordText.setSelection(userPassword.length());
    }
}
