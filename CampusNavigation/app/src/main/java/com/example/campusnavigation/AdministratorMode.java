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


    // Add
//    Button button_add_submit;
//    EditText edittext_addfrag_name;
//    EditText edittext_addfrag_number;
//    EditText edittext_addfrag_about;
//    EditText edittext_addfrag_longitude;
//    EditText edittext_addfrag_latitude;

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


//        button_add_submit = findViewById(R.id.button_add_submit);
//        edittext_addfrag_name = findViewById(R.id.edittext_addfrag_name);
//        edittext_addfrag_number = findViewById(R.id.edittext_addfrag_number);
//        edittext_addfrag_about = findViewById(R.id.edittext_addfrag_about);
//        edittext_addfrag_longitude = findViewById(R.id.edittext_addfrag_longitude);
//        edittext_addfrag_latitude = findViewById(R.id.edittext_addfrag_latitude);
//        button_add_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Node node = new Node(edittext_addfrag_name.getText().toString(), edittext_addfrag_number.getText().toString(),
//                        Double.valueOf(edittext_addfrag_longitude.getText().toString()), Double.valueOf(edittext_addfrag_latitude.getText().toString()),
//                        edittext_addfrag_about.getText().toString());
//                Graph.getInstance().Nodes.add(node);
//            }
//        });

    }
}
