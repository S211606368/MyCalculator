package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 计算器
 * @author 林书浩
 * calculation 存储的是你在计算器上输入的运算式，比如123+456+568
 * total 是当前计算器上输入的运算式数值的合计，比如123+456+789=1368，total就会存储1368这个数值
 * count 是计算器上当前输入数值的显示，比如123%，count上的数值会在使用符号后加入到calculation中
 * symbol 是符号标记用于判断运算时的符号
 * isPoint 判断count中是否已存在小数点
 * textViewCal 用于显示calculation的文本框
 * @value ADD
 * @value SUB
 * @value RIDE
 * @value DIVISION
 */
public class MainActivity extends AppCompatActivity{
    /**

     */
    String calculation;
    double total = 0;
    String count;
    char symbol = '+';
    boolean isPoint = false;
    public static final int ADD = '+';
    public static final int SUB = '-';
    public static final int RIDE = '*';
    public static final int DIVISION = '/';

    TextView textViewCal = findViewById(R.id.text_cal);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonNub0 = findViewById(R.id.button_0);
        buttonNub0.setOnClickListener(new ButtonNub0OnClick());

        Button buttonNub1 = findViewById(R.id.button_1);
        buttonNub1.setOnClickListener(new ButtonNub1OnClick());

        Button buttonNub2 = findViewById(R.id.button_2);
        buttonNub2.setOnClickListener(new ButtonNub2OnClick());

        Button buttonNub3 = findViewById(R.id.button_3);
        buttonNub3.setOnClickListener(new ButtonNub3OnClick());

        Button buttonNub4 = findViewById(R.id.button_4);
        buttonNub4.setOnClickListener(new ButtonNub4OnClick());

        Button buttonNub5 = findViewById(R.id.button_5);
        buttonNub5.setOnClickListener(new ButtonNub5OnClick());

        Button buttonNub6 = findViewById(R.id.button_6);
        buttonNub6.setOnClickListener(new ButtonNub6OnClick());

        Button buttonNub7 = findViewById(R.id.button_7);
        buttonNub7.setOnClickListener(new ButtonNub7OnClick());

        Button buttonNub8 = findViewById(R.id.button_8);
        buttonNub8.setOnClickListener(new ButtonNub8OnClick());

        Button buttonNub9 = findViewById(R.id.button_9);
        buttonNub9.setOnClickListener(new ButtonNub9OnClick());

        Button buttonPer = findViewById(R.id.button_per);
        buttonPer.setOnClickListener(new ButtonPerOnClick());

        Button buttonPoint = findViewById(R.id.button_point);
        buttonPoint.setOnClickListener(new ButtonPointOnClick());

