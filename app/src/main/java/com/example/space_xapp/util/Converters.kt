package com.example.space_xapp.util

import androidx.room.TypeConverter
import com.example.space_xapp.data.HeadQuarters
import com.example.space_xapp.data.LinksObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromLinksObject(linksObject: LinksObject?): String? {
        val gson = Gson()
        return gson.toJson(linksObject)
    }

    @TypeConverter
    fun fromLinksObjectString(value: String?): LinksObject? {
        val listType: Type = object : TypeToken<LinksObject?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromHeadQuartersObject(headQuarters: HeadQuarters): String? {
        val gson = Gson()
        return gson.toJson(headQuarters)
    }

    @TypeConverter
    fun fromHeadQuartersObjectString(value: String?): HeadQuarters? {
        val listType: Type = object : TypeToken<HeadQuarters?>() {}.type
        return Gson().fromJson(value, listType)
    }


}