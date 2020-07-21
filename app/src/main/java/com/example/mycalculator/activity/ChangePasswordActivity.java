package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycalculator.R;
import com.example.mycalculator.service.function.PasswordFunction;

/**
 * 修改密码界面
 * @author LIN
 */
public class ChangePasswordActivity extends AppCompatActivity {
    Button changePassword;
    TextView discardChange;

    EditText oldPasswordText;
    EditText newPasswordText;
    EditText rePasswordText;

    ImageView showOldPassword;
    ImageView showNewPassword;
    ImageView showRePassword;

    ImageView clearOldPassword;
    ImageView clearNewPassword;
    ImageView clearRePassword;

    String oldPassword;
    String newPassword;
    String rePassword;

    boolean isShowOldPassword;
    boolean isShowNewPassword;
    boolean isShowRePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        changePassword = findViewById(R.id.change_password);
        changePassword.setOnClickListener(new ChangePasswordOnclick());

        discardChange = findViewById(R.id.discardChange);
        discardChange.setOnClickListener(new DiscardChangeOnClick());

        oldPasswordText = findViewById(R.id.oldPassword);
        newPasswordText = findViewById(R.id.newPassword);
        rePasswordText = findViewById(R.id.rePassword);

        showOldPassword = findViewById(R.id.hideOldPassword);
        showOldPassword.setOnClickListener(new ShowOldPasswordOnClick());
        showNewPassword = findViewById(R.id.hideNewPassword);
        showNewPassword.setOnClickListener(new ShowNewPasswordOnClick());
        showRePassword = findViewById(R.id.hideRePassword);
        showRePassword.setOnClickListener(new ShowRePasswordOnClick());

        clearOldPassword = findViewById(R.id.clearOldPassword);
        clearOldPassword.setOnClickListener(new ClearOldPasswordOnClick());
        clearNewPassword = findViewById(R.id.clearNewPassword);
        clearNewPassword.setOnClickListener(new ClearNewPasswordOnClick());
        clearRePassword = findViewById(R.id.clearRePassword);
        clearRePassword.setOnClickListener(new ClearRePasswordOnClick());
    }

    /**
     * 确认修改密码按钮
     */
    private class ChangePasswordOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            oldPassword = oldPasswordText.getText().toString();
            newPassword = newPasswordText.getText().toString();
            rePassword = rePasswordText.getText().toString();
        }
    }

    /**
     * 放弃修改密码按钮
     */
    private class DiscardChangeOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 显示旧的密码
     */
    private class ShowOldPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            isShowRePassword = PasswordFunction.showPassword(oldPasswordText,isShowOldPassword,showOldPassword);
        }
    }

    /**
     * 显示新的密码
     */
    private class ShowNewPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            isShowNewPassword = PasswordFunction.showPassword(newPasswordText,isShowNewPassword,showNewPassword);
        }
    }

    /**
     * 显示确认密码
     */
    private class ShowRePasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            PasswordFunction.showPassword(rePasswordText,isShowRePassword,showRePassword);
        }
    }

    private class ClearOldPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            oldPasswordText.setText(PasswordFunction.clearPassword());
        }
    }

    private class ClearNewPasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            newPasswordText.setText(PasswordFunction.clearPassword());
        }
    }

    private class ClearRePasswordOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            rePasswordText.setText(PasswordFunction.clearPassword());
        }
    }
}
