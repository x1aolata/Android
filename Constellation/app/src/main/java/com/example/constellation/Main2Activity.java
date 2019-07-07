package com.example.constellation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    DatePicker datePicker;
    int year,month,day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
       datePicker=findViewById(R.id.datepicker);

    }

    private final static int[] dayArr = new int[]
            {20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
    private final static String[] constellationArr = new String[]
            {"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
                    "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    //通过生日计算星座
    public static String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];
    }

    //通过生日计算属相
    public static String getYear(int year) {
        if (year < 1900) {
            return "未知";
        }
        int start = 1900;
        String[] years = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        return years[(year - start) % years.length];
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                year=datePicker.getYear();
                month=datePicker.getMonth()+1;
                day=datePicker.getDayOfMonth();
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("Constellation",getConstellation(month, day));
                intent.putExtra("SX",getYear(year));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}