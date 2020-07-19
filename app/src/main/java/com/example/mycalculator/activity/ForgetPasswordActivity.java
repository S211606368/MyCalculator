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
 * 找回密码界面
 * @author LIN
 */
public class ForgetPasswordActivity extends AppCompatActivity {

    /**
     * reLogin 放回登录界面按钮
     * changePassword 确认变更密码按钮
     */
    Button reLogin;
    Button changePassword;

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
            userDao = new UserDaoImpl(ForgetPasswordActivity.this);
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

        List<User> arrayList;

        arrayList = userDao.selectUser(userName);

        int passwordLength = 6;
        if (userPassword.length() < passwordLength){
            isChange = false;
            Toast.makeText(ForgetPasswordActivity.this,"密码应该大于六位",Toast.LENGTH_SHORT).show();
        }else {
            if (!userPassword.equals(rePassword)) {
                isChange = false;
                Toast.makeText(ForgetPasswordActivity.this,"确认密码应该和密码相同",Toast.LENGTH_SHORT).show();
            } else {
                if (arrayList == null || arrayList.size() == 0) {
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
