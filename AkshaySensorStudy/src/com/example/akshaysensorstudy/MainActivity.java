package com.example.akshaysensorstudy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

	TextView tvX, tvY, tvZ;

	SensorManager sensmgr;
	Sensor accSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvX = (TextView) findViewById(R.id.tvX);
		tvY = (TextView) findViewById(R.id.tvY);
		tvZ = (TextView) findViewById(R.id.tvZ);

		sensmgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		accSensor = sensmgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;

		float accX = values[0];
		float accY = values[1];
		float accZ = values[2];

		tvX.setText("X : " + accX);
		tvY.setText("Y : " + accY);
		tvZ.setText("Z : " + accZ);

		if (accX >= 18 || accY >= 18 || accZ >= 18) {
			Toast.makeText(getBaseContext(), "I am shaked !",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onResume() {

		sensmgr.registerListener(this, accSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	@Override
	protected void onPause() {
		sensmgr.unregisterListener(this);
		super.onPause();
	}
}
