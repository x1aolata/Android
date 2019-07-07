package com.example.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(btn1_click);

    }

    public View.OnClickListener btn1_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("gcd1","num");
            startActivity(intent);
        }
    };

    public void gcd(View v) {
        EditText editText = findViewById(R.id.edit_1);
        String nums = editText.getText().toString();
        int a = Integer.valueOf(nums.substring(0, nums.indexOf(',')));
        int b = Integer.valueOf(nums.substring(nums.indexOf(',') + 1));
        int min;
        int num = 0;
        boolean m = false;
        if (a > b) {
            min = b;
        } else {
            min = a;
        }
        for (int i = min; i >= 2; i--) {

            if (a % i == 0 && b % i == 0) {
                num = i;
                m = true;
                break;
            }

        }
        if (m) {
//            System.out.println("最大公约数为：" + num);
        } else {
            num = 1;
        }

        Toast.makeText(MainActivity.this, "最大公约数为：" + num, Toast.LENGTH_LONG).show();
    }
}
