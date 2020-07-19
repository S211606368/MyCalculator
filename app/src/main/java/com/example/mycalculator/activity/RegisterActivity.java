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
import com.example.mycalculator.dao.impl.UserDaoImpl;
import com.example.mycalculator.pojo.User;

import java.util.List;

/**
 * 登录界面
 * @author LIN
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * register确认注册按钮
     * ownUser已拥有用户按钮点击后返回返回登录界面
     */
    Button register;
    TextView ownUser;

    /**
     * userNameText 用户账号文本框
     * userPassword 用户密码文本框
     * rePasswordText 确认密码文本框
     */
    EditText userNameText;
    EditText userPasswordText;
    EditText rePasswordText;

    /**
     * passwordShow 显示用户密码按钮，暂时放在密码文本框前面的密码两个字上，后面会改
     * rePasswordShow 显示用户确认密码按钮
     */
    TextView passwordShow;
    TextView rePasswordShow;

    /**
     * userName 存储用户账号文本框中的字符串
     * userPassword 存储用户密码文本框中的字符串
     * rePassword 存储用户确认密码文本框中的字符串
     */
    String userName;
    String userPassword;
    String rePassword;

    UserDaoImpl userDao;

    boolean isShowPassword = false;

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

        passwordShow = findViewById(R.id.passwordText);
        passwordShow.setOnClickListener(new PasswordSwitchOnClick());
        rePasswordShow = findViewById(R.id.rePasswordText);
        rePasswordShow.setOnClickListener(new RePasswordSwitchOnClick());
    }

    /**
     * 注册按钮
     */
    public class RegisterButtonOnclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userDao = new UserDaoImpl(RegisterActivity.this);
            if (isRegister()){
                userDao.addUser(userName,userPassword);
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

        arrayList = userDao.selectUser(userName);

        int passwordLength = 6;
        if (userName.length() < passwordLength || userPassword.length() < passwordLength){
            isRegister = false;
            Toast.makeText(RegisterActivity.this,"账号和密码应该大于六位",Toast.LENGTH_SHORT).show();
        }else {
            if (!userPassword.equals(rePassword)) {
                isRegister = false;
                Toast.makeText(RegisterActivity.this,"确认密码应该和密码相同",Toast.LENGTH_SHORT).show();
            } else {
                if (!(arrayList == null || arrayList.size() == 0)) {
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
    public class OwnUserButtonOnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
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
