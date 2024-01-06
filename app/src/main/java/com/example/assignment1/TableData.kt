package com.example.assignment1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "final_table")

data class TableData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val heartRate: String,
    val respRate : String,
    val nausea: Int,
    val headache : Int,
    val diarrhea : Int,
    val soarThroat : Int,
    val fever : Int,

    val muscleAche : Int,
    val lossofSmellorTaste : Int,
    val cough : Int,
    val shortnessofBreath : Int,
    val feelingTired : Int,

)



