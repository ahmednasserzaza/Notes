package com.example.notes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "NOTE_TABLE")
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Long,
    val content: String,
    val date:Date,
    @ColumnInfo("Importance") val isImportant:Boolean
)
