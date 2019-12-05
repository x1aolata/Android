package com.example.campusnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dxtt.coolmenu.CoolMenuFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdministratorMode extends AppCompatActivity {

    CoolMenuFrameLayout coolMenuFrameLayout;
    List<Fragment> fragments = new ArrayList<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_mode);
        setTitle("开发者模式");
        getSupportActionBar().hide(); // 隐藏ActionBar


        // 菜单配置
        coolMenuFrameLayout = findViewById(R.id.administrator);
        String[] titles = {"修改景点信息", "删除景点信息", "增加景点信息"};
        List<String> titleList = Arrays.asList(titles);
        coolMenuFrameLayout.setTitles(titleList);
        coolMenuFrameLayout.setBackgroundColor(Color.WHITE);
//        coolMenuFrameLayout.setMenuIcon(R.drawable.menu2);
        fragments.add(new AlterSpotFragment());
        fragments.add(new DeleteSpotFragment());
        fragments.add(new AddSpotFragment());
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
