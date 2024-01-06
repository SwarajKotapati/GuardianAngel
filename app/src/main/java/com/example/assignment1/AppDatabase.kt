package com.example.assignment1

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TableData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
//    abstract fun ratingDataDao(): RatingDataDao
    abstract fun tableEntryDao(): TableEntryDao
}
