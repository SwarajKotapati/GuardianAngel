package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SensorActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var ratingBar: RatingBar
    private val saveRatings = IntArray(10) { 0 }
    private lateinit var appDatabase: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        // Initialize the database in the onCreate method
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "final_database" // Replace with your database name
        ).build()

        spinner = findViewById(R.id.spinner)
        ratingBar = findViewById(R.id.ratingBar)

        val homeButton = findViewById<Button>(R.id.homebutton)

        homeButton.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        // Populate the Spinner with data
        val feverArray = resources.getStringArray(R.array.symptoms_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, feverArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Set an item selection listener for the Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Update the RatingBar based on the saved rating for the selected entry
                ratingBar.rating = saveRatings[position].toFloat()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection if needed
            }
        }

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            // Save the rating for the selected entry in the SaveRatings array
            val selectedEntryIndex = spinner.selectedItemPosition
            saveRatings[selectedEntryIndex] = rating.toInt()
        }

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
//            for(num in saveRatings){
//                println(num)
//            }
//            val ratingDataDao = appDatabase.ratingDataDao()
            val tableEntryDao = appDatabase.tableEntryDao()

            val newTableEntry = TableData(heartRate = MainActivity.GlobalData.heartVariable.toString(), respRate = SensorHandlerClass.GlobalData.globalVariable.toString(), nausea = saveRatings[0], headache = saveRatings[1],
                diarrhea = saveRatings[2], soarThroat = saveRatings[3], fever = saveRatings[4], muscleAche = saveRatings[5],
                lossofSmellorTaste = saveRatings[6], cough = saveRatings[7], shortnessofBreath = saveRatings[8], feelingTired = saveRatings[9])

            GlobalScope.launch {
                tableEntryDao.insert(newTableEntry)
            }

            val text = "Data Saved !"

            val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT) // in Activity
            toast.show()
        }
    }

}
