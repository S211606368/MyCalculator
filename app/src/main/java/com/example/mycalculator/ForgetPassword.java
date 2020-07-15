package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 找回密码界面
 * @author LIN
 */
public class ForgetPassword extends AppCompatActivity {

    Button reLogin;
    Button changePassword;

    EditText userIdText;
    EditText userPasswordText;
    String userId;
    String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        reLogin = findViewById(R.id.reLogin);
        reLogin.setOnClickListener(new ReLoginButtonOnClick());

        changePassword = findViewById(R.id.changePassword);
        changePassword.setOnClickListener(new ChangePasswordButtonOnClick());

    }

    public class ReLoginButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ForgetPassword.this,Login.class);
            startActivity(intent);
        }
    }

    public class ChangePasswordButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (isChange()){
                Intent intent = new Intent(ForgetPassword.this,Login.class);
                startActivity(intent);
                Toast.makeText(ForgetPassword.this,"修改密码成功",Toast.LENGTH_SHORT).show();
            } else{
                Intent intent = new Intent(ForgetPassword.this,ForgetPassword.class);
                startActivity(intent);
                Toast.makeText(ForgetPassword.this,"账号不存在或密码不正确",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isChange(){
        boolean isChange = true;
        userId = userIdText.getText().toString();
        userPassword = userPasswordText.getText().toString();
        if (userId.length() < 6){
            isChange = false;
        } else if (userPassword.length() < 6){
            isChange = false;
        }
        return isChange;
    }
}
