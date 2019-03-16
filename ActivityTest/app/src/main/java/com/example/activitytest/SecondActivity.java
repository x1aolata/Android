package com.example.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity", "Task id is" + getTaskId());
        setContentView(R.layout.second_layout);


        Button button1 = (Button) findViewById(R.id.button_2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(SecondActivity.this,"You clicked Button 2",
//                        Toast.LENGTH_SHORT).show();

//                finish();//销毁活动

//                显式Intent
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);

//                隐式Intent
//                Intent intent =new Intent("com.example.activitytest.ACTION_START");
//                intent.addCategory("com.example.activitytest.MY-CATEGORY");
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestroy");
    }
}
