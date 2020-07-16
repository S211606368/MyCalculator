package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.dao.UserDao;
import com.example.mycalculator.pojo.User;

/**
 * 登录界面
 * @author LIN
 */
public class RegisterActivity extends AppCompatActivity {

    Button register;
    TextView ownUser;

    String userName;
    String userPassword;
    String rePassword;
    EditText userNameText;
    EditText userPasswordText;
    EditText rePasswordText;

    UserDao userDao = new UserDao(this);
    User user;

    public RegisterActivity(){
        userDao = new UserDao(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        register = findViewById(R.id.register);
        register.setOnClickListener(new RegisterButtonOnclick());

        ownUser = findViewById(R.id.ownUser);
        ownUser.setOnClickListener(new OwnUserButtonOnClick());

        userNameText = findViewById(R.id.user);

        userPasswordText = findViewById(R.id.password);

        rePasswordText = findViewById(R.id.rePassword);
    }

    /**
     *
     */
    public class RegisterButtonOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (isRegister()){
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(RegisterActivity.this,"昵称、账号、密码不正确，或账号已存在",Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * 判断该账号密码是否能注册
     * @return boolean
     */
    private boolean isRegister(){
        boolean isRegister = true;
        userName = userNameText.getText().toString();
        userPassword = userPasswordText.getText().toString();
        rePassword = rePasswordText.getText().toString();

        user = userDao.selectUser(userName).get(0);

        int passwordLength = 6;

        if (userName.length() < passwordLength){
            isRegister = false;
        }else if (userPassword.length() < passwordLength){
            isRegister = false;
        }else if (userName.equals(user.getUserName())) {
            isRegister = false;
        }else if (!userPassword.equals(rePassword)){
            isRegister = false;
        }
        return isRegister;
    }

    /**
     * 已拥有账号按钮，返回登录界面
     */
    public class OwnUserButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
