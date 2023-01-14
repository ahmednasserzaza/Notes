package com.example.notes.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.data.Note
import io.reactivex.rxjava3.core.Completable

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query(value = "SELECT * FROM NOTE_TABLE ORDER BY id DESC")
    suspend fun getAllNotes(): List<Note>
}