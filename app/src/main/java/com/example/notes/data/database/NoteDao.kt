package com.example.notes.data.database

import androidx.room.*
import com.example.notes.data.Note
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note):Completable

    @Delete
    fun deleteNote(note: Note):Completable

    @Update
    fun updateNote(note: Note):Completable

    @Query(value = "SELECT * FROM NOTE_TABLE")
    fun getAllNotes() : Observable<List<Note>>
}