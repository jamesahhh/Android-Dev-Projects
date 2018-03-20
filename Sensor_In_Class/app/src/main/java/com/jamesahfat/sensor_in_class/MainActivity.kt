package com.jamesahfat.sensor_in_class

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {


    private var mSensorManager : SensorManager? = null
    private var mGyroscope: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        mGyroscope = mSensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        mSensorManager!!.registerListener(this,mGyroscope,SensorManager.SENSOR_DELAY_NORMAL)

        setContentView(R.layout.activity_main)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {

        if(sensorEvent!!.values[0] == 0.0f && sensorEvent!!.values[1] == 0.0f && sensorEvent!!.values[2] != 0.0f) { // anticlockwise
            window.decorView.setBackgroundColor(Color.BLUE)
        } else if(sensorEvent!!.values[0] == 0.0f && sensorEvent!!.values[1] != 0.0f && sensorEvent!!.values[2] == 0.0f){ // clockwise
            window.decorView.setBackgroundColor(Color.YELLOW)
        } else if (sensorEvent!!.values[0] != 0.0f && sensorEvent!!.values[1] == 0.0f && sensorEvent!!.values[2] == 0.0f) {
            window.decorView.setBackgroundColor(Color.GREEN)
        }
        else {

        }

            Log.i("X_VALUE", sensorEvent!!.values[0].toString())
            Log.i("Y_VALUE", sensorEvent!!.values[1].toString())
            Log.i("Z_VALUE", sensorEvent!!.values[2].toString())


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        x.text = event!!.x.toString()
        y.text = event!!.y.toString()
        return false

    }
}
