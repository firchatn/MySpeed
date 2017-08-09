package tn.tik.myspeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener  {
    private TextView mes;
    private TextView err;
    private Sensor mySensor;
    private SensorManager SM;
    private String msg ="FIX the Mobile ";


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
        speed = (int) Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2)) -9 ;
        if (speed < 0) {
            err.setText(msg);
        }
        if (speed == 0) {
            err.setText("");
        }

        mes.setText("Speed : " + speed + " KM/H");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
