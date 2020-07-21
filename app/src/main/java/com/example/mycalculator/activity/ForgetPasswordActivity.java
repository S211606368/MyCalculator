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
import com.example.mycalculator.service.function.PasswordFunction;
import com.example.mycalculator.dao.impl.UserDaoImpl;
import com.example.mycalculator.pojo.User;

import java.io.IOException;
import java.util.List;

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

        showPassword = findViewById(R.id.hidePassword);
        showPassword.setOnClickListener(new ShowPasswordOnClick());
        showRePassword = findViewById(R.id.hideRePassword);
        showRePassword.setOnClickListener(new ShowRePasswordOnClick());

        clearPassword = findViewById(R.id.clearPassword);
        clearPassword.setOnClickListener(new ClearPasswordOnClick());
        clearPassword = findViewById(R.id.clearRePassword);
        clearRePassword.setOnClickListener(new ClearRePasswordOnClick());
    }

    /**
     * 反回登登录界面按钮
     */
    private class ReLoginButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 修改密码按钮
     */
    private class ChangePasswordButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            try {
                userDaoImpl = new UserDaoImpl(ForgetPasswordActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isChange()){
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(ForgetPasswordActivity.this,"修改密码成功",Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(ForgetPasswordActivity.this,"账号不存在或密码不正确",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 判断是否可以修改密码
     * @return 如果可以修改密码返回true值
     */
    private boolean isChange(){
        boolean isChange = true;
        userName = userNameText.getText().toString();
        userPassword = userPasswordText.getText().toString();
        rePassword = rePasswordText.getText().toString();

        List<User> arrayList = null;
        try {
            arrayList = userDaoImpl.selectUser(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int passwordLength = 6;
        if (userPassword.length() < passwordLength){
            isChange = false;
            Toast.makeText(ForgetPasswordActivity.this,"密码应该大于六位",Toast.LENGTH_SHORT).show();
        }else {
            if (!userPassword.equals(rePassword)) {
                isChange = false;
                Toast.makeText(ForgetPasswordActivity.this,"确认密码应该和密码相同",Toast.LENGTH_SHORT).show();
            } else {
                if (arrayList == null || arrayList.isEmpty()) {
                    isChange = false;
                    Toast.makeText(ForgetPasswordActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
                }
            }
        }
        return isChange;
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
