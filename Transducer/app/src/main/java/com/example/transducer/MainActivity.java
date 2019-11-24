package com.example.transducer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    TextView mtextViewx;
    TextView mtextViewy;
    TextView mtextViewz;
    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextViewx = findViewById(R.id.tvx);
        mtextViewy = findViewById(R.id.tvy);
        mtextViewz = findViewById(R.id.tvz);
        SensorManager mSensorManager;
        List<Sensor> sensorList;
        // 实例化传感器管理者
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // 得到设置支持的所有传感器的List
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> sensorNameList = new ArrayList<String>();
        String s = "";
        for (Sensor sensor : sensorList) {
            s = s + "\n" + sensor.getName();
        }
        Log.d("x1aolata", s);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        /**
         * 传入的参数决定传感器的类型
         * Senor.TYPE_ACCELEROMETER: 加速度传感器
         * Senor.TYPE_LIGHT:光照传感器
         * Senor.TYPE_GRAVITY:重力传感器
         * SenorManager.getOrientation(); //方向传感器
         */

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//            float X_lateral = sensorEvent.values[0];
//            float Y_longitudinal = sensorEvent.values[1];
//            float Z_vertical = sensorEvent.values[2];
//            mtextViewx.setText("X:"+X_lateral);
//            mtextViewy.setText("Y:"+Y_longitudinal);
//            mtextViewz.setText("Z:"+Z_vertical);
//        }
        if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
            float X = sensorEvent.values[0];
            mtextViewx.setText("距离为"+ X );
        }
    }
}

