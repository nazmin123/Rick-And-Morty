package com.example.myapplication.data.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.myapplication.data.pojo.Location
import com.example.myapplication.data.pojo.Origin

@TypeConverters
class Converters {
    @TypeConverter
    fun fromLocation(location: Location): String {
        return location.name
    }

    @TypeConverter
    fun toLocation(name: String): Location {
        return Location(name, name)
    }
    @TypeConverter
    fun fromOrigin(location: Origin): String {
        return location.name
    }
    @TypeConverter
    fun toOrigin(name: String): Origin {
        return Origin(name, name)
    }

    @TypeConverter
    fun fromEpisode(episodeList:List<String>):String{
        return episodeList.toString()
    }

    @TypeConverter
    fun toEpisode(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n)
            } catch (e: Exception) {

            }
        }
        return result
    }

}