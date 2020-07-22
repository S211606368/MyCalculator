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
 * 找回密码界面
 * @author LIN
 */
public class ForgetPasswordActivity extends AppCompatActivity {

    Button reLoginButton;
    Button changePasswordButton;

    EditText userNameEditText;
    EditText userPasswordEditText;
    EditText rePasswordEditText;

    ImageView showPasswordImageView;
    ImageView showRePasswordImageView;

    ImageView clearPasswordImageView;
    ImageView clearRePasswordImageView;

    String userNameString;
    String userPasswordString;
    String rePasswordString;

    UserDaoImpl userDaoImpl;

    boolean isShowPassword = false;
    boolean isShowRePassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        DatabaseOpenHelper.getInstance(ForgetPasswordActivity.this);

        reLoginButton = findViewById(R.id.reLogin);
        reLoginButton.setOnClickListener(new ReLoginButtonOnClick());

        changePasswordButton = findViewById(R.id.changePassword);
        changePasswordButton.setOnClickListener(new ChangePasswordButtonOnClick());

        userNameEditText = findViewById(R.id.user);

        userPasswordEditText = findViewById(R.id.password);


        rePasswordEditText = findViewById(R.id.rePassword);

        showPasswordImageView = findViewById(R.id.hidePassword);
        showPasswordImageView.setOnClickListener(new ShowPasswordOnClick());
        showRePasswordImageView = findViewById(R.id.hideRePassword);
        showRePasswordImageView.setOnClickListener(new ShowRePasswordOnClick());

        clearPasswordImageView = findViewById(R.id.clearPassword);
        clearPasswordImageView.setOnClickListener(new ClearPasswordOnClick());
        clearRePasswordImageView = findViewById(R.id.clearRePassword);
        clearRePasswordImageView.setOnClickListener(new ClearRePasswordOnClick());
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
                userDaoImpl = new UserDaoImpl();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isChange()){
                userDaoImpl.updateUser(userNameString,PasswordFunction.encryptedPassword(userPasswordString));
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
        userNameString = userNameEditText.getText().toString();
        userPasswordString = userPasswordEditText.getText().toString();
        rePasswordString = rePasswordEditText.getText().toString();

        List<User> arrayList = null;
        try {
            arrayList = userDaoImpl.selectUser(userNameString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int passwordLength = 6;
        if (userPasswordString.length() < passwordLength){
            isChange = false;
            Toast.makeText(ForgetPasswordActivity.this,"密码应该大于六位",Toast.LENGTH_SHORT).show();
        }else {
            if (!userPasswordString.equals(rePasswordString)) {
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
            isShowPassword = PasswordFunction.showPassword(userPasswordEditText,isShowPassword,showPasswordImageView);
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

    private class ClearPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userPasswordEditText.setText(PasswordFunction.clearPassword());
        }
    }

    private class ClearRePasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            rePasswordEditText.setText(PasswordFunction.clearPassword());
        }
    }
}
