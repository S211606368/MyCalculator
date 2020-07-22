package com.example.mycalculator.service.function;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mycalculator.R;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
            showPassword.setBackgroundResource(R.drawable.hide);
        } else {
            userPasswordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showPassword.setBackgroundResource(R.drawable.show);
        }
        userPassword = userPasswordText.getText().toString();
        userPasswordText.setSelection(userPassword.length());
        return !isShowPassword;
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

    public static String encryptedPassword(String password){
        byte[] encryptedPassword = new byte[0];
        try {
            encryptedPassword = MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder(encryptedPassword.length*2);
        for (byte b : encryptedPassword) {
            if ((b & 0xFF) < 0x10){
                stringBuilder.append("0");
            }
            stringBuilder.append(Integer.toHexString(b & 0xFF));
        }
        return stringBuilder.toString();
    }
}
