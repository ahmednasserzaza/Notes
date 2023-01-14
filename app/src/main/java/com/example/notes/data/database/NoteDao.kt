package com.example.notes.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.data.Note
import io.reactivex.rxjava3.core.Completable

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note):Completable

    @Delete
    fun deleteNote(note: Note):Completable

    @Update
    fun updateNote(note: Note):Completable

    @Query(value = "SELECT * FROM NOTE_TABLE ORDER BY id DESC")
    fun getAllNotes() : LiveData<List<Note>>
}