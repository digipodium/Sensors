package com.op.sensorappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView text0;
    private TextView text1;
    private TextView text2;
    private TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        text0 = findViewById(R.id.text0);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        textName = findViewById(R.id.textName);
        int sensor_type = getIntent().getExtras().getInt("sensor");
        sensor = sensorManager.getDefaultSensor(sensor_type);
        if (sensor !=null){
            Toast.makeText(this, "sensor working", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "not working", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        float[] values = sensorEvent.values;
        try {
            text0.setText(String.valueOf(values[0]));
        }catch (Exception e){

        }
        try {
            text1.setText(String.valueOf(values[1]));
        }catch (Exception e){

        }
        try {
            text2.setText(String.valueOf(values[2]));
        }catch (Exception e){

        }

        textName.setText(sensor.getName());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}