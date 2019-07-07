package com.example.kfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView total = findViewById(R.id.textView2);
        ListView listView = findViewById(R.id.list1);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < 7; i++) {
            map.put("name", "芝猪柳帕尼尼豆浆S");
            map.put("price", "17.00");
            map.put("img", R.drawable.pnn);
            list.add(map);
            map = new HashMap<String, Object>();
            map.put("name", "十翅一桶");
            map.put("price", "39.00");
            map.put("img", R.drawable.scyt);
            list.add(map);
            map = new HashMap<String, Object>();
            map.put("name", "香辣鸡腿堡S");
            map.put("price", "19.00");
            map.put("img", R.drawable.xljtb);
            list.add(map);
            map = new HashMap<String, Object>();
            map.put("name", "(六块)吮指原味鸡");
            map.put("price", "58.00");
            map.put("img", R.drawable.rzywj);
            list.add(map);
            map = new HashMap<String, Object>();
            map.put("name", "超级翅桶可乐餐");
            map.put("price", "109.00");
            map.put("img", R.drawable.cjct);
            list.add(map);
            map = new HashMap<String, Object>();
        }
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.list_item, new String[]{"name", "price", "img"}, new int[]{R.id.cb1, R.id.tv1, R.id.iv1});
        listView.setAdapter(simpleAdapter);
        final boolean[] ischeck = new boolean[list.size()];
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("wasd", String.valueOf(position));
//                Log.d("wasd", String.valueOf(id));
                TextView tv = view.findViewById(R.id.tv1);
                CheckBox cb = view.findViewById(R.id.cb1);
                if (cb.isChecked()) {
                    cb.setChecked(false);
                    ischeck[position] = false;
//                    Log.d("wasd", cb.getText().toString());
                    double danjia = Double.valueOf(tv.getText().toString());
                    double zongjia = Double.valueOf(total.getText().toString());
                    total.setText(String.valueOf(zongjia - danjia));
                } else {
                    cb.setChecked(true);
                    ischeck[position] = true;
                    double danjia = Double.valueOf(tv.getText().toString());
                    double zongjia = Double.valueOf(total.getText().toString());
                    total.setText(String.valueOf(zongjia + danjia));

                }
            }
        });

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListView listView = findViewById(R.id.list1);
                String s = "你选择了:";
                Log.d("wasd", "共有：" + String.valueOf(simpleAdapter.getCount()));
                for (int i = 0; i < simpleAdapter.getCount(); i++) {
                    View view = listView.getAdapter().getView(i, null, null);
                    if (ischeck[i]) {
                        Log.d("wasd", String.valueOf(ischeck[i]));
                        CheckBox cb = view.findViewById(R.id.cb1);
                        s += "<" + cb.getText().toString() + ">";
                    }
                }
                s += ". 共计：" + total.getText().toString() + "元";
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
