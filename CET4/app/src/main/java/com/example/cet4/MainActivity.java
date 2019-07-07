package com.example.cet4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText et1 = findViewById(R.id.et1);
        final EditText et2 = findViewById(R.id.et2);
        final EditText et3 = findViewById(R.id.et3);
        final EditText et4 = findViewById(R.id.et4);
        final EditText et5 = findViewById(R.id.et5);
        final EditText et6 = findViewById(R.id.et6);
        final EditText et7 = findViewById(R.id.et7);
        final TextView tvsum = findViewById(R.id.tvsum);
        Button btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double f1, f2, f3, f4, f5, f6, f7;
                if (TextUtils.isEmpty(et1.getText())) {
                    f1 = 0;
                } else {
                    f1 = Double.valueOf(et1.getText().toString());
                }
                if (TextUtils.isEmpty(et2.getText())) {
                    f2 = 0;
                } else {
                    f2 = Double.valueOf(et2.getText().toString());
                }
                if (TextUtils.isEmpty(et3.getText())) {
                    f3 = 0;
                } else {
                    f3 = Double.valueOf(et3.getText().toString());
                }
                if (TextUtils.isEmpty(et4.getText())) {
                    f4 = 0;
                } else {
                    f4 = Double.valueOf(et4.getText().toString());
                }
                if (TextUtils.isEmpty(et5.getText())) {
                    f5 = 0;
                } else {
                    f5 = Double.valueOf(et5.getText().toString());
                }
                if (TextUtils.isEmpty(et6.getText())) {
                    f6 = 0;
                } else {
                    f6 = Double.valueOf(et6.getText().toString());
                }
                if (TextUtils.isEmpty(et7.getText())) {
                    f7 = 0;
                } else {
                    f7 = Double.valueOf(et7.getText().toString());
                }


//                 f3 = Double.valueOf(et3.getText().toString());
//                 f4 = Double.valueOf(et4.getText().toString());
//                 f5 = Double.valueOf(et5.getText().toString());
//                 f6 = Double.valueOf(et6.getText().toString());
//                 f7 = Double.valueOf(et7.getText().toString());
                double sum = f1 + f6 + (f2 + f4) * 7.1 + (f7 + f5) * 14.2 + f3 * 3.55;
                sum = (int) (sum * 100 + 0.5) / 100.0;
                tvsum.setText("     总分：" + String.valueOf(sum));
            }
        });

    }
}
