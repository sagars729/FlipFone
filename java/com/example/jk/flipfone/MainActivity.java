package com.example.jk.flipfone;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.button;
import static android.R.attr.fragment;
import static android.R.attr.gravity;
import static android.R.attr.start;
import static android.R.id.button1;
import static com.example.jk.flipfone.R.id.score;
import static com.example.jk.flipfone.R.layout.classic;
import static com.example.jk.flipfone.R.layout.play;

public class MainActivity extends FragmentActivity implements classicFrag.classicResponse , PlayFrag.OnButtonPressedListener, endFrag.endResponse{
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;
    boolean recordStatus = false;
    float totDist = (float) 0.0;
    int count = 0;
    boolean allow = true;
    classicFrag daClassic=new classicFrag();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscopeSensor == null) {
            Toast.makeText(this, "The device has no Gyroscope =(", Toast.LENGTH_SHORT).show();
            finish();
        }

        gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float zVal = sensorEvent.values[1];
                //float t = sensorEvent.timestamp;
                //Log.i("time", t + "");
                if(allow) {
                    if (recordStatus == true) {
                        daClassic.startControl("#00ff00", false);
                    } else if (Math.abs(zVal) > 3) {
                        daClassic.startControl("#ff0000", false);
                    } else {
                        daClassic.startControl("#000000", true);
                    }
                }
                if (recordStatus) {
                    if (zVal < -3) {
                        //Log.i("we in this bish","f wrong wichu");
                        count=1;
                        //if (zVal < 0){
                            totDist += (Math.abs(zVal) / 5);
                        //}
                    }
                    else{
                        if(count>0){
                            recordStatus=false;
                            count = 0;
                            daClassic.changeFlips((float) (totDist / (2 * Math.PI)));
                            daClassic.delay();
                            daClassic.changeRange();
                        }
                    }
                }
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            PlayFrag firstFragment = new PlayFrag();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void getSensorData()
    {
        recordStatus=true;
        count = 0;
        totDist = (float) 0.0;
    }
    @Override
    public void goToEnd(int x, double y)
    {
        String score;
        String highScore;
        score = x+"";
        SharedPreferences prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(prefs.contains("highScore"))
        {
            int temp = prefs.getInt("highScore", 0);
            if(x>temp) {
                temp = x;
                editor.putInt("highScore",temp);
                editor.commit();
            }
        }
        else
        {
            editor.putInt("highScore", x);
            editor.commit();
        }
        highScore = prefs.getInt("highScore",0) + "";
        endFrag fragObj = new endFrag();
        fragObj.setScores(score,highScore, y+"");
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragObj);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void changeAllow(){
        if(allow==true)
            allow=false;
        else
            allow=true;
    }


    @Override
    public void onButtonSelected() {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, daClassic);
        transaction.addToBackStack(null);
        transaction.commit();
     }

    @Override
    public void comeBack(int x) {
        if(x==0){
            daClassic = new classicFrag();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, daClassic);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else{
            PlayFrag newStart = new PlayFrag();
            daClassic = new classicFrag();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newStart);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }
}