package com.example.pablo.lab09_2016;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements SensorEventListener{


    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float[] gravity;
    private float[] linear_aceleration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//revisar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                SensorManager.SENSOR_DELAY_NORMAL);



        gravity = new float[3];
        gravity[0] = 98/10;
        gravity[1] = 98/10;
        gravity[2] = 98/10;
        linear_aceleration = new float[3];
        Log.i("X: ","q onda");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i("X: ","q onda");
        synchronized (this) {
            final float alpha = (float) 0.8;
            for (int i = 0; i < 3; i++) {
                gravity[i] = alpha * gravity[i] + (1 - alpha) * event.values[i];
                linear_aceleration[i] = event.values[i] - gravity[i];
            }
            Log.i("X: ", "" + linear_aceleration[0]);
            Log.i("Y: ", "" + linear_aceleration[1]);
            Log.i("Z: ", "" + linear_aceleration[2]);
            Log.d("X: ", "" + linear_aceleration[0]);
            Log.d("Y: ", "" + linear_aceleration[1]);
            Log.d("Z: ", "" + linear_aceleration[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
