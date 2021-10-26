package com.example.space_xapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crew")
data class Crew(
    @PrimaryKey var id: String,
    var name: String,
    var agency: String,
    var status: String,
    var wikipedia: String,
    var image: String
)