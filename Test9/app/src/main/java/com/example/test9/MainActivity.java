package com.example.test9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NiceSpinner niceSpinnerstart,niceSpinnerend;

        LinkedList<String> data;
        niceSpinnerstart = (NiceSpinner) findViewById(R.id.start_NiceSpinner);
        niceSpinnerstart.setTextColor(Color.BLACK);
        data = new LinkedList<>(Arrays.asList("体检中心", "操场", "校门北口", "银杏景观", "邯郸音乐厅", "图书馆", "餐厅", "信息学部", "花园景观", "校门东口", "网计学院", "校门南口"));
        niceSpinnerstart.attachDataSource(data);


        niceSpinnerend = (NiceSpinner) findViewById(R.id.end_NiceSpinner);
        niceSpinnerend.setTextColor(Color.BLACK);
        data = new LinkedList<>(Arrays.asList("体检中心", "操场", "校门北口", "银杏景观", "邯郸音乐厅", "图书馆", "餐厅", "信息学部", "花园景观", "校门东口", "网计学院", "校门南口" ));
        niceSpinnerend.attachDataSource(data);

        Button button = findViewById(R.id.search_shortestPath);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this,niceSpinnerstart.getText()+"   "+niceSpinnerend.getText(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,ScrollingActivity.class);
                intent.putExtra("lable","花园");
                startActivity(intent);
            }
        });

    }


}
