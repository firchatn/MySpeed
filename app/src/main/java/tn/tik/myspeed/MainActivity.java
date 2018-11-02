package tn.tik.myspeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener  {
    private TextView mes;
    private TextView err;
    private TextView maxx;
    private Sensor mySensor;
    private SensorManager SM;
    private String msg ="FIX the Mobile ";
    private static int MaxSpeed = 0 ;
    int s;
    public static final String EXTRA_MESSAGE = "SpeedMessage";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        mes = (TextView)findViewById(R.id.affiche);
        err = (TextView)findViewById(R.id.error);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        int speed= 0;
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        /* create SharedPreferences to save max speed value */
        SharedPreferences prefs = this.getSharedPreferences(
                "tn.tik.myspeed", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();


        speed = (int) Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2)) -9 ;
        if (speed < 0) {
            err.setText(msg);
        }
        if (speed == 0) {
            err.setText("");
        }
         s = prefs.getInt("max", 0);
        if (speed > s){
            MaxSpeed = speed;
            editor.putInt("max", MaxSpeed);
            editor.commit();
            s = prefs.getInt("max", 0);

        }

        s = prefs.getInt("max", 0);


        mes.setText("Speed : " + speed + " KM/H");
    }

    public void details(View view){
        Intent intent = new Intent(this, DetailsSpeed.class);
        String ss = Integer.toString(s);
        intent.putExtra(EXTRA_MESSAGE, ss);
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
