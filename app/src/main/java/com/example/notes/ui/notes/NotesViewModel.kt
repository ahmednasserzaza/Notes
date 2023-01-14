package com.example.notes.ui.notes

import androidx.lifecycle.*
import com.example.notes.data.Note
import com.example.notes.data.repositories.NoteRepository
import kotlinx.coroutines.launch
import java.util.*

class NotesViewModel : ViewModel(), NoteInteractionListener {

    private val repository = NoteRepository()

    val newNoteText = MutableLiveData<String>()

    val notes: LiveData<List<Note>> = repository.getAllNotes().asLiveData()


    fun addNote() {
        viewModelScope.launch {
            newNoteText.value?.let {
                repository.insertNewNote(Note(0, it, Date(), false))
                newNoteText.value = ""
            }

        }
    }


}