package com.example.space_xapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CrewDao {

    @Query("SELECT * FROM crew")
    fun getAllCrew(): Flow<List<Crew>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrewData(crewData:List<Crew>)

    @Query("DELETE FROM crew")
    suspend fun deleteAllCrew()

}