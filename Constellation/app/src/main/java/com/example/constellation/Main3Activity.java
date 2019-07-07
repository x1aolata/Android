package com.example.constellation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    ImageView xz;
    ImageView sx;
    TextView textView;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.text_view2);
        textView1 = findViewById(R.id.text_view3);
        xz = findViewById(R.id.image_xz);
        sx = findViewById(R.id.image_sx);
        Intent intent = getIntent();


        textView.setText("你的星座为：" + intent.getStringExtra("Constellation"));
        textView1.setText("你的生肖为：" + intent.getStringExtra("SX"));

        Toast.makeText(Main3Activity.this, "你的星座为：" + intent.getStringExtra("Constellation") + "   你的生肖为：" + intent.getStringExtra("SX"), Toast.LENGTH_SHORT).show();
        xz.setImageResource(R.drawable.baiyang);
        switch (intent.getStringExtra("Constellation")) {
//            "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
//                    "狮子座", "处女座", "天秤座", "天蝎座", "射手座",
            case "摩羯座":
                xz.setImageResource(R.drawable.mojie);
                break;
            case "水瓶座":
                xz.setImageResource(R.drawable.shuiping);
                break;
            case "狮子座":
                xz.setImageResource(R.drawable.shizi);
                break;
            case "双鱼座":
                xz.setImageResource(R.drawable.shuangyu);
                break;
            case "白羊座":
                xz.setImageResource(R.drawable.baiyang);
                break;
            case "金牛座":
                xz.setImageResource(R.drawable.jinniu);
                break;
            case "双子座":
                xz.setImageResource(R.drawable.shuangzi);
                break;
            case "巨蟹座":
                xz.setImageResource(R.drawable.juxie);
                break;
            case "处女座":
                xz.setImageResource(R.drawable.chunv);
                break;
            case "天秤座":
                xz.setImageResource(R.drawable.tianping);
                break;
            case "天蝎座":
                xz.setImageResource(R.drawable.tianxie);
                break;
            case "射手座":
                xz.setImageResource(R.drawable.sheshou);
                break;

        }
        switch (intent.getStringExtra("SX")) {
          //  "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"
            case "鼠":
                sx.setImageResource(R.drawable.shu);
                break;
            case "牛":
                sx.setImageResource(R.drawable.niu);
                break;
            case "虎":
                sx.setImageResource(R.drawable.hu);
                break;
            case "兔":
                sx.setImageResource(R.drawable.tu);
                break;
            case "龙":
                sx.setImageResource(R.drawable.long12);
                break;
            case "蛇":
                sx.setImageResource(R.drawable.she);
                break;
            case "马":
                sx.setImageResource(R.drawable.ma);
                break;
            case "羊":
                sx.setImageResource(R.drawable.yang);
                break;
            case "猴":
                sx.setImageResource(R.drawable.hou);
                break;
            case "鸡":
                sx.setImageResource(R.drawable.ji);
                break;
            case "狗":
                sx.setImageResource(R.drawable.gou);
                break;
            case "猪":
                sx.setImageResource(R.drawable.zhu);
                break;


        }

        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
//                Intent intent=getIntent();
//                xz.setImageResource(R.drawable.baiyang);
//
//                textView.setText("你的星座为："+intent.getStringExtra("Constellation"));
//                textView1.setText("你的生肖为："+intent.getStringExtra("SX"));
//
//                Toast.makeText(Main3Activity.this,"你的星座为："+intent.getStringExtra("Constellation")+"   你的生肖为："+intent.getStringExtra("SX"),Toast.LENGTH_SHORT).show();
            }
        });

    }


}
