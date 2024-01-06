package com.example.assignment1

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment1.MainActivity.GlobalData.heartVariable


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_PICK_VIDEO = 123
    private val OPEN_DOCUMENT_REQUEST_CODE = 123  // You can use any integer value you like
    var calulatedheartRate = 0


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val respButton = findViewById<Button>(R.id.accelerate)
        val heartButton = findViewById<Button>(R.id.recordButton)
        val symptomsButton = findViewById<Button>(R.id.homebutton)

        symptomsButton.setOnClickListener {

            val intent = Intent(this, SensorActivity::class.java)
            startActivity(intent)
        }

        heartButton.setOnClickListener {
            openFilePicker()
        }

        respButton.setOnClickListener {

            val text = "Analysing respiratory rate"

            val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT) // in Activity
            toast.show()

            val startSensorService = Intent(this, SensorHandlerClass::class.java)
            startService(startSensorService)

        }
    }

    companion object {
        private const val TAG = "SensorActivity"
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "video/*"

        val videoResId = R.raw.video1
        val videoView = findViewById<VideoView>(R.id.videoView)
        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + videoResId))
        videoView.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
            override fun onCompletion(mp: MediaPlayer) {

                Toast.makeText(
                    this@MainActivity,
                    "Your heart rate is $calulatedheartRate !",
                    Toast.LENGTH_SHORT
                ).show()
                val parentView = videoView.parent as ViewGroup
                parentView.removeView(videoView)
                //toast.show()
            }
        })

        //pickVideoLauncher.launch(intent)
        videoView.start()

        val text = "Analysing heart rate"

        val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT) // in Activity
        toast.show()


        val path = Uri.parse("android.resource://" + packageName + "/" + videoResId)
        val metadataRetriever = MediaMetadataRetriever()
        metadataRetriever.setDataSource(this, path)
        val duration =
            metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_FRAME_COUNT)
        var m_bitmap: Bitmap? = null
        var frameList = ArrayList<Bitmap>()

        Thread.sleep(1000)



        try {

            var aduration = duration!!.toInt()
            var i = 2
            while (i < aduration) {
                val bitmap = metadataRetriever.getFrameAtIndex(i)
                frameList.add(bitmap!!)
                i += 2
            }
        } catch (m_e: Exception) {

        } finally {
            metadataRetriever?.release()
            var redBucket: Long = 0
            var pixelCount: Long = 0
            val a = mutableListOf<Long>()
            for (i in frameList) {
                redBucket = 0
                for (y in 120 until 250) {
                    for (x in 120 until 250) {
                        val c: Int = i.getPixel(x, y)
                        pixelCount++
                        redBucket += Color.red(c) + Color.blue(c) + Color.green(c)
                    }
                }
                a.add(redBucket)
            }
            val b = mutableListOf<Long>()
            for (i in 0 until a.lastIndex - 5) {
                var temp =
                    (a.elementAt(i) + a.elementAt(i + 1) + a.elementAt(i + 2)
                            + a.elementAt(
                        i + 3
                    ) + a.elementAt(
                        i + 4
                    )) / 4
                b.add(temp)
            }
            var x = b.elementAt(0)
            var count = 0
            for (i in 1 until b.lastIndex) {
                var p = b.elementAt(i.toInt())
                if ((p - x) > 3500) {
                    count = count + 1
                }
                x = b.elementAt(i.toInt())
            }
            var rate = ((count.toFloat() / 45) * 60).toInt()
            //return (rate/2).toString()
            println("Heart Rate")
            heartVariable = (rate/2)
            println((rate / 2).toString())
        }
    }

    object GlobalData {
        var heartVariable: Int = 0
    }
}









