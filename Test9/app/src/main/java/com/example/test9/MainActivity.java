package com.example.test9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NiceSpinner niceSpinnerstart, niceSpinnerend;

        LinkedList<String> data;
        niceSpinnerstart = (NiceSpinner) findViewById(R.id.start_NiceSpinner);
        niceSpinnerstart.setTextColor(Color.BLACK);
        data = new LinkedList<>(Arrays.asList("体检中心", "操场", "校门北口", "银杏景观", "邯郸音乐厅", "图书馆", "餐厅", "信息学部", "花园景观", "校门东口", "网计学院", "校门南口"));
        niceSpinnerstart.attachDataSource(data);


        niceSpinnerend = (NiceSpinner) findViewById(R.id.end_NiceSpinner);
        niceSpinnerend.setTextColor(Color.BLACK);
        data = new LinkedList<>(Arrays.asList("体检中心", "操场", "校门北口", "银杏景观", "邯郸音乐厅", "图书馆", "餐厅", "信息学部", "花园景观", "校门东口", "网计学院", "校门南口"));
        niceSpinnerend.attachDataSource(data);

        Button button = findViewById(R.id.search_shortestPath);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, niceSpinnerstart.getText() + "   " + niceSpinnerend.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                intent.putExtra("lable", "花园");
                startActivity(intent);
            }
        });


        BoomMenuButton bmb = findViewById(R.id.bmb);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .normalImageRes(R.drawable.voice)
                    .normalText("Butter").shadowEffect(true).normalColor(Color.parseColor("#FF474E4A"))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .pieceColor(Color.RED);
            bmb.addBuilder(builder);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_1:
                Toast.makeText(MainActivity.this, "Option 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_2:
                Toast.makeText(MainActivity.this, "Option 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_3:
                Toast.makeText(MainActivity.this, "Option 3", Toast.LENGTH_SHORT).show();
                return true;
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);

    }


}
