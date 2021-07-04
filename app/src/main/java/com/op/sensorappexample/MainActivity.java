package com.op.sensorappexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        RecyclerView sensorView = findViewById(R.id.sensorsView);
        sensorView.setLayoutManager(new LinearLayoutManager(this));
        sensorView.setAdapter(new SensorAdapter(this, android.R.layout.simple_list_item_2,sensorList));
    }

    private class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.Holder> {

        private final Context c;
        private final LayoutInflater inflater;
        private final int layout;
        private final List<Sensor> sensorList;

        public SensorAdapter(Context c, int layout, List<Sensor> sensorList) {
            this.c = c;
            inflater = LayoutInflater.from(c);
            this.layout = layout;
            this.sensorList = sensorList;
        }

        @NonNull

        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(inflater.inflate(layout,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.SensorAdapter.Holder holder, int position) {
            Sensor sensor = sensorList.get(position);
            holder.t1.setText(sensor.getName());
            holder.t2.setText(sensor.getVendor());
        }

        @Override
        public int getItemCount() {
            return sensorList.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView t1,t2;
            public Holder(@NonNull View itemView) {
                super(itemView);
                t1 = itemView.findViewById(android.R.id.text1);
                t2 = itemView.findViewById(android.R.id.text2);
                itemView.setOnClickListener(view -> {
                    Intent i = new Intent(c,SensorActivity.class);
                    Sensor sensor = sensorList.get(getAdapterPosition());
                    i.putExtra("sensor",sensor.getType());
                    startActivity(i);
                });

            }
        }
    }
}