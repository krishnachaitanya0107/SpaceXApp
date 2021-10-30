package com.example.space_xapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.space_xapp.util.Converters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "ship")
data class Ship(
    @PrimaryKey var id: String,
    var name: String,
    var image: String?,
    var active:Boolean,
    var year_built:Int,
    @TypeConverters(Converters::class)
    var roles:ArrayList<String>,
    var link:String?
):Parcelable