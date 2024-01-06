package com.example.assignment1

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.widget.Toast
import com.example.assignment1.SensorHandlerClass.GlobalData.globalVariable
import java.lang.Math.sqrt
import kotlin.math.abs

class SensorHandlerClass: Service(), SensorEventListener {

    private lateinit var accelManager: SensorManager
    private var senseAccel: Sensor? = null

    private val accelValuesX = FloatArray(550)
    private val accelValuesY = FloatArray(550)
    private val accelValuesZ = FloatArray(550)

    private var index = 0
    private var k = 0

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {

        println("Here1")
        val mySensor = sensorEvent.sensor

        if (mySensor.type == Sensor.TYPE_ACCELEROMETER) {
            index++
            accelValuesX[index] = sensorEvent.values[0]
            accelValuesY[index] = sensorEvent.values[1]
            accelValuesZ[index] = sensorEvent.values[2]

            if (index >= 450) {
                index = 0
                accelManager.unregisterListener(this)
                callRespiratoryCalculator()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle changes in sensor accuracy here, if needed
    }

    override fun onCreate() {

        println("Here2")
        super.onCreate()

        accelManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        senseAccel = accelManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        accelManager.registerListener(this, senseAccel, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun callRespiratoryCalculator():Int {

        println("Here3")
        var previousValue = 0f
        var currentValue = 0f
        previousValue = 10f
        k=0
        for (i in 11..450) {
            currentValue = sqrt(
                Math.pow(accelValuesZ[i].toDouble(), 2.0) + Math.pow(
                    accelValuesX[i].toDouble(),
                    2.0
                ) + Math.pow(accelValuesY[i].toDouble(), 2.0)
            ).toFloat()
            if (abs(x = previousValue - currentValue) > 0.15) {
                k++
            }
            previousValue = currentValue
        }
        val ret= (k/45.00)
        val res = (ret*30).toInt()

        globalVariable = res

        println(res)

        val text = "Your respiratory rate is $res"

        val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT) // in Activity
        toast.show()
        return res
    }

    object GlobalData {
        var globalVariable: Int = 0
    }


}