package com.example.room

import androidx.lifecycle.LiveData

class ContactRepository(private val noteDao: NoteDao) {
    val notes: LiveData<List<Note>> = noteDao.getAllNote()

    suspend fun instert(note:Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}