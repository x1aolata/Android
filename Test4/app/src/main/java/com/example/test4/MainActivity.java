package com.example.test4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str="选择了：";
                RadioButton rb1=findViewById(R.id.radioButton1);
                if(rb1.isChecked())
                {
                    str=str+rb1.getText().toString();
                }
                rb1=findViewById(R.id.radioButton2);
                if(rb1.isChecked())
                {
                    str=str+rb1.getText().toString();
                }
                rb1=findViewById(R.id.radioButton3);
                if(rb1.isChecked())
                {
                    str=str+rb1.getText().toString();
                }
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();


            }
        });
        Button btn2=findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str="选择了：";
                CheckBox cb=findViewById(R.id.checkBox1);
                if(cb.isChecked())
                {
                    str=str+cb.getText().toString()+" ";
                }
                cb=findViewById(R.id.checkBox2);
                if(cb.isChecked())
                {
                    str=str+cb.getText().toString()+" ";
                }
                cb=findViewById(R.id.checkBox3);
                if(cb.isChecked())
                {
                    str=str+cb.getText().toString()+" ";
                }
                cb=findViewById(R.id.checkBox4);
                if(cb.isChecked())
                {
                    str=str+cb.getText().toString()+" ";
                }
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
