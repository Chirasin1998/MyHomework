package com.example.frongapi;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2_Compass2 extends AppCompatActivity implements SensorEventListener {

private ImageView imageView;
private float[] mGravity = new float[3];
private float[] mGeomagnetic = new float[3];
private float azimuth = 0f;
private float currectAzimuth = 0f;
private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2__compass2);

        imageView = (ImageView)findViewById(R.id.compass);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
    final float alpha = 0.97f;
    synchronized (this){
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity[0] = alpha*mGravity[0]+(1-alpha)*sensorEvent.values[0];
            mGravity[1] = alpha*mGravity[1]+(1-alpha)*sensorEvent.values[1];
            mGravity[2] = alpha*mGravity[2]+(1-alpha)*sensorEvent.values[2];
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            mGeomagnetic[0] = alpha*mGeomagnetic[0]+(1-alpha)*sensorEvent.values[0];
            mGeomagnetic[1] = alpha*mGeomagnetic[1]+(1-alpha)*sensorEvent.values[1];
            mGeomagnetic[2] = alpha*mGeomagnetic[2]+(1-alpha)*sensorEvent.values[2];
        }
        float R[] = new float[9];
        float I[] = new float[9];
        boolean  success =  SensorManager.getRotationMatrix(R,I,mGravity,mGeomagnetic);
        if (success){
            float orientation[] = new float[3];
            SensorManager.getOrientation(R,orientation);
            azimuth = (float)Math.toDegrees(orientation[0]);
            azimuth = (azimuth+360)%360;

            //A N I M A T I O N//
            Animation anime = new RotateAnimation(-currectAzimuth,-azimuth,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            currectAzimuth = azimuth;
            anime.setDuration(500);
            anime.setRepeatCount(0);
            anime.setFillAfter(true);
            imageView.startAnimation(anime);
        }
    }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}