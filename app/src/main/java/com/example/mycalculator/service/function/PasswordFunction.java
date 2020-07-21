package com.example.mycalculator.service.function;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mycalculator.R;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 部分密码功能
 * @author LIN
 */
public class PasswordFunction {

    /**
     * 显示密码
     * @param userPasswordText 密码文本框
     * @param isShowPassword 判断是否显示密码
     * @param showPassword 显示密码图标
     * @return 返回值为!isShowPassword
     */
    public static boolean showPassword(EditText userPasswordText, boolean isShowPassword, ImageView showPassword){
        String userPassword;
        if (isShowPassword) {
            userPasswordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showPassword.setBackgroundResource(R.drawable.show);
        } else {
            userPasswordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showPassword.setBackgroundResource(R.drawable.hide);
        }
        isShowPassword = !isShowPassword;
        userPassword = userPasswordText.getText().toString();
        userPasswordText.setSelection(userPassword.length());
        return isShowPassword;
    }

    /**
     * 清除密码
     * @return ""
     */
    @NotNull
    @Contract(pure = true)
    public static String clearPassword(){
        return "";
    }
}
