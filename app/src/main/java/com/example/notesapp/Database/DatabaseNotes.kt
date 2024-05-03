package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.NoteDao
import com.example.notesapp.EntityModel.Notes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class DatabaseNotes: RoomDatabase() {
    abstract fun myNotesDao():NoteDao
    companion object{

        @Volatile
        var INSTANCE:DatabaseNotes?=null

        @OptIn(InternalCoroutinesApi::class)
         fun getDatabaseINstance(context: Context):DatabaseNotes{

            val temInstance= INSTANCE
            if (temInstance!=null){
                return temInstance
            }
            synchronized(this){
                val roomDatabaseInstance=Room
                    .databaseBuilder(context,DatabaseNotes::class.java,"Notes")
                    .allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance

                return roomDatabaseInstance
            }
        }
    }

}