package com.example.coolmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.graphics.Color;
import android.os.Bundle;

import com.dxtt.coolmenu.CoolMenuFrameLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CoolMenuFrameLayout coolMenuFrameLayout;
    List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coolMenuFrameLayout = findViewById(R.id.rl_main);

        String[] titles = {"修改地点", "删除景点", "增加景点"};
        List<String> titleList = Arrays.asList(titles);
//set your titles,which is optional
        coolMenuFrameLayout.setTitles(titleList);
        coolMenuFrameLayout.setBackgroundColor(Color.WHITE);

//set your menu icon
//        coolMenuFrameLayout.setMenuIcon(R.drawable.menu2);

        fragments.add(new AddscenicspotsFragment());
        fragments.add(new DeleteFragment());
        fragments.add(new ReviseFragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        coolMenuFrameLayout.setAdapter(adapter);


    }
}
