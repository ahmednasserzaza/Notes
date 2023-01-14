package com.example.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.data.Note
import com.example.notes.data.repositories.NoteRepository
import io.reactivex.rxjava3.schedulers.Schedulers

class NotesViewModel : ViewModel(), NoteInteractionListener {

    private val repository = NoteRepository()

    val newNoteText = MutableLiveData<String>()

    val notes: LiveData<List<Note>> = repository.getAllNotes()


    fun addNote() {
        newNoteText.value?.let {
            repository.insertNewNote(Note(0, it, "14/1/2023", false))
                .subscribeOn(Schedulers.io())
                .subscribe()
            newNoteText.postValue("")
        }
    }

}