package com.example.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Note
import com.example.notes.data.repositories.NoteRepository
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel(), NoteInteractionListener {

    private val repository = NoteRepository()

    val newNoteText = MutableLiveData<String>()

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    init {
        loadAllNotes()
    }

    fun addNote() {
        viewModelScope.launch {
            newNoteText.value?.let {
                repository.insertNewNote(Note(0, it, "14/1/2023", false))
            }
        }
    }

    private fun loadAllNotes() {
        viewModelScope.launch {
            val allNotes = repository.getAllNotes()
            _notes.postValue(allNotes)
        }

    }
}