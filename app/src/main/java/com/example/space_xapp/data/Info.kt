package com.example.space_xapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.space_xapp.util.Converters
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "info")
data class Info(
    @PrimaryKey var id: String,
    var name: String,
    var founded: Int,
    var founder: String,
    var ceo: String,
    var employees: Int,
    @TypeConverters(Converters::class)
    var headquarters: HeadQuarters,
    var summary: String,
    @TypeConverters(Converters::class)
    var links: LinksObject
) : Parcelable

@Parcelize
data class LinksObject(
    var website: String,
    var twitter: String,
    var flickr: String
) : Parcelable

@Parcelize
data class HeadQuarters(
    var address: String,
    var city: String,
    var state: String
) : Parcelable
