package com.example.notes.data.database

import androidx.room.*
import com.example.notes.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // بمعنى ان لو عندك id نفس ال id هيعملها تجاهل اثناء انشائها ومش هيضيفها
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM NOTE_TABLE ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM NOTE_TABLE WHERE content LIKE :searchTerm ORDER BY id DESC")
    suspend fun getFilteredNotes(searchTerm:String) : List<Note>
}