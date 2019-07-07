package com.example.test3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Integer[] a = new Integer[]{321,5,1123,123,15,3451,2312,234};
////        for (int i = 0; i < a.length; i++) {
////            a[i] = 2 * i - 1;
////        }
//        Log.d("aaaaaaaaaa", Arrays.toString(a));
//       Arrays.sort(a,Collections.reverseOrder());
//       // Arrays.sort(a);
//        Log.d("aaaaaaaaaa", Arrays.toString(a));
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(btn1_click);
        ImageButton buttonimage=findViewById(R.id.buttonimage);
        buttonimage.setOnClickListener(btnimage);


    }

    public View.OnClickListener btn1_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intenta = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:18731606496");
            intenta.setData(data);
            startActivity(intenta);
//            Intent intenta = new Intent(Intent.ACTION_VIEW);
//            Uri data = Uri.parse("https://x1aolata.github.io");
//            intenta.setData(data);
//            startActivity(intenta);

//            int[] arr = {1, 10, 20, 30, 40, 50, 60};
//            // 创建指定长度的线程数组
//            SortThread[] sortThreads = new SortThread[arr.length];
//            // 指定每个线程数组的值
//            for (int i = 0; i < sortThreads.length; i++) {
//                sortThreads[i] = new SortThread(arr[i]);
//            }
//            // 开启每个线程
//            for (int i = 0; i < sortThreads.length; i++) {
//                sortThreads[i].start();
//            }


        }

//        class SortThread extends Thread {
//            int s = 0;
//
//            public SortThread(int s) {
//                this.s = s;
//            }
//
//            public void run() {
//                try {
//                    sleep(s * 1000 + 10); // 睡眠指定的时间
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // 输出该数
//                //System.out.println(s);
//                Intent intenta = new Intent(Intent.ACTION_CALL);
//                Uri data = Uri.parse("tel:13718070686");
//                intenta.setData(data);
//                startActivity(intenta);
//            }
//        }
    };
    public View.OnClickListener btnimage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent=new Intent(MainActivity.this,Main2Activity.class);
          startActivity(intent);


        }

//        class SortThread extends Thread {
//            int s = 0;
//
//            public SortThread(int s) {
//                this.s = s;
//            }
//
//            public void run() {
//                try {
//                    sleep(s * 1000 + 10); // 睡眠指定的时间
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // 输出该数
//                //System.out.println(s);
//                Intent intenta = new Intent(Intent.ACTION_CALL);
//                Uri data = Uri.parse("tel:13718070686");
//                intenta.setData(data);
//                startActivity(intenta);
//            }
//        }
    };
}
