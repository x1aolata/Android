package com.example.campusnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setTitle("关于");

//        TextView textView = findViewById(R.id.about_text);
//        textView.setText("\n\n  这是小邋遢制作的校园导航");

    }
}
