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
import android.widget.TextView;

public class DetailedInfo extends AppCompatActivity {


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

        TextView text_introduction = findViewById(R.id.text_introduction);
        if (s.equals("图书馆")) {
            text_introduction.setText(R.string.library_text);
        } else if (s.equals("网计学院")) {
            text_introduction.setText(R.string.cs_text);
        } else {
            text_introduction.setText(Graph.getInstance().getAbout(s));
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "谢谢你的点赞！！٩(๑^o^๑)۶", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }
}
