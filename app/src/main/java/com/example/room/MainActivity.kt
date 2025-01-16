package com.example.room

import android.os.Bundle
import android.view.View

import android.widget.EditText
import android.widget.Toast


import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone


class MainActivity : AppCompatActivity(), ContactAdapter.NoteClickListener {
    private lateinit var viewModel: ContactViewModel
    private lateinit var recyclerViewRV: RecyclerView
    private lateinit var lastNameET: EditText
    private lateinit var numberET: EditText
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        lastNameET = findViewById(R.id.lastNameET)
        numberET = findViewById(R.id.numberET)

        recyclerViewRV = findViewById(R.id.recyclerViewRV)


        recyclerViewRV.layoutManager = LinearLayoutManager(this)

        val adapter = ContactAdapter(this, this)
        recyclerViewRV.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))[ContactViewModel::class.java]

        viewModel.notes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.lastname} удален", Toast.LENGTH_LONG).show()
    }

    fun saveData(view: View) {
        val lastName = lastNameET.text.toString()
        val number = numberET.text.toString()
        val timestamp = formatMilliseconds(Date().time)
        if (lastName.isNotEmpty() || number.isNotEmpty()) {
            viewModel.insertNote(Note(lastName, number, timestamp))
            Toast.makeText(this, "$lastName, $number, $timestamp сохранены", Toast.LENGTH_LONG).show()
        }
        lastNameET.text.clear()
        numberET.text.clear()
    }

    private fun formatMilliseconds(time: Long): String {
        val timeFormat = SimpleDateFormat("EEE, HH:mm")
        timeFormat.timeZone = TimeZone.getTimeZone("GMT+02")
        return timeFormat.format(Date(time))
    }
}