        Button buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new ButtonAddOnClick());

        Button buttonSub = findViewById(R.id.button_sub);
        buttonSub.setOnClickListener(new ButtonSubOnClick());

        Button buttonRide = findViewById(R.id.button_ride);
        buttonRide.setOnClickListener(new ButtonRideOnClick());

        Button buttonExcept = findViewById(R.id.button_except);
        buttonExcept.setOnClickListener(new ButtonExceptOnClick());

        Button buttonSign = findViewById(R.id.button_sign);
        buttonSign.setOnClickListener(new ButtonSignOnClick());

        Button buttonClean = findViewById(R.id.button_clear);
        buttonClean.setOnClickListener(new ButtonClearOnClick());

        Button buttonEqual = findViewById(R.id.button_equal);
        buttonEqual.setOnClickListener(new ButtonEqualOnClick());
    }

    /**
     * 数字0按钮
     */
    private class ButtonNub0OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "0";
        }
    }
    /**
     * 数字1按钮
     */
    private class ButtonNub1OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "1";
        }
    }

    /**
     * 数字2按钮
     */
    private class ButtonNub2OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            count += "2";
        }
    }
    /**
     * 数字3按钮
     */
    private class ButtonNub3OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "3";
        }
    }
    /**
     * 数字4按钮
     */
    private class ButtonNub4OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "4";
        }
    }
    /**
     * 数字5按钮
     */
    private class ButtonNub5OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "5";
        }
    }
    /**
     * 数字6按钮
     */
    private class ButtonNub6OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "6";
        }
    }
    /**
     * 数字7按钮
     */
    private class ButtonNub7OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "7";
        }
    }
    /**
     * 数字8按钮
     */
    private class ButtonNub8OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "8";
        }
    }
    /**
     * 数字9按钮
     */
    private class ButtonNub9OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "9";
        }
    }
    /**
     * 百分号按钮
     */
    private class ButtonPerOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count = "" + Double.parseDouble(count) * 0.01;
        }
    }
    /**
     * 小数点按钮
     */
    private class ButtonPointOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            if (!isPoint){
                count += ".";
                isPoint = true;
            }
        }
    }
    /**
     * 加号按钮
     */
    private class ButtonAddOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            char c = calculation.charAt(calculation.length()-1);
            if (c == ADD||c == SUB|| c == RIDE || c == DIVISION){
                calculation = calculation.substring(0,calculation.length()-1);
                calculation += "+";
                symbol = '+';
                return;
            }
            calculation += (" " + count + " +");
            total = getOperation(total,Double.parseDouble(count));
            textViewCal.setText(calculation);
        }
    }
    /**
     * 减号按钮
     */
    private class ButtonSubOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            char c = calculation.charAt(calculation.length()-1);
            if (c == ADD||c == SUB|| c == RIDE || c == DIVISION){
                calculation = calculation.substring(0,calculation.length()-1);
                calculation += "-";
                symbol = '-';
                return;
            }
            calculation += (" "+count + " -");
            total = getOperation(total,Double.parseDouble(count));
            textViewCal.setText(calculation);
        }
    }
    /**
     * 乘号按钮
     */
    private class ButtonRideOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            char c = calculation.charAt(calculation.length()-1);
            if (c == ADD||c == SUB|| c == RIDE || c == DIVISION){
                calculation = calculation.substring(0,calculation.length()-1);
                calculation += "*";
                symbol = '*';
                return;
            }
            calculation += (" "+count + " *");
            total = getOperation(total,Double.parseDouble(count));
            textViewCal.setText(calculation);
        }
    }
    /**
     * 除号按钮
     */
    private class ButtonExceptOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            char c = calculation.charAt(calculation.length()-1);
            if (c == ADD||c == SUB|| c == RIDE || c == DIVISION){
                calculation = calculation.substring(0,calculation.length()-1);
                calculation += "/";
                symbol = '/';
                return;
            }
            calculation += (" "+count + " /");
            total = getOperation(total,Double.parseDouble(count));
            textViewCal.setText(calculation);
        }
    }
    /**
     * 正负号按钮
     */
    private class ButtonSignOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            if (count.charAt(0) == SUB){
                count = count.substring(1);
            } else{
                count = "-"+ count;
            }
        }
    }
    /**
     * 清除按钮
     */
    private class ButtonClearOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count = "";
            calculation = "";
            total = 0;
        }
    }
    /**
     * 等于号按钮
     */
    private class ButtonEqualOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            total = getOperation(total,Double.parseDouble(count));
            count = "" + total;
            calculation = "";
            textViewCal.setText(calculation);
        }
    }

    /**
     * 运算方法,使用时需要输入两个double类型的值，运算式为number ＋\－\×\÷ otherNub
     * @param number 被计算的数字(比如被除数,被乘数)
     * @param otherNub 用来计算的数字(比如除数,乘数)
     * @return 返回计算结果result
     */
    private double getOperation(double number,double otherNub){
        double result = number;
        if (symbol == ADD){
            result += otherNub;
        }
        if (symbol == SUB){
            total -= otherNub;
        }
        if (symbol == RIDE){
            total *= otherNub;
        }
        if (symbol == DIVISION){
            total /= otherNub;
        }
        return result;
    }
}
