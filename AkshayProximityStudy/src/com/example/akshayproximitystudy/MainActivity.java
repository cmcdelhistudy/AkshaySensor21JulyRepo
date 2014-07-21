package com.example.akshayproximitystudy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	SensorManager sensmgr;
	Sensor proxSensor;
	TextView tvInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvInfo = (TextView) findViewById(R.id.tvInfo);

		sensmgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		proxSensor = sensmgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		float[] sensorValues = event.values;

		tvInfo.setText("" + sensorValues[0]);

	}

	@Override
	protected void onResume() {

		sensmgr.registerListener(this, proxSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	@Override
	protected void onPause() {
		sensmgr.unregisterListener(this);
		super.onPause();
	}

}
