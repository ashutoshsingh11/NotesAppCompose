package com.example.notesappcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Note::class], version = 1
)
//@TypeConverters(TypeConverter::class)
abstract class NotesDatabase: RoomDatabase(){
    abstract val dao: NoteDao
}