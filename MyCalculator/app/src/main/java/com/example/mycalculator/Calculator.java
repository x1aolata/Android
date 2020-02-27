package com.example.mycalculator;


import java.util.Collections;
import java.util.Stack;

/**
 * 计算器类
 */
public class Calculator {
    // 后缀表达式式栈
    private Stack<String> suffixStack = new Stack<String>();
    // 运算符栈
    private Stack<Character> operatorStack = new Stack<Character>();
    // 操作符优先级
    private int[] operatorPriority = new int[]{0, 3, 2, 1, -1, 1, 0, 2};



    public static double conversion(String expression) {
        double result = 0;
        Calculator cal = new Calculator();
        try {
            expression = transform(expression);
            result = cal.calculate(expression);
        } catch (Exception e) {

            return 0.0 / 0.0; // 运算错误
        }

        return result;
    }


    private static String transform(String expression) {
        char[] arr = expression.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if (i == 0) {
                    arr[i] = '~';
                } else {
                    char c = arr[i - 1];
                    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == 'E' || c == 'e') {
                        arr[i] = '~';
                    }
                }
            }
        }
        if (arr[0] == '~' || arr[1] == '(') {
            arr[0] = '-';
            return "0" + new String(arr);
        } else {
            return new String(arr);
        }
    }

    /**
     * 计算表达式的值
     *
     * @param expression
     * @return
     */
    public double calculate(String expression) {
        Stack<String> resultStack = new Stack<String>();
        prepare(expression);
        Collections.reverse(suffixStack);
        String firstValue, secondValue, currentValue;
        while (!suffixStack.isEmpty()) {
            currentValue = suffixStack.pop();
            if (!isOperator(currentValue.charAt(0))) {
                currentValue = currentValue.replace("~", "-");
                resultStack.push(currentValue);
            } else {
                secondValue = resultStack.pop();
                firstValue = resultStack.pop();


                firstValue = firstValue.replace("~", "-");
                secondValue = secondValue.replace("~", "-");

                String tempResult = calculate(firstValue, secondValue, currentValue.charAt(0));
                resultStack.push(tempResult);
            }
        }
        return Double.valueOf(resultStack.pop());
    }


    private void prepare(String expression) {
        operatorStack.push(',');
        char[] arr = expression.toCharArray();
        int currentIndex = 0;
        int count = 0;
        char currentOp, peekOp;
        for (int i = 0; i < arr.length; i++) {
            currentOp = arr[i];
            if (isOperator(currentOp)) {
                if (count > 0) {
                    suffixStack.push(new String(arr, currentIndex, count));
                }
                peekOp = operatorStack.peek();
                if (currentOp == ')') {
                    while (operatorStack.peek() != '(') {
                        suffixStack.push(String.valueOf(operatorStack.pop()));
                    }
                    operatorStack.pop();
                } else {
                    while (currentOp != '(' && peekOp != ',' && compare(currentOp, peekOp)) {
                        suffixStack.push(String.valueOf(operatorStack.pop()));
                        peekOp = operatorStack.peek();
                    }
                    operatorStack.push(currentOp);
                }
                count = 0;
                currentIndex = i + 1;
            } else {
                count++;
            }
        }
        if (count > 1 || (count == 1 && !isOperator(arr[currentIndex]))) {
            suffixStack.push(new String(arr, currentIndex, count));
        }

        while (operatorStack.peek() != ',') {
            suffixStack.push(String.valueOf(operatorStack.pop()));
        }
    }

    /**
     * 计算
     *
     * @param firstValue
     * @param secondValue
     * @param currentOp
     * @return
     */
    private String calculate(String firstValue, String secondValue, char currentOp) {
        String result = "";
        switch (currentOp) {
            case '+':
                result = String.valueOf(Double.valueOf(firstValue) + Double.valueOf(secondValue));
                break;
            case '-':
                result = String.valueOf(Double.valueOf(firstValue) - Double.valueOf(secondValue));
                break;
            case '*':
                result = String.valueOf(Double.valueOf(firstValue) * Double.valueOf(secondValue));
                break;
            case '/':
                result = String.valueOf(Double.valueOf(firstValue) / Double.valueOf(secondValue));
                break;
        }
        return result;
    }

    /**
     * 符号判断
     *
     * @param c
     * @return
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    /**
     * 优先级确定
     *
     * @param cur
     * @param peek
     * @return
     */
    public boolean compare(char cur, char peek) {
        boolean result = false;
        if (operatorPriority[(peek) - 40] >= operatorPriority[(cur) - 40]) {
            result = true;
        }
        return result;
    }


}
