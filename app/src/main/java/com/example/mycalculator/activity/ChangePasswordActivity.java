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
import com.example.mycalculator.dao.impl.UserDaoImpl;
import com.example.mycalculator.service.function.PasswordFunction;
import com.example.mycalculator.sqlite.DatabaseOpenHelper;

import java.io.IOException;

/**
 * 修改密码界面
 * @author LIN
 */
public class ChangePasswordActivity extends AppCompatActivity {
    Button changePasswordButton;
    TextView discardChangeTextView;

    EditText oldPasswordEditText;
    EditText newPasswordEditText;
    EditText rePasswordEditText;

    ImageView showOldPasswordImageView;
    ImageView showNewPasswordImageView;
    ImageView showRePasswordImageView;

    ImageView goBackImageView;

    ImageView clearOldPasswordImageView;
    ImageView clearNewPasswordImageView;
    ImageView clearRePasswordImageView;

    String oldPasswordString;
    String newPasswordString;
    String rePasswordString;

    boolean isShowOldPassword;
    boolean isShowNewPassword;
    boolean isShowRePassword;

    UserDaoImpl userDaoImpl;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        sharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        DatabaseOpenHelper.getInstance(ChangePasswordActivity.this);

        changePasswordButton = findViewById(R.id.changePassword);
        changePasswordButton.setOnClickListener(new ChangePasswordOnclick());

        oldPasswordEditText = findViewById(R.id.oldPassword);
        newPasswordEditText = findViewById(R.id.newPassword);
        rePasswordEditText = findViewById(R.id.rePassword);

        goBackImageView = findViewById(R.id.go_back);
        goBackImageView.setOnClickListener(new GoBackOnClick());

        showOldPasswordImageView = findViewById(R.id.hideOldPassword);
        showOldPasswordImageView.setOnClickListener(new ShowOldPasswordOnClick());
        showNewPasswordImageView = findViewById(R.id.hideNewPassword);
        showNewPasswordImageView.setOnClickListener(new ShowNewPasswordOnClick());
        showRePasswordImageView = findViewById(R.id.hideRePassword);
        showRePasswordImageView.setOnClickListener(new ShowRePasswordOnClick());

        clearOldPasswordImageView = findViewById(R.id.clearOldPassword);
        clearOldPasswordImageView.setOnClickListener(new ClearOldPasswordOnClick());
        clearNewPasswordImageView = findViewById(R.id.clearNewPassword);
        clearNewPasswordImageView.setOnClickListener(new ClearNewPasswordOnClick());
        clearRePasswordImageView = findViewById(R.id.clearRePassword);
        clearRePasswordImageView.setOnClickListener(new ClearRePasswordOnClick());

        PasswordFunction.watcherText(oldPasswordEditText,clearOldPasswordImageView);
        PasswordFunction.watcherText(newPasswordEditText,clearNewPasswordImageView);
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
            Intent intent = new Intent(ChangePasswordActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 确认修改密码按钮
     */
    private class ChangePasswordOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (isChange()){
                String userName = sharedPreferences.getString("userName","");
                userDaoImpl.updateUser(userName,PasswordFunction.encryptedPassword(newPasswordString));

                Intent intent = new Intent(ChangePasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(ChangePasswordActivity.this,"修改成功，原先的账号已失效请重新登录",Toast.LENGTH_SHORT).show();

                SharedPreferences .Editor editor = sharedPreferences.edit();
                editor.remove("userPassword");
                editor.apply();
            }
        }
    }

    private boolean isChange(){
        boolean isChange = true;
        oldPasswordString = oldPasswordEditText.getText().toString();
        newPasswordString = newPasswordEditText.getText().toString();
        rePasswordString = rePasswordEditText.getText().toString();

        String userPassword = sharedPreferences.getString("userPassword","");

        int passwordLength = 6;
        if (newPasswordString.length() < passwordLength){
            isChange = false;
            Toast.makeText(ChangePasswordActivity.this,"新密码应该大于六位",Toast.LENGTH_SHORT).show();
        }else {
            if (!newPasswordString.equals(rePasswordString)) {
                isChange = false;
                Toast.makeText(ChangePasswordActivity.this,"确认密码应该和新密码相同",Toast.LENGTH_SHORT).show();
            } else {
                if (!oldPasswordString.equals(userPassword)) {
                    isChange = false;
                    Toast.makeText(ChangePasswordActivity.this,"原本的密码输入错误",Toast.LENGTH_SHORT).show();
                }
            }
        }


        return isChange;
    }

    /**
     * 显示旧的密码
     */
    private class ShowOldPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            isShowRePassword = PasswordFunction.showPassword(oldPasswordEditText,isShowOldPassword,showOldPasswordImageView);
        }
    }

    /**
     * 显示新的密码
     */
    private class ShowNewPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            isShowNewPassword = PasswordFunction.showPassword(newPasswordEditText,isShowNewPassword,showNewPasswordImageView);
        }
    }

    /**
     * 显示确认密码
     */
    private class ShowRePasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            PasswordFunction.showPassword(rePasswordEditText,isShowRePassword,showRePasswordImageView);
        }
    }

    private class ClearOldPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            oldPasswordEditText.setText(PasswordFunction.clearPassword());
        }
    }

    private class ClearNewPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            newPasswordEditText.setText(PasswordFunction.clearPassword());
        }
    }

    private class ClearRePasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            rePasswordEditText.setText(PasswordFunction.clearPassword());
        }
    }
}
