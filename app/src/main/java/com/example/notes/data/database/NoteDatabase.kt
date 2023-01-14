package com.example.notes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.data.Note

@Database(entities = [Note::class] , version = 1 , exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object{

        private const val DATABASE_NAME = "noteDatabase"
        @Volatile private var instance : NoteDatabase? = null

        fun getInstance(context: Context) : NoteDatabase {
            return instance ?: synchronized(this) {buildDatabase(context).also { instance = it }}
        }

        fun getRandomInstance():NoteDatabase{
            return instance!!
        }

        private fun buildDatabase(context: Context): NoteDatabase{
            return Room.databaseBuilder(context , NoteDatabase::class.java , DATABASE_NAME).build()
        }
    }
}