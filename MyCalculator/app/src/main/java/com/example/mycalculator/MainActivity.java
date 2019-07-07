package com.example.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9, bt_point;
    ImageButton bt_plus, bt_minus, bt_multiply, bt_division;
    ImageButton bt_equal, bt_ac, bt_delete, bt_kuohao, bt_kuohao2;
    EditText edit_output1;
    boolean edit_flag;
    boolean iskong = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        bt_kuohao = (ImageButton) findViewById(R.id.button_baifen);
        bt_kuohao2 = (ImageButton) findViewById(R.id.button_switch);
        edit_output1 = (EditText) findViewById(R.id.edit_output1);


        bt_0.setOnClickListener((View.OnClickListener) this);
        bt_1.setOnClickListener((View.OnClickListener) this);
        bt_2.setOnClickListener((View.OnClickListener) this);
        bt_3.setOnClickListener((View.OnClickListener) this);
        bt_4.setOnClickListener((View.OnClickListener) this);
        bt_5.setOnClickListener((View.OnClickListener) this);
        bt_6.setOnClickListener((View.OnClickListener) this);
        bt_7.setOnClickListener((View.OnClickListener) this);
        bt_8.setOnClickListener((View.OnClickListener) this);
        bt_9.setOnClickListener((View.OnClickListener) this);
        bt_point.setOnClickListener((View.OnClickListener) this);
        bt_plus.setOnClickListener((View.OnClickListener) this);
        bt_minus.setOnClickListener((View.OnClickListener) this);
        bt_multiply.setOnClickListener((View.OnClickListener) this);
        bt_division.setOnClickListener((View.OnClickListener) this);
        bt_equal.setOnClickListener((View.OnClickListener) this);
        bt_ac.setOnClickListener((View.OnClickListener) this);
        bt_delete.setOnClickListener((View.OnClickListener) this);
        bt_kuohao.setOnClickListener((View.OnClickListener) this);
        bt_kuohao2.setOnClickListener((View.OnClickListener) this);
        edit_output1.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        String str = edit_output1.getText().toString();

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
            case R.id.button_baifen:
                edit_output1.setText(str + "(");
                break;
            case R.id.button_switch:
                edit_output1.setText(str + ")");
                break;
//                if (edit_flag) {
//                    edit_flag = false;
//                    str = "";
//                    edit_output1.setText("");
//                }
//                if (str.contains("+") || str.contains("-") || str.contains("×") || str.contains("÷")) {
//                    str = str.substring(0, str.indexOf(" "));
//                }
//                edit_output1.setText(str + " " + ((Button) v).getText() + " ");
//                break;
            case R.id.button_ac:
                edit_output1.setText("");
                break;
            case R.id.button_delete: //判断是否为空，然后在进行删除
                if (edit_flag) {
                    edit_flag = false;
                    str = "";
                    edit_output1.setText("");
                } else if (str != null && !str.equals("")) {
                    edit_output1.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.button_equal: //单独运算最后结果
                getResult();
                break;

        }


    }

    private void getResult() {
        String string = edit_output1.getText().toString();
        string = string.replaceAll("×", "*");
        string = string.replaceAll("÷", "/");
        double result = Calculator.conversion(string);
        edit_output1.setText(String.valueOf(result));

    }

//    private void getResult() {
//        // TODO Auto-generated method stub
//
//        Stack<Integer> nums = new Stack<Integer>(); // 保存数字
//        Stack<Character> opes = new Stack<Character>(); // 保存操作符
//        String string = edit_output1.getText().toString();
//        string = string.replaceAll("×", "*");
//        string = string.replaceAll("÷", "/");
//        int n = 0; // 保存每一个数字
//        char[] cs = string.toCharArray();
//        for (int i = 0; i < cs.length; i++) {
//            char temp = cs[i];
//            if (Character.isDigit(cs[i])) {
//                n = 10 * n + Integer.parseInt(String.valueOf(cs[i])); // 大于10的数字保存
//            } else {
//                if (n != 0) {
//                    nums.push(n);
//                    n = 0;
//                }
//                if (temp == '(') {
//                    opes.push(temp);
//                } else if (temp == ')') {
//                    while (opes.peek() != '(') { // 括号里面运算完
//                        int t = cal(nums.pop(), nums.pop(), opes.pop());
//                        nums.push(t);
//                    }
//                    opes.pop();
//                } else if (isType(temp) > 0) {
//                    if (opes.isEmpty()) { // 栈为空直接入栈
//                        opes.push(temp);
//                    } else {
//                        // 若栈顶元素优先级大于或等于要入栈的元素,将栈顶元素弹出并计算,然后入栈
//                        if (isType(opes.peek()) >= isType(temp)) {
//                            int t = cal(nums.pop(), nums.pop(), opes.pop());
//                            nums.push(t);
//                        }
//                        opes.push(temp);
//                    }
//                }
//            }
//        }
//        // 最后一个字符若是数字,未入栈
//        if (n != 0) {
//            nums.push(n);
//        }
//        while (!opes.isEmpty()) {
//            int t = cal(nums.pop(), nums.pop(), opes.pop());
//            nums.push(t);
//        }
//        //  System.out.println(nums.pop());
//        edit_output1.setText(String.valueOf(nums.pop()));
//    }
//
//
//    // 返回的是运算符的优先级,数字和()不需要考虑
//    public static int isType(char c) {
//        if (c == '+' || c == '-') {
//            return 1;
//        } else if (c == '*' || c == '/') {
//            return 2;
//        } else {
//            return 0;
//        }
//    }
//
//    // 运算次序是反的,跟入栈出栈次序有关
//    public static int cal(int m, int n, char c) {
//        int sum = -987654321;
//        if (c == '+') {
//            sum = n + m;
//        } else if (c == '-') {
//            sum = n - m;
//        } else if (c == '*') {
//            sum = n * m;
//        } else if (c == '/') {
//            sum = n / m;
//        }
//        return sum;
//    }
}
