package com.example.recitewords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getAbsolutePath() + "/四级单词.db", null);
            String sql1 = "select * from vocabulary ";
            Cursor cursor = db.rawQuery(sql1, null);


            if (cursor.moveToFirst()) {         //移到第1项数据（若有数据才继续）
                String str = "总共有  " + cursor.getCount() + "项数据\n";
                str += "-------\n";

                do {              //逐项读出数据，并串接成信息字符串
                    str += "序号:" + cursor.getString(0) + "\n";
                    str += "汉语:" + cursor.getString(1) + "\n";
                    str += "英文:" + cursor.getString(2) + "\n";
                    str += "音标:" + cursor.getString(3) + "\n";
                    str += "分数:" + cursor.getString(4) + "\n";
                    str += "------\n";
                    Log.d("x1aolata", str);
                    str="";
                } while (cursor.moveToNext());   //有下一项就继续循环

            }

//            db.execSQL(sql1);
//            sql1 = " create table log(id integer primary key autoincrement, rightinteger, wrong integer, date date)";
//            db.execSQL(sql1);


        } catch (Exception e) {
            Log.d("x1aolata", e.getMessage());

        }

//        创建数据库
//        Log.d("x1aolata",getFilesDir().getAbsolutePath() + "/words.db");
//        try {
//            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir().getAbsolutePath() + "/words.db", null);
//            String sql1 = "create table vocabulary(id integer primary key autoincrement, english text, chinese text, phonetic text, score integer)";
//            db.execSQL(sql1);
//            sql1 = " create table log(id integer primary key autoincrement, rightinteger, wrong integer, date date)";
//            db.execSQL(sql1);
//
//            InputStream fileInputStream = this.getAssets().open("四级单词.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            String str = "";
//            while ((str = bufferedReader.readLine()) != null) {
//                Log.d("x1aolata", str);
//
//                String english = str.substring(0, str.indexOf("/"));
//                Log.d("x1aolataenglish", english);
//                String chinese = str.substring(str.lastIndexOf("/") + 1);
//                Log.d("x1aolatachinese", chinese);
//                String phonetic = str.substring(str.indexOf("/"), str.lastIndexOf("/") + 1);
//                Log.d("x1aolataphonetic", phonetic);
//                sql1 = " insert into vocabulary (english, chinese, phonetic, score) values('" + english + "','" + chinese + "','" + phonetic + "',0)";
//                Log.d("x1aolata", sql1);
//                db.execSQL(sql1);
//            }
//            fileInputStream.close();
//            db.close();
//
//
//        } catch (Exception e) {
//            Log.d("x1aolata", e.getMessage());
//
//        }
//
//        Log.d("x1aolata", "创建成功");


    }


}



