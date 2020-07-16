package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.dao.UserDao;
import com.example.mycalculator.pojo.User;

/**
 * 找回密码界面
 * @author LIN
 */
public class ForgetPasswordActivity extends AppCompatActivity {

    Button reLogin;
    Button changePassword;

    EditText userNameText;
    EditText userPasswordText;
    EditText rePasswordText;
    String userName;
    String userPassword;
    String rePassword;

    UserDao userDao;
    User user;

    public ForgetPasswordActivity(){
        userDao = new UserDao(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        reLogin = findViewById(R.id.reLogin);
        reLogin.setOnClickListener(new ReLoginButtonOnClick());

        changePassword = findViewById(R.id.changePassword);
        changePassword.setOnClickListener(new ChangePasswordButtonOnClick());

        userNameText = findViewById(R.id.user);

        userPasswordText = findViewById(R.id.password);

        rePasswordText = findViewById(R.id.rePassword);

    }

    public class ReLoginButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public class ChangePasswordButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (isChange()){
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(ForgetPasswordActivity.this,"修改密码成功",Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(ForgetPasswordActivity.this,"账号不存在或密码不正确",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isChange(){
        boolean isChange = true;
        userName = userNameText.getText().toString();
        userPassword = userPasswordText.getText().toString();
        rePassword = rePasswordText.getText().toString();

        user = userDao.selectUser(userName).get(0);

        int passwordLength = 6;
        if (userName.length() < passwordLength){
            isChange = false;
        } else if (userPassword.length() < passwordLength){
            isChange = false;
        } else if (userName.equals(user.getUserName())){
            isChange = false;
        } else if (!userPassword.equals(rePassword)){
            isChange = false;
        }
        return isChange;
    }
}
