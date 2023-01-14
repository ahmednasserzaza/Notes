package com.example.notes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.data.Note
import com.example.notes.data.repositories.NoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NotesViewModel : ViewModel() {

    val newNoteText = MutableLiveData<String>()

    private val _note = MutableLiveData<List<Note>>()
    val note:LiveData<List<Note>> = _note

    private val repository = NoteRepository()

    init {
        loadData()
    }

    fun addNote(){
        newNoteText.value?.let {
            repository.insertNewNote(Note(0 , it , "14/1/2023" , false))
                .subscribeOn(Schedulers.io())
                .subscribe()
            newNoteText.postValue("")
        }
    }

    private fun loadData(){
        repository.getAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onGetNotes,
                this::onNotesFailed
            )
    }

    private fun onGetNotes(listOfNotes:List<Note>){
        _note.postValue(listOfNotes)
    }
    private fun onNotesFailed(t:Throwable){
        t.message
    }
}