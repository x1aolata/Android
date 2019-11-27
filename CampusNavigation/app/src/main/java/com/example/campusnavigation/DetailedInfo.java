package com.example.campusnavigation;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class DetailedInfo extends AppCompatActivity {
    long mHints = 0;
    int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // 设置新标题
        Intent intent = getIntent();
        String s = intent.getStringExtra("lable");
        setTitle(s);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Snackbar.make(view, "别点了,没东西......", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                Log.d("x1aolata", "onClick: " + mHints);

//                if (times == 0) {
//                    mHints = SystemClock.uptimeMillis();
//                    times++;
//
//                } else if (SystemClock.uptimeMillis() - mHints <= 1000) {//连续点击之间间隔小于一秒，有效
//                    mHints = SystemClock.uptimeMillis();
//                    times++;
//                } else {
//                    times = 0;
//                }
//                if (times == 5) {
//                    Snackbar.make(view, "别点了,没东西......", Snackbar.LENGTH_SHORT)
//                            .setAction("Action", null).show();
//                    times=0;
//                }
//                else
//                {
//                    Snackbar.make(view, "再点击"+(5-times)+"次进入开发者模式", Snackbar.LENGTH_SHORT)
//                            .setAction("Action", null).show();
//                }


            }
        });
    }
}
