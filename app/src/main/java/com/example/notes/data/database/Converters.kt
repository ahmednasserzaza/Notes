package com.example.notes.data.database

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun dateToLong(date: Date):Long{
        return date.time
    }

    @TypeConverter
    fun longToDate(long:Long):Date{
        return Date(long)
    }
}