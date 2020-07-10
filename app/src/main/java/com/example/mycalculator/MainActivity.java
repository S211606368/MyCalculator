package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 计算器
 * @author 林书浩
 * @value ADD
 * @value SUB
 * @value RIDE
 * @value DIVISION
 */
public class MainActivity extends AppCompatActivity {
    /**
     * calculation 存储的是你在计算器上输入的运算式，比如123+456+568
     */
    String calculation = "";

    /**
     * total 是当前计算器上输入的运算式数值的合计，比如123+456+789=1368，total就会存储1368这个数值
     */
    double total = 0;

    double lastNumber = 0;

    /**
     * count 是计算器上当前输入数值的显示，比如123%，count上的数值会在使用符号后加入到calculation中
     */
    String count = "";

    /**
     * symbol 是符号标记用于判断运算时的符号
     */
    char symbol = '+';

    /**
     * isPoint 判断count中是否已存在小数点
     */
    boolean isPoint = false;

    private static final char ADD = '+';
    private static final char SUB = '-';
    private static final char RIDE = '*';
    private static final char DIVISION = '/';

    /**
     * textViewCal 用于显示calculation的文本框
     */
    TextView textViewCal;

    /**
     * textTotal 用于显示count的文本框
     */
    TextView textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCal = findViewById(R.id.text_cal);
        textViewTotal = findViewById(R.id.text_total);

        Button buttonNumber0 = findViewById(R.id.button_0);
        buttonNumber0.setOnClickListener(new ButtonNumber0OnClick());

        Button buttonNumber1 = findViewById(R.id.button_1);
        buttonNumber1.setOnClickListener(new ButtonNumber1OnClick());

        Button buttonNumber2 = findViewById(R.id.button_2);
        buttonNumber2.setOnClickListener(new ButtonNumber2OnClick());

        Button buttonNumber3 = findViewById(R.id.button_3);
        buttonNumber3.setOnClickListener(new ButtonNumber3OnClick());

        Button buttonNumber4 = findViewById(R.id.button_4);
        buttonNumber4.setOnClickListener(new ButtonNumber4OnClick());

        Button buttonNumber5 = findViewById(R.id.button_5);
        buttonNumber5.setOnClickListener(new ButtonNumber5OnClick());

        Button buttonNumber6 = findViewById(R.id.button_6);
        buttonNumber6.setOnClickListener(new ButtonNumber6OnClick());

        Button buttonNumber7 = findViewById(R.id.button_7);
        buttonNumber7.setOnClickListener(new ButtonNumber7OnClick());

        Button buttonNumber8 = findViewById(R.id.button_8);
        buttonNumber8.setOnClickListener(new ButtonNumber8OnClick());

        Button buttonNumber9 = findViewById(R.id.button_9);
        buttonNumber9.setOnClickListener(new ButtonNumber9OnClick());

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

        Button buttonDivision = findViewById(R.id.button_division);
        buttonDivision.setOnClickListener(new ButtonExceptOnClick());

        Button buttonEqual = findViewById(R.id.button_equal);
        buttonEqual.setOnClickListener(new ButtonEqualOnClick());

        Button buttonSign = findViewById(R.id.button_sign);
        buttonSign.setOnClickListener(new ButtonSignOnClick());

        Button buttonClean = findViewById(R.id.button_clear);
        buttonClean.setOnClickListener(new ButtonClearOnClick());

