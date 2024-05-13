package com.example.notesappcompose.presentation

import com.example.notesappcompose.data.Note


sealed interface NotesEvent {
    object SortNotes: NotesEvent
    data class DeleteNote(val note: Note): NotesEvent
    data class SaveNote(
        val id :Int,
        val title: String,
        val description: String
    ): NotesEvent

    data class UpdateNote( val note:Note ): NotesEvent
}
