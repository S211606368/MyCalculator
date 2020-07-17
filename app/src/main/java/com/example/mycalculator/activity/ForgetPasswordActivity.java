package com.example.mycalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
 * 找回密码界面
 * @author LIN
 */
public class ForgetPasswordActivity extends AppCompatActivity {

    Button reLogin;
    Button changePassword;

    EditText userNameText;
    EditText userPasswordText;
    EditText rePasswordText;

    TextView passwordShow;
    TextView rePasswordShow;

    String userName;
    String userPassword;
    String rePassword;

    UserDao userDao = new UserDao(ForgetPasswordActivity.this);
    User user;

    boolean isShowPassword = false;

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

        passwordShow = findViewById(R.id.passwordText);
        passwordShow.setOnClickListener(new PasswordSwitchOnClick());
        rePasswordShow = findViewById(R.id.rePasswordText);
        rePasswordShow.setOnClickListener(new RePasswordSwitchOnClick());

    }

    /**
     * 反回登登录界面按钮
     */
    public class ReLoginButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 修改密码按钮
     */
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

    /**
     * 判断是否可以修改密码
     * @return
     */
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

    /**
     * 点击显示密码
     */
    public class PasswordSwitchOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            showPassword(userPasswordText);
        }
    }

    /**
     * 点击显示确认密码
     */
    public class RePasswordSwitchOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            showPassword(rePasswordText);
        }
    }

    private void showPassword(EditText userPasswordText){
        if (isShowPassword) {
            userPasswordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            userPasswordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        isShowPassword = !isShowPassword;
        userPassword = userPasswordText.getText().toString();
        userPasswordText.setSelection(userPassword.length());
    }
}
