package com.example.notes.ui.notes

import androidx.lifecycle.*
import com.example.notes.data.Note
import com.example.notes.data.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.util.*

class NotesViewModel : ViewModel(), NoteInteractionListener {

    private val repository = NoteRepository()

    val newNoteText = MutableLiveData<String>()
    val searchText = MutableStateFlow("")

    val notes =  MutableLiveData<List<Note>>()

    init {
        viewModelScope.launch {
            repository.getAllNotes().collect{
                notes.postValue(it)
            }
        }

        viewModelScope.launch {
            searchText.debounce(1000).collect{
                val result = repository.getFilteredNotes(it)
                notes.postValue(result)
            }
        }
    }


    fun addNote() {
        viewModelScope.launch {
            newNoteText.value?.let {
                repository.insertNewNote(Note(0, it, Date(), false))
                newNoteText.value = ""
            }

        }
    }


}