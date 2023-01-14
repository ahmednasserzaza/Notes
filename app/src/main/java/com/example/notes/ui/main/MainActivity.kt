package com.example.notes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.notes.R
import com.example.notes.data.database.NoteDatabase
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.ui.notes.NotesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        NoteDatabase.getInstance(applicationContext)

    }
}