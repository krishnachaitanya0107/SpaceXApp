package com.example.space_xapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShipDao {

    @Query("SELECT * FROM ship")
    fun getAllShips(): Flow<List<Ship>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShipData(shipData:List<Ship>)

    @Query("DELETE FROM ship")
    suspend fun deleteAllShipsData()

}