        Button buttonDel = findViewById(R.id.button_del);
        buttonDel.setOnClickListener(new ButtonDelOnClick());
    }

    /**
     * 数字0按钮
     */
    private class ButtonNumber0OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "0";
            textViewTotal.setText(count);
        }
    }
    /**
     * 数字1按钮
     */
    private class ButtonNumber1OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "1";
            textViewTotal.setText(count);
        }
    }

    /**
     * 数字2按钮
     */
    private class ButtonNumber2OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            count += "2";
            textViewTotal.setText(count);
        }
    }
    /**
     * 数字3按钮
     */
    private class ButtonNumber3OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "3";
            textViewTotal.setText(count);
        }
    }
    /**
     * 数字4按钮
     */
    private class ButtonNumber4OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "4";
            textViewTotal.setText(count);
        }
    }
    /**
     * 数字5按钮
     */
    private class ButtonNumber5OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "5";
            textViewTotal.setText(count);
        }
    }
    /**
     * 数字6按钮
     */
    private class ButtonNumber6OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "6";
            textViewTotal.setText(count);
        }
    }
    /**
     * 数字7按钮
     */
    private class ButtonNumber7OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "7";
            textViewTotal.setText(count);
        }
    }
    /**
     * 数字8按钮
     */
    private class ButtonNumber8OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "8";
            textViewTotal.setText(count);
        }
    }
    /**
     * 数字9按钮
     */
    private class ButtonNumber9OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            count += "9";
            textViewTotal.setText(count);
        }
    }
    /**
     * 百分号按钮
     */
    private class ButtonPerOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            boolean isDouble;
            count = "" + Double.parseDouble(count) * 0.01;
            isDouble = (0 == (Double.parseDouble(count) % 1));
            if (isDouble){
                count = count.substring(0,count.length()-2);
            }
            textViewTotal.setText(count);
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
                textViewTotal.setText(count);
            }
        }
    }
    /**
     * 加号按钮
     */
    private class ButtonAddOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (count.length() > 0) {
                total = getOperation(total, Double.parseDouble(count));
                calculation += (count + "＋");
                count = "";
            } else if (calculation.length() > 0) {
                calculation = calculation.substring(0, calculation.length() - 1);
                calculation += "＋";
            }
            symbol = '+';
            textViewTotal.setText(("" + total));
            textViewCal.setText(calculation);
        }
    }
    /**
     * 减号按钮
     */
    private class ButtonSubOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (count.length() > 0) {
                total = getOperation(total, Double.parseDouble(count));
                calculation += (count + "－");
                count = "";
            } else if (calculation.length() > 0) {
                calculation = calculation.substring(0, calculation.length() - 1);
                calculation += "－";
            }
            symbol = '-';
            textViewTotal.setText(("" + total));
            textViewCal.setText(calculation);
        }
    }
    /**
     * 乘号按钮
     */
    private class ButtonRideOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (count.length() > 0) {
                total = getOperation(total, Double.parseDouble(count));
                lastNumber = Double.parseDouble(count);
                calculation += (count + "×");
                count = "";
            } else if (calculation.length() > 0) {
                calculation = calculation.substring(0, calculation.length() - 1);
                calculation += "×";
            }
            symbol = '*';
            textViewTotal.setText(("" + total));
            textViewCal.setText(calculation);
        }
    }
    /**
     * 除号按钮
     */
    private class ButtonExceptOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (count.length() > 0) {
                total += getOperation(lastNumber, Double.parseDouble(count))-lastNumber;
                lastNumber = Double.parseDouble(count);
                calculation += (count + "÷");
                count = "";
            } else if (calculation.length() > 0) {
                calculation = calculation.substring(0, calculation.length() - 1);
                calculation += "÷";
            }
            symbol = '/';
            textViewTotal.setText(("" + total));
            textViewCal.setText(calculation);
        }
    }

    /**
     * 等于号按钮
     */
    private class ButtonEqualOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            if (count.length() > 0){
                total = getOperation(total,Double.parseDouble(count));
            }
            calculation += count;
            count = "" + total;

            if (0 == Double.parseDouble(count)%1){
                count = count.substring(0,count.length()-2);
            }
            symbol = ' ';
            textViewTotal.setText(count);
            textViewCal.setText(calculation);
            calculation = "";
        }
    }
    /**
     * 正负号按钮
     */
    private class ButtonSignOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            if ("".equals(count) || SUB != count.charAt(0)){
                count = "-"+ count;
            } else{
                count = count.substring(1);
            }
            textViewTotal.setText(count);
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
            symbol = '+';
            lastNumber = 0;
            total = 0;
            textViewTotal.setText(count);
            textViewCal.setText(calculation);
        }
    }

    private class ButtonDelOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            int length = count.length();
            if (length>0){
                count = count.substring(0,length-1);
                textViewTotal.setText(count);
            }
        }
    }


    /**
     * 运算方法,使用时需要输入两个double类型的值，运算式为number ＋\－\×\÷ otherNumber
     * @param number 被计算的数字(比如被除数,被乘数)
     * @param otherNumber 用来计算的数字(比如除数,乘数)
     * @return 返回计算结果result
     */
    private double getOperation(double number,double otherNumber){
        double result = number;
        switch (symbol){
            case ADD:result += otherNumber;break;
            case SUB:result -= otherNumber;break;
            case RIDE:result *= otherNumber;break;
            case DIVISION:result /= otherNumber;break;
            default: break;
        }
        return result;
    }


}
