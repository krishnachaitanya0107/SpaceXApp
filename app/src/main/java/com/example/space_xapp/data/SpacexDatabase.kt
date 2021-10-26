package com.example.space_xapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.space_xapp.util.Converters

@Database(entities = [Crew::class,Ship::class] , version = 1)
@TypeConverters(Converters::class)
abstract class SpacexDatabase : RoomDatabase(){

    abstract fun crewDao():CrewDao

    abstract fun shipDao():ShipDao
}