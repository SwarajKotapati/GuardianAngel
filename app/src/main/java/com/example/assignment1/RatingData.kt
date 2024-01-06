package com.example.assignment1

import androidx.room.PrimaryKey

//@Entity(tableName = "ratings_table")

data class RatingData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val entryName: String,
    val rating: String
)
