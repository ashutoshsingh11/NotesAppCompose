package com.example.notesappcompose.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.notesappcompose.data.Note

data class NoteState(
    val notes: List<Note> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),
    val time : MutableState<Long?> = mutableStateOf(null),
    val id:MutableState<Int> = mutableIntStateOf(0)

)