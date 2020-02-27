package com.example.mycalculator;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9, bt_point;
    ImageButton bt_plus, bt_minus, bt_multiply, bt_division;
    ImageButton bt_equal, bt_ac, bt_delete, bt_left, bt_right;
    EditText edit_output1;
    EditText edit_back;
    boolean edit_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        String str = edit_output1.getText().toString();
        shock();
        switch (v.getId()) {
            case R.id.button_0:
                edit_output1.setText(str + "0");
                break;
            case R.id.button_1:
                edit_output1.setText(str + "1");
                break;
            case R.id.button_2:
                edit_output1.setText(str + "2");
                break;
            case R.id.button_3:
                edit_output1.setText(str + "3");
                break;
            case R.id.button_4:
                edit_output1.setText(str + "4");
                break;
            case R.id.button_5:
                edit_output1.setText(str + "5");
                break;
            case R.id.button_6:
                edit_output1.setText(str + "6");
                break;
            case R.id.button_7:
                edit_output1.setText(str + "7");
                break;
            case R.id.button_8:
                edit_output1.setText(str + "8");
                break;
            case R.id.button_9:
                edit_output1.setText(str + "9");
                break;
            case R.id.button_point:
                edit_output1.setText(str + ".");
                break;
            case R.id.button_plus:
                edit_output1.setText(str + "+");
                break;
            case R.id.button_minus:
                edit_output1.setText(str + "-");
                break;
            case R.id.button_multiply:
                edit_output1.setText(str + "×");
                break;
            case R.id.button_division:
                edit_output1.setText(str + "÷");
                break;
            case R.id.button_left:
                edit_output1.setText(str + "(");
                break;
            case R.id.button_right:
                edit_output1.setText(str + ")");
                break;
            case R.id.button_ac:
                edit_output1.setText("");
                edit_back.setText("");
                break;
            case R.id.button_delete:
                if (edit_flag) {
                    edit_flag = false;
                    edit_output1.setText("");
                } else if (str != null && !str.equals("")) {
                    edit_output1.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.button_equal:
                edit_back.setText(edit_output1.getText().toString() + "=");
                getResult();
                break;

        }


    }


    /**
     * 获取运算结果
     */
    private void getResult() {
        String string = edit_output1.getText().toString();
        string = string.replaceAll("×", "*");
        string = string.replaceAll("÷", "/");
        double result = Calculator.conversion(string);
        edit_output1.setText(String.valueOf(result));
    }

    /**
     * 震动
     */
    private void shock() {
        Vibrator vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(90);
    }

    /**
     * 初始化
     * 控件的获取与监听器设置
     */
    private void init() {
        bt_0 = (ImageButton) findViewById(R.id.button_0);
        bt_1 = (ImageButton) findViewById(R.id.button_1);
        bt_2 = (ImageButton) findViewById(R.id.button_2);
        bt_3 = (ImageButton) findViewById(R.id.button_3);
        bt_4 = (ImageButton) findViewById(R.id.button_4);
        bt_5 = (ImageButton) findViewById(R.id.button_5);
        bt_6 = (ImageButton) findViewById(R.id.button_6);
        bt_7 = (ImageButton) findViewById(R.id.button_7);
        bt_8 = (ImageButton) findViewById(R.id.button_8);
        bt_9 = (ImageButton) findViewById(R.id.button_9);
        bt_point = (ImageButton) findViewById(R.id.button_point);
        bt_plus = (ImageButton) findViewById(R.id.button_plus);
        bt_minus = (ImageButton) findViewById(R.id.button_minus);
        bt_multiply = (ImageButton) findViewById(R.id.button_multiply);
        bt_division = (ImageButton) findViewById(R.id.button_division);
        bt_equal = (ImageButton) findViewById(R.id.button_equal);
        bt_ac = (ImageButton) findViewById(R.id.button_ac);
        bt_delete = (ImageButton) findViewById(R.id.button_delete);
        bt_left = (ImageButton) findViewById(R.id.button_left);
        bt_right = (ImageButton) findViewById(R.id.button_right);
        edit_output1 = (EditText) findViewById(R.id.edit_output1);
        edit_back = (EditText) findViewById(R.id.edit_outputback);

        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_point.setOnClickListener(this);
        bt_plus.setOnClickListener(this);
        bt_minus.setOnClickListener(this);
        bt_multiply.setOnClickListener(this);
        bt_division.setOnClickListener(this);
        bt_equal.setOnClickListener(this);
        bt_ac.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_left.setOnClickListener(this);
        bt_right.setOnClickListener(this);
        edit_output1.setOnClickListener(this);
        edit_back.setOnClickListener(this);
    }
}
