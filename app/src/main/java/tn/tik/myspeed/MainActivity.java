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
    private Sensor mySensor;
    private SensorManager SM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        mes = (TextView)findViewById(R.id.affiche);



    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float speed= 0;
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        

        mes.setText("x : " + speed);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
