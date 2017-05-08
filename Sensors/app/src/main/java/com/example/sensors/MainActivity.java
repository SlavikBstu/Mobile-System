package com.example.sensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity implements SensorEventListener {

    private long lastUpdate = 0;
    private float lastX, lastY, lastZ;
    private static final int SHAKE_THRESHOLD = 600;

    private SensorManager sensorManager;
    private Sensor sensorAccelerometer;

    public TextView coords, numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        coords = (TextView)findViewById(R.id.coordView);
        numbers = (TextView)findViewById(R.id.numberView);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z -lastX - lastY - lastZ)/diffTime * 10000;

                if (speed > SHAKE_THRESHOLD){
                    getRandomNumber();
                }
                lastX = x;
                lastY = y;
                lastZ = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensorAccelerometer, sensorManager.SENSOR_DELAY_NORMAL);
    }

    private void getRandomNumber(){
        ArrayList numberGenerated = new ArrayList();

        for(int i = 0; i < 10; i++){
            Random random = new Random();
            int iNumber = random.nextInt(49) + 1;

            if (!numberGenerated.contains(iNumber)){
                numberGenerated.add(iNumber);
            }
            else
                i--;
        }

        for (int i =0; i < 10; i++){
            numbers.setText(numbers.getText() + "\n" + (Integer) numberGenerated.get(i) + "\n");
            coords.setText(coords.getText() + "\n" + "X: " + lastX + " Y: " + lastY + " Z: " + lastZ + "\n");
        }
    }
}
