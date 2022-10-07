package com.example.sensordatareading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private static final String TAG = "MainActivity";
    private SensorManager mSensorManager;
    private Sensor mAccelerometer, mGyroscope, mGravity , mMagnetometer, mLight, mPressure, mTemperature, mHumidity, mProximity;
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

        // sensors Initialization
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

       /*   check if the sensor is supported or not   */
        if(mAccelerometer !=null) {
            mSensorManager.registerListener(MainActivity.this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Accelerometer Listener");
        } else{
            xAcc.setText("-");
            yAcc.setText("Accelerometer Sensor is not supported");
            zAcc.setText("-");
        }
        if(mGyroscope !=null) {
            mSensorManager.registerListener(MainActivity.this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Gyroscope Listener");
        } else{
            xGyro.setText("-");
            yGyro.setText("Gyroscope Sensor is not supported");
            zGyro.setText("-");
        }
        if(mGravity !=null) {
            mSensorManager.registerListener(MainActivity.this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Gravity Listener");
        } else{
            xGravity.setText("-");
            yGravity.setText("Gravity Sensor is not supported");
            zGravity.setText("-");
        }
        if(mMagnetometer !=null) {
            mSensorManager.registerListener(MainActivity.this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Magnetic Listener");
        } else{
            xMagno.setText("-");
            yMagno.setText("Magnetometer Sensor is not supported");
            zMagno.setText("-");
        }
        if(mLight !=null) {
            mSensorManager.registerListener(MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Light Listener");
        } else{
            LightVal.setText("Light Sensor is not supported");
        }
        if(mPressure !=null) {
            mSensorManager.registerListener(MainActivity.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Pressure Listener");
        } else{
            PressVal.setText("Pressure Sensor is not supported");
        }
        if(mTemperature !=null) {
            mSensorManager.registerListener(MainActivity.this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Temperature Listener");
        } else{
            TempVal.setText("Temperature Sensor is not supported");
        }
        if(mHumidity !=null) {
            mSensorManager.registerListener(MainActivity.this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Humidity Listener");
        } else{
            HumVal.setText("Humidity Sensor is not supported");
        }
        if(mProximity !=null) {
            mSensorManager.registerListener(MainActivity.this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Registered Proximity Listener");
        } else{
            ProxmVal.setText("Proximity Sensor is not supported");
        }
    }

    /*    Sensor Data showing    */
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        DecimalFormat df = new DecimalFormat("#.#####");
        // Logic For ACCELEROMETER sensor
        if( sensor.getType() == Sensor.TYPE_ACCELEROMETER  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0]+ "onSensorChanged: Y"+event.values[1]+
                            "onSensorChanged: Z"+ event.values[2]);
            String x_Val = df.format(event.values[0]);
            String y_Val = df.format(event.values[1]);
            String z_Val = df.format(event.values[2]);
            xAcc.setText("X: " + x_Val + "m/s\u00b2");
            yAcc.setText("Y: " + y_Val + "m/s²");
            zAcc.setText("Z: " + z_Val + "m/s²");
        }
        // Logic For GYROSCOPE sensor
        else if( sensor.getType() == Sensor.TYPE_GYROSCOPE  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0]+ "onSensorChanged: Y"+event.values[1]+
                    "onSensorChanged: Z"+ event.values[2]);
            String x_Val = df.format(event.values[0]);
            String y_Val = df.format(event.values[1]);
            String z_Val = df.format(event.values[2]);
            xGyro.setText("X: " + x_Val+"rad/s");
            yGyro.setText("Y: " + y_Val+"rad/s");
            zGyro.setText("Z: " + z_Val+"rad/s");
        }
        // Logic For MAGNETIC_FIELD sensor
        else if( sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0]+ "onSensorChanged: Y"+event.values[1]+
                    "onSensorChanged: Z"+ event.values[2]);
            String x_Val = df.format(event.values[0]);
            String y_Val = df.format(event.values[1]);
            String z_Val = df.format(event.values[2]);
            xMagno.setText("X: " + x_Val+"μT");
            yMagno.setText("Y: " + y_Val+"μT");
            zMagno.setText("Z: " + z_Val+"μT");
        }
        // Logic For Gravity sensor
        else if( sensor.getType() == Sensor.TYPE_GRAVITY  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0]+ "onSensorChanged: Y"+event.values[1]+
                    "onSensorChanged: Z"+ event.values[2]);
            String x_Val = df.format(event.values[0]);
            String y_Val = df.format(event.values[1]);
            String z_Val = df.format(event.values[2]);
            xGravity.setText("X: " + x_Val+"m/s²");
            yGravity.setText("Y: " + y_Val+"m/s²");
            zGravity.setText("Z: " + z_Val+"m/s²");
        }
        // Logic For LIGHT sensor
        else if( sensor.getType() == Sensor.TYPE_LIGHT  ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            LightVal.setText("value: " + event.values[0]+"lx");
        }
        // Logic For PRESSURE sensor
        else if( sensor.getType() == Sensor.TYPE_PRESSURE ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            PressVal.setText("value: " + event.values[0]+" hPa");
        }
        // Logic For TEMPERATURE sensor
        else if( sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            TempVal.setText("value: " + event.values[0]+" °C");
        }
        // Logic For HUMIDITY sensor
        else if( sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            HumVal.setText("value: " + event.values[0]+" %");
        }
        // Logic For PROXIMITY sensor
        else if( sensor.getType() == Sensor.TYPE_PROXIMITY ) {
            Log.d(TAG, "onSensorChanged: X"+ event.values[0] );
            ProxmVal.setText("value: " + event.values[0]+" cm");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}