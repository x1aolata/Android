package com.example.uiwidgettest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.image_view);
        editText = (EditText) findViewById(R.id.edit_text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        button.setOnClickListener(this);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:


                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("WARNING");
                progressDialog.setMessage("你的手机即将爆炸，请稍后...");
                progressDialog.setCancelable(true);
                progressDialog.show();




//                进度条逐渐增加
//                int progress=progressBar.getProgress();
//                progress=progress+10;
//                progressBar.setProgress(progress);





//                进度条的切换
//                if (progressBar.getVisibility() == View.GONE) {
//                    progressBar.setVisibility(View.VISIBLE);
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                }


//                切换图片
//                imageView.setImageResource(R.drawable.img_1);


//                显示输入框的文字
//                String inputText = editText.getText().toString();
//                Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
