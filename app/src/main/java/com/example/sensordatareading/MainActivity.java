package com.example.sensordatareading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private static final String TAG = "MainActivity";
    private SensorManager mSensorManager;
    private Sensor mAccelerometer, mGyroscope, mGravity , mMagno, mLight, mPressure, mTemp, mHumd, mProximity;
    TextView xAcc, yAcc, zAcc, xGyro, yGyro, zGyro, xGravity, yGravity, zGravity,
            xMagno, yMagno, zMagno, LightVal, PressVal, TempVal, HumVal, ProxmVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        xAcc = findViewById(R.id.acc_xVal);
        yAcc = findViewById(R.id.acc_yVal);
        zAcc = findViewById(R.id.acc_zVal);
        xGyro = findViewById(R.id.gyro_xVal);
        yGyro = findViewById(R.id.gyro_yVal);
        zGyro = findViewById(R.id.gyro_zVal);
        xGravity = findViewById(R.id.grav_xVal);
        yGravity = findViewById(R.id.grav_yVal);
        zGravity = findViewById(R.id.grav_zVal);
        xMagno = findViewById(R.id.magno_xVal);
        yMagno = findViewById(R.id.magno_yVal);
        zMagno = findViewById(R.id.magno_zVal);
        LightVal = findViewById(R.id.lightID);
        PressVal = findViewById(R.id.pressID);
        TempVal = findViewById(R.id.tempID);
        HumVal = findViewById(R.id.humidID);
        ProxmVal = findViewById(R.id.proxmID);

        Log.d(TAG, "OnCreate Initializing Sensor Service");
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mMagno = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mHumd = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(mAccelerometer !=null) {
            mSensorManager.registerListener(MainActivity.this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Accelerometer Listener");
        }else {
            xAcc.setText("-");
            yAcc.setText("Accelerometer Sensor is not supported");
            zAcc.setText("-");
        }
        if(mGyroscope !=null) {
            mSensorManager.registerListener(MainActivity.this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Gyroscope Listener");
        }else {
            xGyro.setText("-");
            yGyro.setText("Gyroscope Sensor is not supported");
            zGyro.setText("-");
        }
        if(mGravity !=null) {
            mSensorManager.registerListener(MainActivity.this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Gravity Listener");
        }else {
            xGravity.setText("-");
            yGravity.setText("Gravity Sensor is not supported");
            zGravity.setText("-");
        }
        if(mMagno !=null) {
            mSensorManager.registerListener(MainActivity.this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Magnetic Listener");
        }else {
            xMagno.setText("-");
            yMagno.setText("Magnetic Sensor is not supported");
            zMagno.setText("-");
        }
        if(mLight !=null) {
            mSensorManager.registerListener(MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Light Listener");
        }else {
            LightVal.setText("Light Sensor is not supported");
        }
        if(mPressure !=null) {
            mSensorManager.registerListener(MainActivity.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Pressure Listener");
        }else {
            PressVal.setText("Pressure Sensor is not supported");
        }
        if(mTemp !=null) {
            mSensorManager.registerListener(MainActivity.this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Temperature Listener");
        }else {
            TempVal.setText("Temperature Sensor is not supported");
        }
        if(mHumd !=null) {
            mSensorManager.registerListener(MainActivity.this, mHumd, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Humidity Listener");
        }else {
            HumVal.setText("Humidity Sensor is not supported");
        }
        if(mProximity !=null) {
            mSensorManager.registerListener(MainActivity.this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Proximity Listener");
        }else {
            ProxmVal.setText("Proximity Sensor is not supported");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        // Logic For ACCELEROMETER sensor
        if( sensor.getType() == Sensor.TYPE_ACCELEROMETER  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0]+ "onSensorChanged: Y"+event.values[1]+
                            "onSensorChanged: Z"+ event.values[2]);
            xAcc.setText("Xval: " + event.values[0]+"m/s2");
            yAcc.setText("Yval: " + event.values[1]+"m/s2");
            zAcc.setText("Zval: " + event.values[2]+"m/s2");
        }
        // Logic For GYROSCOPE sensor
        else if( sensor.getType() == Sensor.TYPE_GYROSCOPE  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0]+ "onSensorChanged: Y"+event.values[1]+
                    "onSensorChanged: Z"+ event.values[2]);
            xGyro.setText("Xval: " + event.values[0]+"rad/s");
            yGyro.setText("Yval: " + event.values[1]+"rad/s");
            zGyro.setText("Zval: " + event.values[2]+"rad/s");
        }
        // Logic For MAGNETIC_FIELD sensor
        else if( sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0]+ "onSensorChanged: Y"+event.values[1]+
                    "onSensorChanged: Z"+ event.values[2]);
            xMagno.setText("Xval: " + event.values[0]+"μT");
            yMagno.setText("Yval: " + event.values[1]+"μT");
            zMagno.setText("Zval: " + event.values[2]+"μT");
        }
        // Logic For Gravity sensor
        else if( sensor.getType() == Sensor.TYPE_GRAVITY  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0]+ "onSensorChanged: Y"+event.values[1]+
                    "onSensorChanged: Z"+ event.values[2]);
            xGravity.setText("Xval: " + event.values[0]+"m/s2");
            yGravity.setText("Yval: " + event.values[1]+"m/s2");
            zGravity.setText("Zval: " + event.values[2]+"m/s2");
        }
        // Logic For LIGHT sensor
        else if( sensor.getType() == Sensor.TYPE_LIGHT  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            LightVal.setText("value: " + event.values[0]+"lx");
        }
        // Logic For PRESSURE sensor
        else if( sensor.getType() == Sensor.TYPE_PRESSURE ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            PressVal.setText("value: " + event.values[0]+"hPa");
        }
        // Logic For TEMPERATURE sensor
        else if( sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            TempVal.setText("value: " + event.values[0]+"°C");
        }
        // Logic For HUMIDITY sensor
        else if( sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            HumVal.setText("value: " + event.values[0]+"%");
        }
        // Logic For PROXIMITY sensor
        else if( sensor.getType() == Sensor.TYPE_PROXIMITY ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            ProxmVal.setText("value: " + event.values[0]+"cm");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}