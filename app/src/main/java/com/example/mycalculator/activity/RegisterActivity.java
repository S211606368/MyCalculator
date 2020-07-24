package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.dao.impl.UserDaoImpl;
import com.example.mycalculator.pojo.User;
import com.example.mycalculator.service.function.PasswordFunction;
import com.example.mycalculator.sqlite.DatabaseOpenHelper;

import java.io.IOException;
import java.util.List;

/**
 * 注册界面
 * @author LIN
 */
public class RegisterActivity extends AppCompatActivity {

    Button registerButton;

    EditText userNameEditText;
    EditText userPasswordEitText;
    EditText rePasswordEditText;

    ImageView showPasswordImageView;
    ImageView showRePasswordImageView;

    ImageView goBackImageView;

    ImageView clearUserNameImageView;
    ImageView clearPasswordImageView;
    ImageView clearRePasswordImageView;

    String userNameString;
    String userPasswordString;
    String rePasswordString;

    UserDaoImpl userDaoImpl;

    boolean isShowPassword = false;
    boolean isShowRePassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        DatabaseOpenHelper.getInstance(RegisterActivity.this);

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new RegisterButtonOnclick());

        userNameEditText = findViewById(R.id.user);

        userPasswordEitText = findViewById(R.id.password);

        rePasswordEditText = findViewById(R.id.rePassword);

        goBackImageView = findViewById(R.id.go_back);
        goBackImageView.setOnClickListener(new GoBackOnClick());

        showPasswordImageView = findViewById(R.id.hidePassword);
        showPasswordImageView.setOnClickListener(new ShowPasswordOnClick());
        showRePasswordImageView = findViewById(R.id.hideRePassword);
        showRePasswordImageView.setOnClickListener(new ShowRePasswordOnClick());

        clearUserNameImageView = findViewById(R.id.clearUserName);
        clearUserNameImageView.setOnClickListener(new ClearUserNameOnClick());
        clearPasswordImageView = findViewById(R.id.clearPassword);
        clearPasswordImageView.setOnClickListener(new ClearPasswordOnClick());
        clearRePasswordImageView = findViewById(R.id.clearRePassword);
        clearRePasswordImageView.setOnClickListener(new ClearRePasswordOnClick());

        PasswordFunction.watcherText(userNameEditText,clearUserNameImageView);
        PasswordFunction.watcherText(userPasswordEitText,clearPasswordImageView);
        PasswordFunction.watcherText(rePasswordEditText,clearRePasswordImageView);

        try {
            userDaoImpl = new UserDaoImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回上个界面
     */
    private class GoBackOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 注册按钮
     */
    private class RegisterButtonOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            if (isRegister()){
                userPasswordString = PasswordFunction.encryptedPassword(userPasswordString);
                userDaoImpl.addUser(userNameString,userPasswordString);
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
        userNameString = userNameEditText.getText().toString();
        userPasswordString = userPasswordEitText.getText().toString();
        rePasswordString = rePasswordEditText.getText().toString();

        List<User> arrayList;

        arrayList = userDaoImpl.selectUser(userNameString);

        int passwordLength = 6;
        if (userNameString.length() < passwordLength || userPasswordString.length() < passwordLength){
            isRegister = false;
            Toast.makeText(RegisterActivity.this,"账号和密码应该大于六位",Toast.LENGTH_SHORT).show();
        }else {
            if (!userPasswordString.equals(rePasswordString)) {
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
            isShowPassword = PasswordFunction.showPassword(userPasswordEitText,isShowPassword,showPasswordImageView);
        }
    }

    /**
     * 点击显示确认密码
     */
    private class ShowRePasswordOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            isShowRePassword = PasswordFunction.showPassword(rePasswordEditText,isShowRePassword,showRePasswordImageView);
        }
    }

    /**
     * 清除用户账号
     */
    private class ClearUserNameOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            userNameEditText.setText(PasswordFunction.clearPassword());
        }
    }

    /**
     * 清除用户密码
     */
    private class ClearPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userPasswordEitText.setText(PasswordFunction.clearPassword());
        }
    }

    /**
     * 清除确认密码
     */
    private class ClearRePasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            rePasswordEditText.setText(PasswordFunction.clearPassword());
        }
    }


}
