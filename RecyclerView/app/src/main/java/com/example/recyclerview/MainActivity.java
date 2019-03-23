package com.example.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter =new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }
    private void initFruits() {
        for(int i=0;i<5;i++)
        {
            Fruit apple1=new Fruit("Apple1",R.drawable.p1);
            Fruit apple2=new Fruit("Apple2",R.drawable.p2);
            Fruit apple3=new Fruit("Apple3",R.drawable.p1);
            Fruit apple4=new Fruit("Apple4",R.drawable.p2);
            fruitList.add(apple1);
            fruitList.add(apple2);
            fruitList.add(apple3);
            fruitList.add(apple4);
        }
    }
}
