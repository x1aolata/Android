package com.example.test3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,666);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 666:
                    Bundle bundle =data.getExtras();
                    Bitmap bitmap=(Bitmap)bundle.get("data");
                    ImageView iv1=findViewById(R.id.iv1);
                    iv1.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.sohu:
                intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.souhu.com"));
                startActivity(intent);
                break;
            case R.id.baidu:
                intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.jingdong:
                intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jd.com"));
                startActivity(intent);
                break;
            case R.id.taobao:
                intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.taobao.com"));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

