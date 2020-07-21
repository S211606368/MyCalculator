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
import com.example.mycalculator.service.function.PasswordFunction;
import com.example.mycalculator.dao.impl.UserDaoImpl;
import com.example.mycalculator.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * 登录界面
 * @author LIN
 */
public class RegisterActivity extends AppCompatActivity {

    Button register;
    TextView ownUser;

    EditText userNameText;
    EditText userPasswordText;
    EditText rePasswordText;

    ImageView showPassword;
    ImageView showRePassword;

    ImageView clearPassword;
    ImageView clearRePassword;

    String userName;
    String userPassword;
    String rePassword;

    UserDaoImpl userDaoImpl;

    boolean isShowPassword = false;
    boolean isShowRePassword = false;

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

        showPassword = findViewById(R.id.hidePassword);
        showPassword.setOnClickListener(new ShowPasswordOnClick());
        showRePassword = findViewById(R.id.hideRePassword);
        showRePassword.setOnClickListener(new ShowRePasswordOnClick());

        clearPassword = findViewById(R.id.clearPassword);
        clearPassword.setOnClickListener(new ClearPasswordOnClick());
        clearRePassword = findViewById(R.id.clearRePassword);
        clearRePassword.setOnClickListener(new ClearRePasswordOnClick());
    }

    /**
     * 注册按钮
     */
    private class RegisterButtonOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            try {
                userDaoImpl = new UserDaoImpl(RegisterActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (isRegister()){
                userDaoImpl.addUser(userName,userPassword);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
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

        List<User> arrayList;

        arrayList = userDaoImpl.selectUser(userName);

        int passwordLength = 6;
        if (userName.length() < passwordLength || userPassword.length() < passwordLength){
            isRegister = false;
            Toast.makeText(RegisterActivity.this,"账号和密码应该大于六位",Toast.LENGTH_SHORT).show();
        }else {
            if (!userPassword.equals(rePassword)) {
                isRegister = false;
                Toast.makeText(RegisterActivity.this,"确认密码应该和密码相同",Toast.LENGTH_SHORT).show();
            } else {
                if (!(arrayList == null || arrayList.isEmpty())) {
                    isRegister = false;
                    Toast.makeText(RegisterActivity.this,"账号已存在",Toast.LENGTH_SHORT).show();
                }
            }
        }

        return isRegister;
    }

    /**
     * 已拥有账号按钮，返回登录界面
     */
    private class OwnUserButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    /**
     * 点击显示密码
     */
    private class ShowPasswordOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            isShowPassword = PasswordFunction.showPassword(userPasswordText,isShowPassword,showPassword);
        }
    }

    /**
     * 点击显示确认密码
     */
    private class ShowRePasswordOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            isShowRePassword = PasswordFunction.showPassword(rePasswordText,isShowRePassword,showRePassword);
        }
    }

    private class ClearPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userPasswordText.setText(PasswordFunction.clearPassword());
        }
    }

    private class ClearRePasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            rePasswordText.setText(PasswordFunction.clearPassword());
        }
    }
}
