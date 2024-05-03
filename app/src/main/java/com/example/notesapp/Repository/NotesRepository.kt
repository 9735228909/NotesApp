package com.example.notesapp.Repository

import androidx.lifecycle.LiveData
import com.example.notesapp.Dao.NoteDao
import com.example.notesapp.EntityModel.Notes

class NotesRepository(val dao: NoteDao) {

    fun getallNotes():LiveData<List<Notes>> = dao.getNotes()

    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()
    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()


    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}