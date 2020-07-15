package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 登录界面
 * @author LIN
 */
public class Register extends AppCompatActivity {

    Button register;
    TextView ownUser;

    String userName;
    String userId;
    String userPassword;
    EditText userNameText;
    EditText userIdText;
    EditText userPasswordText;

    private static final int TWO = 2;
    private static final int SIX = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        register = findViewById(R.id.register);
        register.setOnClickListener(new RegisterButtonOnclick());

        ownUser = findViewById(R.id.ownUser);
        ownUser.setOnClickListener(new OwnUserButtonOnClick());

        userNameText = findViewById(R.id.name);
        userName = userNameText.getText().toString();

        userIdText = findViewById(R.id.user);
        userId = userIdText.getText().toString();

        userPasswordText = findViewById(R.id.password);
        userPassword = userPasswordText.getText().toString();
    }

    public class RegisterButtonOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (isRegister()){
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
                Toast.makeText(Register.this,"注册成功",Toast.LENGTH_SHORT).show();
            } else{
                Intent intent = new Intent(Register.this,Register.class);
                startActivity(intent);
                Toast.makeText(Register.this,"昵称、账号、密码不正确，或账号已存在",Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean isRegister(){
        boolean isRegister = true;
        userName = userNameText.getText().toString();
        userId = userIdText.getText().toString();
        userPassword = userPasswordText.getText().toString();

        if (userName.length() < TWO){
            isRegister = false;
        }else if (userId.length() < SIX){
            isRegister = false;
        }else if (userPassword.length() <SIX){
            isRegister = false;
        }
        return isRegister;
    }

    public class OwnUserButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Register.this,Login.class);
            startActivity(intent);
        }
    }
}
