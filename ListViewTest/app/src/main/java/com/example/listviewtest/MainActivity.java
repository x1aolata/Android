package com.example.listviewtest;



import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.widget.Toast;



import java.util.ArrayList;

import java.util.List;



public class MainActivity extends AppCompatActivity {



    private List<Fruit> fruitList = new ArrayList<Fruit>();



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initFruits(); // 初始化水果数据

        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);

        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view,

                                    int position, long id) {

                Fruit fruit = fruitList.get(position);

                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();

            }

        });

    }
    private void initFruits() {
        for(int i=0;i<5;i++)
        {
            Fruit apple1=new Fruit("Apple1",R.drawable.p1);
            Fruit apple2=new Fruit("Apple2",R.drawable.p2);
            Fruit apple3=new Fruit("Apple3",R.drawable.p3);
            Fruit apple4=new Fruit("Apple4",R.drawable.p4);
            fruitList.add(apple1);
            fruitList.add(apple2);
            fruitList.add(apple3);
            fruitList.add(apple4);
        }
    }
}
