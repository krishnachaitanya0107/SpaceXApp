package com.example.space_xapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InfoDao {

    @Query("SELECT * FROM info")
    fun getAllInfo(): Flow<Info>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfo(info: Info)

    @Query("DELETE FROM info")
    suspend fun deleteAllInfo()

}