package com.example.assignment1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TableEntryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tableData: TableData)

    @Query("SELECT * FROM final_table WHERE fever = :entryName")
    suspend fun getRatingForEntry(entryName: Int): TableData?
}
