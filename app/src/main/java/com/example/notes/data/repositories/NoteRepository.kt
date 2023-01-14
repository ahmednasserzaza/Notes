package com.example.notes.data.repositories

import com.example.notes.data.Note
import com.example.notes.data.database.NoteDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class NoteRepository {

    private val dao = NoteDatabase.getRandomInstance().noteDao()

    fun insertNewNote(note:Note):Completable{
        return dao.insertNote(note)
    }

    fun getAllNotes() = dao.getAllNotes()

}